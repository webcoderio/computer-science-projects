package com.neurotechnology.Library;

import java.util.Vector;

public class ScannerFiles {
	private String name;
	private Vector<String> files;
	protected ScannerFiles(){
		files = new Vector<String>();
	}
	
	protected void setName(String name){
		this.name = name;
	}
	
	protected void addFile(String fileName){
		files.add(fileName);
	}
	
	public String getName(){
		return name;
	}
	
	public Vector<String> getFiles(){
		return files;
	}
	
}
