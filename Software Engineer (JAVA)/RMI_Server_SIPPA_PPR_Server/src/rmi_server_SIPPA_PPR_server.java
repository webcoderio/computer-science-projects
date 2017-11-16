import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * 
 */

/**
 * @author bon
 *
 */
public class rmi_server_SIPPA_PPR_server extends UnicastRemoteObject 
implements rmi_interface_SIPPA_PPR_Server {

    public rmi_server_SIPPA_PPR_server (int port) throws RemoteException
    {
        super(port);
    }
    
	public void invoke_SIPPA_PPR_Server (final int SIPPA_port) throws Exception {
		
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				new SIPPA_PPR_Server(SIPPA_port);
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
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		rmi_server_SIPPA_PPR_server svr = new rmi_server_SIPPA_PPR_server(Integer.parseInt(args[0]));
	    //
            System.out.println("RmiRegistry listens at port 1099 ");
            System.out.println("RMI Server for SIPPA PPR Server is ready to listen on " + args[0]);
 //            System.out.println(InetAddress.getLocalHost().getHostName());
	    Naming.bind("rmi_svr_SIPPA_PPR_server", svr);
            System.out.println("RMI server for SIPPA Server starts ... ");
	}

}
