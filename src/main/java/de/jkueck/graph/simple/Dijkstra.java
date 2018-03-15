package de.jkueck.graph.simple;

import de.jkueck.graph.Timetable;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.joda.time.Minutes;

import java.util.*;

public class Dijkstra {

	public Route dijsktra1(Node startNode, Node endNode, LocalDateTime departureDateTime) {

		PriorityQueue<Node> priorityQueue = new PriorityQueue<>();

		startNode.setCosts(0);
		startNode.setPreviousNode(startNode);

		LocalTime tmpDepartureTime = departureDateTime.toLocalTime();

		priorityQueue.add(startNode);

		while (!priorityQueue.isEmpty()) {

			Node minNode = priorityQueue.poll();

			for (Edge edge : minNode.getOut()) {
				Timetable timetable = this.findNextDeparture(edge.getTimetables(), tmpDepartureTime);

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

			// if (minNode.getTimetable() != null) {
				tmpDepartureTime = minNode.getTimetable().getArrival();
			// }

		}

		Route route = new Route();

		List<RouteDetail> routeDetails = new ArrayList<>();

		Node tmpNode = endNode;
		while (tmpNode != startNode) {
			routeDetails.add(new RouteDetail(tmpNode.getName(), tmpNode.getTimetable()));
			tmpNode = tmpNode.getPreviousNode();
		}
		routeDetails.add(new RouteDetail(startNode.getName(), routeDetails.get(routeDetails.size() - 1).getTimetable()));

		Collections.reverse(routeDetails);

		route.getRouteDetails().addAll(routeDetails);

		return route;
	}

	public Route dijsktra(Node startNode, Node endNode) {

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

		Route route = new Route();

		List<RouteDetail> tmpRoute = new ArrayList<>();

		Node tmpNode = endNode;
		// tmpRoute.add(new RouteDetail(endNode.getName()));
		while (!tmpNode.equals(startNode)) {
			tmpNode = tmpNode.getPreviousNode();
			// tmpRoute.add(new RouteDetail(tmpNode.getName()));
		}

		Collections.reverse(tmpRoute);

		route.getRouteDetails().addAll(tmpRoute);
		route.setName(route.getRouteDetails().getFirst().getName() + " -> " + route.getRouteDetails().getLast().getName());

		return route;
	}


	private Timetable findNextDeparture(Set<Timetable> timetables, LocalTime departureTime) {
		for (Timetable timetable : timetables) {
			if (departureTime.compareTo(timetable.getDeparture()) <= 0) {
				return timetable;
			}
		}
		return null;
	}

}
