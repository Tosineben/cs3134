//Alden Quimby
//adq2101
//04.10.11
//COMS 3134

//Written by Professor Pasik

package curriculum;

public class Edge implements Comparable {

    private int source;
    private int cost;
    private int destination;

    public Edge (int src, int dest, int c) { source = src; destination = dest; cost = c; }

    public boolean equals (Object o) {
        Edge that = (Edge) o;
        return this.source == that.source && this.destination == that.destination && this.cost == that.cost;
    }

    public int compareTo (Object d) { return ((Integer) cost).compareTo((Integer) ((Edge) d).cost); }

    public int source () { return source; }

    public int destination () { return destination; }

    public int cost () { return cost; }

    public void setCost (int value) { cost = value; }

}
