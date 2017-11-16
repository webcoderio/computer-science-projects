package PasswordAPI_Implemented;

import org.bioapi.Sensor.CaptureResult.Options;
import org.bioapi.data.BIR.Format;
import org.bioapi.data.BIR.Purpose;
import org.bioapi.data.BIR.Subtype;
import org.bioapi.template.SensorUnit_Implemented;


public class PasswordSensorUnit extends SensorUnit_Implemented
{
	private PasswordEntry pent;


    PasswordSensorUnit(){
        pent = new PasswordEntry();
    }


    @Override
	public CaptureResult capture(Purpose[] purpose, Subtype subtype,
			Format outputFormat, int timeout, Options[] options)
			
	{
		return new PasswordCaptureResult(pent.getsUserName(), pent.sPassword);
	}
}
