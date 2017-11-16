package com.neurotechnology.NMatcher;

public class NFMatchDetails {
	private int fingerCount;
	private int score;
	private FingerMatchDetail[] matchedFingers;
	
	public void setMatchedFingers(FingerMatchDetail[] matchedFingers) {
		this.matchedFingers = matchedFingers;
	}
	public FingerMatchDetail[] getMatchedFingers() {
		return matchedFingers;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getScore() {
		return score;
	}
	public void setFingerCount(int fingerCount) {
		this.fingerCount = fingerCount;
	}
	public int getFingerCount() {
		return fingerCount;
	}
	
	private void initMatchedFingers(int count){
		matchedFingers = new FingerMatchDetail[count];
	}
	
	private FingerMatchDetail getFingerMatchDetail(int index){
		matchedFingers[index] = new FingerMatchDetail();
		return matchedFingers[index];
	}
}
