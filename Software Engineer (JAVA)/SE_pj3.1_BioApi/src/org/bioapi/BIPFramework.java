package org.bioapi;

import org.bioapi.net.IRI;

/**
 * Defines BIP extensions to BioAPI.
 * 
 * @author Ashwin Mohan
 */
public interface BIPFramework
{
	/**
	 * Links the endpoint to the slave endpoint identified by IRI
	 * 
	 * @param slaveEndpointIRI identifies the target slave endpoint.
	 * @throws BioAPIException if any error occurs
	 */
	public void linkToEndpoint(IRI slaveEndpointIRI) throws BioAPIException;
	
	/**
	 * Unlinks the endpoint from the slave endpoint identified by IRI
	 * 
	 * @param slaveEndpointIRI identifies the target slave endpoint.
	 * @throws BioAPIException if the slave endpoint is not linked to this endpoint or if any other error occurs.
	 */
	public void unlinkFromEndpoint(IRI slaveEndpointIRI) throws BioAPIException;
}
