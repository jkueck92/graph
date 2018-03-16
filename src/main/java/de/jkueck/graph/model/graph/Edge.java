package de.jkueck.graph.model.graph;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Edge implements Serializable {

    @Getter
    private String name;

    @Getter
    private Node from;

    @Getter
    private Node to;

    @Getter
    private int costs;

    @Getter
    @Setter
    private Set<Timetable> timetables = new HashSet<>();

    public Edge(Node from, Node to, int costs) {
        this.from = from;
        this.to = to;
        this.costs = costs;
        this.name = from.getName() + " -> " + to.getName();
    }

    @Override
    public String toString() {
        return "Edge{" +
                "name='" + name + '\'' +
                '}';
    }

}
