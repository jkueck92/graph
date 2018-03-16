package de.jkueck.graph;

import de.jkueck.graph.algorithm.Dijkstra;
import de.jkueck.graph.model.*;
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
        edgeAF.getTimetables().add(new Timetable(line3, new LocalTime(0, 12), new LocalTime(0, 15)));
        nodeA.getOut().add(edgeAF);

        Edge edgeBE = new Edge(nodeB, nodeE);
        edgeBE.getTimetables().add(new Timetable(line1, new LocalTime(0, 25), new LocalTime(0, 35)));
        nodeB.getOut().add(edgeBE);

        Edge edgeBC = new Edge(nodeB, nodeC);
        edgeBC.getTimetables().add(new Timetable(line2, new LocalTime(0, 22), new LocalTime(0, 24)));
        nodeB.getOut().add(edgeBC);

        Edge edgeCD = new Edge(nodeC, nodeD);
        edgeCD.getTimetables().add(new Timetable(line2, new LocalTime(0, 24), new LocalTime(0, 26)));
        nodeC.getOut().add(edgeCD);

        Edge edgeDE = new Edge(nodeD, nodeE);
        edgeDE.getTimetables().add(new Timetable(line2, new LocalTime(0, 26), new LocalTime(0, 29)));
        nodeD.getOut().add(edgeDE);

        Edge edgeFE = new Edge(nodeF, nodeE);
        edgeFE.getTimetables().add(new Timetable(line3, new LocalTime(0, 15), new LocalTime(0, 31)));
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

        route.printRoute();

        System.out.println("");

    }

    @Test
    public void test1() {

        Line line1 = new Line("S1", "C", TrafficType.TRAIN);
        Line line2 = new Line("S2", "C", TrafficType.TRAIN);
        Line line3 = new Line("U3", "F", TrafficType.SUBWAY);
        Line line4 = new Line("U4", "C", TrafficType.SUBWAY);

        Node nodeA = new Node("A");
        Node nodeB = new Node("B");
        Node nodeC = new Node("C");
        Node nodeD = new Node("D");
        Node nodeE = new Node("E");
        Node nodeF = new Node("F");

        Edge edgeAB = new Edge(nodeA, nodeB);
        edgeAB.getTimetables().add(new Timetable(line2, new LocalTime(0, 10), new LocalTime(0, 50)));
        nodeA.getOut().add(edgeAB);

        Edge edgeBC = new Edge(nodeB, nodeC);
        edgeBC.getTimetables().add(new Timetable(line2, new LocalTime(0, 50), new LocalTime(0, 56)));
        nodeB.getOut().add(edgeBC);

        Edge edgeAD = new Edge(nodeA, nodeD);
        edgeAD.getTimetables().add(new Timetable(line1, new LocalTime(0, 15), new LocalTime(0, 20)));
        nodeA.getOut().add(edgeAD);

        Edge edgeDE = new Edge(nodeD, nodeE);
        edgeDE.getTimetables().add(new Timetable(line3, new LocalTime(0, 25), new LocalTime(0, 27)));
        nodeD.getOut().add(edgeDE);

        Edge edgeEF = new Edge(nodeE, nodeF);
        edgeEF.getTimetables().add(new Timetable(line3, new LocalTime(0, 27), new LocalTime(0, 32)));
        nodeE.getOut().add(edgeEF);

        Edge edgeDC = new Edge(nodeD, nodeC);
        edgeDC.getTimetables().add(new Timetable(line1, new LocalTime(0, 20), new LocalTime(0, 44)));
        nodeD.getOut().add(edgeDC);

        Edge edgeFB = new Edge(nodeF, nodeB);
        edgeFB.getTimetables().add(new Timetable(line3, new LocalTime(0, 32), new LocalTime(0, 45)));
        nodeF.getOut().add(edgeFB);

        Edge edgeFC = new Edge(nodeF, nodeC);
        edgeFC.getTimetables().add(new Timetable(line4, new LocalTime(0, 35), new LocalTime(0, 37)));
        nodeF.getOut().add(edgeFC);

        Graph graph = new Graph();
        graph.getNodes().add(nodeA);
        graph.getNodes().add(nodeB);
        graph.getNodes().add(nodeC);
        graph.getNodes().add(nodeD);
        graph.getNodes().add(nodeE);


        Dijkstra dijkstra = new Dijkstra();
        Route route = dijkstra.dijsktra1(nodeA, nodeC, new LocalDateTime(2018, 3, 15, 0, 5));

        graph.resetGraph();

        route.printRoute();

    }

}
