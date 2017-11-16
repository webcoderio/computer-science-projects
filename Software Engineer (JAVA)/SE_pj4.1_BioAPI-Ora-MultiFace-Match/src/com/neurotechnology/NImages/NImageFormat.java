package com.neurotechnology.NImages;

import com.neurotechnology.Library.NativeManager;
import com.neurotechnology.Library.NativeObject;

public class NImageFormat extends NativeObject {

	private NImageFormat(){}
	private NImageFormat(long handle){
		this.setHandle(handle);
	}
	
	public void finalize() throws Throwable{
		//free(this.getHandle());
		super.finalize();
	}
	
	public static NImageFormat[] getFormats(){
		long[] handles = getFormats0(); 
		if (handles == null) return null;
		NImageFormat [] formats = new NImageFormat[handles.length];
		for (int i = 0; i < handles.length; i++)
			formats[i] = new NImageFormat(handles[i]);
		return formats;
	}
	
	public String getName(){
		return getName(this.getHandle());
	}
	
	public String getDefoultFileExtension(){
		return getDefaultFileExtension(this.getHandle());
	}
	
	public NImage loadNImage(String filename){
		return new NImage(loadNImage(this.getHandle(), filename));
	}
	
	public NImage loadNImage(byte [] data){
		return new NImage(loadNImage(this.getHandle(), data, data.length));
	}
	
	public byte [] saveToMemory(NImage image){
		return saveToMemory(this.getHandle(), image.getHandle());
	}
	
	private synchronized static native long[] getFormats0(); 
	private synchronized static native String getName(long handle);
	private synchronized static native String getDefaultFileExtension(long handle);
	private synchronized static native long loadNImage(long handle, String filename);
	private synchronized static native long loadNImage(long handle, byte[] data, int size);
	private synchronized static native void free(long handle);
	private synchronized static native byte [] saveToMemory(long handle, long nimage);
	
	static {
    	NativeManager.loadDefault();
    	NativeManager.checkLoad("NImages");
	}
}
