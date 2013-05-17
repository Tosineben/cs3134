//Alden Quimby
//adq2101
//COMS 3134
//Feb 05, 2011

package hearts;
import java.io.IOException;

public class Deal {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Deck myDeck = Deck.parseDeck(IO.stdin.readLine());
			Hand north = new Hand(13);
			Hand west = new Hand(13);
			Hand east = new Hand(13);
			Hand south = new Hand(13);
			for (int i = 0; i < 13; i++) {
				north.addCard(myDeck.getCard(i));
				west.addCard(myDeck.getCard(i+13));
				east.addCard(myDeck.getCard(i+26));
				south.addCard(myDeck.getCard(i+39));
			}
			IO.stdout.println(north);
			IO.stdout.println(west);
			IO.stdout.println(east);
			IO.stdout.println(south);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
