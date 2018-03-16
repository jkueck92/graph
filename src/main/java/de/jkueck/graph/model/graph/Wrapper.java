package de.jkueck.graph.model.graph;

import lombok.Getter;
import lombok.Setter;

public class Wrapper implements Comparable<Wrapper> {

    @Getter
    @Setter
    private Node node = null;

    @Getter
    @Setter
    private int costs = Integer.MAX_VALUE;

    @Getter
    @Setter
    private Wrapper previous;

    @Getter
    @Setter
    private Timetable timetable;

    @Override
    public int compareTo(Wrapper o) {
        return Integer.compare(this.costs, o.getCosts());
    }

    @Override
    public String toString() {
        return "Wrapper{" +
                "node=" + node +
                ", costs=" + costs +
                '}';
    }
}
