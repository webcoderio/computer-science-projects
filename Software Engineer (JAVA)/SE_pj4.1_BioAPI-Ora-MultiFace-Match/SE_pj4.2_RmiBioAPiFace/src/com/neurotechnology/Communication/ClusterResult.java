package com.neurotechnology.Communication;

import com.neurotechnology.NMatcher.NMatchDetails;

public class ClusterResult {
	private int similarity;
	private String id;
	private NMatchDetails nMatchDetails;
	
	public void setSimilarity(int similarity) {
		this.similarity = similarity;
	}
	public int getSimilarity() {
		return similarity;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getId() {
		return id;
	}
	public void setNMatchDetails(NMatchDetails nMatchDetails) {
		this.nMatchDetails = nMatchDetails;
	}
	public NMatchDetails getNMatchDetails() {
		return nMatchDetails;
	}	
	
	private NMatchDetails getNMatchDetailsInit() {
		nMatchDetails = new NMatchDetails();
		return nMatchDetails;
	}	
}
