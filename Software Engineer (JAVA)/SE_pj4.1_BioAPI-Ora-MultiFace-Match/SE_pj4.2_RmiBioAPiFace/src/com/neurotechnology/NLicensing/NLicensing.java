package com.neurotechnology.NLicensing;

import com.neurotechnology.Library.NativeManager;

public class NLicensing {
	
	public static boolean licenseObtain(String server, String port, String product){
		if(server == null || port == null || product == null) throw new NullPointerException();
		return nlicenseObtain(server, port, product);
	}
	public static void licenseRelease(String product){
		if (product == null) throw new NullPointerException();
		nlicenseRelease(product);
	}
	
	public static NLicenseInfo getNLicenseInfo(String product){
		if (product == null) throw new NullPointerException();
		NLicenseInfo info = new NLicenseInfo();
		getNLicenseInfo(product, info);
		return info;
	}
	
	public static native boolean nlicenseObtain(String server, String port, String product);
	public static native void nlicenseRelease(String product);
	private static native void getNLicenseInfo(String product, NLicenseInfo info);
	
	static{
		NativeManager.loadDefault();
		NativeManager.checkLoad("NLicensing");
	}
}
