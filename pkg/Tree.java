package pkg;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

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
 	public static ArrayList<Coordinate> coordsX;
 	public static ArrayList<Coordinate> coordsY;

 	public static boolean comparePoints(Node n, Coordinate c, boolean evenLevel) {
 		// On even levels, we look at x coordinates, else y coordinates
 		// Goes to the right if returns true, left if returns false
        if (evenLevel) {
            return c.lat > n.data.lat;
        }
        else return c.lon > n.data.lon;
    }

    // public static void sortCoords(ArrayList<Coordinate> coords, boolean sortByX) {
    // 	if (coords == null || coords.size() == 0) {
    //         return;
    //     }
    //     this.array = coords;
    //     length = coords.size();
    //     if (sortByX) {
    //     	quickSortX(0, length - 1);
    //     }
    //     else {
    //     	quickSortY(0, length - 1);
    //     }
    // }

    // private void quickSortX(int lowerIndex, int higherIndex) {
        
    //     int i = lowerIndex;
    //     int j = higherIndex;
    //     int middle = coordsX.get(lowerIndex+(higherIndex-lowerIndex)/2);
    //     while (i <= j) {
    //         while (coordsX.get(i) < middle) {
    //             i++;
    //         }
    //         while (coordsX.get(j) > middle) {
    //             j--;
    //         }
    //         if (i <= j) {
    //             int temp = coordsX.get(i);
		  //       coordsX.get(i) = coordsX.get(j);
		  //       coordsX.get(j) = temp;
    //             i++;
    //             j--;
    //         }
    //     }
    //     if (lowerIndex < j)
    //         quickSortX(lowerIndex, j);
    //     if (i < higherIndex)
    //         quickSortX(i, higherIndex);
    // }

    // private void quickSortY(int lowerIndex, int higherIndex) {
        
    //     int i = lowerIndex;
    //     int j = higherIndex;
    //     int middle = coordsY.get(lowerIndex+(higherIndex-lowerIndex)/2);
    //     while (i <= j) {
    //         while (coordsY.get(i) < middle) {
    //             i++;
    //         }
    //         while (coordsY.get(j) > middle) {
    //             j--;
    //         }
    //         if (i <= j) {
    //             int temp = coordsY.get(i);
		  //       coordsY.get(i) = coordsY.get(j);
		  //       coordsY.get(j) = temp;
    //             i++;
    //             j--;
    //         }
    //     }
    //     if (lowerIndex < j)
    //         quickSortY(lowerIndex, j);
    //     if (i < higherIndex)
    //         quickSortY(i, higherIndex);
    // }

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

 	public void FindMedian(ArrayList<Coordinate> xCoords, ArrayList<Coordinate> yCoords, boolean isEvenLevel, int n) {
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
			t.Insert(new Coordinate(lat, lon, county, state)); // TODO: remove later
		} 

		// Continue to grab and insert median until all elements are inserted into the tree
		// sortCoords(coords, true);
		// sortCoords(coords, false);
		// int halfSize = coords.size() / 2;
		// int fullSize = coords.size();
		// int divisor = 2;
		// boolean halfDone = false;
		// for (int i=0; i<coords.size(); i++) {
		// 	if (halfDone) {
		// 		int medianIndex = i/divisor + halfSize;
		// 	} else {
		// 		int medianIndex = i/divisor;
		// 	}
		// 	t.Insert(coordsX.get(medianIndex));
		// 	t.Insert(coordsY.get(medianIndex));
		// 	if (divisor == halfSize) {
		// 		divisor = 2;
		// 		halfDone = true;
		// 	} else {
		// 		divisor = divisor * 2;
		// 	}
		// }

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