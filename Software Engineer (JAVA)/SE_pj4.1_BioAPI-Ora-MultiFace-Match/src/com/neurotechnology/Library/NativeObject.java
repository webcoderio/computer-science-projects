package com.neurotechnology.Library;

public class NativeObject extends Object{

	private long handle;
	
	private static int count;
	
	public NativeObject(){
		count++;
		//System.out.println(this.getClass() + " created, total - " + count + " - " + handle);
	}

	public void setHandle(long handle) {
		this.handle = handle;
	}

	public long getHandle() {
		return handle;
	}
	
	protected void finalize() throws Throwable {
		count--;
		//System.out.println(this.getClass() + " finalized, total - " + count + " F- " + handle);
		if(handle != 0) NativeObject.free(handle);
		handle = 0;
		super.finalize();
	}
	
	private static native void free(long handle);
	
	static{
		NativeManager.loadDefault();
		NativeManager.checkLoad("NCore");
	}
}
