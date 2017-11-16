

public enum PixelFormat {
	grayscale,
	rgb,
	invalid;
	public int eval(){
		switch(this){					
			case grayscale : 	return 0x00301001;		
			case rgb :	 		return 0x00303003;	
			case invalid :		return 0;
		}
		return 0;
	}
	public static PixelFormat getVal(int intval){
		switch(intval){					
			case 0x00301001 : 	return PixelFormat.grayscale;		
			case 0x00303003 :	return PixelFormat.rgb;	
		}
		
		return PixelFormat.invalid;
	}
}
