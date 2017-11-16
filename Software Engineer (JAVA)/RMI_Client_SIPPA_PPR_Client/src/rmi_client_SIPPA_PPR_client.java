/**
 * 
 */

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Random;

/**
 * @author bon
 *
 */
public class rmi_client_SIPPA_PPR_client {

    public void Invoke_rmi_server_SIPPA_PPR_Client(String[] argum)   
    {
    	try {
    		
	    		DatagramSocket socket = new DatagramSocket(6340);
	    		DatagramPacket packet = new DatagramPacket(new byte[512],512);
    			int jj = 6;
    			String nn = "n";
    			int noservers = 0;
    			
    			while(!(argum[jj].equals(nn))){    				
    				noservers++;    				
    				jj++;     				
    			}
    			
	    		
	    		String TransactionID = argum[1];
	    		
	    		int transno = 1;
    		
    		
    			while(true){    				
    				
    			    DatagramSocket socket1 = new DatagramSocket();
    			    socket1.setSoTimeout( 5000 );
    			    byte[] buffer = "give".getBytes();
    			    DatagramPacket packet1 = new DatagramPacket(buffer,buffer.length,InetAddress.getByName(argum[0]),6343);
    			    socket1.send( packet1 ); 
    			    
    			    
    			    socket.receive( packet );  
    			    String message = new String(packet.getData(),0,packet.getLength());
    			    
    			    if (message.length() > 6) { 
    			    	
    			    			final String TransactionID1 = TransactionID + transno;
    			    			Random diceRoller = new Random();
    			    			final int roll = diceRoller.nextInt(noservers) + 6;	
    			    			
    			    			final String a = argum[2];
    			    			final String b = argum[3];
    			    			final String c = argum[4];
    			    			final String d = argum[5];
    			    			final String e = argum[roll];
    			    			final String f = argum[0];
    			    			
    			    	
    			    			final String ServerIP = message.substring(0, message.indexOf(":")-1);
    			    			final int ServerPort = Integer.parseInt(message.substring(message.indexOf(":")+1));

    	    					final rmi_interface_SIPPA_PPR_Client service = (rmi_interface_SIPPA_PPR_Client) 
    	    					Naming.lookup("rmi://" + argum[roll] + "/rmi_svr_SIPPA_PPR_client");
    	    					
    	    					Thread thread = new Thread(new Runnable() {
    	    						@Override
    	    						public void run() {
    	    							try {
											service.invoke_SIPPA_PPR_Client(TransactionID1, ServerIP, ServerPort, Integer.parseInt(a), Integer.parseInt(b), Integer.parseInt(c), d, e, f);
										} catch (NumberFormatException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										} catch (RemoteException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
    	    						}
    	    					});
    	    					thread.start();
    	    					long endTimeMillis = System.currentTimeMillis() + 1000;
    	    					while (thread.isAlive()) {
    	    						if (System.currentTimeMillis() > endTimeMillis) {
    	    							// set an error flag
    	    							break;
    	    						}
    	    						try {
    	    							Thread.sleep(500);
    	    						}
    	    						catch (InterruptedException t) {}
    	    					}   	    	    		    
    	    	    		    
    	    	    		    transno = transno + 100;
    	    		}
    			}    			        				
    			       		
    	} catch (Exception e) {
    		System.out.println("RMI client SIPPA PPR Client Naming lookup fails!");
    	}
    }
       
    public rmi_client_SIPPA_PPR_client (String[] argum) {
    	Invoke_rmi_server_SIPPA_PPR_Client(argum);	
    }
    
	private static void printUsage()
	{
		System.out.println("rmi_client_SIPPA_PPR_Client");
		System.out.println("Returns 0 if successful, 1 otherwise");
		System.out.println("Usage: java -jar rmi_client_SIPPA_PPR_Client.jar <ListServerIP> <Transaction ID> <option> <userDefinedRowSize> <userDefinedColumnSize> <optionHelperData> <ClientIps>");
		System.out.println("   <userDefinedRowSize> and <userDefinedColumnSize> are ignored if they are -1, and are used only if they are positive integer matching the matrix size");
		System.out.println("   <optionHelperData> should be 0 for using only helper data 1/De^T*x and eigenvalue, and 1 for using vector b3 and 1/De^T*x");
		System.out.println("Requester matrix and vector default filenames are <Transaction ID>-Requester-matrix.txt and <Transaction ID>-Requester-vector.txt");
		System.out.println("EW filename is assumed to be <EW_initial>.txt");
		System.out.println("option: 0");
		//System.out.println("option: 1 for generating random requester matrix and vector at run-time");
		//System.out.println("option: 2 for using pre-defined requester matrix and b vector= vector - EW");
		System.out.println("Success returns errorlevel 0. Error return greater than zero.");
		System.exit(0);
	}
    
    
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try
		{
			if (args.length < 8) {
				printUsage();
			}

	        /* Print a copyright. */
	        System.out.println("RMI Client for the SIPPA PPR Client requester utility");
	        System.out.println("Copyright: Bon Sy");
	        System.out.println("Free to use this at your own risk!");
	        
        	new rmi_client_SIPPA_PPR_client(args);
		} 
		catch (Exception e){
			// probably error in input
			printUsage();
		}
	}

}
