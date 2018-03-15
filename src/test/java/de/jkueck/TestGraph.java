package de.jkueck;

import de.jkueck.graph.*;
import de.jkueck.graph.test.Dijkstra;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

public class TestGraph {

    @Test
    public void test() {

        Line line001 = new Line("RS1", "Bremen Hbf", TrafficType.TRAIN);
        Line line002 = new Line("RS2", "Bremen Hbf", TrafficType.TRAIN);
        Line line003 = new Line("RS3", "Bad Zwischenahn", TrafficType.TRAIN);
        Line line004 = new Line("RS3", "Nordenham", TrafficType.TRAIN);

        Node node001 = new Node("Ritterhude");
        Node node002 = new Node("Bremen-Burg");
        Node node003 = new Node("Bremen-Oslebshausen");
        Node node004 = new Node("Bremen-Walle");
        Node node005 = new Node("Bremen Hbf");
        Node node006 = new Node("Bremen Neustadt");
        Node node007 = new Node("Heidkrug");
        Node node008 = new Node("Delmenhorst");
        Node node009 = new Node("Osterholz-Scharmbeck");

        // RS2 Ritterhude -> Bremen-Burg
        Edge edge001 = new Edge(node001, node002);
        edge001.addTimetable(new Timetable(line002, new LocalTime(0, 47), new LocalTime(0, 51)));
        edge001.addTimetable(new Timetable(line002, new LocalTime(4, 48), new LocalTime(4, 53)));
        edge001.addTimetable(new Timetable(line002, new LocalTime(5, 32), new LocalTime(5, 36)));

        // RS2 Bremen-Burg -> Bremen Hbf
        Edge edge002 = new Edge(node002, node005);
        edge002.addTimetable(new Timetable(line002, new LocalTime(0, 51), new LocalTime(1, 0)));
        edge002.addTimetable(new Timetable(line002, new LocalTime(4, 53), new LocalTime(5, 4)));
        edge002.addTimetable(new Timetable(line002, new LocalTime(5, 36), new LocalTime(5, 45)));

        // RS1 Bremen-Burg -> Bremen-Oslebshausen
        Edge edge003 = new Edge(node002, node003);
        edge003.addTimetable(new Timetable(line001, new LocalTime(0, 44), new LocalTime(0, 47)));
        edge003.addTimetable(new Timetable(line001, new LocalTime(1, 44), new LocalTime(1, 47)));
        edge003.addTimetable(new Timetable(line001, new LocalTime(2, 44), new LocalTime(2, 47)));

        // RS1 Bremen-Oslebshausen -> Bremen-Walle
        Edge edge004 = new Edge(node003, node004);
        edge004.addTimetable(new Timetable(line001, new LocalTime(0, 47), new LocalTime(0, 51))); // 00:51
        edge004.addTimetable(new Timetable(line001, new LocalTime(1, 47), new LocalTime(1, 51))); // 01:51
        edge004.addTimetable(new Timetable(line001, new LocalTime(2, 47), new LocalTime(2, 51))); // 02:51

        // RS1 Bremen-Walle -> Bremen Hbf
        Edge edge005 = new Edge(node004, node005);
        edge005.addTimetable(new Timetable(line001, new LocalTime(0, 51), new LocalTime(0, 55)));
        edge005.addTimetable(new Timetable(line001, new LocalTime(1, 51), new LocalTime(2, 54)));
        edge005.addTimetable(new Timetable(line001, new LocalTime(2, 51), new LocalTime(1, 54)));

        // RS3 Bremen Hbf -> Bremen Neustadt
        Edge edge006 = new Edge(node005, node006);
        edge006.addTimetable(new Timetable(line003, new LocalTime(1, 15), new LocalTime(1, 19)));
        edge006.addTimetable(new Timetable(line003, new LocalTime(5, 15), new LocalTime(5, 19)));

        // RS3 Bremen Neustadt -> Heidkrug
        Edge edge007 = new Edge(node006, node007);
        edge007.addTimetable(new Timetable(line003, new LocalTime(1, 19), new LocalTime(1, 24)));
        edge007.addTimetable(new Timetable(line003, new LocalTime(5, 19), new LocalTime(5, 24)));

        // RS3 Heidkrug -> Delmenhorst
        Edge edge008 = new Edge(node007, node008);
        edge008.addTimetable(new Timetable(line003, new LocalTime(1, 24), new LocalTime(1, 28)));
        edge008.addTimetable(new Timetable(line003, new LocalTime(5, 24), new LocalTime(5, 28)));

        // RS4 Bremen Hbf -> Delmenhorst
        Edge edge009 = new Edge(node005, node008);
        edge009.addTimetable(new Timetable(line004, new LocalTime(5, 10), new LocalTime(5, 20)));
        edge009.addTimetable(new Timetable(line004, new LocalTime(6, 10), new LocalTime(6, 20)));
        edge009.addTimetable(new Timetable(line004, new LocalTime(7, 10), new LocalTime(7, 20)));

        node001.addOut(edge001);
        node002.addIn(edge001);
        node002.addOut(edge002);
        node005.addIn(edge002);
        node002.addOut(edge003);
        node003.addOut(edge004);
        node003.addIn(edge003);
        node004.addOut(edge005);
        node004.addIn(edge004);
        node005.addIn(edge005);
        node005.addOut(edge006);
        node005.addOut(edge009);
        node006.addOut(edge007);
        node007.addOut(edge008);
        node006.addIn(edge006);
        node007.addIn(edge007);
        node008.addIn(edge008);
        node008.addIn(edge009);

        Graph graph = new Graph();
        graph.addAllNodes(node001, node002, node003, node004, node005, node006, node007, node008);
        graph.addAllEdges(edge001, edge002, edge003, edge004, edge005, edge006, edge007, edge008, edge009);

        Dijkstra dijkstra = new Dijkstra();
        LinkedList<Wrapper> wrappers = dijkstra.dijkstra(graph.getNode("Bremen-Walle"), graph.getNode("Delmenhorst"), new LocalDateTime(2018, 3, 14, 4, 30), Dijkstra.MAX_CHANGES_DEFAULT);

        dijkstra.printRoute(wrappers);

        System.out.println();

    }

}
