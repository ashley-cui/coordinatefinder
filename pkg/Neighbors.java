
package pkg;

import java.lang.Math;
import java.util.PriorityQueue;
import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;


public class Neighbors {


    public static int k;
    public static Double champDist;
    public static Coordinate champCoord;
    public static Double minChamp = 0.0;
    public static ArrayList<Coordinate> champs;

    public static Double Haversine(Coordinate a, Coordinate b) {
        // Find the distance between coordinate pair a and b
        Double radiusEarth = 6371.00; // in kilometers
        Double x = (b.lon - a.lon) * Math.cos(Math.toRadians((a.lat + b.lat)/2));
        Double y = b.lat - a.lat;
        Double distance = Math.sqrt(x*x + y*y) * radiusEarth;
        return distance;
    }

    public static Double hdist(Coordinate p, Tree.Node n, boolean evenLevel){
        if (!evenLevel) { //lon=0
            Coordinate p1= new Coordinate(1.0,p.lon, null, null);
            Coordinate n2= new Coordinate(1.0,n.data.lon, null, null);
            return Haversine(p1,n.data);
        }
        else{
            Coordinate p1= new Coordinate(p.lat,1.0, null, null);
            Coordinate n2= new Coordinate(n.data.lat,1.0, null, null);
            return Haversine(p1,n.data);
        }
    }

    public static ArrayList<Coordinate> Nearest(Tree.Node n, Coordinate p, int k) {
		champCoord = n.data;
		Coordinate curNearest = nearest(n, p, champCoord, true);
		minChamp = Haversine(curNearest, p);
		for(int i=0; i<k; i++) {
			curNearest = nearest(n, p, champCoord, true);
			minChamp = Haversine(nearest(n, p, champCoord, true), p);
			champs.add(curNearest);
		}


    	return champs;
    }

    public static Coordinate nearest(Tree.Node n, Coordinate p, Coordinate champ, boolean evenLevel) {
    	
    	if (n==null) {
    		return champ;
    	}
    	Double dis = Haversine(n.data, p);
    	Double disChamp = Haversine(champ, p);
    	if (dis < disChamp && dis > minChamp) {
    		champ = n.data;
    	}
    	Double partition = dis;
    	if (partition < 0) {
    		champ = nearest(n.leftChild, p, champ, !evenLevel);
    		if (Haversine(champ, p) >= partition) {
    			champ = nearest(n.rightChild, p, champ, !evenLevel);
    		}
    	} else {
    		champ = nearest(n.rightChild, p, champ, !evenLevel);
    		if (Haversine(champ, p) >= partition) {
    			champ = nearest(n.leftChild, p, champ, !evenLevel);
    		}
    	}
    	return champ;
    }


}
