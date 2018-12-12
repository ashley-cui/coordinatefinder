package pkg;

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
}