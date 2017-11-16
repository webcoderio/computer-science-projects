package com.neurotechnology.Communication;

import com.neurotechnology.Library.NativeManager;
import com.neurotechnology.Templates.NRecord;

public class Admin {
	
	private long hClusterAdminSocket;
	//private String server;
	//private String port;
	
	public Admin(String server, String port) throws Exception{
		if (!Client.isInitialized){
			Client.initialize();
			Client.isInitialized = true;
		}
		hClusterAdminSocket = Client.connect(server,port);
		//this.port = port;
		//this.server = server;
		//Client.disconnect(hClusterAdminSocket);
	}
	
	public Admin(String server, int port) throws Exception{
		if (!Client.isInitialized){
			Client.initialize();
			Client.isInitialized = true;
		}
		hClusterAdminSocket = Client.connect(server, new Integer(port).toString());
		//this.port =  new Integer(port).toString();
		//this.server = server;
		//Client.disconnect(hClusterAdminSocket);
	}
	
	public void finilize()throws Throwable{
		super.finalize();
		Client.disconnect(hClusterAdminSocket);
	}
	
	public synchronized void nodeStop(int nodeId)throws Exception{
		//hClusterAdminSocket = Client.connect(server, port);
		nodeStop(hClusterAdminSocket, nodeId);
		//Client.disconnect(hClusterAdminSocket);
	}
	
	public synchronized void clusterStart()throws Exception{
		//hClusterAdminSocket = Client.connect(server, port);
		clusterStart(hClusterAdminSocket);
		//Client.disconnect(hClusterAdminSocket);
	}
	
	public synchronized void serverKill()throws Exception{
		//hClusterAdminSocket = Client.connect(server, port);
		serverKill(hClusterAdminSocket);
		//Client.disconnect(hClusterAdminSocket);
	}
	
	public synchronized void nodeKill(int nodeId)throws Exception{
		//hClusterAdminSocket = Client.connect(server, port);
		nodeKill(hClusterAdminSocket, nodeId);
		//Client.disconnect(hClusterAdminSocket);
	}
	
	public synchronized  void updateDatabase()throws Exception{
		//hClusterAdminSocket = Client.connect(server, port);
		updateDatabase(hClusterAdminSocket);
		//Client.disconnect(hClusterAdminSocket);
	}
	
	public synchronized ClusterNodeState[] getClusterNodeInfo()throws Exception{
		//hClusterAdminSocket = Client.connect(server, port);
		ClusterNodeState cns = new ClusterNodeState();
		ClusterNodeState[] ret = getNodeState(hClusterAdminSocket,cns);
		//Client.disconnect(hClusterAdminSocket);
		return ret;
	}
	
	public synchronized int[] getResultIds()throws Exception{
		//hClusterAdminSocket = Client.connect(server, port);
		int[] ret =  getResultIds(hClusterAdminSocket);
		//Client.disconnect(hClusterAdminSocket);
		return ret;
	}
	
	public synchronized ClusterTaskInfo[] getClusterTaskInfo()throws Exception{
		//hClusterAdminSocket = Client.connect(server, port);
		ClusterTaskInfo[] ret = getClusterTaskInfo(hClusterAdminSocket,new ClusterTaskInfo());
		//Client.disconnect(hClusterAdminSocket);
		return ret;
	}
	
	public synchronized ClusterShortInfo[] getClusterShortInfo()throws Exception{
		//hClusterAdminSocket = Client.connect(server, port);
		ClusterShortInfo[] ret = getClusterShortInfo(hClusterAdminSocket, new ClusterShortInfo());
		//Client.disconnect(hClusterAdminSocket);
		return ret;
	}
	
	public synchronized void updateDBRecords(String[] IDs)throws Exception{
		//hClusterAdminSocket = Client.connect(server, port);
		updateDBRecords(hClusterAdminSocket,IDs,IDs.length);
		//Client.disconnect(hClusterAdminSocket);
	}
	
	public synchronized int insertDBRecords(String[] IDs, NRecord[] templates) throws Exception{
		
		if (IDs.length != templates.length) throw new Exception("ID and template count is not equal");
		long [] pBuffers = new long[IDs.length];
		int[] pBufferSizes = new int[IDs.length];
		for (int i = 0; i < IDs.length; i++){
			pBuffers[i] = templates[i].getPBuffer();
			pBufferSizes[i] = templates[i].getPBufferSize();
		}
			
		//hClusterAdminSocket = Client.connect(server, port);
		int insertId = insertDBRecords(hClusterAdminSocket, IDs.length, IDs, pBufferSizes, pBuffers);
		//Client.disconnect(hClusterAdminSocket);
		
		for (int i = 0; i < IDs.length; i++)
			NRecord.freePBuffer(pBuffers[i]);
		
		return insertId;
	}
	
	public synchronized InsertDeleteResult getInsertResult(int insertId){
		InsertDeleteResult result = new InsertDeleteResult();
		getInsertResult(hClusterAdminSocket, insertId, result);
		return result;
	}
	
	public synchronized InsertDeleteResult getDeleteResult(int deleteId){
		InsertDeleteResult result = new InsertDeleteResult();
		getDeleteResult(hClusterAdminSocket, deleteId, result);
		return result;
	}
	
	public synchronized int deleteDBRecords(String[] IDs)throws Exception{
		//hClusterAdminSocket = Client.connect(server, port);
		return deleteDBRecords(hClusterAdminSocket, IDs.length, IDs);
		//Client.disconnect(hClusterAdminSocket);
	}
	
	public synchronized void flush() throws Exception{
		//hClusterAdminSocket = Client.connect(server, port);
		flush(hClusterAdminSocket);
		//Client.disconnect(hClusterAdminSocket);
	}

	private static native void clusterStart(long hClusterAdminSocket)throws Exception;
	private static native void nodeStop(long hClusterAdminSocket, int nodeId)throws Exception;
	private static native void serverKill(long hClusterAdminSocket)throws Exception;
	private static native void nodeKill(long hClusterAdminSocket, int nodeId)throws Exception;
	private static native void updateDatabase(long hClusterAdminSocket)throws Exception;
	private static native ClusterNodeState[] getNodeState(long hClusterAdminSocket, ClusterNodeState cns)throws Exception;
	private static native int[] getResultIds(long hClusterAdminSocket)throws Exception;
	private static native ClusterTaskInfo[] getClusterTaskInfo(long hClusterAdminSocket, ClusterTaskInfo cti)throws Exception;
	private static native ClusterShortInfo[] getClusterShortInfo(long hClusterAdminSocket, ClusterShortInfo csi)throws Exception;
	private static native void updateDBRecords(long hClusterAdminSocket, String[] ids, int count)throws Exception;
	private static native int insertDBRecords(long hClusterAdminSocket, int count, String[] ids, int[] pBufferSizes, long[] pBuffers)throws Exception;
	private static native int deleteDBRecords(long hClusterAdminSocket, int count, String[] ids) throws Exception;
	private static native void getInsertResult(long hClusterAdminSocket, int insertDeleteId, InsertDeleteResult result);
	private static native void getDeleteResult(long hClusterAdminSocket, int insertDeleteId, InsertDeleteResult result);
	private static native void flush(long hClusterAdminSocket) throws Exception;
	
	static{
		NativeManager.loadDefault();
	}
	
}
