package com.neurotechnology.ScannerMan;



public class ScanManException extends Exception {

	private static final long serialVersionUID = 1L;
	protected int errorCode;

    public ScanManException(int errorcode, String message)
    {
        super(message);
        errorCode = errorcode;
    }

    public int getErrorCode()
    {
        return errorCode;
    }

    public static boolean failed(int errorCode)
    {
        return ((errorCode) < 0);
    }

    public static boolean succeeded(int errorCode)
    {
        return ((errorCode) >= 0);
    }

    // Error codes: 

    // General
    public static final int SME_OK                   = 0;
    public static final int SME_FAILED               = -1;
    public static final int SME_OUT_OF_MEMORY        = -2;
    public static final int SME_NOT_INITIALIZED      = -3;
    public static final int SME_ARGUMENT_NULL        = -4;
    public static final int SME_INVALID_ARGUMENT     = -5;
    // Parameters
    public static final int SME_INVALID_PARAMETER    = -10;
    public static final int SME_PARAMETER_READ_ONLY  = -11;
    // Capturing
    public static final int SME_NOT_CAPTURING        = -100;
    public static final int SME_ALREADY_CAPTURING    = -101;
}
