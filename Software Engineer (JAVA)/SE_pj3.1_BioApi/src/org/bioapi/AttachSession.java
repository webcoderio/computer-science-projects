package org.bioapi;

import org.bioapi.Archive;
import org.bioapi.BioAPIException;
import org.bioapi.GUIImageObserver;
import org.bioapi.GUIStateObserver;
import org.bioapi.Matching;
import org.bioapi.Processing;
import org.bioapi.Sensor;
import org.bioapi.Unit;
import org.bioapi.data.BIR;
import org.bioapi.data.Event;
import org.bioapi.data.FMR;
import org.bioapi.data.IdentifyPopulation;
import org.bioapi.data.Payload;

/**
 * Represents the attached BioAPI BSP session. Depending on unit categories that were specified when the Session object was created it exposes groups of available biometric operations via Archive, Processing, Matching and Sensor interfaces. The Session object also provide tool fir control of generic unit via the Unit interface
 *  
 * @author Ashwin Mohan,James Kupke
 */
public interface AttachSession
{
	/**
	 * Cancels any presently blocked calls associated with the attach session. The method shall not return until all blocking calls have been cancelled.
	 * 
	 * @throws BioAPIException
	 */
	public void cancel() throws BioAPIException;
	
	/**
	 * Enables the events specified by the 'eventKinds' coming from all the BioAPI Units selected in the BSP attach session, and disables all other events from those BioAPI Units. Events from other BioAPI Units directly or indirectly managed by the same BSP (possibly selected in other attach sessions but not selected in the specified attach session) are not affected. In some cases, INSERT events cannot be suppressed.
	 * 
	 * @param eventKinds specifies the list of events to be enabled
	 * @throws BioAPIException
	 */
	public void enableEvents(Event.Kind[] eventKinds) throws BioAPIException;
	
	/**
	 * Captures biometric data from the attached device (sensor unit) for the purpose of creating a processed BIR for the purpose of ENROLL, ENROLL_FOR_VERIFICATION_ONLY, or ENROLL_FOR_IDENTIFICATION ONLY (i.e., a reference template). The optional input reference template is provided for use in creating the new template, if the BSP supports this capability. When present, use of the input reference template by the BSP, to create the output template is optional. If the BSP supports an internal or BSP-controlled BIR database (e.g., smartcard or identification engine), it may optionally return the UUID assigned to the newly created template as stored within that BSP controlled BIR database. The UUID value shall be the same as that included in the BIR header, if present.
	 * <p>
	 * The BSP is responsible for providing the user interface associated with the enrollment operation as a default. The application may request control of the GUI "look-and-feel" by providing a GUI callback vis the Session.setGUIObservers() method.
	 * <p>
	 * Since the enroll() operation includes a capture, it serializes use of the sensor device. If two or more applications are racing for the device, the losers will wait until the operation completes or the timeout expires. This serialization takes place in all functions that capture data. The BSP is responsible for serializing. It may do this by either throwning an exception to indicate that device is busy or by queuing requests.
	 * 
	 * @param purpose           indicates the desired purpose (ENROLL, ENROLL_FOR_VERIFICATION_ONLY, or ENROLL_FOR_IDENTIFICATION ONLY).
	 * @param subtype           specifies which subtype (e.g., left/right eye) to capture. null indicates that the value is not provided
	 * @param outputFormat      specifies which BDB format to use for the returned processed BIR, if the BSP supports more than one format. null indicates that the BSP is to select the format.
	 * @param referenceTemplate optionally, the existing template to be updated
	 * @param payload           a payload that will be stored by the BSP
	 * @param timeout           an integer specifying the timeout value (in milliseconds) for the operation. If this timeout is reached an exception is thrown. This value can be any positive number. A -1 value means the BSPs default timeout value will be used
	 * @param options           requests additional output such as audit data
	 * @return                  the EnrollResult object that represents the result of capture operation.
	 * @throws BioAPIException  if the sensor device is busy or operation has failed due to another error.
	 */
	public AttachSession.EnrollResult enroll(BIR.Purpose purpose, BIR.Subtype subtype, BIR.Format outputFormat, BIR referenceTemplate, Payload payload, int timeout, AttachSession.EnrollResult.Options options) throws BioAPIException;
	
