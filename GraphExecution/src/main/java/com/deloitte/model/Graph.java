/**
 * 
 */
package com.deloitte.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author vbejjanki
 *
 */

public class Graph {

	private Map<Node, List<Node>> graph = new HashMap<>();
	
	public List<Node> getAdjNodes(Node node) {
	    return graph.get(node);
	}
	
	public void addNode(Node node) {
		graph.putIfAbsent(node, new ArrayList<>());
	}

	public void addEdge(Node from, Set<Node> toNodes) {
		graph.get(from).addAll(toNodes);
	}
}
