package com.neurotechnology.ScannerMan;

import com.neurotechnology.NImages.NImage;

public interface ImageScanned {
	void imageScannedCallback(NImage jNImage);
	void setCurrScaner(String currScaner);
}
