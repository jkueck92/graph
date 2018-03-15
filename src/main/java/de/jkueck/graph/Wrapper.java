package de.jkueck.graph;

import lombok.Getter;

public class Wrapper {

	@Getter
	private Node nodeFrom;

	@Getter
	private Node nodeTo;

	@Getter
	private Timetable timetable;

	@Getter
	private int costs;

	public Wrapper(Node nodeFrom, Node nodeTo, Timetable timetable, int costs) {
		this.nodeFrom = nodeFrom;
		this.nodeTo = nodeTo;
		this.timetable = timetable;
		this.costs = costs;
	}

	@Override
	public String toString() {
		return "Wrapper{" +
				"nodeFrom=" + nodeFrom +
				", nodeTo=" + nodeTo +
				", timetable=" + timetable +
				", costs=" + costs +
				'}';
	}

}