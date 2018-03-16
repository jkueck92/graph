package de.jkueck.graph.algorithm;

import de.jkueck.graph.common.TimetableUtils;
import de.jkueck.graph.model.*;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.joda.time.Minutes;

import java.util.*;

public class Dijkstra {

    public Route dijsktra1(Node startNode, Node endNode, LocalDateTime departureDateTime) {
/*
        LocalTime tmpDepartureTime = departureDateTime.toLocalTime();

        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();

        startNode.setCosts(0);
        startNode.setTimetable(new Timetable(tmpDepartureTime));

        priorityQueue.add(startNode);

        while (!priorityQueue.isEmpty()) {

            Node minNode = priorityQueue.poll();
            tmpDepartureTime = minNode.getTimetable().getArrival();

            for (Edge edge : minNode.getOut()) {
                Timetable timetable = TimetableUtils.findNextDeparture(edge.getTimetables(), tmpDepartureTime);

                if (!edge.getTo().isProcessed()) {

                    int waitingMinutes = Minutes.minutesBetween(tmpDepartureTime, timetable.getDeparture()).getMinutes();
                    int edgeCosts = timetable.getDuration().getMinutes();
                    int nodeCosts = minNode.getCosts();
                    int totalCosts = edgeCosts + nodeCosts + waitingMinutes;

                    if (totalCosts < edge.getTo().getCosts()) {
                        edge.setCosts(totalCosts);
                        edge.getTo().setCosts(totalCosts);
                        edge.getTo().setPreviousNode(minNode);
                        edge.getTo().setTimetable(timetable);
                    }
                    priorityQueue.add(edge.getTo());
                }

            }

            minNode.setProcessed(Boolean.TRUE);

        }

        List<RouteDetail> routeNodes = new ArrayList<>();
        Node node = endNode;
        while (node != null) {
            if (node.getTimetable().getEdge() != null) {
                routeNodes.add(new RouteDetail(node.getTimetable().getEdge().getFrom().getName() + " -> " + node.getTimetable().getEdge().getTo().getName(), node.getTimetable()));
            } else {
                routeNodes.add(new RouteDetail("", node.getTimetable()));
            }
            node = node.getPreviousNode();
        }

        Collections.reverse(routeNodes);

        Route route = new Route(null, endNode.getCosts());

        route.getRouteDetails().addAll(routeNodes);
*/
        return null;

    }

    public Route dijsktra(Node startNode, Node endNode) {
/*
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();

        startNode.setCosts(0);
        startNode.setPreviousNode(startNode);

        priorityQueue.add(startNode);

        while (!priorityQueue.isEmpty()) {

            Node minNode = priorityQueue.poll();
            minNode.setProcessed(Boolean.TRUE);

            for (Edge edge : minNode.getOut()) {
                int costs = edge.getCosts() + minNode.getCosts();
                if (!edge.getTo().isProcessed()) {
                    if (costs < edge.getTo().getCosts()) {
                        edge.getTo().setCosts(costs);
                        edge.getTo().setPreviousNode(minNode);
                    }
                    priorityQueue.add(edge.getTo());
                }
            }
        }

        Route route = new Route(null, 0);

        List<RouteDetail> tmpRoute = new ArrayList<>();

        Node tmpNode = endNode;
        // tmpRoute.add(new RouteDetail(endNode.getName()));
        while (!tmpNode.equals(startNode)) {
            tmpNode = tmpNode.getPreviousNode();
            // tmpRoute.add(new RouteDetail(tmpNode.getName()));
        }

        Collections.reverse(tmpRoute);

        route.getRouteDetails().addAll(tmpRoute);
        // route.setName(route.getRouteDetails().getFirst().getName() + " -> " + route.getRouteDetails().getLast().getName());
*/
        return null;
    }

}
