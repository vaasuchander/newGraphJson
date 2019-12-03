package com.deloitte.model.json;

import java.time.LocalDateTime;

import com.deloitte.model.PlaybookTask;
import com.deloitte.model.jsonSerialize.DateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class TimeConstraintNodeJson {

	private long nodeId;

	@JsonSerialize(using = DateSerializer.class)
	private LocalDateTime constraintDateandTime;

	private PlaybookTask task;

	public long getNodeId() {
		return nodeId;
	}

	public void setNodeId(long nodeId) {
		this.nodeId = nodeId;
	}

	public LocalDateTime getConstraintDateandTime() {
		return constraintDateandTime;
	}

	public void setConstraintDateandTime(LocalDateTime constraintDateandTime) {
		this.constraintDateandTime = constraintDateandTime;
	}

	public PlaybookTask getTask() {
		return task;
	}

	public void setTask(PlaybookTask task) {
		this.task = task;
	}

}
