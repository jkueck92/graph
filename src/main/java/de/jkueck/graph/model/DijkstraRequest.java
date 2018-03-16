package de.jkueck.graph.model;

import de.jkueck.graph.model.graph.Node;
import lombok.Getter;
import org.joda.time.LocalDateTime;

public class DijkstraRequest {

    @Getter
    private Node startNode;

    @Getter
    private Node endNode;

    @Getter
    private LocalDateTime departureDateTime;

    @Getter
    private int maxChanges;

    public DijkstraRequest(Node startNode, Node endNode, LocalDateTime departureDateTime, int maxChanges) {
        this.startNode = startNode;
        this.endNode = endNode;
        this.departureDateTime = departureDateTime;
        this.maxChanges = maxChanges;
    }

    @Override
    public String toString() {
        return "DijkstraRequest{" +
                "startNode=" + startNode +
                ", endNode=" + endNode +
                ", departureDateTime=" + departureDateTime +
                ", maxChanges=" + maxChanges +
                '}';
    }

}
