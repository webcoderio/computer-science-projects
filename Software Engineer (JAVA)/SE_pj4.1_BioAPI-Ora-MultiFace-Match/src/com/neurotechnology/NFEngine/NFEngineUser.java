package com.neurotechnology.NFEngine;

import com.neurotechnology.Library.NativeManager;
import com.neurotechnology.Library.NativeObject;

public class NFEngineUser extends NativeObject{

	private NFEngine owner;
	
	protected NFEngineUser(long handle, NFEngine owner){
		this.setHandle(handle);
		this.owner = owner;
	}
	
	
	public int getID() throws Exception{
		if (owner == null || !owner.contains(this)) throw new Exception("User was dispozed or removed from engine");
		return getID(this.getHandle());
	}
	
	
	public NFEngineImage getNFEngineImage() throws Exception{
		if (owner == null || !owner.contains(this)) throw new Exception("User was dispozed or removed from engine");
		NFEngineImage image = new NFEngineImage();
		loadImage(this.getHandle(), image);
		return image;
	}

	protected void finalize() throws Throwable {
		owner = null;
		super.finalize();
	}
	
	public String toString(){
		return new Integer(getID(this.getHandle())).toString();
	}
	
	private static native int getID(long handle);
	private static native void loadImage(long handle, NFEngineImage image);
	
	static{
		NativeManager.defaultlibrary = "NFEngineJavaNative";
		NativeManager.loadDefault();
	}
}
