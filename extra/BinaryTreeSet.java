//Implementing the Set interface with BinaryTree's


public class BinaryTreeSet implements Set {

private BinaryTree tree;

public int size() {
	if (tree.isEmpty()) return 0;
	return (1 + tree.right().size() + tree.left().size());
}

public boolean isEmpty() {


}

public boolean isMember(Comparable e) {


}

public void include(Comparable e) {


}

public void exclude(Comparable e) {


}

public Set union(Set that) {


}

public Set intersection(Set that) {


}

public Comparable first() {


}

public Comparable next() {


}

public Set copy() {


}

public Set empty() {


}



}