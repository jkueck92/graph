package de.jkueck.graph.dijkstra;

import de.jkueck.graph.common.TimetableUtils;
import de.jkueck.graph.model.Timetable;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.joda.time.Minutes;

import java.util.*;

public class Dijkstra {

    public LinkedList<Wrapper> dijkstra02(Node start, Node end, LocalDateTime departureDateTime) {

        HashMap<String, Integer> costs = new HashMap<>();

        List<Wrapper> wrappers = new ArrayList<>();

        Wrapper wrapper = new Wrapper();
        wrapper.setCosts(0);
        wrapper.setNode(start);

        PriorityQueue<Wrapper> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(wrapper);

        LocalTime tmpLocalTime = departureDateTime.toLocalTime();

        while (!priorityQueue.isEmpty()) {

            Wrapper minWrapper = priorityQueue.poll();

            if (minWrapper.getTimetable() != null) {
                tmpLocalTime = minWrapper.getTimetable().getArrival();
            }

            if (minWrapper.getNode().equals(end)) {
                break;
            }

            for (Edge edge : minWrapper.getNode().getOut()) {

                Timetable timetable = TimetableUtils.findNextDeparture(edge.getTimetables(), tmpLocalTime);

                if (!costs.containsKey(edge.getTo().getName())) {
                    costs.put(edge.getTo().getName(), Integer.MAX_VALUE);
                }

                int a = timetable.getDuration().getMinutes();
                int b = Minutes.minutesBetween(tmpLocalTime, timetable.getDeparture()).getMinutes();
                int c = minWrapper.getCosts();
                int d = a + b + c;

                Wrapper w = new Wrapper();
                if (d <= costs.get(edge.getTo().getName())) {

                    w.setNode(edge.getTo());
                    w.setCosts(d);
                    w.setPrevious(minWrapper);
                    w.setTimetable(timetable);

                    if (minWrapper.getTimetable() == null) {
                        minWrapper.setTimetable(timetable);
                    }

                    priorityQueue.add(w);
                    wrappers.add(w);

                    costs.put(edge.getTo().getName(), d);

                }

            }


        }

        Collections.reverse(wrappers);

        return this.generateRoute(wrappers, end);

    }

    private LinkedList<Wrapper> generateRoute(List<Wrapper> wrappers, Node end) {
        LinkedList<Wrapper> route = new LinkedList<>();
        Wrapper wrapper = null;
        for (Wrapper tmpWrapper : wrappers) {
            if (tmpWrapper.getNode().getName().equals(end.getName())) {
                wrapper = tmpWrapper;
            }
        }
        while (wrapper != null) {
            route.add(wrapper);
            wrapper = wrapper.getPrevious();
        }
        Collections.reverse(route);
        return route;
    }

    public void printRoute(List<Wrapper> wrappers) {
        System.out.println(wrappers.get(0).getNode().getName() + " - abfahrt: " + wrappers.get(0).getTimetable().getDeparture() + " - line: " + wrappers.get(0).getTimetable().getLine().getName() + " - type: " + wrappers.get(0).getTimetable().getLine().getTrafficType());
        for (int i = 1; i < wrappers.size(); i ++) {
            System.out.println(wrappers.get(i).getNode().getName() + " - ankunft: " + wrappers.get(i).getTimetable().getArrival() + " - line: " + wrappers.get(i).getTimetable().getLine().getName() + " - type: " + wrappers.get(i).getTimetable().getLine().getTrafficType());
        }
    }

}
