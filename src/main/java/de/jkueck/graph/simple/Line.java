package de.jkueck.graph.simple;

import lombok.Getter;

public class Line {

    @Getter
    private long id;

    @Getter
    private String name;

    @Getter
    private String header;

    @Getter
    private TrafficType trafficType;

    public Line(long id, String name, String header, TrafficType trafficType) {
        this.id = id;
        this.name = name;
        this.header = header;
        this.trafficType = trafficType;
    }

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
