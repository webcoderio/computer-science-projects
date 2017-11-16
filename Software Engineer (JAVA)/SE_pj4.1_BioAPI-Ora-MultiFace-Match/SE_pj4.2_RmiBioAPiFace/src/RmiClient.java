import java.rmi.Naming;
import java.rmi.RemoteException;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;

import PasswordApp.*;


public class RmiClient extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private JLabel imageLabel, refImageLabel;
	private JPanel leftPanel, lImagePanel, lTextPanel;
	private JPanel rightPanelUp, rightPanelDown;
	private JButton matchButton, candExtrButton, candImageButton, refImageButton, refExtrButton;
	private JTextArea textArea;
	private JSplitPane split;
	private JFileChooser fileChooser;
	@SuppressWarnings("unused")
	private ImageFileFilter imageFileFilter;
	//public MultipleFaceMatcher mfMatcher;
	private String userName;
	@SuppressWarnings("unused")
	private String serverIp;
	public RmiInterface ServerObj;
	
	//Below are added by Bon Sy
	private JButton saveImageButton, identImageButton;
	//
	
	public void paintComponent(Graphics g) {
		int width = this.getWidth();
		split.setDividerLocation(2 * width / 3);
	}

	public RmiClient() throws Exception {
		super("Service name: Biometric Face Identification ( Credit to Professor: Dr. Bon Sy | Student: Shing Ng | Course: CS370 Software Enginnering )");
				
		// Create an instance of BIOAPI StratFrame for Account control list management (ACL)
		StartFrame AccountUId = new StartFrame();
		
		// Getters on StarFrame
		
		userName = AccountUId.getUserName();
		if(userName.isEmpty()) 	// die
			System.exit(1);
		else					// keep alive
			System.out.println("Your unique user name ID: '"+userName+"' is now Verified valid.");
		
		String serverIp = AccountUId.getServerIp();
		if(serverIp.isEmpty()) 	// die
			System.exit(1);
		else					// keep alive
			System.out.println("Your connection to Server: '"+serverIp+"' is now Established.");

	    // "ServerObj" is the identifier that we'll use to refer 
	    // to the remote object that implements the "RmiInterface" 
	    ServerObj = (RmiInterface)Naming.lookup("//" + serverIp + "/RmiServerService"); 
		
		
		/**
		 * Step 1. Initialize multiple face matcher object encapsulating the
		 * instances of the JExctractor and JMatcher classes.
		 */
		//mfMatcher = new MultipleFaceMatcher(); // REPLACE WITH rmi.ServerObj
		Container curContentPane = getContentPane();
		curContentPane.setLayout(new FlowLayout());

		// file chooser initialization
		fileChooser = new JFileChooser();
		imageFileFilter = new ImageFileFilter();

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

		// right panel up
		rightPanelUp = new JPanel();
		rightPanelUp.setLayout(new BoxLayout(rightPanelUp, BoxLayout.PAGE_AXIS));
		rightPanelUp.setBorder(BorderFactory.createTitledBorder("User ID: "+userName+" | Service Host: "+serverIp));
		rightPanelUp.setPreferredSize(new Dimension(400, 100));
		candImageButton = createJButton(this, "Transfer Multiface Image", "Open an image with multiple faces.",
				new Dimension(215, 40));
		candExtrButton = createJButton(this, "Extract Multface Detail", "Extract templates from multiple face image.",
				new Dimension(215, 40));
		refImageButton = createJButton(this, "Transfer Singleface Image", "Open an image with single (reference) face.",
				new Dimension(215, 40));
		refExtrButton = createJButton(this, "Extract Singleface Detail",
				"Extract reference template from single face image.", new Dimension(215, 40));
		matchButton = createJButton(this, "Match Multface w/ Singleface", "Match multiple faces with single reference face.",
				new Dimension(215, 40));
		identImageButton = createJButton(this, "Identify Face Using DB", "Face ident using DB.",
				new Dimension(215, 40));		
		saveImageButton = createJButton(this, "Upload Reference to DB", "Upload reference to DB.",
				new Dimension(215, 40));		
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
		
		rightPanelUp.add(Box.createRigidArea(new Dimension(0, 10)));
		rightPanelUp.add(candImageButton);
		rightPanelUp.add(Box.createRigidArea(new Dimension(0, 10)));
		rightPanelUp.add(candExtrButton);
		rightPanelUp.add(Box.createRigidArea(new Dimension(0, 10)));
		rightPanelUp.add(refImageButton);
		rightPanelUp.add(Box.createRigidArea(new Dimension(0, 10)));
		rightPanelUp.add(refExtrButton);
		rightPanelUp.add(Box.createRigidArea(new Dimension(0, 10)));
		rightPanelUp.add(matchButton);
		rightPanelUp.add(Box.createRigidArea(new Dimension(0, 10)));
		rightPanelUp.add(identImageButton);
		rightPanelUp.add(Box.createRigidArea(new Dimension(0, 10)));
		rightPanelUp.add(saveImageButton);
		rightPanelUp.add(Box.createRigidArea(new Dimension(0, 10)));
		
		// reference image right panel down
		rightPanelDown = new JPanel(new BorderLayout());
		rightPanelDown.setBorder(BorderFactory.createTitledBorder("Reference single face image:"));
		rightPanelDown.setAlignmentX(JFrame.CENTER_ALIGNMENT);
		rightPanelDown.setAlignmentY(JFrame.CENTER_ALIGNMENT);
		rightPanelDown.setPreferredSize(new Dimension(240, 200));
		rightPanelDown.setMaximumSize(new Dimension(240, 200));
		rightPanelDown.setMinimumSize(new Dimension(240, 200));
		refImageLabel = new JLabel();
		refImageLabel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		refImageLabel.setAlignmentY(JPanel.CENTER_ALIGNMENT);
		rightPanelDown.add(refImageLabel);
		rightPanelUp.add(rightPanelDown);

		split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		split.setOneTouchExpandable(true);
		split.setLeftComponent(leftPanel);
		split.setRightComponent(rightPanelUp);
		split.setDividerLocation(700);

		setContentPane(split);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultLookAndFeelDecorated(true);
		setPreferredSize(new Dimension(1024, 570)); // width, height
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

	
	
	
	
	/*
	 *  Actions start here(non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	
	
	
	
	
	
	
	
	
	
	
	
	public void actionPerformed(ActionEvent e) {
	
		// initialize variables
		String multifacename = null;
		String multifacefullname = null;
		String singlefacename = null;
		String singlefacefullname = null;

		
		if (e.getSource() == candImageButton) {
			int returnVal = fileChooser.showOpenDialog(this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				try {
					String fileName = fileChooser.getSelectedFile().getAbsolutePath();
					textArea.append("Uploading multiple face image file " + fileName + " ...\n");
					
					/**
					 * Step 2. Send and Load a multiple face image from the specified
					 * file from Client to Server.
					 */
					
					// Send locally uploaded image to server formatted as "username_multiface"
					ImageConverter multifaceimageTool = new ImageConverter(); 				// using ImageConverter class
					byte[] multifacebytes = multifaceimageTool.imageToByteArray(fileName); // convert binary image on file chooser to bytes
					multifacename = userName+"_multiface"; // face name is on server while filename is on local
					multifacefullname = multifacename+".jpg";
					ServerObj.sendByteArrayImage(multifacebytes, multifacename);
					
					// The image was already sent to the server, ask RMI server to work on it
					//mfMatcher.loadMultiFaceImage(fileName);
					ServerObj.loadMultiFaceImage(multifacefullname);
					
					//imageLabel.setIcon(mfMatcher.getMultiFaceImageIcon());
					imageLabel.setIcon(ServerObj.getMultiFaceImageIcon());
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
				multifacename = userName+"_multiface"; // face name is on server while filename is on local
				multifacefullname = userName+"_multiface.jpg";
				textArea.append("Extracting features from multiple face image file ...\n");
				/**
				 * Step 3. Extract face features templates from the uploaded
				 * multiple face image. After extraction the details of the
				 * face detection can be checked.
				 */
				//mfMatcher.extractMultiFaceImage();
				ServerObj.extractMultiFaceImage();
				imageLabel.setIcon(ServerObj.getFaceDetectedMultiFaceImageIcon());
				//textArea.append(mfMatcher.getMultiFaceDetectionDetails());
				textArea.append(ServerObj.getMultiFaceDetectionDetails());

				if (ServerObj.isMultiFaceImageExtracted()) {
					textArea.append("Features extraction successfully finished.\n\n");
					refImageButton.setEnabled(true);
					refExtrButton.setEnabled(ServerObj.getSingleFaceImageIcon() != null);
					matchButton.setEnabled(ServerObj.isSingleFaceImageExtracted());
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
					 * Step 4. Send and Load a single (reference) face image from the
					 * specified file.
					 */
					
					// Send locally uploaded image to server formatted as "username_singleface"
					ImageConverter singlefaceimageTool = new ImageConverter(); // using ImageConverter class
					byte[] singlefacebytes = singlefaceimageTool.imageToByteArray(fileName); // convert binary image on file chooser to bytes
					singlefacename = userName+"_singleface"; // face name is on server while filename is on local
					singlefacefullname = singlefacename+".jpg";
					ServerObj.sendByteArrayImage(singlefacebytes, singlefacename);
					
					// The image was already sent to the server, ask RMI server to work on it
					//mfMatcher.loadSingleFaceImage(fileName);
					ServerObj.loadSingleFaceImage(singlefacefullname);
					refImageLabel.setIcon(ServerObj.getScaledRefImageIcon());
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
				 * Extraction the details of the face detection can be checked.
				 */
				ServerObj.extractSingleFaceImage();
				refImageLabel.setIcon(ServerObj.getScaledRefImageIcon());
				
				textArea.append(ServerObj.getSingleFaceDetectionDetails());
				
				if (ServerObj.isSingleFaceImageExtracted()) {
					textArea.append("Single face features extraction successfully finished.\n\n");
					matchButton.setEnabled(true);
				} else {
					textArea.append("Single face features extraction failed.\n\n");
					matchButton.setEnabled(false);
				}
				
				textArea.append("Single face features extraction successfully finished.\n\n");
				matchButton.setEnabled(true);
			} catch (Exception ex) {
				ex.printStackTrace();
				textArea.append("Single face detection or extraction failed.\n\n");
				matchButton.setEnabled(false);
				JOptionPane.showMessageDialog(this, ex.getMessage(), " Operation failed1", JOptionPane.ERROR_MESSAGE);
			}
		} else if (e.getSource() == matchButton) {
			try {
				textArea.append("Matching process started ...\n");
				/**
				 * Step 6. Request matching of templates extracted from multiple
				 * face image with reference template extracted from single face
				 * image.
				 */
				String matchInfo = ServerObj.matchMultipleFaces();
				textArea.append(matchInfo);
				textArea.append("Verification finished.\n\n");
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, ex.getMessage(), " Operation failed2", JOptionPane.ERROR_MESSAGE);
				textArea.append("Verification process failed at matchButton.\n\n");
			} 
		} else if (e.getSource() == identImageButton) {
			///////////////////////////////////////////////////////////////////////////////////////
				textArea.append("Verify using DB process started ...\n");
				/**
				 * Request matching of templates extracted from multiple
				 * face image with reference template of single face image
				 * pulled directly from the DB.
				 */
				
					//textArea.append("Single face features extraction successfully finished.\n\n");
	
					String name = (String) JOptionPane.showInputDialog(
			                this,
			                 "Use the single face set defined by:\n",
			                 "Please wait DB results in Next step!",
			                 JOptionPane.PLAIN_MESSAGE);
						System.out.println(name);
					
					if(name != null) {
						// Get String return result Once after DB operation to decrease complexity on Client
						try {
							textArea.append( ServerObj.getDBVerifiedByName(name) );
						} catch (RemoteException e1) {
							e1.printStackTrace();
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					} // if name ! = null
					
				textArea.append("Identification based on single face images in DB finished.\n\n");
			///////////////////////////////////////////////////////////////////////////////////////
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
					ServerObj.loadSingleFaceImage(fileName);
					refImageLabel.setIcon(ServerObj.getScaledRefImageIcon());

					ServerObj.extractSingleFaceImage();
					refImageLabel.setIcon(ServerObj.getScaledRefImageIcon());
					textArea.append(ServerObj.getSingleFaceDetectionDetails());
					if (ServerObj.isSingleFaceImageExtracted()) {
						textArea.append("Single face features extraction successfully finished.\n\n");
						String name = (String) JOptionPane.showInputDialog(
				                this,
				                 "Enter name ID caption:\n",
				                 "Enter name ID caption",
				                 JOptionPane.PLAIN_MESSAGE);
							System.out.println(name);
						if(name != null) {
							//Save_SingleFaceImage_To_DB (name, fileName);
							ServerObj.SaveSingleFaceImageToDB(name, fileName);
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
	
	public static void main(String[] args) throws Throwable {
		new RmiClient();
	}
}

