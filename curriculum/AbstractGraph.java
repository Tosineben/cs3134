//Written by Professor Pasik

package curriculum;

import java.io.*;

abstract public class AbstractGraph implements Graph {

    protected int maxsize;
    protected int size;
    protected Object[] values;
    protected int[] mark;

    public int getMark (int node) { return mark[node]; }

    public void setMark (int node, int value) { mark[node] = value; }

    public int size () { return size; }

    public Object value (int node) { return values[node]; }

    abstract public Edge firstEdge (int node) ;

    abstract public Edge nextEdge (int node) ;

    public int find (Object o) {
	for (int i = 0; i < size; i++) if (values[i].equals(o)) return i;
	return Graph.FAIL;
    }

    abstract public int cost (int src, int dest) ;

    abstract public void setCost (int src, int dest, int value) ;

    public boolean connected (int src, int dest) { return cost(src, dest) != Graph.INFINITY; }

    abstract public int degree (int node) ;

    private int findOrAdd (Object o) {
	int i = find(o);

	if (i != Graph.FAIL) return i;
	if (size == maxsize) return Graph.FAIL;

	values[size] = o;
	return size++;
    }

    public void read (BufferedReader in) throws IOException {

	final int SOURCE = 0;
	final int DESTINATION = 1;
	final int COST = 2;

	String line;

	while ((line = in.readLine()) != null && line.length() != 0) {

	    String sdc[] = line.split("\t"); // sdc is source, destination, cost

	    int source = this.findOrAdd(sdc[SOURCE]);

	    if (source == Graph.FAIL) throw new IOException("Graph too large.");

	    if (sdc.length > 1) {
		int destination = this.findOrAdd(sdc[DESTINATION]);
		if (destination == Graph.FAIL) throw new IOException("Graph too large.");
		this.setCost(source, destination, Integer.parseInt(sdc[COST]));
	    }

	}
    }

    public void read () throws IOException { read(IO.stdin); }

    private void writeEdges (PrintStream p, int node) {

	for (Edge e = firstEdge(node); e != null; e = nextEdge(node)) {

	    int destination = e.destination();

	    setMark(node, 1);
	    setMark(destination, 1);

	    if (e.cost() != Graph.INFINITY)
	        p.println(value(node) + "\t" + value(destination) + "\t" + e.cost());
	}
    }

    public void write (PrintStream p) {
	for (int i = 0; i < size; i++) setMark(i, 0);
	for (int i = 0; i < size; i++) writeEdges(p, i);
	for (int i = 0; i < size; i++) if (getMark(i) == 0) p.println(value(i));
    }

    public void write () { write(IO.stdout); }

}

