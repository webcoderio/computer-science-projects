package org.bioapi.template;

import java.util.Enumeration;
import java.util.UUID;
import java.util.Vector;

import org.bioapi.*;
import org.bioapi.data.*;

/**
 *
 * @author Ashwin Mohan
 */

public class Framework_Implemented implements org.bioapi.Framework, org.bioapi.data.FrameworkSchema
{
	private final static ComponentRegistry_Implemented componentRegistry = new org.bioapi.template.ComponentRegistry_Implemented();
    private Vector<BFPSchema> v=null;
    private Vector<BSPSchema> vnew=null;
    private static Vector<Framework>fs= null;
    private Vector<Framework>fsnew= null;
    private BFPSchema bfpschema;
    private BSPSchema bspschema;
    private Framework framework;
   
	UUID frameworkUuid = new UUID(0,1000);
	String fwDescription = "Sample Descriptison";
	byte[] fwProperty = {0,1,2,3,4};
	UUID fwPropertyId = new UUID(1,1001);
	String path = "sample path";
	String productVersion = "some product version";
	String specVersion = "BIOAPI2.0 - it's javatastic!";
	String vendor = "CERIAS";

    Framework_Implemented()
    {
       // fs.add(this);

    }

	public Enumeration<BFPSchema> enumBFPs() throws BioAPIException
	{
		return ComponentRegistry_Implemented.bfpTable.elements();
	}

	public Enumeration<BFPSchema> enumBFPs(Query<BFPSchema> query)
	throws BioAPIException
	{
        while(ComponentRegistry_Implemented.bfpTable.elements().hasMoreElements())
        {
            bfpschema=ComponentRegistry_Implemented.bfpTable.elements().nextElement();
        if(query.select(bfpschema))
        {
            v.add(bfpschema);
        }
          
        }
          return v.elements();
	}

	public Enumeration<BSPSchema> enumBSPs() throws BioAPIException
	{
		return ComponentRegistry_Implemented.bspTable.elements();
	}

	public Enumeration<BSPSchema> enumBSPs(Query<BSPSchema> query)
	throws BioAPIException
	{
		while(ComponentRegistry_Implemented.bspTable.elements().hasMoreElements())
        {
            bspschema=ComponentRegistry_Implemented.bspTable.elements().nextElement();
        if(query.select(bspschema))
        {
            vnew.add(bspschema);
        }

        }
          return vnew.elements();
	}

	public Enumeration<org.bioapi.Framework> enumFrameworks() throws BioAPIException
	{
		return fs.elements();
	}

	public Enumeration<org.bioapi.Framework> enumFrameworks(
			Query<org.bioapi.Framework> query) throws BioAPIException
	{
        Enumeration<org.bioapi.Framework> e= fs.elements();
		while(e.hasMoreElements())
        {
          framework= e.nextElement();
        if(query.select(framework))
        {
            fsnew.add(framework);
        }

        }
          return fsnew.elements();
	}

	public ComponentRegistry getComponentRegistry() throws BioAPIException
	{
		return componentRegistry;
	}

	public DataFactory getDataFactory() throws BioAPIException
	{
		//return org.bioapi.template.DataFactory_Implemented.datafactory;
        return null;
	}

	public FrameworkSchema getFrameworkSchema() throws BioAPIException
	{
		return this;
	}

	public BSP loadBSP(UUID bspID, EventHandler listener)
	throws BioAPIException
	{
        return null;
	}

	public void terminate() throws BioAPIException
	{
	}

	public UUID getFrameworkUuid()
	{
		
		return frameworkUuid;
	}

	public String getFwDescription()
	{
		
		return fwDescription;
	}

	public byte[] getFwProperty()
	{
		
		return fwProperty;
	}

	public UUID getFwPropertyId()
	{
		
		return fwPropertyId;
	}

	public String getPath()
	{
		
		return path;
	}

	public String getProductVersion()
	{
		
		return productVersion;
	}

	public String getSpecVersion()
	{
		
		return specVersion;
	}

	public String getVendor()
	{
		
		return vendor;
	}

}
