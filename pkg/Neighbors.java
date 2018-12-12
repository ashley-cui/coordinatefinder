package pkg;

import java.lang.Math;
import java.util.PriorityQueue;
import java.util.Collections;
import java.util.Comparator;


public class Neighbors {

    public static Comparator<ResultTuple> tupleComparator = new Comparator<ResultTuple>() {
        @Override
        public int compare(ResultTuple t1, ResultTuple t2) {
		   Double t1d = t1.dist;
		   Double t2d = t2.dist;

		   //ascending order
		   return t2d.compareTo(t1d);
        }
    };

	public static int k; //user input for k
	public static PriorityQueue<ResultTuple> queue = new PriorityQueue<ResultTuple>(tupleComparator);

	public static Coordinate inputPoint; // The point that the user inputs to find nearest neighbors


	public static Double Haversine(Coordinate a, Coordinate b) {
		// Find the distance between coordinate pair a and b
		Double radiusEarth = 6371.00; // in kilometers
		Double x = (b.lon - a.lon) * Math.cos(Math.toRadians((a.lat + b.lat)/2));
		Double y = b.lat - a.lat;
		Double distance = Math.sqrt(x*x + y*y) * radiusEarth;
		return distance;
	}

  	public static void KNN(Coordinate input, int k, Tree.Node r) {

        if (input == null) throw new java.lang.NullPointerException(
                "called contains() with a null Point2D");
        if (r == null) return;
    	for (int i = 0; i<k; i++){
    		ResultTuple tuple = new ResultTuple(Double.MAX_VALUE, null);
    		queue.add(tuple);
    	}
    	System.out.println(input==null);
        knn(r, input, true);
   
    }
    
    public static void knn(Tree.Node n, Coordinate input, boolean evenLevel) { //operates on queue

    	//Node n, Point2D p, Point2D champion, boolean evenLevel
        
        if (n == null) {
        	System.out.println("hello");
        	return;
        }
        
        if (Haversine(n.data, input) < queue.peek().dist) {
        	queue.poll();
        	queue.add(new ResultTuple(Haversine(n.data, input),n.data));
        }

        double toPartitionLine = comparePoints(input, n, evenLevel);
        if (queue.peek().dat != null) {
	        if (toPartitionLine < 0) {
	            knn(n.leftChild, input, !evenLevel);

	            if (Haversine(queue.peek().dat, input) >= toPartitionLine) {
	                knn(n.rightChild, input, !evenLevel);
	            }
	        }
	        
	        else {
	            knn(n.rightChild, input, !evenLevel);

	            if (Haversine(queue.peek().dat, input) >= toPartitionLine) {
	                knn(n.leftChild, input, !evenLevel);
	            }
	        }
	    }
    }

    public static double comparePoints(Coordinate p, Tree.Node n, boolean evenLevel) {
        if (evenLevel) {
        	p.lon = 1;
        	n.lon = 1;
            return Haversine(p, n.data);
        }
        else {
        	p.lat = 1;
        	n.lat = 1;
            return Haversine(p, n.data);
        }
    }

	public static void main(String[] args) {
	}
}