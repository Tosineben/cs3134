BinaryTree.java

data, left, right

Comparable data()
BinaryTree left()
BinaryTree right()

setData(Comparable d)
setLeft(BinaryTree t)
setRight(BinaryTree t)

boolean isEmpty()

BinaryTree insert(BinaryTree newNode)
BinaryTree insertDestructive(BinaryTree newNode)
BinaryTree insertAsDList (Comparable d)
BinaryTree appendAsDList (BinaryTree that)
BinaryTree tree2DList()

String toString()
String toStringAsDList()
_____________________________________________________

AnimalTree.java

boolean isLeaf()

static String[] getOrders(String file)
static AnimalTree read(String p, String i)
static String write(AnimalTree t)
_____________________________________________________

IO.java

stdin, stdout, stderr

//returns true if "yn" starts with a y or Y
static boolean affirmative (String yn)

//reads an entire file into a single string
static String readFile(String name)

//prints out "s", then returns prompted input
//as a String via stdin
static String prompt(String s)
_____________________________________________________



/*
    //returns the total number of elements in the tree
    public int count() {

	if (this.isEmpty()) return 0;
	if (this.isLeaf()) return 1;
	if (this.left().isEmpty()) return this.rightCount() + 1;
	if (this.right().isEmpty()) return this.leftCount() + 1;

	return this.rightCount() + this.leftCount() + 2;
    }

    //returns the number of elements in this.left()
    public int leftCount() {
	if (this.left().isEmpty()) return 0;
	return this.left().count();
    }

    //returns the number of elements in this.right()
    public int rightCount() {
	if (this.right().isEmpty()) return 0;
	return this.right().count();
    }
*/