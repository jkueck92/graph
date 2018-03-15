package de.jkueck.graph.simple;

import lombok.Getter;
import org.joda.time.LocalTime;
import org.joda.time.Minutes;

public class Timetable {

    @Getter
    private Edge edge;

    @Getter
    private long id;

    @Getter
    private Line line;

    @Getter
    private LocalTime departure;

    @Getter
    private LocalTime arrival;

    @Getter
    private Minutes duration;

    public Timetable(long id, Line line, LocalTime departure, LocalTime arrival, Edge edge) {
        this.id = id;
        this.line = line;
        this.departure = departure;
        this.arrival = arrival;
        if (departure != null && arrival != null) {
            this.duration = Minutes.minutesBetween(departure, arrival);
        }
        this.edge = edge;
    }

    public Timetable(Line line, LocalTime departure, LocalTime arrival, Edge edge) {
        this.line = line;
        this.departure = departure;
        this.arrival = arrival;
        if (departure != null && arrival != null) {
            this.duration = Minutes.minutesBetween(departure, arrival);
        }
        this.edge = edge;
    }

    public Timetable(LocalTime arrival) {
        this(null, null, arrival, null);
    }

    @Override
    public String toString() {
        return "Timetable{" +
                "line=" + line +
                ", departure=" + departure +
                ", arrival=" + arrival +
                ", duration=" + duration +
                ", edge=" + edge +
                '}';
    }

}
