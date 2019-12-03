/**
 * 
 */
package com.deloitte.model.json;

import com.deloitte.model.PlaybookTask;
import com.deloitte.model.jsonSerialize.NodeSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @author vbejjanki
 *
 */
@JsonSerialize(using = NodeSerializer.class)
public class NodeJson {

	private long nodeId;

	@JsonIgnore
	private String type;
	
	private PlaybookTask task;

	public long getNodeId() {
		return nodeId;
	}

	public void setNodeId(long nodeId) {
		this.nodeId = nodeId;
	}

	public PlaybookTask getTask() {
		return task;
	}

	public void setTask(PlaybookTask task) {
		this.task = task;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
