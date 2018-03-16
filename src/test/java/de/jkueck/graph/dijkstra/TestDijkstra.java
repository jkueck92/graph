package de.jkueck.graph.dijkstra;

import de.jkueck.graph.model.Line;
import de.jkueck.graph.model.Timetable;
import de.jkueck.graph.model.TrafficType;
import org.apache.log4j.Logger;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TestDijkstra {

    private static final Logger log = Logger.getLogger(TestDijkstra.class);

    @Test
    public void test01() {

        Line line1 = new Line("S1", null, TrafficType.BUS);
        Line line2 = new Line("S2", null, TrafficType.TRAIN);
        /*Line line3 = new Line("S3", null, TrafficType.TRAIN);
        Line line4 = new Line("S4", null, TrafficType.TRAIN);
        Line line5 = new Line("S5", null, TrafficType.TRAIN);
        Line line6 = new Line("S6", null, TrafficType.TRAIN);*/
        Line line7 = new Line("S7", null, TrafficType.BUS);
        Line line8 = new Line("S8", null, TrafficType.SUBWAY);

        Node nodeA = new Node("A");
        Node nodeB = new Node("B");
        Node nodeC = new Node("C");
        Node nodeD = new Node("D");
        /*Node nodeE = new Node("E");
        Node nodeF = new Node("F");
        Node nodeG = new Node("G");
        Node nodeH = new Node("H");
        Node nodeI = new Node("I");
        Node nodeJ = new Node("J");*/

        Edge edgeAB = new Edge(nodeA, nodeB, 10);
        edgeAB.getTimetables().add(new Timetable(line2, new LocalTime(0, 10), new LocalTime(0, 20)));

        Edge edgeAC = new Edge(nodeA, nodeC, 13);
        edgeAC.getTimetables().add(new Timetable(line1, new LocalTime(0, 07), new LocalTime(0, 21)));

        Edge edgeBD = new Edge(nodeB, nodeD, 2);
        edgeBD.getTimetables().add(new Timetable(line2, new LocalTime(0, 20), new LocalTime(0, 22)));

        Edge edgeCB = new Edge(nodeC, nodeB, 3);
        edgeCB.getTimetables().add(new Timetable(line7, new LocalTime(0, 21), new LocalTime(0, 24)));

        Edge edgeCD = new Edge(nodeC, nodeD, 5);
        edgeCD.getTimetables().add(new Timetable(line8, new LocalTime(0, 25), new LocalTime(0, 30)));

        /*Edge edgeCJ = new Edge(nodeC, nodeJ, 2);

        Edge edgeDE = new Edge(nodeD, nodeE, 12);
        edgeDE.getTimetables().add(new Timetable(line2, new LocalTime(0, 22), new LocalTime(0, 34)));

        Edge edgeEF = new Edge(nodeE, nodeF, 1);
        Edge edgeEG = new Edge(nodeE, nodeG, 3);

        Edge edgeFG = new Edge(nodeF, nodeG, 1);
        Edge edgeFH = new Edge(nodeF, nodeH, 2);
        Edge edgeFI = new Edge(nodeF, nodeI, 1);

        Edge edgeGH = new Edge(nodeG, nodeH, 2);
        Edge edgeGF = new Edge(nodeG, nodeF, 1);

        Edge edgeIH = new Edge(nodeI, nodeH, 1);

        Edge edgeJG = new Edge(nodeJ, nodeG, 2);*/

        nodeA.getOut().add(edgeAB);
        nodeA.getOut().add(edgeAC);

        nodeB.getOut().add(edgeBD);

        nodeC.getOut().add(edgeCB);
        nodeC.getOut().add(edgeCD);
        /*nodeC.getOut().add(edgeCJ);

        nodeD.getOut().add(edgeDE);

        nodeE.getOut().add(edgeEF);
        nodeE.getOut().add(edgeEG);

        nodeF.getOut().add(edgeFG);
        nodeF.getOut().add(edgeFH);
        nodeF.getOut().add(edgeFI);

        nodeG.getOut().add(edgeGH);
        nodeG.getOut().add(edgeGF);

        nodeI.getOut().add(edgeIH);

        nodeJ.getOut().add(edgeJG);*/

        Graph graph = new Graph();
        graph.getNodes().add(nodeA);
        graph.getNodes().add(nodeB);
        graph.getNodes().add(nodeC);
        graph.getNodes().add(nodeD);
        /*graph.getNodes().add(nodeE);
        graph.getNodes().add(nodeF);
        graph.getNodes().add(nodeG);
        graph.getNodes().add(nodeH);
        graph.getNodes().add(nodeI);*/

        Dijkstra dijkstra = new Dijkstra();
        Route route = dijkstra.dijkstra02(nodeA, nodeD, new LocalDateTime(2018, 3, 16, 0, 5));
        /*for (Wrapper wrapper : wrapperList) {
            System.out.println(wrapper.getNode().getName());
        }*/


        log.info("");

        dijkstra.printRoute(route);

        log.info("");

    }

    @Test
    public void test02() {

    }

}

