/*
 * BSP_Schema.java
 *
 * Created on April 3, 2007, 9:45 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.bioapi.template;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.UUID;

import org.bioapi.AttachSession;
import org.bioapi.BSP;
import org.bioapi.BioAPIException;
import org.bioapi.Query;
import org.bioapi.BSP.UnitSet;
import org.bioapi.Unit.Category;
import org.bioapi.data.BFPSchema;
import org.bioapi.data.BIR;
import org.bioapi.data.BSPSchema;
import org.bioapi.data.FMR;
import org.bioapi.data.UnitSchema;

/**
 *
 * @author Ashwin Mohan
 */
public class BSP_Schema implements BSP, BSPSchema, UnitSet
{
	private final static Hashtable<Integer, UnitSchema> unitTable = new Hashtable<Integer, UnitSchema>();
	
	private String BSPDescription;
	private BIR_Implemented.Format[] BSPSupportedFormats;
	private UUID BSPUuid;
	private int DefaultCalibrateTimeout;
	private int DefaultCaptureTimeout;
	private int DefaultEnrollTimeout;
	private int DefaultIdentifyTimeout;
	private int DefaultVerifyTimeout;
	private BIR_Implemented.BiometricType FactorsMask;
	private long MaxBSPDbSize;
	private long MaxIdentify;
	private long MaxPayloadSize;
	private BSP_Schema.Operations Operations;
	private BSP_Schema.Options Options;
	private String Path;
	private FMR PayloadPolicy;
	private String ProductVersion;
	private String SpecVersion;
	private String Vendor;

        //RAH: needs to be an attachsession here
        
	public class Schema_Operations implements BSPSchema.Operations
	{
		private ArrayList<BSPSchema.Operations.Operation> ops;

		public Schema_Operations(BSPSchema.Operations.Operation[] _ops)
		{
			ops = new ArrayList<BSPSchema.Operations.Operation>();
			for(int i=0; i<_ops.length; i++)
			{
				ops.add(_ops[i]);
			}
		}

		public BSPSchema.Operations.Operation[] getOps()
		{
			BSPSchema.Operations.Operation[] ret = new BSPSchema.Operations.Operation[ops.size()];
			for(int i=0; i< ops.size(); i++)
			{
				ret[i]=(BSPSchema.Operations.Operation)ops.get(i);
			}

			return ret;
		}

		public boolean supportsEnableEvents(){return ops.contains(BSPSchema.Operations.Operation.ENABLEEVENTS);}
		public boolean supportsSetGUICallbacks(){return false;}
		public boolean supportsCapture(){return ops.contains(BSPSchema.Operations.Operation.CAPTURE);}
		public boolean supportsCreateTemplate(){return ops.contains( BSPSchema.Operations.Operation.CREATETEMPLATE);}
		public boolean supportsProcess(){return ops.contains( BSPSchema.Operations.Operation.PROCESS);}
		public boolean supportsProcessWithAuxBIR(){return ops.contains( BSPSchema.Operations.Operation.PROCESSWITHAUXBIR);}
		public boolean supportsVerifyMatch(){return ops.contains( BSPSchema.Operations.Operation.VERIFYMATCH);}
		public boolean supportsIdentifyMatch(){return ops.contains( BSPSchema.Operations.Operation.IDENTIFYMATCH);}
		public boolean supportsEnroll(){return ops.contains(BSPSchema.Operations.Operation.ENROLL);}
		public boolean supportsVerify(){return ops.contains( BSPSchema.Operations.Operation.VERIFY);}
		public boolean supportsIdentify(){return ops.contains( BSPSchema.Operations.Operation.IDENTIFY);}
		public boolean supportsImport(){return ops.contains( BSPSchema.Operations.Operation.IMPORT);}
		public boolean supportsPresetIdentifyPopulation(){return ops.contains( BSPSchema.Operations.Operation.PRESETIDENTIFYPOPULATION);}
		public boolean supportsDatabaseOperations(){return ops.contains( BSPSchema.Operations.Operation.DATABASEOPERATIONS);}
		public boolean supportsSetPowerMode(){return ops.contains( BSPSchema.Operations.Operation.SETPOWERMODE);}
		public boolean supportsSetIndicatorStatus(){return ops.contains( BSPSchema.Operations.Operation.SETINDICATORSTATUS);}
		public boolean supportsGetIndicatorStatus(){return ops.contains( BSPSchema.Operations.Operation.GETINDICATORSTATUS);}
		public boolean supportsCalibrateSensor(){return ops.contains( BSPSchema.Operations.Operation.CALIBRATESENSOR);}
		public boolean supportsUtilities(){return ops.contains( BSPSchema.Operations.Operation.UTILITIES);}
		public boolean supportsQueryUnits(){return ops.contains( BSPSchema.Operations.Operation.QUERYUNITS);}
		public boolean supportsQueryBFPs(){return ops.contains( BSPSchema.Operations.Operation.QUERYBFPS);}
		public boolean supportsControlUnit(){return ops.contains( BSPSchema.Operations.Operation.CONTROLUNIT);}
		public boolean supports(Operation operation){return ops.contains( operation);}
	}

