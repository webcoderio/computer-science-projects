package PasswordAPI_Implemented;

import java.util.UUID;

import org.bioapi.AttachSession;
import org.bioapi.BIRDatabase.Access;
import org.bioapi.BioAPIException;
import org.bioapi.template.BFP_Implemented;
import org.bioapi.template.BIRDatabase_Implemented;

public class PasswordBFP extends BFP_Implemented
{
	private PasswordAttachSession session;
    private PasswordArchiveUnit _pass= new PasswordArchiveUnit();
    BIRDatabase_Implemented _birdatabase;
    PasswordBIR _bir;
    private Access access;
    UUID [] entries;

	public PasswordBFP(AttachSession attachSession) throws BioAPIException
	{
		session = (PasswordAttachSession)attachSession;
        _pass= (PasswordArchiveUnit) session.getArchive();
        _birdatabase= (BIRDatabase_Implemented) _pass.openDatabase("biopassword", access.READ);
        entries=_pass.getEntries();
	}
	
	// Implement this method to allow knowing the user who login
	public String Verify() throws BioAPIException
	{
       int count =0;
        while(count < entries.length){
            _bir= (PasswordBIR) _birdatabase.getSingleBIR(entries[count]).getValue();
            if(session.verify(null,_bir, null,-1,null).getResult()==true)
            return _bir.getUsername();
            count ++;
           }
             return null;
	}
	
    public boolean Enroll() throws BioAPIException
	{
        int count =0;
		PasswordBIR entered = (PasswordBIR) session.getSensor().capture(null, null, null, 0, null);
        while(count < entries.length){
           _bir= (PasswordBIR) _birdatabase.getSingleBIR(entries[count]).getValue();
            if(entered.getUsername().equalsIgnoreCase(_bir.getUsername()))
            return false;
            count ++;

           }
        _pass.insertRecord(entered.getUsername(),entered.getPassword());
           return true;
	}

	
    @Override
	public UUID getBFPPropertyID()
	{
		return UUID.randomUUID();
	}

}
