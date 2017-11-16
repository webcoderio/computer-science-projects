package com.neurotechnology.Faces;


import com.neurotechnology.Library.LibraryInfo;
import com.neurotechnology.Library.NativeManager;
import com.neurotechnology.Library.NativeObject;
import com.neurotechnology.NImages.NImage;
import com.neurotechnology.Templates.NLTemplate;

public class NLExtractor extends NativeObject{
	
	private static final int VLEP_MIN_IOD = 10101;
	private static final int VLEP_MAX_IOD = 10102;
	private static final int VLEP_FACE_CONFIDENCE_THRESHOLD = 10103;
	private static final int VLEP_FACE_QUALITY_THRESHOLD = 10350;
	private static final int VLEP_GENERALIZATION_THRESHOLD = 10501;
	private static final int VLEP_MODE = 1000;
	private static final int VLEP_USE_LIVENESS_CHECK = 10402; // Bool, Default = False
	private static final int VLEP_LIVENESS_THRESHOLD = 10403; // Double [0, 100], Default = 50
	
	private static final int NLEP_FAVOR_LARGEST_FACE = 10104;
	private static final int NLEP_MAX_ROLL_ANGLE_DEVIATION = 10105;
	private static final int NLEP_TEMPLATE_SIZE = 10360;
	
	
	//private long hExtractor;
	private DetectionDetails detectionDetails;
	private ExtractionStatus extractionStatus;
	
	private boolean finalized;
	
	public NLExtractor() throws Exception{
		this.setHandle(create());
		finalized = false;
	}
	
	public void finalize()throws Throwable{
		if(!finalized) ;//free(this.getHandle());
		finalized = true;
		super.finalize();
	}
	
	synchronized public Face detectFace(NImage image) throws Exception{
		Face face = new Face();
		if (detectFace(this.getHandle(), image.getHandle(), face)) 
			return face;
		else 
			return null;
	}
	
	synchronized public Face[] detectFaces(NImage image) throws Exception{
		return detectFaces(this.getHandle(), image.getHandle(), new Face());
	}
	
	synchronized public DetectionDetails detectFacialFeatures(NImage image, Face face)throws Exception{
		DetectionDetails detectionDetails = new DetectionDetails();
		detectFacialFeatures(this.getHandle(), image.getHandle(), face.getWidth(), face.getWidth(),
				face.getConfidence(),
				face.getUpperLeftPoint().x,
				face.getUpperLeftPoint().y,
				face.getRotation().getYaw(), 
				face.getRotation().getPitch(),
				face.getRotation().getRoll(),
				detectionDetails);
		return detectionDetails;
	}
	
	synchronized public NLTemplate extract(NImage image) throws Exception{
		NLTemplate tmpl = new NLTemplate(0);
		detectionDetails = new DetectionDetails();
		extract(this.getHandle(), image.getHandle(), detectionDetails, tmpl, this);
		if (tmpl.getHandle()== 0) 
			return null;
		return tmpl;
	}
	
	synchronized public NLTemplate extractUsingDetails(NImage image, DetectionDetails detectionDetails) throws Exception{
		NLTemplate tmpl = new NLTemplate(0);
		
		extractUsingDetails(this.getHandle(), image.getHandle(),
				detectionDetails.isEyesAvailable(),
				detectionDetails.isFaceAvailable(),
				detectionDetails.getFace().getConfidence(),
				detectionDetails.getFace().getWidth(),
				detectionDetails.getFace().getHeight(),
				detectionDetails.getFace().getUpperLeftPoint().x,
				detectionDetails.getFace().getUpperLeftPoint().y,
				detectionDetails.getEyes().getFirstConfidence(),
				detectionDetails.getEyes().getSecondConfidence(),
				detectionDetails.getEyes().getFirst().x,
				detectionDetails.getEyes().getFirst().y,
				detectionDetails.getEyes().getSecond().x,
				detectionDetails.getEyes().getSecond().y,
				detectionDetails.getFace().getRotation().getYaw(),
				detectionDetails.getFace().getRotation().getPitch(),
				detectionDetails.getFace().getRotation().getRoll(),
				tmpl, this);
		
		if (tmpl.getHandle() == 0) 
			return null;
		return tmpl;
	}

