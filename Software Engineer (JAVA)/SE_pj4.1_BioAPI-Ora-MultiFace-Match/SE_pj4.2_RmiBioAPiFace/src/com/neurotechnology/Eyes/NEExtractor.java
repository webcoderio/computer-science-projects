package com.neurotechnology.Eyes;

import java.awt.Point;

import com.neurotechnology.Library.LibraryInfo;
import com.neurotechnology.Library.NativeManager;
import com.neurotechnology.Library.NativeObject;
import com.neurotechnology.NImages.NImage;
import com.neurotechnology.Templates.NEPosition;
import com.neurotechnology.Templates.NERecord;

public class NEExtractor extends NativeObject{
	
	private NEExtractionStatus extractionStatus;
	private SegmentationDetails segmentationDetails;
	
	private static final int NEEP_DEINTERLACE = 10001; // Bool
	private static final int NEEP_INNER_BOUNDARY_FROM = 10002; // Int | 16..256
	private static final int NEEP_INNER_BOUNDARY_TO = 10003; // Int | 16..256
	private static final int NEEP_OUTER_BOUNDARY_FROM = 10004; // Int | 16..256
	private static final int NEEP_OUTER_BOUNDARY_TO = 10005; // Int | 16..256
	
	public NEExtractor() throws Exception{
		this.setHandle(create());
	}
	
	public void finalize()throws Throwable{
		//free(this.getHandle());
		super.finalize();
	}
	
	
	public static LibraryInfo getInfo() throws Exception{
		LibraryInfo libinfo = new LibraryInfo();
		getInfo(libinfo);
		return libinfo;
	}
	
	public void reset(){
		reset(this.getHandle());
	}
	
	
	public NERecord extract(NImage image, NEPosition position){
		long handle = extract(this.getHandle(), image.getHandle(), position.eval(), this);
		if (handle == 0) return null;
		return new NERecord(handle);
	}
	
	/*
	@Deprecated
	public static native boolean isRegistered();
	*/
	private static native long create() throws Exception;
	private static native void free(long hExtractor);
	private static native void getInfo(LibraryInfo libinfo) throws Exception;
	private static native void reset(long handle);
	private static native long extract(long handle, long imagehandle, int position, NEExtractor extractor);
	private static native void setIntParameter(long handle, int paramID, int value);
	private static native int getIntParameter(long handle, int paramID);
	private static native void setBooleanParameter(long handle, int paramID, boolean value);
	private static native boolean getBooleanParameter(long handle, int paramID);

	public void setExtractionStatus(NEExtractionStatus extractionStatus) {
		this.extractionStatus = extractionStatus;
	}
	
	private void setExtractionStatusI(int status){
		this.extractionStatus = NEExtractionStatus.getVal(status);
	}

	public NEExtractionStatus getExtractionStatus() {
		return extractionStatus;
	}

	public void setSegmentationDetails(SegmentationDetails segmentationDetails) {
		this.segmentationDetails = segmentationDetails;
	}
	
	public void setInnerBoundaryFrom(int value){
		setIntParameter(this.getHandle(), NEEP_INNER_BOUNDARY_FROM, value);
	}
	
	public void setInnerBoundaryTo(int value){
		setIntParameter(this.getHandle(), NEEP_INNER_BOUNDARY_TO, value);
	}
	
	public int getInnerBoundaryFrom(){
		return getIntParameter(this.getHandle(), NEEP_INNER_BOUNDARY_FROM);
	}
	
	public int getInnerBoundaryTo(){
		return getIntParameter(this.getHandle(), NEEP_INNER_BOUNDARY_TO);
	}
	
	public void setOuterBoundaryFrom(int value){
		setIntParameter(this.getHandle(), NEEP_OUTER_BOUNDARY_FROM, value);
	}
	
	public int getOuterBoundaryFrom(){
		return getIntParameter(this.getHandle(), NEEP_OUTER_BOUNDARY_FROM);
	}
	
	public void setOuterBoundaryTo(int value){
		setIntParameter(this.getHandle(), NEEP_OUTER_BOUNDARY_TO, value);
	}
	
	public int getOuterBoundaryTo(){
		return getIntParameter(this.getHandle(), NEEP_OUTER_BOUNDARY_TO);
	}
	
	public void setDeinterlace(boolean value){
		setBooleanParameter(this.getHandle(), NEEP_DEINTERLACE, value);
	}
	
	public boolean isDeinterlace(){
		return getBooleanParameter(this.getHandle(), NEEP_DEINTERLACE);
	}
	
	private void setSegmentationDetails(int [] x, int[] y) {
		if (x == null){
			segmentationDetails = null;
		}else{
			segmentationDetails = new SegmentationDetails();
			segmentationDetails.setAvailable(true);
			Point [] points = new Point[x.length];
			for (int i = 0; i < x.length; i++)
				points[i] = new Point(x[i], y[i]);
			segmentationDetails.setPoints(points);
		}
	}

	public SegmentationDetails getSegmentationDetails() {
		return segmentationDetails;
	}
	
	static{
		NativeManager.loadDefault();
		NativeManager.checkLoad("NExtractors");
	}
}
