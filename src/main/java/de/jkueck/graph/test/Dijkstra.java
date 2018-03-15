package de.jkueck.graph.test;

import de.jkueck.graph.*;
import lombok.Getter;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.joda.time.Minutes;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Dijkstra {

    public static final Integer MAX_CHANGES_DEFAULT = 10;

    public LinkedList<Wrapper> dijkstra(Node departureNode, Node arrivalNode, LocalDateTime departureDateTime, int maxChanges) {

        LinkedList<Wrapper> wrapperLinkedList = new LinkedList<>();
        List<Wrapper> wrapperList = new ArrayList<>();

        LocalTime departureTime = departureDateTime.toLocalTime();
        int dayOfWeek = departureDateTime.getDayOfWeek();

        for (Edge edge : departureNode.getOut()) {
            Timetable timetable = this.findNextDeparture(edge.getTimetables(), departureTime);
            if (timetable != null) {
                int minutesToDeparture = Minutes.minutesBetween(departureTime, timetable.getDeparture()).getMinutes();
                wrapperList.add(new Wrapper(edge.getFrom(), edge.getTo(), timetable, timetable.getDuration().getMinutes() + minutesToDeparture));
            }
        }

        Line tmpLine = null;
        int countChanges = 0;

        while (!wrapperList.isEmpty()) {

            Wrapper tmpWrapper = null;
            Integer tmpCosts = null;
            for (Wrapper wrapper : wrapperList) {
                if (tmpCosts == null) {
                    tmpCosts = wrapper.getCosts();
                    tmpWrapper = wrapper;
                } else {
                    if (tmpCosts > wrapper.getCosts()) {
                        tmpCosts = wrapper.getCosts();
                        tmpWrapper = wrapper;
                    }
                }
            }

            if (tmpLine == null) {
                tmpLine = tmpWrapper.getTimetable().getLine();
            } else {
                if (!tmpLine.equals(tmpWrapper.getTimetable().getLine())) {
                    countChanges++;
                    tmpLine = tmpWrapper.getTimetable().getLine();
                }
            }

            if (countChanges > maxChanges) {
                System.out.println("exceed max changes, no route found with max changes [" + maxChanges + "] actual changes [" + countChanges + "]");
                break;
            }

            wrapperLinkedList.add(tmpWrapper);

            wrapperList = new ArrayList<>();
            for (Edge edge : tmpWrapper.getNodeTo().getOut()) {

                LocalTime tmpArrivalTime = tmpWrapper.getTimetable().getArrival();
                Timetable timetable = this.findNextDeparture(edge.getTimetables(), tmpArrivalTime);

                if (timetable != null) {
                    int waitingMinutes = Minutes.minutesBetween(tmpArrivalTime, timetable.getDeparture()).getMinutes();
                    int drivingCosts = timetable.getDuration().getMinutes();
                    wrapperList.add(new Wrapper(edge.getFrom(), edge.getTo(), timetable, tmpWrapper.getCosts() + waitingMinutes + drivingCosts));
                }
            }

            if (tmpWrapper.getNodeTo().getName().equals(arrivalNode.getName())) {
                break;
            }

        }

        return wrapperLinkedList;

    }

    private Timetable findNextDeparture(Set<Timetable> timetables, LocalTime departureTime) {
        for (Timetable timetable : timetables) {
            if (departureTime.compareTo(timetable.getDeparture()) <= 0) {
                return timetable;
            }
        }
        return null;
    }

    public void printRoute(LinkedList<Wrapper> wrappers) {
        if (!wrappers.isEmpty()) {
            System.out.println(wrappers.getFirst().getNodeFrom().getName() + " // " + wrappers.getFirst().getTimetable().getDeparture() + " // " + wrappers.getFirst().getTimetable().getLine().getName());
            for (int i = 1; i < wrappers.size() - 1; i++) {
                System.out.println(wrappers.get(i).getNodeFrom().getName() + " // " + wrappers.get(i).getTimetable().getDeparture() + " // " + wrappers.get(i).getTimetable().getLine().getName());
            }
            System.out.println(wrappers.getLast().getNodeTo().getName() + " // " + wrappers.getLast().getTimetable().getArrival() + " // " + wrappers.getLast().getTimetable().getLine().getName());
        }
    }

}
