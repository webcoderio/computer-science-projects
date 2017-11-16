import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.EnumSet;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;

//import FingersSampleJava.RecordDB;

import com.neurotechnology.Fingers.NFExtractor;
import com.neurotechnology.Library.NativeManager;
import com.neurotechnology.NImages.ImageFileFilter;
import com.neurotechnology.NImages.NImage;
import com.neurotechnology.NImages.NImageFormat;
import com.neurotechnology.NLicensing.NLicensing;
import com.neurotechnology.NLicensing.LicensingConfig;
import com.neurotechnology.NMatcher.NMatcher;

import com.neurotechnology.Faces.*;
import java.io.*;

public class MainWindow extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;

	private JLabel imageLabel, refImageLabel;
	private JPanel leftPanel, lImagePanel, lTextPanel;
	private JPanel rightPanel, rRefImagePanel;
	private JMenuBar menuBar;
	private JMenu aboutMenu;
	private JMenuItem aMenuItem, aMenuItem2;
	private JButton matchButton, candExtrButton, candImageButton, refImageButton, refExtrButton;
	private JTextArea textArea;
	private JSplitPane split;
	private JFileChooser fileChooser;
	private ImageFileFilter imageFilter;
	private MultipleFaceMatcher mfMatcher;
	
	//Below are added by Bon Sy
	private JButton saveImageButton, identImageButton;
	//
	
	public void paintComponent(Graphics g) {
		int width = this.getWidth();
		split.setDividerLocation(2 * width / 3);
	}

	public MainWindow() throws Exception {
		super("Modified Multiple Face Matching (using Neurotechnology SDK) by Bon Sy Jan 2010");
		/**
		 * Step 1. Initialize multiple face matcher object encapsulating the
		 * instances of the JExctractor and JMatcher classes.
		 */
		mfMatcher = new MultipleFaceMatcher();
		Container curContentPane = getContentPane();
		curContentPane.setLayout(new FlowLayout());

		// file chooser initialization
		fileChooser = new JFileChooser();
		imageFilter = new ImageFileFilter();
		fileChooser.setFileFilter(imageFilter);

		// left panel
		leftPanel = new JPanel(new BorderLayout());
		// left image panel
		lImagePanel = new JPanel(new BorderLayout());
		lImagePanel.setBorder(BorderFactory.createTitledBorder("Selected multiple face image:"));
		imageLabel = new JLabel();
		imageLabel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		imageLabel.setAlignmentY(JPanel.CENTER_ALIGNMENT);
		JScrollPane scrPane = new JScrollPane(imageLabel);
		lImagePanel.add(scrPane, BorderLayout.CENTER);
		lImagePanel.setMinimumSize(new Dimension(600, 600));
		lImagePanel.setMaximumSize(new Dimension(700, 600));
		lImagePanel.setPreferredSize(new Dimension(600, 600));
		// left text area panel
		lTextPanel = new JPanel(new BorderLayout());
		lTextPanel.setBorder(BorderFactory.createTitledBorder("General log:"));
		lImagePanel.setMinimumSize(new Dimension(600, 600));
		lImagePanel.setMaximumSize(new Dimension(700, 600));
		lImagePanel.setPreferredSize(new Dimension(600, 600));
		textArea = new JTextArea("Images source is set to file...\n", 10, 20);
		textArea.setFont(new Font("Serif", Font.BOLD, 14));
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setEditable(false);
		JScrollPane scPane = new JScrollPane(textArea);
		scPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		lTextPanel.add(scPane);

		leftPanel.add(lImagePanel, BorderLayout.CENTER);
		leftPanel.add(lTextPanel, BorderLayout.PAGE_END);

		// right panel
		rightPanel = new JPanel();
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.PAGE_AXIS));
		rightPanel.setBorder(BorderFactory.createTitledBorder("Matching actions:"));
		rightPanel.setPreferredSize(new Dimension(400, 100));
		candImageButton = createJButton(this, "Open multiface image", "Open an image with multiple faces.",
				new Dimension(170, 40));
		candExtrButton = createJButton(this, "Extract multiple faces", "Extract templates from multiple face image.",
				new Dimension(170, 40));
		refImageButton = createJButton(this, "Open single face image", "Open an image with single (reference) face.",
				new Dimension(170, 40));
		refExtrButton = createJButton(this, "Extract single face",
				"Extract reference template from single face image.", new Dimension(170, 40));
		matchButton = createJButton(this, "Match multiple faces", "Match multiple faces with single reference face.",
				new Dimension(170, 40));
		identImageButton = createJButton(this, "Face ident using DB", "Face ident using DB.",
				new Dimension(170, 40));		
		saveImageButton = createJButton(this, "Upload reference to DB", "Upload reference to DB.",
				new Dimension(170, 40));		
		candExtrButton.setEnabled(false);
		refImageButton.setEnabled(false);
		refExtrButton.setEnabled(false);
		matchButton.setEnabled(false);
		identImageButton.setEnabled(false);
		candImageButton.addActionListener(this);
		candExtrButton.addActionListener(this);
		refImageButton.addActionListener(this);
		refExtrButton.addActionListener(this);
		matchButton.addActionListener(this);
		identImageButton.addActionListener(this);
		saveImageButton.addActionListener(this);
		
		rightPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		rightPanel.add(candImageButton);
		rightPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		rightPanel.add(candExtrButton);
		rightPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		rightPanel.add(refImageButton);
		rightPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		rightPanel.add(refExtrButton);
		rightPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		rightPanel.add(matchButton);
		rightPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		rightPanel.add(identImageButton);
		rightPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		rightPanel.add(saveImageButton);
		rightPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		
		// reference image right panel
		rRefImagePanel = new JPanel(new BorderLayout());
		rRefImagePanel.setBorder(BorderFactory.createTitledBorder("Reference single face image:"));
		rRefImagePanel.setAlignmentX(JFrame.CENTER_ALIGNMENT);
		rRefImagePanel.setAlignmentY(JFrame.CENTER_ALIGNMENT);
		rRefImagePanel.setPreferredSize(new Dimension(240, 200));
		rRefImagePanel.setMaximumSize(new Dimension(240, 200));
		rRefImagePanel.setMinimumSize(new Dimension(240, 200));
		refImageLabel = new JLabel();
		refImageLabel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		refImageLabel.setAlignmentY(JPanel.CENTER_ALIGNMENT);
		rRefImagePanel.add(refImageLabel);
		rightPanel.add(rRefImagePanel);

		split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		split.setOneTouchExpandable(true);
		split.setLeftComponent(leftPanel);
		split.setRightComponent(rightPanel);
		split.setDividerLocation(700);

		setContentPane(split);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(new MainWindowsAdapater());
		setDefaultLookAndFeelDecorated(true);
		setPreferredSize(new Dimension(990, 840));
		pack();
		setVisible(true);
	}

	private static JButton createJButton(JFrame frame, String name, String toolTipText, Dimension size) {
		JButton but = new JButton(name);
		but.setToolTipText(toolTipText);
		but.setAlignmentX(JFrame.CENTER_ALIGNMENT);
		but.setAlignmentY(JFrame.CENTER_ALIGNMENT);
		but.setPreferredSize(size);
		but.setMinimumSize(size);
		but.setMaximumSize(size);
		return but;
	}

	public void actionPerformed(ActionEvent e) {
		String info;

		int score_begin_index;
		String sub_matchInfo1;
		String sub_matchInfo2;
		Float score;
		
		int multiFace_count;
		
		if (e.getSource() == candImageButton) {
			int returnVal = fileChooser.showOpenDialog(this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				try {
					String fileName = fileChooser.getSelectedFile().getAbsolutePath();
					textArea.append("Uploading multiple face image file " + fileName + " ...\n");
					/**
					 * Step 2. Load a multiple face image from the specified
					 * file.
					 */
					mfMatcher.loadMultiFaceImage(fileName);
					imageLabel.setIcon(mfMatcher.getMultiFaceImageIcon());
					textArea.append("Multiple face image successfully uploaded.\n\n");
					candExtrButton.setEnabled(true);
				} catch (Exception ex) {
					imageLabel.setIcon(null);
					JOptionPane
							.showMessageDialog(this, ex.getMessage(), " Operation failed", JOptionPane.ERROR_MESSAGE);
					textArea.append("Multiple face image uploading failed.\n\n");
					candExtrButton.setEnabled(false);
				}
				refImageButton.setEnabled(false);
				refExtrButton.setEnabled(false);
				matchButton.setEnabled(false);
			}
		} else if (e.getSource() == candExtrButton) {
			try {
				textArea.append("Extracting features from multiple face image file ...\n");
				/**
				 * Step 3. Extract face features templates from the uploaded
				 * multiple face image. After extraction the details of the
				 * face detection can be checked.
				 */
				mfMatcher.extractMultiFaceImage();
				imageLabel.setIcon(mfMatcher.getFaceDetectedMultiFaceImageIcon());
				textArea.append(mfMatcher.getMultiFaceDetectionDetails());
						
				//System.out.println(mfMatcher.getMultiFaceDetectionDetails());
	
				if (mfMatcher.isMultiFaceImageExtracted()) {
					textArea.append("Features extraction successfully finished.\n\n");
					refImageButton.setEnabled(true);
					refExtrButton.setEnabled(mfMatcher.getSingleFaceImageIcon() != null);
					matchButton.setEnabled(mfMatcher.isSingleFaceImageExtracted());
					identImageButton.setEnabled(true);
				} else {
					textArea.append("Features extraction failed.\n\n");
					refImageButton.setEnabled(false);
					refExtrButton.setEnabled(false);
					matchButton.setEnabled(false);
					identImageButton.setEnabled(false);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(this, ex.getMessage(), " Operation failed", JOptionPane.ERROR_MESSAGE);
				refImageButton.setEnabled(false);
				refExtrButton.setEnabled(false);
				matchButton.setEnabled(false);
				textArea.append("Multiple face detection or extraction failed.\n\n");
			}
		} else if (e.getSource() == refImageButton) {
			int returnVal = fileChooser.showOpenDialog(this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				try {
					String fileName = fileChooser.getSelectedFile().getAbsolutePath();
					textArea.append("Uploading and extracting reference image file " + fileName + " ...\n");
					/**
					 * Step 4. Load a single (reference) face image from the
					 * specified file.
					 */
					mfMatcher.loadSingleFaceImage(fileName);
					refImageLabel.setIcon(mfMatcher.getScaledRefImageIcon());
					textArea.append("Image successfully uploaded.\n\n");
					refExtrButton.setEnabled(true);
					matchButton.setEnabled(false);
				} catch (Exception ex) {
					ex.printStackTrace();
					refImageLabel.setIcon(null);
					refExtrButton.setEnabled(false);
					matchButton.setEnabled(false);
					JOptionPane
							.showMessageDialog(this, ex.getMessage(), " Operation failed", JOptionPane.ERROR_MESSAGE);
					textArea.append("Image uploading failed.\n\n");
				}
			}
		} else if (e.getSource() == refExtrButton) {
			try {
				textArea.append("Extracting features from single face image file ...\n");
				/**
				 * Step 5. Request automatic extraction of face feature template
				 * from an uploaded single face reference image. After
				 * exctraction the details of the face detection can be checked.
				 */
				mfMatcher.extractSingleFaceImage();
				refImageLabel.setIcon(mfMatcher.getScaledRefImageIcon());
				textArea.append(mfMatcher.getSingleFaceDetectionDetails());
				if (mfMatcher.isSingleFaceImageExtracted()) {
					textArea.append("Single face features extraction successfully finished.\n\n");
					matchButton.setEnabled(true);
				} else {
					textArea.append("Single face features extraction failed.\n\n");
					matchButton.setEnabled(false);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				textArea.append("Single face detection or extraction failed.\n\n");
				matchButton.setEnabled(false);
				JOptionPane.showMessageDialog(this, ex.getMessage(), " Operation failed", JOptionPane.ERROR_MESSAGE);
			}
		} else if (e.getSource() == matchButton) {
			try {
				textArea.append("Matching process started ...\n");
				/**
				 * Step 6. Request matching of templates extracted from multiple
				 * face image with reference template extracted from single face
				 * image.
				 */
				String matchInfo = mfMatcher.matchMultipleFaces();
				textArea.append(matchInfo);
				textArea.append("Verification finished.\n\n");
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, ex.getMessage(), " Operation failed", JOptionPane.ERROR_MESSAGE);
				textArea.append("Verification process failed.\n\n");
			} 
		} else if (e.getSource() == identImageButton) {
			try {
				textArea.append("Matching process started ...\n");
				/**
				 * Request matching of templates extracted from multiple
				 * face image with reference template of single face image
				 * pulled directly from the DB.
				 */
				//
				if (mfMatcher.isMultiFaceImageExtracted()) {
					//textArea.append("Single face features extraction successfully finished.\n\n");
					String name = (String) JOptionPane.showInputDialog(
			                this,
			                 "Use the single face set defined by:\n",
			                 "Use the single face set defined by",
			                 JOptionPane.PLAIN_MESSAGE);
						System.out.println(name);
					if(name != null) {
						RecordDB rcd_db = new RecordDB();
						
						List<NImage> image_set = rcd_db.getImagesByName(name);				
						
						// Derive number of faces in the multiface image
						//int start_index = mfMatcher.getMultiFaceDetectionDetails().indexOf("Number of faces detected:")+26;
						String tem_substring = mfMatcher.getMultiFaceDetectionDetails().substring(mfMatcher.getMultiFaceDetectionDetails().indexOf("Number of faces detected:")+26);
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
							mfMatcher.loadSingleFaceImage("temp_rs_"+i+".png");
							refImageLabel.setIcon(mfMatcher.getScaledRefImageIcon());
							
							mfMatcher.extractSingleFaceImage();
							refImageLabel.setIcon(mfMatcher.getScaledRefImageIcon());
							textArea.append(mfMatcher.getSingleFaceDetectionDetails());
							
							System.out.println(mfMatcher.getSingleFaceDetectionDetails());
							
							if (mfMatcher.isSingleFaceImageExtracted()) {
								textArea.append("Single face features extraction successfully finished.\n\n");
								String matchInfo = mfMatcher.matchMultipleFaces();
								textArea.append(matchInfo);
	
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
								
								textArea.append("Current single face image matching successfully completed.\n\n");
							} else {
								textArea.append("Single face features extraction failed.\n\n");
							}	
						}

						String subjectName = null;
						for (int jj = 0; jj < multiFace_count; jj++) {
							if (multiface_match_singleface_score[jj] > 0) {
								subjectName = rcd_db.subjectCaption[multiface_reference_to_singleface[jj]];			
								subjectName = subjectName.substring(subjectName.indexOf("-")+1);
								subjectName = subjectName.substring(0,subjectName.indexOf("-"));

								textArea.append("Face " + jj + " in multi-face image is matched by the single face image " + multiface_reference_to_singleface[jj] + " with a matching score " + multiface_match_singleface_score[jj] + ".\n");
								textArea.append("Single face image with index " + multiface_reference_to_singleface[jj] + " is identified as " + subjectName + ".\n");
								System.out.println("Face " + jj + " in multi-face image is matched by the single face image " + multiface_reference_to_singleface[jj] + " with a matching score " + multiface_match_singleface_score[jj] + ".\n");
								System.out.println("Single face image with index " + multiface_reference_to_singleface[jj] + " is identified as " + subjectName + ".\n");
								mfMatcher.loadSingleFaceImage("temp_rs_"+multiface_reference_to_singleface[jj]+".png");
								refImageLabel.setIcon(mfMatcher.getScaledRefImageIcon());
								Thread.sleep(2000);
							} else {
								textArea.append("Face " + jj + " in multi-face image has no match to any single face image reference.\n");
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

						}
						
					}
					//textArea.append("Image face identification successfully completed.\n\n");
				} else {
					textArea.append("Features extraction from multiface image failed.\n\n");
				}
				//
				textArea.append("Identification based on single face images in DB finished.\n\n");
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, ex.getMessage(), " Operation failed", JOptionPane.ERROR_MESSAGE);
				textArea.append("Verification process failed.\n\n");
			} 
		} else if (e.getSource() == saveImageButton) {
			int returnVal = fileChooser.showOpenDialog(this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				try {
					String fileName = fileChooser.getSelectedFile().getAbsolutePath();
					textArea.append("Saved single face image to DB from " + fileName + " ...\n");
					/**
					 * Load a single (reference) face image from the
					 * specified file to be saved to the DB
					 */
					mfMatcher.loadSingleFaceImage(fileName);
					refImageLabel.setIcon(mfMatcher.getScaledRefImageIcon());

					mfMatcher.extractSingleFaceImage();
					refImageLabel.setIcon(mfMatcher.getScaledRefImageIcon());
					textArea.append(mfMatcher.getSingleFaceDetectionDetails());
					if (mfMatcher.isSingleFaceImageExtracted()) {
						textArea.append("Single face features extraction successfully finished.\n\n");
						String name = (String) JOptionPane.showInputDialog(
				                this,
				                 "Enter name ID caption:\n",
				                 "Enter name ID caption",
				                 JOptionPane.PLAIN_MESSAGE);
							System.out.println(name);
						if(name != null) {
							Save_SingleFaceImage_To_DB (name, fileName);
						}
						textArea.append("Image successfully uploaded.\n\n");
					} else {
						textArea.append("Single face features extraction failed.\n\n");
					}
				} catch (Exception ex) {
					ex.printStackTrace();
					refImageLabel.setIcon(null);
					JOptionPane
							.showMessageDialog(this, ex.getMessage(), " Operation failed", JOptionPane.ERROR_MESSAGE);
					textArea.append("Image uploading failed.\n\n");
				}
			}
			textArea.setCaretPosition(textArea.getDocument().getLength());
		}
	}

	class MainWindowsAdapater extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			dispose();
			LicensingConfig licConfig = LicensingConfig.getInstance();
			String licensesString = licConfig.getLicenseString("FacesExtractor", "FacesMatcher");
			NLicensing.licenseRelease(licensesString);
			System.exit(0);
		}
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
				//return image;
				
			} catch (Exception e) {
				System.out.println(e.getMessage() + "\n");
			}
		return image;
	}
	
// End of additional functionalities
	
	public static void main(String[] args) throws Throwable {
		try {
			LicensingConfig licConfig = LicensingConfig.getInstance();
			String licensesString = licConfig.getLicenseString("FacesExtractor", "FacesMatcher");
			if (NLicensing.licenseObtain(licConfig.getServerIP(), licConfig.getServerPort(), licensesString)) {
				new MainWindow();
			} else {
				JOptionPane.showMessageDialog(null, "Unable to obtain the following licenses: " + licensesString,
						"Failed to obtain required licenses", JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception exc) {
			System.out.println(exc.getMessage());
			exc.printStackTrace(System.err);
			System.exit(1);
		}
	}
}

