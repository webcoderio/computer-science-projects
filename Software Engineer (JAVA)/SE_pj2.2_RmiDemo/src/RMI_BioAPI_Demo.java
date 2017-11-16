import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.PrintStream;
import java.sql.*;
//import java.rmi.server.UnicastRemoteObject;
import java.rmi.Naming;
import java.rmi.RemoteException;
//import java.rmi.Naming;


public class RMI_BioAPI_Demo {
	
	private String socket_listener_ip = "localhost";
// socket_listener_ip of socket server at DSP machine
////	private String socket_listener_ip = "<whatever IP it should be>";
	
	
	//*** This section is for multi-threading initialization
    private final class RequestThread extends Thread {
        private String option;
        private String AsteriskJava_IP;
        private String Service_UID;
        private String remote_AsteriskSrcFilename;
        private String socket_listener_ip;
        private int socket_port;
        private String local_fileName;

        RequestThread(final String option, final String AsteriskJava_IP, final String Service_UID, final String remote_AsteriskSrcFilename, final String socket_listener_ip, final int socket_port, final String local_fileName
) {     	
        	
            this.option = option;
            this.AsteriskJava_IP = AsteriskJava_IP;
            this.Service_UID = Service_UID;
            this.remote_AsteriskSrcFilename = remote_AsteriskSrcFilename;
            this.socket_listener_ip = socket_listener_ip;
            this.socket_port = socket_port;
            this.local_fileName = local_fileName;
            this.start();
        }

        public void run() {

            if (option.equals("socket")) {
                    try {
                            initialize_socket(socket_port);

                    } catch (Exception e) {
                            System.out.println("Error on initializing socket server");
                    }
            }
            if (option.equals("AsteriskJava")) {
        		new RMI_BioAPI_AsteriskJava_Client(AsteriskJava_IP, Service_UID, remote_AsteriskSrcFilename, socket_listener_ip, socket_port, local_fileName);
        		//RPC_FileRead(String Service_UID, String srcFileName, String socket_ip, int socket_port, String remote_fileName)
            }
            // Runs the above operations simultaneously as multi-threads
        	
        	try {
        		RMI_BioAPI_AsteriskJava_Interface service = (RMI_BioAPI_AsteriskJava_Interface) 
    				Naming.lookup("rmi://" + socket_listener_ip + "/RMI_BioAPI_AsteriskJava");
        		    		
        		service.RPC_FileRead(Service_UID, local_fileName, socket_listener_ip, socket_port, remote_AsteriskSrcFilename);
        	} catch (Exception e) {
                    System.out.println(e.getMessage());
                    System.out.println("RMI_BioAPI_AsteriskJava Naming lookup fails!");
        	}
        	
        }

}

    @SuppressWarnings("unused")
	private PrintWriter pw, pwt;
	private BufferedReader br;	
	private Socket socket;
	private ServerSocket serverSocket = null;
	// private int port = 1688;
	
	public void initialize_socket_stream_buffer() {
        try{
        	socket=serverSocket.accept();
        	System.out.println("connected");
        	br=new  BufferedReader(new InputStreamReader(socket.getInputStream()));
        	pw = new PrintWriter(socket.getOutputStream(), true);
        }catch(IOException e) {e.printStackTrace();}
	}
	
	public void socket_stream_buffer_close () {
    	pw.close();
    	try {
    		br.close();
    	} catch(IOException e) {e.printStackTrace();}
	}
    
