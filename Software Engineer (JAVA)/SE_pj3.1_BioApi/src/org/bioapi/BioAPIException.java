package org.bioapi;


/**
 * Represents exceptional condition that occurs during the operation of a biometric system. Along with the
 * message the object carries additional information about the error, such as the ID of the component that
 * reported the error and the numeric code taht identifies the reason of error.
 * 
 * @author	Ashwin Mohan, Eric Goldman
 */
public class BioAPIException
	extends java.lang.Exception
{
	/**
	 * Defines source of error.
	 * 
	 * @status	Implemented but untested.
	 */
	public enum Facility
	{
		/** The error was reported by the framework component. */
		FRAMEWORK,
		/** The error was reported by the biometric service provider. */
		BSP,
		/** The error was reported by the biometric unit. */
		UNIT;
	}

	protected Facility m_Source;
	protected int m_ErrorCode;

	/**
	 * Creates a new instance of BioAPIException with the specified error source and the reason code.
	 * 
	 * @param	source	identifies component of the biometric system that reported an error.
	 * @param	code	numeric code that specifies the reason of error.
	 * 
	 * @status	Implemented but untested.
	 */
	public BioAPIException(Facility source, int code)
	{
		setup(source, code);
	}

	/**
	 * Creates a new instance of BioAPIException with the specified error source, reason code, and the message.
	 * 
	 * @param	source	identifies component of the biometric system taht reported an error.
	 * @param	code	numeric code that specifies the reason of error
	 * @param	message	specifies the error message
	 * 
	 * @status	Implemented but untested.
	 */
	public BioAPIException(Facility source, int code, java.lang.String message)
	{
		super(message);	//Exception
		setup(source, code);
	}

	/**
	 * Creates a new instance of BioAPIException with the specified error source, reason code, message, 
	 * and cause. This constructor is used to represent an error that was caused by another exception.
	 * 
	 * @param	source	identifies component of the biometric system taht reported an error.
	 * @param	code	numeric code that specifies the reason of error
	 * @param	message	specifies the error message
	 * @param	cause	the cause (which is saved for later retrieval by the BioAPIException.getCause() method). (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
	 * 
	 * @status	Implemented but untested.
	 */
	public BioAPIException(Facility source, int code, java.lang.String message, java.lang.Throwable cause)
	{
		super(message, cause);	//Exception
		setup(source, code);
	}

	/**
	 * Creates a new instance of BioAPIException with the specified error source, reason code, cause, 
	 * and error message associated with the cause (if any). This constructor is used to represent an 
	 * error that was caused by another exception.
	 * 
	 * @param	source	identifies component of the biometric system taht reported an error.
	 * @param	code	numeric code that specifies the reason of error
	 * @param	cause	the cause (which is saved for later retrieval by the BioAPIException.getCause() method). (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
	 * 
	 * @status	Implemented but untested.
	 */
	public BioAPIException(Facility source, int code, java.lang.Throwable cause)
	{
		super(cause);	//Exception
		setup(source, code);
	}

	/**
	 * Internal method that is used to initialize the instance.
	 * 
	 * @param	source	identifies component of the biometric system taht reported an error.
	 * @param	code	numeric code that specifies the reason of error
	 * 
	 * @status	Implemented but untested.
	 */
	protected void setup(Facility source, int code)
	{
		m_Source = source;
		m_ErrorCode = code;
	}

	/**
	 * 
	 * 
	 * @return	the ID of the component of the biometric system that reported the error.
	 * 
	 * @status	Implemented but untested.
	 */
	public Facility getSource()
	{
		return m_Source;
	}

	/**
	 * 
	 * 
	 * @return	the numeric code that identifies the reason of the error.
	 * 
	 * @status	Implemented but untested.
	 */
	public int getErrorCode()
	{
		return m_ErrorCode;
	}

	public static final int BIOAPI_FRAMEWORK_ERROR = 0x00000000;
	public static final int BIOAPI_BSP_ERROR = 0x01000000;
	public static final int BIOAPI_UNIT_ERROR = 0x02000000;

	/**
	 * General system error; indicates that an operating system or internal state
	 * error has occured and the system may not be in a known state.
	 */
	public static final int BioAPIERR_INTERNAL_ERROR = 0x000101;

	/**
	 * A memory error occured. 
	 */
	public static final int BioAPIERR_MEMORY_ERROR = 0x000102;

	/**
	 * An input/output function parameter or input/output field inside of a 
	 * data structure is an invalid pointer.
	 */
	public static final int BioAPIERR_INVALID_POINTER = 0x000103;

	/**
	 *An input function parameter or input field in a datastructure is an invlaid pointer. 
	 */
	public static final int BioAPIERR_INVALID_INPUT_POINTER = 0x000104;

	/**
	 * An output function parameter or output field in a data structure is an invalid pointer.
	 */
	public static final int BioAPIERR_INVALID_OUTPUT_POINTER = 0x000105;

	/**
	 *The function is not implemented by the biometric service provider.
	 */
	public static final int BioAPIERR_FUNCTION_NOT_SUPPORTED = 0x000106;

	/**
	 *The operating system denied access to a required resource.
	 */
	public static final int BioAPIERR_OS_ACCESS_DENIED = 0x000107;

	/**
	 * The function failed for an unknown reason.
	 */
	public static final int BioAPIERR_FUNCTION_FAILED = 0x000108;

	/**
	 * An input UUID is invalid. [May occur of a component requested by the UUID is not present on the system or cannot be found.
	 */
	public static final int BioAPIERR_INVALID_UUID = 0x000109;

	/**
	 * Version incompatibility. [May occur if the called component cannot support the version of the BioAPI specification expected by the application.]
	 */
	public static final int BioAPIERR_INCOMPATIBLE_VERSION = 0x00010a;

	/**
	 * The data in an input parameter is invalid.
	 */
	public static final int BioAPIERR_INVALID_DATA = 0x00010b;

	/**
	 * The associated BSP is unable to capture raw samples from the requested BioAPI Unit.
	 */
	public static final int BioAPIERR_UNABLE_TO_CAPTURE = 0x00010c;

	/**
	 * The associated BSP has no more space to allocated BIR handles.
	 */
	public static final int BioAPIERR_TOO_MANY_HANDLES = 0x00010d;

	/**
	 * The function has been terminated because the timeout value has expired.
	 */
	public static final int BioAPIERR_TIMEOUT_EXPIRED = 0x00010e;

	/**
	 * The input BIR is invalid for the purpose required.
	 */
	public static final int BioAPIERR_INVALID_BIR = 0x00010f;

	/**
	 * The associated BSP could not validate the signature on the BIR.
	 */
	public static final int BioAPIERR_BIR_SIGNATURE_FAILURE = 0x000110;

	/**
	 * The associated BSP is unable to store the payload.
	 */
	public static final int BioAPIERR_UNABLE_TO_STORE_PAYLOAD = 0x000111;

	/**
	 * The identity population is null.
	 */
	public static final int BioAPIERR_NO_INPUT_BIRS = 0x000112;

	/** 
	 * The associated BSP does not support the BDB requests.
	 */
	public static final int BioAPIERR_UNSUPPORTED_FORMAT = 0x000113;

	/**
	 * The associated BSP was unable to construct a BIR from the input data.
	 */
	public static final int BioAPIERR_UNABLE_TO_IMPORT = 0x000114;

	/**
	 * The purpose recorded in the BIR and/or the requested purpose are inconsistent with the function being performed.
	 */
	public static final int BioAPIERR_INCONSISTENT_PURPOSE = 0x000115;

	/**
	 * The function requires a fully processed BIR.
	 */
	public static final int BioAPIERR_BIR_NOT_FULLY_PROCESSED = 0x000116;

	/**
	 * The BSP does not support the requested purpose.
	 */
	public static final int BioAPIERR_PURPOSE_NOT_SUPPORTED = 0x000117;

	/**
	 * User cancelled operation before completion or timeout.
	 */
	public static final int BioAPIERR_USER_CANCELLED = 0x000118;

	/**
	 * BSP (or BioAPI Unit attached to BSP) is currently being used by another biometric application.
	 */
	public static final int BioAPIERR_UNIT_IN_USE = 0x000119;

	/**
	 * The given BSP handle is invalid
	 */
	public static final int BioAPIERR_INVALID_BSP_HANDLE = 0x00011a;

	/**
	 * A function has been called without initialized the BioAPI Framework.
	 */
	public static final int BioAPIERR_FRAMEWORK_NOT_INITIALIZED = 0x00011b;

	/**
	 * BIR handle is invalid (does not exist or has not been released).
	 */
	public static final int BioAPIERR_INVALID_BIR_HANDLE = 0x00011c;

	/**
	 * The attempted calibration of a sensor unit was not able to be successfully completed.
	 */
	public static final int BioAPIERR_CALIBRATION_NOT_SUCCESSFUL = 0x00011d;

	/**
	 * No preset BIR population has been established.
	 */
	public static final int BioAPIERR_PRESET_BIR_DOES_NOT_EXIST = 0x00011e;

	/**
	 * The BSP could not decrypt an input BIR (and thus was unable to use it for the related operation).
	 */
	public static final int BioAPIERR_DECRYPTION_FAILURE = 0x00011f;

	/**
	 * A reference to the file or the component being loaded cannot be found.
	 */
	public static final int BioAPIERR_COMPONENT_FILE_REF_NOT_FOUND = 0x000201;

	/**
	 * Framework was unable to successfully load the BSP.
	 */
	public static final int BioAPIERR_BSP_LOAD_FAIL = 0x000202;

	/**
	 * BSP for which an action was requested is not loaded.
	 */
	public static final int BioAPIERR_BSP_NOT_LOADED = 0x000203;

	/**
	 * BioAPI Unit for which an action was not requested is not in the inserted state.
	 */
	public static final int BioAPIERR_UNIT_NOT_INSERTED = 0x000204;

	/** 
	 * An invalid BioAPI Unit ID was requested.
	 */
	public static final int BioAPIERR_INVALID_UNIT_ID = 0x000205;

	/**
	 * An invalid category of BFP or BioAPI Unit was requested.
	 */
	public static final int BioAPIERR_INVALID_CATEGORY = 0x000206;

	/**
	 * Invalid database handle.
	 */
	public static final int BioAPIERR_INVALID_DB_HANDLE = 0x000300;

	/**
	 * The associated BSP is unable to open the specified database.
	 */
	public static final int BioAPIERR_UNABLE_TO_OPEN_DATABASE = 0x000301;

	/**
	 * The database cannot be opened for the access requested because it is locked.
	 */
	public static final int BioAPIERR_DATABASE_IS_LOCKED = 0x000302;

	/**
	 * The specified database does not exist.
	 */
	public static final int BioAPIERR_DATABASE_DOES_NOT_EXIST = 0x000303;

	/**
	 * Create failed because the database already exists.
	 */
	public static final int BioAPIERR_DATABASE_ALREADY_EXISTS = 0x000304;

	/**
	 * Invalid database name (UUID)\
	 */
	public static final int BioAPIERR_INVALID_DATABASE_NAME = 0x000305;

	/**
	 * No record exists with the requested key.
	 */
	public static final int BioAPIERR_RECORD_NOT_FOUND = 0x000306;

	/**
	 * The specified marker handled is invalid.
	 */
	public static final int BioAPIERR_MARKER_HANDLE_IS_INVALID = 0x000307;

	/**
	 * The database is already open.
	 */
	public static final int BioAPIERR_DATABASE_IS_OPEN = 0x000308;

	/**
	 * Unrecognized access type.
	 */
	public static final int BioAPIERR_INVALID_ACCESS_REQUEST = 0x000309;

	/**
	 * End of database has been reached.
	 */
	public static final int BioAPIERR_END_OF_DATABASE = 0x00030a;

	/**
	 * The associated BSP cannot create the database.
	 */
	public static final int BioAPIERR_UNABLE_TO_CREATE_DATABASE = 0x00030b;

	/**
	 * The associated BSP cannot close the database.
	 */
	public static final int BioAPIERR_UNABLE_TO_CLOSE_DATABASE = 0x00030c;

	/**
	 * The associated BSP cannot delete the database.
	 */
	public static final int BioAPIERR_UNABLE_TO_DELETE_DATABASE = 0x00030d;

	/**
	 * The specified database is corrupt.
	 */
	public static final int BioAPIERR_DATABASE_IS_CORRUPT = 0x00030e;

	/**
	 * A general location error.
	 */
	public static final int BioAPIERR_LOCATION_ERROR = 0x000400;

	/**
	 * Invalid horizontal or vertical postion.
	 */
	public static final int BioAPIERR_OUT_OF_FRAME = 0x000401;

	/**
	 * Invalid crosswise position.
	 */
	public static final int BioAPIERR_INVALID_CROSSWISE_POSITION = 0x000402;

	/**
	 * Invalid lengthwise position.
	 */
	public static final int BioAPIERR_INVALID_LENGTHWISE_POSITION = 0x000403;

	/**
	 * Invalid distance.
	 */
	public static final int BioAPIERR_INVALID_DISTANCE = 0x000404;


	/**
	 * The position was too far to the right.
	 */
	public static final int BioAPIERR_LOCATION_TOO_RIGHT = 0x000405;

	/** The position was too far to the left.
	 *
	 */
	public static final int BioAPIERR_LOCATION_TOO_LEFT = 0x000406;

	/**
	 * The position was too high.
	 */
	public static final int BioAPIERR_LOCATION_TOO_HIGH = 0x000407;

	/** 
	 * The position was too low.
	 */
	public static final int BioAPIERR_LOCATION_TOO_LOW = 0x000408;

	/** 
	 * The position was too far away.
	 */
	public static final int BioAPIERR_LOCATION_TOO_FAR = 0x000409;

	/**
	 * The position was too near (close).
	 */
	public static final int BioAPIERR_LOCATION_TOO_NEAR = 0x00040a;

	/** 
	 * The position was too far foward.
	 */
	public static final int BioAPIERR_LOCATION_TOO_FORWARD = 0x00040b;

	/**
	 * The position was too far backward
	 */
	public static final int BioAPIERR_LOCATION_TOO_BACKWARD = 0x00040c;

	/** 
	 * Sample quality is too poor for the operation to succeed. Note: quality errors can be returned from any function which received a BioAPI BIR input.
	 */
	public static final int BioAPIERR_QUALITY_ERROR = 0x000501;
}