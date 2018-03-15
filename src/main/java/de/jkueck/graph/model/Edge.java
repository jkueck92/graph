package de.jkueck.graph.model;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

public class Edge {

    @Getter
    private Node from;

    @Getter
    private Node to;

    @Getter
    @Setter
    private int costs = Integer.MAX_VALUE;

    @Getter
    private Set<Timetable> timetables = new LinkedHashSet<>();

    public Edge(Node from, Node to, int costs) {
        this.from = from;
        this.to = to;
        this.costs = costs;
    }

    public Edge(Node from, Node to) {
        this.from = from;
        this.to = to;
    }

}
