package com.neurotechnology.Templates;

import com.neurotechnology.Fingers.ImpressionType;
import com.neurotechnology.Fingers.PatternClass;
import com.neurotechnology.Fingers.Position;
import com.neurotechnology.Fingers.RidgeCountsType;
import com.neurotechnology.Library.NativeManager;

public class NFRecord extends NRecord {
	
	/// <summary>The flag indicating whether ridge counts should be skipped while unpacking or packing NFRecord.</summary>
	public static final int FlagSkipRidgeCounts    = 0x00010000;
	
	/// <summary>
    /// The flag indicating whether singular points (cores, deltas
    /// and double cores) should be skipped while unpacking or
    /// packing NFRecord.
    /// </summary>                                                
	public static final int FlagSkipSingularPoints = 0x00020000;
	
	/// <summary>
	/// The flag indicating whether blocked orientations should be
    /// skipped while unpacking NFRecord.
    /// </summary>                                                
	public static final int FlagSkipBlockedOrients = 0x00040000;
	
	/// <summary>
    /// The flag indicating whether blocked orientations should be
    /// packed in NFRecord.
    /// </summary>                                                
	public static final int FlagSaveBlockedOrients = 0x00040000;
	
	// Save/SaveV1
	/// <summary>The flag indicating whether minutiae qualities should be skipped while unpacking or packing NFRecord.</summary>
	public static final int FlagSkipQualities      = 0x00100000;
	
	/// <summary>
    /// The flag indicating whether minutiae curvatures should be
    /// skipped while unpacking or packing NFRecord.
    /// </summary>                                               
	public static final int FlagSkipCurvatures     = 0x00200000;
	
	/// <summary>
    /// The flag indicating whether minutiae gs should be skipped
    /// while unpacking or packing NFRecord.
    /// </summary>                                               
	public static final int FlagSkipGs             = 0x00400000;
	
	/// <summary>
    /// Saves NFRecord using version 2 algorithm. 
    /// </summary>                                                               
	public static final int FlagSaveV2 = 0x20000000;
	
	/// <summary>
	/// Saves NFRecord using version 3 algorithm. 
	/// </summary>                                
	public static final int FlagSaveV3 = 0x30000000;
	
	/// <summary>The resolution of minutiae, cores, deltas and double cores coordinates in a NFRecord.</summary>
	public static final int Resolution = 500;

	/// <summary>
	/// The maximum number of finger dimension. 
	/// </summary>                              
	public static final int MaxFingerDimension = 2047;

	/// <summary>
	/// The maximum number of finger minutiae a NFRecord can contain.
	/// 
	/// </summary>                                                   
	public static final int MaxFingerMinutiaCount = 255;
	
	/// <summary>
	/// The maximum number of cores a NFRecord can contain. 
	/// </summary>                                          
	public static final int MaxFingerCoreCount = 15;
	
	/// <summary>
	/// The maximum number of deltas a NFRecord can contain. 
	/// </summary>                                           
	public static final int MaxFingerDeltaCount = 15;
	
	/// <summary>
	/// The maximum number of double cores a NFRecord can contain. 
	/// </summary>                                                 
	public static final int MaxFingerDoubleCoreCount = 15;
	
