package com.neurotechnology.NMatcher;

public class MatchDetails {
	private int Score;
	private IndexPair[] MatedMinutiae;
	
	private void setPeirCount(int count){
		MatedMinutiae = new IndexPair[count];
	}
	
	public void setScore(int score) {
		Score = score;
	}
	public int getScore() {
		return Score;
	}

	private void addMatedMinutiae(int index1,int index2, int i){
		MatedMinutiae[i] = new IndexPair(index1,index2);
	}
}
