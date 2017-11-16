package com.neurotechnology.Templates;

import com.neurotechnology.Library.NativeManager;


public class NFTemplate extends NRecord{
	
	//private long hNFTemplate;
	private NTemplate ntemplate;
	
	//for internal use only
	public NFTemplate(long hNFTemplate){
		this.setHandle(hNFTemplate);
	}
	
	public NFTemplate()throws Exception{
		this.setHandle(create());
	}
	
	public NFTemplate(byte[] data)throws Exception{
		this.setHandle(createFromMemory(data,data.length));
	}
	
	public NFTemplate(NFRecord[] records)throws Exception{
		/*
		long [] hNFRecords = new long[records.length];
		for (int i = 0; i < records.length; i++)
			hNFRecords[i] = records[i].getHNFRecord();
		hNFTemplate = createFromRecordArray(hNFRecords, hNFRecords.length);
		*/
		this.setHandle(create());
		for (int i = 0; i < records.length; i++)
			addNFRecord(this.getHandle(), records[i].getHandle());
			
		
	}
	
	public void finalize()throws Throwable{
		//free(this.getHandle());
		super.finalize();
	}
	
	public void addJNFRecord(NFRecord record)throws Exception{
		addNFRecord(this.getHandle(), record.getHandle());
	}
	
	public int calculateSize()throws Exception{
		return calcSize(this.getHandle());
	}
	
	public void  clearJNFRecords()throws Exception{
		clearRecords(this.getHandle());
	}
	
	public NFTemplate clone(){
		try{
			if (this.getHandle() == 0) return new NFTemplate();
			return new NFTemplate(clone(this.getHandle()));
		}catch (Exception e) {
			throw new Error(e.getMessage());
		}
	}
	
	public int recordCount()throws Exception{
		return getRecordCount(this.getHandle());
	}
	
	public NFRecord[] getJNFRecords() throws Exception{
		long [] hNFRecords = unpack(this.getHandle());
		NFRecord[] records = new NFRecord[hNFRecords.length];
		for (int i = 0; i < hNFRecords.length; i++)
			records[i] = new NFRecord(NFRecord.NFRecordClone(hNFRecords[i]));
		return records;
	}
	
	public byte[] toByteArray() throws Exception{
		return saveToMemory(this.getHandle());
	}
	
	private static native long create() throws Exception;
	private static native void addNFRecord(long hNFTemplate, long hNFRecord) throws Exception;
	//private native boolean check(long hNFTemplate) throws Exception;
	private static native void clearRecords(long hNFTemplate) throws Exception;
	protected static native long clone(long hNFTemplate) throws Exception;
	private static native long createFromMemory(byte[] data, int size) throws Exception;
	private static native long createFromRecordArray(long[] hNFRecords, int count) throws Exception;
	private static native void free(long hNFTemplate);
	//private native int getRecordCapacity(long HNFTemplate);
	private static  native int getRecordCount(long hNFTemplate) throws Exception;
	private static native int calcSize(long hNFTemplate) throws Exception;
	//private native void pack(long hNLTemplate) throws Exception;
	private static native long[] unpack(long hNFTemplate) throws Exception;
	private static native byte[] saveToMemory(long hNFTemplate) throws Exception;
	private static native long getPBuffer(long hNRecord, NRecord rec);  
	public long getPBuffer(){
		return getPBuffer(getHandle(), this);
	}
	
	void setNtemplate(NTemplate ntemplate) {
		this.ntemplate = ntemplate;
	}

	NTemplate getNtemplate() {
		return ntemplate;
	}

	static{
		NativeManager.loadDefault();
		NativeManager.checkLoad("NTemplates");
	}
}