	static native long NFRecordCreateFromMemory(byte [] template,int size) throws Exception;
	static native void NFRecordFree(long hRecord);
	static native long NFRecordClone(long handle);
	static native byte[] NFRecordSaveToMemory(long hRecord) throws Exception;
	static native int NFRecordGetMinutiaCount(long hRecord)  throws Exception;
	static native void NFRecordGetMinutia(long hRecord, int index, Minutia minutia)  throws Exception;
	static native int NFRecordGetCoreCount(long hRecord)  throws Exception;
	static native void NFRecordGetCore(long hRecord, int index, Core core)  throws Exception;
	static native int NFRecordGetDeltaCount(long hRecord)  throws Exception;
	static native void NFRecordGetDelta(long hRecord, int index, Delta delta)  throws Exception;
	static native int NFRecordGetDoubleCoreCount(long hRecord) throws Exception;
	static native void NFRecordGetDoubleCore(long hRecord, int index, DoubleCore doubleCore)  throws Exception;
	static native void NFRecordSortMinutiae(long handle, int sortOrder);
	static native void NFRecordTruncateMinutiaeByQuality(long handle, int threshold, int maxCount);
	static native void NFRecordTruncateMinutiae(long handle, int maxCount);
	static native int NFRecordGetG(long hRecord)throws Exception;
	static native void NFRecordSetG(long handle, int value);
	static native int NFRecordGetCbeffProductType(long handle);
	static native void NFRecordSetCbeffProductType(long handle, int value);
	static native int NFRecordGetWidth(long handle);
	static native int NFRecordGetHeight(long handle);
	static native int NFRecordGetHorzResolution(long handle);
	static native int NFRecordGetVertResolution(long handle);
	static native int NFRecordGetImpressionType(long handle);
	static native void NFRecordSetImpressionType(long handle, int impressionType);
	static native int NFRecordGetPatternClass(long handle);
	static native void NFRecordSetPatternClass(long handle, int value);
	static native int NFRecordGetPosition(long handle);
	static native void NFRecordSetPosition(long handle, int value);
	static native int NFRecordGetQuality(long handle);
	static native void NFRecordSetQuality(long handle, int value);
	static native int NFRecordGetRidgeCountsType(long handle);
	static native void NFRecordSetRidgeCountsType(long handle, int value);

	//for internal use only
	public NFRecord(long hNFRecord){
		this.setHandle(hNFRecord);
	}
	
	public void finalize()throws Throwable{
		free();
		super.finalize();
	}
	
	private void free(){
		//NFRecordFree(this.getHandle());
	}
	
	public Object clone() {
		long clonedHandle = NFRecordClone(getHandle());
		return new NFRecord(clonedHandle);
	}
	
	public void sortMinutiae(MinutiaOrder order) {
		int orderValue = order.eval();
		NFRecordSortMinutiae(getHandle(), orderValue);
	}
	
	public void truncateMinutiae(int maxCount) {
		NFRecordTruncateMinutiae(getHandle(), maxCount);
	}
	
	public void truncateMinutiaeByQuality(int threshold, int maxCount) {
		NFRecordTruncateMinutiaeByQuality(getHandle(), threshold, maxCount);
	}
	
	public byte[] save() throws Exception {
		return NFRecordSaveToMemory(this.getHandle());
	}
	
	public NFRecord(byte[] template) throws Exception{
		this.setHandle(NFRecordCreateFromMemory(template, template.length));
	}
	
	public Minutia[] getMinutias()throws Exception{
		if (NFRecordGetMinutiaCount(this.getHandle()) == 0)  return null;
		Minutia[] minutias = new Minutia[NFRecordGetMinutiaCount(this.getHandle())];
		for (int i = 0; i < minutias.length; i++){
			minutias[i] = new Minutia(); 
			NFRecordGetMinutia(this.getHandle(), i, minutias[i]);
		}
		return minutias;	 
	}
	
	public Core[] getCores()throws Exception{
		if (NFRecordGetCoreCount(this.getHandle()) == 0)  return null;
		Core[] cores = new Core[NFRecordGetCoreCount(this.getHandle())];
		for (int i = 0; i < cores.length; i++){
			cores[i] = new Core(); 
			NFRecordGetCore(this.getHandle(), i, cores[i]);
		}
		return cores;
	}
	
	public Delta[] getDeltas()throws Exception{
		if (NFRecordGetDeltaCount(this.getHandle()) == 0)  return null;
		Delta[] Deltas = new Delta[NFRecordGetDeltaCount(this.getHandle())];
		for (int i = 0; i < Deltas.length; i++){
			Deltas[i] = new Delta(); 
			NFRecordGetDelta(this.getHandle(), i, Deltas[i]);
		}
		return Deltas;
	}
	
	public DoubleCore[] getDoubleCores()throws Exception{
		if (NFRecordGetDoubleCoreCount(this.getHandle()) == 0)  return null;
		DoubleCore[] DoubleCores = new DoubleCore[NFRecordGetDoubleCoreCount(this.getHandle())];
		for (int i = 0; i < DoubleCores.length; i++){
			DoubleCores[i] = new DoubleCore(); 
			NFRecordGetDoubleCore(this.getHandle(), i, DoubleCores[i]);
		}
		return DoubleCores;
	}
	
