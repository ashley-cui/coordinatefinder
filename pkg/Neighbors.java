package pkg;

import java.lang.Math;

public class Neighbors {

	 public static Double Haversine(Coordinate a, Coordinate b) {
 		// Find the distance between coordinate pair a and b
 		Double radiusEarth = 6371.00; // in kilometers
		Double x = (b.lon - a.lon) * Math.cos(Math.toRadians((a.lat + b.lat)/2));
		Double y = b.lat - a.lat;
		Double distance = Math.sqrt(x*x + y*y) * radiusEarth;
 		return distance;
 	}

	public static void main(String[] args) {
		
	}
}