import java.sql.*;

public class ora_DBTest {

   public ora_DBTest (){
   }

    public int testConnection (int hr_offset) {
        @SuppressWarnings("unused")
		String jdbcDriver = "oracle.jdbc.driver.OracleDriver";
        //sng:local dns host|String dbURL1 = "jdbc:oracle:thin:@bonnet19.cs.qc.edu:1521:dminor";
        //String dbURL1 = "jdbc:oracle:thin:@5.154.255.136:1521:sulfur";
        String dbURL1 = "jdbc:oracle:thin:@149.4.211.237:1521:sulfur";
        String userName1 = "sng";
        @SuppressWarnings("unused")
		String userPassword1 = "sng"; // default
        String userPassword2 = "sng2"; // new one
        String oracle_driver = "oracle.jdbc.driver.OracleDriver";
           
        Connection conn;
        PreparedStatement pstmt;
        ResultSet rs;

	    int flag = 0;
	    String newTime;
      

        try
        {
         Class.forName(oracle_driver);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        try
        {
            conn = DriverManager.getConnection(dbURL1, userName1, userPassword2);
            String stmtQuery = "select sysdate + " + hr_offset + " from dual";
            pstmt = conn.prepareStatement(stmtQuery);
            // pstmt.setString(1,usrname);
            rs = pstmt.executeQuery();
            if (rs.next())
            {
              newTime = rs.getString(1); 
              System.out.println(hr_offset + " hour(s) ahead of the system clock of Oracle at bonnet19 is:" + newTime);
            }
            rs.close();
            pstmt.close();
            
            
            // Change password statement
           
            Statement statement = conn.createStatement();
            statement.executeUpdate("alter user "+userName1+" identified by "+userPassword2);
            
            
            try
            {
              conn.close();
            } 
            catch (SQLException e) {};
            
        } // try
        catch (SQLException e)
        {
          System.out.println(e.getMessage());
          flag = -1;
        } // catch
        
      return flag;
    } // test connection
 
    
   public static void main(String[] args)
   {
      try
      {
         if (args.length != 1) {
            System.out.println("Usage: java -jar Ora_DBTest.jar <number_of_hr_offset>");
            System.out.println("Success returns errorlevel 0. Error return greater than zero.");
            System.exit(1);
         } // if

           /* Print a copyright. */
           System.out.println("Example for Oracle DB connection via Java");
           System.out.println("Copyright: Bon Sy");
           System.out.println("Free to use this at your own risk!");
           
           // create instance
           ora_DBTest DBConnect_instance = new ora_DBTest();
           
           // execute method testConnection()
           if (DBConnect_instance.testConnection(Integer.parseInt(args[0])) == 0) {
               System.out.println("Successful Completion");
           } else {
               System.out.println("Oracle DB connection fail");
           }
           
      } // try 
      catch (Exception e){
         // probably error in input
         System.out.println("Hmmm... Looks like input error ....");
      } // catch
      
      
  } // main
   
   
} // class ora_DBTest