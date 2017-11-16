package com.neurotechnology.NMatcher;

import com.neurotechnology.Library.LibraryInfo;
import com.neurotechnology.Library.NativeManager;
import com.neurotechnology.Library.NativeObject;
import com.neurotechnology.Templates.NRecord;

public class NMatcher extends NativeObject{
//	 Part ids
	public final static int PartNone = 0;
	public final static int PartNfm = 1;
	public final static int PartFingers = 10;
	public final static int PartFaces = 20;
	public final static int PartNlm = 1;

	// Main parameters

	public final static int ParameterName = 10;
	public final static int ParameterVersionHigh = 11;
	public final static int ParameterVersionLow = 12;
	public final static int ParameterCopyright = 13;

	public final static int ParameterMatchingThreshold = 100;

	public final static int ParameterFusionType = 200;
	public final static int ParameterFacesMatchingThreshold = 210;
	
	public final static int ParameterMinMatchedFingerCount = 201;
	public final static int ParameterMinMatchedFingerCountThreshold = 202;
	public final static int ParameterMatchingSpeed = 220;
	
	public final static int ParameterMaximalRotation = 201;
	
	public final static int ParameterFaceMatchingSpeed = 10001;

	public final static int ParameterMode = 1000;
	
	private static final int VLMP_MATCHING_THRESHOLD = 100;
	
	static final int NMP_MATCHING_THRESHOLD = 100;
	static final int NESMP_MATCHING_THRESHOLD = 100;
	static final int NMP_MAXIMAL_ROTATION = 201;
	static final int NMP_MODE = 1000;
	static final int NMP_PART_NONE = 0;	
	static final int NMP_PART_FINGERS = 10;
	static final int NFSM_PART_NFM = 1;
	
	//>>Parameters
	private int matchingTreshold = 0;
	private int maximalRotation = 180;
	private MatcherMode mode = MatcherMode.GENERAL;
	private Speed fingerspeed = Speed.Low;
	private boolean uptodate;
	//<<Parameters
	
	
	//private long hMatcher;
	private boolean finalized;
	private long currMatchDetail;
	
	public NMatcher() throws Exception{
		this.setHandle(create());
		finalized = false;
	}
	public void finalize(){
		if(!finalized) ;//  free(this.getHandle());
		finalized = true;
	}
	synchronized public void identifyStart(NRecord template) throws Exception{
		long pBuffer = template.getPBuffer();
		try{
			identifyStart(this.getHandle(), pBuffer, template.getPBufferSize());
		}catch (Exception e) {
			throw e;
		}
		finally{
			NRecord.freePBuffer(pBuffer);
		}
	}
	synchronized public int identifyNext(NRecord template) throws Exception{
		long pBuffer = template.getPBuffer();
		int ret = identifyNext(this.getHandle(), pBuffer, template.getPBufferSize());
		NRecord.freePBuffer(pBuffer);
		return ret;
	}
	synchronized public void identifyEnd() throws Exception{
		identifyEnd(this.getHandle());
	}
	static public LibraryInfo getInfo() throws Exception{
		LibraryInfo libInf = new LibraryInfo();
		getInfo(libInf);
		return libInf;
	}
	public void reset() throws Exception{
		reset(this.getHandle());
	}
	synchronized public int verify(NRecord template1, NRecord template2) throws Exception{
		long pBuffer1 = template1.getPBuffer();
		long pBuffer2 = template2.getPBuffer();
		int ret = verify(this.getHandle(), pBuffer1, template1.getPBufferSize(), pBuffer2, template2.getPBufferSize());
		NRecord.freePBuffer(pBuffer1);
		NRecord.freePBuffer(pBuffer2);
		return ret;
	}
	private static native long create() throws Exception;
	private static native void free(long hMatcher);
	private static native void identifyStart(long hMatcher, long hTemplate, int size) throws Exception;
	private static native int identifyNext(long hMatcher, long hTemplate, int size) throws Exception;
	private static native void identifyEnd(long hMatcher) throws Exception;
	private static native void getInfo(LibraryInfo libInf) throws Exception;
	private static native void reset(long hMatcher) throws Exception;
	private static native int verify(long hMatcher, long hTemplate1, int size1, long hTemplate2, int size2)  throws Exception;
	private static native void setIntParameter(long hMatcher, int paramcode, int paramvalue);
	private static native int getIntParameter(long hMatcher, int paramcode);
	
