//Alden Quimby
//adq2101
//04.10.11
//COMS 3134

package curriculum;

public class Curriculum extends AbstractGraph implements Graph {

    private ImmutableList[] edges;
    private ImmutableList[] next;
    private String[] name;

    private ImmutableList removedEdges;


    public Curriculum (int max) {
        maxsize = max;
        size = 0;
        name = new String[maxsize];
        mark = new int[maxsize];
        edges = new ImmutableList[maxsize];
        next = new ImmutableList[maxsize];
	removedEdges = ImmutableList.NIL;
        for (int i = 0; i < maxsize; i++) edges[i] = ImmutableList.NIL;
    }

    public Edge firstEdge (int node) {
        next[node] = edges[node];
        return next[node].isEmpty() ? null : (Edge) next[node].head();
    }

    public Edge nextEdge (int node) {
        next[node] = next[node].tail();
        return next[node].isEmpty() ? null : (Edge) next[node].head();
    }

    public int cost (int src, int dest) {
        for (Edge e = firstEdge(src); e != null; e = nextEdge(src))
            if (e.destination() == dest) return e.cost();
        return Graph.INFINITY;
    }

    public void setCost (int src, int dest, int cost) {
        Edge e = findEdge(src, dest);
        if (e == null)  edges[src] = edges[src].push(new Edge(src, dest, cost));
        else e.setCost(cost);
    }

    public int degree (int node) { return edges[node].length(); }

    private Edge findEdge (int src, int dest) {
        for (Edge e = firstEdge(src); e != null; e = nextEdge(src)) 
            if (e.destination() == dest) return e;
        return null;
    }

******************************** NEW *************************************

   
    //returns a fixed curriculum, and pushes bad edges onto removedEdges
    public Curriculum fixCurriculum() {



    }


    public String removedEdges() { 

	String result = "";

	for (ImmutableList p = this.removedEdges; !p.isEmpty(); p = p.tail()) {

	    


    }



    //returns false if there's a cycle somewhere in the curriculum
    public boolean checkCurriculum() {



    }



    public void setName(int i, String s) { this.names[i] = s; }


    //return a curriculum with appropriate edges and names
    public Curriculum parse(String s) {

	String[] allEdges = s.split("\n");

	String[] oneEdge = new String[3];

	Curriculum result = new Curriculum(2*allEdges.length);

	for (int i = 0; i < allEdges.length; i++) {

	    oneEdge = allEdges[i].split(" ");
	
	    if (oneEdge[1].length() == 0) result.setName(i, oneEdge[0]);

	    else {

		result.setNam


	}

    }


 
/*
   public String toString() {
	String result = "";
	for (int i = 0; i < this.size; i++) {
	    result += this.names[i] + " (";
	    for (ImmutableList p = this.edges[i]; !p.isEmpty(); p = p.tail()) {
		result += (p.tail().isEmpty()) ? "" : " ";
		result += this.names[p.head().destination()];
	    }
	    result += (i==this.size-1) ? ")" : ")\n";
	}
	return result;
    }

*/

    public String toString() {

	String result = "";

	for (int i = 0; i < this.size; i++)
	    for (Edge e = this.firstEdge(i); e != null; e = nextEdge(i))
		result += names[e.source()] + " " + names[e.destination()] + " " + cost + "\n";
	
	return result.substring(0, result.length() - 1);

    }








}
