package de.jkueck.graph.simple;

import lombok.Getter;

public class RouteDetail {

    @Getter
    private String name;

    @Getter
    private Timetable timetable;

    public RouteDetail(String name, Timetable timetable) {
        this.name = name;
        this.timetable = timetable;
    }

}
