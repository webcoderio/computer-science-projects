package com.neurotechnology.Communication;


public class ClusterNodeState {

	private int ID;
	private NodeState state;
	
	public void setID(int iD) {
		ID = iD;
	}
	public int getID() {
		return ID;
	}
	public void setState(NodeState state) {
		this.state = state;
	}
	public NodeState getState() {
		return state;
	}
	
	private void setValue(int id, int state){
		this.ID = id;
		switch (state){
			case 1: this.state = NodeState.starting; break;
			case 2: this.state = NodeState.ready; break;
			case 3: this.state = NodeState.removing; break;
		}
	}

}
