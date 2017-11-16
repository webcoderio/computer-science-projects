package org.bioapi;

import org.bioapi.BIRDatabase.Access;
import org.bioapi.data.IdentifyPopulation;

/**
 * Represents functionality that is available when the archive unit is attached to the Session.
 * 
 * @author Ashwin Mohan
 */
public interface Archive
{
	/**
	 * Requests the BSP to create an empty database.
	 * 
	 * @param name				specifies the name of the database. The syntax of the name depends on the version of the standard. It can be a simple name or a String containing UUID.
	 * @throws BioAPIException	if the name specifies the database that already exists or the creation has failed due to an error
	 */
	public void createDatabase(String name) throws BioAPIException;
	
	/**
	 * Requests the BSP to delete an existing database.
	 *
     * @param name				specifies the name of the database. The syntax of the name depends on the version of the standard. It can be a simple name or a String containing UUID.
	 * @throws BioAPIException	if the database connected to the BSP does not exist or database deletion failed due to an error
	 */
	public void deleteDatabase(String name) throws BioAPIException;
	
	/**
	 * Assigns the database identified by name as the data source that defines the population to be used for biometric identification
	 * 	 
	 * @return					the object representing the identify population.
	 * @throws BioAPIException	if the database connected to the BSP does not exist or cannot be opened due to an error.
	 */
	public IdentifyPopulation newIdentifyPopulation() throws BioAPIException;
	
	/**
	 * Opens the database managed by the BSP.
	 *
     * @param name				specifies the name of the database. The syntax of the name depends on the version of the standard. It can be a simple name or a String containing UUID.
	 * @param access			access mode in which the database is opened.
	 * @return					the object representing the database opened
	 * @throws BioAPIException	if the database connected to the BSP does not exist or the open has failed due to an error
     * @deprecated              since access for an open Database comes from legacy use in the C implementation
	 */
	public BIRDatabase openDatabase(String name,Access access) throws BioAPIException;
}
