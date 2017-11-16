package com.neurotechnology.Fingers;

public enum TemplateSize {
	small,
	large;
	public int eval(){
		switch(this){
			case small : return 0;
			case large : return 256;
		}
		return 0;
	}
	public static TemplateSize getVal(int val){
		switch(val){
			case 0 : return TemplateSize.small;
			case 256 : return TemplateSize.large;
		}
		return TemplateSize.small;
	}
}