	public void socket_listener () throws IOException {
		
		System.out.println("t1");
		
		// Stream to write file
	    System.out.println("Getting File...");
	    String FileNameString = "demo2.txt"; // need to change to local_fileName variable later
	    String file_content = "";
		FileOutputStream fout;
		try
		{
		    // Open an output stream
		    fout = new FileOutputStream (FileNameString);
		    // Print a line of text
		    while ((file_content = br.readLine()) != null) {
		    	new PrintStream(fout).println(file_content);
		    }
		    new PrintStream(fout).println(file_content);
		    // Close our output stream
		    fout.close();
		}
		// Catches any error conditions
		catch (IOException e)
		{
			System.err.println ("Unable to write to file");
			System.exit(-1);
		}
	    System.out.println("File Successful Reccieved from server to client");
	    System.out.println("");
	    
	    // File content correction
	    FileUtil util = new FileUtil();
	    util.removeLineFromFile("demo2.txt", "StartXfer");
	    util.removeLineFromFile("demo2.txt", "demo2.txt");
	    util.removeLineFromFile("demo2.txt", "Done");
	    util.removeLineFromFile("demo2.txt", "null");
	    
	    
	    // ######################## DATABASE ##############################
		@SuppressWarnings("unused")
		String jdbcDriver = "oracle.jdbc.driver.OracleDriver";
        //sng:local dns host|String dbURL1 = "jdbc:oracle:thin:@bonnet19.cs.qc.edu:1521:dminor";
		String dbURL1 = "jdbc:oracle:thin:@5.154.255.136:1521:sulfur";
		//String dbURL1 = "jdbc:oracle:thin:@149.4.211.237:1521:sulfur";
        String userName1 = "sng";
		String userPassword1 = "sng2";
        String oracle_driver = "oracle.jdbc.driver.OracleDriver";
           
        Connection conn;
        PreparedStatement pstmt;
        ResultSet rs;
      
        System.out.println("Operating DB...");
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
        	String hr_offset ="1"; // as a parameter
    	    String newTime ="";
            conn = DriverManager.getConnection(dbURL1, userName1, userPassword1);
            String stmtQuery = "select sysdate + " + hr_offset + " from dual";
            pstmt = conn.prepareStatement(stmtQuery);
            rs = pstmt.executeQuery();
            if (rs.next())
            {
              newTime = rs.getString(1); 
              System.out.println(hr_offset + " hour(s) ahead of the system clock of Oracle at bonnet19 is:" + newTime);
            }
            rs.close();
            pstmt.close();
            
            
            // SQL Statements
            
            Statement statement = conn.createStatement();
            // Drop table
            try {
            System.out.println("Drop table if exits...");
            String sqls1 = "DROP TABLE MYDATA";
            statement.executeUpdate(sqls1);
            }
            catch (SQLException e) {
            	System.out.print(e.getMessage());
            }
            // Create table
            try {
            System.out.println("Create table...");
            String sqls2 = "CREATE TABLE MYDATA (DATA1 varchar2(300) )";
            statement.executeUpdate(sqls2);
            System.out.println("creating done...");
            }
            catch (SQLException e) {
            	// ...
            }
            // Insert into table
            try {
            System.out.println("Insert data to MYDATA.DATA1...");
            String filecontentstring = readFileAsString("demo2.txt"); // read file into strings
            String sqls3 = "INSERT INTO MYDATA (DATA1) VALUES('"+filecontentstring+"')";
            statement.executeUpdate(sqls3);
            System.out.println("Insertion done...");
            }
            catch (SQLException e) {
            	System.out.print(e.getMessage());
            }
            
            // Close DB instance conn
            try {
              conn.close();
            } 
            catch (SQLException e) {};
            System.out.println("DB Operation done");
        } // try
        catch (SQLException e)
        {
          System.out.println(e.getMessage());
        } // catch
	    // ################################################################
	    
	} // socket_listener()
	

	public void initialize_socket(int port) {
		// Auto-generated method stub
		try {
            serverSocket = new ServerSocket(port);
            String addr=serverSocket.getInetAddress().toString();
            String addrr=serverSocket.getLocalSocketAddress().toString();
            System.out.println("IP address: "+addr);
            System.out.println("socket address(IP address: port): "+addrr);

            initialize_socket_stream_buffer();
            socket_listener();
            serverSocket.close();		
		} catch (IOException e) {
            System.err.println("Could not listen on port: "+port);
            System.exit(-1);
        }            
	}

	
	public RMI_BioAPI_Demo(String local_fileName, int socket_port, 
			String AsteriskJava_IP, String Service_UID,
			String remote_AsteriskSrcFilename) throws RemoteException {
        new RequestThread("socket", "N/A", "N/A", "N/A", socket_listener_ip, socket_port, "N/A");
        new RequestThread("AsteriskJava", AsteriskJava_IP, Service_UID, remote_AsteriskSrcFilename, socket_listener_ip, socket_port, local_fileName);
	}

	private static String readFileAsString(String filePath) throws java.io.IOException{
	    byte[] buffer = new byte[(int) new File(filePath).length()];
	    FileInputStream f = new FileInputStream(filePath);
	    f.read(buffer);
	    return new String(buffer);
	}

	public static void main(String[] args) throws Exception {
	    if (args.length != 5)
	    {
	            System.out.println
	                ("Syntax - java RMI_BioAPI_Demo <local_Filename> <host_port> <Remote_AsteriskJava_IP> <service_UID> <remote_source_Filename>");
	            System.exit(1);
	    }
        // Create an instance of our service server ...
	    RMI_BioAPI_Demo demo_instance = new RMI_BioAPI_Demo(args[0], Integer.parseInt(args[1]), args[2], args[3], args[4]);
	}

}

