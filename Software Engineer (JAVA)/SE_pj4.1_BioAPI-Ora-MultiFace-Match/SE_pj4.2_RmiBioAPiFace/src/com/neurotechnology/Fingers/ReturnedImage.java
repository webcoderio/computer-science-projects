package com.neurotechnology.Fingers;

public enum ReturnedImage {
	None,
	Binarized,
	Skeletonized;
	public int eval(){
		switch(this){
			case None : return 0;
			case Binarized : return 1;
			case Skeletonized : return 2;
		}
		return 0;
	}
	public static ReturnedImage getVal(int val){
		switch(val){
			case 0 : return ReturnedImage.None;
			case 1 : return ReturnedImage.Binarized;
			case 2 : return ReturnedImage.Skeletonized;
		}
		return ReturnedImage.None;
	}
}
