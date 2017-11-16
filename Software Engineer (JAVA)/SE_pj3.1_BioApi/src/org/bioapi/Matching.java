package org.bioapi;

import org.bioapi.data.BIR;
import org.bioapi.data.Candidates;
import org.bioapi.data.FMR;
import org.bioapi.data.IdentifyPopulation;
import org.bioapi.data.Payload;

/**
 * Represents the group of biometric operations that are available when the unit of MATCHING category is linked to the attach session.
 *
 * @author Ashwin Mohan
 */
public interface Matching
{
	/**
	 * Performs biometric identification of the existing biometric sample. This function performs an identification (1-to-many) match between a processed BIR and a set of reference BIRs. The input is the processed BIR captured specifically for this identification. The population that the match takes place against can be presented in one of two ways:
	 * <ol>
	 * <li>In a BIR database. Such population is defined via the Archive.newIdentifyPopulation(String).
	 * <li>Input in a collection of BIRs. Such population is created via the DataFactory.newIdentifyPopulation(Collection)
	 * </ol>
	 * <p>
	 * When the identify population is not explicitly specified (the 'population' parameter is null) the identify population that is set by the previous Matching.presetIdentifyPopulation() will be used.
	 *
	 * @param maxFMRrequested        the requested FMR criterion for successful identification (i.e., the matching threshold).
	 * @param processedBIR           the BIR to be identified
	 * @param totalNumberOfTemplates specifies the total number of templates stored by the application in the population. A value of zero indicates that the application is not providing a number. If the total population is distributed over several databases/partitions, then the total size of the population will be greater than the population seen by the BSP. The BSP may map the FARRequested to its internal matching threshold based on this total population size.
	 * @param population             the population of reference BIRs (templates) against which the identification is to be performed (by this BSP).
	 * @param binning                a boolean indicating whether binning is on or off. Binning is a search optimization technique that the BSP may employ. It is based on searching a subset of the population according to the intrinsic characteristics of the biometric data. While it may improve the speed of the match operation, it may also increase the probability of missing a candidate (due to the possibility of mis-binning and as a result, searching a bin which should, but does not, contain the matching BIR)
	 * @param maxResults             specifies the maximum number of match candidates to be returned as a result of	the 1:N match. A value of zero is a request for all candidates.
	 * @param timeout                an integer specifying the timeout value (in milliseconds) for the operation. If this timeout is reached an exception is thrown. This value can be any positive number. A -1 value means the BSPs default timeout value will be used.
	 * @return                       the IdentifyResult object that represents the result of the biometric operation
	 * @throws BioAPIException       if input parameters are invalid or operation fails due to an error.
	 */
	Matching.IdentifyResult identify(FMR maxFMRrequested, BIR processedBIR, int totalNumberOfTemplates, IdentifyPopulation population, boolean binning, int maxResults, int timeout) throws BioAPIException;

	/**
	 * Provides the population of BIRs to the matching unit. After this method is invoked successfully, an application can call AttachSession.identify() or Matching.identify() specifying null for the 'population' parameter. The BSP keeps this setting in effect until the presetIdentifyPopulation() is invoked with different setting or AttachSession.terminate() is called. The enrollment population to be matched against can be presented in one of two ways:
	 * <ol>
	 * <li>In a BIR database. Such population is defined via the Archive.newIdentifyPopulation(String).
	 * <li>Input in a collection of BIRs. Such population is created via the DataFactory.newIdentifyPopulation(Collection)
	 * </ol>
	 *
	 * @param population       the population of BIRs against which the identification is performed.
	 * @throws BioAPIException
	 */
	void presetIdentifyPopulation(IdentifyPopulation population) throws BioAPIException;

	/**
	 * Performs biometric verification of an existing biometric sample. This function performs a verification (1-to-1) match between two BIRs: the input BIR and the reference template. The input BIR is the processed BIR constructed specifically for this verification. The reference template was created at enrollment. The application shall request a maximum FMR value criterion (threshold) for a successful match. The boolean VerifyResult.getResult() indicates whether verification was successful or not, and the VerifyResult.getFMRAchieved() is a FMR value (score) indicating how closely the BIRs actually matched.
	 * <p>
	 * By setting the REQUEST_ADAPTED_BIR option the application can request that a BIR be constructed by adapting the reference template using the input processed BIR. If the match is successful, an attempt may be made to adapt the reference template with information taken from the input BIR. (Not all BSPs perform adaptation). The resulting adapted BIR should now be considered an optimal enrollment template (it is up to the application whether it uses or discards this data.)
	 * <p>
	 * It is important to note that adaptation may not occur in all cases.
	 * <p>
	 * If a payload is associated with the reference template, the payload may be returned upon successful verification if the achieved FMR is sufficiently stringent; this is controlled by the policy of the BSP and specified in its schema. The return of payload is requested by setting the REQUEST_PAYLOAD option.
	 *
	 * @param maxFMRrequested   the requested FMR criterion for successful verification (i.e., the matching threshold).
	 * @param processedBIR      the BIR to be verified.
	 * @param referenceTemplate the BIR to be verified against.
	 * @param options           requests additional output such as adapted BIR and/or the payload.
	 * @return                  the VerifyResult object that represents the result of the biometric operation.
	 * @throws BioAPIException  if input parameters are invalid or operation fails due to an error.
	 */
	Matching.VerifyResult verify(FMR maxFMRrequested, BIR processedBIR, BIR referenceTemplate, Matching.VerifyResult.Options options) throws BioAPIException;
	/* Nested Classes */

	/**
	 * Represents the result of biometric identification of an input BIR.
	 *
	 * @author James Kupke
	 */
	public static interface IdentifyResult
	{
		/**
		 * Returns the Candates object containing information about the BIRs identified as a result of the match process. The enumeration returned by the Candidates.getMembers() is in rank order, with the best scoring (closest matching) record being first.
		 *
		 * @return the information about the BIRs identified as a result of the match process.
		 */
		public Candidates getCandidates();
	}

	/**
	 * Represents the result of biometric verification of an input BIR.
	 *
	 * @author James Kupke
	 */
	public static interface VerifyResult
	{
		public BIR getAdaptedBIR();

		/**
		 * @return the FMR value indicating the closeness of the match (i.e., the match score).
		 */
		public int getFMRachieved();

		/**
		 * @return payload data or null if payload is not available.
		 */
		public Payload getPayload();

		/**
		 * @return true if the input processed BIR matches the reference template.
		 */
		public boolean getResult();

		/**
		 * Indicates if the adapted BIR is available. The method returns false if BIR adaptation has not been requested or BSP does not support it or if the construction of adapted BIR is not possible.
		 *
		 * @return true if an adapted BIR was constructed as a result of verification.
		 */
		public boolean hasAdaptedBIR();

		/**
		 * Indicates if the payload has been released. The method returns false if the payload has not been requested, if the reference template has not associated payload, if the BSP does not support payloads or if the payload has not been released due to the achieved FMR does not satisfy the payload policy of the BSP.
		 *
		 * @return true if the payload has been released as a result of biometric verification.
		 */
		public boolean hasPayload();

		/* Nested Enum */

		/**
		 * Defines optional output of the biometric verification.
		 */
		public enum Options
		{
			/**
			 * Requests that a BIR be constructed by adapting the reference template using the processed BIR that is the input to the biometric verification.
			 */
			REQUEST_ADAPTED_BIR,

			/**
			 * If a payload is associated with the reference template, requests that the payload should returned upon successful verification if the achieved FMR is sufficiently stringent; this is controlled by the policy of the BSP and specified in its schema.
			 */
			REQUEST_PAYLOAD;
		}
	}
}
