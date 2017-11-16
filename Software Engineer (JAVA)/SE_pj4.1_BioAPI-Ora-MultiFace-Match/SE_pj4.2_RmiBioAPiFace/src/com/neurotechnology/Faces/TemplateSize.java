package com.neurotechnology.Faces;

import java.util.EnumSet;

public enum TemplateSize {
	
	Small (0, "Small"),
	Medium (64, "Medium"),
	Large (128, "Large");

	private final int index;
	private final String desc;
	
	TemplateSize(int index, String desc)
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
	
	public static TemplateSize parse(int value){
		for (TemplateSize it : EnumSet.allOf(TemplateSize.class)) {
			if (it.index == value) {
				return it;
			}
		}
		throw new RuntimeException("value out of bounds - " + value);
	}
}
