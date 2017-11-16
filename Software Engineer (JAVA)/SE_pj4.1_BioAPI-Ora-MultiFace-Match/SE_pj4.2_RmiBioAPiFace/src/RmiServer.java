import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.io.*;
import java.rmi.RMISecurityManager;
import java.util.List;

// GUI
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

// MfMatcher
import com.neurotechnology.NImages.NImage;
import com.neurotechnology.NLicensing.NLicensing;
import com.neurotechnology.NLicensing.LicensingConfig;
import com.neurotechnology.NMatcher.NMatcher;
import com.neurotechnology.Faces.DetectionDetails;
import com.neurotechnology.Faces.Eyes;
import com.neurotechnology.Faces.Face;
import com.neurotechnology.Faces.NLExtractor;
import com.neurotechnology.Templates.NLTemplate;

public class RmiServer extends UnicastRemoteObject
    implements RmiInterface {
	
	RmiServer ServerObj;
	RecordDB rcd_db;
	
	// mfmatcher
	private NLExtractor extractor;
	private NMatcher matcher;
	private NImage multiFaceImage, refSingleFaceImage;
	// multiple faces related objects
	private DetectionDetails[] detdet;
	private NLTemplate[] templates;
	private int extractedTemplates = 0;
	// single face related object
	private DetectionDetails refDetdet;
	private NLTemplate refTemplate;
	

	private static final long serialVersionUID = 5289900962559714948L;
	//static MultipleFaceMatcher mfMatcher;

	public RmiServer() throws RemoteException {
        super();
        // Obtain License directly
        //NLicensing.licenseObtain("/local", "5000", "SingleComputerLicense:VLExtractor,SingleComputerLicense:VLMatcher,SingleComputerLicense:VLExtended,SingleComputerLicense:VLBSS");
		LicensingConfig licConfig = LicensingConfig.getInstance();
		@SuppressWarnings("unused")
		String licensesString = licConfig.getLicenseString("FacesExtractor", "FacesMatcher");
		
        try {
			@SuppressWarnings("unused")
			RecordDB rcd_db = new RecordDB();
		} catch (Exception e) {
			e.printStackTrace();
		}				
		
		
		/**
		 * Step 1. All the parameters of extraction routines are stored in an
		 * extractor object and all the parameters of matching routines are
		 * stored in a matcher object. Thus both of those objects must be
		 * initialized before usage.
		 */
		try {
			try {
				extractor = new NLExtractor();
			} catch (Exception ex) {
				throw new Exception("Unable to create extractor object.\n" + ex.getMessage() + "\n");
			}
			try {
				matcher = new NMatcher();
			} catch (Exception ex) {
				throw new Exception("Unable to create matcher object.\n" + ex.getMessage() + "\n");
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage() + "Program will exit.\n", " Error message",
					JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}
        
    }	// Rmiserver()
	
    public String getMessage() {
        String message = "Server Says, 'Hi, Client!' (This is a message sent from Server to Client)";
        return message;
    }	// printMessage()
    
    public void sendMessage(String SentMessage) {
        System.out.println(SentMessage);
    }	// printMessage()
    
    public void sendByteArrayImage(byte[] SentByteArray, String facename) {
    	@SuppressWarnings("unused")
		String imagesavedpath = null;
    	String imagesavename = facename;
    	// call converter and save binary image on remote side
	    ImageConverter ImageTool = new ImageConverter(); // instance of image converter
	    try {
			imagesavedpath = ImageTool.bytesToImages(SentByteArray, imagesavename); // execute and returned path
		} catch (FileNotFoundException e) {
			System.out.println("File not found at sendByteArray()");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    }	// sendByteArray()
    
    
    ///////////////////////////////////////////////////////////////////////////////////////////  
    // The followings are written by Dr. Bon Sy's RmiClient mfmatcher
    ///////////////////////////////////////////////////////////////////////////////////////////  
    
	public boolean isSingleFaceImageExtracted() {
		return refTemplate != null;
	}

	public boolean isMultiFaceImageExtracted() {
		return extractedTemplates > 0;
	}

	public ImageIcon getMultiFaceImageIcon() throws Exception {
		return multiFaceImage != null ? multiFaceImage.getImageIcon() : null;
	}

	public ImageIcon getSingleFaceImageIcon() throws Exception {
		return refSingleFaceImage != null ? refSingleFaceImage.getImageIcon() : null;
	}

	public ImageIcon getScaledRefImageIcon() throws Exception {
		if (refSingleFaceImage == null)
			return null;
		BufferedImage bfImage = (BufferedImage) getFaceDetectedSingleFaceImageIcon().getImage();
		int height = bfImage.getHeight(), width = bfImage.getWidth();
		width /= (int) Math.ceil(bfImage.getWidth() / 240.0);
		height /= (int) Math.ceil(bfImage.getHeight() / 200.0);
		return new ImageIcon(bfImage.getScaledInstance(-1, height, Image.SCALE_FAST));
	}

	public ImageIcon getFaceDetectedSingleFaceImageIcon() throws Exception {
		if (refSingleFaceImage == null)
			return null;
		BufferedImage bufImage = refSingleFaceImage.getBufferedImage();
		if (refDetdet != null) {
			return new ImageIcon(getFaceDetectedImageIcon(bufImage, new DetectionDetails[] { refDetdet }, true));
		} else {
			return new ImageIcon(bufImage);
		}
	}

	public ImageIcon getFaceDetectedMultiFaceImageIcon() throws Exception {
		
		if (multiFaceImage == null)
			return null;
		BufferedImage bufImage = multiFaceImage.getBufferedImage();
		if (detdet != null) {
			return new ImageIcon(getFaceDetectedImageIcon(bufImage, detdet, false));
		} else {
			return new ImageIcon(bufImage);
		}
	}

	public String getMultiFaceDetectionDetails() {
		int faceCount = detdet == null ? 0 : detdet.length;
		String fullInfo = "Number of faces detected: " + faceCount + ".\n";
		for (int i = 0; i < faceCount; i++) {
			fullInfo += "Face Nr. " + (i + 1) + " information:\n";
			fullInfo += getFaceDetectionDetails(detdet[i]);
			if (templates == null)
				continue;
			if (templates[i] == null)
				fullInfo += "NO face features template extracted from face Nr. " + (i + 1) + ".\n";
			else {
				fullInfo += "Face features template extracted and compressed to \n";
			}
		}
		return fullInfo + "Total number of templates extracted: " + extractedTemplates + ".\n";
	}

	public String getSingleFaceDetectionDetails() {
		String fullInfo = getFaceDetectionDetails(refDetdet);
		if (refTemplate == null)
			fullInfo += "NO face features reference template extracted.\n";
		else {
			fullInfo += "Face features template extracted to \n";
		}
		return fullInfo;
	}

	/**
	 * Step 2. Load a multiple face image from the specified file.
	 * @throws Exception 
	 */
	public void loadMultiFaceImage(String fileName) throws Exception {
		detdet = null;
		templates = null;
		extractedTemplates = 0;
		multiFaceImage = loadImageFromFile(fileName);
	}

	/**
	 * Step 3. Extract face features templates from the specified multiple face
	 * image file. Note: All the extracted templates must be compressed.
	 * @throws Exception 
	 */
	public void extractMultiFaceImage() throws Exception {
		extractUsingEyes(multiFaceImage);
	}

	/**
	 * Step 4. Upload single face (reference) image from the specified file.
	 * @throws Exception 
	 */
	public void loadSingleFaceImage(String fileName) throws Exception {
		refTemplate = null;
		refDetdet = null;
		refSingleFaceImage = loadImageFromFile(fileName);
	}

	/**
	 * Step 5. Extract face features template from the specified single image
	 * file. Note: Single face (reference) template must remain uncompressed.
	 * @param fileName 
	 * @throws Exception 
	 */
	public void extractSingleFaceImage() throws Exception {
		extractAutomatically(refSingleFaceImage);
	}

	private NImage loadImageFromFile(String fileName) throws Exception {
		NImage image = null;
		try {
			image = NImage.loadFromFile(fileName);
			return image;
		} catch (Exception e) {
			throw new Exception("Unable to upload an image from the specified file.\n"
					+ "File format may be unsupported.\n" + e.getMessage());
		}
	}

	/**
	 * Step 6. Match extracted compressed face features templates with
	 * uncompressed reference face features template.
	 * @throws Exception 
	 */
	public String matchMultipleFaces() throws Exception {
		String infoStr = "";
		if (extractedTemplates == 0)
			throw new Exception("No templates specified to be matched.\n");
		else if (refTemplate == null)
			throw new Exception("No reference template specified to match with.\n");
		try {
			int templCount = templates == null ? 0 : templates.length;
			double score = 0;
			/**
			 * 6.1 Start matching process by providing the reference template.
			 */
			matcher.identifyStart(refTemplate);
			infoStr += "Templates Matching results:\n";
			for (int i = 0; i < templCount; i++) {
				try {
					if (templates[i] != null) {
						/**
						 * 6.2. Match all extracted templates from multiple face
						 * image with reference template.
						 */
						score = matcher.identifyNext(templates[i]);
						infoStr += "\t template[" + (i + 1) + String.format("] scored %.4f\n", score);
					} else {
						infoStr += "\t template[" + (i + 1) + "] = null\n";
					}
				} catch (Exception e) {
					infoStr += "\t template[" + (i + 1)
							+ String.format("] identification aborted:\n" + e.getMessage() + "\n");
				}
			}
			/**
			 * 6.3. Don't forget to finish the matching process to free
			 * allocated resources.
			 */
			matcher.identifyEnd();
			return infoStr;
		} catch (Exception ex) {
			throw new Exception("Matching process failed.\n" + ex.getMessage());
		}
	}

	/**
	 * The simplest way to extract face feature template from an image is to use
	 * function that will automatically detect face in an image and return face
	 * features template and face detection results.
	 * 
	 * Note 1: There is no need to convert an image to grayscale format prior to
	 * calling VeriLook SDK functions for it. The convertion is perfomed on the
	 * C++ wrapper side. Note 2: Single reference is left uncompressed.
	 */
	private void extractAutomatically(NImage image) throws Exception {
		try {
			if (image != null) {
				refTemplate = extractor.extract(image);
				refDetdet = extractor.getDetectionDetails();
			} else
				throw new Exception("No image has been provided for single face extraction!");
		} catch (Exception ex) {
			throw new Exception("Single face features extraction failed.\n" + ex.getMessage());
		}
	}

	/**
	 * Multiple faces can be extracted from a single image by using
	 * extractUsingEyes() method. All eyes features must be detected. Eyes are
	 * searched within an appropriate face region bounding box, thus face
	 * detection must be perfomed in advance.
	 * 
	 * Note 1: There is no need to convert an image to grayscale format prior to
	 * calling VeriLook SDK functions for it. The convertion is perfomed on the
	 * C++ wrapper side.
	 * 
	 * Note 2: All extracted templates are compressed.
	 */
	@SuppressWarnings("deprecation")
	private void extractUsingEyes(NImage image) throws Exception {
		int i = 0;
		try {
			/**
			 * 3.1. Detect face regions in an image.
			 */
			Face[] faces;
			if (image != null)
				faces = extractor.detectFaces(image);
			else
				throw new Exception("No image has been provided for multiple face extraction!");

			int faceCount = faces == null ? 0 : faces.length;
			extractedTemplates = 0;
			if (faceCount != 0) {
				templates = new NLTemplate[faceCount];
				detdet = new DetectionDetails[faceCount];
				for (; i < faceCount; i++) {
					/**
					 * 3.2. Detect eyes within each detected face region.
					 */
					if (faces[i] != null)
						detdet[i] = extractor.detectFacialFeatures(image, faces[i]);
					/**
					 * 3.3. Extract face features templates for each face and
					 * eyes detected region.
					 */
					if (detdet[i] != null)
						templates[i] = extractor.extractUsingDetails(image, detdet[i]);
					/**
					 * 3.4. Compress face features template for each extracted
					 * face and eyes region.
					 */
					if (templates[i] != null) {
						extractor.compressTemplate(templates[i]);
						extractedTemplates++;
					}
				}
			}
		} catch (Exception ex) {
			templates = null;
			throw new Exception("Multiple faces extraction failed when processing face Nr. " + i + ".\n"
					+ ex.getMessage());
		}
	}

	/**
	 * After extraction the details of the face detection can be accessed and
	 * checked.
	 */
	private static String getFaceDetectionDetails(DetectionDetails dd) {
		String detStr = "";
		Face face = dd.getFace();
		Eyes eyes = dd.getEyes();
		if (face != null) {
			Point ulPoint = face.getUpperLeftPoint();
			detStr += "Found face:\n";
			detStr += String.format("\tlocation = (%.0f, %.0f), width = %d, height = %d, confidence = %.2f\n", ulPoint
					.getX(), ulPoint.getY(), face.getWidth(), face.getHeight(), face.getConfidence());
		}
		if (eyes != null) {
			detStr += "Found eyes:\n";
			detStr += String.format("\tfirst eye:\t location = (%.0f, %.0f), confidence = %.2f.\n", eyes.getFirst()
					.getX(), eyes.getFirst().getY(), eyes.getFirstConfidence());
			detStr += String.format("\tsecond eye:\t location = (%.0f, %.0f), confidence = %.2f.\n", eyes.getSecond()
					.getX(), eyes.getSecond().getY(), eyes.getSecondConfidence());
		}
		if (detStr != "")
			detStr = "Face detected.\n" + detStr;
		else
			detStr = "Face detection failed.\n";
		return detStr;
	}

	private BufferedImage getFaceDetectedImageIcon(BufferedImage bfImage, DetectionDetails[] dd,
			boolean drawOnlyBoundingBox) throws Exception {
		if (dd != null && dd.length > 0)
		{
			for (DetectionDetails details : dd) {
				if (details == null) {
					continue;
				}
				
				Face face = details.getFace();
				Eyes eyes = details.getEyes();
				if (face != null)
					drawFace(bfImage, face, drawOnlyBoundingBox);
				if (eyes != null && !drawOnlyBoundingBox)
					drawEyes(bfImage, eyes);
			}
		}
		return bfImage;
	}

	private static void drawFace(BufferedImage bfImage, Face face, boolean drawOnlyBoundingBox) {
		if (face == null)
			return;
		Graphics graphics = bfImage.getGraphics();
		graphics.setColor(Color.green);
		Point upLeftpoint = face.getUpperLeftPoint();
		Graphics2D g2 = (Graphics2D) graphics;
		g2.setStroke(new BasicStroke(3));
		g2.drawRect((int) upLeftpoint.getX(), (int) upLeftpoint.getY(), face.getWidth(), face.getHeight());
		if (!drawOnlyBoundingBox) {
			g2.setFont(new Font("Serif", Font.BOLD, 16));
			g2.drawString(String.format(" %.2f ", face.getConfidence()), (int) upLeftpoint.getX(), (int) upLeftpoint
					.getY()
					+ face.getHeight() + 20);
		}
	}

	private static void drawEyes(BufferedImage bfImage, Eyes eyes) {
		if (eyes == null)
			return;
		Graphics graphics = bfImage.getGraphics();
		graphics.setColor(Color.green);
		Point firstEye = eyes.getFirst(), secondEye = eyes.getSecond();
		graphics.drawLine((int) firstEye.getX(), (int) firstEye.getY(), (int) secondEye.getX(), (int) secondEye.getY());
	}

	/////////////////////// End of mfmatcher ///////////////////////
	
    public static void main(String args[]) { 
    	
        // Create and install a security manager 
        if (System.getSecurityManager() == null) { 
        	System.setSecurityManager(new RMISecurityManager()); 
        }
    	//Obtain license directly
    	//NLicensing.licenseObtain("/local", "5000", "SingleComputerLicense:VLExtractor,SingleComputerLicense:VLMatcher,SingleComputerLicense:VLExtended,SingleComputerLicense:VLBSS");
		LicensingConfig licConfig = LicensingConfig.getInstance();
		String licensesString = licConfig.getLicenseString("FacesExtractor", "FacesMatcher");
		
		if (NLicensing.licenseObtain(licConfig.getServerIP(), licConfig.getServerPort(), licensesString))
		{
	        try
	        {        	
			    RmiServer ServerObj = new RmiServer(); 
			    // Bind this object instance to the name "RmiServer"
			    // Note that the name must be compatible with URL
			    // No space
			    Naming.rebind("RmiServerService", ServerObj); 
			    System.out.println("RmiServerService is now bound in registry at port 1099"); 
			    System.out.println("Service RmiServerService listener is now redy...");
			    System.out.println("-------------------------------------------");
			    System.out.println("");
	        } catch (Exception e)
	        { 
			    System.out.println("RmiServerService error: "+e.getMessage());
			    e.printStackTrace(); 
	        }	// try catch
		} // if NLicense
		
        // Create instance of  MultipleFaceMatcher Object
        //mfMatcher = new MultipleFaceMatcher();
        
    }	// main()

    
    /////////////////////// Start of DB Operation ///////////////////////

	public String getDBVerifiedByName(String name) throws Exception {
		Integer multiFace_count = null;
		String return_strings = null;
		String sub_matchInfo1 = null;
		int score_begin_index;
		String sub_matchInfo2 = null;
		float score;
		
		RecordDB rcd_db = new RecordDB();
		System.out.println("Getting images by name you input now, it may take a while, please wait");
		List<NImage> image_set = rcd_db.getImagesByName(name);
		System.out.println("Getting images on DB successfully finished!");
		
		// Derive number of faces in the multiface image
		//int start_index = mfMatcher.getMultiFaceDetectionDetails().indexOf("Number of faces detected:")+26;
		String tem_substring = getMultiFaceDetectionDetails().substring(getMultiFaceDetectionDetails().indexOf("Number of faces detected:")+26);
		multiFace_count = Integer.valueOf(tem_substring.substring(0,tem_substring.indexOf(".")));
		System.out.println("multiFace_count is "+ multiFace_count);							
								
		int [] multiface_reference_to_singleface = new int[multiFace_count];
		float [] multiface_match_singleface_score = new float[multiFace_count];
		
		for (int j = 0; j < multiFace_count; j++) {
			multiface_reference_to_singleface[j] = -1;
			multiface_match_singleface_score[j] = -1;
		}
		
		for (int i = 0; i < image_set.size(); i++) {
			//image_set.get(i).saveToFile("c:\\temp");													
			//mfMatcher.loadSingleFaceImage("c:\\temp_rs_"+i+".png");
			loadSingleFaceImage("temp_rs_"+i+".png");
			//refImageLabel.setIcon(getScaledRefImageIcon());
			
			extractSingleFaceImage();
			//refImageLabel.setIcon(getScaledRefImageIcon());
			//textArea.append(getSingleFaceDetectionDetails());
			return_strings = getSingleFaceDetectionDetails();
			
			System.out.println(return_strings);
			
			if (isSingleFaceImageExtracted()) {
				//textArea.append("Single face features extraction successfully finished.\n\n");
				return_strings = return_strings + "\n\nSingle face features extraction successfully finished.\n\n";
				String matchInfo = matchMultipleFaces();
				//textArea.append(matchInfo);
				return_strings = return_strings + "\n\n"+matchInfo;
	
				//Capture score of the single image comparison
				sub_matchInfo1 = matchInfo;
				for (int ii = 0; ii < multiFace_count; ii++) {
					score_begin_index = sub_matchInfo1.indexOf("scored ")+7;
					sub_matchInfo2 = sub_matchInfo1.substring(score_begin_index);
					score = Float.valueOf(sub_matchInfo2.substring(0,sub_matchInfo2.indexOf(".")+5));
					sub_matchInfo1 = sub_matchInfo2.substring(sub_matchInfo2.indexOf(".")+5);
	
					if (multiface_match_singleface_score[ii] < score) {
						multiface_reference_to_singleface[ii] = i;
						multiface_match_singleface_score[ii] = score;
					}
	
					System.out.println("Single image " + i + " matches face " + ii + " of multiface image with a score = " + score);
				}
	
				//System.out.println(matchInfo);
				
				//textArea.append("Current single face image matching successfully completed.\n\n");
				return_strings = return_strings + "\n\n" + "Current single face image matching successfully completed.\n\n";
			} else {
				//textArea.append("Single face features extraction failed.\n\n");
				return_strings = return_strings + "\n\n" + "Single face features extraction failed.\n\n";
			} // else
		} // for i img_sizw
	
		String subjectName = null;
		for (int jj = 0; jj < multiFace_count; jj++) {
			if (multiface_match_singleface_score[jj] > 0) {
				subjectName = rcd_db.subjectCaption[multiface_reference_to_singleface[jj]];			
				subjectName = subjectName.substring(subjectName.indexOf("-")+1);
				subjectName = subjectName.substring(0,subjectName.indexOf("-"));
	
				//textArea.append("Face " + jj + " in multi-face image is matched by the single face image " + multiface_reference_to_singleface[jj] + " with a matching score " + multiface_match_singleface_score[jj] + ".\n");
				return_strings = return_strings + "\n\n" + "Face " + jj + " in multi-face image is matched by the single face image " + multiface_reference_to_singleface[jj] + " with a matching score " + multiface_match_singleface_score[jj] + ".\n";
				//textArea.append("Single face image with index " + multiface_reference_to_singleface[jj] + " is identified as " + subjectName + ".\n");
				return_strings = return_strings + "\n\n" + "Single face image with index " + multiface_reference_to_singleface[jj] + " is identified as " + subjectName + ".\n";
				System.out.println("Face " + jj + " in multi-face image is matched by the single face image " + multiface_reference_to_singleface[jj] + " with a matching score " + multiface_match_singleface_score[jj] + ".\n");
				System.out.println("Single face image with index " + multiface_reference_to_singleface[jj] + " is identified as " + subjectName + ".\n");
				loadSingleFaceImage("temp_rs_"+multiface_reference_to_singleface[jj]+".png");
				//refImageLabel.setIcon(getScaledRefImageIcon());
				Thread.sleep(2000);
			} else {
				//textArea.append("Face " + jj + " in multi-face image has no match to any single face image reference.\n");
				return_strings = return_strings + "\n\n" + "Face " + jj + " in multi-face image has no match to any single face image reference.\n";
				System.out.println("Face " + jj + " in multi-face image has no match to any single face image reference.\n");
	
			}
		}
		
		for (int i = 0; i < image_set.size(); i++) {
		    // A File object to represent the filename
		    //File f = new File("c:\\temp_rs_"+i+".png");
		    File f = new File("temp_rs_"+i+".png");
		    boolean success = f.delete();
	
		    if (!success)
		      //throw new IllegalArgumentException("Unable to delete " + "c:\\temp_rs_"+i+".png");
		      throw new IllegalArgumentException("Unable to delete " + "temp_rs_"+i+".png");
	
		} // for
		
		return return_strings;
		
	}	// getDBVerifiedByName
	
	
	/////////////////////// Additional Bon Sy DB operation ///////////////////////
	
	public void SaveSingleFaceImageToDB(String name, String fileName) {
		Save_SingleFaceImage_To_DB(name, fileName);
	}
	
	// Additional functionalities added by Bon Sy Jan 31, 2010
	private void Save_SingleFaceImage_To_DB (String name, String fname) {
		try 
		{
			RecordDB record_db = new RecordDB();	
			NLExtractor extractor = new NLExtractor();			

			record_db.append_single_face(name, My_LoadFromFile(fname), extractor.extract(My_LoadFromFile(fname)).toByteArray());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Problem on Save_SingleFaceImage_To_DB/n");
		}		
	}
	
	private NImage My_LoadFromFile(String fname) {
		NImage image = null;

			try {
				image = NImage.loadFromFile(fname);
				float hrez = image.getHorizontalResoliution();
				float vrez = image.getVerticalResoliution();

				if (hrez < 250.0f || vrez < 250.0f) image.setResolution(500.0f, 500.0f);

				// currImageScanner.imageScannedCallback(image);
				// return image;
				
			} catch (Exception e) {
				System.out.println(e.getMessage() + "\n");
			}
		return image;
	}
	
	// End of additional functionalities
	
} // main class