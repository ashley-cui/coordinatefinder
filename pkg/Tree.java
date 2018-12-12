package pkg;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.Collections;
import java.util.Comparator;


public class Tree {

	private static class Node {
		Coordinate data;
		Node leftChild;
		Node rightChild;
		boolean evenLevel;

		public Node(Double lat, Double lon, String county, String state, boolean evenLevel) {
			this.data = new Coordinate(lat, lon, county, state);
			this.leftChild = null;
			this.rightChild = null;
			this.evenLevel = evenLevel;
		}
	}
 	
	int dimensions = 2;

 	public static Node root;
 	// public static ArrayList<Coordinate> coordsX = new ArrayList<Coordinate>();
 	// public static ArrayList<Coordinate> coordsY = new ArrayList<Coordinate>();
	public static ArrayList<Coordinate> lX = new ArrayList<Coordinate>();
    public static ArrayList<Coordinate> lY = new ArrayList<Coordinate>();
    public static ArrayList<Coordinate> rX = new ArrayList<Coordinate>();
    public static ArrayList<Coordinate> rY = new ArrayList<Coordinate>();

    public static int midX = 0;
    public static int midY = 0;




 	public static boolean comparePoints(Node n, Coordinate c, boolean evenLevel) {
 		// On even levels, we look at x coordinates, else y coordinates
 		// Goes to the right if returns true, left if returns false
        if (evenLevel) {
            return c.lat > n.data.lat;
        }
        else return c.lon > n.data.lon;
    }

    public static void Insert(Coordinate c) {
    	if (root == null) {
    		root = insert(root, c, true);
    	}
    	else {
    		insert(root, c, true);
    	}
    }

 	public static Node insert(Node node, Coordinate c, boolean evenLevel) {
 		if (node==null) {
 			Node n = new Node(c.lat, c.lon, c.county, c.state, !evenLevel);
 			return n;
 		}
 		// else if (node.data.lat == c.lat && node.data.lon == c.lon) {
 		// 	return;
 		// }
 		else if (comparePoints(node, c, evenLevel)) {
 			//insert as right child
 			node.rightChild = insert(node.rightChild, c, !evenLevel);
 		} 
 		else if (!comparePoints(node, c, evenLevel)) {
 			//insert as left child
 			node.leftChild = insert(node.leftChild, c, !evenLevel);
 		}
 		return node;
 	}

 	public static void FindMedian(ArrayList<Coordinate> xCoords, ArrayList<Coordinate> yCoords, boolean isEvenLevel, int n) {

	    if (n < 2) {
	        return;
	    }

		// System.out.println("\nx" + xCoords);
		// System.out.println("\ny" + yCoords);
		if (isEvenLevel && xCoords.size() >= 2) {
			if (xCoords.size() == 2) {
			    Insert(xCoords.get(0));
			    Insert(xCoords.get(1));
			    return;
			}
		    midX = n / 2;
			lX = new ArrayList<Coordinate>();
			rX = new ArrayList<Coordinate>();
			for (int i = 0; i < midX; i++) {
		        lX.add(xCoords.get(i));
		    }
		    for (int i = midX; i < n; i++) {
		        rX.add(xCoords.get(i));
		    }
		    Insert(xCoords.get(midX));
		    FindMedian(lX, lY, !isEvenLevel, midY);
		    FindMedian(rX, rY, !isEvenLevel, n - midY);
		} else if (yCoords.size() >= 2) {
			if (yCoords.size() == 2) {
			    Insert(yCoords.get(0));
			    Insert(yCoords.get(1));
			    return;
			}
		    midY = n / 2;
			lY = new ArrayList<Coordinate>();
			rY = new ArrayList<Coordinate>();
			for (int i = 0; i < midY; i++) {
		        lY.add(yCoords.get(i));
		    }
		    for (int i = midY; i < n; i++) {
		        rY.add(yCoords.get(i));
		    }
		    Insert(yCoords.get(midY));
		    FindMedian(lX, lY, !isEvenLevel, midX);
		    FindMedian(rX, rY, !isEvenLevel, n - midX);
		}

 	}


