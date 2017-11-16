package com.neurotechnology.NMatcher;

public class IndexPair {
	private int Index1; 
	private int Index2;
	
	public IndexPair(){
		
	}
	IndexPair(int Index1,int Index2){
		this.Index1 = Index1;
		this.Index2 = Index2;
	}
	
	public void setIndex1(int index1) {
		Index1 = index1;
	}
	public int getIndex1() {
		return Index1;
	}
	public void setIndex2(int index2) {
		Index2 = index2;
	}
	public int getIndex2() {
		return Index2;
	} 
}
