package com.neurotechnology.NFEngine;

import java.util.ArrayList;
import java.util.List;

import com.neurotechnology.Library.NativeManager;
import com.neurotechnology.Library.NativeObject;

public class NFEngine extends NativeObject{
	
	private ArrayList<NFEngineUser> users;
	private NFEngineStatus engineStatus;
	
	public NFEngine(String database, String password, ScannerModule[] scannerModules){
		String scanners = "";
		for(int i = 0; i < scannerModules.length; i++){
			if(scanners.length() != 0) scanners += ";";
			scanners += scannerModules[i].getName();
		}
		this.setHandle(create(database, password, scanners));
		
		users = new ArrayList<NFEngineUser>();
		long[] handles = getUsers(this.getHandle());
		for (int i = 0; i < handles.length; i++)
			users.add(new NFEngineUser(handles[i],this));
			
	}
	
	public List<NFEngineUser> getUsers(){
		return users;
	}
	
	public void clearUsers(){
		users = new ArrayList<NFEngineUser>();
		removeUsers(this.getHandle());
	}
	
	public NFEngineUser getUserByID(int id){
		long handle = getUserByID(this.getHandle(),id);
		for (NFEngineUser user : users) {
			if(user.getHandle() == handle) return user;
		}		
		return null;
	}
	
	public void removeUser(NFEngineUser user) throws Exception{
		for (int i = 0; i < this.users.size(); i++)
			if(users.get(i) == user){
				removeUser(this.getHandle(), user.getHandle());
				users.remove(user);
				return;
			}
	}
	
	public void removeUserID(int ID){
		removeUserID(this.getHandle(), ID);
	}
	
	public boolean contains(NFEngineUser user){
		return users.contains(user);
	}
	
	public static ScannerModule[] getAvailableScannerModules(){
		String modules = getAvailableScannerModules0();
		if(modules == null) return null;
		
		String[] names = modules.split(";");
		ScannerModule[] scannerModules = new ScannerModule[names.length];
		
		for(int i = 0; i < names.length; i++)
			scannerModules[i] = new ScannerModule(names[i]);
		
		return scannerModules;
	}
	
	public NFEngineUser enroll(int timeout){
		long handle = enroll(this.getHandle(), timeout, this);
		if (handle == 0) return null;
		NFEngineUser user = new NFEngineUser(handle, this);
		users.add(user);
		return user;
	}
	
	public int verify(NFEngineUser user, int timeout){
		return verify(this.getHandle(), user.getHandle(), timeout, this);
	}
	
	
	private static native String getAvailableScannerModules0();
	private static native long create(String database, String password, String scannerModules);
	private static native long[] getUsers(long landle);
	private static native long getUserByID(long handle, int id);
	private static native void removeUser(long handle, long userHandle);
	private static native void removeUserID(long handle, int id);
	private static native void removeUsers(long handle);
	private static native int verify(long handle, long userhandle, int timeout, NFEngine engine);
	private static native long enroll(long handle, int timeout, NFEngine engine);
	
	public static native int getMaxUserCount();

	protected void setEngineStatus(NFEngineStatus engineStatus) {
		this.engineStatus = engineStatus;
	}
	
	protected void setIntEngineStatus(int engineStatus) {
		this.engineStatus = NFEngineStatus.getVal(engineStatus);
	}

	public NFEngineStatus getEngineStatus() {
		return engineStatus;
	}
	
	static{
		NativeManager.defaultlibrary = "NFEngineJavaNative";
		NativeManager.loadDefault();
	}
}
