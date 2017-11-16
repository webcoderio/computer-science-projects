package com.neurotechnology.NMatcher;


public enum MatcherMode {
	GENERAL,					//  0
	DIGITALPERSONA_UAREU,		//  100
	BIOMETRIKA_FX2000,			//  200
	BIOMETRIKA_FX3000,			//  201
	KEYTRONIC_SECUREDESKTOP,	//  300
	IDENTIX_TOUCHVIEW,			//  400
	IDENTIX_DFR2090,			//  401
	PRECISEBIOMETRICS_100CS,	//  500
	UPEK_TOUCHCHIP,				//  600
	IDENTICATORTECHNOLOGY_DF90,	//  700
	AUTHENTEC_AFS2,				//  800
	AUTHENTEC_AES4000,			//  810
	AUTHENTEC_AES2501B,			//  811
	ATMEL_FINGERCHIP,			//  900
	BMF_BLP100,					//  1000
	SECUGEN_HAMSTER,			//  1100
	ETHENTICA,					//  1200
	CROSSMATCH_VERIFIER300,		//  1300
	NITGEN_FINGKEY_HAMSTER,		//  1400
	TESTECH_BIOI,				//  1500
	DIGENT_IZZIX,				//  1600
	STARTEK_FM200,				//  1700
	FUJITSU_MBF200,				//  1800
	FUTRONIC_FS80,				//  1900
	LIGHTUNING_LTTC500,			//  2000
	TACOMA_CMOS;				//  2100
	
	public int eval(){
		switch(this){
			case GENERAL : 						return 0;					
			case DIGITALPERSONA_UAREU : 		return 100;		
			case BIOMETRIKA_FX2000 :	 		return 200;		
			case BIOMETRIKA_FX3000 :			return 201;			
			case KEYTRONIC_SECUREDESKTOP : 		return 300;	
			case IDENTIX_TOUCHVIEW : 			return 400;			
			case IDENTIX_DFR2090 : 				return 401;			
			case PRECISEBIOMETRICS_100CS :	 	return 500;	
			case UPEK_TOUCHCHIP : 				return 600;				
			case IDENTICATORTECHNOLOGY_DF90 : 	return 700;	
			case AUTHENTEC_AFS2 : 				return 800;				
			case AUTHENTEC_AES4000 : 			return 810;			
			case AUTHENTEC_AES2501B : 			return 811;			
			case ATMEL_FINGERCHIP : 			return 900;			
			case BMF_BLP100 : 					return 1000;					
			case SECUGEN_HAMSTER : 				return 1100;			
			case ETHENTICA : 					return 1200;					
			case CROSSMATCH_VERIFIER300 : 		return 1300;		
			case NITGEN_FINGKEY_HAMSTER : 		return 1400;		
			case TESTECH_BIOI : 				return 1500;			
			case DIGENT_IZZIX : 				return 1600;			
			case STARTEK_FM200 : 				return 1700;			
			case FUJITSU_MBF200 : 				return 1800;			
			case FUTRONIC_FS80 : 				return 1900;			
			case LIGHTUNING_LTTC500 : 			return 2000;			
			case TACOMA_CMOS : 					return 2100;			
		}
		return 0;
	}
	public static MatcherMode getVal(int intval){
		switch(intval){
			case 0   : return MatcherMode.GENERAL;					
			case 100 : return MatcherMode.DIGITALPERSONA_UAREU;
			case 200 : return MatcherMode.BIOMETRIKA_FX2000;	
			case 201 : return MatcherMode.BIOMETRIKA_FX3000;	
			case 300 : return MatcherMode.KEYTRONIC_SECUREDESKTOP;		
			case 400 : return MatcherMode.IDENTIX_TOUCHVIEW;				
			case 401 : return MatcherMode.IDENTIX_DFR2090;				
			case 500 : return MatcherMode.PRECISEBIOMETRICS_100CS;		
			case 600 : return MatcherMode.UPEK_TOUCHCHIP;					
			case 700 : return MatcherMode.IDENTICATORTECHNOLOGY_DF90;		
			case 800 : return MatcherMode.AUTHENTEC_AFS2;					
			case 810 : return MatcherMode.AUTHENTEC_AES4000;				
			case 811 : return MatcherMode.AUTHENTEC_AES2501B;	
			case 900 : return MatcherMode.ATMEL_FINGERCHIP;				
			case 1000 : return MatcherMode.BMF_BLP100;						
			case 1100 : return MatcherMode.SECUGEN_HAMSTER;				
			case 1200 : return MatcherMode.ETHENTICA;						
			case 1300 : return MatcherMode.CROSSMATCH_VERIFIER300;			
			case 1400 : return MatcherMode.NITGEN_FINGKEY_HAMSTER;			
			case 1500 : return MatcherMode.TESTECH_BIOI;				
			case 1600 : return MatcherMode.DIGENT_IZZIX;				
			case 1700 : return MatcherMode.STARTEK_FM200;				
			case 1800 : return MatcherMode.FUJITSU_MBF200;				
			case 2000 : return MatcherMode.LIGHTUNING_LTTC500;				
			case 2100 : return MatcherMode.TACOMA_CMOS;	
		}
		return MatcherMode.GENERAL;
	}
}
