package com.neurotechnology.NMatcher;

public class FingerMatchDetail {
	private int matchIndex;
	private IndexPair [] matedMinutiae;
	private int score;
	
	public void setScore(int score) {
		this.score = score;
	}
	public int getScore() {
		return score;
	}
	public void setMatedMinutiae(IndexPair [] matedMinutiae) {
		this.matedMinutiae = matedMinutiae;
	}
	public IndexPair [] getMatedMinutiae() {
		return matedMinutiae;
	}
	
	private void initMatedMinutiae(int count){
		matedMinutiae = new IndexPair [count];
	}
	
	private void addMatedMinutiae(int index, int first, int secound){
		matedMinutiae[index] = new IndexPair(first, secound);
	}
	public void setMatchIndex(int matchIndex) {
		this.matchIndex = matchIndex;
	}
	public int getMatchIndex() {
		return matchIndex;
	}
	
}
