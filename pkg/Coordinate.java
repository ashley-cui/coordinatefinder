package pkg;

import java.util.Collections;
import java.util.Comparator;

public class Coordinate {
	
	public Double lat;
	public Double lon;
	public String county;
	public String state;

	public Coordinate(Double lat, Double lon, String county, String state) {
		this.lat = lat;
		this.lon = lon;
		this.county = county;
		this.state = state;
	}
	public Double getLatitude() {
         return lat;
    }

    public Double getLongitude() {
         return lon;
    }

    /*Comparator for sorting the Coordinates by latitude*/
    public static Comparator<Coordinate> coordsXComparator = new Comparator<Coordinate>() {

		public int compare(Coordinate c1, Coordinate c2) {
		   Double c1x = c1.getLatitude();
		   Double c2x = c2.getLatitude();

		   //ascending order
		   return c1x.compareTo(c2x);
    }};

 	/*Comparator for sorting the Coordinates by longitude*/
    public static Comparator<Coordinate> coordsYComparator = new Comparator<Coordinate>() {

		public int compare(Coordinate c1, Coordinate c2) {
		   Double c1y = c1.getLongitude();
		   Double c2y = c2.getLongitude();

		   //ascending order
		   return c1y.compareTo(c2y);
    }};

}