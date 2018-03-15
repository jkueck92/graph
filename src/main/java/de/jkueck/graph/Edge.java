package de.jkueck.graph;

import lombok.Getter;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class Edge {

    @Getter
    private long id;

    @Getter
    private String name;

    @Getter
    private Node from;

    @Getter
    private Node to;

    @Getter
    private Set<Timetable> timetables = new LinkedHashSet<>();

    public Edge(long id, Node from, Node to) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.name = from.getName() + " -> " + to.getName();
    }

    public Edge(Node from, Node to) {
        this.from = from;
        this.to = to;
        this.name = from.getName() + " -> " + to.getName();
    }

    public void addTimetable(Timetable timetable) {
        this.timetables.add(timetable);
    }

    public void addAllTimetable(Timetable... timetables) {
        this.timetables.addAll(Arrays.asList(timetables));
    }

    @Override
    public String toString() {
        return "Edge{" +
                "name='" + name + '\'' +
                '}';
    }

}
