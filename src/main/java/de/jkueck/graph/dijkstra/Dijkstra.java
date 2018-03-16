package de.jkueck.graph.dijkstra;

import de.jkueck.graph.common.TimetableUtils;
import de.jkueck.graph.model.Timetable;
import org.apache.log4j.Logger;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.joda.time.Minutes;

import java.util.*;

public class Dijkstra {

    private static final Logger log = Logger.getLogger(Dijkstra.class);

    public Route dijkstra02(Node start, Node end, LocalDateTime departureDateTime) {

        HashMap<String, Integer> costs = new HashMap<>();

        List<Wrapper> wrappers = new ArrayList<>();

        Wrapper wrapper = new Wrapper();
        wrapper.setCosts(0);
        wrapper.setNode(start);

        PriorityQueue<Wrapper> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(wrapper);
        log.debug("add initial wrapper to queue: " + wrapper);

        LocalTime tmpLocalTime = departureDateTime.toLocalTime();

        while (!priorityQueue.isEmpty()) {

            Wrapper minWrapper = priorityQueue.poll();
            log.debug("poll minWrapper from queue: " + minWrapper);

            if (minWrapper.getTimetable() != null) {
                tmpLocalTime = minWrapper.getTimetable().getArrival();
                log.debug("set tmpLocalTime to: " + tmpLocalTime);
            }

            if (minWrapper.getNode().equals(end)) {
                log.debug("found end node, cancel dijkstra: " + end);
                break;
            }

            for (Edge edge : minWrapper.getNode().getOut()) {

                Timetable timetable = TimetableUtils.findNextDeparture(edge.getTimetables(), tmpLocalTime);
                log.debug("found timetable: " + timetable);

                if (!costs.containsKey(edge.getTo().getName())) {
                    costs.put(edge.getTo().getName(), Integer.MAX_VALUE);
                    log.debug("add new entry to costs: " + edge.getTo().getName() + " -> " + Integer.MAX_VALUE);
                }

                if (timetable != null) {

                    int totalCosts = getTotalCosts(tmpLocalTime, minWrapper, timetable);
                    log.debug("calculated total costs: " + totalCosts);

                    Wrapper w = new Wrapper();
                    if (totalCosts <= costs.get(edge.getTo().getName())) {

                        w.setNode(edge.getTo());
                        w.setCosts(totalCosts);
                        w.setPrevious(minWrapper);
                        w.setTimetable(timetable);

                        if (minWrapper.getTimetable() == null) {
                            minWrapper.setTimetable(timetable);
                        }

                        log.debug("add new wrapper to queue: " + w);

                        priorityQueue.add(w);
                        wrappers.add(w);

                        costs.put(edge.getTo().getName(), totalCosts);
                        log.debug("refresh costs in costs: " + edge.getTo().getName() + " -> " + totalCosts);

                    }
                }
            }

        }

        Collections.reverse(wrappers);

        return this.getRoute(wrappers, end);

    }

    private int getTotalCosts(LocalTime tmpLocalTime, Wrapper minWrapper, Timetable timetable) {
        int a = timetable.getDuration().getMinutes();
        int b = Minutes.minutesBetween(tmpLocalTime, timetable.getDeparture()).getMinutes();
        int c = minWrapper.getCosts();
        return a + b + c;
    }

    private Route getRoute(List<Wrapper> wrappers, Node end) {
        LinkedList<RouteDetail> routeDetails = new LinkedList<>();
        Wrapper wrapper = null;
        for (Wrapper tmpWrapper : wrappers) {
            if (tmpWrapper.getNode().getName().equals(end.getName())) {
                wrapper = tmpWrapper;
            }
        }
        while (wrapper != null) {
            routeDetails.add(new RouteDetail(wrapper.getNode().getName(), wrapper.getTimetable().getArrival(), wrapper.getTimetable().getDeparture()));
            wrapper = wrapper.getPrevious();
        }
        Collections.reverse(routeDetails);
        return new Route(routeDetails.getFirst().getName() + " -> " + routeDetails.getLast().getName(), routeDetails);
    }

    public void printRoute(Route route) {
        log.info(route.getRouteDetails().getFirst().getName() + " - abfahrt: " + route.getRouteDetails().getFirst().getDeparture());
        for (int i = 1; i < route.getRouteDetails().size(); i++) {
            log.info(route.getRouteDetails().get(i).getName() + " - ankunft: " + route.getRouteDetails().get(i).getArrival());
        }
    }

}
