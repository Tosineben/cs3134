//Alden Quimby
//adq2101
//COMS 3134
//Feb 05, 2011

package hearts;
import java.io.IOException;

public class Shuffle {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Deck myDeck = Deck.parseDeck(IO.stdin.readLine());
			myDeck.shuffle();
			IO.stdout.println(myDeck);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

}
