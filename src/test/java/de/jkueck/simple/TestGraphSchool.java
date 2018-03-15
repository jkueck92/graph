package de.jkueck.simple;

import de.jkueck.graph.simple.Dijkstra;
import de.jkueck.graph.simple.Edge;
import de.jkueck.graph.simple.Graph;
import de.jkueck.graph.simple.Node;
import org.junit.jupiter.api.Test;

public class TestGraphSchool {

    @Test
    public void test() {

        Node nodeA = new Node("A");
        Node nodeB = new Node("B");
        Node nodeC = new Node("C");
        Node nodeD = new Node("D");
        Node nodeE = new Node("E");


        nodeA.getOut().add(new Edge(nodeA, nodeC, 6));
        nodeA.getOut().add(new Edge(nodeA, nodeD, 3));

        nodeB.getOut().add(new Edge(nodeB, nodeA, 3));

        nodeC.getOut().add(new Edge(nodeC, nodeD, 2));

        nodeD.getOut().add(new Edge(nodeD, nodeC, 1));
        nodeD.getOut().add(new Edge(nodeD, nodeB, 1));

        nodeE.getOut().add(new Edge(nodeE, nodeB, 4));
        nodeE.getOut().add(new Edge(nodeE, nodeD, 2));

        Graph graph = new Graph();
        graph.getNodes().add(nodeA);
        graph.getNodes().add(nodeB);
        graph.getNodes().add(nodeC);
        graph.getNodes().add(nodeD);
        graph.getNodes().add(nodeE);

        Dijkstra dijkstra = new Dijkstra();
        dijkstra.dijsktra(nodeE, nodeA);


    }

}

