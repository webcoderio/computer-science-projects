package com.neurotechnology.Communication;

public class ClusterShortInfo {

	private int taskId;
	private int nodesCompleted;
	private int workingNodesCount;
	
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
	public int getTaskId() {
		return taskId;
	}
	public void setNodesCompleted(int nodesCompleted) {
		this.nodesCompleted = nodesCompleted;
	}
	public int getNodesCompleted() {
		return nodesCompleted;
	}
	public void setWorkingNodesCount(int workingNodesCount) {
		this.workingNodesCount = workingNodesCount;
	}
	public int getWorkingNodesCount() {
		return workingNodesCount;
	}
	
	private void setValue(int taskId, int nodesCompleted, int workingNodesCount){
		this.taskId = taskId;
		this.nodesCompleted = nodesCompleted;
		this.workingNodesCount = workingNodesCount;
	}

}
