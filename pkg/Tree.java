package pkg;

import java.io.*;

public class Tree {

	private static class Node {
		Coordinate data;
		Node leftChild;
		Node rightChild;
		boolean evenLevel;

		public Node(double lat, double lon, String county, boolean evenLevel) {
			data = new Coordinate(lat, lon, county);
			leftChild = null;
			rightChild = null;
			evenLevel = evenLevel;
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
    	root = insert(root, c, true);
    }

 	public static Node insert(Node node, Coordinate c, boolean evenLevel) {
 		if (node==null) {
 			Node n = new Tree.Node(c.lon, c.lat, c.county, !evenLevel);
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

	public static void main(String[] args) {
		// File file = new File("TestData.txt"); 

		// BufferedReader br = new BufferedReader(new FileReader(file)); 

		// String st; 
		// while ((st = br.readLine()) != null) {
		// 	System.out.println(st); 
		// } 
	}
}