package com.neurotechnology.Communication;

import java.util.ArrayList;
import java.util.List;

import com.neurotechnology.Templates.NRecord;

public class Task {
	private NRecord nRecord;
	private String query;
	private List<TaskParameter> taskParameters;
	private TaskMode mode;
	private int resultLimit;
	
	public Task(){
		taskParameters = new ArrayList<TaskParameter>();
	}
	

	public void setQuery(String query) {
		this.query = query;
	}
	public String getQuery() {
		return query;
	}
	
	public void addParameter(TaskParameter parameter){
		taskParameters.add(parameter);
	}
	
	public void removeParameter(TaskParameter parameter){
		taskParameters.remove(parameter);
	}
	
	public void removeParameter(int index){
		taskParameters.remove(index);
	}
	
	public List<TaskParameter> getTaskParameters(){
		return taskParameters;
	}
	
	public TaskParameter[] getParameters(){
		TaskParameter taskParam[] = new TaskParameter[taskParameters.size()];
		for (int i = 0; i < taskParameters.size(); i++)
			taskParam[i] = taskParameters.get(i);
		return taskParam;
	}
	
	public void setMode(TaskMode mode) {
		this.mode = mode;
	}
	public TaskMode getMode() {
		return mode;
	}

	public void setResultLimit(int resultLimit) {
		this.resultLimit = resultLimit;
	}

	public int getResultLimit() {
		return resultLimit;
	}


	public void setNRecord(NRecord nRecord) {
		this.nRecord = nRecord;
	}


	public NRecord getNRecord() {
		return nRecord;
	}
}
