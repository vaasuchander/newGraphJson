/**
 * 
 */
package com.deloitte.model.jsonSerialize;

import java.io.IOException;
import java.util.Objects;

import com.deloitte.model.json.NodeJson;
import com.deloitte.model.json.TaskExecutionNodeJson;
import com.deloitte.model.json.TimeConstraintNodeJson;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

/**
 * @author vbejjanki
 *
 */
public class NodeSerializer extends StdSerializer<NodeJson> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NodeSerializer() {
		this(null);
	}

	protected NodeSerializer(Class<NodeJson> node) {
		super(node);
	}

	@Override
	public void serialize(NodeJson node, JsonGenerator gen, SerializerProvider provider) throws IOException {

		switch (node.getType()) {
		case "TaskExecutionNode":
			if(Objects.isNull(node.getTask().getConstraintDateTime())) {
				TaskExecutionNodeJson taskExecutionJson = new TaskExecutionNodeJson();
				taskExecutionJson.setNodeId(node.getNodeId());
				taskExecutionJson.setTask(node.getTask());
				gen.writeObject(taskExecutionJson);
			}else {
				TimeConstraintNodeJson timeConstraintJson = new TimeConstraintNodeJson();
				timeConstraintJson.setNodeId(node.getNodeId());
				timeConstraintJson.setConstraintDateandTime(node.getTask().getConstraintDateTime());
				timeConstraintJson.setTask(node.getTask());
				gen.writeObject(timeConstraintJson);
			}
			break;
		case "NoActionNode":
			gen.writeStartObject();
			gen.writeNumberField("nodeId", node.getNodeId());
			gen.writeEndObject();
		}

	}

}