	/**
	 * Provides the interface that gives access to the group of biometric operations that require the availability of an archive unit
	 * 
	 * @return
	 * @throws BioAPIException
	 */
	public Archive getArchive() throws BioAPIException;

	/**
	 * Provides the interface that gives access to the group of biometric operations that require the availability of a matching unit.
	 * 
	 * @return
	 * @throws BioAPIException
	 */
	public Matching getMatching() throws BioAPIException;
	
	/**
	 * Provides the interface that gives access to the group of biometric operations that require the availability of a processing unit.
	 * 
	 * @return
	 * @throws BioAPIException
	 */
	public Processing getProcessing() throws BioAPIException;
	
	/**
	 * Provides the interface that gives access to the group of biometric operations that require the availability of a sensor unit.
	 * @return
	 * @throws BioAPIException
	 */
	public Sensor getSensor() throws BioAPIException;
	
	/**
	 * Provides the interface that the application can use to control generic unit of any category.
	 * 
	 * @param  unitID identifies the target unit
	 * @return the Unit object that provides API to control the generic unit
	 * @throws BioAPIException
	 */
	public Unit getUnit(int unitID) throws BioAPIException;
	
	/**
	 * Captures biometric data from the attached device (sensor unit), and compares it against a set of reference BIRs (the Population). The population that the match takes place against can be presented in one of two ways:
	 * <ol>
	 * <li>In a BIR database. Such population is defined via the Archive.newIdentifyPopulation(String).
	 * <li>Input in a collection of BIRs. Such population is created via the DataFactory.newIdentifyPopulation(Collection).
	 * </ol>
	 * <p>
	 * When the identify population is not explicitly specified (the 'population' parameter is null) the identify population that is set by the previous Session.presetIdentifyPopulation() will be used.
	 * <p>
	 * The BSP is responsible for providing the user interface associated with the identify operation as a default. The application may request control of the GUI "look-and-feel" by providing a GUI callback vis the Session.setGUIObservers() method.
	 * <p>
	 * Since the identify() operation includes a capture, it serializes use of the sensor device. If two or more applications are racing for the device, the losers will wait until the operation completes or the timeout expires. This serialization takes place in all functions that capture data. The BSP is responsible for serializing. It may do this by either throwning an exception to indicate that device is busy or by queuing requests.
	 * 
	 * @param maxFMRrequested        the requested FMR criterion for successful identification (i.e., the matching threshold)
	 * @param subtype                specifies which subtype (e.g., left/right eye) to capture. null indicates that the value is not provided.
	 * @param totalNumberOfTemplates specifies the total number of templates stored by the application in the population. A value of zero indicates that the application is not providing a number. If the total population is distributed over several databases/partitions, then the total size of the population will be greater than the population seen by the BSP. The BSP may map the FARRequested to its internal matching threshold based on this total population size.
	 * @param population             the population of reference BIRs (templates) against which the identification is to be performed (by this BSP)
	 * @param binning                a boolean indicating whether binning is on or off. Binning is a search optimization technique that the BSP may employ. It is based on searching a subset of the population according to the intrinsic characteristics of the biometric data. While it may improve the speed of the match operation, it may also increase the probability of missing a candidate (due to the possibility of mis-binning and as a result, searching a bin which should, but does not, contain the matching BIR).
	 * @param maxResults             specifies the maximum number of match candidates to be returned as a result of the 1:N match. A value of zero is a request for all candidates.
	 * @param timeout                an integer specifying the timeout value (in milliseconds) for the operation. If this timeout is reached an exception is thrown. This value can be any positive number. A -1 value means the BSPs default timeout value will be used.
	 * @param options                requests additional output such as audit data
	 * @return                       the IdentifyResult object that represents the result of the biometric operation
	 * @throws BioAPIException       if the sensor device is busy or operation has failed due to another error
	 */
	public AttachSession.IdentifyResult identify(FMR maxFMRrequested, BIR.Subtype subtype, int totalNumberOfTemplates, IdentifyPopulation population, boolean binning, int maxResults, int timeout, AttachSession.IdentifyResult.Options options) throws BioAPIException;
	
