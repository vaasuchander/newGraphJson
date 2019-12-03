/**
 * 
 */
package com.deloitte.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.deloitte.model.Edge;
import com.deloitte.model.Graph;
import com.deloitte.model.NoActionNode;
import com.deloitte.model.Node;
import com.deloitte.model.PlayBook;
import com.deloitte.model.PlaybookTask;
import com.deloitte.model.TaskExecutionNode;
import com.deloitte.model.json.EdgeJson;
import com.deloitte.model.json.GraphJson;
import com.deloitte.model.json.GraphModel;
import com.deloitte.model.json.GraphTaskJson;
import com.deloitte.model.json.NodeJson;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author vbejjanki
 *
 */
@Service
public class JsonService {

	Logger log = Logger.getLogger(JsonService.class.getName());

	@Inject
	private GraphService graphService;

	@Inject
	private ObjectMapper objMapper;

	@Inject
	private DataService dataService;

	/**
	 * @param This Method is used to Execute the PlayBook
	 * @return
	 */
	public String exec(final PlayBook playBook) {
		final GraphModel graphModel = graphService.buildGraphModel(playBook);
		String json = null;
		try {
			GraphJson graphJson = processJson(graphModel, playBook.getId());
			json = objMapper.writeValueAsString(graphJson);
		} catch (JsonProcessingException e) {
			log.warning("The Json Parsing has been Failed.");
		}
		log.info("The Graph Json: " + json);
		return json;
	}

	/**
	 * This Method is used to Generate the Graph in Json Tasks
	 * 
	 * @param graphModel
	 * @param playBookId
	 * @return
	 */
	public GraphTaskJson processJsonTasks(GraphModel graphModel, long playBookId) {
		final Map<Long, PlaybookTask> mapTasks = dataService.getMapTaskList(playBookId);
		GraphTaskJson graphTaskJson = new GraphTaskJson();
		graphTaskJson.setAntiRootNode(graphModel.getAntiRootNode());
		graphTaskJson.setRootNode(graphModel.getRootNode());
		graphTaskJson.setNodes(graphModel.getNodes().stream().map(node -> {
			final NodeJson nodeJson = new NodeJson();
			nodeJson.setNodeId(node.getNodeId());
			if (node instanceof TaskExecutionNode) {
				nodeJson.setType("TaskExecutionNode");
				PlaybookTask task = mapTasks.get(node.getTaskId());
				if (Objects.nonNull(task)) {
					nodeJson.setTask(task);
				}
			} else if (node instanceof NoActionNode) {
				nodeJson.setType("NoActionNode");
			}
			return nodeJson;
		}).collect(Collectors.toSet()));
		graphTaskJson.setEdges(graphModel.getEdges().stream().map(edge -> {
			EdgeJson eJson = new EdgeJson();
			eJson.setFrom(edge.getFromNode().getNodeId());
			if (Objects.nonNull(edge.getToNodes())) {
				for (Node node : edge.getToNodes()) {
					eJson.getTo().add(node.getNodeId());
				}
			}
			return eJson;
		}).collect(Collectors.toSet()));
		return graphTaskJson;
	}

	/**
	 * This Method is used to Generate the Graph in Json
	 * 
	 * @param graphModel
	 * @param playBookId
	 * @return
	 */
	public GraphJson processJson(GraphModel graphModel, long playBookId) {
		GraphJson gJson = new GraphJson();
		gJson.setExecutionGraph(graphModel);
		Map<Long, PlaybookTask> mapTasks = dataService.getMapTaskList(playBookId);
		Map<Long, PlaybookTask> execTasksMap = new HashMap<>();
		for (Node node : graphModel.getNodes()) {
			if (Objects.nonNull(mapTasks.get(node.getTaskId()))) {
				execTasksMap.putIfAbsent(node.getExecutionId(), mapTasks.get(node.getTaskId()));
			}
		}
		gJson.setMetadata(execTasksMap);
		return gJson;
	}

	/**
	 * Upadte start Time of Each Task
	 * 
	 * @param graphModel
	 * @param execTasksMap
	 * @param root
	 */
	public void updateStartTime(GraphModel graphModel, Map<Long, PlaybookTask> execTasksMap, Node root) {
		Graph graph = new Graph();
		graphModel.getNodes().forEach(graph::addNode);
		for (Edge edge : graphModel.getEdges()) {
			graph.addEdge(edge.getFromNode(), edge.getToNodes());
		}
		bfsStartTimeUpdate(graph, execTasksMap, root);

	}

	/**
	 * Breadth First Traversal to Update the StartTime
	 * 
	 * @param graph
	 * @param execTasksMap
	 * @param root
	 */
	private void bfsStartTimeUpdate(Graph graph, Map<Long, PlaybookTask> execTasksMap, Node root) {
		Set<Node> visited = new LinkedHashSet<>();
		Queue<Node> queue = new LinkedList<>();
		PlaybookTask task = null;
		LocalDateTime dateTime = null;
		LocalDateTime plannedDateTime = null;
		queue.add(root);
		visited.add(root);
		while (!queue.isEmpty()) {
			Node node = queue.poll();
			for (Node adjNode : graph.getAdjNodes(node)) {
				if (!visited.contains(adjNode)) {
					task = execTasksMap.get(adjNode.getExecutionId());
					if (Objects.nonNull(task)) {
						dateTime = task.getReleaseToEnvironment().getStartDateAndTime();
						plannedDateTime = dateTime.plusMinutes(task.getPlannedDuration());
						task.setPlannedStartDateAndTime(dateTime);
						task.setPlannedEndDateAndTime(plannedDateTime);
					}
					visited.add(adjNode);
					queue.add(adjNode);
				}
			}
		}

	}

}
