package org.bioapi;

import java.util.Enumeration;

import org.bioapi.BioAPIException;
import org.bioapi.Query;
import org.bioapi.BSP;
import org.bioapi.ComponentRegistry;
import org.bioapi.EventHandler;
import org.bioapi.data.BFPSchema;
import org.bioapi.data.BSPSchema;
import org.bioapi.data.DataFactory;
import org.bioapi.data.FrameworkSchema;

/**
 * Represents the biometric system. The biometric system is the hierarchical assembly which root node is the Framework component. The Framework controls biometric service providers (BSPs) that drive concrete biometric sensor units and implement various biometric technologies. To provide necessary services to the Framework BSPs use biometric function provider modules that are libraries implementing biometric algorithms and/or code that manages sensors hardware. Along with BSPs, another subordinate of the Framework is component registry that stores the information about BSPs and BFPs that areavailable in the biometric system.
 *
 * @author Ashwin Mohan
 */
public interface Framework 
{
  /**
   * Retrieves the information about the biometric system. 
   * @Deprecated This method is being replaced by the enumFrameworks().
   * @return the FrameworkSchema object that identifies the implementation of the BioAPI framework. 
   * @throws BioAPIException - if the method is called after the framework was terminated via its terminate() method.
   */
  public FrameworkSchema getFrameworkSchema() throws BioAPIException;
  
  /**
   * Enumerates framework records.
   * @return enumration of framework records in the component registry.
   * @throws BioAPIException - if Enumeration of records fails due to an error.
   */
  public java.util.Enumeration<Framework> enumFrameworks() throws BioAPIException;
  
  /**
   * Enumerates framework records
   * @param query - the filter that is provided by the application and which selects records matching the particular criteria. See the documentation for the enumerateBSPs(Query<BSPSchema>) for an example. 
   * @return enumration of framework records in the component registry.
   * @throws BioAPIException - if Enumeration of records fails due to an error.
   */
  public java.util.Enumeration<Framework> enumFrameworks(Query<Framework> query) throws BioAPIException;
  
  /**
   * Enumerates biometric service providers that are available in the biometric system. 
   * @return Enumeration of BSPSchema where each element describes properties of the BSP available in the biometric system. 
   * @throws BioAPIException - if the method is called after the framework is terminated by the call to the terminate() method or if any other error occurs while available BSPs are enumerated. 
   */
  public java.util.Enumeration<BSPSchema> enumBSPs() throws BioAPIException;
  
  /**
   * Enumerates biometric service providers that are available in the biometric system but returns only BSPs which properties match the specific criteria.
   * @param query - the implementation of the Query interface that is provided by the caller to filter available BSPs.
   * @return Enumeration of BSPSchema where each element describes properties of the BSP available in the biometric system and matches the criteria specified by the query argument.
   * @throws BioAPIException - if the method is called after the framework is terminated by the call to theterminate() method or if any other error occurs while available BSPs are enumerated.
   */
  public java.util.Enumeration<BSPSchema> enumBSPs(Query<BSPSchema> query)  throws BioAPIException; 
  
  /**
   * Enumerates biometric function providers that are available in the biometric system.
   * @return Enumeration of BFPProperties where each element describes properties of the BFP available in the biometric system.
   * @throws BioAPIException - if the method is called after the framework is terminated by the call to the terminate() method or if any other error occurs while available BFPs are enumerated.
   */
  public Enumeration<BFPSchema> enumBFPs() throws BioAPIException;
  /**
   * Enumerates biometric function providers that are available in the biometric system but returns only BFPs which properties match the specific criteria
   * @param query - the implementation of the Query interface that is provided by the caller to filter available BFPs.
   * @return Enumeration of BFPProperties where each element describes properties of the BFP available in the biometric system and matches the criteria specified by the query argument.
   * @throws BioAPIException - if the method is called after the framework is terminated by the call to the terminate() method or if any other error occurs while available BFPs are enumerated.
   */
  public java.util.Enumeration<BFPSchema> enumBFPs(Query<BFPSchema> query) throws BioAPIException;
  
  /**
   * Activates the BSP and makes it available for use by the biometric application. 
   * <P>
   * Note: due to asynchronous nature of events the event handler can be called before the getBSP() will return to the caller.
   * @param bspID - identifies the BSP to activate. 
   * @param listener - the application implements the EventListener to receive notification about events. Event indicate that the state of the unit managed by the BSP has changed.
   * @return the BSP object that provides access to biometric operations.
   * @throws BioAPIException - if the method is called after the framework is terminated by the call to the terminate() method, if the bspID refers to unknown BSP or if any other error occurs while the BSP is activated.
   */
  public BSP loadBSP(java.util.UUID bspID, EventHandler listener) throws BioAPIException;
  
  /**
   * Retrieves the component registry.
   * @return the object that can be used to add, update or delete records about available BSPs and BFPs in the component registry. 
   * @throws BioAPIException - if the method is called after the framework is terminated by the call to the terminate() method or if any other error occurs while the component registry manager is instantiated.
   */
  public ComponentRegistry getComponentRegistry() throws BioAPIException;
  
  /**
   * Retrieves the data factory for BioAPI data types.
   * @return the object that can be used by the application to instantiate BioAPI data types.
   * @throws BioAPIException - if the method is called after the framework is terminated by the call to theterminate() method or if any other error occurs while the data factory object is created. 
   */
  public DataFactory getDataFactory() throws BioAPIException;
  
  /**
   * Terminates all subordinate objects created via the getInstaller(), getDataFactory() and getBSP() methods and shutdowns the biometric system releasing external resources associated with it.
   * @throws BioAPIException - if the shutdown failed due to an error or if the terminate() method has been called already.
   */
  public void terminate() throws BioAPIException;
}
