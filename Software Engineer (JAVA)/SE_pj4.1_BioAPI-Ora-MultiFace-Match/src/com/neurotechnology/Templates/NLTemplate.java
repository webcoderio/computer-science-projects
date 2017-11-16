package com.neurotechnology.Templates;

import com.neurotechnology.Library.NativeManager;

public class NLTemplate extends NRecord{
	
	//private long hNLTemplate;
	private NTemplate ntemplate;
	
	//for intrernal use only
	public NLTemplate(long hNLTemplate){
		this.setHandle(hNLTemplate);
	}
	
	public NLTemplate()throws Exception{
		this.setHandle(create());
	}
	
	public NLTemplate(byte[] data)throws Exception{
		this.setHandle(createFromMemory(data,data.length));
	}
	
	public NLTemplate(NLRecord[] records)throws Exception{
		long [] pBuffers = new long[records.length];
		for (int i = 0; i < records.length; i++){
			pBuffers[i] = records[i].getHandle();
		}
		this.setHandle(createFromRecordArray(pBuffers, pBuffers.length));
	}
	
	public void finalize()throws Throwable{
		//free(this.getHandle());
		super.finalize();
	}
	
	public void add(NLRecord record)throws Exception{
		addNLRecord(this.getHandle(), record.getHandle());
	}
	
	public void add(NLTemplate nltemplate)throws Exception{
		NLRecord[] records = nltemplate.getJNLRecords();
		for(int i = 0; i < records.length; i++)
			this.add(records[i]);
	}
	
	public int calculateSize()throws Exception{
		return calcSize(this.getHandle());
	}
	
	/*
	public boolean check()throws Exception{
		return check(hNLTemplate);
	}
	*/
	
	public void  clearJNLRecords()throws Exception{
		clearRecords(this.getHandle());
	}
	
	public NLTemplate clone(){
		try{
			if (this.getHandle() == 0) return new NLTemplate();
			return new NLTemplate(clone(this.getHandle()));
		}catch (Exception e) {
			throw new Error(e.getMessage());
		}
	}
	
	public int recordCount()throws Exception{
		return getRecordCount(this.getHandle());
	}
	
	/*
	public void pack()throws Exception{
		pack(hNLTemplate);
	}
	*/
	
	public NLRecord[] getJNLRecords() throws Exception{
		long [] pBuffers = unpack(this.getHandle());
		NLRecord[] records = new NLRecord[pBuffers.length];
		for (int i = 0; i < pBuffers.length; i++)
			records[i] = new NLRecord(NLRecord.clone(pBuffers[i]));
		return records;
	}
	
	public byte[] toByteArray() throws Exception{
		return saveToMemory(this.getHandle());
	}
	
	private static native long create() throws Exception;
	private static native void addNLRecord(long hNLTemplate, long pBuffer) throws Exception;
	//private native boolean check(long hNLTemplate) throws Exception;
	private static native void clearRecords(long hNLTemplate) throws Exception;
	protected static native long clone(long hNLTemplate) throws Exception;
	private static native long createFromMemory(byte[] data, int size) throws Exception;
	private static native long createFromRecordArray(long[] pBuffers, int count) throws Exception;
	private static native void free(long hNLTemplate);
	//private native int getRecordCapacity(long HNLTemplate);
	private static native int getRecordCount(long hNLTemplate) throws Exception;
	private static native int calcSize(long hNLTemplate) throws Exception;
	//private native void pack(long hNLTemplate) throws Exception;
	private static native long[] unpack(long hNLTemplate) throws Exception;
	private static native byte[] saveToMemory(long hNLTemplate) throws Exception;
	private static native long getPBuffer(long hNRecord, NRecord rec);  
	public long getPBuffer(){
		return getPBuffer(getHandle(), this);
	}
	
	protected void setNtemplate(NTemplate ntemplate) {
		this.ntemplate = ntemplate;
	}

	protected NTemplate getNtemplate() {
		return ntemplate;
	}

	static{
		NativeManager.loadDefault();
		NativeManager.checkLoad("NTemplates");
	}
	
}
