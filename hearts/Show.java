//Alden Quimby
//adq2101
//COMS 3134
//Feb 05, 2011

package hearts;

import java.io.IOException;
import java.util.*;

public class Show {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Hand north = Hand.parseHand(IO.stdin.readLine());
			Hand west = Hand.parseHand(IO.stdin.readLine());
			Hand east = Hand.parseHand(IO.stdin.readLine());
			Hand south = Hand.parseHand(IO.stdin.readLine());

			IO.stdout.println("\t\t\tNorth" + "\n\t\t\t" + north.printClubs()
			+ "\n\t\t\t" + north.printDiamonds() + "\n\t\t\t" + north.printSpades()
			+ "\n\t\t\t" + north.printHearts());	
			
			IO.stdout.println("\nWest\t\t\t\t\t\tEast\n"
			+ west.printClubs() + "\t\t" + east.printClubs() + "\n"
			+ west.printDiamonds() + "\t\t" + east.printDiamonds() + "\n"
			+ west.printSpades() + "\t\t" + east.printSpades() + "\n"
			+ west.printHearts() + "\t\t" + east.printHearts());
			
			IO.stdout.println("\n\t\t\tSouth" + "\n\t\t\t" + south.printClubs()
			+ "\n\t\t\t" + south.printDiamonds() + "\n\t\t\t" + south.printSpades()
			+ "\n\t\t\t" + south.printHearts());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}