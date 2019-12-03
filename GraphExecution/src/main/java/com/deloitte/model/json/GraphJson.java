package com.deloitte.model.json;

import java.util.HashMap;
import java.util.Map;

import com.deloitte.model.PlaybookTask;

public class GraphJson {

	private GraphModel executionGraph;

	private Map<Long, PlaybookTask> metadata = new HashMap<>();

	public GraphModel getExecutionGraph() {
		return executionGraph;
	}

	public void setExecutionGraph(GraphModel executionGraph) {
		this.executionGraph = executionGraph;
	}

	public Map<Long, PlaybookTask> getMetadata() {
		return metadata;
	}

	public void setMetadata(Map<Long, PlaybookTask> metadata) {
		this.metadata = metadata;
	}

}
