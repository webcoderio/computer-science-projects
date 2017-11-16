package com.neurotechnology.NMatcher;

public enum Speed {
	Low, 
	Medium, 
	High;
	
	public int eval(){
		switch (this) {
			case Low: return 0;
			case Medium: return 128;
			case High: return 256;
		}
		return 0;
	}
	
	public static Speed getValue(int value){
		switch(value){
			case 0: return Low;
			case 128: return Medium;
			case 256: return High;
			default: return Low;
		}
	}
}
