package com.neurotechnology.Communication;


public class TaskResult {
	private int progress;
	private int status;
	private String error;
	private ClusterResult res[];
	
	public void setProgress(int progress) {
		this.progress = progress;
	}
	public int getProgress() {
		return progress;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getStatus() {
		return status;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getError() {
		return error;
	}
	public void setRes(ClusterResult[] res) {
		this.res = res;
	}
	public ClusterResult[] getRes() {
		return res;
	}
	
	/*called only from native side*/
	private void initClusterResult(int count){
		res = new ClusterResult[count];
	}
	
	/*called only from native side*/
	private ClusterResult getResElement(int index){
		res[index] = new ClusterResult();
		return res[index];
	}
}
