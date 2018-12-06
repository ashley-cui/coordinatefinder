import java.lang.Math

public class Tree {

	private static class Node {
		Coordinate data;
		Node leftChild;
		Node rightChild;
		boolean evenLevel;

		public Node(double x, double y, String county, boolean evenLevel) {
			data = new Coordinate(x, y, county);
			leftChild = null;
			rightChild = null;
			evenLevel = evenLevel;
		}
	}

	public Coordinate(double lon, double lat, String county) {
		lat = lat;
		lon = lon;
		county = county;
	}
 	
	int dimensions = 2;

 	private Node root;

 	public boolean comparePoints(Node n, Coordinate c, boolean evenLevel) {
 		// On even levels, we look at x coordinates, else y coordinates
 		// Goes to the right if returns true, left if returns false
        if (evenLevel) {
            return c.x() > n.data.x();
        }
        else return c.y() > n.data.y();
    }

    public static void Insert(Coordinate c) {
    	root = insert(root, c, True);
    }

 	public static void insert(Node node, Coordinate c, boolean evenLevel) {
 		if (n==null) {
 			return new Node(c.lon, c.lat, c.county, !evenLevel);
 		}
 		else if (comparePoints(node, c, evenLevel) == True) {
 			//insert as right child
 		} 
 		else if (comparePoints(node, c, evenLevel) == False) {
 			//insert as left child
 		}
 	}

	public static void main(String[] args) {
		
	}
}