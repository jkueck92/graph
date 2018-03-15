package de.jkueck.graph;

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

    public Timetable(long id, Line line, LocalTime departure, LocalTime arrival) {
        this.id = id;
        this.line = line;
        this.departure = departure;
        this.arrival = arrival;
        this.duration = Minutes.minutesBetween(departure, arrival);
    }

    public Timetable(Line line, LocalTime departure, LocalTime arrival) {
        this.line = line;
        this.departure = departure;
        this.arrival = arrival;
        this.duration = Minutes.minutesBetween(departure, arrival);
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
