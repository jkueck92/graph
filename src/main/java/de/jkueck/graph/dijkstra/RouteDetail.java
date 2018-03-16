package de.jkueck.graph.dijkstra;

import de.jkueck.graph.model.Line;
import lombok.Getter;
import org.joda.time.LocalTime;

public class RouteDetail {

    @Getter
    private String name;

    @Getter
    private LocalTime arrival;

    @Getter
    private LocalTime departure;

    @Getter
    private Line line;

    public RouteDetail(String name, LocalTime arrival, LocalTime departure, Line line) {
        this.name = name;
        this.arrival = arrival;
        this.departure = departure;
        this.line = line;
    }

}
