package de.jkueck.graph.model.graph;

import lombok.Getter;

public class Line {

    @Getter
    private String name;

    @Getter
    private String header;

    @Getter
    private TrafficType trafficType;

    public Line(String name, String header, TrafficType trafficType) {
        this.name = name;
        this.header = header;
        this.trafficType = trafficType;
    }

    @Override
    public String toString() {
        return "Line{" +
                "name='" + name + '\'' +
                ", header='" + header + '\'' +
                ", trafficType=" + trafficType +
                '}';
    }

}
