package com.neurotechnology.Faces;

public enum Mode {
	//#define VLE_MODE_GENERAL                            0
	//#define VLE_MODE_VERILOOK_30_TEMPLATE_COMPATIBLE  100
	general,
	verilook30TemplateCompatible;
	public int eval(){
		switch(this){					
			case general : 	return 0;		
			case verilook30TemplateCompatible :	 return 100;	
		}
		return 0;
	}
	public static Mode getVal(int intval){
		switch(intval){					
			case 0 : 	return Mode.general;		
			case 100 :	return Mode.verilook30TemplateCompatible;	
		}
		
		return Mode.general;
	}
}
