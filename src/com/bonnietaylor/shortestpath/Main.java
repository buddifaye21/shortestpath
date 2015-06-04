package com.bonnietaylor.shortestpath;
import java.util.*;

public class Main {
	
	public static void main(String[] args)
	{
		String[] strArr = {"7","A","B","C","D","E","F","G","A|B|1","A|E|9","B|C|2","C|D|1","D|F|2","E|D|6","F|G|2"};
		
		WeightedPath(strArr);
		
	}
	public static void WeightedPath(String[] strArr)
	{
		int length;
		length= Integer.parseInt(strArr[0]);
		final ArrayList<Point> points = new ArrayList<Point>(length);
		final ArrayList<Edge> edges = new ArrayList<Edge>(length);
		
		//copy points from strArr to ArrayList
		for (int i = 1; i <= length; i++)
		{
			Point p =new Point(strArr[i]);
			points.add(p);
		}
		
		//parse and copy edges from strArr to ArrayList
		for (int i = 1; i <=length; i++)
		{
			String edge = strArr[i+length];
			String[] s = edge.split("\\|");
			Edge e = new Edge(new Point(s[0]), new Point(s[1]), Integer.parseInt(s[2]));
			edges.add(e);
		}
		Graph graph = new Graph(points,edges);
		
		Dijkstras algorithm = new Dijkstras(graph);
		algorithm.execute(points.get(0));
		System.out.println(join(algorithm.getPath(points.get(points.size()-1)),"-"));
		
	}
	public static <T> String join(Collection<T> items, String delimiter) {
	    String result = "";
	    for(T item : items) {
	        result += item.toString() + delimiter;
	    }
	    return result.substring(0, result.length() - 1); 
	}
	
}
