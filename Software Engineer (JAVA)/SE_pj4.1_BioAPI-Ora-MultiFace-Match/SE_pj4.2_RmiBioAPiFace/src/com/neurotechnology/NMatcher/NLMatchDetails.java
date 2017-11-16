package com.neurotechnology.NMatcher;



public class NLMatchDetails {

	private double score;
	private int matchedIndex;
	private int faceCount;
	private FaceMatchDetail[] faceMatchDetails;
	
	
	public void setScore(double score) {
		this.score = score;
	}
	public double getScore() {
		return score;
	}
	public void setMatchedIndex(int matchedIndex) {
		this.matchedIndex = matchedIndex;
	}
	public int getMatchedIndex() {
		return matchedIndex;
	}
	public void setFaceCount(int faceCount) {
		this.faceCount = faceCount;
	}
	public int getFaceCount() {
		return faceCount;
	}
	public void setFaceMatchDetails(FaceMatchDetail[] faceMatchDetails) {
		this.faceMatchDetails = faceMatchDetails;
	}
	public FaceMatchDetail[] getFaceMatchDetails() {
		return faceMatchDetails;
	}
	
	private void intFaceMatchDetailArray(int count){
		faceMatchDetails = new FaceMatchDetail[count];
	}
	
	private void setFaceMatchDetail(int index, int matchindex, double score){
		faceMatchDetails[index] = new FaceMatchDetail(matchindex,score);
	}
	
	

}
