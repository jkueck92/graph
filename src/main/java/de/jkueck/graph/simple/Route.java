package de.jkueck.graph.simple;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;

public class Route {

	@Getter
	@Setter
	private String name;

	@Getter
	private LinkedList<RouteDetail> routeDetails = new LinkedList<>();

}
