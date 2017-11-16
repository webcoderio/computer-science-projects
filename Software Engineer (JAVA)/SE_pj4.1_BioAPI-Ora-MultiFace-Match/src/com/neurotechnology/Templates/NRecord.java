package com.neurotechnology.Templates;

import com.neurotechnology.Library.NativeObject;

public abstract class NRecord extends NativeObject{
	
	private int size;
	
	public abstract long getPBuffer();
	public static native void freePBuffer(long pBuffer);
	
	void setPBufferSize(int size) {
		this.size = size;
	}
	public int getPBufferSize() {
		return size;
	}
}
