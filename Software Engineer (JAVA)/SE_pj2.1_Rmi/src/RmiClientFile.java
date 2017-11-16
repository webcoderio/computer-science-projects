import java.rmi.Naming; 
import java.io.*;
//import java.rmi.RemoteException; 

public class RmiClientFile { 

	String FileNameString = "";
  
    // "ServerObj" is the identifier that we'll use to refer 
    // to the remote object that implements the "RmiInterface" 
    RmiInterface ServerObj = null; 

    public RmiClientFile(String hostip, int method, String filename) { 
		try
		{
			System.out.println("RmiClient starts runing.");
			System.out.println("RmiClient trying to connect to Server IP " + hostip +"...");
			System.out.println("");
			
		    ServerObj = (RmiInterface)Naming.lookup("//" + hostip + "/RmiServerService"); 
		    
		    if(method==1)
		    {
			    // getFile()
			    // get file from server side to client side
			    System.out.println("Execute Server API: getFile()");
			    System.out.println("");
			    System.out.println("Getting File...");
			    FileNameString = filename;
			    byte[] filedata = ServerObj.getFile(FileNameString);
				File file = new File(FileNameString);
				BufferedOutputStream output = new
				BufferedOutputStream(new FileOutputStream(file.getName()));
				output.write(filedata,0,filedata.length);
				output.flush();
				output.close();
			    System.out.println("File '"+FileNameString+"' Reccieved.");
			    System.out.println("");
		    } // if 1
		    else
		    {
		    	System.out.println("There is no such method: "+method);
		    	System.exit(1);
		    } // else
		    System.out.println("Done.");
		    System.out.println("");
		} catch (Exception e)
		{ 
		    System.out.println("RmiServerService exception: " + e.getMessage()); 
		    e.printStackTrace(); 
		} 
    } // RmiClientTextLocal()

	public static void main(String[] args) throws Exception {
		if (args.length != 3)
		{
			System.out.println("Missing Paramters");
			System.out.println("getFile()");
			System.out.println("Syntax - java RmiClientFile <hostip> <1> <filename>");
			System.out.println("sendFile()");
			System.out.println("Syntax - java RmiClientFile <hostip> <2> <filename>");
			System.exit(1);
		}
		
		// Call registry for Service
		new RmiClientFile(args[0], Integer.parseInt(args[1]), args[2]); // <hostip> <method> <filename>
		System.exit(1);
	} // main
} // class
