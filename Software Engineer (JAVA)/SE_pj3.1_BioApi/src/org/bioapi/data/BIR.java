package org.bioapi.data;

import org.bioapi.*;
import java.util.*;

/**
 * <p>Represents BIR. No methods to modify the existing BIR object are provided. An application should use 
 * the corresponding method of the DataFactory to create new BIR containing modified data.</p>
 * 
 * <p>Depending on the way the BIR object was created, the BIR is either bound to the BSP or is unbound.
 * Output of biometric operation normally creates the BIR bound to the BSP. Bound BIR is the BIR 
 * encapsulated in the BSP with the lifetime limited by the lifetime of the AttachSession that created it.</p>
 * 
 * <p>The bound BIR can be extracted from a BSP:</p>
 * <ol>
 *	<li>implicitly, by calling either of {@link #getBiometricData() getBiometricData()} or {@link #getSecurityBlock() getSecurityBlock()}.</li>
 *	<li>explicitly, by calling the {@link #unbind() unbind()} method.</li>
 * </ol>
 * 
 * @author	Ashwin Mohan
 */
public interface BIR
{
	/**
	 * Describes the set of biometric types (factors) contained within a BioAPI BIR.
	 * 
	 * @status	Implemented but untested.
	 */
	static interface BiometricType
	{
		public enum Type
		{
			FACIAL_FEATURES,
			FINGERPRINT,
			GAIT,
			HAND_GEOMETRY,
			IRIS,
			KEYSTROKE_DYNAMICS,
			LIP_MOVEMENT,
			MULTIPLE,
			OTHER,
			PASSWORD,
			RETINA,
			SIGNATURE_DYNAMICS,
			THERMAL_FACE_IMAGE,
			THERMAL_HAND_IMAGE,
			VOICE
		}
		public boolean containsMultipleFactors();
		public boolean containsFacialFeatures();
		public boolean containsVoice();
		public boolean containsFingerprint();
		public boolean containsIris();
		public boolean containsRetina();
		public boolean containsHandGeometry();
		public boolean containsKeystrokeDynamics();
		public boolean containsLipMovement();
		public boolean containsThermalFaceImage();
		public boolean containsThermalHandImage();
		public boolean containsGait();
		public boolean containsOther();
		public boolean containsPassword();
		public boolean contains(Type type);
	}

	/**
	 * <p>
	 * Defines the date and time when the BIR was created.
	 * </p>
	 * 
	 * @author	Eric Goldman
	 * 
	 * @status	Implemented but untested.
	 */
	static interface DTG
	{
		public Date getDate();
		public Time getTime();
	}

	/**
	 * <p>
	 * Defines the format of the data contained within the "opaque" biometric data block (BDB) of the BioAPI
	 * BIR. 'Format Owner' values are assigned and registered by the CBEFF Registration Authority. 'Format 
	 * Type' is assigned by the Format Owner and may optionally be registered. registration information is
	 * located in ISO/IEC 19785-2. The BioAPI BIR Biometric Data Format corresponds to a combination of the
	 * "CBEFF_BDB_format_owner" and "CBEFF_BDB_format_type" in ISO/ICE 19875-1.
	 * </p>
	 * 
	 * @author	Eric Goldman
	 * 
	 * @status	Implemented but untested.
	 */
	static interface Format
		extends OwnerTypePair
	{
	}

	/**
	 * <p>
	 * Defines the identification containing of two numbers where the second number identifies the entity 
	 * (format, productID) and the first number identifies the "namespace" (the authority who has defined the 
	 * entity identification). This is the common base for format and product ID identifiers.
	 * </p>
	 * 
	 * @author	Eric Goldman
	 * 
	 * @status	Implemented but untested.
	 */
	public interface OwnerTypePair
	{
		public short getOwner();
		public short getType();
	}

	/**
	 * This data type identifies the type of biometric sample (raw, intermediate, or processed) that is contained 
	 * in the BDB.
	 * 
	 * @author	Eric Goldman
	 * 
	 * @status	Implemented but untested.
	 */
	public enum ProcessedLevel
	{
		INTERMEDIATE,
		PROCESSED,
		RAW
	}

	/**
	 * Provides the product identifier (PID) for the BSP that generated the BDB in the BIR. Product Owner 
	 * values are assigned and registered by the CBEFF Registration Authority as Biometric Organization 
	 * Identifiers. Product Type is assigned by the Product Owner and may optionally be registered. 
	 * Registration authority information is located in ISO/IEc 19785-2. Product IDs are analogous to Format 
	 * IDs and the registration process is the same. A single vendor can register for one Format/Product Owner 
	 * value (a Biometric Organization identifier) that can be used in both fields. The BioAPI BIR Biometric 
	 * Product ID corresponds to the "CBEFF_BDB_product_owner" and "CBEFF_BDB_product_type" in ISO/IEC 
	 * 19785-1.
	 * 
	 * @author	Eric Goldman
	 * 
	 * @status	Implemented but untested.
	 */
	public interface ProductID
		extends OwnerTypePair
	{
	}

	/**
	 * Defines the purpose for which the BioAPI BIR is intended (when used as an input to a BioAPI function) or 
	 * is suitable (when used as an output from a BioAPI function or within the BIR header).
	 * 
	 * @author	Eric Goldman
	 * 
	 * @status	Implemented but untested.
	 */
	public enum Purpose
	{
		AUDIT,
		ENROLL,
		ENROLL_FOR_IDENTIFICATION_ONLY,
		ENROLL_FOR_VERIFICATION_ONLY,
		IDENTIFY,
		VERIFY
	}

