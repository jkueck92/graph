package de.jkueck.graph;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Graph {

    @Getter
    @Setter
    private Set<Node> nodes = new HashSet<>();

    @Getter
    @Setter
    private Set<Edge> edges = new HashSet<>();

    public void addAllNodes(Node... nodes) {
        this.nodes.addAll(Arrays.asList(nodes));
    }

    public void addAllEdges(Edge... edges) {
        this.edges.addAll(Arrays.asList(edges));
    }

    public Node getNode(final String name) {
        for (Node node : this.nodes) {
            if (node.getName().equals(name)) {
                return node;
            }
        }
        return null;
    }

    public int getTotalNumberNodes() {
        return this.nodes.size();
    }

    public int getTotalNumberEdges() {
        return this.edges.size();
    }

}
