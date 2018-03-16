package de.jkueck.graph.dijkstra;

import de.jkueck.graph.model.Timetable;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

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
        if (this.costs > o.getCosts()) {
            return 1;
        } else if (this.costs < o.getCosts()) {
            return -1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return "Wrapper{" +
                "node=" + node +
                ", costs=" + costs +
                '}';
    }
}
