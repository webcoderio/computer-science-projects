package org.bioapi;

import java.util.*;

import org.bioapi.AttachSession;
import org.bioapi.BioAPIException;
import org.bioapi.Query;
import org.bioapi.Unit;
import org.bioapi.data.BFPSchema;
import org.bioapi.data.UnitSchema;

/**
 * Represents the Biometric Service Provider. The BSP is the factory of Session objects that provide access to biometric operations. 
 * 
 * @author Ashwin Mohan,Ross Hinkley
 */
public interface BSP {
    
    public static interface UnitSet
    {
        /**
         * The default unit. The BSP should throw an exception if no default unit is available (explicit "don't care" option) 
         **/
        public static final int DONT_CARE = 0;
        
        /**
         * The default unit. The BSP should throw an exception if no default unit is available (explicit "don't care" option) 
         **/
        public static final int DONT_USE = -1;
      
        /**
        * Adds a unit identified by the UnitSchema to the set. If the category of the unit conflicts with some entry previously defined by means of addUnit() or exclude() an exception is thrown. 
        * 
        * @param unitID - along with the 'unitCategory' identifies the unit within the BSP 
        * @param unitCategory - along with the 'unitID' identifies the unit within the BSP 
        * @throws BioAPIException - if the category of the unit conflicts with some previous entry. 
        **/
        public void addUnit(int unitID, Unit.Category unitCategory) throws BioAPIException;

        
        /**
        * Indicates that BSP should choose the unit of the particular category for the attach session. This is equivalent to the explicit "don't care" option in the BioAPI. If the category of the unit conflicts with some entry previously defined by means of addUnit() or exclude() an exception is thrown. The exception is also thrown if the 'unitID' specifies special ID (DONT_CARE or DONT_USE).
        * 
        * @param unitSchema - identifies the unit 
        * @throws BioAPIException - if the category of the unit conflicts with some previous entry. 
        **/
        public void addUnit(UnitSchema unitSchema) throws BioAPIException; 
        
        /**
        * Indicates that BSP should not use the unit of the particular category for the attach session. This is equivalent to the "don't use" option in the BioAPI. If the category of the unit conflicts with some entry previously defined by means of addUnit() or exclude() an exception is thrown.
        *    
        * @param unitCategory - identifies the unit category 
        * @throws BioAPIException - if the category of the unit conflicts with some previous entry.   
        **/
        public void exclude(Unit.Category unitCategory) throws BioAPIException;
        
        /**
        * Indicates if the UnitSet includes any unit of the specified category. This method returns 'false' for implicit "don't care" units and for excluded units. 
        * @param unitCategory - specifies the category of unit 
        * @return 'true' if the unit of this category was added by means of any of addUnit() methods(). 
        **/
        public boolean contains(Unit.Category unitCategory) throws BioAPIException;

        /**
        *Returns the identification (unit ID) for the unit of the specified category: 
        * 
        * <ol>
        * <li>1. for units added by means of addUnit(UnitSchema) and addUnit(int, Unit.Category) the method returns actual ID of the unit.
        * <li>2. for units added by means of addUnit(Unit.Category) the method returns the DONT_CARE special ID.
        * <li>3. for categories excluded by means of exclude() the method returns the special DONT_USE ID.
        * <li>4. for other categories the method returns the DONT_CARE special ID (the implicit "don't care" option). One can use the contains() method to distinguish between the implicit "don't care" units and explicit "dont'care" units.  
        * </ol>
        * @param unitCategory - specifies the category for which the ID is returned. 
        * @returns the unit ID or the special ID indicating selection for the particular category. 
        **/
        public int getUnitID(Unit.Category unitCategory) throws BioAPIException; 

    }
    
    /**
     * Enumerates BFPs that are supported by this BSP. 
     * 
     * @return an enumeration of all BFPs supported by this BSP.
     * @throws BioAPIException if the method is called after the BSP is explicitly destroyed by the call to the terminate() method or if any other error occurs while available units are enumerated. 
     **/
    Enumeration<BFPSchema> enumBFPs() throws BioAPIException;
    
    /**
     * Enumerates BFPs that are supported by this BSP but returns only BFPs which properties match the specific criteria. 
     *
     * @param the implementation of the Query interface that is provided by the caller to filter available units. 
     * @return the implementation of the Query interface that is provided by the caller to filter available units. 
     * @throws BioAPIException - if the method is called after the BSP is explicitly destroyed by the call to the terminate() method or if any other error occurs while available units are enumerated. 
     **/
    Enumeration<BFPSchema> enumBFPs(Query<BFPSchema> query) throws BioAPIException;
    
    
    /**
     * Enumerates units available for the attach session.
     *
     * @return enumeration of UnitSchema where each element describes properties of the unit available for the attach session. 
     * @throws BioAPIException - if the method is called after the BSP is explicitly destroyed by the call to the terminate() method or if any other error occurs while available units are enumerated. 
     **/
    java.util.Enumeration <UnitSchema> enumUnits() throws BioAPIException;
    
    /**
     * Enumerates units available for the attach session but returns only units which properties match the specific criteria.
     *
     * @param query - the implementation of the Query interface that is provided by the caller to filter available units.
     * @return enumeration of UnitSchema where each element describes properties of the unit available for the attach session. 
     * @throws BioAPIException - if the method is called after the BSP is explicitly destroyed by the call to the terminate() method or if any other error occurs while available units are enumerated. 
     **/
    java.util.Enumeration <UnitSchema> enumUnits(Query<UnitSchema> query) throws BioAPIException;

    /**
     * Creates new attach session to the BSP. The BSP selects default sensor, processing, matching and archive units if available. If, for any category, a default unit is not available no error is reported.
     *
     * @return the Session object with default sensor, processing, matching and archive units attached. 
     * @throws BioAPIException - if the method is called after the BSP is explicitly destroyed by the call to the terminate() method or if any other error occurs while available units are enumerated.    
    **/
    AttachSession newAttachSession() throws BioAPIException; 

     /**
     * Creates new attach session to the BSP. The application explicitly specifies the list of units to attach. The selection of units occurs as follows: 
     * Enumerates units available for the attach session but returns only units which properties match the specific criteria.
     * <ol>
     * <li>for categories defined in the UnitSet by means of addUnit(UnitSchema) or addUnit(int, UnitCategory) the unit identified by the explicit unit ID is used. If the ID refers to non-existing unit or to unit that is not available an exception is thrown.
     * <li>for categories defined in the UnitSet by means of addUnit(Unit.Category) the BSP attempts to select the default unit. If there are no any available units of requested category an exception is thrown (explicit "don't care" option). 
     * <li>units of remaining categories that are explicitly excluded from the UnitSet by means of exclude(Unit.Category) are not attached. Otherwise BSP attempts to attach a default unit of the category but does not report an error is no units of this category are available (implicit "don't care" option). 
     * </ol> 
     *
     * @param units - the UnitSet that specifies units for the attach session. 
     * @return the AttachSession object that represents attachment to selected units. 
     * @throws BioAPIException - if the method is called after the BSP is explicitly destroyed by the call to the terminate() method or if any other error occurs while available units are enumerated. 
     **/
    AttachSession newAttachSession(BSP.UnitSet units) throws BioAPIException; 

    /**
     * Instantiates a UnitSet object. 
     *
     * @return an instance of the UnitSet that can be used to specify which units the attach session should use.  terminate() method or if any other error occurs while available units are enumerated. 
     **/
    BSP.UnitSet newUnitSet();
    

    void terminate(); 

    
    
    
}
