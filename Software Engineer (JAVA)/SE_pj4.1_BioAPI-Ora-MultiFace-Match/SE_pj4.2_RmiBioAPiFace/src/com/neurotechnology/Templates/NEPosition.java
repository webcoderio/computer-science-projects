package com.neurotechnology.Templates;

public enum NEPosition {
	unknown,
	right,
	left;
	//nepUnknown = 0,  ///< Unknown eye
	//nepRight = 1,    ///< Right eye
	//nepLeft = 2,     ///< Left eye
	
	public int eval(){
		switch(this){					
			case unknown: 	return 0;		
			case right 	:	return 1;	
			case left 	:	return 2;
		}
		return 0;
	}
	public static NEPosition getVal(int intval){
		switch(intval){			
			case 0 : 	return NEPosition.unknown;
			case 1 : 	return NEPosition.right;		
			case 2 :	return NEPosition.left;	
		}
		
		return NEPosition.unknown;
	}
}
