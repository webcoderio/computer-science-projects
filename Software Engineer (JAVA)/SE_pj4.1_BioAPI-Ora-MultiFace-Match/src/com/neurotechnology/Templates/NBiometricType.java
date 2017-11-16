package com.neurotechnology.Templates;

import java.util.EnumSet;

public enum NBiometricType {
	
	nbtFacialFeatures(0x00000002, "FacialFeatures"),
	nbtFingerprint(0x00000008, "Fingerprint"),
	nbtIris(0x00000010, "Iris"),
	nbtPalmPrint(0x00020000, "PalmPrint");

	private final int index;
	private final String desc;
	NBiometricType(int index, String desc)
	{
		this.index = index;
		this.desc = desc;
	}
	
	public int eval() {
		return index;
	}
	
	public String toString() {
		return desc;
	}
	
	public static NBiometricType parse(int value) throws Exception {
		for (NBiometricType it : EnumSet.allOf(NBiometricType.class)) {
			if (it.index == value) {
				return it;
			}
		}
		throw new Exception("value out of bounds");
	}
}
