package de.jkueck.graph.model;

import lombok.Getter;

import java.util.LinkedList;

public class Route {

    @Getter
    private String name;

    @Getter
    private int changes;

    @Getter
    private LinkedList<RouteDetail> routeDetails;

    public Route(String name, LinkedList<RouteDetail> routeDetails, int changes) {
        this.name = name;
        this.routeDetails = routeDetails;
        this.changes = changes;
    }

}
