import java.rmi.Naming; 
//import java.rmi.RemoteException; 

public class RmiClientText { 

	String SentMessage = "";
	String ReciveMessage = "";
  
    // "ServerObj" is the identifier that we'll use to refer 
    // to the remote object that implements the "RmiInterface" 
    RmiInterface ServerObj = null;

    public RmiClientText(String hostip, int method, String message) { 
		try
		{
			System.out.println("RmiClient starts runing.");
			System.out.println("RmiClient trying to connect to Server IP " + hostip +"...");
			System.out.println("");
			
		    ServerObj = (RmiInterface)Naming.lookup("//" + hostip + "/RmiServerService"); 
		    
		    if(method==1)
		    {
			    // getMessage()
			    System.out.println("Execute Server API: getMessage()");
			    System.out.println("");
			    System.out.println("Getting Message...");
			    System.out.println( ServerObj.getMessage() ); // print the String from Server to Client console
			    System.out.println("Messenge Reccieved.");;
			    System.out.println("");
		    } // if 1
		    else if(method==2)
		    {
			    // sendMessage()
			    System.out.println("Execute Server API: sendMessage(String message)");
			    SentMessage = message;
			    System.out.println("");
			    System.out.println("Sending Message...");
			    ServerObj.sendMessage(SentMessage); // print the String I sent on Server
			    System.out.println("Messenge Sent, check server console for the message you sent!");
			    System.out.println("");
		    } // if 2
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
			System.out.println("getMessenge()");
			System.out.println("Syntax - java RmiClientText <hostip> <1> <any_message>");
			System.out.println("sendMessenge()");
			System.out.println("Syntax - java RmiClientText <hostip> <2> <message>");
			System.exit(1);
		}
		
		// Call registry for Service
		new RmiClientText(args[0], Integer.parseInt(args[1]), args[2]); // <hostip> <method> <message>
		System.exit(1);
	} // main
} // class
