package com.neurotechnology.NMatcher;

public class FaceMatchDetail {

	private int matchIndex;
	private double score;
	
	FaceMatchDetail(int matchIndex, double score){
		this.matchIndex = matchIndex;
		this.score = score;
	}
	
	public void setMatchIndex(int matchIndex) {
		this.matchIndex = matchIndex;
	}
	public int getMatchIndex() {
		return matchIndex;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public double getScore() {
		return score;
	}

}
