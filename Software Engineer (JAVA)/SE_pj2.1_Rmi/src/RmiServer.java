import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.io.*;
import java.rmi.RMISecurityManager;

public class RmiServer extends UnicastRemoteObject
    implements RmiInterface {

	private static final long serialVersionUID = 5289900962559714948L;

	public RmiServer() throws RemoteException {
        super();
    } // Rmiserver()

    public String getMessage() { // method  must be implemented
        String message = "Server Says, 'Hi, Client!' (This is a message sent from Server to Client)";
        return message;
    } // printMessage()
    
    public void sendMessage(String SentMessage) { // method  must be implemented
        System.out.println(SentMessage);
    } // printMessage()

    public static void main(String args[]) { 

        // Create and install a security manager 
        if (System.getSecurityManager() == null) { 
        	System.setSecurityManager(new RMISecurityManager()); 
        }
        try
        { 
		    RmiServer ServerObj = new RmiServer();  // 
		    // Bind this object instance to the name "RmiServer"
		    // Note that the name must be compatible with URL
		    // No space
		    Naming.rebind("RmiServerService", ServerObj); 
		    System.out.println("RmiServerService is now bound in registry at port 1099"); 
		    System.out.println("Service RmiServerService listener is now redy...");
		    
		    System.out.println("");
		    System.out.println("-------------------------------------------");
		    System.out.println("We have the folowing API for you to call:");
		    System.out.println("");
		    System.out.println("getMessage(String hostip, int 1, String null)");
		    System.out.println("sendMessage(String hostip, int 2, String SentMessage)");
		    System.out.println("getFile(String hostip, int 1, String FileName)");
		    
		    System.out.println("");
        } catch (Exception e)
        { 
		    System.out.println("RmiServerService error: " + e.getMessage()); 
		    e.printStackTrace(); 
        } // try catch
    } // main()

	public byte[] getFile(String FileName) throws RemoteException {
	  try {
	      File file = new File(FileName);
	      byte buffer[] = new byte[(int)file.length()];
	      BufferedInputStream input = new
	      BufferedInputStream(new FileInputStream(FileName));
	      input.read(buffer,0,buffer.length);
	      input.close();
	      return(buffer);
	   } catch(Exception e){
	      System.out.println("RmiServer Error: "+e.getMessage());
	      e.printStackTrace();
	      return(null);
	   } //try catch
	// return null;
	} // getFile()
} // class
