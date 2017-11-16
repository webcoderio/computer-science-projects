/**
 * 
 */

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.rmi.Naming;

/**
 * @author bon
 *
 */
public class rmi_server_SIPPA_PPR_client extends UnicastRemoteObject implements rmi_interface_SIPPA_PPR_Client {

    public rmi_server_SIPPA_PPR_client (int port) throws RemoteException
    {
        super(port);
    }
    
	public void invoke_SIPPA_PPR_Client (String transaction_ID, String rmi_client_ip_of_SIPPA_server, int SIPPA_Server_socket_port, int option, int userDefinedRowSize, int userDefinedColumnSize, String optionHelperData, String rmi_server_ip_of_SIPPA_client, String iplist) throws RemoteException {
		int currentfile = Integer.getInteger(transaction_ID.substring(transaction_ID.indexOf("$")+1));
		for (int i = 0; i <100; i++){
			
			String transid = transaction_ID.substring(0,transaction_ID.indexOf("$")) + currentfile;
		
		 	new SIPPA_PPR_Client(transid, rmi_client_ip_of_SIPPA_server, SIPPA_Server_socket_port, option, userDefinedRowSize, userDefinedColumnSize, optionHelperData);
			
			currentfile++;
		} 
		
		try {
			DatagramSocket socket1;
			socket1 = new DatagramSocket();
			socket1.setSoTimeout( 5000 );
			String Messagesend = "fr"+rmi_client_ip_of_SIPPA_server+":"+SIPPA_Server_socket_port;
			byte[] buffer = Messagesend.getBytes();
			DatagramPacket packet1 = new DatagramPacket(buffer,buffer.length,InetAddress.getByName(iplist),6343);
			socket1.send( packet1 );
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}

	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		rmi_server_SIPPA_PPR_client svr = new rmi_server_SIPPA_PPR_client(Integer.parseInt(args[0]));
	    //
            System.out.println("RmiRegistry listens at port 1099 ");
            System.out.println("RMI Server for SIPPA PPR Client is ready to listen on " + args[0]);
 //            System.out.println(InetAddress.getLocalHost().getHostName());
	    Naming.bind("rmi_svr_SIPPA_PPR_client", svr);
            System.out.println("RMI server for SIPPA Client starts ... ");
	}

}
