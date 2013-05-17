//Alden Quimby
//adq2101
//March 11, 2011
//COMS 3134

package animals;

import java.io.*;

public class Animal {

    public static void main (String[] args) throws IOException {

	if (args.length != 1) throw new IOException("Command-line argument must be a file name.");

	String[] orders = AnimalTree.getOrders(args[0]);
	AnimalTree knowledge = AnimalTree.read(orders[0], orders[1]);

	AnimalTree memory = knowledge;
	String path = "";

	while (IO.affirmative(IO.prompt("Do you want to play the animal guessing game? "))) {

	    while (!memory.isLeaf()) {
		if (IO.affirmative(IO.prompt(memory.data() + " "))) {
		    memory = memory.left();
		    path += "1";
		}
		else {
		    memory = memory.right();
		    path += "0";
		}
	    }

	    if (IO.affirmative(IO.prompt("I think I know your animal. Is it a " + memory.data() + "? ")))
		IO.stdout.println("\nAwesome, I guessed it!!!\n");

	    else {
		String newAnimal = IO.prompt("I give up. What is your animal? ");
		String oldAnimal = memory.data();

		memory.setData(IO.prompt("Type a question that distinguishes " + oldAnimal + " from " + newAnimal + ":\n"));

		if (IO.affirmative(IO.prompt("What is the answer for " + oldAnimal + "? "))) {
		    memory.setLeft(new AnimalTree(oldAnimal));
		    memory.setRight(new AnimalTree(newAnimal));
		}

		else {
		    memory.setLeft(new AnimalTree(newAnimal));
		    memory.setRight(new AnimalTree(oldAnimal));
		}

		knowledge = knowledge.add(memory, path); //knowledge now contains all the info we have
	    }

	    //reset the tracking variables before starting the loop again:
	    memory = knowledge;
	    path = "";

	} //end outer while loop

	//Once the game is finished, re-write the inputted "knowledge" file:
	FileWriter f = new FileWriter(args[0]);
	f.write(knowledge.write());
	f.close();

    }

}