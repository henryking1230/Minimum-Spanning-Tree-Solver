The purpose of this code was to practice solving for 
A minimum spanning tree by using a variation of Prim's
Algorithm. 

The code reads in an imputes file named 'input_MST.txt'
That describes the layout of an adjacency matrix. 
	-The first line is the maximum number of vertices
	-The second line is the number of edges in matrix
	-The remaining lines each contain 3 elements
		-integer representing a vertex
		-integer representing another vertex
		-floating point number representing the 
		 weight of the edge between the two vertices 

The code then outputs a list of the individual edges of the 
MST to STDERR and the final MST weight to STDOUT