package com.neurotechnology.Templates;

import com.neurotechnology.Library.NativeManager;

public class NLRecord extends NRecord{
	//private long pBuffer;
	//protected static int count;
	
	protected NLRecord(long HNLRecord){
		this.setHandle(HNLRecord);
		//count++;
	}
	
	public void finalize()throws Throwable{
		//free(this.getHandle());
		super.finalize();
	}
	
	protected static NLRecord create(long pBuffer){
		return new NLRecord(pBuffer);
	}
	
	public NLRecord(byte[] bytes){
		this.setHandle(fromByteArray(bytes, bytes.length));
	}
	
	public byte[] toByteArray(){
		return toByteArray(this.getHandle());
	}
	
	
	private static native byte[] toByteArray(long pBuffer);
	private static native long fromByteArray(byte[] bytes, int size);
	private static native void free(long pBuffer);
	private static native long getPBuffer(long hNRecord, NRecord rec);  
	protected static native long clone(long handle);
	public long getPBuffer(){
		return getPBuffer(getHandle(), this);
	}
	
	public int getTemplateQuality()
	{
		return getNLRecordQualityMem(this.getHandle());
	}
	
	private static native int getNLRecordQualityMem(long pBuffer);

	
	
	static{
		NativeManager.loadDefault();
		NativeManager.checkLoad("NTemplates");
	}
}
