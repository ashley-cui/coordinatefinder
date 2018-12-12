# Coordinate Finder


## Overview

Using a large data set of coordinates in the U.S., Coordinate Finder takes a
query from the user in the form of a latitude value and a longitude value.  It
will return the K (where K is an integer 1-10) nearest points in the data set.
Additionally, it will provide the county and state of the input.


## How to Run Locally

Ensure that the Java JDK is installed on your computer.

To run the program, simply run the following command in the main project directory:
```
$ javac pkg/Tree.java
$ java pkg/Tree
``` 
This will compile and run the code, and then prompt the user for each of
three things:
1. The latitude of the query
2. The longitude of the query
3. K, an integer between 1 and 10, which specifies the number of nearest points
that the program should return to the user


## Implementation of the Problem

### Storing the Data
Written in Java, a K-D tree is implemented in order to properly store the base
data set that the imput is compared against.  It is useful in storing
multi-dimensional data, such as the 2-dimensional data type of a geographical
coordinate.  In order to properly handle these coordinates, a Coordinate class
is used to store all relevant data (latitude, longitude, county, state).

### Finding the Nearest Neighbors
The program utilizes a K-nearest-neighbors algorithm to efficiently find the K
nearest neighbors to the input point, where K is between 1 and 10.  To calculate
the distance between the input point and the data points in our data set, it utilizes
[equirectangular approximation](http://www.movable-type.co.uk/scripts/latlong.html).

### Computing the County and State of the Input
To determine a given input coordinate's county and state, the program looks first at
the 5 nearest neighbors of the input.  This is done as explained in the above
section, entitled "Finding the Nearest Neighbors".  Based on these 5, it will return
the county and state combination that appears most frequently.


## Comparisons

To prove that the K-nearest-neighbors algorithm is faster, it is compared with the 
brute force method.  The brute force method is contained within a separate function
that does not run automatically.  To implement the brute force method, simply uncomment
the code with the "BRUTE FORCE" comment line above it in the `Tree.java` file.  Then
run the program as normal.

