package de.jkueck.simple;

import de.jkueck.graph.simple.*;
import org.junit.jupiter.api.Test;

public class TestGraph {

	@Test
	public void test() {

		Node nodeA = new Node("A");
		Node nodeB = new Node("B");
		Node nodeC = new Node("C");
		Node nodeD = new Node("D");
		Node nodeE = new Node("E");
		Node nodeF = new Node("F");

		nodeA.getOut().add(new Edge(nodeA, nodeB, 10));
		nodeA.getOut().add(new Edge(nodeA, nodeC, 20));

		nodeB.getOut().add(new Edge(nodeB, nodeE, 10));
		nodeB.getOut().add(new Edge(nodeB, nodeD, 50));

		nodeC.getOut().add(new Edge(nodeC, nodeD, 20));
		nodeC.getOut().add(new Edge(nodeC, nodeE, 33));

		nodeD.getOut().add(new Edge(nodeD, nodeE, 20));
		nodeD.getOut().add(new Edge(nodeD, nodeF, 2));

		nodeE.getOut().add(new Edge(nodeE, nodeF, 1));

		Graph graph = new Graph();
		graph.getNodes().add(nodeA);
		graph.getNodes().add(nodeB);
		graph.getNodes().add(nodeC);
		graph.getNodes().add(nodeD);
		graph.getNodes().add(nodeE);
		graph.getNodes().add(nodeF);

		Dijkstra dijkstra = new Dijkstra();
		Route route = dijkstra.dijsktra(nodeA, nodeF);

		graph.resetGraph();

		for (RouteDetail routeDetail : route.getRouteDetails()) {
			System.out.println(routeDetail.getName());
		}

		System.out.println();


	}

}
