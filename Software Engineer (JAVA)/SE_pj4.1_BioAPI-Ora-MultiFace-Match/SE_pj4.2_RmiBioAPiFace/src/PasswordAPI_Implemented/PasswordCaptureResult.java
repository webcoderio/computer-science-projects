package PasswordAPI_Implemented;

import org.bioapi.data.BIR;
import org.bioapi.Sensor.CaptureResult;

public class PasswordCaptureResult extends PasswordBIR implements CaptureResult
{
	public PasswordCaptureResult(String username, String password)
	{
		super(username, password);
	}

	public BIR getAuditData()
	{
		return (BIR) this;
	}

	public BIR getCapturedBIR()
	{
		return (BIR) this;
	}

	public boolean hasAuditData()
	{
		return true;
	}
}
