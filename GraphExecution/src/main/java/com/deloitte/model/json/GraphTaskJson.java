/**
 * 
 */
package com.deloitte.model.json;

import java.util.HashSet;
import java.util.Set;

import com.deloitte.model.Node;

/**
 * @author vbejjanki
 *
 */
public class GraphTaskJson {

	private Set<NodeJson> nodes = new HashSet<>();

	private Set<EdgeJson> edges = new HashSet<>();

	private Node rootNode;

	private Node antiRootNode;

	public Set<NodeJson> getNodes() {
		return nodes;
	}

	public void setNodes(Set<NodeJson> nodes) {
		this.nodes = nodes;
	}

	public Set<EdgeJson> getEdges() {
		return edges;
	}

	public void setEdges(Set<EdgeJson> edges) {
		this.edges = edges;
	}

	public Node getRootNode() {
		return rootNode;
	}

	public void setRootNode(Node rootNode) {
		this.rootNode = rootNode;
	}

	public Node getAntiRootNode() {
		return antiRootNode;
	}

	public void setAntiRootNode(Node antiRootNode) {
		this.antiRootNode = antiRootNode;
	}

}
