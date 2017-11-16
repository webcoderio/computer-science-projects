package org.bioapi.net;

/**
 * <p>
 * Represents the International Resource Identifier as defined in RFC 3987. The API offered by the class will 
 * be much like the API of java.net.URI. Hopefulle, next version(s) of JDK will provide standard class to deal 
 * with International Resource Identifiers.
 * </p>
 * 
 * @author	Eric Goldman.
 */
public class IRI
	extends java.lang.Object
	implements java.io.Serializable, java.lang.Comparable<IRI>
{
	/**
	 * Creates a new instance of IRI
	 */
	public IRI(String path)
	{
	}

	/**
	 */
	public int compareTo(IRI t)
	{
		return 0;
	}
}