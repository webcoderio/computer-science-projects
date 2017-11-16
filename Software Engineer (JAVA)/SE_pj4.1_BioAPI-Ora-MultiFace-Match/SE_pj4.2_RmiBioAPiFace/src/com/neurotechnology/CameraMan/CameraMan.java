package com.neurotechnology.CameraMan;


import com.neurotechnology.Library.LibraryInfo;
import com.neurotechnology.Library.NativeManager;

public class CameraMan {
	
	public CameraMan() throws Exception{
		initialize();
	}
	
	public void finalize()throws Throwable{
		uninitialize();
		super.finalize();
	}
	
	public Camera[] getCameras()throws Exception{
		if (getCameraCount() == 0) return null;
		Camera cams[] = new Camera[getCameraCount()];
		for (int i = 0; i < cams.length; i++){
			cams[i] = new Camera(getCamera(i), this);
		}
		return cams;
	}
	public static LibraryInfo getInfo()throws Exception{
		LibraryInfo info = new LibraryInfo();
		getInfo(info);
		return info;
	}
	
	public Camera getCameraById(String id)throws Exception{
		return new Camera(getCameraByIdN(id), this);
	}
	
	private static native long getCamera(int index) throws Exception;
	private static native long getCameraByIdN(String id) throws Exception;
	private static native int getCameraCount() throws Exception;
	private static native void getInfo(LibraryInfo info) throws Exception;
	private static native void initialize() throws Exception;
	private static native void uninitialize();
	

	static {
		NativeManager.loadDefault();
		NativeManager.checkLoad("NDeviceManager");
	}
}
