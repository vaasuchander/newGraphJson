/**
 * 
 */
package com.deloitte.model.json;

import com.deloitte.model.PlaybookTask;

/**
 * @author vbejjanki
 *
 */
public class TaskExecutionNodeJson {

	private long nodeId;

	private PlaybookTask task;

	public PlaybookTask getTask() {
		return task;
	}

	public void setTask(PlaybookTask task) {
		this.task = task;
	}

	public long getNodeId() {
		return nodeId;
	}

	public void setNodeId(long nodeId) {
		this.nodeId = nodeId;
	}

}
