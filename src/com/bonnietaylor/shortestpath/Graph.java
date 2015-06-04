package com.bonnietaylor.shortestpath;
import java.util.*;
public class Graph {
	private List<Point> points;
	private List<Edge> edges;
	
	public Graph(List<Point> points, List<Edge> edges) {
		super();
		this.points = points;
		this.edges = edges;
	}

	public List<Point> getPoints() {
		return points;
	}

	public List<Edge> getEdges() {
		return edges;
	}
	
	
}
