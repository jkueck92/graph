package de.jkueck.graph;

import lombok.Getter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Node {

    @Getter
    private long id;

    @Getter
    private String name;

    @Getter
    private Set<Edge> out = new HashSet<>();

    @Getter
    private Set<Edge> in = new HashSet<>();

    public Node(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Node(String name) {
        this.name = name;
    }

    public void addAllOut(Edge... edges) {
        this.out.addAll(Arrays.asList(edges));
    }

    public void addOut(Edge edge) {
        this.out.add(edge);
    }

    public void addAllIn(Edge... edges) {
        this.in.addAll(Arrays.asList(edges));
    }

    public void addIn(Edge edge) {
        this.in.add(edge);
    }

    @Override
    public String toString() {
        return "Node{" +
                "name='" + name + '\'' +
                '}';
    }

}
