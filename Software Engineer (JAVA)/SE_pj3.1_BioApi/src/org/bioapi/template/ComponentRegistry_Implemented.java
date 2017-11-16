package org.bioapi.template;

import java.util.Hashtable;
import java.util.UUID;

import org.bioapi.BioAPIException;
import org.bioapi.data.BFPSchema;
import org.bioapi.data.BSPSchema;
/**
 *
 * @author Ashwin Mohan
 */

public class ComponentRegistry_Implemented implements org.bioapi.ComponentRegistry
{
	protected  final static Hashtable<UUID, BSPSchema> bspTable = new Hashtable<UUID, BSPSchema>();
	protected  final static Hashtable<UUID, BFPSchema> bfpTable = new Hashtable<UUID, BFPSchema>();
	
	public void install(Action command, BSPSchema bsp) throws BioAPIException
	{
		switch(command)
		{
			case INSTALL : bspTable.put(bsp.getBSPUuid(), bsp); 
				break;
			case REFRESH : bsp = bspTable.get(bsp.getBSPUuid()); 
				break;
			case UNINSTALL : bspTable.remove(bsp.getBSPUuid()); 
				break;
			default : throw new BioAPIException(BioAPIException.Facility.FRAMEWORK, BioAPIException.BIOAPI_FRAMEWORK_ERROR, "Invalid Component Registry Action");
		}
	}
	
	public void install(Action command, BFPSchema bfp) throws BioAPIException
	{
		switch(command)
		{
			case INSTALL : bfpTable.put(bfp.getBFPPropertyID(), bfp); 
				break;
			case REFRESH : bfp = bfpTable.get(bfp.getBFPCategory()); 
				break;
			case UNINSTALL : bfpTable.remove(bfp.getBFPCategory());
				break;
			default : throw new BioAPIException(BioAPIException.Facility.FRAMEWORK, BioAPIException.BIOAPI_FRAMEWORK_ERROR, "Invalid Component Registry Action");
		}
	}
}
