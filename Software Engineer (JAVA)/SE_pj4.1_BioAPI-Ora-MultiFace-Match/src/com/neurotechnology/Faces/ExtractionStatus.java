package com.neurotechnology.Faces;

public enum ExtractionStatus {
	None, // = 0,
	TemplateCreated, // = 1,
	FaceNotDetected, // = 2,
	EyesNotDetected, // = 3,
	FaceTooCloseToImageBorder, // = 4,
	QualityCheckGrayscaleDensityFailed, // = 100,
	QualityCheckExposureFailed, // = 101,
	QualityCheckSharpnessFailed, // = 102,
	LivenessCheckFailed; // = 200,
	
	public int eval(){
		switch (this) {
			case None: return 0;
			case TemplateCreated: return 1;
			case FaceNotDetected: return 2;
			case EyesNotDetected: return 3;
			case FaceTooCloseToImageBorder: return 4;
			case QualityCheckGrayscaleDensityFailed: return 100;
			case QualityCheckExposureFailed: return 101;
			case QualityCheckSharpnessFailed: return 102;
			case LivenessCheckFailed: return 200;
		}
		return 0;
	}
	
	public static  ExtractionStatus fromInt(int i){
		switch(i){
			case 0: return None;  
			case 1: return TemplateCreated;
			case 2: return FaceNotDetected;
			case 3: return EyesNotDetected;
			case 4: return FaceTooCloseToImageBorder;
			case 100: return QualityCheckGrayscaleDensityFailed;
			case 101: return QualityCheckExposureFailed;
			case 102: return QualityCheckSharpnessFailed;
			case 200: return LivenessCheckFailed;
		}
		return None;
	}
}
