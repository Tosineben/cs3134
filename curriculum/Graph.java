//Written by Professor Pasik

package curriculum;

import java.io.*;

public interface Graph {

    public static final int INFINITY = Integer.MAX_VALUE;
    public static final int FAIL = -1;

    public int getMark (int node);

    public void setMark (int node, int value);

    public int size ();

    public Object value (int node);

    public Edge firstEdge (int node);

    public Edge nextEdge (int node);

    public int find (Object value);

    public int cost (int src, int dest);

    public void setCost (int src, int dest, int cost);

    public boolean connected (int src, int dest);

    public int degree (int node);

    public void read (BufferedReader in) throws IOException;

    public void read () throws IOException;

    public void write (PrintStream out);

    public void write ();

}