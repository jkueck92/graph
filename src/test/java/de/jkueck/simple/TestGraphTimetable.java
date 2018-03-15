package de.jkueck.simple;

import de.jkueck.graph.Line;
import de.jkueck.graph.Timetable;
import de.jkueck.graph.TrafficType;
import de.jkueck.graph.simple.*;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.junit.jupiter.api.Test;

public class TestGraphTimetable {

	@Test
	public void test() {

		Line line1 = new Line("S1", "E", TrafficType.TRAIN);
		Line line2 = new Line("S2", "E", TrafficType.TRAIN);
		Line line3 = new Line("S3", "E", TrafficType.TRAIN);

		Node nodeA = new Node("A");
		Node nodeB = new Node("B");
		Node nodeC = new Node("C");
		Node nodeD = new Node("D");
		Node nodeE = new Node("E");
		Node nodeF = new Node("F");

		Edge edgeAB = new Edge(nodeA, nodeB);
		edgeAB.getTimetables().add(new Timetable(line1, new LocalTime(0, 10), new LocalTime(0, 20)));
		nodeA.getOut().add(edgeAB);

		Edge edgeAF = new Edge(nodeA, nodeF);
		edgeAF.getTimetables().add(new Timetable(line3, new LocalTime(0, 12), new LocalTime(0, 14)));
		nodeA.getOut().add(edgeAF);

		Edge edgeBE = new Edge(nodeB, nodeE);
		edgeBE.getTimetables().add(new Timetable(line1, new LocalTime(0, 20), new LocalTime(0, 50)));
		nodeB.getOut().add(edgeBE);

		Edge edgeBC = new Edge(nodeB, nodeC);
		edgeBC.getTimetables().add(new Timetable(line2, new LocalTime(0, 35), new LocalTime(0, 40)));
		nodeB.getOut().add(edgeBC);

		Edge edgeCD = new Edge(nodeC, nodeD);
		edgeCD.getTimetables().add(new Timetable(line2, new LocalTime(0, 40), new LocalTime(0, 42)));
		nodeC.getOut().add(edgeCD);

		Edge edgeDE = new Edge(nodeD, nodeE);
		edgeDE.getTimetables().add(new Timetable(line2, new LocalTime(0, 42), new LocalTime(0, 44)));
		nodeD.getOut().add(edgeDE);

		Edge edgeFE = new Edge(nodeF, nodeE);
		edgeFE.getTimetables().add(new Timetable(line3, new LocalTime(0, 14), new LocalTime(0, 58)));
		nodeF.getOut().add(edgeFE);

		Graph graph = new Graph();
		graph.getNodes().add(nodeA);
		graph.getNodes().add(nodeB);
		graph.getNodes().add(nodeC);
		graph.getNodes().add(nodeD);
		graph.getNodes().add(nodeE);


		Dijkstra dijkstra = new Dijkstra();
		Route route = dijkstra.dijsktra1(nodeA, nodeE, new LocalDateTime(2018, 3, 15, 0, 5));

		graph.resetGraph();

		System.out.println(route.getRouteDetails().getFirst().getName() + " // abfahrt: " + route.getRouteDetails().getFirst().getTimetable().getDeparture());
		for (int i = 1; i < route.getRouteDetails().size(); i ++) {
			RouteDetail routeDetail = route.getRouteDetails().get(i);
			System.out.println(routeDetail.getName() + " // ankunft: " + routeDetail.getTimetable().getArrival());
		}

		System.out.println();


	}

}
