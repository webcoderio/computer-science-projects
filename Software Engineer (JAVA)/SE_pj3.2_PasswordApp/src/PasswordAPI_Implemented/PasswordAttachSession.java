package PasswordAPI_Implemented;

import org.bioapi.Archive;
import org.bioapi.BioAPIException;
import org.bioapi.Matching;
import org.bioapi.Sensor;
import org.bioapi.AttachSession.VerifyResult;
import org.bioapi.data.BIR;
import org.bioapi.data.FMR;
import org.bioapi.data.BIR.Subtype;
import org.bioapi.template.AttachSession_Implemented;

public class PasswordAttachSession extends AttachSession_Implemented
{
	private PasswordMatchingUnit matchingUnit ;
	private PasswordSensorUnit sensorUnit ;
    private PasswordArchiveUnit archiveUnit ;

    PasswordAttachSession(){
        matchingUnit = new PasswordMatchingUnit();
        sensorUnit = new PasswordSensorUnit();
        archiveUnit= new PasswordArchiveUnit();
    }

    @Override
	public Archive getArchive() throws BioAPIException
	{
		return archiveUnit;
	}

    @Override
	public Matching getMatching() throws BioAPIException
	{
		return matchingUnit;
	}

    @Override
	public Sensor getSensor() throws BioAPIException
	{
		return sensorUnit;
	}

	
    @Override
	public VerifyResult verify(FMR maxFMRrequested, BIR referenceTemplate,
			Subtype subtype, int timeout,
			org.bioapi.AttachSession.VerifyResult.Options options)
			throws BioAPIException
	{
		return (VerifyResult) matchingUnit.verify(maxFMRrequested, 
                (PasswordBIR) getSensor().capture(null, null, null, 0, null),
                referenceTemplate, null);
	}

}
