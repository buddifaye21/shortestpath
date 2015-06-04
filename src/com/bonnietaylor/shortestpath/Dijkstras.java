package com.bonnietaylor.shortestpath;
import java.util.*;

public class Dijkstras{

	private final List<Point> nodes;
	private final List<Edge> edges;
	private Set<Point> settledNodes;
	private Set<Point> unsettledNodes;
	private Map<Point, Point> predecessors;
	private Map<Point, Integer> distance;

	public Dijkstras(Graph graph)
	{
		this.nodes = new ArrayList<>(graph.getPoints());
	    this.edges = new ArrayList<>(graph.getEdges());
	}
	
	public void execute(Point source)
	{
		settledNodes = new HashSet<>();
		unsettledNodes = new HashSet<>();
		distance = new HashMap<>();
		predecessors = new HashMap<>();
		distance.put(source, 0);
		unsettledNodes.add(source);
		
		
		while(!unsettledNodes.isEmpty())
		{
			Point node = findPointWithShortestDist(unsettledNodes); //gets the node with the lowest weight
			settledNodes.add(node);  
			unsettledNodes.remove(node); ///swaps current node to settled
			findMinDistance(node);
		}
	}

	private void findMinDistance(Point node)
	{
		List<Point> neighbors = getNeighbors(node);
		for(Point neighbor: neighbors)
		{
			if(getShortestDistance(neighbor) > (getShortestDistance(node) + getDistance(node, neighbor))) //is possible new distance shorter that the current shortest
			{
		        distance.put(neighbor, getShortestDistance(node) + getDistance(node, neighbor));
		        predecessors.put(neighbor, node);
		        unsettledNodes.add(neighbor);
		    }
		}
	}
	
	private int getDistance(Point node, Point neighbor)
	{
		for (Edge edge : edges)
		{
			if (edge.getSource().equals(node) && edge.getDest().equals(neighbor))
			{
				return edge.getWeight();
			}
		}
		throw new RuntimeException("Somethings Wrong");
	}
	
	private List<Point> getNeighbors(Point node)
	{
		List<Point> neighbors = new ArrayList<>();
		for(Edge edge : edges)
		{
			if(edge.getSource().equals(node) && !isSettled(edge.getDest()))
			{
				neighbors.add(edge.getDest());
			}
		}
		return neighbors;
	}
	
	private Point findPointWithShortestDist(Set<Point> points)
	{
		Point min = null;
		
		for(Point point : points)
		{
			if(min == null)
			{
				min = point;
			}
			else
			{
				if(getShortestDistance(point) < getShortestDistance(min))
				{
					min = point;
				}
			}
		}
		return min;
	}
	
	private boolean isSettled(Point point)
	{
		return settledNodes.contains(point);
	}
	
	private int getShortestDistance(Point destination)
	{
		Integer dist = distance.get(destination);
		if(dist == null)
		{
			return Integer.MAX_VALUE;
		}
		else
		{
			return dist;
		}
	}
	
	public List<Point> getPath(Point destination)
	{
		List<Point> path = new LinkedList<>();
		Point step = destination;
		
		if(!predecessors.containsKey(step))
		{
			return null;
		}
		
		path.add(step);
		while(predecessors.get(step) != null)
		{
			step = predecessors.get(step);
			path.add(step);
		}
		
		Collections.reverse(path);
		return path;
	}
}
