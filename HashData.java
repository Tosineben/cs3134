//Written by Professor Pasik

public class HashData implements Comparable {

    private String str;
    private int count;
    private int modulus;

    public HashData (String s, int m) { str = s; count = 1; modulus = m; }

    public boolean equals (Object d) {
	return d != null &&
	    d.getClass().equals(getClass()) &&
	    str.equals(((HashData) d).str); 
    }

    public int compareTo (Object d) {
	return str.compareTo(((HashData) d).str); 
    }

    public String toString () { return str; }

    public int hashCode () {
	int x = 0;
	for (int i = 0; i < str.length(); i++)
	    x = (((int) str.charAt(i)) + 256 * x) % modulus;
	return x;
    }

    public void incr () { count++; }

}