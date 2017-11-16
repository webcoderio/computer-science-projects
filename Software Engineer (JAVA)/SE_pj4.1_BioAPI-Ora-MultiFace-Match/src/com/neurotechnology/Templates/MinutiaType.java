package com.neurotechnology.Templates;

public enum MinutiaType {
		nfmtUnknown,		// = 0 < The type of the minutia is unknown.
		nfmtEnd,   			// = 1 < The minutia that is an end of a ridge.
		nfmtBifurcation;	// = 2 < The minutia that is a bifurcation of a ridge.
		
		public int eval(){
			switch (this){
				case nfmtUnknown : 		return 0;		
				case nfmtEnd : 			return 1;	
				case nfmtBifurcation : 	return 2; 	
			}
			return 0;
		}
}
