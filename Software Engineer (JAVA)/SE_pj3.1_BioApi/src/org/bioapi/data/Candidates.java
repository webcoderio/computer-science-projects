/**
 * 
 */
package org.bioapi.data;

import org.bioapi.BioAPIException;

/**
 * Represents the collection of BIRs identified as a result of match process. No methods to modify the existing Candidates object are provided.
 * 
 * @author Ashwin Mohan
 */
public interface Candidates
{
	/**
	 * @return true if at least one candate BIR is bound to the BSP.
	 * @throws BioAPIException
	 */
	public boolean isBound() throws BioAPIException;
	
	/**
	 * Ensures that all candidate BIRs are unbound.
	 * @throws BioAPIException
	 */
	public void unbind() throws BioAPIException;
	
	public void destroy() throws BioAPIException;
	
	public java.util.Enumeration<Candidates.Candidate> getMembers() throws BioAPIException;
	
	/**
	 * Represents individual BIR identified as a result of match process.
	 * 
	 * @author James Kupke
	 */
	public interface Candidate
	{
		public BIR getTemplate();
		
		public FMR getFMRAchieved();
	}
}
