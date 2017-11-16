package com.neurotechnology.Communication;

public class ClusterTaskInfo {

	private int taskId;
	private int nodesCompleted;
	private int workingWodesCount;
	private int taskProgress;
	private ClusterNodeInfo [] workingNodesInfo;
	
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
	public void setWorkingWodesCount(int workingWodesCount) {
		this.workingWodesCount = workingWodesCount;
	}
	public int getWorkingWodesCount() {
		return workingWodesCount;
	}
	public void setTaskProgress(int taskProgress) {
		this.taskProgress = taskProgress;
	}
	public int getTaskProgress() {
		return taskProgress;
	}
	public void setWorkingNodesInfo(ClusterNodeInfo [] workingNodesInfo) {
		this.workingNodesInfo = workingNodesInfo;
	}
	public ClusterNodeInfo [] getWorkingNodesInfo() {
		return workingNodesInfo;
	}
	
	private void setValue(int taskid, int nodesCompleted, int workingnodes, int taskprogres){
		this.taskId = taskid;
		this.nodesCompleted = nodesCompleted;
		this.workingWodesCount = workingnodes;
		this.taskProgress = taskprogres;
		workingNodesInfo = new ClusterNodeInfo[workingnodes];
	}
	
	private void initClusterNodeInfo(int count){
		workingNodesInfo = new ClusterNodeInfo[count];
	}
	
	private void setClusterNodeInfo(int index, int nodeId, int progress){
		workingNodesInfo[index] = new ClusterNodeInfo();
		workingNodesInfo[index].setNodeId(nodeId);
		workingNodesInfo[index].setProgress(progress);
	}

}