	public static void main(String[] args) throws Exception {

		Scanner input = new Scanner(System.in);

		System.out.println("Choose a coordinate within the U.S.");

		System.out.println("Enter the latitude: ");
		Double inputLat = input.nextDouble();

		System.out.println("Enter the longitude: ");
		Double inputLon = input.nextDouble();

		System.out.println("Enter the number of neighbors (between 1-10): ");
		int k = input.nextInt();

		System.out.println("Would you like to use a balanced tree? (yes/no): ");
		String balanced = input.next();

		long startTime = System.nanoTime();

		Tree t = new Tree();
		ArrayList<Coordinate> coords = new ArrayList<Coordinate>(); // ArrayList because we don't know the size beforehand

		// Read data from file to create tree
		File file = new File("TestData.txt"); 

		BufferedReader br = new BufferedReader(new FileReader(file)); 

		String st; 
		int x = 0;
		while ((st = br.readLine()) != null) {
			if (x==0) {
				x++;
				continue;
			}
			// Parse each data point
			String[] pieces = st.split("\\s+");
			String state = pieces[0];		
			String county = pieces[1];
			Double lat = Double.parseDouble(pieces[2]);
			Double lon = Double.parseDouble(pieces[3]);
			// Do not count invalid coordinates (0,0)
			if (lat == 0 && lon == 0) {
				continue;
			}
			Coordinate c = new Coordinate(lat, lon, county, state);
			coords.add(c);
			if (balanced.equals("no")) {
				t.Insert(new Coordinate(lat, lon, county, state));
			}
		} 

		if (balanced.equals("yes")) {
			@SuppressWarnings("unchecked")
			ArrayList<Coordinate> coordsX = (ArrayList<Coordinate>) coords.clone();
			@SuppressWarnings("unchecked")
			ArrayList<Coordinate> coordsY = (ArrayList<Coordinate>) coords.clone();
			// Sort once by x (lat) coorinates, and once by y (lon) coordinates
			// mergeSort(coords, coords.size(), true);
			// mergeSort(coords, coords.size(), false);
			Collections.sort(coordsX, Coordinate.coordsXComparator);
			Collections.sort(coordsY, Coordinate.coordsYComparator);

			// Continue to grab and insert median until all elements are inserted into the tree
			lX = (ArrayList<Coordinate>) coords.clone(); // Includes up to mid
    		lY = (ArrayList<Coordinate>) coords.clone(); // Includes up to mid
    		rX = (ArrayList<Coordinate>) coords.clone(); // Includes after mid
			rY = (ArrayList<Coordinate>) coords.clone(); // Includes after mid
		 	FindMedian(coordsX, coordsY, true, coords.size());
		 	long endTime = System.nanoTime();
			long timeElapsed = endTime - startTime;
			System.out.println("\nBalanced tree created\n");
			System.out.println("Execution time in nanoseconds: " + timeElapsed);
			System.out.printf("should be Buchanan: %s\n", root.data.county);
			System.out.printf("should be McCurtain: %s\n", root.leftChild.data.county);
			System.out.printf("should be Pottawattamie: %s\n", root.rightChild.data.county);
			System.out.printf("should be Maui: %s\n", root.leftChild.leftChild.data.county);
			System.out.printf("should be McCurtain: %s\n", root.leftChild.rightChild.data.county);
			System.out.printf("should be Pottawattamie: %s\n", root.rightChild.leftChild.data.county);
			System.out.printf("should be Washington: %s\n", root.rightChild.rightChild.data.county);
			System.out.printf("should be Lake: %s\n", root.rightChild.rightChild.rightChild.data.county);
			System.out.printf("should be Summit: %s\n", root.rightChild.rightChild.rightChild.rightChild.data.county);
		}
		// For testing with smaller data set -- should NOT be used in prod
		if (balanced.equals("no")) {
			long endTime = System.nanoTime();
			long timeElapsed = endTime - startTime;
			System.out.println("\nUnbalanced tree created\n");
			System.out.println("Execution time in nanoseconds: " + timeElapsed);
			System.out.printf("should be Benton: %s\n", root.data.county);
			System.out.printf("should be Maricopa: %s\n", root.leftChild.data.county);
			System.out.printf("should be Buchanan: %s\n", root.rightChild.data.county);
			System.out.printf("should be Maui: %s\n", root.leftChild.leftChild.data.county);
			System.out.printf("should be McCurtain: %s\n", root.leftChild.rightChild.data.county);
			System.out.printf("should be Pottawattamie: %s\n", root.rightChild.leftChild.data.county);
			System.out.printf("should be Washington: %s\n", root.rightChild.rightChild.data.county);
			System.out.printf("should be Lake: %s\n", root.rightChild.rightChild.rightChild.data.county);
			System.out.printf("should be Summit: %s\n", root.rightChild.rightChild.rightChild.rightChild.data.county);
		}

	}
}