package com.neurotechnology.Templates;

import java.util.EnumSet;

public enum MinutiaOrder {
	Ascending (0x01, "Ascending"), //< Specifies than minutiae are sorted ascending by the specified order.
	Descending (0x02, "Descending"), //< Specifies than minutiae are sorted descending by the specified order.
	CartesianXY (0x04, "CartesianXY"), //< Specifies than minutiae are sorted by X field. If X field of two minutiae are equal Y field is compared.
	CartesianYX (0x08, "CartesianYX"), //< Specifies than minutiae are sorted by Y field. If Y field of two minutiae are equal X field is compared.
	Angle (0x0C, "Angle"), //< Specifies than minutiae are sorted by Angle field.
	Polar (0x10, "Polar"); //< Specifies than minutiae are sorted by distance from minutiae center of mass. If distance of two minutiae are equal Angle field is compared.
	
	private final int index;
	private final String desc;
	MinutiaOrder(int index, String desc)
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
	
	public static MinutiaOrder parse(int value) throws Exception {
		for (MinutiaOrder it : EnumSet.allOf(MinutiaOrder.class)) {
			if (it.index == value) {
				return it;
			}
		}
		throw new Exception("value out of bounds");
	}
}
