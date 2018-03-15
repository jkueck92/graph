package de.jkueck.simple;

import de.jkueck.graph.simple.Dijkstra;
import de.jkueck.graph.simple.Edge;
import de.jkueck.graph.simple.Graph;
import de.jkueck.graph.simple.Node;
import org.junit.jupiter.api.Test;

public class TestGraphTownsEurope {

    @Test
    public void test() {

        Node nodeA = new Node("A");
        Node nodeB = new Node("B");
        Node nodeC = new Node("C");
        Node nodeD = new Node("D");
        Node nodeE = new Node("E");
        Node nodeF = new Node("F");
        Node nodeG = new Node("G");
        Node nodeH = new Node("H");
        Node nodeI = new Node("I");
        Node nodeJ = new Node("J");

        nodeA.getOut().add(new Edge(nodeA, nodeI, 464));
        nodeA.getOut().add(new Edge(nodeA, nodeH, 1435));
        nodeA.getOut().add(new Edge(nodeA, nodeF, 343));

        nodeB.getOut().add(new Edge(nodeB, nodeH, 811));
        nodeB.getOut().add(new Edge(nodeB, nodeG, 954));
        nodeB.getOut().add(new Edge(nodeB, nodeJ, 524));
        nodeB.getOut().add(new Edge(nodeB, nodeF, 879));

        nodeC.getOut().add(new Edge(nodeC, nodeE, 1364));
        nodeC.getOut().add(new Edge(nodeC, nodeF, 1054));

        nodeD.getOut().add(new Edge(nodeD, nodeG, 433));
        nodeD.getOut().add(new Edge(nodeD, nodeJ, 1053));

        nodeE.getOut().add(new Edge(nodeE, nodeC, 1364));
        nodeE.getOut().add(new Edge(nodeE, nodeJ, 766));
        nodeE.getOut().add(new Edge(nodeE, nodeF, 1106));

        nodeF.getOut().add(new Edge(nodeF, nodeA, 343));
        nodeF.getOut().add(new Edge(nodeF, nodeB, 879));
        nodeF.getOut().add(new Edge(nodeF, nodeC, 1054));
        nodeF.getOut().add(new Edge(nodeF, nodeE, 1106));

        nodeG.getOut().add(new Edge(nodeG, nodeD, 433));
        nodeG.getOut().add(new Edge(nodeG, nodeH, 837));
        nodeG.getOut().add(new Edge(nodeG, nodeB, 954));

        nodeH.getOut().add(new Edge(nodeH, nodeA, 1435));
        nodeH.getOut().add(new Edge(nodeH, nodeG, 837));
        nodeH.getOut().add(new Edge(nodeH, nodeB, 811));

        nodeI.getOut().add(new Edge(nodeI, nodeA, 464));

        nodeJ.getOut().add(new Edge(nodeJ, nodeB, 524));
        nodeJ.getOut().add(new Edge(nodeJ, nodeD, 1053));
        nodeJ.getOut().add(new Edge(nodeJ, nodeE, 766));

        Graph graph = new Graph();
        graph.getNodes().add(nodeA);
        graph.getNodes().add(nodeB);
        graph.getNodes().add(nodeC);
        graph.getNodes().add(nodeD);
        graph.getNodes().add(nodeE);
        graph.getNodes().add(nodeF);
        graph.getNodes().add(nodeG);
        graph.getNodes().add(nodeH);
        graph.getNodes().add(nodeI);
        graph.getNodes().add(nodeJ);

        Dijkstra dijkstra = new Dijkstra();
        dijkstra.dijsktra(nodeC, nodeG);


    }

}
