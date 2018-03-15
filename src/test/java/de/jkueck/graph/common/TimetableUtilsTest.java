package de.jkueck.graph.common;

import de.jkueck.graph.model.Timetable;
import org.joda.time.LocalTime;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class TimetableUtilsTest {

    @Test
    public void test1() {
        assertNull(TimetableUtils.findNextDeparture(null, new LocalTime(10, 0)));
    }

    @Test
    public void test2() {
        Set<Timetable> emptyTimetableSet = new HashSet<>();
        assertNull(TimetableUtils.findNextDeparture(emptyTimetableSet, new LocalTime(10, 0)));
    }

    @Test
    public void test3() {
        assertNull(TimetableUtils.findNextDeparture(null, null));
    }

    @Test
    public void test4() {
        Set<Timetable> emptyTimetableSet = new HashSet<>();
        assertNull(TimetableUtils.findNextDeparture(emptyTimetableSet, null));
    }

    @Test
    public void test5() {
        Set<Timetable> timetableSet = new HashSet<>();
        timetableSet.add(new Timetable(null, new LocalTime(10, 0), new LocalTime(11, 0), null));
        assertNull(TimetableUtils.findNextDeparture(timetableSet, null));
    }

}