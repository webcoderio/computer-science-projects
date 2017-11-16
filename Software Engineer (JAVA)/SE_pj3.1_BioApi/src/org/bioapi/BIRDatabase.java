package org.bioapi;

/**
 * Represents an open database that is managed internally by the Biometric Service provider. The database contains of recods where each record includes BIR and the unique key that is the UUID.  
 *
 * @author Ashwin Mohan
 */
import java.util.UUID;

import org.bioapi.data.BIR;

public interface BIRDatabase 
{
    /**
     * Defines access modes to the database.
     */
 
    public enum Access
    {
        /**
         * The access mode that allows to retrieve records but probiits any modifications of the databse.
         */
        READ,
        /**
         *The access mode that allows bot the record retrieval and that modifications or the database.
         */
        READ_WRITE,
        /**
         *The access mode that allows t add and delete records from the databse but prohibits retrieval of records. 
         */
        WRITE;
    }
    /**
     * Represents records in BIR database. The record is the combination of BIR and unique key that identifies it in the database.
     * 
     * @author Brian Osmus
     * @status Implemented but untested.
     */
    public static interface Record
    {
        /**
         *
         * @return the unique key of the record.
         */
         public UUID getKey();
        /**
         * @return BIR associated with the record.
         */
         public BIR getValue();
         
    }
    /**
     * The Marker is the tool for sequental access to records in the BIR database. Along with the functionality provided by the Iterator this interface defines additional terminate() method which does explicit cleanup of physical resources that can be associated with database browsing (database cursors, etc.).  
     *
     * @author Brian Osmus
     * @status Implemented but untested.
     */
    public static interface Marker extends java.util.Iterator<BIRDatabase.Record>
    {
        /**
         * Destroys the marker and relases physical resourses with it (such as marker or cursor of the physical database).
         *
         * @throws BioAPIException if the associated BIR database has been closed or any other error occured while physical resources consumed by the Marker are released.
         */
        public void terminate() throws BioAPIException;
    } 
    /**
     * Creates a BIR object that is the reference to record in the open BIR database.
     * The returned BIR is analog of "BIR in the database" of the BioAPI and can be used as input to biometric operations.
     * The BIR is initially bound to the BSP. Access to any BIR properties other than
     * BDB and/or security block may result in that the BIR is temporary extracted from the database to BSP memory.
     * To permanently copy the BIR contained in the database int the BSP memory use the getSingleBIR() method instead.
     * 
     * @param key  identifies the record in the BIR database.
     * @return      the BIR object that represents the reference to the record in the BIR database.
     * @throws BioAPIException  if the database was closed.
     */
    public BIR newBIR(UUID key) throws BioAPIException;
    /**
     * Adds BIR to the database.
     *
     * @param bir specifies the BIR to add.
     * @return the UUID key for the newly added BIR.
     * @throws BioAPIException  if the database has been closed, if the database was opened in read-only
     *  mode or the addition of new BIR has failed due to another error.
     */
    public UUID storeBIR(BIR bir) throws BioAPIException;

   
   /**
    * Closes the database. External resources associated with the database are released. Any attempt
    * to se database object after the database is closed will result in error.
    *
    * @throws BioAPIException - if the operation fails due to an error.
    */
    public void close() throws BioAPIException;
    /**
     * Deletes the record from the database.
     * 
     * @param record identifies the record to delete.
     * @throws BioAPIException if the databas has been closed, if the database was opened in read-only mode, 
     * if the requested record was not found or if any ther error occurs when the record is deleted.
     */
    public void deleteBIR(BIRDatabase.Record record) throws BioAPIException;        
    
    /**
     *  Deletes the record from the database.
     *
     * @param key the UUID that identifies the record to delete.
     * @throws BioAPIException if the databas has been closed, if the database was opened in read-only mode, 
     * if the requested record was not found or if any ther error occurs when the record is deleted.
     */
    public void deleteBIR(UUID key) throws BioAPIException;
    
    /**
     * Enumerates records in the database. The iterator returned allows record deletion only when the
     * database as opened in read-write mode.
     *
     * @return the iterator that enumerates database records.
     * @throws BioAPIException if the database has been closed, if the database was opened in write-only 
     * mode or if any other error occurs.
     */        
    public BIRDatabase.Marker getBIRs() throws BioAPIException;
    
    /**
     * Enumerates records in the database. The iterator returned allows record deletion only when the
     * database as opened in read-write mode.
     *
     * @param start specifies the key of the starting record.
     * @return the iterator that enumerates database records.
     * @throws BioAPIException if the database has been closed, if the database was opened in write-only 
     * mode or if any other error occurs.
     */
    public BIRDatabase.Marker getBIRs(UUID start) throws BioAPIException;
    
    
    /**
     * Enumerates records in the database. The iterator returned allows record deletion only when the
     * database as opened in read-write mode.
     *
     * @param query specifies the criteria that is used to selct records.
     * @returns the iterator that enumerates database records.
     * @throws BioAPIException if the database has been closed, if the database was opened in write-only 
     * mode or if any other error occurs.   
     */
    public BIRDatabase.Marker getBIRs(Query<Record> query) throws BioAPIException;
   
    /**
     * Enumerates records in the database. The iterator returned allows record deletion only when the
     * database as opened in read-write mode.
     *
     * @param query specifies the criteria that is used to selct records.
     * @param start specifies the key of the starting record.
     * @returns the iterator that enumerates database records.
     * @throws BioAPIException if the database has been closed, if the database was opened in write-only 
     * mode or if any other error occurs. 
     */
    public BIRDatabase.Marker getBIRs(UUID start, Query<BIRDatabase.Record> query) throws BioAPIException;
   
    /**
     * Retrieves the record from the databse.
     * @param key the UUID that identifies the record to retrieve.
     * @returns the requested record.
     * @throws BioAPIException if the databse has been closed, if the database was opened in write-only
     * mode, if the requested record was not found, or if any other error occurs when the record is retrieved.
     */
    public BIRDatabase.Record getSingleBIR(UUID key) throws BioAPIException;
    
    
}

