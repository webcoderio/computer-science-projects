package org.bioapi;

import org.bioapi.BioAPIException;
import org.bioapi.data.BIR;

/**
 * Represents the group of biometric operations that are available when the unit of SENSOR category is linked to the attach session.  
 *  
 * @author Ashwin Mohan
 */
public interface Sensor 
{
    public interface CaptureResult
    {
        /**
         * Defines optional output of capturing the biometric sample.
         */
        public enum Options
        {
            /**
             *Requests that BSP should collect audit data. This data may be used to provide human-identifiable data of the person at the sensor unit. Not all BSPs support the collection of audit data.
             */
            REQUEST_AUDIT_DATA


        };
        
        /**
        * @returns captured biometric sample.
        **/
        
        public BIR getCapturedBIR();


        /**
        *Indicates if audit data has been collected. The method returns false if the collection of audit data has not been requested of if the BSP does not support collection of audit data.
        * @returns true if audit data has been collected during the operation.
        **/

        public boolean hasAuditData() ;


        /**
        *public BIR getAuditData()
        * @returns BIR object containing raw audit data or null if audit data is not available.
        **/

        public BIR getAuditData();
    }
    
    /**
    *capture public Sensor.CaptureResult capture(BIR.Purpose[] purpose,  BIR.Subtype subtype,  BIR.Format outputFormat,  int timeout,  Sensor.CaptureResult.Options[] options) throws BioAPIException Captures samples for the purpose specified, and the BSP returns either an "intermediate" type BIR (if the Processing.process() method needs to be called), or a "processed" BIR (if not). The purpose is recorded in the header of the captured BIR. If REQUEST_AUDIT_DATA option is specified a BIR of type "raw" may be returned in CaptureResult. By default, the BSP is responsible for providing the user interface associated with the capture operation. The application may, however, request control of the GUI "look-and-feel" by providing a GUI callback vis the Session.setGUIObservers() method. Capture serializes use of the sensor device. If two or more biometric applications are racing for the sensor, the losers will wait until the operation completes or the timeout expires. This serialization takes place in all functions that capture data. The BSP is responsible for serializing. It may do this by either throwning an exception to indicate that device is busy or by queuing requests.
    * @param purpose - indicates the purpose of the biometric data capture. 
    * @param subtype - specifies which subtype (e.g., left/right eye) to capture. null indicates that the value is not provided. 
    * @param outputFormat - specifies which BDB format to use for the returned processed BIR, if the BSP supports more than one format. null indicates that the BSP is to select the format. 
    * @param timeout - an integer specifying the timeout value (in milliseconds) for the operation. If this timeout is reached an exception is thrown. This value can be any positive number. A -1 value means the BSPs default timeout value will be used. 
    * @param options - requests additional output such as audit data. 
    * @returns the CaptureResult object that represents the result of capture operation.
    * @throws BioAPIException - if the sensor device is busy or operation has failed due to another error.org.bioapi.Sensor
    **/

    public Sensor.CaptureResult capture(BIR.Purpose[] purpose,  BIR.Subtype subtype,  BIR.Format outputFormat,  int timeout,  Sensor.CaptureResult.Options[] options) throws BioAPIException;


    /**
    *calibrate public void calibrate(int timeout) throws BioAPIException Performs a calibration of the attached sensor BioAPI Unit if that sensor unit supports it.
    * @param timeout - an integer specifying the timeout value (in milliseconds) for the operation. If this timeout is reached an exception is thrown. This value can be any positive number. A -1 value means the BSPs default timeout value will be used. 
    * @throws BioAPIException - if calibration is not supported or failed due to an error.
    **/

    public void calibrate(int timeout) throws BioAPIException;



    
}
