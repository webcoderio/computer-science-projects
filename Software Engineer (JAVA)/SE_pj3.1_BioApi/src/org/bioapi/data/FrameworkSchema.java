package org.bioapi.data;

/**
 * Represents the record in the component registry that defines properties of the implementation of the biometric system. For security reasons no methods to modify the existing FrameworkSchema object are provided.
 * 
 * @author Ashwin Mohan
 */
public interface FrameworkSchema
{
	public java.util.UUID getFrameworkUuid();
	
	public java.lang.String getFwDescription();
	
	public byte[] getFwProperty();
	
	public java.util.UUID getFwPropertyId();
	
	public java.lang.String getPath();
	
	public java.lang.String getProductVersion();
	
	public java.lang.String getSpecVersion();
	
	public java.lang.String getVendor();
}
