//Alden Quimby
//adq2101
//04.10.11
//COMS 3134

package curriculum;

public class Curriculum extends AbstractGraph implements Graph {

    private ImmutableList[] edges;
    private ImmutableList[] next;

    public Curriculum (int max) {
        maxsize = max;
        size = 0;
        values = new Object[maxsize];
        mark = new int[maxsize];
        edges = new ImmutableList[maxsize];
        next = new ImmutableList[maxsize];
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

//******************************** NEW *************************************

    public void fixCurriculum () {
	for (int i = 0; i < size; i++) setMark(i, 0);
        for (int i = 0; i < size; i++) dfs(i);
    }

    private void dfs (int i) {
        if (getMark(i) == 0) {
            setMark(i, 1);

            for (Edge e = firstEdge(i); e != null; e = nextEdge(i)) {
                if (getMark(e.destination()) == 0)
		    dfs(e.destination());

		if (getMark(e.destination()) == 1) {
		    e.setCost(Graph.INFINITY);
		    IO.stderr.print(this.invalidEdge(e));
		}
	    }

            setMark(i, 2);
	}
    }


    public String invalidEdge(Edge e) { 
	return "Invalid edge: the connection from " + values[e.source()] + " to "
		+ values[e.destination()] + " has been removed.\n";
    }



}

