import java.rmi.Remote; 
import java.rmi.RemoteException; 
import javax.swing.ImageIcon;

public interface RmiInterface extends Remote { 
	/**
	 * @param null
	 * @return String
	 * @throws RemoteException
	 * @description To get default message from server
	 */
	public String getMessage() throws RemoteException;
    
	/**
	 * @param String SentMessage
	 * @return Void
	 * @throws RemoteException
	 * @description To send message from client and print on server console
	 */
    public void sendMessage(String SentMessage) throws RemoteException;
    
	/**
	 * @param byte[] SentByteArray
	 * @param string fileName
	 * @return Void
	 * @throws RemoteException
	 * @description To send Byte Array from client and to be used on server side
	 */
    public void sendByteArrayImage(byte[] SendByteArray, String fileName) throws RemoteException;

    ///////////////////////////////////////////////////////////////////////////////////////////  
    // The followings are adapted from original Dr. Bon Sy's mfmatcher
    ///////////////////////////////////////////////////////////////////////////////////////////  
        
    public void loadMultiFaceImage(String fileName) throws RemoteException, Exception;

	public void extractMultiFaceImage() throws RemoteException, Exception;

	public ImageIcon getMultiFaceImageIcon() throws RemoteException, Exception;

	public ImageIcon getFaceDetectedMultiFaceImageIcon() throws RemoteException, Exception;

	public String getMultiFaceDetectionDetails() throws RemoteException;

	public boolean isMultiFaceImageExtracted() throws RemoteException;

	public void loadSingleFaceImage(String fileName) throws RemoteException, Exception;

	public ImageIcon getScaledRefImageIcon() throws RemoteException, Exception;

	public void extractSingleFaceImage() throws RemoteException, Exception;

	public String getSingleFaceDetectionDetails() throws RemoteException;

	public boolean isSingleFaceImageExtracted() throws RemoteException;

	public String matchMultipleFaces() throws RemoteException, Exception;

	public ImageIcon getSingleFaceImageIcon() throws RemoteException, Exception;

	///////////////////////////////////////////////////////////////////////////////////////////  
    // DB Operations
    ///////////////////////////////////////////////////////////////////////////////////////////  
	
	public void SaveSingleFaceImageToDB(String name, String fileName) throws RemoteException, Exception;

	public String getDBVerifiedByName(String name) throws RemoteException, Exception;

}