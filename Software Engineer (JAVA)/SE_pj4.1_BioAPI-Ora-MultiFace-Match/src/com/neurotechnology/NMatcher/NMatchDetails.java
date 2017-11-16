package com.neurotechnology.NMatcher;


public class NMatchDetails {
	private NFMatchDetails nfMatchDetails;
	private NLMatchDetails nlMatchDetails;
	private int score;
	
	public void setScore(int score) {
		this.score = score;
	}
	public int getScore() {
		return score;
	}
	public void setNfMatchDetails(NFMatchDetails nfMatchDetails) {
		this.nfMatchDetails = nfMatchDetails;
	}
	
	public NFMatchDetails getNfMatchDetails() {
		return nfMatchDetails;
	}

	public void setNlMatchDetails(NLMatchDetails nlMatchDetails) {
		this.nlMatchDetails = nlMatchDetails;
	}
	public NLMatchDetails getNlMatchDetails() {
		return nlMatchDetails;
	}
	
	private NLMatchDetails getNlMatchDetailsInit() {
		nlMatchDetails = new NLMatchDetails();
		return nlMatchDetails;
	}
	
	private NFMatchDetails getNfMatchDetailsInit() {
		nfMatchDetails = new NFMatchDetails();
		return nfMatchDetails;
	}
}
