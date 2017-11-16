/**
 * 
 */

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.net.*;
import java.util.*;

/**
 * @author bon
 *
 */
public class rmi_client_SIPPA_PPR_server {

	public rmi_client_SIPPA_PPR_server (String[] Servers) {
		// argument 0 =  ip of client client, argument 1 = ip of servers etc
		
		Queue<String> queue = new LinkedList<String>();

		
	    try {
		    	DatagramSocket socket = new DatagramSocket(6343);
		    	DatagramPacket packet = new DatagramPacket(new byte[512],512);
    			int jj = 1;
    			
    			String nn = "n";
    			
    			while(!(Servers[jj].equals(nn))){
    				
    				int ports = 1688;
    				while(true){
    				
    					rmi_interface_SIPPA_PPR_Server service = (rmi_interface_SIPPA_PPR_Server) Naming.lookup("rmi://" + Servers[jj] + "/rmi_svr_SIPPA_PPR_server");
    					service.invoke_SIPPA_PPR_Server(ports);
    					String addq = Servers[jj];
    					addq += ":";
    					addq += ports;
    					queue.add(addq);    					
    					System.out.println("Added to Queue "+addq);
    					if (ports == 1691){
    						break;
    					}
    					ports++;  					
    				}
    				jj++;    				
    			}   
    			
    			while ( true ) {

    				socket.receive( packet );    				
    				String message = new String(packet.getData(),0,packet.getLength());
    				
    				if (message.equalsIgnoreCase("give")){
    					
    					DatagramSocket socket1 = new DatagramSocket();

    					socket1.setSoTimeout( 5000 );
     					System.out.println("Removed From Queue"+queue.peek());
    				
    					byte[] buffer = queue.poll().getBytes();
    					DatagramPacket packets1 = new DatagramPacket(buffer,buffer.length,InetAddress.getByName(Servers[0]),6340);
    					socket1.send( packets1 );
    				}
    				
    				if (message.substring(0, 1).equalsIgnoreCase("fr")){
    					
    					System.out.println("Added to Queue"+message.substring(2));
    					queue.add(message.substring(2));				
    					
    				}   			    
    			}  			
    		
    		
    	} catch (Exception e) {
    		System.out.println("RMI client SIPPA PPR Server Naming lookup fails!");
    		e.printStackTrace();
    	}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try
		{

	        /* Print a copyright. */
	        System.out.println("RMI Client for the SIPPA PPR Server requester utility");
	        System.out.println("Copyright: Bon Sy");
	        System.out.println("Free to use this at your own risk!");
	        
        	new rmi_client_SIPPA_PPR_server(args);
		} 
		catch (Exception e){
			// probably error in input
		}
	}

}
