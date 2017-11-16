package com.neurotechnology.NFIQ;

public enum NFIQQuality {
	nfqPoor,
	nfqFair,
	nfqGood,
	nfqVeryGood,
	nfqExcellent;
	
	public int eval(){
		switch(this){
			case nfqPoor : return 5;
			case nfqFair : return 4;
			case nfqGood : return 3;
			case nfqVeryGood : return 2;
			case nfqExcellent : return 1;
		}
		return 0;
	}
	
	public static NFIQQuality getVal(int val) throws Exception{
		System.out.println(val);
		switch(val){
			case 5 : return nfqPoor;
			case 4 : return nfqFair;
			case 3 : return nfqGood;
			case 2 : return nfqVeryGood;
			case 1 : return nfqExcellent;
		}
		throw new Exception("invalid value"); 
	}
}
