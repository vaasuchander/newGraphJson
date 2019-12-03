/**
 * 
 */
package com.deloitte.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Predicate;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.deloitte.model.Edge;
import com.deloitte.model.NoActionNode;
import com.deloitte.model.Node;
import com.deloitte.model.PlayBook;
import com.deloitte.model.PlaybookTask;
import com.deloitte.model.TaskExecutionNode;
import com.deloitte.model.TimeConstraintNode;
import com.deloitte.model.json.GraphModel;

/**
 * @author vbejjanki
 *
 */

@Service
public class GraphService {

	Logger log = Logger.getLogger(GraphService.class.getName());

	@Inject
	private DataService dataService;

	private AtomicLong seq = new AtomicLong(1);

	private AtomicLong playBookId;

	/**
	 * This Method is used to build the GraphModel by taking the playBook Parameter
	 * 
	 * @param playBook
	 * @return graphModel
	 */
	public GraphModel buildGraphModel(PlayBook playBook) {

		List<PlaybookTask> tasksList = playBook.getTasks();
		playBookId = new AtomicLong(playBook.getId());
		dataService.setPlayBookTasks(playBook);
		Map<PlaybookTask, Node> entryPointCollection = new HashMap<>();
		Map<PlaybookTask, Node> exitPointCollection = new HashMap<>();
		List<Edge> edges = new ArrayList<>();
		Set<Node> nodes = new HashSet<>();

		GraphModel graph = new GraphModel();
		Node rootNode = new NoActionNode(getSequence(), getSequence());
		Node antiRootNode = new NoActionNode(getSequence(), getSequence());
		Node taskExecutionNode = null;
		Node noActionNode = null;
		Node entryPointNode = null;
		Node exitPointNode = new NoActionNode(getSequence(), getSequence());
		Edge edge = null;

		for (PlaybookTask task : tasksList) {
			taskExecutionNode = new TaskExecutionNode(getSequence(), task.getPlaybookTaskId(), getSequence());
			nodes.add(taskExecutionNode);
			if (startToStartFilter(task)) {
				noActionNode = new NoActionNode(getSequence(), getSequence());
				entryPointNode = noActionNode;
				nodes.add(noActionNode);
				nodes.add(entryPointNode);
				edge = new Edge(noActionNode, entryPointNode);
				edges.add(edge);
			} else {
				entryPointNode = taskExecutionNode;
				entryPointCollection.put(task, taskExecutionNode);
			}
			entryPointCollection.put(task, entryPointNode);
			exitPointCollection.put(task, exitPointNode);
			if (isDependentFilter(task)) {
				nodes.add(rootNode);
				nodes.add(entryPointNode);
				edge = new Edge(rootNode, entryPointNode);
				edges.add(edge);
			}
			if (isIndependentFilter(task)) {
				nodes.add(exitPointNode);
				nodes.add(antiRootNode);
				edge = new Edge(exitPointNode, antiRootNode);
				edges.add(edge);
			}
		}
		nodes.add(exitPointNode);
		List<PlaybookTask> predTaskList = tasksList.stream()
				.filter(task -> Objects.nonNull(task.getPredecessorConstraint())).collect(Collectors.toList());
		Map<Long, PlaybookTask> tasksMap = dataService.getMapTaskList(playBook.getId());

		for (PlaybookTask task : predTaskList) {
			PlaybookTask task1 = tasksMap.get(task.getPredecessorConstraint().getId());
			PlaybookTask task2 = task;
			Node node1 = entryPointCollection.get(task1);
			Node node2 = exitPointCollection.get(task2);
			nodes.add(node1);
			nodes.add(node2);
			edge = new Edge(node1, node2);
			edges.add(edge);
		}
		graph.setRootNode(rootNode);
		graph.setAntiRootNode(antiRootNode);
		graph.setNodes(nodes);
		Collections.sort(edges);
		graph.setEdges(edges);
		return graph;
	}
	
	/**
	 * Evaluates this start to Start Filter predicate on the given given Task
	 * 
	 * @param task
	 * @return
	 */
	private boolean startToStartFilter(PlaybookTask task) {
		Predicate<PlaybookTask> nullPredfilter = t -> Objects.nonNull(t.getPredecessorConstraint());
		Predicate<PlaybookTask> filter = t -> "start-to-start"
				.equals(t.getPredecessorConstraint().getPredecessorConstraintType().getName());
		return nullPredfilter.and(filter).test(task);
	}

	/**
	 * Evaluates this Dependent Filter predicate on the given given Task
	 *  
	 * @param task
	 * @return
	 */
	private boolean isDependentFilter(PlaybookTask task) {
		Predicate<PlaybookTask> hasParent = t -> t.isParent();
		Predicate<PlaybookTask> hasConstarintDateTime = t -> t.getConstraintDateTime() != null;
		Predicate<PlaybookTask> hasPredecessor = t -> t.getPredecessorConstraint() != null;
		return hasParent.negate().and(hasConstarintDateTime.negate()).and(hasPredecessor.negate()).test(task);
	}

	/**
	 *  Evaluates this independent Filter predicate on the given given Task
	 * @param task
	 * @return
	 */
	private boolean isIndependentFilter(PlaybookTask task) {
		Predicate<PlaybookTask> isIndependent = t -> Objects.nonNull(t.getConstraintDateTime());
		return isIndependent.test(task);
	}

	/**
	 *  This Method is used to calculate the Planned satrt and End Time
	 * @param node
	 */
	private void updateTask(Node node) {
		PlaybookTask task = dataService.getMapTaskList(playBookId.get()).get(node.getTaskId());
		LocalDateTime plannedStartDateAndTime = task.getPlannedEndDateAndTime();
		LocalDateTime endTime = node.getEndTime(); // Need to clarify
		if (node instanceof TimeConstraintNode) {
			LocalDateTime constraintDateAndTime = task.getConstraintDateTime();
			if (endTime.isAfter(constraintDateAndTime))
				plannedStartDateAndTime = constraintDateAndTime;
			else
				plannedStartDateAndTime = endTime;
			if (plannedStartDateAndTime.isAfter(constraintDateAndTime)) {
				log.warning("Start date and Time will be after constraints Date and Time");
			}
			task.setPlannedStartDateAndTime(plannedStartDateAndTime);
		} else {
			plannedStartDateAndTime = endTime; // Need To Clarify
		}
		task.setPlannedEndDateAndTime(plannedStartDateAndTime.plusMinutes(task.getPlannedDuration()));
	}

	/**Method is used to Generate Sequence Number
	 * 
	 * @return
	 */
	private long getSequence() {
		return seq.getAndIncrement();
	}
}