	/**
	 * Passes raw biometric data obtained by a biometric application by any means, and requests the specified BSP attach session to construct a BIR for the purpose specified. Input 'data' identifies the memory buffer containing the raw biometric data, while 'inputFormat' identifies the form of the raw biometric data. The input formats that a particular BSP will be prepared to accept are determined by the BSP.
	 * 
	 * @param data         a data to import into a BIR. The format of the data conforms to the standard identified by the 'inputFormat'
	 * @param inputFormat  the format of the input data
	 * @param outputFormat specifies which BDB format to use for the returned BIR, if the BSP supports more than one format. null indicates that the BSP is to select the format
	 * @param purpose      indicates the purpose of the constructed BIR.
	 * @return             BIR constructed from raw data
	 * @throws BioAPIException
	 */
	public BIR importBIR(byte data, BIR.Format inputFormat, BIR.Format outputFormat, BIR.Purpose purpose) throws BioAPIException;
	
	/**
	 * Allows the application to establish callbacks so that the application may control the "look-andfeel" of the biometric user interface by receiving from the BSP a sequence of bit-map images, called streaming data, for display by the biometric application as well as state information. NOTE: Not all BSPs support the provision of streaming data.
	 * 
	 * @param imageChangedObserver a pointer to an application callback to deal with the presentation of biometric streaming data
	 * @param stateChangedObserver a pointer to an application callback to deal with GUI state changes.
	 * @throws BioAPIException
	 */
	public void setGUIObservers(GUIImageObserver imageChangedObserver, GUIStateObserver stateChangedObserver) throws BioAPIException;
	
	/**
	 * Destroys the Session object and releases external resources associated with it. After this method is called the object is no longer useable and an attemp to invoke any method of the Session results in that an exception is thrown.
	 * 
	 * @throws BioAPIException  if termination of Session fails due to an error or if the BSP has been terminated already.
	 */
	public void terminate() throws BioAPIException;
	
	/**
	 * Captures biometric data from the attached device (sensor unit), and compares it against the reference template.
	 * <p>
	 * The application shall request a maximum FMR value criterion (threshold) for a successful match. The boolean VerifyResult.getResult() indicates whether verification was successful or not, and the VerifyResult.getFMRAchieved() is a FMR value (score) indicating how closely the BIRs actually matched.
	 * <p>
	 * By setting the REQUEST_ADAPTED_BIR option the application can request that a BIR be constructed by adapting the reference template using the input processed BIR. If the match is successful, an attempt may be made to adapt the reference template with information taken from the input BIR. (Not all BSPs perform adaptation). The resulting adapted BIR should now be considered an optimal enrollment template (it is up to the application whether it uses or discards this data.)
	 * <p>
	 * It is important to note that adaptation may not occur in all cases.
	 * <p>
	 * If a payload is associated with the reference template, the payload may be returned upon successful verification if the achieved FMR is sufficiently stringent; this is controlled by the policy of the BSP and specified in its schema. The return of payload is requested by setting the REQUEST_PAYLOAD option.
	 * <p>
	 * The BSP is responsible for providing the user interface associated with the verify operation as a default. The application may request control of the GUI "look-and-feel" by providing a GUI callback vis the Session.setGUIObservers() method.
	 * <p>
	 * Since the verify() operation includes a capture, it serializes use of the sensor device. If two or more applications are racing for the device, the losers will wait until the operation completes or the timeout expires. This serialization takes place in all functions that capture data. The BSP is responsible for serializing. It may do this by either throwning an exception to indicate that device is busy or by queuing requests
	 * 
	 * @param maxFMRrequested   the requested FMR criterion for successful verification (i.e., the matching threshold).
	 * @param referenceTemplate the BIR to be verified against
	 * @param subtype           specifies which subtype (e.g., left/right eye) to capture. null indicates that the value is not provided.
	 * @param timeout
	 * @param options           requests additional output such as audit data, adapted BIR and/or the payload.
	 * @return                  the VerifyResult object that represents the result of the biometric operation.
	 * @throws BioAPIException  if the sensor device is busy or operation has failed due to another error.
	 */
	public AttachSession.VerifyResult verify(FMR maxFMRrequested, BIR referenceTemplate, BIR.Subtype subtype, int timeout, AttachSession.VerifyResult.Options options) throws BioAPIException;
	
