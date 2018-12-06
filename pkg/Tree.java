package pkg;

import java.io.*;

public class Tree {

	private static class Node {
		//Coordinate data;
		Node leftChild;
		Node rightChild;
		boolean evenLevel;
		double lat;
		double lon;
		String county;

		public Node(double lat, double lon, String county, boolean evenLevel) {
			lat = lat;
			lon = lon;
			county = county;
			leftChild = null;
			rightChild = null;
			evenLevel = evenLevel;
		}
	}
 	
	int dimensions = 2;

 	public static Node root;

 	public static boolean comparePoints(Node n, double lat, double lon, boolean evenLevel) {
 		// On even levels, we look at x coordinates, else y coordinates
 		// Goes to the right if returns true, left if returns false
        if (evenLevel) {
            return lat > n.lat;
        }
        else return lon > n.lon;
    }

    public static void Insert(double lat, double lon, String county) {
    	if (root == null) {
    		System.out.println(lon);
    		root = insert(root, lat, lon, county, true);
    	}
    	else {
    		System.out.println(lon);
    		insert(root, lat, lon, county, true);
    	}
    }

 	public static Node insert(Node node, double lat, double lon, String county, boolean evenLevel) {
 		if (node==null) {
 			Node n = new Node(lat, lon, county, evenLevel);
 			return n;
 		}
 		else if (comparePoints(node, lat, lon, evenLevel)) {
 			//insert as right child
 			node.rightChild = insert(node.rightChild, lat, lon, county, !evenLevel);
 		} 
 		else if (!comparePoints(node, lat, lon, evenLevel)) {
 			//insert as left child
 			node.leftChild = insert(node.leftChild, lat, lon, county, !evenLevel);
 		}
 		return node;
 	}

	public static void main(String[] args) {

		Tree t = new Tree();

		t.Insert(100.0,10.0,"County");
		t.Insert(50.0,200.0,"County");
		t.Insert(250.0,7.0,"County");
		t.Insert(75.0,4.0,"County");
		t.Insert(27.0,400.0,"County");
		t.Insert(470.0,2.0,"County");
		t.Insert(150.0,82.0,"County");

		System.out.printf("should be 100 %f\n", root.lat);
		System.out.printf("should be 50 %f\n", root.leftChild.lat);
		System.out.printf("should be 250 %f\n", root.rightChild.lat);
		System.out.printf("should be 75 %f\n", root.leftChild.leftChild.lat);
		System.out.printf("should be 27 %f\n", root.leftChild.rightChild.lat);
		System.out.printf("should be 470 %f\n", root.rightChild.leftChild.lat);
		System.out.printf("should be 150 %f\n", root.rightChild.rightChild.lat);



		// File file = new File("TestData.txt"); 

		// BufferedReader br = new BufferedReader(new FileReader(file)); 

		// String st; 
		// while ((st = br.readLine()) != null) {
		// 	System.out.println(st); 
		// } 
	}
}