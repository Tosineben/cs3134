//Alden Quimby
//adq2101
//March 11, 2011
//COMS 3134

package animals;

public class AnimalTree {

    protected String data;
    protected AnimalTree left;
    protected AnimalTree right;

    public final static AnimalTree NIL = new AnimalTree();

    protected AnimalTree() { }

    public AnimalTree(String s) {
	this.data = s;
	this.left = nil();
	this.right = nil();
    }

    public AnimalTree(String s, AnimalTree l, AnimalTree r) {
	this.data = s;
	this.left = l;
	this.right = r;
    }

    public AnimalTree nil() { return NIL; }
    public String data () { return this.data; }
    public AnimalTree left () { return this.left; }
    public AnimalTree right () { return this.right; }
    public void setData (String d) { this.data = d; }
    public void setLeft (AnimalTree t) { this.left = t; }
    public void setRight (AnimalTree t) { this.right = t; }
    public boolean isEmpty () { return this == nil(); }
    public boolean isLeaf() { return (this.left().isEmpty() && this.right().isEmpty() && !this.isEmpty()); }


    //note that the readFile() method of IO.java will throw an exception if "file" is not a file name
    public static String[] getOrders(String file) {

	String total = IO.readFile(file);

	if (total.indexOf("\n\n") < 0 || total.lastIndexOf("\n") != total.length() - 1)
	    throw new IllegalArgumentException("The file " + file + " is formatted incorrectly.");

	String[] result = new String[2];
	result[0] = total.substring(0, total.indexOf("\n\n") + 1);
	result[1] = total.substring(total.indexOf("\n\n") + 2);

	return result;
    }


    //"p" is the preOrder String, and "i" is the inOrder String
    //returns the AnimalTree specified by these two Strings
    public static AnimalTree read(String p, String i) {

	if (p.indexOf("\n") == p.length() - 1) return new AnimalTree(p.substring(0, p.length()-1));

	String data = p.substring(0, p.indexOf("\n"));

	String leftI = i.substring(0, i.indexOf(data));
	String leftP = p.substring(data.length() + 1, data.length() + 1 + leftI.length());

	String rightI = i.substring(i.indexOf(data) + data.length() + 1);
	String rightP = p.substring(data.length() + leftI.length() + 1);

	return new AnimalTree(data, read(leftP, leftI), read(rightP, rightI));
    }


    //returns a String in the format: preOrder "\n\n" inOrder "\n"
    public String write() {
	return this.toString("newPreOrder") + "\n\n" + this.toString("newInOrder") + "\n";
    }


    //adds "t" to "this" in the location specified by "path"
    public AnimalTree add(AnimalTree t, String path) {
	
	if (path.length() == 0) return t;

	if (path.length() == 1) {
	    if (path.equals("1")) this.setLeft(t);
	    else this.setRight(t);
	    return this;
	}

	if (path.substring(0,1).equals("1")) this.setLeft(this.left().add(t, path.substring(1)));
	else this.setRight(this.right().add(t, path.substring(1)));

	return this;
    }


    public String toString(String order) {

	if (this.isEmpty()) return "";

	String dataStr = this.data();
	String leftStr = this.left().toString(order);
	String rightStr = this.right().toString(order);

	if (order.equals("newPreOrder"))
	    return dataStr + (leftStr != "" ? "\n" : "") + leftStr + (rightStr != "" ? "\n" : "") + rightStr;

	if (order.equals("newInOrder"))
	    return leftStr + (leftStr != "" ? "\n" : "") + dataStr + (rightStr != "" ? "\n" : "") + rightStr;

	throw new IllegalArgumentException("Bad Binary Tree Ordering: " + order);
    }

}
