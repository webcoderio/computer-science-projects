package org.bioapi.data;

import org.bioapi.BioAPIException;

/**
 * Represents the collection of BIRs the match takes place against on biometric identification. The population can be presented in one of two ways:
 * <ol>
 * <li>In a BIR database. Such population is defined via the Archive.newIdentifyPopulation(String).
 * <li>In a collection of BIRs. Such population is created via the DataFactory.newIdentifyPopulation(Collection).
 * </ol>
 * <p>
 * No methods to modify the existing IdentifyPopulation object are provided. Instead, an application should use the corresponding method of the DataFactory to create new IdentifyPopulation containing modified data.
 * 
 * @author Ashwin Mohan
 */
public interface IdentifyPopulation<BIR>
{
	/**
	 * @return true if at least one BIR in the population is bound to the BSP
	 * @throws BioAPIException
	 */
	public boolean isBound() throws BioAPIException;
	
	/**
	 * Ensures that all member BIRs are unbound.
	 * 
	 * @throws BioAPIException
	 */
	public void unbind() throws BioAPIException;
	
	public void destroy() throws BioAPIException;
	
	public java.util.Enumeration<BIR> getMembers() throws BioAPIException;
}