	/**
	 * <p>
	 * Represents the relative quality of the biometric data in the BDB.
	 * </p>
	 * <p>
	 * Quality scores in the range 0-100 have the following general interpretation:
	 * </p>
	 * <p>
	 * 0-25: UNACCEPTABLE: The BDB cannot be used for the pupose specified by the application. The BDB
	 * needs to be replaced using one or more new biometric samples.
	 * </p>
	 * <p>
	 * 26-50: MARGINAL: The BDB will provide poor performance for the pupose specified by the application 
	 * and in most application environments will compromise the intent of the application. The BDB needs to 
	 * be replaced using one or more new biometric samples.
	 * </p>
	 * <p>
	 * 51-75: ADEQUATE: The biometric data will provide good performance in most application environments 
	 * based on the pupose specified by the application. The application should attempt to obtain higher 
	 * quality data if the application developer anticipates demanding usage.
	 * </p>
	 * <p>
	 * 76-100: EXCELLENT: The biometric data will provide good performance for the pupose specified by the 
	 * application.
	 * </p>
	 * <p>
	 * NOTE: The BioAPI Quality corresponds to the "CBEFF_BDB_quality" in ISO/IED 19785-1.
	 * </p>
	 * 
	 * @author	Eric Goldman
	 * 
	 * @status	Implemented but untested.
	 */
	public interface Quality
	{
		public int getQuality();
	}

	/**
	 * <p>
	 * Defines the format of the data contained within the security block (SB) of the BioAPI BIR. 
	 * 'SecurityFormatOwner' values are assigned and registered by the CBEFF Registration Authority as 
	 * Biometric Organization identifiers. Security Format Type is assigned by the Security Format Owner (the 
	 * Biometric Organization) and may optionally be registered. Registration Authority information is located 
	 * in ISO/IEC 19785-2. Security Format IDs are analogous to Format IDs and the registration process is the 
	 * same. A single vendor can register for one Format/Product/Security Owner value (a Biometric 
	 * Organization identifier) that can be used in all associated fields. The content of the Security Block itself 
	 * may include a digital signature or message authentication code (calculated on the BIR Header + BDB), 
	 * BDB encryption parameters (e.g., encryption algorithm, key length), and/or BIR integiry parameters 
	 * (e.g., algorithm ID, keyname, version). The BioAPI BIR Security Block Format in a BioAPI BIR corresponds 
	 * to the "CBEFF_SB_format_owner" and "CBEFF_CB_format_type" is ISO/IEC 19785-1.
	 * </p>
	 * 
	 * @author	Eric Goldman
	 * 
	 * @status	Implemented but untested.
	 */
	public interface SecurityBlockFormat
		extends OwnerTypePair
	{
	}

	/**
	 * Identifies a subtype withing the BDB type.
	 * 
	 * @author	Eric Goldman
	 * 
	 * @status	Implemented but untested.
	 */
	public interface Subtype
	{
		/**
		 * 
		 * @author	Eric Goldman
		 * 
		 * @status	Implemented but untested.
		 */
		public enum Instance
		{
			
			LEFT_LITTLEFINGER,
			LEFT_MIDDLEFINGER,
			LEFT_POINTERFINGER,
			LEFT_RINGFINGER,
			LEFT_THUMB,
			RIGHT_LITTLEFINGER,
			RIGHT_MIDDLEFINGER,
			RIGHT_POINTERFINGER,
			RIGHT_RINGFINGER,
			RIGHT_THUMB
		}

		public Instance[] getInstances();
		public boolean hasLeftThumb();
		public boolean hasLeftPointerFinger();
		public boolean hasLeftMiddleFinger();
		public boolean hasLeftRingFinger();
		public boolean hasLeftLittleFinger();
		public boolean hasRightThumb();
		public boolean hasRightPointerFinger();
		public boolean hasRightMiddleFinger();
		public boolean hasRightRingFinger();
		public boolean hasRightLittleFinger();
		public boolean contains(Instance instance);
	}

	public boolean isBound() throws BioAPIException;
	public void unbind() throws BioAPIException;
	public void destroy() throws BioAPIException;
	public String getHeaderVersion() throws BioAPIException;
	public BIR.ProcessedLevel getProcessedLevel() throws BioAPIException;
	public BIR.Format getFormat() throws BioAPIException;
	public BIR.Quality getQuality() throws BioAPIException;
	public BIR.Purpose getPurpose() throws BioAPIException;
	public BIR.BiometricType getFactorsMask() throws BioAPIException;
	public BIR.ProductID getProductID() throws BioAPIException;
	public BIR.DTG getCreationDTG() throws BioAPIException;
	public BIR.Subtype getSubtype() throws BioAPIException;
	public Date getExpirationDate() throws BioAPIException;
	public boolean isBDBEncrypted() throws BioAPIException;
	public boolean isBIRSigned() throws BioAPIException;
	public BIR.SecurityBlockFormat getSBFormat() throws BioAPIException;
	public boolean isBIRIndexed() throws BioAPIException;
	public UUID getIndex() throws BioAPIException;
	public byte[] getBiometricData() throws BioAPIException;
	public byte[] getSecurityBlock() throws BioAPIException;
}