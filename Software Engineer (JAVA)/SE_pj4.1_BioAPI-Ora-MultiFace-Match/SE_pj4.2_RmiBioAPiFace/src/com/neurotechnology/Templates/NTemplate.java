package com.neurotechnology.Templates;

import com.neurotechnology.Library.NativeManager;

public class NTemplate extends NRecord{
	
	//private long hNTemplate;
	
	public NTemplate()throws Exception{
		this.setHandle(create());
	}
	
	public NTemplate(byte[] data)throws Exception{
		this.setHandle(createFromMemory(data, data.length));
	}
	
	protected NTemplate(long hNTemplate){
		this.setHandle(hNTemplate); 
	}
	
	public void finalize()throws Throwable{
		super.finalize();
	}
	
	public NTemplate clone(){
		return new NTemplate(clone(this.getHandle()));
	}
	
	
	public void setJNLTemplate(NLTemplate faces)throws Exception{
		try{
			removeFaces(this.getHandle());
		}catch (Exception e) {};
		if (faces != null) addFaces(this.getHandle(), faces.getHandle());
	}
	
	public NLTemplate getJNLTemplate()throws Exception{
		return new NLTemplate(NLTemplate.clone(getFaces(this.getHandle())));
	}
	
	public void setNETemplate(NETemplate eyes)throws Exception{
		try{
			removeEyes(this.getHandle());
		}catch (Exception e) {};
		if (eyes != null) addEyes(this.getHandle(), eyes.getHandle());
	}
	
	public NETemplate getNETemplate()throws Exception{
		return new NETemplate(NETemplate.clone(getEyes(this.getHandle())));
	}
	
	public void setJNFTemplate(NFTemplate fingers)throws Exception{
		try{
			removeFingers(this.getHandle());
		}catch (Exception e) {};
		if (fingers != null) addFingers(this.getHandle(), fingers.getHandle());
	}
	
	public NFTemplate getJNFTemplate()throws Exception{
		return new NFTemplate(NFTemplate.clone(getFingers(this.getHandle())));
	}
	
	public byte[] toByteArray()throws Exception{
		return saveToMemory(this.getHandle());
	}
	

	private static native long create()throws Exception;
	private static native long createFromMemory(byte[] data, int size) throws Exception;
	private static native void removeFaces(long hNTemplate)throws Exception;
	private static native void removeEyes(long hNTemplate)throws Exception;
	private static native void addFaces(long hNTemplate, long hNLTemplate)throws Exception;
	private static native long getFaces(long hNTemplate)throws Exception;
	private static native void removeFingers(long hNTemplate)throws Exception;
	private static native void addFingers(long hNTemplate, long hNFTemplate)throws Exception;
	private static native long getFingers(long hNTemplate)throws Exception;
	private static native void addEyes(long hNTemplate, long hNETemplate)throws Exception;
	private static native long getEyes(long hNTemplate)throws Exception;
	private static native void free(long hNTemplate);
	private static native long clone(long hNTemplate);
	private static native byte[] saveToMemory(long hNTemplate)throws Exception;
	private static native long getPBuffer(long hNRecord, NRecord rec);  
	public long getPBuffer(){
		return getPBuffer(getHandle(), this);
	}
	
	static{
		NativeManager.loadDefault();
		NativeManager.checkLoad("NTemplates");
	}
	
}
