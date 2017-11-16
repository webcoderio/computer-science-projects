package com.neurotechnology.Library;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Properties;
import java.util.Vector;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

public class NetInstall {
	
	private Handler handler;
	private XMLReader parser;
	private int progress;
	private int total;
	private static int bufferSize = 10000;
	
	public NetInstall() throws Exception{
		handler = new Handler();
		parser = XMLReaderFactory.createXMLReader();
		parser.setContentHandler(handler);
		parser.setErrorHandler(handler);
	}

	public static boolean checkLoadDefault(){
		try{
			NativeManager.loadDefault();
			System.out.println("default library found");
			return true;
		}catch (Error e) {
			e.printStackTrace();
		}
		System.out.println("default library not found");
		return false;
	}	

	public boolean checkLoadTemp(){
		try{
			Vector<String> mainlibs = null;
			if (System.getProperty("os.name").indexOf("Windows") != -1){
				mainlibs = getMainLibrariesWindows();
				if(!checkFilesExist(mainlibs));// return false;
				for (int i = 0; i < mainlibs.size(); i++){
					System.out.println("Loading - " + mainlibs.get(i));
					System.out.println(getPath() + mainlibs.get(i));
					try{
						System.load(getPath() + mainlibs.get(i));
						if (mainlibs.get(i).indexOf(NativeManager.defaultlibrary) >= 0) NativeManager.setLoaded(true);
					}catch (Error e) {
						e.printStackTrace();
					}
				}
			}
			if (System.getProperty("os.name").indexOf("Linux") != -1){
				if(!checkFilesExist(getMainLibrariesLinux()));// return false;
				try{
					System.load(getPath() + "lib" + NativeManager.defaultlibrary + ".so");
					NativeManager.setLoaded(true);
				}catch (Error e) {
					e.printStackTrace();
				}
			}

			return NativeManager.isLoaded();
		}catch (Error e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("temp library not found");
		return false;
	}
	
	public Properties getEnvironment() throws java.io.IOException {
		Properties env = new Properties();
		env.load(Runtime.getRuntime().exec("env").getInputStream());
		return env;
	}
	
	public Vector<String> getMainLibrariesWindows() throws Exception{
		URL mainLibsURL = NetInstall.class.getResource("/com/neurotechnology/Library/MainLibWindows.xml");
		InputSource mainlibs = new InputSource(new InputStreamReader(mainLibsURL.openStream()));
		parser.parse(mainlibs);
		
		return handler.mainlibs;
	}
	
	public Vector<ScannerFiles> getScannerLibrariesWindows()throws Exception{
		if ("VeriLook".equals(NativeManager.getWrapperLibraryInfo().getProduct()))
			throw new Exception("This product does not support fingerprint scanners");
		URL fpsmmLibsURL = NetInstall.class.getResource("/com/neurotechnology/Library/fpsmmLibWindows.xml");
		InputSource fpsmmlibs = new InputSource(new InputStreamReader(fpsmmLibsURL.openStream()));
		parser.parse(fpsmmlibs);
		
		return handler.sf;
	}
	
	public Vector<String> getMainLibrariesLinux()throws Exception{
		URL mainLibsURL = NetInstall.class.getResource("/com/neurotechnology/Library/MainLibLinux.xml");
		InputSource mainlibs = new InputSource(new InputStreamReader(mainLibsURL.openStream()));
		parser.parse(mainlibs);
		
		return handler.mainlibs;
	}
	
	public Vector<String> getCmmLibrariesWindows() throws Exception{
		URL mainLibsURL = NetInstall.class.getResource("/com/neurotechnology/Library/CmmLibWindows.xml");
		InputSource cmmlibs = new InputSource(new InputStreamReader(mainLibsURL.openStream()));
		parser.parse(cmmlibs);
		
		return handler.cmmlibs;
	}
	
	public Vector<String> getCmmLibrariesLinux()throws Exception{
		URL mainLibsURL = NetInstall.class.getResource("/com/neurotechnology/Library/CmmLibLinux.xml");
		InputSource cmmlibs = new InputSource(new InputStreamReader(mainLibsURL.openStream()));
		parser.parse(cmmlibs);
		
		return handler.cmmlibs;
	}
	
	public void installTemp(String codeBase, Vector<String> mainlibs, Vector<ScannerFiles> scanners){
		installTemp0(codeBase, mainlibs, scanners, null);
	}
	
	public void installTemp(String codeBase, Vector<String> mainlibs, Vector<ScannerFiles> scanners, Vector<String> cmmlibs){
		installTemp0(codeBase, mainlibs, scanners, cmmlibs);
	}
	
	private void installTemp0(String codeBase, Vector<String> mainlibs, Vector<ScannerFiles> scanners, Vector<String> cmmlibs){
		//System.out.println(mainlibs + " ML " + mainlibs.size());
		//System.out.println(scanners + " SL " + scanners.size());
		//System.out.println(cmmlibs + " CL " + cmmlibs.size());
		String destination = getPath();
		
		//System.out.println("ML");
		total = 0;
		progress = 0;
		if (mainlibs != null) total += mainlibs.size(); 
		if (scanners != null) total += scanners.size(); 
		if (cmmlibs != null) total += cmmlibs.size(); 
		//System.out.println(total);
		
		if (mainlibs != null){
			for (int i = 0; i < mainlibs.size(); i++, progress++)
				copyURL(codeBase + mainlibs.get(i), destination + mainlibs.get(i));
		}
		//System.out.println("SL");
		if (scanners != null){
			String destinationFpsmm = destination + "fpsmm" + System.getProperty("file.separator");
			new File(destinationFpsmm).mkdirs();
			new File(destinationFpsmm + "plugin\\").mkdirs();
			new File(destinationFpsmm + "config\\").mkdirs();
			String codeBaseFpsmm = codeBase + "fpsmm" + System.getProperty("file.separator");
			for (int i = 0; i < scanners.size(); i++, progress++)
				for (int j = 0; j < scanners.get(i).getFiles().size(); j++)
					copyURL(codeBaseFpsmm + scanners.get(i).getFiles().get(j), destinationFpsmm + scanners.get(i).getFiles().get(j));
			
		}
		//System.out.println("CL");
		if (cmmlibs != null){
			String destinationCmm = destination + "Cmm" + System.getProperty("file.separator");
			new File(destinationCmm).mkdirs();
			String codeBaseCmm = codeBase + "Cmm" + System.getProperty("file.separator");
			for (int i = 0; i < cmmlibs.size(); i++, progress++)
				copyURL(codeBaseCmm + cmmlibs.get(i), destinationCmm + cmmlibs.get(i));
		}
	}
	
	public int getProgress(){
		//System.out.println("p - " + progress);
		int ret = 0;
		if (total != 0) ret = (int) (progress*100/total);
		else ret = 0;
		//System.out.println(ret);
		return ret;
	}
	
	private static String getPath(){
		String path = System.getProperty("user.home") 
			+ System.getProperty("file.separator") + ".neurotec" 
			+ System.getProperty("file.separator") + getOSArchExtension() 
			+ System.getProperty("file.separator");
		new File(path).mkdirs();
		return path;
	}
	
	private static String getOSArchExtension(){
		String osarch = "unknown";
		if (System.getProperty("os.name").indexOf("Windows") != -1){
			osarch = "Win";
			if(System.getProperty("os.arch").indexOf("64") != -1) osarch += "64_x64";
			else osarch += "32_x86";
		}
		if (System.getProperty("os.name").indexOf("Linux") != -1){
			osarch = "linux_x86";
			if(System.getProperty("os.arch").indexOf("64") != -1) osarch += "_64";
		}
		return osarch;
	}
	
	private static boolean checkFilesExist(Vector<String> mainlibs){
		String destination = getPath();
		for (String lib : mainlibs) {
			try{
				File f = new File(destination + lib);
				if(!f.exists()) {
					System.out.println(destination + lib + " do not exist");
					return false;
				}
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}
	
	private static void copyURL(String srcURL, String destFile)
	  {
	      try{
	          URL url  = new URL(srcURL);
	      
	          System.out.println("Opening connection to " + srcURL);

	          InputStream is = url.openStream();

	          FileOutputStream fos=null;
	          fos = new FileOutputStream( new File(destFile));
	          int read, count=0;
	          
	          byte [] buffer = new byte[getBufferSize()];
	          
	          do{
	        	  read = is.read(buffer);
	        	  if (read > 0){
	        		  fos.write(buffer, 0, read);
	        		  count += read;
	        	  }
	          }while(read != -1);

	          /*
	          while ((oneChar=is.read()) != -1)
	          {
	             fos.write(oneChar);
	             count++;
	          }
	          */
	          is.close();
	          fos.close();

	          System.out.println(count + " byte(s) copied");
	      }
	      catch (Exception e){ 
	    	  e.printStackTrace();
	      }
	  }
	
	public static void setBufferSize(int bufferSize) {
		NetInstall.bufferSize = bufferSize;
	}

	public static int getBufferSize() {
		return bufferSize;
	}

	class Handler extends DefaultHandler{
		
		ScannerFiles currScanner;
		Vector<ScannerFiles> sf;
		Vector<String> mainlibs;
		Vector<String> cmmlibs;
		String oelem;
		
		
		public void startDocument (){
			//System.out.println("Start document");
	    }

	    public void endDocument (){
	    	//System.out.println("End document");
	    }
	    
	    public void startElement (String uri, String name, String qName, Attributes atts){
	    	if ("mainlib".equals(name)){
	    		//System.out.println("Start parsing mainlib");
	    		mainlibs = new Vector<String>();
	    	}
	    	if ("fpsmm".equals(name)){
	    		//System.out.println("Start parsing fpsmm");
	    		sf = new Vector<ScannerFiles>();
	    	}
	    	if ("lib".equals(name) || 
	    		"name".equals(name) || 
	    		"file".equals(name) || 
	    		"cmmlib".equals(name)){
	    		//System.out.print(name + ":");
	    		oelem = name;
	    	}
	    	if ("scanner".equals(name)){
	    		currScanner = new ScannerFiles();
	    	}
	    	if ("cmm".equals(name)){
	    		//System.out.println("Start parsing mainlib");
	    		cmmlibs = new Vector<String>();
	    	}
	    }


	    public void endElement (String uri, String name, String qName){
	    	
	    	if (name.equals("mainlib")){
	    		//System.out.println("end parsing mainlib");
	    	}
	    	if (name.equals("fpsmm")){
	    		//System.out.println("end parsing fpsmm");
	    	}
	    	if (name.equals("lib") || 
	    		name.equals("name") || 
	    		name.equals("file") ||
	    		"cmmlib".equals(name)){
	    		//System.out.println();
	    		oelem = null;
	    	}
	    	if (name.equals("scanner")){
	    		sf.add(currScanner);
	    	}
	    }


	    public void characters (char ch[], int start, int length){
	    	
	    	if (oelem != null){
	    		if (oelem.equals("lib")){
	    			mainlibs.add(new String(ch,start,length));
	    		}
	    		if (oelem.equals("name")){
	    			currScanner.setName(new String(ch,start,length));
	    		}
	    		if (oelem.equals("file")){
	    			currScanner.addFile(new String(ch,start,length));
	    		}
	    		if (oelem.equals("cmmlib")){
	    			cmmlibs.add(new String(ch,start,length));
	    		}
	    		//System.out.print(new String(ch,start,length));
	    	}
	    	
	    }
	}
}
