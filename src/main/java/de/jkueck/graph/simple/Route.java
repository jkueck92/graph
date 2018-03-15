package de.jkueck.graph.simple;

import lombok.Getter;

import java.util.LinkedList;

public class Route {

    @Getter
    private String name;

    @Getter
    private int totalTavelCosts;

    @Getter
    private LinkedList<RouteDetail> routeDetails = new LinkedList<>();

    public Route(String name, int totalTavelCosts) {
        this.name = name;
        this.totalTavelCosts = totalTavelCosts;
    }

    public void printRoute() {
        for (int i = 1; i < this.getRouteDetails().size(); i++) {
            RouteDetail routeDetail = this.getRouteDetails().get(i);
            System.out.println(
                    routeDetail.getName()
                            + " - departureTime: " + routeDetail.getTimetable().getDeparture()
                            + " - arrivalTime: " + routeDetail.getTimetable().getArrival()
                            + " - line: " + routeDetail.getTimetable().getLine().getName()
                            + " - type: " + routeDetail.getTimetable().getLine().getTrafficType()
            );
        }
        System.out.println("-------------");
        System.out.println("travel costs: " + this.totalTavelCosts);
    }

}
