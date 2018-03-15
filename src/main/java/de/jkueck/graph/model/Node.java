package de.jkueck.graph.model;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

public class Node implements Comparable<Node> {

    @Getter
    private String name;

    @Getter
    @Setter
    private int costs = Integer.MAX_VALUE;

    @Getter
    @Setter
    private Node previousNode = null;

    @Getter
    private Set<Edge> out = new HashSet<>();

    @Getter
    @Setter
    private boolean isProcessed = Boolean.FALSE;

    @Getter
    @Setter
    private Timetable timetable;

    public Node(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Node o) {
        if (this.costs > o.getCosts()) {
            return 1;
        } else if (this.costs < o.getCosts()) {
            return -1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "name='" + name + '\'' +
                ", timetable=" + timetable +
                '}';
    }

}
