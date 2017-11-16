package org.bioapi.data;

import java.util.*;

/**
 * <p>
 * Represents the record in the component registry that defines properties of the BSP installed in the 
 * system. For security reasons no methods to modify the existing BSPSchema object are provided. 
 * Instead, an application should use the corresponding method of the DataFactory to create new 
 * BSPSchema containing modified data.
 * </p>
 * 
 * @author	Ashwin Mohan,Eric Goldman
 */
public interface BSPSchema
{
	public interface Operations
	{
		public enum Operation
		{
			CALIBRATESENSOR,
			CAPTURE,
			CONTROLUNIT,
			CREATETEMPLATE,
			DATABASEOPERATIONS,
			ENABLEEVENTS,
			ENROLL,
			GETINDICATORSTATUS,
			IDENTIFY,
			IDENTIFYMATCH,
			IMPORT,
			PRESETIDENTIFYPOPULATION,
			PROCESS,
			PROCESSWITHAUXBIR,
			QUERYBFPS,
			QUERYUNITS,
			SETGUICALLBACKS,
			SETINDICATORSTATUS,
			SETPOWERMODE,
			UTILITIES,
			VERIFY,
			VERIFYMATCH
		}

		public boolean supportsEnableEvents();
		public boolean supportsSetGUICallbacks();
		public boolean supportsCapture();
		public boolean supportsCreateTemplate();
		public boolean supportsProcess();
		public boolean supportsProcessWithAuxBIR();
		public boolean supportsVerifyMatch();
		public boolean supportsIdentifyMatch();
		public boolean supportsEnroll();
		public boolean supportsVerify();
		public boolean supportsIdentify();
		public boolean supportsImport();
		public boolean supportsPresetIdentifyPopulation();
		public boolean supportsDatabaseOperations();
		public boolean supportsSetPowerMode();
		public boolean supportsSetIndicatorStatus();
		public boolean supportsGetIndicatorStatus();
		public boolean supportsCalibrateSensor();
		public boolean supportsUtilities();
		public boolean supportsQueryUnits();
		public boolean supportsQueryBFPs();
		public boolean supportsControlUnit();
		public boolean supports(Operation operation);
	}

	public interface Options
	{
		public enum Option
		{
			ADAPTATION,
			APP_GUI,
			ARCHIVEBFP,
			BINNING,
			BIR_ENCRYPT,
			BIR_SIGN,
			COARSESCORES,
			MATCHINGBFP,
			MOC,
			PAYLOAD,
			PROCESSINGBFP,
			QUALITY_INTERMEDIATE,
			QUALITY_PROCESSED,
			QUALITY_RAW,
			RAW,
			SELFCONTAINEDDEVICE,
			SENSORBFP,
			SOURCEPRESENT,
			STREAMINGDATA,
			SUBTYPE_TO_CAPTURE,
			TEMPLATEUPDATE
		}

		public boolean supportsRaw();
		public boolean supportsQualityRaw();
		public boolean supportsQualityIntermediate();
		public boolean supportsQualityProcessed();
		public boolean supportsAppGUI();
		public boolean supportsStreamingData();
		public boolean supportsSourcePresent();
		public boolean supportsPayload();
		public boolean supportsBIRSign();
		public boolean supportsBIREncrypt();
		public boolean supportsTemplateUpdate();
		public boolean supportsAdaptation();
		public boolean supportsBinning();
		public boolean supportsSelfContainedDevice();
		public boolean supportsMOC();
		public boolean supportsSubtypeToCapture();
		public boolean supportsSensorBFP();
		public boolean supportsArchiveBFP();
		public boolean supportsMatchingBFP();
		public boolean supportsProcessingBFP();
		public boolean supportsCoarseScores();
		public boolean supports(Option option);
	}

	public UUID getBSPUuid();
	public String getBSPDescription();
	public String getPath();
	public String getSpecVersion();
	public String getProductVersion();
	public String getVendor();
	public BIR.Format[] getBSPSupportedFormats();
	public BIR.BiometricType getFactorsMask();
	public Operations getOperations();
	public Options getOptions();
	public FMR getPayloadPolicy();
	public long getMaxPayloadSize();
	public int getDefaultVerifyTimeout();
	public int getDefaultIdentifyTimeout();
	public int getDefaultCaptureTimeout();
	public int getDefaultEnrollTimeout();
	public int getDefaultCalibrateTimeout();
	public long getMaxBSPDbSize();
	public long getMaxIdentify();
}