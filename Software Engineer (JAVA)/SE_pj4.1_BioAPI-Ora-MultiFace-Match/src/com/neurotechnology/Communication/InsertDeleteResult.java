package com.neurotechnology.Communication;

public class InsertDeleteResult {
	private InsertDeleteStatus insertDeleteStatus;
	private InsertDeleteTemplateResult [] insertDeleteTemplateResults;
	
	public void setInsertDeleteStatus(InsertDeleteStatus insertDeleteStatus) {
		this.insertDeleteStatus = insertDeleteStatus;
	}
	public InsertDeleteStatus getInsertDeleteStatus() {
		return insertDeleteStatus;
	}
	public void setInsertDeleteTemplateResult(InsertDeleteTemplateResult [] insertDeleteTemplateResult) {
		this.insertDeleteTemplateResults = insertDeleteTemplateResult;
	}
	public InsertDeleteTemplateResult [] getInsertDeleteTemplateResult() {
		return insertDeleteTemplateResults;
	}
	
	protected void init(int status, int size){
		this.insertDeleteStatus = InsertDeleteStatus.getValue(status);
		if(size != 0) this.insertDeleteTemplateResults = new InsertDeleteTemplateResult[size]; 
	}
	
	protected void setTemplateResult(int index, int status, String error){
		InsertDeleteTemplateResult insertDeleteTemplateResult = new InsertDeleteTemplateResult();
		insertDeleteTemplateResult.setStatus(InsertDeleteTemplateStatus.getValue(status));
		insertDeleteTemplateResult.setError(error);
		this.insertDeleteTemplateResults[index] = insertDeleteTemplateResult;
	}
}
