package com.neurotechnology.Communication;

import java.security.InvalidParameterException;

public enum InsertDeleteTemplateStatus {

	none,
	ok,
	internalError,
	invalidFormat,
	databaseError;
	
	public int eval(){
		switch (this) {
			case none: return 0;
			case ok: return 1;
			case internalError: return 2;
			case invalidFormat: return 3;
			case databaseError: return 4;
		}
		throw new InvalidParameterException("Invalit enum value");
	}
	
	public static InsertDeleteTemplateStatus getValue(int value){
		switch(value){
			case 0: return none;
			case 1: return ok;
			case 2: return internalError;
			case 3: return invalidFormat;
			case 4: return databaseError;
			default: throw new InvalidParameterException("Invalid value");
		}
	}
}