	/* Nested Classes */
	
	public interface EnrollResult extends Processing.CreateTemplateResult
	{
		/**
		 * Indicates if audit data has been collected. The method returns false if the collection of audit data has not been requested of if the BSP does not support collection of audit data.
		 * 
		 * @return true if audit data has been collected during the operation.
		 */
		public BIR getAuditData();
		
		/**
		 * @return BIR object containing raw audit data or null if audit data is not available
		 */
		public boolean hasAuditData();
		
		/* Nested Enum */
		
		/**
		 * Defines optional output of enrollment the biometric template.
		 */
		public enum Options
		{
			/**
			 * Requests that BSP should collect audit data. This data may be used to provide human-identifiable data of the person at the sensor unit. Not all BSPs support the collection of audit data.
			 */
			REQUEST_AUDIT_DATA;
		}
	}
	
	public interface IdentifyResult extends Matching.IdentifyResult
	{
		/**
		 * Indicates if audit data has been collected. The method returns false if the collection of audit data has not been requested of if the BSP does not support collection of audit data.
		 * 
		 * @return true if audit data has been collected during the operation.
		 */
		public BIR getAuditData();
		
		/**
		 * @return BIR object containing raw audit data or null if audit data is not available
		 */
		public boolean hasAuditData();
		
		/* Nested Enum */
		
		/**
		 * Defines optional output of enrollment the biometric template.
		 */
		public enum Options
		{
			/**
			 * Requests that BSP should collect audit data. This data may be used to provide human-identifiable data of the person at the sensor unit. Not all BSPs support the collection of audit data.
			 */
			REQUEST_AUDIT_DATA;
		}
	}
	
	public interface VerifyResult extends Matching.VerifyResult
	{
		/**
		 * Indicates if audit data has been collected. The method returns false if the collection of audit data has not been requested of if the BSP does not support collection of audit data.
		 * 
		 * @return true if audit data has been collected during the operation.
		 */
		public BIR getAuditData();
		
		/**
		 * @return BIR object containing raw audit data or null if audit data is not available
		 */
		public boolean hasAuditData();
		
		/* Nested Enum */
		
		/**
		 * Defines optional output of biometric verification.
		 */
		public enum Options
		{
			/**
			 * Requests that a BIR be constructed by adapting the reference template using the processed BIR that is the input to the biometric verification.
			 */
			REQUEST_ADAPTED_BIR,
			
			/**
			 * Requests that BSP should collect audit data. This data may be used to provide human-identifiable data of the person at the sensor unit. Not all BSPs support the collection of audit data.
			 */
			REQUEST_AUDIT_DATA,
			
			/**
			 * If a payload is associated with the reference template, requests that the payload should returned upon successful verification if the achieved FMR is sufficiently stringent; this is controlled by the policy of the BSP and specified in its schema.
			 */
			REQUEST_PAYLOAD;
		}
	}
}
