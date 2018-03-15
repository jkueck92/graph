package de.jkueck.graph.common;

import de.jkueck.graph.*;
import de.siegmar.fastcsv.reader.CsvContainer;
import de.siegmar.fastcsv.reader.CsvReader;
import de.siegmar.fastcsv.reader.CsvRow;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

public class CSVImporter {

	public Graph getGraph() {
		try {

			Set<Line> lines = this.extractLines();
			Set<Node> nodes = this.extractStops();
			Set<Edge> edges = this.extractEdges(nodes);
			this.extractTimes(edges, lines);

			Graph graph = new Graph();
			graph.setNodes(nodes);
			graph.setEdges(edges);

			return graph;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private Set<Node> extractStops() throws Exception {
		Set<Node> nodes = new HashSet<>();
		File file = new File("C:/users/jkueck/desktop/gtfs/stops.csv");
		CsvReader csvReader = new CsvReader();

		csvReader.setContainsHeader(Boolean.TRUE);
		csvReader.setFieldSeparator(';');

		CsvContainer csv = csvReader.read(file, StandardCharsets.UTF_8);
		for (CsvRow row : csv.getRows()) {
			nodes.add(new Node(Integer.valueOf(row.getField(0)), row.getField(1)));
		}
		return nodes;
	}

	private Set<Line> extractLines() throws Exception {
		Set<Line> lines = new HashSet<>();
		File file = new File("C:/users/jkueck/desktop/gtfs/lines.csv");
		CsvReader csvReader = new CsvReader();

		csvReader.setContainsHeader(Boolean.TRUE);
		csvReader.setFieldSeparator(';');

		CsvContainer csv = csvReader.read(file, StandardCharsets.UTF_8);
		for (CsvRow row : csv.getRows()) {
			if (row.getField(3).equals(TrafficType.TRAIN.name())) {
				lines.add(new Line(Integer.valueOf(row.getField(0)), row.getField(1), row.getField(2), TrafficType.TRAIN));
			}
		}
		return lines;
	}

	private Line findLineById(Set<Line> lines, long id) {
		for (Line line : lines) {
			if (line.getId() == id) {
				return line;
			}
		}
		return null;
	}

	private Edge findEdgeById(Set<Edge> edges, long id) {
		for (Edge edge : edges) {
			if (edge.getId() == id) {
				return edge;
			}
		}
		return null;
	}

	private Node findNodeById(Set<Node> nodes, long id) {
		for (Node node : nodes) {
			if (node.getId() == id) {
				return node;
			}
		}
		return null;
	}

	private Set<Edge> extractEdges(Set<Node> nodes) throws Exception {
		Set<Edge> edges = new HashSet<>();
		File file = new File("C:/users/jkueck/desktop/gtfs/edges.csv");
		CsvReader csvReader = new CsvReader();

		csvReader.setContainsHeader(Boolean.TRUE);
		csvReader.setFieldSeparator(';');

		CsvContainer csv = csvReader.read(file, StandardCharsets.UTF_8);
		for (CsvRow row : csv.getRows()) {

			Node tmpNode1 = this.findNodeById(nodes, Integer.valueOf(row.getField(1)));
			Node tmpNode2 = this.findNodeById(nodes, Integer.valueOf(row.getField(2)));

			Edge edge = new Edge(Integer.valueOf(row.getField(0)), tmpNode1, tmpNode2);
			edges.add(edge);

			tmpNode1.addOut(edge);
			tmpNode2.addIn(edge);

		}
		return edges;
	}

	private Set<Timetable> extractTimes(Set<Edge> edges, Set<Line> lines) throws Exception {
		Set<Timetable> timetables = new HashSet<>();
		File file = new File("C:/users/jkueck/desktop/gtfs/times.csv");
		CsvReader csvReader = new CsvReader();

		csvReader.setContainsHeader(Boolean.TRUE);
		csvReader.setFieldSeparator(';');

		CsvContainer csv = csvReader.read(file, StandardCharsets.UTF_8);
		for (CsvRow row : csv.getRows()) {

			Line tmpLine = this.findLineById(lines, Integer.valueOf(row.getField(4)));
			Edge tmpEdge = this.findEdgeById(edges, Integer.valueOf(row.getField(3)));

			DateTimeFormatter dtf = DateTimeFormat.forPattern("HH:mm");

			LocalTime departureTime = dtf.parseLocalTime(row.getField(1));
			LocalTime arrivalTime = dtf.parseLocalTime(row.getField(2));

			Timetable timetable = new Timetable(Integer.valueOf(row.getField(0)), tmpLine, departureTime, arrivalTime);
			tmpEdge.addTimetable(timetable);

		}
		return timetables;
	}

}
