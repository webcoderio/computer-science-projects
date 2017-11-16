package com.neurotechnology.Library;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class NativeManager {

	private static boolean isLibraryLoaded;
	public static String defaultlibrary = "NeurotecJavaNative";
	
	public static void loadDefault(){
		if(isLibraryLoaded) return;
		System.loadLibrary(defaultlibrary);
		setLoaded(true);
		compareVersions();
	}
	
	public static void loadFile(String neuratecjavanative, String nlicensing){
		if(isLibraryLoaded) return;
		System.out.println("Loading " + defaultlibrary);
		System.load(neuratecjavanative);
		setLoaded(true);
		compareVersions();
	}
	
	protected static void setLoaded(boolean value){ 
		isLoadOk();
		if (value)
			isLibraryLoaded = isLoadOk();
		else {
			isLibraryLoaded = false;
			return;
		}
		if(!isLibraryLoaded) throw new Error("Loading failed");
	}
	
	public static boolean isLoaded(){
		return isLibraryLoaded;
	}
	
	public static String getProductName() throws Exception{
		if(!isLibraryLoaded)throw new Exception("Library not loaded");
		return getProductNameN();
	}
	
	public static int getVersionMajor()throws Exception{
		if(!isLibraryLoaded)throw new Exception("Library not loaded");
		return  getVersionMajorN();
	}
	
	public static int getVersionMinor()throws Exception{
		if(!isLibraryLoaded)throw new Exception("Library not loaded");
		return  getVersionMinorN();
	}
	
	private static void compareVersions(){
		LibraryInfo libinf = getWrapperLibraryInfo();
		if(getVersionMajorN() != libinf.getVersionMajor() || getVersionMinorN() != libinf.getVersionMinor())
			System.out.println("WARNING: native library(" + getVersionMajorN() + "." + getVersionMinorN()
			+ ") and java wrapper(" + libinf.getVersionMajor() + "." + libinf.getVersionMinor() + ") versions do not match");
	}
	
	public static LibraryInfo getWrapperLibraryInfo(){
		LibraryInfo libinf = new LibraryInfo();
		try{
			URL url = libinf.getClass().getResource("/com/neurotechnology/Library/LibraryInfo.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
			libinf.setCompany(br.readLine());
			libinf.setCopyright(br.readLine());
			libinf.setProduct(br.readLine());
			libinf.setTitle(br.readLine());
			libinf.setVersionBuild(new Integer(br.readLine()).intValue());
			libinf.setVersionMajor(new Integer(br.readLine()).intValue());
			libinf.setVersionMinor(new Integer(br.readLine()).intValue());
			libinf.setVersionRevision(new Integer(br.readLine()).intValue());
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return libinf;
	}
	
	public static String getFolderByCCIDL(CCIDL ccidl){
		return getFolderByCCIDL(ccidl.eval());
	}
	
	private static native String getProductNameN();
	private static native int getVersionMajorN();
	private static native int getVersionMinorN();
	private static native boolean isLoadOk();
	public static native void checkLoad(String libraryName);
	private static native String getFolderByCCIDL(int id); 
	
	static{
		NativeManager.loadDefault();
		NativeManager.checkLoad("NCore");
	}

}
