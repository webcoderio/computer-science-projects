package PasswordAPI_Implemented;

import org.bioapi.BioAPIException;
import org.bioapi.BIRDatabase.Access;
import org.bioapi.template.BIRDatabase_Implemented;
import java.sql.*;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bioapi.BIRDatabase;
import org.bioapi.template.Archive_Implemented;

public class PasswordArchiveUnit extends Archive_Implemented
{
    BIRDatabase_Implemented _birdatabase;
    PasswordBIR _bir;
    String DBname;
    Connection con = null;
    Statement stmt;
    ResultSet rs;
     UUID [] entries;


	public PasswordArchiveUnit()
	{
        try{
			Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
            con = DriverManager.getConnection("jdbc:oracle:thin:@149.4.211.237:1521:sulfur",
                         "sng", "sng2");
            _birdatabase= new BIRDatabase_Implemented("test");
		}
        catch (Exception ex) {
                Logger.getLogger(PasswordArchiveUnit.class.getName()).log(Level.SEVERE, null, ex);
            }
    } // PasswordArchiveUnit()
		
		
    @Override
    public BIRDatabase openDatabase(String name,Access access) throws BioAPIException {

        try{
             int count=0;

             DBname = name;
      if(!con.isClosed()){
        System.out.println("Successfully connected to Oracle server...");
        
        // sng: insert tables for initialize
        /*
        stmt= con.createStatement();
        rs=stmt.executeQuery("CREATE TABLE biopassword( name varchar2(30) primary key, password varchar2(30) )");
        */
        
        // do the rest
        stmt= con.createStatement();
            rs=stmt.executeQuery("SELECT * from "+DBname);

            while(rs.next())
            {
             count ++;

            }
           rs=stmt.executeQuery("SELECT * from "+DBname);
            entries = new UUID[count];
            count=0;

            while(rs.next())
            {
                entries[count++]=  UUID.randomUUID();
                _bir= new PasswordBIR(rs.getString("Name"), rs.getString("Password"),
                         entries[count-1] );
                  _birdatabase.storeBIR(_bir);

            }

      }

      }
         catch (Exception ex) {
                Logger.getLogger(PasswordArchiveUnit.class.getName()).log(Level.SEVERE, null, ex);
            }

         return _birdatabase;
    }

    @Override
    public void createDatabase(String name) throws BioAPIException {
    try{
      DBname=name;
      
      if(!con.isClosed()){
        System.out.println("Successfully connected to MySQL server...");
        stmt= con.createStatement();
        stmt.executeUpdate("CREATE DATABASE "+ DBname);
      }

      }
         catch(Exception e) {
      System.err.println("Exception: " + e.getMessage());
         }
    }

    public boolean insertRecord(String name, String password)
    {
        try{
           if(!con.isClosed()){
            System.out.println("Successfully connected to MySQL server...");
            stmt= con.createStatement();
            stmt.executeUpdate("INSERT INTO "+DBname+ " VALUES ( '"+name+"' , '"+password+"' )");
        }
         return true;
      }
         catch(Exception e) {
            System.err.println("Exception: " + e.getMessage());
             return false;
         }

    }

    
    public UUID[] getEntries(){
     return entries;
    }

}
