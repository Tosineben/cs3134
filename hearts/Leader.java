//Alden Quimby
//adq2101
//COMS 3134
//Feb 05, 2011

package hearts;

import java.io.IOException;

public class Leader {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Hand north = Hand.parseHand(IO.stdin.readLine());
			Hand west = Hand.parseHand(IO.stdin.readLine());
			Hand east = Hand.parseHand(IO.stdin.readLine());
			Hand south = Hand.parseHand(IO.stdin.readLine());

			if (north.hasTwoOfClubs()) IO.stdout.println("North");
			else if (east.hasTwoOfClubs()) IO.stdout.println("East");
			else if (south.hasTwoOfClubs()) IO.stdout.println("South");
			else if (west.hasTwoOfClubs()) IO.stdout.println("West");
			else IO.stdout.println("You must be using an incorrect deck.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
