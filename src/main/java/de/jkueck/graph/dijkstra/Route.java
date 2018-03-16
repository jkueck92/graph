package de.jkueck.graph.dijkstra;

import lombok.Getter;

import java.util.LinkedList;

public class Route {

    @Getter
    private String name;

    @Getter
    private LinkedList<RouteDetail> routeDetails;

    public Route(String name, LinkedList<RouteDetail> routeDetails) {
        this.name = name;
        this.routeDetails = routeDetails;
    }

}
