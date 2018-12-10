package pkg;

import java.io.*;
import java.util.ArrayList

public class Tree {

	private static class Node {
		Coordinate data;
		Node leftChild;
		Node rightChild;
		boolean evenLevel;

		public Node(Double lat, Double lon, String county, boolean evenLevel) {
			this.data = new Coordinate(lat, lon, county);
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

    public static void sortCoords(ArrayList<Coordinate> coords, boolean sortByX) {
    	if (coords == null || coords.size() == 0) {
            return;
        }
        this.array = coords;
        length = coords.size();
        if (sortByX) {
        	quickSortX(0, length - 1);
        }
        else {
        	quickSortY(0, length - 1);
        }
    }

    private void quickSortX(int lowerIndex, int higherIndex) {
        
        int i = lowerIndex;
        int j = higherIndex;
        int middle = coordsX.get(lowerIndex+(higherIndex-lowerIndex)/2);
        while (i <= j) {
            while (coordsX.get(i) < middle) {
                i++;
            }
            while (coordsX.get(j) > middle) {
                j--;
            }
            if (i <= j) {
                int temp = coordsX.get(i);
		        coordsX.get(i) = coordsX.get(j);
		        coordsX.get(j) = temp;
                i++;
                j--;
            }
        }
        if (lowerIndex < j)
            quickSortX(lowerIndex, j);
        if (i < higherIndex)
            quickSortX(i, higherIndex);
    }

    private void quickSortY(int lowerIndex, int higherIndex) {
        
        int i = lowerIndex;
        int j = higherIndex;
        int middle = coordsY.get(lowerIndex+(higherIndex-lowerIndex)/2);
        while (i <= j) {
            while (coordsY.get(i) < middle) {
                i++;
            }
            while (coordsY.get(j) > middle) {
                j--;
            }
            if (i <= j) {
                int temp = coordsY.get(i);
		        coordsY.get(i) = coordsY.get(j);
		        coordsY.get(j) = temp;
                i++;
                j--;
            }
        }
        if (lowerIndex < j)
            quickSortY(lowerIndex, j);
        if (i < higherIndex)
            quickSortY(i, higherIndex);
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
 			Node n = new Node(c.lat, c.lon, c.county, !evenLevel);
 			return n;
 		}
 		else if (node.lat == c.lat && node.lon == c.lon) {
 			return;
 		}
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

	public static void main(String[] args) throws Exception {

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
			String county = pieces[0] + " - " + pieces[1]; // Concat state and county
			Double lat = Double.parseDouble(pieces[2]);
			Double lon = Double.parseDouble(pieces[3]);
			Coordinate c = new Coordinate(lat, lon, county);
			coords.add(c);
			// t.Insert(new Coordinate(lat, lon, county));
		} 

		// Continue to grab and insert median until all elements are inserted into the tree
		sortCoords(coords, true);
		sortCoords(coords, false);
		for (int i=0; i<coords.size(); i++) {
			median = 0; // TODO: grab median from coords
			t.Insert(coordsX.get(median));
			t.Insert(coordsY.get(median));
		}

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