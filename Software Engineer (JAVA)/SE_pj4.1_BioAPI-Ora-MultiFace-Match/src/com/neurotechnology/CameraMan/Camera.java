package com.neurotechnology.CameraMan;


import com.neurotechnology.Library.NativeManager;
import com.neurotechnology.Library.NativeObject;
import com.neurotechnology.NImages.NImage;

public class Camera extends NativeObject{
	
	private static final int CAMERAP_MIRROR_HORIZONTAL = 10200;
	private static final int CAMERAP_MIRROR_VERTICAL = 10201;
	private static final int CAMERAP_AUTOMATIC_SETTINGS = 10301;
	
	
	//private long hCamera;
	private CameraMan owner;
	
	protected Camera(long hCamera, CameraMan owner){
		this.setHandle(hCamera);
		this.owner = owner;
	}
	
	public String getID() throws Exception{
		return getID(this.getHandle());
	}
	
	public String toString(){
		try{
			return getID(this.getHandle());
		}catch (Exception e) {
				
		}
		return super.toString();
	}
	
	public VideoFormat[] getAvailableVideoFormats() throws Exception{
		return getAvailableVideoFormats(this.getHandle(), new VideoFormat());
	}
	
	public VideoFormat getVideoFormat() throws Exception{
		VideoFormat vf = new VideoFormat();
		getCameraVideoFormat(this.getHandle(), vf);
		return vf;
	}
	
	public boolean isMirrorHorizontal() throws Exception{
		return getParameter(this.getHandle(), CAMERAP_MIRROR_HORIZONTAL);
	}
	
	synchronized public void setMirrorHorizontal(boolean value) throws Exception{
		setParameter(this.getHandle(), CAMERAP_MIRROR_HORIZONTAL, value);
	}
	
	public boolean isMirrorVertical() throws Exception{
		return getParameter(this.getHandle(), CAMERAP_MIRROR_VERTICAL);
	}
	
	synchronized public void setMirrorVertical(boolean value) throws Exception{
		setParameter(this.getHandle(), CAMERAP_MIRROR_VERTICAL, value);
	}
	
	public boolean isAutomaticSettings() throws Exception{
		return getParameter(this.getHandle(), CAMERAP_AUTOMATIC_SETTINGS);
	}
	
	synchronized public void setAutomaticSettings(boolean value) throws Exception{
		setParameter(this.getHandle(), CAMERAP_AUTOMATIC_SETTINGS, value);
	}
	
	public boolean isCapturing() throws Exception{
		return cameraIsCapturing(this.getHandle());
	}
	
	synchronized public void startCapturing() throws Exception{
		startCapturing(this.getHandle());
	}
	
	synchronized public void stopCapturing() throws Exception{
		stopCapturing(this.getHandle());
	}
	
	synchronized public void setVideoFormat(VideoFormat videoFormat) throws Exception{
		boolean capturing = cameraIsCapturing(this.getHandle());
		if (capturing)
			stopCapturing(this.getHandle());
		setVideoFormat(this.getHandle(), videoFormat);
		if (capturing) 
			startCapturing(this.getHandle());
	}
	
	synchronized public NImage getCurrentFrame() throws Exception{
		NImage image = NImage.getEmptyImage();
		image.setHandle(getCurrentFrame(this.getHandle()));
		return image; 
	}
	
	private static native String getID(long hCamera) throws Exception;
	private static native VideoFormat[] getAvailableVideoFormats(long hCamera, VideoFormat vf) throws Exception;
	private static native void getCameraVideoFormat(long hCamera, VideoFormat vf) throws Exception;
	private static native void setParameter(long hCamera, int parameterId, boolean value) throws Exception;
	private static native boolean getParameter(long hCamera, int parameterId) throws Exception;
	private static native boolean cameraIsCapturing(long hCamera) throws Exception;
	private static native void setVideoFormat(long hCamera, VideoFormat vforamt) throws Exception;
	private static native void startCapturing(long hCamera) throws Exception;
	private static native void stopCapturing(long hCamera) throws Exception;
	private static native long getCurrentFrame(long hCamera) throws Exception;
	
	static {
		NativeManager.loadDefault();
		NativeManager.checkLoad("NDeviceManager");
	}
	
}
