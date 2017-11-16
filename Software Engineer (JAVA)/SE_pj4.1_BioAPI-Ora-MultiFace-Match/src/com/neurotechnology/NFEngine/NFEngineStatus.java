package com.neurotechnology.NFEngine;

public enum NFEngineStatus {
	None,
	TemplateCreated,
	NoScanner,
	ScannerTimeout,
	QualityCheckFailed;
	
	public int eval(){
		switch(this){
			case None : return 0;
			case TemplateCreated : return 1;
			case NoScanner : return 2;
			case ScannerTimeout : return 3;
			case QualityCheckFailed : return 100;
		}
		return 0;
	}
	
	public static NFEngineStatus getVal(int val){
		switch(val){
			case 0 : return NFEngineStatus.None;
			case 1 : return NFEngineStatus.TemplateCreated;
			case 2 : return NFEngineStatus.NoScanner;
			case 3 : return NFEngineStatus.ScannerTimeout;
			case 100 : return NFEngineStatus.QualityCheckFailed;
		}
		return NFEngineStatus.None;
	}
}
