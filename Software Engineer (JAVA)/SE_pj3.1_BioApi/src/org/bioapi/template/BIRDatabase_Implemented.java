/*
 * BIRDatabase_Implemented.java
 *
 * Created on April 26, 2007, 7:53 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.bioapi.template;

/**
 *
 * @author Ashwin Mohan
 */

//bioapi interface imports
import org.bioapi.BIRDatabase;
import org.bioapi.data.BIR;
//import org.bioapi.data.IdentifyPopulation;
import org.bioapi.BioAPIException;
import org.bioapi.Query;

// utility class imports
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.UUID;





public class BIRDatabase_Implemented implements org.bioapi.BIRDatabase
{
    private  Hashtable<UUID, BIR> populationTable =null;
    String name;
    Access access;


    class record implements org.bioapi.BIRDatabase.Record{

         UUID key;
         BIR value;
         record()
         {

         }

        public void setKey(UUID key){ this.key=key;}
        public void setValue(BIR value){this.value=value;}

        public UUID getKey(){return key;}
        public BIR getValue(){return value;}
        
    };



    public class marker implements org.bioapi.BIRDatabase.Marker{

        
        Enumeration<UUID> e=null;
        Hashtable<UUID, BIR> h;
         
         public marker(Hashtable<UUID, BIR> h)
         {
             this.h=h;
             e=this.h.keys();
         }
         
         public marker(UUID start,Hashtable<UUID, BIR> h){
            e=h.keys();
             while(hasNext()){
                 if(e.nextElement().equals(start)){
                  break;
                 }
             }

         }

         public record next(){
            record  r= new record();
            UUID key=e.nextElement();
            r.setKey(key);
            r.setValue(h.get(key));
            return r;
        }
        public void remove(){
            h.remove(e.nextElement());
        }
        public void terminate(){
            e=null;
        }
        public boolean hasNext(){
            return e.hasMoreElements();
        }
    };


	
    /** Creates a new instance of BIRDatabase_Implemented */
    public BIRDatabase_Implemented(String _name)
    {
       populationTable = new Hashtable<UUID, BIR>();
       name=_name;
    }

   
    public BIRDatabase_Implemented(IdentifyPopulation_Implemented idp)throws BioAPIException
    {
        populationTable = new Hashtable<UUID, BIR>();
        Enumeration<BIR> e=idp.getMembers();
        while(e.hasMoreElements())
        {
            UUID key= UUID.randomUUID();
            if(!populationTable.containsKey(key))
            {
            populationTable.put(key, e.nextElement());
            }

        }
    }
    
    public BIR newBIR(UUID key) throws BioAPIException
    {
        return populationTable.get(key);
    }

    public UUID storeBIR(BIR bir) throws BioAPIException
    {
     
        if(!populationTable.containsKey(bir.getIndex()))
        {
            populationTable.put(bir.getIndex(), bir);
        }
        return bir.getIndex();
      
    }
   

     public void open( Access _access)
    {
       populationTable = new Hashtable<UUID, BIR>();
       access=_access;
    }


    public void close() throws BioAPIException
    {
      populationTable= null;
    }

    public Boolean isName(String _name)throws BioAPIException
    {
        return (name.equals(_name));
    }

    public void deleteBIR(BIRDatabase.Record record) throws BioAPIException
    {
        populationTable.remove(record.getKey());
    }

    public void deleteBIR(UUID key) throws BioAPIException
    {
         populationTable.remove(key);
    }

    public BIRDatabase.Marker getBIRs() throws BioAPIException
    {
        return new marker(populationTable);
    }

    public BIRDatabase.Marker getBIRs(UUID start) throws BioAPIException
    {

        return new marker(start,populationTable);
    }

    public BIRDatabase.Marker getBIRs(org.bioapi.Query<org.bioapi.BIRDatabase.Record> query) throws BioAPIException
    {
        Hashtable<UUID, BIR> temp =new Hashtable<UUID, BIR>();
        marker m = new marker(populationTable);
        if(m.hasNext())
        {
            record  r=m.next();
            if(query.select(r)){
            temp.put(r.getKey(),r.getValue());
            }

        }

        return new marker(temp);

    }

    public BIRDatabase.Marker getBIRs(UUID start, Query<BIRDatabase.Record> query) throws BioAPIException
    {
        Hashtable<UUID, BIR> temp =new Hashtable<UUID, BIR>();
        marker m = new marker(start,populationTable);
        if(m.hasNext())
        {
            record  r=m.next();
            if(query.select(r)){
            temp.put(r.getKey(),r.getValue());
            }

        }

        return new marker(temp);
    }
    
    public BIRDatabase.Record getSingleBIR(UUID key) throws BioAPIException
    {
        record  r= new record();
        r.setKey(key);
        r.setValue(populationTable.get(key));
        return r;
    }

    public int size(){

       return populationTable.size();
    }
    
};


