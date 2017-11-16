package com.neurotechnology.Fingers;

import com.neurotechnology.Library.NativeManager;
import com.neurotechnology.Library.NativeObject;
import com.neurotechnology.NImages.NImage;
import com.neurotechnology.NMatcher.MatcherMode;
import com.neurotechnology.Templates.NFRecord;


public class NFExtractor extends NativeObject {
	//private long pHExtractor;
	private int genIndex;
	
	static final int VFEP_RETURNED_IMAGE = 190;
	static final int VFEP_USE_QUALITY = 900; 
	static final int VFEP_QUALITY_THRESHOLD = 910;
	static final int VFEP_GENERALIZATION_THRESHOLD = 300;
	static final int VFEP_GENERALIZATION_MAXIMAL_ROTATION = 301; 
	static final int VFEP_MODE = 1000;
	static final int VFEP_TEMPLATE_SIZE = 100; 
	static final int VFEP_EXTRACTED_RIDGE_COUNTS = 120;
	
	//>>Parameters
	private int generalizationThreshold = 0;
	private int generalizationMaximalRotation = 180;
	private int qualityThreshhold = 0;
	private ReturnedImage returnedImage = ReturnedImage.None;
	private boolean usequality = false;
	private MatcherMode mode = MatcherMode.GENERAL;
	private int genPicCount = 3;
	private TemplateSize templateSize = TemplateSize.large;
	private int extractedRidgeCounts = 0;
	//<<Parameters
	
	public NFExtractor() throws Exception{
		this.setHandle(VfeCreate());
	}
	public void finalize()throws Throwable{
		//VfeFree(this.getHandle());
		super.finalize();
	}
	
	public void reset()throws Exception{
		VfeReset(this.getHandle());
	}
	
	public NFRecord ExtractFromUnpackedImage(NImage image,Position position, ImpressionType impressionType)throws Exception{
		image.convertToGreyscale();
		NFRecord nfRec = new NFRecord(0);
		nfRec.setHandle(VfeExtractUnpackedFromImage(this.getHandle(),image.getHandle(),position.eval(),impressionType.eval()));
		return nfRec;
	}
	public NFRecord generalize(NFRecord[] recs)  throws Exception{
		NFRecord  nfRec = new NFRecord(0);
		long [] hNFRecords = new long[recs.length];
		for (int i = 0; i < recs.length; i++){
			hNFRecords[i] = recs[i].getHandle();
		}
		genIndex = VfeGeneralizeUnpacked(this.getHandle(),hNFRecords,hNFRecords.length,nfRec);
		return nfRec;
	}
	
	/*
	@Deprecated
	public static boolean isRegistered(){
		return VfeIsRegistered();
	}
	*/
	
	public void updateParameters() throws Exception{
		getParameters(this.getHandle(), this);
	}

	private static native long VfeCreate()  throws Exception;
	private static native void VfeFree(long hExtractor);
	private static native void VfeReset(long hExtractor)  throws Exception;
	private static native int VfeGeneralizeUnpacked(long hExtractor,long [] hNFRecords,int count,NFRecord genRec) throws Exception;
	private static native boolean VfeIsRegistered();
	private static native void setParameters(long pHExtractor,int param,NFExtractor extractor);
	private static native void getParameters(long pHExtractor, NFExtractor extractor) throws Exception;
	
	private static native long VfeExtractUnpackedFromImage(long hExtractor, long hImage,
			int position, int impressionType)throws Exception;
	
	protected void setGenIndex(int genIndex) {
		this.genIndex = genIndex;
	}

	public int getGenIndex() {
		return genIndex;
	}


	public void setGeneralizationThreshold(int generalizationThreshold) {
		this.generalizationThreshold = generalizationThreshold;
		setParameters(this.getHandle(), VFEP_GENERALIZATION_THRESHOLD, this);
	}
	public int getGeneralizationThreshold() {
		return generalizationThreshold;
	}


	public void setGeneralizationMaximalRotation(int generalizationMaximalRotation) {
		this.generalizationMaximalRotation = generalizationMaximalRotation;
		setParameters(this.getHandle(), VFEP_GENERALIZATION_MAXIMAL_ROTATION, this);
	}
	public int getGeneralizationMaximalRotation() {
		return generalizationMaximalRotation;
	}

	public void setExtractedRidgeCounts(int extractedRidgeCounts) {
		this.extractedRidgeCounts = extractedRidgeCounts;
		setParameters(this.getHandle(), VFEP_EXTRACTED_RIDGE_COUNTS, this);
	}
	public int getExtractedRidgeCounts() {
		return extractedRidgeCounts;
	}
	
	public void setTemplateSize(TemplateSize templateSize) {
		this.templateSize = templateSize;
		setParameters(this.getHandle(), VFEP_TEMPLATE_SIZE, this);
	}
	public TemplateSize getTemplateSize() {
		return templateSize;
	}
	
	private int getIntTemplateSize(){
		return templateSize.eval();
	}
	
	private void setIntTemplateSize(int intTemplateSize){
		this.templateSize = TemplateSize.getVal(intTemplateSize);
	}
	
	public void setQualityThreshhold(int qualityThreshhold) {
		this.qualityThreshhold = qualityThreshhold;
		setParameters(this.getHandle(), VFEP_QUALITY_THRESHOLD, this);
	}
	public int getQualityThreshhold() {
		return qualityThreshhold;
	}


	public void setReturnedImage(ReturnedImage returnedImage) {
		this.returnedImage = returnedImage;
		setParameters(this.getHandle(), VFEP_RETURNED_IMAGE, this);
	}
	public ReturnedImage getReturnedImage() {
		return returnedImage;
	}
	
	private void setIntReturnedImage(int val){
		this.returnedImage = ReturnedImage.getVal(val);
	}
	
	private int getIntReturnedImage(){
		return returnedImage.eval();
	}


	public void setUsequality(boolean usequality) {
		this.usequality = usequality;
		setParameters(this.getHandle(), VFEP_USE_QUALITY, this);
	}
	public boolean isUsequality() {
		return usequality;
	}


	public void setMode(MatcherMode mode) {
		this.mode = mode;
	}
	
	public void setIntMode(int mode){
		this.mode = MatcherMode.getVal(mode);
	}
	public MatcherMode getMode() {
		return mode;
	}
	
	private int getIntMode(){
		return mode.eval();
	}

	public void setGenPicCount(int genPicCount) {
		this.genPicCount = genPicCount;
	}
	public int getGenPicCount() {
		return genPicCount;
	}

	static {
    	NativeManager.loadDefault();
    	NativeManager.checkLoad("NExtractors");
	}
}
