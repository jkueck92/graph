package de.jkueck.graph.model.graph;

import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

public class Graph {

    @Getter
    private Set<Node> nodes = new HashSet<>();

}
