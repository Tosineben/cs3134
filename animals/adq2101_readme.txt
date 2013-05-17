//Alden Quimby
//adq2101
//March 11, 2011
//COMS 3134

1. How to run my program:

I used three classes for this program, IO.java, AnimalTree.java, and Animal.java.
Compile all three of these classes. Do not compile the file "knowledge". This is a file
used for the game. Then to launch the program, run the command:

java animals/Animal animals/knowledge

From then on, just follow the game, answering the questions asked. Note that almost everything
is a yes/no question, except when it asks you enter a new question or new animal.
_______________________________________________________________________________________________

2. Description of classes used:

The first class is IO.java, which professor Pasik wrote.

AnimalTree.java:
This class is very similar to professor Pasik's BinaryTree class. However, AnimalTree has
five additional methods that BinaryTree does not have.

getOrders() takes a String that should be the name of a file, and returns an array of
Strings of length 2, that contains the preOrder String representation of an AnimalTree,
and the inOrder String representation of an AnimalTree. This method throws an exception if
the file is not formatted correctly. The file should be formatted: preOrder + "\n\n" +
inOrder + "\n". This is how I chose to represent an AnimalTree in String format.

read() takes two Strings, the preOrder and inOrder representations of an AnimalTree,
and returns the AnimalTree defined by those Strings. This method works recursively. The base
case is if the preOrder String contains only one line. This means there is only one element
in the tree. The recursive calls are then made to create left() and right(), by appropriately
taking pieces from the preOrder and inOrder Strings.

write() uses the toString() method to write the AnimalTree "this" in the appropriate String
format, which is: preOrder + "\n\n" + inOrder + "\n".

toString() is very similar to the elementString() method written by professor Pasik in the
BinaryTree class. The only difference is that elements are separated by new line characters,
instead of spaces. This is what I labeled these orders "newPreOrder" and "newInOrder".

add() takes an AnimalTree "t", and a String called "path". This method will add the
tree "t" to the tree "this" at the node specified by the String "path". The method works 
recursively. There are two bases cases. The first is if "path" is empty. If this is the case,
then "t" is returned, because this means the tree "t" should be added at the root node. If
"path" has length 1, then "t" is set to be either the left() or right() tree of "this".
A "1" represents left, and a "0" represents right. So, for example, if path was "0110", that
would mean: go right, then go left, then go left, then set the right() tree to "t".
The recursive calls do just that.


Animal.java:
This contains the main method, and is the driver class for the program. The command line
argument must be the name of a file that holds the correctly formatted String representation
of an AnimalTree. The AnimalTree "knowledge" is created from this file. "knowledge" is the
tree that will be traversed throughout the game. Another AnimalTree, called "memory", is used
while traversing the tree "knowledge". And "path" is used to record the path taken by the
user so that a new question can be added when needed. The outer while loop runs through the
whole game once. There are three parts within to the outer while loop.

Part 1
While "memory" is not a leaf, ask the question at memory.data(). If the answer is yes,
move left and add a "1" to path. Otherwise move right and add a "0" to path. Once this while
loop is finished, we know we are at a leaf, which means we're at an animal, not a question.

Part 2
Guess the animal at the leaf node. If it's correct, the game is complete and returns to the
outer while loop. If it is incorrect, "memory" becomes a three node AnimalTree, whose root
is a new question entered by the user, and whose left and right are the original guess, and
the new animal entered by the user (depending on where the user says they should go).
Then "memory" is added to the tree "knowledge" with the add() method described above. This
means "knowledge" is fully updated and contains everything we know at this point.
And then before resetting the game, memory is reset to be knowledge, and path is emptied.

Once the user no longer wants to play the game, the fully updated tree "knowledge" is written
to the original file given on the command line using the write() method described earlier.



**Also note that the file "knowledge" holds the preOrder and inOrder String representations
of the complete AnimalTree with everything learned by the game at any point. This is args[0].