	public int getG()throws Exception{
		return NFRecordGetG(this.getHandle());
	}
	
	public void setG(int g) {
		NFRecordSetG(this.getHandle(), g);
	}
	
	public int getCbeffProductType() {
		return NFRecordGetCbeffProductType(getHandle());
	}
	
	public void setCbeffProductType(int value) {
		NFRecordSetCbeffProductType(getHandle(), value);
	}
	
	public int getWidth() {
		return NFRecordGetWidth(getHandle());
	}
	
	public int getHeight() {
		return NFRecordGetHeight(getHandle());
	}
	
	public int getHorzResolution() throws Exception {
		return NFRecordGetHorzResolution(getHandle());
	}
	
	public int getVertResolution() throws Exception {
		return NFRecordGetVertResolution(getHandle());
	}
	
	public ImpressionType getImpressionType() throws Exception {
		int impressionValue = NFRecordGetImpressionType(getHandle());
		return ImpressionType.parse(impressionValue);
	}
	
	public void setImpressionType(ImpressionType value) throws Exception {
		int impressionType = value.eval();
		NFRecordSetImpressionType(getHandle(), impressionType);
	}
	
	public PatternClass getPatternClass() throws Exception {
		int patternClassValue = NFRecordGetPatternClass(getHandle());
		return PatternClass.parse(patternClassValue);
	}
	
	public void setPatternClass(PatternClass value) throws Exception {
		int patternClass = value.eval();
		NFRecordSetPatternClass(getHandle(), patternClass);
	}
	
	public Position getPosition() throws Exception {
		int position = NFRecordGetPosition(getHandle());
		return Position.parse(position);
	}
	
	public void setPosition(Position pos) throws Exception {
		int positionValue = pos.eval();
		NFRecordSetPosition(getHandle(), positionValue);
	}
	
	public int getQuality()throws Exception{
		return NFRecordGetQuality(getHandle());
	}
	
	public void setQuality(int quality) throws Exception {
		NFRecordSetQuality(getHandle(), quality);
	}
	
	public RidgeCountsType getRidgeCountsType() throws Exception {
		int ridgeCounts = NFRecordGetRidgeCountsType(getHandle());
		return RidgeCountsType.parse(ridgeCounts);
	}
	
	public void setRidgeCountsType(RidgeCountsType value) {
		int ridgeCounts = value.eval();
		NFRecordSetRidgeCountsType(getHandle(), ridgeCounts);
	}
	
	public int getMinutiaCount() throws Exception{
		return NFRecordGetMinutiaCount(this.getHandle());
	}
	
	public Minutia getMinutia(int index) throws Exception{
		Minutia minutia = new Minutia();
		NFRecordGetMinutia(this.getHandle(), index, minutia);
		return minutia;
	}	
	public int getCoreCount() throws Exception {
		return NFRecordGetCoreCount(this.getHandle());
	}
	public Core getCore(int index) throws Exception {
		Core core = new Core();
		NFRecordGetCore(this.getHandle(), index, core);
		return core;
	}
	public int getDeltaCount() throws Exception{
		return NFRecordGetDeltaCount(this.getHandle());
	}
	public Delta getDelta(int index) throws Exception{
		Delta delta = new Delta();
		NFRecordGetDelta(this.getHandle(), index, delta);
		return delta;
	}
	public int getDoubleCoreCount() throws Exception{
		return NFRecordGetDoubleCoreCount(this.getHandle());
	}
	public DoubleCore getDoubleCore(int index) throws Exception{
		DoubleCore doubleCore = new DoubleCore();
		NFRecordGetDoubleCore(this.getHandle(), index, doubleCore);
		return doubleCore;
	}
	
	private static native long getPBuffer(long hNRecord, NRecord rec);  
	public long getPBuffer(){
		return getPBuffer(getHandle(), this);
	}
	
	static {
    	NativeManager.loadDefault();
    	NativeManager.checkLoad("NTemplates");
	}
}