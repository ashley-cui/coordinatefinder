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
3. K, an integer between 1 and 10, which specifies the number of nearest points
that the program should return to the user
4. A string no or yes, with the former indicating the use of an unbalanced tree,
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

### Finding the Nearest Neighbors
The program utilizes a K-nearest-neighbors algorithm to efficiently find the K
nearest neighbors to the input point, where K is between 1 and 10.  To calculate
the distance between the input point and the data points in our data set, it utilizes
[equirectangular approximation](http://www.movable-type.co.uk/scripts/latlong.html).
With a priority queue, the program stores the K closest points that it has found so
far, replacing the values with closer ones as it progresses through the nodes in 
the tree.

### Computing the County and State of the Input
To determine a given input coordinate's county and state, the program looks first at
the 5 nearest neighbors of the input.  This is done as explained in the above
section, entitled "Finding the Nearest Neighbors".  Based on these 5, it will return
the county and state combination that appears most frequently.


## Comparisons

To prove that the balanced k-d tree is faster, it is compared with the unbalanced tree.
When the program is run, the user is prompted to specify whether they want to use a
balanced or unbalanced tree.  Upon specification, the program will run with that type
of tree.  Once it has finished running, the program will tell you how long it took to
run in nanoseconds.  This number can be compared with the time from using the alternative
version of the tree to see which method will run faster.

