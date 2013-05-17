//Alden Quimby
//adq2101
//02.25.11
//COMS 3134

package lists;

public class ListSet implements Set {

    private ImmutableList list;
    private int count;
    //count counts how many times next() has been called

    public ListSet() { count = 0; list = ImmutableList.NIL; }

    public int size () { return list.length(); }

    public boolean isEmpty () { return list.isEmpty(); }

    //the find(e) method will return an ImmutableList that has e as it's head,
    //so if this call returns an empty list, then isMember() return false
    public boolean isMember (Comparable e) { return !list.find(e).isEmpty(); }

    //note that if an element is already part of a set, it will not be included
    public void include (Comparable e) { if (!this.isMember(e)) list = list.insert(e); }

    public void exclude (Comparable e) { list = list.delete(e); }

    public Comparable first () {
	count = 0;
	return (Comparable)(list.head());
    }

    //should be used together with first(). sequential calls to this method
    //return every other element of "this" not returned by first
    public Comparable next () {
	if (count >= this.size()-1) return null;
	ImmutableList temp = list;
	for (int i = 0; i <= count; i++) temp = temp.tail();
	count++;
	return (Comparable)(temp.head());
    }

    public Set empty () { return new ListSet(); }

    //iterating over "this", every element is copied into "result"
    public Set copy () {
	Set result = this.empty();
	for (Comparable e = this.first(); e != null; e = this.next()) result.include(e);
	return result;
    }

    //taken from professor Pasik's AbstractSet class
    public Set union (Set that) {
        Set result = this.copy();
        for (Comparable e = that.first(); e != null; e = that.next()) result.include(e);
        return result;
    }

    //taken from professor Pasik's AbstractSet class
    public Set intersection (Set that) {
        Set result = this.empty();
        for (Comparable e = that.first(); e != null; e = that.next()) 
            if (this.isMember(e)) result.include(e);
        return result;
    }

    public String toString() { 
	String result = "{ " + list.toString().substring(1);
	return (result.substring(0, result.length() - 1) + " }");
    }

}