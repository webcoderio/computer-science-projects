package com.neurotechnology.ScannerMan;

import com.neurotechnology.Library.NativeManager;
import com.neurotechnology.NImages.NImage;

public class Scanner {
	
	private long hScanner;
	
	protected  Scanner(long hScanner){
		this.hScanner = hScanner;
	}
	public String GetID(){
		return FPScannerGetID(hScanner);
	}
	public boolean IsCapturing(){
		return FPScannerIsCapturing(hScanner);
	}
	public void SetIsCapturingChangedCallback(IsCapturingChanged isCapturingChanged){
		FPScannerSetIsCapturingChangedCallback(isCapturingChanged, hScanner);
	}
	public void SetFingerPlacedCallback(FingerPlaced fingerPlaced){
		FPScannerSetFingerPlacedCallback(fingerPlaced, hScanner);
	}
	public void SetFingerRemovedCallback(FingerRemoved fingerRemoved){
		FPScannerSetFingerRemovedCallback(fingerRemoved, hScanner);
	}
	public void SetImageScannedCallback(ImageScanned imageScanned){
		FPScannerSetImageScannedCallback(imageScanned, hScanner);
	}
	public void StartCapturing(){
		FPScannerStartCapturing(hScanner);
	}
	public void StartCapturingForOneImage(){
		FPScannerStartCapturingForOneImage(hScanner);
	}
	public void StopCapturing(){
		FPScannerStopCapturing(hScanner);
	}
	
	private static native String FPScannerGetID(long hScanner);
	private static native boolean FPScannerIsCapturing(long hScanner);
	private static native void FPScannerSetIsCapturingChangedCallback(IsCapturingChanged isCapturingChanged, long hScanner);
	private static native void FPScannerSetFingerPlacedCallback(FingerPlaced fingerPlaced, long hScanner);
	private static native void FPScannerSetFingerRemovedCallback(FingerRemoved fingerRemoved, long hScanner);
	private static native void FPScannerSetImageScannedCallback(ImageScanned ImageScanned, long hScanner);
	private static native void FPScannerStartCapturing(long hScanner);
	private static native void FPScannerStartCapturingForOneImage(long hScanner);
	private static native void FPScannerStopCapturing(long hScanner);
	
    static {
    	NativeManager.loadDefault();
    	NativeManager.checkLoad("NDeviceManager");
	}
    
    public String toString(){
    	return GetID();
    }
}
