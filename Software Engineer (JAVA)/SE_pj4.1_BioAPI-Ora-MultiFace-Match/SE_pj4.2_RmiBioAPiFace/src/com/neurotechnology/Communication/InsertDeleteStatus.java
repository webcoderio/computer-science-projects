package com.neurotechnology.Communication;

import java.security.InvalidParameterException;

public enum InsertDeleteStatus {

	notReady,
	failed,
	waiting,
	succeeded,
	partiallySucceeded;
	
	public int eval(){
		switch (this) {
			case notReady: return -2;
			case failed: return -1;
			case waiting: return 0;
			case succeeded: return 1;
			case partiallySucceeded: return 2;
		}
		throw new InvalidParameterException("Invalit enum value");
	}
	
	public static InsertDeleteStatus getValue(int value){
		switch(value){
			case -2: return notReady;
			case -1: return failed;
			case 0: return waiting;
			case 1: return succeeded;
			case 2: return partiallySucceeded;
			default: throw new InvalidParameterException("Invalid value");
		}
	}
	
}
