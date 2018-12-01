public class Tree {

	private static class Node {
		Coordinate data;
		Node leftChild;
		Node rightChild;
		int depth;

		public Node(double x, double y, int depth) {
			data = new Coordinate(x, y);
			leftChild = null;
			rightChild = null;
			depth = depth;

			public Coordinate(double x, double y, String county) {
				xCoor = x;
				yCoor = y;
				county = county;
			}
		}
	}
 	
	int dimensions = 2;

 	private Node root;

	public static void main(String[] args) {
		
	}
}