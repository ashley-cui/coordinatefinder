package pkg;

import java.lang.Math;

public class Neighbors {

	// public static int numNeighbors;
	// public static Node[] nearestNodes = new Node[10]; // The nearest nodes to the input point
	// public static Double[] nearestDistances = new Double[10]; // The distances from the nearest points
	// public static Coordinate inputPoint; // The point that the user inputs to find nearest neighbors


	// public static Double Haversine(Coordinate a, Coordinate b) {
 // 		// Find the distance between coordinate pair a and b
 // 		Double radiusEarth = 6371.00; // in kilometers
	// 	Double x = (b.lon - a.lon) * Math.cos(Math.toRadians((a.lat + b.lat)/2));
	// 	Double y = b.lat - a.lat;
	// 	Double distance = Math.sqrt(x*x + y*y) * radiusEarth;
 // 		return distance;
 // 	}

 // 	public static void kNearestNeighbors(Coordinate input, Tree t, int k) {
 // 		for (int i=0; i<k; i++) {
 // 			Search(input, t, i);
 // 		}
 // 	}

 // 	public static Node nearest(Coordinate input, Node node, boolean evenLevel, Double minComparison) {

 // 	}

 // 	public static void Search(Coordinate input, Tree t, int minIndex) {
 //    	if (t.root == null) {
 //    		t.root = search(t.root, input, true, nearestDistances[minIndex]);
 //    	}
 //    	else {
 //    		search(t.root, input, true, nearestDistances[minIndex]);
 //    	}
 // 	}

 // 	public static Node search(Coordinate input, Node node, boolean evenLevel, Double minComparison) {
 // 		if (node == null) {
 // 			return node;
 // 		}
 // 		else if (comparePoints(node, c, evenLevel)) {
 // 			//insert as right child
 // 			node = search(node.rightChild, c, !evenLevel);
 // 		} 
 // 		else if (!comparePoints(node, c, evenLevel)) {
 // 			//insert as left child
 // 			node = search(node.leftChild, c, !evenLevel);
 // 		}
 // 		return node;
 // 	}

	public static void main(String[] args) {
		
	}
}