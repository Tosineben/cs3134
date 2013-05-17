//Alden Quimby
//adq2101
//COMS 3134
//Feb 05, 2011

package hearts;

public class NewDeck {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Deck myDeck = new Deck(52);
		myDeck.fill();
		IO.stdout.println(myDeck);
	}

}