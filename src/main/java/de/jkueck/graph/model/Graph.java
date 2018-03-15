package de.jkueck.graph.model;

import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

public class Graph {

	@Getter
	private Set<Node> nodes = new HashSet<>();

	public void resetGraph() {
		for (Node node : this.nodes) {
			node.setProcessed(Boolean.FALSE);
			node.setPreviousNode(null);
			node.setCosts(Integer.MAX_VALUE);
			node.setTimetable(null);
		}
	}

}
