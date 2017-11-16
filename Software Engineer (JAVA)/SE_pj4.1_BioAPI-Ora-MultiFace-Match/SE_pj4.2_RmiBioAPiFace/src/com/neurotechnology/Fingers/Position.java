package com.neurotechnology.Fingers;

import java.util.EnumSet;


public enum Position {
	nfpUnknown (0, "Unknown"), //< Unknown finger
	nfpRightThumb (1, "Right thumb"), //< Right thumb
	nfpRightIndex (2, "Right index"), //< Right index finger
	nfpRightMiddle (3, "Right middle"), //< Right middle finger
	nfpRightRing (4, "Right ring"), //< Right ring finger
	nfpRightLittle (5, "Right little"), //< Right little finger
	nfpLeftThumb (6, "Left thumb"), //< Left thumb finger
	nfpLeftIndex (7, "Left index"), //< Left index finger
	nfpLeftMiddle (8, "Left middle"), //< Left middle finger
	nfpLeftRing (9, "Left ring"), //< Left ring finger
	nfpLeftLittle (10, "Left little"), //< Left little finger
	nfpPlainRightThumb (11, "Plain right thumb"), //< Plain right thumb
	nfpPlainLeftThumb (12, "Plain left thumb"), //< Plain left thumb
	nfpPlainRightFourFingers (13, "Plain right four fingers"), 	//< Plain right four fingers
	nfpPlainLeftFourFingers (14, "Plain left four fingers"); 	//< Plain left four fingers
	
	private final int index;
	private final String desc;
	Position(int index, String desc)
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
	
	public static Position parse(int value) throws Exception {
		for (Position it : EnumSet.allOf(Position.class)) {
			if (it.index == value) {
				return it;
			}
		}
		throw new Exception("value out of bounds");
	}
}
