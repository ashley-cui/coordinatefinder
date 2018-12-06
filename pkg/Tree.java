package pkg;

import java.io.*;

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
 			Node n = new Node(c.lat, c.lon, c.county, !evenLevel);
 			return n;
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

		// Tree t = new Tree();

		// t.Insert(new Coordinate(100.0,10.0,"County"));
		// t.Insert(new Coordinate(50.0,200.0,"County"));
		// t.Insert(new Coordinate(250.0,7.0,"County"));
		// t.Insert(new Coordinate(75.0,4.0,"County"));
		// t.Insert(new Coordinate(27.0,400.0,"County"));
		// t.Insert(new Coordinate(470.0,2.0,"County"));
		// t.Insert(new Coordinate(150.0,82.0,"County"));

		// System.out.printf("should be 100 %f\n", root.data.lat);
		// System.out.printf("should be 50 %f\n", root.leftChild.data.lat);
		// System.out.printf("should be 250 %f\n", root.rightChild.data.lat);
		// System.out.printf("should be 75 %f\n", root.leftChild.leftChild.data.lat);
		// System.out.printf("should be 27 %f\n", root.leftChild.rightChild.data.lat);
		// System.out.printf("should be 470 %f\n", root.rightChild.leftChild.data.lat);
		// System.out.printf("should be 150 %f\n", root.rightChild.rightChild.data.lat);


		Tree t = new Tree();

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
			String county = pieces[0] + " - " + pieces[1];
			Double lat = Double.parseDouble(pieces[2]);
			Double lon = Double.parseDouble(pieces[3]);
			t.Insert(new Coordinate(lat, lon, county));
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