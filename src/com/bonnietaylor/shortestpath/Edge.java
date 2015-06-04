package com.bonnietaylor.shortestpath;
public class Edge implements Comparable<Edge> {
	
	
	private Point source;
	private Point dest;
	private int weight;
	public Edge(Point source, Point dest, int weight) {
		super();
		this.source = source;
		this.dest = dest;
		this.weight = weight;
	}
	public Point getSource() {
		return source;
	}
	public Point getDest() {
		return dest;
	}
	public int getWeight() {
		return weight;
	}
	@Override
	public int compareTo(Edge arg0) {
		// TODO Auto-generated method stub
		return this.weight-arg0.getWeight();
	}
}
