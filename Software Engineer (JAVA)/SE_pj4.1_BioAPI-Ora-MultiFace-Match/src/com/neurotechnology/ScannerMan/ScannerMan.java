package com.neurotechnology.ScannerMan;

import com.neurotechnology.Library.NativeManager;
import com.neurotechnology.NImages.NImage;

public class ScannerMan {
	
	public ScannerMan()throws ScanManException{
		scannerManInitialize();
		setJNImageclass(NImage.getEmptyImage());
	} 
	
	public ScannerMan(ScannerModule[] scannerModules)throws ScanManException{
		String scanners = "";
		for(int i = 0; i < scannerModules.length; i++){
			if(scanners.length() != 0) scanners += ";";
			scanners += scannerModules[i].getName();
		}
		scannerManInitializeWithModules(scanners);
		setJNImageclass(NImage.getEmptyImage());
	}
	
	public void finalize(){
		scannerManUninitialize();
	}
	public void initialize() throws ScanManException{
		scannerManInitialize();
	}
	public void uninitialize(){
		scannerManUninitialize();
	}
	
	/*
	@Deprecated
	
	public static boolean isRegistered(){
		return scannerManIsRegistered();
	}
	*/
	
	public Scanner[] getScanners() throws ScanManException {
		if (getScannerCount() == 0) return null;
		Scanner[] scanners = new Scanner[getScannerCount()];
		for (int i = 0; i < scanners.length; i++)
			scanners[i] = new Scanner(getScanner(i));
		return scanners;
	}
	
	public static ScannerModule[] getAvailableScannerModules(){
		String modules = getAvailableScannerModules0();
		if(modules == null) return null;
		
		String[] names = modules.split(";");
		ScannerModule[] scannerModules = new ScannerModule[names.length];
		
		for(int i = 0; i < names.length; i++)
			scannerModules[i] = new ScannerModule(names[i]);
		
		return scannerModules;
	}
	
	//Native methods
	private static native int getScannerCount() throws ScanManException;
	private static native long getScanner(int index) throws ScanManException;
	private static native void scannerManInitialize() throws ScanManException;
	private static native void scannerManInitializeWithModules(String modules) throws ScanManException;
	private static native void scannerManUninitialize();
	//private static native boolean scannerManIsRegistered();
	private static native void setJNImageclass(NImage image);
	private static native String getAvailableScannerModules0();
	
	//Loading Library
    static {
    	NativeManager.loadDefault();
    	if (System.getProperty("os.name").indexOf("Windows") != -1) NativeManager.checkLoad("NDeviceManager");
	}
	
}
