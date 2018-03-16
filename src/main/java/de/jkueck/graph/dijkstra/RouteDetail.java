package de.jkueck.graph.dijkstra;

import lombok.Getter;
import org.joda.time.LocalTime;

public class RouteDetail {

    @Getter
    private String name;

    @Getter
    private LocalTime arrival;

    @Getter
    private LocalTime departure;

    public RouteDetail(String name, LocalTime arrival, LocalTime departure) {
        this.name = name;
        this.arrival = arrival;
        this.departure = departure;
    }

}
