package de.jkueck.graph.algorithm;

import de.jkueck.graph.model.DijkstraRequest;
import de.jkueck.graph.model.Route;
import de.jkueck.graph.model.graph.*;
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

        Edge edgeAB = new Edge(nodeA, nodeB);
        edgeAB.getTimetables().add(new Timetable(line2, new LocalTime(0, 10), new LocalTime(0, 20)));

        Edge edgeAC = new Edge(nodeA, nodeC);
        edgeAC.getTimetables().add(new Timetable(line1, new LocalTime(0, 7), new LocalTime(0, 21)));

        Edge edgeBD = new Edge(nodeB, nodeD);
        edgeBD.getTimetables().add(new Timetable(line2, new LocalTime(0, 20), new LocalTime(0, 22)));

        Edge edgeCB = new Edge(nodeC, nodeB);
        edgeCB.getTimetables().add(new Timetable(line7, new LocalTime(0, 21), new LocalTime(0, 24)));

        Edge edgeCD = new Edge(nodeC, nodeD);
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
        // assertEquals(1, route.getChanges());
        assertEquals("A", route.getRouteDetails().get(0).getName());
        assertEquals("B", route.getRouteDetails().get(1).getName());
        assertEquals("D", route.getRouteDetails().get(2).getName());

    }

    @Test
    public void test02() {

        Line line1 = new Line("S1", "B", TrafficType.TRAIN);
        Line line2 = new Line("S2", "B", TrafficType.TRAIN);
        Line line3 = new Line("S3", "B", TrafficType.TRAIN);

        Node nodeA = new Node("A");
        Node nodeB = new Node("B");
        Node nodeC = new Node("C");

        Edge edgeAB = new Edge(nodeA, nodeB);
        edgeAB.getTimetables().add(new Timetable(line2, new LocalTime(0, 5), new LocalTime(0, 38)));

        Edge edgeAC = new Edge(nodeA, nodeC);
        edgeAC.getTimetables().add(new Timetable(line1, new LocalTime(0, 10), new LocalTime(0, 25)));

        Edge edgeCB = new Edge(nodeC, nodeB);
        edgeCB.getTimetables().add(new Timetable(line3, new LocalTime(0, 26), new LocalTime(0, 35)));

        nodeA.getOut().add(edgeAB);
        nodeA.getOut().add(edgeAC);
        nodeC.getOut().add(edgeCB);

        Dijkstra dijkstra = new Dijkstra();
        Route route = dijkstra.dijkstra02(new DijkstraRequest(nodeA, nodeB, new LocalDateTime(2018, 3, 15, 0,3), Integer.MAX_VALUE));

        assertEquals(3, route.getRouteDetails().size());
        assertEquals(1, route.getChanges());
        assertEquals("A", route.getRouteDetails().get(0).getName());
        assertEquals("C", route.getRouteDetails().get(1).getName());
        assertEquals("B", route.getRouteDetails().get(2).getName());
    }

}

