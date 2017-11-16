package com.neurotechnology.Communication;

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
}
