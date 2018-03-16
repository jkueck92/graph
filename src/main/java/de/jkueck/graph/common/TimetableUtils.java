package de.jkueck.graph.common;

import de.jkueck.graph.model.graph.Timetable;
import org.joda.time.LocalTime;

import java.util.Set;

public class TimetableUtils {

    public static Timetable findNextDeparture(Set<Timetable> timetables, LocalTime departureTime) {
        if (timetables != null && departureTime != null) {
            for (Timetable timetable : timetables) {
                if (departureTime.compareTo(timetable.getDeparture()) <= 0) {
                    return timetable;
                }
            }
        }
        return null;
    }

}
