package com.neurotechnology.NLicensing;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.neurotechnology.NLicensing.NLicensing;

public class LicensingDialog extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7693521125378067581L;
	private JTextField serverip;
	private JTextField serverport;
	
	private JTextField[] licenseField;
	private JButton[] obtain;
	private JCheckBox[] isObtained;
	
	private String[] licenses;
	
	public LicensingDialog(){
				
		this.setTitle("License");
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		try{
			URL imgURL = getClass().getResource("/img/logo.png");
			ImageIcon imgc = new ImageIcon(imgURL);
			this.setIconImage(imgc.getImage());
		}catch (Exception e) {
			e.printStackTrace();
		}
		this.setResizable(false);
		
		LicensingConfig licConfig = LicensingConfig.getInstance();
		
		licenses = licConfig.getLicenseStrings();
		this.setSize(new Dimension(430, 90 + 30 * licenses.length));
		
		serverip = new JTextField(licConfig.getServerIP());
		serverport = new JTextField(licConfig.getServerPort());
		
		JPanel namepan = new JPanel();
		JPanel valuepan = new JPanel(); 
		
		JPanel licensePan = new JPanel();
		JPanel obtainPan = new JPanel();
		JPanel isObtainedPan = new JPanel();
		
		JPanel top = new JPanel();
		JPanel middle = new JPanel();
		JPanel mainpan = new JPanel();
		
		licensePan.setLayout(new GridLayout(licenses.length,1));
		obtainPan.setLayout(new GridLayout(licenses.length,1));
		isObtainedPan.setLayout(new GridLayout(licenses.length,1));
		
		namepan.setLayout(new GridLayout(2,1));
		valuepan.setLayout(new GridLayout(2,1));
		
		top.setLayout(new BoxLayout(top, BoxLayout.X_AXIS));
		middle.setLayout(new BoxLayout(middle, BoxLayout.X_AXIS));
		mainpan.setLayout(new BoxLayout(mainpan, BoxLayout.Y_AXIS));
		
		namepan.setPreferredSize(new Dimension(100,50));
		namepan.setMinimumSize(new Dimension(100,50));
		namepan.setMaximumSize(new Dimension(100,50));
		
		namepan.setBorder(new EmptyBorder(5,5,5,5));
		valuepan.setBorder(new EmptyBorder(5,5,5,5));
		licensePan.setBorder(new EmptyBorder(5,5,5,5));
		obtainPan.setBorder(new EmptyBorder(5,5,5,5));
		isObtainedPan.setBorder(new EmptyBorder(5,5,5,5));
		
		namepan.add(new JLabel("Server IP"));
		namepan.add(new JLabel("Server port"));
		
		obtain = new JButton[licenses.length];
		isObtained = new JCheckBox[licenses.length];
		licenseField = new JTextField[licenses.length];
		
		for (int i = 0; i < licenses.length; i++) {
			licenseField[i] = new JTextField(licenses[i]);
			obtain[i] = new JButton("Obtain");
			isObtained[i] = new JCheckBox();
			
			licensePan.add(licenseField[i]);
			obtainPan.add(obtain[i]);
			isObtainedPan.add(isObtained[i]);
			
			obtain[i].addActionListener(this);
			
			isObtained[i].setEnabled(false);			
		}
		
		valuepan.add(serverip);
		valuepan.add(serverport);
		
		top.add(namepan);
		top.add(valuepan);
		
		middle.add(licensePan);
		middle.add(obtainPan);
		middle.add(isObtainedPan);
		
		mainpan.add(top);
		mainpan.add(middle);
		
		this.setContentPane(mainpan);
		refresh();
	}

	public void actionPerformed(ActionEvent e){
		try{
			for (int i = 0; i < licenses.length; i++){
				if (e.getSource() == obtain[i]){
					if (!NLicensing.getNLicenseInfo(licenses[i]).isObtained()){
						if (NLicensing.licenseObtain(serverip.getText(), serverport.getText(), licenseField[i].getText()))
							JOptionPane.showMessageDialog(this,"License obtained","Success",JOptionPane.INFORMATION_MESSAGE);
						else
							JOptionPane.showMessageDialog(this,"Unable to obtain license","Failure",JOptionPane.INFORMATION_MESSAGE);
					}
					else NLicensing.licenseRelease(licenses[i]);
				}
			}
			
			Thread.sleep(50);
			refresh();
			
		}catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(this,"Error occured - " + ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void setServerIP(String serverIP){
		serverip.setText(serverIP);
	}
	
	public String getSerberIP(){
		return serverip.getText();
	}
	
	public void setServerPort(String port){
		serverport.setText(port);
	}
	
	public String getServerPort(){
		return serverport.getText();
	}
	
	public void setVisible(boolean visible){
		refresh();
		super.setVisible(visible);
	}
	
	private void refresh(){
		for (int i = 0; i < licenses.length; i++){
			isObtained[i].setSelected(NLicensing.getNLicenseInfo(licenses[i]).isObtained());
			if(isObtained[i].isSelected()) obtain[i].setText("Release");
			else obtain[i].setText("Obtain");
			
		}
	}
	
	public boolean allObtained(){
		for (String license : licenses) 
			if (!NLicensing.getNLicenseInfo(license).isObtained())
				return false;
		return true;
	}
	
	public boolean obtainAll(){
		boolean allObtained = true;
		for (String license : licenses)
			try{
				allObtained = allObtained & NLicensing.licenseObtain(serverip.getText(), serverport.getText(), license);
			}catch (Exception e) {}
		refresh();
		return allObtained;
	}
}
