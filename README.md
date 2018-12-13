# Coordinate Finder


## Overview

Using a large data set of coordinates in the U.S., Coordinate Finder takes a
query from the user in the form of a latitude value and a longitude value.  It
will return the K (where K is an integer 1-10) nearest points in the data set.
Additionally, it will provide the county and state of the input.


## How to Run Locally

Ensure that the Java JDK is installed on your computer.

To run the program, simply run the following commands in the main project directory:
```bash
$ javac pkg/*.java
$ java pkg/Tree
``` 
This will compile and run the code, and then prompt the user for each of
four things:
1. The latitude of the query
2. The longitude of the query
3. A string no or yes, with the former indicating the use of an unbalanced tree,
and the latter indicating the use of a balanced tree.


## Implementation of the Problem

### Storing the Data
Written in Java, a K-D tree is implemented in order to properly store the base
data set that the imput is compared against.  It is useful in storing
multi-dimensional data, such as the 2-dimensional data type of a geographical
coordinate.  In order to properly handle these coordinates, a Coordinate class
is used to store all relevant data (latitude, longitude, county, state).  In order
to balance the tree, the algorithm first uses Merge Sort to sort the coordinates,
and then recursively inserts the median into the tree on successively smaller array 
sizes until all coordinates have been inserted into the tree.

### Finding the Nearest Neighbor
The program utilizes a Nearest-neighbors algorithm to efficiently find the
nearest neighbor to the input point.  To calculate
the distance between the input point and the data points in our data set, it utilizes
[equirectangular approximation](http://www.movable-type.co.uk/scripts/latlong.html). It uses a recursive find nearest method that searches different subtrees to find the nearest point, and consistently updating the smallest distance. 



## Comparisons

To prove that the balanced k-d tree is faster, it is compared with the unbalanced tree.
When the program is run, the user is prompted to specify whether they want to use a
balanced or unbalanced tree.  Upon specification, the program will run with that type
of tree.  Once it has finished running, the program will tell you how long it took to
run in nanoseconds.  This number can be compared with the time from using the alternative
version of the tree to see which method will run faster.

The unbalanced tree on average has better construct time, but the balanced tree has better search time.

## Implemented Work
The unbalanced and balanced tree are properly implementmented. The nearest neighbors algorithm was also implemented. However, the nearest neighbors algorithm has a few bugs in it, and may return the incorrect location. But, it is consistent with the location it returns, regardless of the tree. Thus, it may be an off by one error or the likes.Tthe K nearest Neighbors was not implemented due to complications with Java's queue function. 

## Work Breakdown 
Ashley implemented the nearest neighbors search while Sarah implemented the trees. Pair coding was used a lot, so git commits may not reflect actual work done by any individual. Debugging was done cooperatively, as was this README. 
