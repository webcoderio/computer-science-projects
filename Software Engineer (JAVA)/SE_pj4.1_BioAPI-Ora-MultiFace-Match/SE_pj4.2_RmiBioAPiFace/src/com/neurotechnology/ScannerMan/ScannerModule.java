package com.neurotechnology.ScannerMan;

public class ScannerModule {
	private String name;
	
	protected ScannerModule(String name){
		this.name = name;
	}

	protected void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
