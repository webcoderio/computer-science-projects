package com.neurotechnology.Communication;

public enum TaskMode {
	normal,
	first,
	details;
	
	public int eval(){
		switch (this){
			case normal: return 0; 
			case first: return (1<<0); 
			case details: return (1<<1); 
		}
		return 0;
	}
}
