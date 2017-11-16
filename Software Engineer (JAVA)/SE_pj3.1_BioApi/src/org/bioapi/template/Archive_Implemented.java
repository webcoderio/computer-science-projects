package org.bioapi.template;

import org.bioapi.Archive;
import org.bioapi.BIRDatabase;
import org.bioapi.BIRDatabase.Access;
import org.bioapi.BioAPIException;
import org.bioapi.data.IdentifyPopulation;

/**
 *
 * @author Ashwin Mohan
 */
public class Archive_Implemented implements Archive{

    public Archive_Implemented(){
        
    }

    public void createDatabase(String name) throws BioAPIException {

    }

    public void deleteDatabase(String name) throws BioAPIException {

    }

    public IdentifyPopulation newIdentifyPopulation() throws BioAPIException {
        return null;
    }

    public BIRDatabase openDatabase(String name, Access access) throws BioAPIException {
        return null;
    }

}
