package com.neurotechnology.Templates;

import com.neurotechnology.Library.NativeManager;

public class NETemplate extends NRecord {

	private NTemplate ntemplate;
	
	//for intrernal use only
	public NETemplate(long handle){
		this.setHandle(handle);
	}
	
	public NETemplate()throws Exception{
		this.setHandle(create());
	}
	
	public NETemplate(byte[] data)throws Exception{
		this.setHandle(createFromMemory(data,data.length));
	}
	
	public NETemplate(NERecord[] records)throws Exception{
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
	
	public void add(NERecord record)throws Exception{
		addNERecord(this.getHandle(), record.getHandle());
	}
	
	public void add(NETemplate netemplate)throws Exception{
		NERecord[] records = netemplate.getNERecords();
		for(int i = 0; i < records.length; i++)
			this.add(records[i]);
	}
	
	public int calculateSize()throws Exception{
		return calcSize(this.getHandle());
	}
	
	public void  clearJNLRecords()throws Exception{
		clearRecords(this.getHandle());
	}
	
	public NETemplate clone(){
		try{
			if (this.getHandle() == 0) return new NETemplate();
			return new NETemplate(clone(this.getHandle()));
		}catch (Exception e) {
			throw new Error(e.getMessage());
		}
	}
	
	public int recordCount()throws Exception{
		return getRecordCount(this.getHandle());
	}
	
	
	public NERecord[] getNERecords() throws Exception{
		long [] pBuffers = unpack(this.getHandle());
		NERecord[] records = new NERecord[pBuffers.length];
		for (int i = 0; i < pBuffers.length; i++)
			records[i] = new NERecord(NERecord.clone(pBuffers[i]));
		return records;
	}
	
	public byte[] toByteArray() throws Exception{
		return saveToMemory(this.getHandle());
	}
	
	private static native long create() throws Exception;
	private static native void addNERecord(long handle, long pBuffer) throws Exception;
	private static native void clearRecords(long handle) throws Exception;
	protected static native long clone(long hNLTemplate) throws Exception;
	private static native long createFromMemory(byte[] data, int size) throws Exception;
	private static native long createFromRecordArray(long[] pBuffers, int count) throws Exception;
	private static native void free(long hNLTemplate);
	private static native int getRecordCount(long hNLTemplate) throws Exception;
	private static native int calcSize(long hNLTemplate) throws Exception;
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
