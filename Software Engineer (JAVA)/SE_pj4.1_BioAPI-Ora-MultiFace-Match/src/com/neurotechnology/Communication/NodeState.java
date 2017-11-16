package com.neurotechnology.Communication;

public enum NodeState {
	starting,
	ready,
	removing,
	spare;
	
	public int eval(){
		switch(this){
			case starting: return 1;
			case ready:	return 2;
			case removing: return 3;
			case spare: return 4;
		}
		return 1;
	}
}
