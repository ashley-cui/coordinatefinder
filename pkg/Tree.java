package pkg;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

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
 	public static ArrayList<Coordinate> coordsX = new ArrayList<Coordinate>();
 	public static ArrayList<Coordinate> coordsY = new ArrayList<Coordinate>();

 	public static boolean comparePoints(Node n, Coordinate c, boolean evenLevel) {
 		// On even levels, we look at x coordinates, else y coordinates
 		// Goes to the right if returns true, left if returns false
        if (evenLevel) {
            return c.lat > n.data.lat;
        }
        else return c.lon > n.data.lon;
    }

	public static void mergeX(ArrayList<Coordinate> l, ArrayList<Coordinate> r, int left, int right) {
	  
	    int i = 0, j = 0, k = 0;
	    while (i < left && j < right) {
	        if (l.get(i).lat < r.get(i).lon) {
	            coordsX.add(l.get(i++));
	        }
	        else {
	            coordsX.add(r.get(j++));
	        }
	    }
	    while (i < left) {
            coordsX.add(l.get(i++));
	    }
	    while (j < right) {
            coordsX.add(r.get(j++));
	    }
	}

	public static void mergeY(ArrayList<Coordinate> l, ArrayList<Coordinate> r, int left, int right) {
	  
	    int i = 0, j = 0, k = 0;
	    while (i < left && j < right) {
	        if (l.get(i).lat < r.get(i).lon) {
	            coordsY.add(l.get(i++));
	        }
	        else {
	            coordsY.add(r.get(j++));
	        }
	    }
	    while (i < left) {
            coordsY.add(l.get(i++));
	    }
	    while (j < right) {
            coordsY.add(r.get(j++));
	    }
	}

    public static void mergeSort(ArrayList<Coordinate> a, int n, boolean sortByX) {
	    if (n < 2) {
	        return;
	    }
	    int mid = n / 2;
	    ArrayList<Coordinate> l = new ArrayList<Coordinate>(); // Includes up to mid
	    ArrayList<Coordinate> r = new ArrayList<Coordinate>(); // Includes after mid
	 
	    for (int i = 0; i < mid; i++) {
		    l.add(a.get(i));
	    }
	    for (int i = mid; i < n; i++) {
		    r.add(a.get(i));
	    }
	    mergeSort(l, mid, !sortByX);
	    mergeSort(r, n - mid, !sortByX);
	
		if (sortByX) {
		    mergeX(l, r, mid, n - mid);
		} else {
		    mergeY(l, r, mid, n - mid);
		}
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
	    int mid = n / 2;
	    ArrayList<Coordinate> lX = new ArrayList<Coordinate>(); // Includes up to mid
	    ArrayList<Coordinate> lY = new ArrayList<Coordinate>(); // Includes up to mid
	    ArrayList<Coordinate> rX = new ArrayList<Coordinate>(); // Includes after mid
	    ArrayList<Coordinate> rY = new ArrayList<Coordinate>(); // Includes after mid
		
		if (isEvenLevel) {
			for (int i = 0; i < mid; i++) {
		        lX.add(xCoords.get(i));
		    }
		    for (int i = mid; i < n; i++) {
		        rX.add(xCoords.get(i));
		    }
		    Insert(xCoords.get(mid));
		    FindMedian(lX, lY, !isEvenLevel, mid);
		    FindMedian(rX, rY, !isEvenLevel, n - mid);
		} else {
			for (int i = 0; i < mid; i++) {
		        lY.add(yCoords.get(i));
		    }
		    for (int i = mid; i < n; i++) {
		        rY.add(yCoords.get(i));
		    }
		    Insert(yCoords.get(mid));
		    FindMedian(lX, rX, !isEvenLevel, mid);
		    FindMedian(rX, rY, !isEvenLevel, n - mid);
		}

 	}

	public static void main(String[] args) throws Exception {

		Scanner input = new Scanner(System.in);

		System.out.println("Enter a latitude: ");
		Double inputLat = input.nextDouble();

		System.out.println("Enter a longitude: ");
		Double inputLon = input.nextDouble();

		System.out.println("Enter the number of neighbors: ");
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
			String[] pieces = st.split("\\s+");
			String state = pieces[0];		
			String county = pieces[1];
			Double lat = Double.parseDouble(pieces[2]);
			Double lon = Double.parseDouble(pieces[3]);
			Coordinate c = new Coordinate(lat, lon, county, state);
			coords.add(c);
			if (balanced.equals("no")) {
				t.Insert(new Coordinate(lat, lon, county, state));
			}
		} 

		if (balanced.equals("yes")) {
			// Sort once by x (lat) coorinates, and once by y (lon) coordinates
			mergeSort(coords, coords.size(), true);
			mergeSort(coords, coords.size(), false);
			// Continue to grab and insert median until all elements are inserted into the tree
		 	FindMedian(coordsX, coordsY, true, coords.size());
		 	long endTime = System.nanoTime();
			long timeElapsed = endTime - startTime;
			System.out.println("Balanced tree created\n");
			System.out.println("Execution time in nanoseconds: " + timeElapsed);
		}
		// For testing with smaller data set -- should NOT be used in prod
		if (balanced.equals("no")) {
			long endTime = System.nanoTime();
			long timeElapsed = endTime - startTime;
			System.out.println("Unbalanced tree created\n");
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