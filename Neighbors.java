public class Neighbors {

	 public static double Haversine(Coordinate a, Coordinate b) {
 		// Find the distance between coordinate pair a and b
 		double radiusEarth = 6371.00; // in kilometers
		double x = (b.lon - a.lon) * Math.cos(Math.toRadians((a.lat + b.lat)/2));
		double y = b.lat - a.lat;
		double distance = Math.Sqrt(x*x + y*y) * radiusEarth;
 		return distance;
 	}

	public static void main(String[] args) {
		
	}
}