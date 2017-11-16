package org.bioapi.data;

import org.bioapi.BioAPIException;
import org.bioapi.Unit;

/**
 * Provides the application the implementation-neutral tool to instantiate BioAPI data types. Because some data types (like UnitProperties or Candidates) are instantiated purely by the Framework or BSP, the Datafactory does not define methods to instantiate values of such types.
 * 
 * @author Ashwin Mohan
 */
public interface DataFactory
{
	public BFPSchema newBFPProperties(java.util.UUID BFPUuid, Unit.Category BFPCategory, java.lang.String BFPDescription, java.lang.String path, java.lang.String specVersion, java.lang.String productVersion, java.lang.String vendor, BIR.Format[] BFPSupportedFormats, BIR.BiometricType factorsMask, java.util.UUID BFPPropertyID, byte BFPProperty) throws BioAPIException;
	
	public BIR newBIR(java.lang.String headerVersion, BIR.ProcessedLevel processedLevel, boolean isBDBEncrypted, boolean isBIRSigned, BIR.Format format, BIR.Quality quality, BIR.Purpose purpose, BIR.BiometricType factorsMask, BIR.ProductID productID, BIR.DTG creationDTG, BIR.Subtype subtype, Date expirationDate, BIR.SecurityBlockFormat SBFormat, java.util.UUID index, byte biometricData, byte securityBlock) throws BioAPIException;
	
	public BSPSchema newBSPProperties(java.util.UUID BSPUuid, java.lang.String BSPDescription, java.lang.String path, java.lang.String specVersion, java.lang.String productVersion, java.lang.String vendor, BIR.Format[] BSPSupportedFormats, BIR.BiometricType factorsMask, BSPSchema.Operations supportedOperations, BSPSchema.Options supportedOptions, FMR payloadPolicy, long maxPayloadSize, int defaultVerifyTimeout, int defaultIdentifyTimeout, int defaultCaptureTimeout, int defaultEnrollTimeout, int defaultCalibrateTimeout, long maxBSPDbSize, long maxIdentify) throws BioAPIException;
	
	public Date newDate(int year, int month, int mday) throws BioAPIException;
	
	public FMR newFMR(int falseMatchRate) throws BioAPIException;
	
	public IdentifyPopulation newIdentifyPopulation(java.util.Collection<BIR> members) throws BioAPIException;
	
	public Payload newPayload(byte data);
	
	public Payload newPayload(java.lang.String data);
	
	public Payload newPayload(java.util.UUID data);
	
	public Time newTime(int hour, int minute, int second) throws BioAPIException;
	
	public BIR.BiometricType newBIR_BiometricType(BIR.BiometricType.Type... types);
	
	public BIR.DTG newBIR_DTG(Date date, Time time);
	
	public BIR.Quality newBIR_Quality(int quality) throws BioAPIException;
	
	public BIR.Format newBIR_Format(int owner, int type) throws BioAPIException;
	
	public BIR.SecurityBlockFormat newBIR_SecurityBlockFormat(int owner, int type) throws BioAPIException;
	
	public BIR.ProductID newBIR_ProductID(int owner, int type) throws BioAPIException;
	
	public BIR.Subtype newBIR_Subtype(BIR.Subtype.Instance... subject);
	
	public BSPSchema.Operations newBSPProperties_Operations(BSPSchema.Operations.Operation... operations);
	
	public BSPSchema.Options newBSPProperties_Options(BSPSchema.Options.Option... options);
}
