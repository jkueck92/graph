package de.jkueck.graph.algorithm;

import de.jkueck.graph.common.TimetableUtils;
import de.jkueck.graph.model.*;
import de.jkueck.graph.model.graph.*;
import org.apache.log4j.Logger;
import org.joda.time.LocalTime;
import org.joda.time.Minutes;

import java.util.*;

public class Dijkstra {

    private static final Logger log = Logger.getLogger(Dijkstra.class);

    public Route dijkstra02(DijkstraRequest request) {

        log.debug("start dijkstra with: " + request);

        HashMap<String, Integer> costs = new HashMap<>();

        List<Wrapper> wrappers = new ArrayList<>();

        Wrapper wrapper = new Wrapper();
        wrapper.setCosts(0);
        wrapper.setNode(request.getStartNode());

        PriorityQueue<Wrapper> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(wrapper);
        log.debug("add initial wrapper to queue: " + wrapper);

        LocalTime tmpLocalTime = request.getDepartureDateTime().toLocalTime();

        Line tmpLine = null;

        int changeCounter = 0;

        while (!priorityQueue.isEmpty()) {

            Wrapper minWrapper = priorityQueue.poll();
            log.debug("poll minWrapper from queue: " + minWrapper);

            if (minWrapper.getTimetable() != null) {
                tmpLocalTime = minWrapper.getTimetable().getArrival();
                log.debug("set tmpLocalTime to: " + tmpLocalTime);
            }

            if (minWrapper.getNode().equals(request.getEndNode())) {
                log.debug("found end node, cancel dijkstra: " + request.getEndNode());
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

                        if (tmpLine != null && !tmpLine.getName().equals(timetable.getLine().getName())) {
                            changeCounter++;
                            if (changeCounter > request.getMaxChanges()) {
                                log.warn("actual changes " + changeCounter + " > [" + request.getMaxChanges() + "] abort dijkstra");
                                break;
                            }
                        }
                        tmpLine = timetable.getLine();

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

        return this.getRoute(wrappers, request.getEndNode(), changeCounter);

    }

    private int getTotalCosts(LocalTime tmpLocalTime, Wrapper minWrapper, Timetable timetable) {
        int a = timetable.getDuration().getMinutes();
        int b = Minutes.minutesBetween(tmpLocalTime, timetable.getDeparture()).getMinutes();
        int c = minWrapper.getCosts();
        return a + b + c;
    }

    private Route getRoute(List<Wrapper> wrappers, Node end, int changes) {
        LinkedList<RouteDetail> routeDetails = new LinkedList<>();
        Wrapper wrapper = null;
        for (Wrapper tmpWrapper : wrappers) {
            if (tmpWrapper.getNode().getName().equals(end.getName())) {
                wrapper = tmpWrapper;
            }
        }
        while (wrapper != null) {
            routeDetails.add(new RouteDetail(wrapper.getNode().getName(), wrapper.getTimetable().getArrival(), wrapper.getTimetable().getDeparture(), wrapper.getTimetable().getLine()));
            wrapper = wrapper.getPrevious();
        }
        Collections.reverse(routeDetails);
        if (routeDetails.isEmpty()) {
            return new Route("no route found", new LinkedList<>(), changes);
        } else {
            return new Route(routeDetails.getFirst().getName() + " -> " + routeDetails.getLast().getName(), routeDetails, changes);
        }
    }

    public void printRoute(Route route) {
        if (!route.getRouteDetails().isEmpty()) {
            log.info("found route: " + route.getName());
            log.info("max changes: " + route.getChanges());
            log.info(route.getRouteDetails().getFirst().getName() + " - abfahrt: " + route.getRouteDetails().getFirst().getDeparture() + " - line: " + route.getRouteDetails().getFirst().getLine().getName() + " - type: " + route.getRouteDetails().getFirst().getLine().getTrafficType());
            for (int i = 1; i < route.getRouteDetails().size(); i++) {
                log.info(route.getRouteDetails().get(i).getName() + " - ankunft: " + route.getRouteDetails().get(i).getArrival() + " - line: " + route.getRouteDetails().get(i).getLine().getName() + " - type: " + route.getRouteDetails().get(i).getLine().getTrafficType());
            }
        } else {
            log.info("found no route");
        }
    }

}
