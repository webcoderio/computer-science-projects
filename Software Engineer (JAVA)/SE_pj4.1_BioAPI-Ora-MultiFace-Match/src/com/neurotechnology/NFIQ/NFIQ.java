package com.neurotechnology.NFIQ;

import com.neurotechnology.Library.NativeManager;
import com.neurotechnology.NImages.NImage;

public class NFIQ {
	
	public static NFIQQuality compute(NImage image) throws Exception{
		return NFIQQuality.getVal(compute(image.getHandle()));
	}
	
	private static native int compute(long handle) throws Exception;
	/*
	@Deprecated
	public static native boolean isRegistered();
	*/
	static 
	{
		NativeManager.loadDefault();
		NativeManager.checkLoad("NBiometricTools");
	}
}
