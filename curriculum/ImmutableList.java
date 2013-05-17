//Alden Quimby
//adq2101
//04.10.11
//COMS 3134

package curriculum;

public class ImmutableList {

    private final  Object data;
    private final ImmutableList next;
    public static final ImmutableList NIL = new ImmutableList();


    private ImmutableList (Object d, ImmutableList n) { data = d; next = n; }

    private ImmutableList () {  data = null; next = null; }


    public ImmutableList nil () { return NIL; }

    public ImmutableList push (Object d) { return new ImmutableList(d, this); }

    public Object head () { return data; }

    public ImmutableList tail () { return next; }

    public boolean isEmpty () { return this == nil(); }

    public int length () { return this.isEmpty() ? 0 : 1 + this.tail().length(); }

    public ImmutableList find (Object d) { 
        return this.isEmpty() || d.equals(this.head()) ? this : this.tail().find(d); 
    }

    public ImmutableList append (ImmutableList a) { 
        return this.isEmpty() ? a : this.tail().append(a).push(this.head()); 
    }

    public ImmutableList reverse () { 
        return this.isEmpty() ? this : this.tail().reverse().append(nil().push(this.head())); 
    }

    public Object nth (int n) {
        if (this.isEmpty() || n < 0) return null;
        return n == 0 ? this.head() : this.tail().nth(n - 1);
    }

    public ImmutableList insert (Comparable d) {
        return (this.isEmpty() || d.compareTo(this.head()) < 0) ? 
            this.push(d) : 
            this.tail().insert(d).push(this.head());
    }

    public ImmutableList delete (Object d) {
        if (this.isEmpty()) return this;
        if (d.equals(this.head())) return this.tail().delete(d);
        return this.tail().delete(d).push(this.head());
    }

//********************************
//METHODS WRITTEN BY ALDEN QUIMBY:
//********************************

    public ImmutableList purge() {
	if (this.isEmpty()) return this;
	return this.tail().delete(this.head()).purge().push(this.head());
    }

    public ImmutableList sort() {
	if (this.isEmpty() || this.tail().isEmpty()) return this;
	return this.firstN(this.length()/2).sort().merge(this.notFirstN(this.length()/2).sort());
    }

    private ImmutableList merge(ImmutableList that) {
	if (this.isEmpty()) return that;
	if (that.isEmpty()) return this; 
	if (((Comparable)(this.head())).compareTo((Comparable)(that.head())) < 0)
	    return this.tail().merge(that).push(this.head());
	return this.merge(that.tail()).push(that.head());
    }

    private ImmutableList firstN(int n) {
	if (n < 1 || n > this.length()) throw new IllegalArgumentException("Huh?");
	if (n == this.length()) return this;
	if (n == 1) return this.nil().push(this.head());
	return this.tail().firstN(n-1).push(this.head());
    }

    private ImmutableList notFirstN(int n) {
	if (n < 0 || n >= this.length()) throw new IllegalArgumentException("Huh?");
	if (n == 0) return this;
	if (n == 1) return this.tail();
	return this.tail().notFirstN(n-1);
    }

//**************************************
//END OF METHODS WRITTEN BY ALDEN QUIMBY
//**************************************

    public String toString () {
        return this.isEmpty() ? "()" : "(" + this.head().toString() + 
            (this.tail().isEmpty() ? "" : " ") + this.tail().toString().substring(1);
    }

    public static ImmutableList parseIntImmutableList (String s) {
        int openBracket = s.indexOf('(');
        int closeBracket = s.indexOf(')');
        if (openBracket != 0 || closeBracket != s.length() - 1) throw new IllegalArgumentException(s);

        String[] intStrings = s.substring(openBracket + 1, closeBracket).split(" ");

        ImmutableList result = NIL;
        for (int i = intStrings.length - 1; i >= 0; i--) 
            result = result.push(Integer.parseInt(intStrings[i]));

        return result;  
    }

    public ImmutableList powerSet () { return powerSet(nil()); }

    public ImmutableList powerSet (ImmutableList selected) {
        if (this.isEmpty()) return NIL.push(selected); // notice use of NIL as opposed to nil()
                                                       // this is because a powerSet is always a List, not
                                                       // the potential subclass
        return this.tail().powerSet(selected).append(this.tail().powerSet(selected.push(this.head())));
    }

}