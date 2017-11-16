/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bioapi.template;

import org.bioapi.BioAPIException;
import org.bioapi.Sensor;
import org.bioapi.Sensor.CaptureResult.Options;
import org.bioapi.data.BIR.Format;
import org.bioapi.data.BIR.Purpose;
import org.bioapi.data.BIR.Subtype;

/**
 *
 * @author Ashwin Mohan
 */
public class SensorUnit_Implemented extends UnitClass_Implemented implements Sensor{

    public CaptureResult capture(Purpose[] purpose, Subtype subtype, Format outputFormat, int timeout, Options[] options) throws BioAPIException {
        return null;
    }

    public void calibrate(int timeout) throws BioAPIException {
        
    }

}
