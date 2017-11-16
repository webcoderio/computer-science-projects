package com.neurotechnology.Eyes;

public enum NEExtractionStatus {
	TemplateCreated, //  = 1,
	SegmentationFailed, // = 2,
	QualityCheckFailed; // = 100,
	
	public int eval(){
		switch(this){					
			case TemplateCreated 	: 	return 1;		
			case SegmentationFailed :	return 2;	
			case QualityCheckFailed :	return 100;
		}
		return 0;
	}
	public static NEExtractionStatus getVal(int intval){
		switch(intval){					
			case 1 : 	return NEExtractionStatus.TemplateCreated;		
			case 2 :	return NEExtractionStatus.SegmentationFailed;	
			case 100 :	return NEExtractionStatus.QualityCheckFailed;	
			default : return NEExtractionStatus.TemplateCreated;
		}
		
		
	}
}
