package de.jkueck.graph.dijkstra;

import lombok.Getter;
import org.joda.time.LocalTime;
import org.joda.time.Minutes;

public class Timetable {

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

    public Timetable(Line line, LocalTime departure, LocalTime arrival) {
        this.line = line;
        this.departure = departure;
        this.arrival = arrival;
        if (departure != null && arrival != null) {
            this.duration = Minutes.minutesBetween(departure, arrival);
        }
    }

    @Override
    public String toString() {
        return "Timetable{" +
                "line=" + line +
                ", departure=" + departure +
                ", arrival=" + arrival +
                ", duration=" + duration +
                '}';
    }

}
