//Alden Quimby
//adq2101
//02.25.11
//COMS 3134
//Written by Alexander Pasik

package lists;

public interface Set {

    public int size ();

    public boolean isEmpty ();

    public boolean isMember (Comparable e);

    public void include (Comparable e);

    public void exclude (Comparable e);

    public Set union (Set that);

    public Set intersection (Set that);

    public Comparable first ();

    public Comparable next ();

    public Set copy ();

    public Set empty ();

}