	synchronized public void extractStart(int durationInFrames) throws Exception{
		extractStart(this.getHandle(), durationInFrames);
	}
	
	synchronized public NLTemplate extractNext(NImage image) throws Exception{
		NLTemplate tmpl = new NLTemplate(0);
		detectionDetails = new DetectionDetails();
		if (extractNext(this.getHandle(), image.getHandle(), detectionDetails, tmpl, this)) 
			if (tmpl.getHandle() != 0) return tmpl;
		return null;
	}
	
	synchronized public NLTemplate generalize(NLTemplate[] templates) throws Exception{
		
		NLTemplate tmpl = new NLTemplate(0);
		generalize(this.getHandle(), templates,templates.length, tmpl);
		if (tmpl.getHandle() == 0) 
			return null;
		return tmpl;
	}
	
	synchronized public static LibraryInfo getInfo() throws Exception{
		LibraryInfo libinfo = new LibraryInfo();
		getInfo(libinfo);
		return libinfo;
	}
	
	@Deprecated
	synchronized public void compressTemplate(NLTemplate templ) throws Exception
	{
		templ.setHandle(compressTempl(this.getHandle(),templ.getHandle()));
	}
	
	/*
	@Deprecated
	public static native boolean isRegistered();
	*/
	private static native long create() throws Exception;
	private static native boolean detectFace(long hExtractor, long hImage, Face face) throws Exception;
	private static native Face[] detectFaces(long hExtractor, long hImage, Face face) throws Exception;
	private static native void free(long hExtractor);
	private static native void extract(long hExtractor, long hImage, DetectionDetails detectionDetails, NLTemplate tmpl, NLExtractor extractor);
	private static native void detectFacialFeatures(long hExtractor, long hImage, 
			  int width,
			  int height,
			  double Confidence,
			  int X,
			  int Y,
			  int yaw, int pitch, int roll,
			  DetectionDetails detectionDetails) throws Exception;
	private static native void extractUsingDetails(long hExtractor, long hImage,
			boolean eyesAvalible,
			boolean faceAvailable,
			double faceConfidence,
			int width, int height, 
			int fx, int fy,
			double firstConfidence,
			double secoundConfidence,
			int x1, int y1,
			int x2, int y2,
			int yaw, int pitch, int roll,
			NLTemplate tmpl,
			NLExtractor extractor
		) throws Exception;
	private static native void extractStart(long hExtractor, int durationInFrames) throws Exception;
	private static native boolean extractNext(long hExtractor, long hImage, DetectionDetails detectionDetails, NLTemplate tmpl, NLExtractor extractor) throws Exception;
	private static native void generalize(long hExtractor, NLTemplate[] templates, int count, NLTemplate tmpl) throws Exception;
	private static native void getInfo(LibraryInfo libinfo) throws Exception;
	private static native long compressTempl(long hExctractor, long hTemplate) throws Exception;
	
	private static native void setIntParameter(long hExtractor, int paramcode, int paramvalue);
	private static native int getIntParameter(long hExtractor, int paramcode);
	
	private static native void setDoubleParameter(long hExtractor, int paramcode, double paramvalue);
	private static native double getDoubleParameter(long hExtractor, int paramcode);
	
	private static native void setByteParameter(long hExtractor, int paramcode, byte paramvalue);
	private static native byte getByteParameter(long hExtractor, int paramcode);
	
	private static native void setParameterBoolean(long hExtractor, int parameterId, boolean value);
	private static native boolean getParameterBoolean(long hExtractor, int parameterId);
	
	private static native void setTempalteSize(long hExtractor, int parameterId, int tempalteSize);
	private static native int getTempalteSize(long hExtractor, int parameterId);

	protected void setDetectionDetails(DetectionDetails detectionDetails) {
		this.detectionDetails = detectionDetails;
	}
	
	protected void setExtractionStatus(int status){
		this.extractionStatus = ExtractionStatus.fromInt(status);
	}
	
	synchronized public ExtractionStatus getExtractionStatus(){
		return extractionStatus;
	}

	synchronized public DetectionDetails getDetectionDetails() {
		return detectionDetails;
	} 
	
	synchronized public void setMinIOD(int minIOD) {
		setIntParameter(this.getHandle(), VLEP_MIN_IOD, minIOD);
	}

