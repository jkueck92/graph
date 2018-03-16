package de.jkueck.graph.model.graph;

import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

public class Node {

    @Getter
    private String name;

    @Getter
    private Set<Edge> out = new HashSet<>();

    public Node(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Node{" +
                "name='" + name +
                '}';
    }
}
