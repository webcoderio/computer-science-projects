package com.neurotechnology.Fingers;

import java.util.EnumSet;

/// <summary>Specifies pattern class of the fingerprint.</summary>
/// <remarks>This enumeration is implemented according to ANSI/NIST-ITL 1-2000 standard.</remarks>
public enum PatternClass {
	Unknown (0, "Unknown"), //< Unknown pattern class.
	PlainArch (1, "Plain arch"), //< Plain arch pattern class.
	TentedArch (2, "Tented arch"), //< Tented arch pattern class.
	RadialLoop (3, "Radial loop"), //< Radial loop pattern class.
	UlnarLoop (4, "Ulnar loop"), //< Ulnar loop pattern class.
	PlainWhorl (5, "Plain whorl"), //< Plain whorl pattern class.
	CentralPocketLoop (6, "Central pocket loop"), //< Central pocket loop pattern class.
	DoubleLoop (7, "Double loop"), //< Double loop pattern class.
	AccidentalWhorl (8, "Accidental whorl"), //< Accidental whorl pattern class.
	Whorl (9, "Whorl"), //< Whorl pattern class.
	RightSlantLoop (10, "Right slant loop"), //< Right slant loop pattern class.
	LeftSlantLoop (11, "Left slant loop"), //< Left slant loop pattern class.
	Scar (12, "Scar"), //< Scar. Pattern class is not available.
	Amputation (15, "Amputation"); //< Amputation. Pattern class is not available.
	
	private final int index;
	private final String desc;
	PatternClass(int index, String desc)
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
	
	public static PatternClass parse(int value) throws Exception {
		for (PatternClass it : EnumSet.allOf(PatternClass.class)) {
			if (it.index == value) {
				return it;
			}
		}
		throw new Exception("value out of bounds");
	}
}
