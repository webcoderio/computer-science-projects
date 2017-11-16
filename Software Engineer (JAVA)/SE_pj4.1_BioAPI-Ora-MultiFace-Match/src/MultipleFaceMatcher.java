import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.neurotechnology.Faces.DetectionDetails;
import com.neurotechnology.Faces.Eyes;
import com.neurotechnology.Faces.Face;
import com.neurotechnology.Faces.NLExtractor;
import com.neurotechnology.Library.LibraryInfo;
import com.neurotechnology.NImages.NImage;
import com.neurotechnology.NMatcher.NMatcher;
import com.neurotechnology.Templates.NLTemplate;

public class MultipleFaceMatcher {
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

	public MultipleFaceMatcher() {
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
	}

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
	 */
	public void extractMultiFaceImage() throws Exception {
		extractUsingEyes(multiFaceImage);
	}

	/**
	 * Step 4. Upload single face (reference) image from the specified file.
	 */
	public void loadSingleFaceImage(String fileName) throws Exception {
		refTemplate = null;
		refDetdet = null;
		refSingleFaceImage = loadImageFromFile(fileName);
	}

	/**
	 * Step 5. Extract face features template from the specified single image
	 * file. Note: Single face (reference) template must remain uncompressed.
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
}
