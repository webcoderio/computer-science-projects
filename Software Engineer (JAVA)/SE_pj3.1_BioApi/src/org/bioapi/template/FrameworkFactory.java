package org.bioapi.template;

import org.bioapi.BioAPIException;
import org.bioapi.Framework;

/**
 *
 * @author Ashwin Mohan
 */
public abstract class FrameworkFactory extends java.lang.Object
{
    /**
     * Base constructor
     */
    public FrameworkFactory()
    {
    }
    
    /**
     * Creates and initializes the new instance of the Framework_Implemented. The method detects the qualified name of the class implementing the Framework_Implemented interface as follows:
     *<ul>
     *<li>Use the org.bioapi.<version>.Framework_Implemented system property.
     *<li>Use the org.bioapi.Framework_Implemented property from the properties file "org.bioapi.<version>.properties" in it.
     *<li>Use the Services API (as detailed in the JAR specification), if available, to determine the classname. The Services API will look for a classname in the file META- INF/services/org.bioapi[.<version>].Framework_Implemented in jars available to the runtime.
     *</ul>
     *<p>
     * where <version> is the version number specified by the 'version' parameter.
     * @param version - the application uses this parameter to request the particular version of the BioAPI.
     * @result the initialized instance of the class that implements the Framework_Implemented interface.
     * @throws BioAPIException - if the application requests unknown version or the initialization of the instance fails due to an error.
     */
    public static Framework createInstance(java.lang.String version) throws BioAPIException
    {
    	if(version.equals("2.0J"))
    	{
    		return new org.bioapi.template.Framework_Implemented();
    	}
    	else
    	{
    		throw new BioAPIException(null, 0, "Not yet implemented.");
    	}
    }
}
