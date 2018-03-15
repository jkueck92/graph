package de.jkueck;

import de.jkueck.graph.*;
import de.jkueck.graph.common.CSVImporter;
import de.jkueck.graph.test.Dijkstra;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

public class TestGraph2 {

    @Test
    public void test() {


        CSVImporter csvImporter = new CSVImporter();
        Graph graph = csvImporter.getGraph();

        Dijkstra dijkstra = new Dijkstra();
        LinkedList<Wrapper> wrappers = dijkstra.dijkstra(graph.getNode("Ritterhude"), graph.getNode("Bremen Hbf"), new LocalDateTime(2018, 3, 14, 6, 0), Dijkstra.MAX_CHANGES_DEFAULT);

        dijkstra.printRoute(wrappers);

        System.out.println();

    }

}
