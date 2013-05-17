
public class PriorityQueueBinaryTree implements Queue {

    private BinaryTree tree;

    private int count;

    public PriorityQueue () {
	tree = BinaryTree.NIL;
	count = 0;
    }

    public boolean isEmpty() { return count == 0; }

    public boolean isFull() { return false; }

    public Object front() { return this.isEmpty() ? null : this.tree.data(); }

    public boolean enter (Object o) {
	Comparable d = (Comparable) o;
	tree = tree.insertAsDList(d);
	count++;
	return true;
    }

    public Object leave() {
	if (this.isEmpty()) return null;

	Object result = this.tree.data();

	if (count == 1) {
	    tree = BinaryTree.NIL;
	    count--;
	    return result;
	}

	BinaryTree end = this.tree.left();
	BinaryTree newTree = this.tree.right();

	newTree.setLeft(end);
	end.setRight(newTree);
	this.tree = newTree;
	count--;

	return result;

    }


