package com.neurotechnology.Communication;

import com.neurotechnology.Library.NativeManager;
import com.neurotechnology.Templates.NTemplate;


public class Client {
	
	private long hClusterClientSocket;
	protected static boolean isInitialized;
	
	//private String server;
	//private String port;
	
	
	public Client(String server, String port) throws Exception{
		if (!isInitialized){
			initialize();
			isInitialized = true;
		}
		hClusterClientSocket = connect(server,port);
		//this.server = server;
		//this.port = port;
		//disconnect(hClusterClientSocket);
	}
	
	public Client(String server, int port) throws Exception{
		if (!isInitialized){
			initialize();
			isInitialized = true;
		}
		hClusterClientSocket = connect(server, new Integer(port).toString());
		//this.server = server;
		//this.port = new Integer(port).toString();
		//disconnect(hClusterClientSocket);
	}
	
	public void finalize()throws Throwable{
		super.finalize();
		disconnect(hClusterClientSocket);
	}
	
	public synchronized TaskProgress getProgress(int taskID)throws Exception{
		//hClusterClientSocket = connect(server,port);
		TaskProgress taskProgress = new TaskProgress();
		getProgress(hClusterClientSocket, taskID, taskProgress);
		//disconnect(hClusterClientSocket);
		return taskProgress;
	}
	
	/*
	public synchronized int getResultCount(int taskID)throws Exception{
		hClusterClientSocket = connect(server,port);
		int count = getResultCount(hClusterClientSocket, taskID);
		disconnect(hClusterClientSocket);
		return count;
	}
	*/
	
	public synchronized int sendTask(Task task)throws Exception{
		//hClusterClientSocket = connect(server,port);
		long pBuffer = task.getNRecord().getPBuffer();
		int id = sendTask(hClusterClientSocket, 
				task.getMode().eval(), pBuffer, task.getNRecord().getPBufferSize(),
				task.getQuery(),
				task.getParameters(),task.getParameters().length,
				task.getResultLimit());
		NTemplate.freePBuffer(pBuffer);
		//disconnect(hClusterClientSocket);
		return id;
	}
	
	public synchronized TaskResult getTaskResult(int taskID, int lowrange, int highrange)throws Exception{
		//hClusterClientSocket = connect(server,port);
		TaskResult taskResult = new TaskResult();
		getResult(hClusterClientSocket, taskID, lowrange, highrange, taskResult);
		//disconnect(hClusterClientSocket);
		return taskResult;
	}
	
	public synchronized void deleteTask(int taskID)throws Exception{
		//hClusterClientSocket = connect(server,port);
		deletetask(hClusterClientSocket, taskID);
		//disconnect(hClusterClientSocket);
	}
	
	protected static native long connect(String server, String port)throws Exception;
	protected static native void disconnect(long hClusterClientSocket);
	protected static native void initialize()throws Exception;
	private static native void getProgress(long hClusterClientSocket, int taskID, TaskProgress taskProgress)throws Exception;
	private static native int sendTask(long hClusterClientSocket,
			int taskMode, long pBuffer, int pBufferSize, String query, 
			TaskParameter[] parameters, int parametersCount, int resultLimit)throws Exception;
	private static native void getResult(long hClusterClientSocket,
			int taskID, int lowrange, int highrange, TaskResult taskResult)throws Exception;
	private static native void deletetask(long hClusterClientSocket, int taskid);
	//private static native int getResultCount(long hClusterClientSocket, int taskid);
	
	static {
    	NativeManager.loadDefault();
    	NativeManager.checkLoad("NCluster");
	}
	
}