	private static native void identifyStart(long hMatcher, byte[] tempalte, int size);
	private static native int identifyNext(long hMatcher, byte[] tempalte, int size);
	private static native int verify(long hMatcher, byte[] tempalte1, int size1, byte[] tempalte2, int size2);
	
	synchronized public void identifyStart(byte [] template){
		identifyStart(this.getHandle(), template, template.length);
	}
	synchronized public int identifyNext(byte [] template){
		return identifyNext(this.getHandle(), template, template.length);
	}
	
	synchronized public int verify(byte [] template1, byte [] template2){
		return verify(this.getHandle(), template1, template1.length, template2, template2.length);
	}
	
	/*
	@Deprecated
	public static native boolean isRegistered();
	*/
	private static native void setParameters(long hMatcher, int idType, int param, NMatcher matcher);
	private static native void getParameters(long hMatcher, NMatcher matcher)throws Exception;
	
	public void setFaceMatchingThreshold(int matchingThreshold){
		setIntParameter(this.getHandle(), VLMP_MATCHING_THRESHOLD, matchingThreshold);
	}
	
	public int getFaceMatchingThreshold(){
		return getIntParameter(this.getHandle(), VLMP_MATCHING_THRESHOLD);
	}
	
	public void setEyesMatchingThreshold(int matchingThreshold){
		setIntParameter(this.getHandle(), NESMP_MATCHING_THRESHOLD, matchingThreshold);
	}
	
	public int getEyesMatchingThreshold(){
		return getIntParameter(this.getHandle(), NESMP_MATCHING_THRESHOLD);
	}
	
	void setFingerCurrMatchDetail(long currMatchDetail) {
		this.currMatchDetail = currMatchDetail;
	}

	long getFingerCurrMatchDetail() throws Exception{
		if(!uptodate){
			uptodate = true;
			getParameters(this.getHandle(),this);
		}
		return currMatchDetail;
	}

	public void setFingerMatchingTreshold(int matchingTreshold) {
		this.matchingTreshold = matchingTreshold; 
		setParameters(this.getHandle(), NMP_PART_NONE, NMP_MATCHING_THRESHOLD, this);
	}
	
	public Speed getFingerprintMatchingSpeed()throws Exception{
		if(!uptodate){
			uptodate = true;
			getParameters(this.getHandle(),this);
		}
		return fingerspeed;
	}

	public void setFingerprintMatchingSpeed(Speed speed){
		this.fingerspeed = speed;
		setParameters(this.getHandle(), NMP_PART_FINGERS, ParameterMatchingSpeed, this);
	}
	
	void setFingerIntSpeed(int speed){
		this.fingerspeed = Speed.getValue(speed);
	}
	
	int getIntFingerSpeed(){
		return fingerspeed.eval();
	}
	
	
	public int getFingerMatchingTreshold()throws Exception {
		if(!uptodate){
			uptodate = true;
			getParameters(this.getHandle(),this);
		}
		return matchingTreshold;
	}

	public void setFingerMaximalRotation(int maximalRotation) {
		this.maximalRotation = maximalRotation;
		setParameters(this.getHandle(), NMP_PART_FINGERS, NMP_MAXIMAL_ROTATION, this);
	}

	public int getFingerMaximalRotation() throws Exception{
		if(!uptodate){
			uptodate = true;
			getParameters(this.getHandle(),this);
		}
		return maximalRotation;
	}

	public void setFingerMode(MatcherMode mode) {
		this.mode = mode;
		setParameters(this.getHandle(), NMP_PART_FINGERS + NFSM_PART_NFM, NMP_MODE, this);
	}
	
	protected void setFingerIntMode(int mode) {
		this.mode = MatcherMode.getVal(mode);
	}

	public MatcherMode getFingerMode() throws Exception{
		if(!uptodate){
			uptodate = true;
			getParameters(this.getHandle(),this);
		}
		return mode;
	}
	
	protected int getIntMode(){
		return mode.eval();
	}
	
	static{
		NativeManager.loadDefault();
		NativeManager.checkLoad("NMatchers");
	}
}