	synchronized public int getMinIOD() {
		return getIntParameter(this.getHandle(), VLEP_MIN_IOD);
	}
	synchronized public void setMaxIOD(int maxIOD) {
		setIntParameter(this.getHandle(), VLEP_MAX_IOD, maxIOD);
	}

	synchronized public int getMaxIOD() {
		return getIntParameter(this.getHandle(), VLEP_MAX_IOD);
	}
	
	synchronized public void setFavorLargestFace(boolean favorLargestFace){
		setParameterBoolean(this.getHandle(), NLEP_FAVOR_LARGEST_FACE, favorLargestFace);
	}
	
	synchronized public boolean getFavorLargestFace(){
		return getParameterBoolean(this.getHandle(), NLEP_FAVOR_LARGEST_FACE);
	}
	
	synchronized public void setMaxRollAangleDeviation(int maxRollAangleDeviation){
		if (maxRollAangleDeviation > 180 || maxRollAangleDeviation < 0)
			throw new RuntimeException("MaxRollAangleDeviation parameter value out of bounds - " + maxRollAangleDeviation + ", must be 0 - 180");
		setIntParameter(this.getHandle(), NLEP_MAX_ROLL_ANGLE_DEVIATION, maxRollAangleDeviation);
	}
	
	synchronized public int getMaxRollAangleDeviation(){
		return getIntParameter(this.getHandle(), NLEP_MAX_ROLL_ANGLE_DEVIATION);
	}
	
	synchronized public void setTemplateSize(TemplateSize templateSize){
		setTempalteSize(this.getHandle(), NLEP_TEMPLATE_SIZE, templateSize.eval());
	}
	
	synchronized public TemplateSize getTemplateSize(){
		return TemplateSize.parse(getTempalteSize(this.getHandle(), NLEP_TEMPLATE_SIZE));
	}
	synchronized public void setConfidenceThreshold(double confidenceThreshold) {
		setDoubleParameter(this.getHandle(), VLEP_FACE_CONFIDENCE_THRESHOLD, confidenceThreshold);
	}

	synchronized public double getConfidenceThreshold() {
		return getDoubleParameter(this.getHandle(), VLEP_FACE_CONFIDENCE_THRESHOLD);
	}
	synchronized public void setGeneralizationThreshold(double generalizationThreshold) {
		setDoubleParameter(this.getHandle(), VLEP_GENERALIZATION_THRESHOLD, generalizationThreshold);
	}

	synchronized public double getGeneralizationThreshold() {
		return getDoubleParameter(this.getHandle(), VLEP_GENERALIZATION_THRESHOLD);
	}
	synchronized public void setQualityThreshold(int qualityThreshold) {
		if (qualityThreshold > 127) qualityThreshold -= 256;
		setByteParameter(this.getHandle(), VLEP_FACE_QUALITY_THRESHOLD, (byte)qualityThreshold);
	}

	synchronized public int getQualityThreshold() {
		int tmp = getByteParameter(this.getHandle(), VLEP_FACE_QUALITY_THRESHOLD);
		if (tmp < 0) return 256 + tmp;
		return tmp;
	}
	synchronized public void setMode(Mode mode) {
		setIntParameter(this.getHandle(), VLEP_MODE, mode.eval());
	}

	synchronized public Mode getMode() {
		return Mode.getVal(getIntParameter(this.getHandle(), VLEP_MODE));
	}
	
	synchronized public void setLivenessCheck(boolean livenessCheck){
		setParameterBoolean(this.getHandle(), VLEP_USE_LIVENESS_CHECK, livenessCheck);
	}
	
	synchronized public boolean getLivenessCheck(){
		return getParameterBoolean(this.getHandle(), VLEP_USE_LIVENESS_CHECK);
	}
	
	synchronized public void setLivenessThreshold(double livenessThreshold){
		setDoubleParameter(this.getHandle(), VLEP_LIVENESS_THRESHOLD, livenessThreshold);
	}
	
	synchronized public double getLivenessThreshold(){
		return getDoubleParameter(this.getHandle(), VLEP_LIVENESS_THRESHOLD);
	}
	
	static 
	{
		NativeManager.loadDefault();
		NativeManager.checkLoad("NExtractors");
	}
}
