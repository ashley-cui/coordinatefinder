// package pkg;

// import java.lang.Math;

// public class Neighbors {





// 	public static int k; //user input for k
// 	public static PriorityQueue<ResultTuple<Double, Coordinate>> queue;

// 	// public static Node[] nearestNodes = new Node[10]; // The nearest nodes to the input point
// 	// public static Double[] nearestDistances = new Double[10]; // The distances from the nearest points
// 	public static Coordinate inputPoint; // The point that the user inputs to find nearest neighbors


// 	public static Double Haversine(Coordinate a, Coordinate b) {
// 		// Find the distance between coordinate pair a and b
// 		Double radiusEarth = 6371.00; // in kilometers
// 		Double x = (b.lon - a.lon) * Math.cos(Math.toRadians((a.lat + b.lat)/2));
// 		Double y = b.lat - a.lat;
// 		Double distance = Math.sqrt(x*x + y*y) * radiusEarth;
// 		return distance;
// 	}





//   	public static void KNN(Coordinate input, int k, Node root) {

//         if (p == null) throw new java.lang.NullPointerException(
//                 "called contains() with a null Point2D");
//         if (isEmpty()) return null;
//     	for (int i = 0; i<k; i++){
//     		queue.push((Double.MAX_VALUE,null));
//     	}



//         return knn(root, input, true);
   
//     }
    
//     private void knn(Node n, Coordinate input, boolean evenLevel) { //operates on queue

//     	//Node n, Point2D p, Point2D champion, boolean evenLevel
        
//         // Handle reaching the end of the tree
//         if (n == null) return;
        

        
//         // Determine if the current Node's point beats the existing champion

//          // if (n.data.distanceSquaredTo(p) < champion.distanceSquaredTo(p)) //change this to haversine comparason 

//          //    champion = n.p;
        
//         if (Haversine(n.data, input) < Haversine(queue.peek().dist, input)) {//change this to haversine comparason 
//         	queue.poll();
//         	queue.add(new ResultTuple(Haversine(n.data, input),n.data));
//         }
        
//         /**
//          * Calculate the distance from the search point to the current
//          * Node's partition line.
//          * 
//          * Primarily, the sign of this calculation is useful in determining
//          * which side of the Node to traverse next.
//          * 
//          * Additionally, the magnitude to toPartitionLine is useful for pruning.
//          * 
//          * Specifically, if we find a champion whose distance is shorter than
//          * to a previous partition line, then we know we don't have to check any
//          * of the points on the other side of that partition line, because none
//          * can be closer.
//          */
//         double toPartitionLine = comparePoints(input, n, evenLevel);
        
//         /**
//          * Handle the search point being to the left of or below
//          * the current Node's point.
//          */
//         if (toPartitionLine < 0) {
//             knn(n.leftChild, input , !evenLevel);
            
//             // Since champion may have changed, recalculate distance
//             if (Haversine(queue.peek(), input) >= toPartitionLine) {
//                 knn(n.rightChild, input, !evenLevel);
//             }
//         }
        
//         /**
//          * Handle the search point being to the right of or above
//          * the current Node's point.
//          * 
//          * Note that, since insert() above breaks point comparison ties
//          * by placing the inserted point on the right branch of the current
//          * Node, traversal must also break ties by going to the right branch
//          * of the current Node (i.e. to the right or top, depending on
//          * the level of the current Node).
//          */
//         else {
//             knn(n.rightChild, p, !evenLevel);
            
//             // Since champion may have changed, recalculate distance
//             if (Haversine(queue.peek(), input) >= toPartitionLine) {
//                 knn(n.leftchild, input, !evenLevel);
//             }
//         }
//         return
//     }
//     public static double comparePoints(Coordinate p, Node n, boolean evenLevel) {
//         if (evenLevel) {
//             return p.lat() - n.p.lat();
//         }
//         else return p.long() - n.p.long();
//     }
// 	public static void main(String[] args) {
// 		queue = new PriorityQueue<Tuple<Double, Coordinate>>(10, Collections.reverseOrder());
// 		while 
// 		// call knn
		
// 	}
// }