	public class Schema_Options implements BSPSchema.Options
	{
		private ArrayList<BSPSchema.Options.Option> ops;

		public Schema_Options(BSPSchema.Options.Option[] _ops)
		{
			ops = new ArrayList<BSPSchema.Options.Option>();
			for(int i=0; i<_ops.length; i++)
			{
				ops.add(_ops[i]);
			}
		}

		public Schema_Options()
		{
			//TODO: supports none? 
					ops = new ArrayList<BSPSchema.Options.Option>();
		}

		public boolean supportsRaw(){ return ops.contains(BSPSchema.Options.Option.RAW);}
		public boolean supportsQualityRaw(){return ops.contains(BSPSchema.Options.Option.QUALITY_RAW);}
		public boolean supportsQualityIntermediate(){return ops.contains(BSPSchema.Options.Option.QUALITY_INTERMEDIATE);}
		public boolean supportsQualityProcessed(){return ops.contains(BSPSchema.Options.Option.QUALITY_PROCESSED);}
		public boolean supportsAppGUI(){return ops.contains(BSPSchema.Options.Option.APP_GUI);}
		public boolean supportsStreamingData(){return ops.contains(BSPSchema.Options.Option.STREAMINGDATA);}
		public boolean supportsSourcePresent(){return ops.contains(BSPSchema.Options.Option.SOURCEPRESENT);}
		public boolean supportsPayload(){return ops.contains(BSPSchema.Options.Option.PAYLOAD);}
		public boolean supportsBIRSign(){return ops.contains(BSPSchema.Options.Option.BIR_SIGN);}
		public boolean supportsBIREncrypt(){return ops.contains(BSPSchema.Options.Option.BIR_ENCRYPT);}
		public boolean supportsTemplateUpdate(){return ops.contains(BSPSchema.Options.Option.TEMPLATEUPDATE);}
		public boolean supportsAdaptation(){return ops.contains(BSPSchema.Options.Option.ADAPTATION);}
		public boolean supportsBinning(){return ops.contains(BSPSchema.Options.Option.BINNING);}
		public boolean supportsSelfContainedDevice(){return ops.contains(BSPSchema.Options.Option.SELFCONTAINEDDEVICE);}
		public boolean supportsMOC(){return ops.contains(BSPSchema.Options.Option.MOC);}
		public boolean supportsSubtypeToCapture(){return ops.contains(BSPSchema.Options.Option.SUBTYPE_TO_CAPTURE);}
		public boolean supportsSensorBFP(){return ops.contains(BSPSchema.Options.Option.SENSORBFP);}
		public boolean supportsArchiveBFP(){return ops.contains(BSPSchema.Options.Option.ARCHIVEBFP);}
		public boolean supportsMatchingBFP(){return ops.contains(BSPSchema.Options.Option.MATCHINGBFP);}
		public boolean supportsProcessingBFP(){return ops.contains(BSPSchema.Options.Option.PROCESSINGBFP);}
		public boolean supportsCoarseScores(){return ops.contains(BSPSchema.Options.Option.COARSESCORES);}
		public boolean supports(BSPSchema.Options.Option option){return ops.contains(option);}

