/**
 * 
 */

import java.rmi.RemoteException;

/**
 * @author bon
 *
 */
public interface rmi_interface_SIPPA_PPR_Client extends java.rmi.Remote {

//  Arugment list for invoke_SIPPA_PPR_Client	
//	   <Transaction ID> <remote SIPPA server DB_Agent IP (i.e., rmi client IP for SPPIA server)> <remote SIPPA Server socket communication Port (not rmi port)> <option> <userDefinedRowSize> <userDefinedColumnSize> <optionHelperData>
//	       whereas <userDefinedRowSize> and <userDefinedColumnSize> are ignored if they are -1, and are used only if they are positive integer matching the matrix size
//	               <optionHelperData> should be 0 for using only helper data 1/De^T*x and eigenvalue, and 1 for using vector b3 and 1/De^T*x
//	               Requester matrix and vector default filenames are <Transaction ID>-Requester-matrix.txt and <Transaction ID>-Requester-vector.txt
//	               EW filename is assumed to be <EW_initial>.txt
//	               option: 0 is always the case due to the PPR software

	
	public void invoke_SIPPA_PPR_Client (String transaction_ID, String rmi_client_ip_of_SIPPA_server, int SIPPA_Server_socket_port, int option, int userDefinedRowSize, int userDefinedColumnSize, String optionHelperData, String rmi_server_ip_of_SIPPA_client, String iplist) throws RemoteException;

	
}
