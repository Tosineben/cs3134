//Alden Quimby
//adq2101
//March 20, 2011
//COMS 3134

package names;

import java.util.*;

//note that args[0] must be the first name file, and args[1] must be the last name file
//also note that the files must be formatted: "John\nJames\nMary" etc...
//and lastly, note that both files contain 30 names

public class MakeNames {

    public static void main (String[] args) {

	if (args.length != 2)
	    throw new IllegalArgumentException("Must have two file names as command line arguments.");

	String[] firstN = (IO.readFile(args[0])).split("\n");
	String[] lastN = (IO.readFile(args[1])).split("\n");

	Random gen = new Random();

	for (int i = 1000; i < 2000; i++)
	    IO.stdout.println(firstN[gen.nextInt(30)] + " " + lastN[gen.nextInt(30)] + " " + i);

    }

}