		public BSPSchema.Options.Option[] getOps()
		{
			BSPSchema.Options.Option[] ret = new BSPSchema.Options.Option[ops.size()];
			for(int i=0; i< ops.size(); i++)
			{
				ret[i]=(BSPSchema.Options.Option)ops.get(i);
			}

			return ret;
		}
	}

	/** Creates a new instance of BSP_Schema */
	public BSP_Schema(String _BSPDescription,
			BIR_Implemented.Format[] _BSPSupportedFormats,
			UUID _BSPUuid,
			int _DefaultCalibrateTimeout,
			int _DefaultCaptureTimeout,
			int _DefaultEnrollTimeout,
			int _DefaultIdentifyTimeout,
			int _DefaultVerifyTimeout,
			BIR.BiometricType.Type[] _FactorsMask,
			long _MaxBSPDbSize,
			long _MaxIdentify,
			long _MaxPayloadSize,
			BSPSchema.Operations.Operation[] _Operations,
			BSPSchema.Options.Option[] _Options,
			String _Path,
			FMR _PayloadPolicy,
			String _ProductVersion,
			String _SpecVersion,
			String _Vendor) {

		BSPDescription=_BSPDescription;

		//TODO: write BIR.Format
		BSPSupportedFormats = new BIR_Implemented.Format[_BSPSupportedFormats.length];
		for(int i=0; i<_BSPSupportedFormats.length; i++)
		{
			BSPSupportedFormats[i] = new BIR_Implemented.Format(_BSPSupportedFormats[i]);
		}

		BSPUuid= _BSPUuid;
		DefaultCalibrateTimeout= _DefaultCalibrateTimeout;
		DefaultCaptureTimeout= _DefaultCaptureTimeout;
		DefaultEnrollTimeout= _DefaultEnrollTimeout;
		DefaultIdentifyTimeout= _DefaultIdentifyTimeout;
		DefaultVerifyTimeout= _DefaultVerifyTimeout;

		FactorsMask= new BIR_Implemented.BiometricType(_FactorsMask);
		MaxBSPDbSize=_MaxBSPDbSize;
		MaxIdentify=_MaxIdentify;
		MaxPayloadSize=_MaxPayloadSize;

		Operations= new BSP_Schema.Schema_Operations(_Operations);

		Options= new BSP_Schema.Schema_Options(_Options);
		Path= _Path;

		PayloadPolicy= _PayloadPolicy;
		ProductVersion= _ProductVersion;
		SpecVersion= _SpecVersion;
		Vendor=  _Vendor;
	}

	public BSP_Schema(BSP_Schema _bsp)
	{

	}

	public String getBSPDescription() {
		return BSPDescription;
	}

	public void setBSPDescription(String BSPDescription) {
		this.BSPDescription = BSPDescription;
	}

	public BIR.Format[] getBSPSupportedFormats() {
		return BSPSupportedFormats;
	}

	public void setBSPSupportedFormats(BIR_Implemented.Format[] BSPSupportedFormats) {
		this.BSPSupportedFormats = BSPSupportedFormats;
	}

	public UUID getBSPUuid() {
		return BSPUuid;
	}

	public void setBSPUuid(UUID BSPUuid) {
		this.BSPUuid = BSPUuid;
	}

	public int getDefaultCalibrateTimeout() {
		return DefaultCalibrateTimeout;
	}

	public void setDefaultCalibrateTimeout(int DefaultCalibrateTimeout) {
		this.DefaultCalibrateTimeout = DefaultCalibrateTimeout;
	}

	public int getDefaultCaptureTimeout() {
		return DefaultCaptureTimeout;
	}

	public void setDefaultCaptureTimeout(int DefaultCaptureTimeout) {
		this.DefaultCaptureTimeout = DefaultCaptureTimeout;
	}

	public int getDefaultEnrollTimeout() {
		return DefaultEnrollTimeout;
	}

	public void setDefaultEnrollTimeout(int DefaultEnrollTimeout) {
		this.DefaultEnrollTimeout = DefaultEnrollTimeout;
	}

