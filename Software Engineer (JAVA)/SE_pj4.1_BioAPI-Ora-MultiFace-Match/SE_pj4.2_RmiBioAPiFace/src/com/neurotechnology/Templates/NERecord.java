package com.neurotechnology.Templates;

import com.neurotechnology.Library.NativeManager;

public class NERecord extends NRecord {

	public NERecord(long handle){
		this.setHandle(handle);
	}
	
	public NERecord(byte [] data){
		this.setHandle(createFromMemory(data, data.length));
	}
	
	public void finalize() throws Throwable{
		//free(this.getHandle());
		super.finalize();
	}
	
	public NERecord clone(){
		return new NERecord(clone(this.getHandle()));
	}
	
	public byte[] toByteArray(){
		return toByteArray(this.getHandle());
	}
	
	public int getSize(){
		return getSize(this.getHandle());
	}
	
	public NEPosition getNEPosition(){
		return NEPosition.getVal(getNEPosition(this.getHandle()));
	}
	
	public void setNEPosition(NEPosition position){
		setPosition(this.getHandle(), position.eval());
	}
		
	public int getHeight(){
		return getHeight(this.getHandle());
	}
	
	public int getWidth(){
		return getWidth(this.getHandle());
	}
	
	public int getQuality(){
		return getQuality(this.getHandle());
	}
	
	public void setQuality(int quality){
		setQuality(this.getHandle(), quality);
	}
	
	public long getPBuffer(){
		return getPBuffer(getHandle(), this);
	}
	
	
	private static native long createFromMemory(byte[] data, int size);
	private static native void free(long handle);
	protected static native long clone(long handle);
	private static native byte[] toByteArray(long handle);
	private static native int getSize(long handle);
	private static native int getNEPosition(long handle);
	private static native void setPosition(long handle, int position);
	private static native int getHeight(long handle);
	private static native int getWidth(long handle);
	private static native int getQuality(long handle);
	private static native void setQuality(long handle, int quality);
	private static native long getPBuffer(long handle, NRecord rec);
	
	static{
		NativeManager.loadDefault();
		NativeManager.checkLoad("NTemplates");
	}
}
