package com.neurotechnology.Communication;

public class InsertDeleteTemplateResult {
	private InsertDeleteTemplateStatus status;
	private String error;
	
	public void setStatus(InsertDeleteTemplateStatus status) {
		this.status = status;
	}
	public InsertDeleteTemplateStatus getStatus() {
		return status;
	}
	
	public void setError(String error) {
		this.error = error;
	}
	public String getError() {
		return error;
	}

}
