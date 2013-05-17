//Alden Quimby
//adq2101
//March 20, 2011
//COMS 3134

package names;

import java.io.*;

public class ImprovedPivotSort {

    public static void main (String[] args) throws IOException {

	if (args.length == 1 && !args[0].equals("-stats"))
	    throw new IllegalArgumentException("Usage -- ImprovedPivotSort [-stats] (\"-stats\" is optional).");

	Array people = new Array(1000);

	String[] temp = new String[3];

	for (int i = 0; i < 1000; i++) {
	    temp = (IO.stdin.readLine()).split(" ");
	    people.add(new Person(temp[0], temp[1], Integer.parseInt(temp[2])));
	}

	people.personQuicksort();

	if (args.length == 0) IO.stdout.println(people.showSortedElements());

	else IO.stdout.println(people.showSortStats("q"));

    }

}