//Alden Quimby
//adq2101
//April 17, 2011
//COMS 3134

1. How to run my program:

Compile all class. Then run the following command:

java curriculum/CheckCurriculum < curriculum/Math

You can also edit the file "Math", and then re-run the above command, to
see how the program works on different inputted curricula.

___________________________________________________________________________

2. Description of classes used:

IO.java, Edge.java, Graph.java, ImmutableList.java

All written by professor Pasik.

AbstractGraph.java

This was also written by professor Pasik. However, I added one line of
code in the writeEdges() method. Each edge is only printed if the cost
is not Graph.INFINITY. The cost of an edge is set to Graph.INFINITY in
my Curriculum class, if the edge is invalid.

CheckCurriculum.java

This class has the main method and is very straightforward. A Curriculum
object "myC" of maxsize 500 is created. Then myC (which is a type of graph) is set to be a graph read from stdin. Then the method fixCurriculum() is called on myC. This method locates, removes, and prints to stderr all of
the edges that are invalid (edges are invalid if they create a cycle
anywhere in the graph). Then the corrected myC is written to stdout.

Curriculum.java

This class is very similar to professor Pasik's LGraph class. There are
only three new methods, and in fact, fixCurriculum() is exactly the
same as the Depth First Search method dfs() that professor Pasik wrote.
The only interesting method is dfs(int i), which examines node i, and is
the bulk of the assignment. First, it should be noted that nothing is
done unless the node has not yet been marked. If the mark is 0, then the following happens:
The mark is set to 1. For every edge, if the mark of the destination is 0,
then dfs the destination node. If not, and the mark of the destination is
1, then we know that the edge was reached in error. This cost of this edge
is set to infinity, and it is printed to stderr. After all edges have been
examined (meaning either depth-first-searched or printed to stderr), the
mark is set to 2.
The mark is set to 2 after every single edge has been checked, because
this means that the node is completely finished with. If any other edge
points to this node, it is not an invalid edge, because it is not part of
a cycle. Invalid edges are cyclical edges. 


Note that my version of ImmutableList has a few methods that were
written for a previous assignment, but aren't used here.
