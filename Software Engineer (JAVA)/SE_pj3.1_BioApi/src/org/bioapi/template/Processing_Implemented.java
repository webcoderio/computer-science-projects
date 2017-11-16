/*
 * Processing_Implemented.java
 *
 * Created on April 18, 2007, 1:05 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.bioapi.template;

import org.bioapi.BioAPIException;
import org.bioapi.data.*;
/**
 *
 * @author Ashwin Mohan
 */
public class Processing_Implemented implements org.bioapi.Processing{
    
    /** Creates a new instance of Processing_Implemented */
    public Processing_Implemented() {
    }
    
    /**
     * Takes a BIR containing biometric data in intermediate form for the purpose of creating a new enrollment template. A new BIR is constructed from the captured BIR, and (optionally) it may perform an update based on an existing reference template. The old reference template remains unchanged. The optional input reference template is provided for use in creating the new template, if the BSP supports this capability. When present, use of the input reference template by the BSP, to create the output template is optional. If the BSP supports an internal or BSP- controlled BIR database (e.g., smartcard or identification engine), it may optionally return the UUID assigned to the newly created template as stored within that BSP controlled BIR database. The UUID value shall be the same as that included in the BIR header, if present.
     *
     * @param capturedBIR       the captured BIR.
     * @param referenceTemplate optionally, the existing template to be updated.
     * @param outputFormat      specifies which BDB format to use for the returned processed BIR, if the BSP supports more than one format. null indicates that the BSP is to select the format.
     * @param payload           a payload that will be stored by the BSP.
     * @return                  the CreateTemplateResult object that represents the result of the operation.
     * @throws BioAPIException  if input parameters are invalid or operation fails due to an error.
     */
    public org.bioapi.Processing.CreateTemplateResult createTemplate(BIR capturedBIR, BIR referenceTemplate, BIR.Format outputFormat, Payload payload) throws BioAPIException {
        return null;
    }
    
    /**
     * Processes the intermediate data captured via a call to Sensor.capture() for the purpose of either verification or identification. The BSP builds a "processed biometric sample" BIR.
     *
     * @param capturedBIR      the captured BIR.
     * @param outputFormat     specifies which BDB format to use for the returned processed BIR, if the BSP supports more than one format. null indicates that the BSP is to select the format.
     * @return                 the ProcessResult object that represents the result of processing.
     * @throws BioAPIException if input parameters are invalid or operation fails due to an error.
     */
    public org.bioapi.Processing.ProcessResult process(BIR capturedBIR, BIR.Format outputFormat) throws BioAPIException {
        return null;
    }
    
    /**
     * Processes the intermediate data previously captured via a call to Sensor.capture() in conjunction with auxiliary data, creating processed biometric samples for the purpose of subsequent verification or identification. It enables implementations that require the input of auxiliary data to the process operation. This capability may be used to support biometric match-on-card (MOC). If the processing with auxiliary data capability is supported by the attached BSP invocation, the BSP builds a "processed biometric sample" BIR, otherwise an exception is thrown.
     * <p>
     * NOTE 1: An example of auxiliary data is information related to the enrollment template which allows the processing operation to properly crop an input image to maximize the possibility of a subsequent match (e.g., to ensure that the processed BIR for verification and the enrollment template are derived from the same portion of a finger). Another would be passing of biometric algorithm parameters.
     * <p>
     * NOTE 2: The content and format of the auxiliary data are specified by the BIR Biometric Data Format field in the auxiliary BIR header and may be specific to a BSP.
     *
     * @param capturedBIR      the captured BIR.
     * @param auxiluaryBIR     a BIR containing auxiliary data used in the processing operation.
     * @param outputFormat     specifies which BDB format to use for the returned processed BIR, if the BSP supports more than one format. null indicates that the BSP is to select the format.
     * @return                 the ProcessResult object that represents the result of processing.
     * @throws BioAPIException if input parameters are invalid or operation fails due to an error.
     */
    public org.bioapi.Processing.ProcessResult process(BIR capturedBIR, BIR auxiluaryBIR, BIR.Format outputFormat) throws BioAPIException {
        return null;
    }
    /* Nested Classes */
    
    /**
     * Represents the result of processing of a BIR.
     *
     * @author James Kupke
     */
    public class ProcessResult implements org.bioapi.Processing.ProcessResult {
        /**
         * @return the processed BIR.
         */
        
        BIR processedBir;
        ProcessResult(BIR _processedBir)
        {
            processedBir = _processedBir;
        }
        
        public BIR getProcessedBIR() 
        {
            return processedBir;
        }
    }
    
    /**
     * Represents the result of processing a BIR into a template.
     *
     * @author James Kupke
     */
    public class CreateTemplateResult implements org.bioapi.Processing.CreateTemplateResult{
        
        BIR template;
        CreateTemplateResult(BIR _template) {
            template = _template;
        }
        
        /**
         * @return true if the BSP has assigned the UUID to the created template.
         */
        public boolean hasTemplateUUID() {
            try
            {
                if(template.getIndex() != null)
                    return true;
                else
                    return false;
            }
            catch(Exception x)
            {
                //TODO: notify user of exceptions
            }
            return false;
        }
        
        /**
         * @return the UUID assigned by the BSP to the created template.
         */
        public java.util.UUID getTemplateUUID() {
            try
            {
                return template.getIndex();
            }
            catch(Exception x)
            {
                //TODO: notify user of exception
            }
            return null;
            
        }
        
        /**
         * @return the BIR representing the template created.
         */
        public BIR getTemplate() {
            return template;
            
        }
    }
}
