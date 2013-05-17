//Alden Quimby
//adq2101
//March 20, 2011
//COMS 3134

package names;

import java.io.*;

public class Sort {

    public static void main (String[] args) throws IOException {

	if (!(args[0].equals("-h") || args[0].equals("-q")) || (args.length == 2 && !args[1].equals("-stats")))
	    throw new IllegalArgumentException("Usage -- Sort [-h | -q] [-stats] (\"-stats\" is optional).");

	Array people = new Array(1000); 

	String[] temp = new String[3];

	for (int i = 0; i < 1000; i++) {
	    temp = (IO.stdin.readLine()).split(" ");
	    people.add(new Person(temp[0], temp[1], Integer.parseInt(temp[2])));
	}

	if (args[0].equals("-h")) people.heapsort();

	else people.quicksort();

	if (args.length == 1) IO.stdout.println(people.showSortedElements());

	else IO.stdout.println(people.showSortStats(args[0].substring(1)));

    }

}