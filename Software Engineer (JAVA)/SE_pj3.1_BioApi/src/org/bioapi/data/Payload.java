package org.bioapi.data;

/**
 * Represents the payload the application can associate with the biometric template. For security reasons no methods to modify the existing Payload object are provided. Instead, an application should use the corresponding method of the DataFactory to create new Payload containing modified data.
 * 
 * @author Ashwin Mohan
 */
public interface Payload<T>
{
	public T getData();
}
