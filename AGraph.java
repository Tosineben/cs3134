//Alden Quimby
//adq2101
//04.10.11
//COMS 3134

//Written by Professor Pasik

public class AGraph extends AbstractGraph implements Graph {

    private int[][] cost;
    private int[] next;
    private String[] name;


    public AGraph (int max) {
        maxsize = max;
        size = 0;
        name = new String[maxsize];
        mark = new int[maxsize];
        cost = new int[maxsize][maxsize];
        next = new int[maxsize];

        for (int i = 0; i < maxsize; i++)
            for (int j = 0; j < maxsize; j++)
                cost[i][j] = Graph.INFINITY;
    }


    public AGraph (LGraph g) {
        this(g.size);
        size = g.size;

        for (int i = 0; i < size; i++) {
            name[i] = g.name(i);
            mark[i] = g.getMark(i);
        }

        for (int i = 0; i < size; i++) {
            for (Edge e = g.firstEdge(i); e != null; e = g.nextEdge(i))
                setCost(i, e.destination(), e.cost());
        }
    }


    public Edge firstEdge (int node) { 
        for (next[node] = 0; next[node] < size && cost(node, next[node]) == Graph.INFINITY; next[node]++) ;
        return (next[node] == size) ? null : new Edge(node, next[node], cost(node, next[node]));
    }

    public Edge nextEdge (int node) {
        for (next[node]++; next[node] < size && cost(node, next[node]) == Graph.INFINITY; next[node]++) ;
        return (next[node] == size) ? null : new Edge(node, next[node], cost(node, next[node]));
    }


    public int cost (int i, int j) { return cost[i][j]; }

    public void setCost (int src, int dest, int value) { cost[src][dest] = value; }


    public int degree (int n) {
        int d = 0;
        for (int j = 0; j < size; j++) if (connected(n,j)) d++;
        return d;
    }

}
