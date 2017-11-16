package org.bioapi;

import org.bioapi.BioAPIException;
import org.bioapi.data.BFPSchema;
import org.bioapi.data.BSPSchema;

/**
 * Represents the interface to the component registry. The Installer adds deletes of modifies BSP and BFP records in the component registry managed by the framework. 
 * 
 * @author Ashwin Mohan
 */
public interface ComponentRegistry {
    
    public enum Action
    {
        /**
         * This action adds new record to the component registry.
         */
        INSTALL,
        /**
         * This action modifies an existing record in the component registry.
         */
        REFRESH,
        /**
         * This action deletes record from the component registry. 
         */
        UNINSTALL;
    }
    
    /**
    * Adds, modifies or deletes BSP record from the component registry. BSP record in the component registry is identified by its UUID attribute that serves as a primary key. If the command is INSTALL the record specified by the 'bsp' parameter is added to the component registry. If the record with identical UUID already exists an exception is thrown.1. if the command is REFRESH the 'bsp' record overwrites the existing record with matching UUID. If the matching record does not exist an exception is thrown.2. if the command is UNINSTALL the record with the UUID specified by the UUID attribute of the 'bsp' parameter is deleted from the component registry. If such record does not exist an exception is thrown. 3. 
    * @param command - specifies management action.
    * @param bsp - specifies the BSP record.
    * @throws BioAPIException - if the management action failed due to an error.
    **/
    public void install(ComponentRegistry.Action command, BSPSchema bsp) throws BioAPIException;
    
    /**
    * Adds, modifies or deletes BFP record from the component registry. BFP record in the component registry is identified by its UUID attribute that serves as a primary key. If the command is INSTALL the record specified by the 'bfp' parameter is added to the component registry. If the record with identical UUID already exists an exception is thrown.1. if the command is REFRESH the 'bfp' record overwrites the existing record with matching UUID. If the matching record does not exist an exception is thrown.2. if the command is UNINSTALL the record with the UUID specified by the UUID attribute of the 'bfp' parameter is deleted from the component registry. If such record does not exist an exception is thrown. 3. 
    * @param command - specifies management action.
    * @param bfp - specifies the BFP record.
    * @throws BioAPIException - if the management action failed due to an error.
    **/
    public void install(ComponentRegistry.Action command, BFPSchema bfp) throws BioAPIException;

}