	public int getDefaultIdentifyTimeout() {
		return DefaultIdentifyTimeout;
	}

	public void setDefaultIdentifyTimeout(int DefaultIdentifyTimeout) {
		this.DefaultIdentifyTimeout = DefaultIdentifyTimeout;
	}

	public int getDefaultVerifyTimeout() {
		return DefaultVerifyTimeout;
	}

	public void setDefaultVerifyTimeout(int DefaultVerifyTimeout) {
		this.DefaultVerifyTimeout = DefaultVerifyTimeout;
	}

	public BIR.BiometricType getFactorsMask() {
		return FactorsMask;
	}

	public void setFactorsMask(BIR_Implemented.BiometricType FactorsMask) {
		this.FactorsMask = FactorsMask;
	}

	public long getMaxBSPDbSize() {
		return MaxBSPDbSize;
	}

	public void setMaxBSPDbSize(long MaxBSPDbSize) {
		this.MaxBSPDbSize = MaxBSPDbSize;
	}

	public long getMaxIdentify() {
		return MaxIdentify;
	}

	public void setMaxIdentify(long MaxIdentify) {
		this.MaxIdentify = MaxIdentify;
	}

	public long getMaxPayloadSize() {
		return MaxPayloadSize;
	}

	public void setMaxPayloadSize(long MaxPayloadSize) {
		this.MaxPayloadSize = MaxPayloadSize;
	}

	public BSPSchema.Operations getOperations() {
		return Operations;
	}

	public void setOperations(BSPSchema.Operations Operations) {
		this.Operations = Operations;
	}

	public BSPSchema.Options getOptions() {
		return Options;
	}



	public String getPath() {
		return Path;
	}

	public void setPath(String Path) {
		this.Path = Path;
	}

	public FMR getPayloadPolicy() {
		return PayloadPolicy;
	}

	public void setPayloadPolicy(FMR PayloadPolicy) {
		this.PayloadPolicy = PayloadPolicy;
	}

	public String getProductVersion() {
		return ProductVersion;
	}

	public void setProductVersion(String ProductVersion) {
		this.ProductVersion = ProductVersion;
	}

	public String getSpecVersion() {
		return SpecVersion;
	}

	public void setSpecVersion(String SpecVersion) {
		this.SpecVersion = SpecVersion;
	}

	public String getVendor() {
		return Vendor;
	}

	public void setVendor(String Vendor) {
		this.Vendor = Vendor;
	}

	public Enumeration<BFPSchema> enumBFPs() throws BioAPIException
	{
		return ComponentRegistry_Implemented.bfpTable.elements();
	}

	public Enumeration<BFPSchema> enumBFPs(Query<BFPSchema> query) throws BioAPIException
	{
		return ComponentRegistry_Implemented.bfpTable.elements();
	}

	public Enumeration<UnitSchema> enumUnits() throws BioAPIException
	{
		return unitTable.elements();
	}

	public Enumeration<UnitSchema> enumUnits(Query<UnitSchema> query) throws BioAPIException
	{
		return unitTable.elements();
	}

	public AttachSession newAttachSession() throws BioAPIException
	{
		// TODO Auto-generated method stub
		return null;
	}

	public AttachSession newAttachSession(UnitSet units) throws BioAPIException
	{
		// TODO Auto-generated method stub
		return null;
	}

	public UnitSet newUnitSet()
	{
		// TODO Auto-generated method stub
		return null;
	}

	public void terminate()
	{
		// TODO Auto-generated method stub
	}

	public void addUnit(int unitID, Category unitCategory) throws BioAPIException
	{
		// TODO Auto-generated method stub
	}

	public void addUnit(UnitSchema unitSchema) throws BioAPIException
	{
		unitTable.put(unitSchema.getUnitId(), unitSchema);
	}

	public boolean contains(Category unitCategory) throws BioAPIException
	{
		// TODO Auto-generated method stub
		return false;
	}

	public void exclude(Category unitCategory) throws BioAPIException
	{
		// TODO Auto-generated method stub
	}

	public int getUnitID(Category unitCategory) throws BioAPIException
	{
		// TODO Auto-generated method stub
		return 0;
	}
}
