package de.jkueck.graph.dijkstra;

import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDijkstra {

    @Test
    public void test01() {

        Line line1 = new Line("S1", null, TrafficType.BUS);
        Line line2 = new Line("S2", null, TrafficType.TRAIN);
        Line line7 = new Line("S7", null, TrafficType.BUS);
        Line line8 = new Line("S8", null, TrafficType.SUBWAY);

        Node nodeA = new Node("A");
        Node nodeB = new Node("B");
        Node nodeC = new Node("C");
        Node nodeD = new Node("D");

        Edge edgeAB = new Edge(nodeA, nodeB, 10);
        edgeAB.getTimetables().add(new Timetable(line2, new LocalTime(0, 10), new LocalTime(0, 20)));

        Edge edgeAC = new Edge(nodeA, nodeC, 13);
        edgeAC.getTimetables().add(new Timetable(line1, new LocalTime(0, 7), new LocalTime(0, 21)));

        Edge edgeBD = new Edge(nodeB, nodeD, 2);
        edgeBD.getTimetables().add(new Timetable(line2, new LocalTime(0, 20), new LocalTime(0, 22)));

        Edge edgeCB = new Edge(nodeC, nodeB, 3);
        edgeCB.getTimetables().add(new Timetable(line7, new LocalTime(0, 21), new LocalTime(0, 24)));

        Edge edgeCD = new Edge(nodeC, nodeD, 5);
        edgeCD.getTimetables().add(new Timetable(line8, new LocalTime(0, 25), new LocalTime(0, 30)));

        nodeA.getOut().add(edgeAB);
        nodeA.getOut().add(edgeAC);

        nodeB.getOut().add(edgeBD);

        nodeC.getOut().add(edgeCB);
        nodeC.getOut().add(edgeCD);

        Graph graph = new Graph();
        graph.getNodes().add(nodeA);
        graph.getNodes().add(nodeB);
        graph.getNodes().add(nodeC);
        graph.getNodes().add(nodeD);

        Dijkstra dijkstra = new Dijkstra();
        Route route = dijkstra.dijkstra02(new DijkstraRequest(nodeA, nodeD, new LocalDateTime(2018, 3, 16, 0, 5), 1));

        assertEquals(3, route.getRouteDetails().size());
        assertEquals(1, route.getChanges());
        assertEquals("A", route.getRouteDetails().get(0).getName());
        assertEquals("B", route.getRouteDetails().get(1).getName());
        assertEquals("D", route.getRouteDetails().get(2).getName());

    }

}

