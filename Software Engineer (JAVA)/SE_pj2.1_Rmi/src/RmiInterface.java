import java.rmi.Remote; 
import java.rmi.RemoteException; 

public interface RmiInterface extends Remote { 
	/**
	 * @param null
	 * @return String
	 * @throws RemoteException
	 * @description To get default message from server
	 */
    String getMessage() throws RemoteException;
    
	/**
	 * @param String SentMessage
	 * @return Void
	 * @throws RemoteException
	 * @description To send message from client and print on server console
	 */
    void sendMessage(String SentMessage) throws RemoteException;
    
	/**
	 * @param String FileName
	 * @return byte[]
	 * @throws RemoteException
	 * @description To get/download file from server
	 */
    public byte[] getFile(String FileName) throws RemoteException;
}