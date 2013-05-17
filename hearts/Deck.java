//Alden Quimby
//adq2101
//COMS 3134
//Feb 05, 2011

package hearts;

public class Deck {

	private Array deck;
	
	public Deck(int length) {
		this.deck = new Array(length);
	}
	
	//fills deck with 52 cards in order
	public void fill() {
		for (int i = 2; i < 15; i++) this.deck.add(new Card(i, "s"));
		for (int i = 2; i < 15; i++) this.deck.add(new Card(i, "h"));
		for (int i = 2; i < 15; i++) this.deck.add(new Card(i, "c"));
		for (int i = 2; i < 15; i++) this.deck.add(new Card(i, "d"));
	}

	public void addCard(Card toAdd) {
		this.deck.add(toAdd);
	}
	
	public Card getCard(int position) {
		return (Card)this.deck.get(position);
	}
	
	public void shuffle() {
		for (int i = 0; i < 10000; i++)
			this.deck.swap((int)(Math.random()*this.deck.length()), (int)(Math.random()*this.deck.length()));
	}
	
	//prints the deck in the format that Array objects are printed
	//Ex: "[As 2s 3s 4s ... Td Jd Qd Kd]"
	public String toString() {
		return this.deck.toString();
	}

	public static Deck parseDeck(String initDeck) {
		if (initDeck.length()%3 != 1 || !(initDeck.substring(0,1).equals("[")) || !(initDeck.substring(initDeck.length() - 1).equals("]")))
			throw new IllegalArgumentException("String must be of form: \"[2s 3s ... Kd Ad]\" to parseDeck");

		int length = (initDeck.length() - 1) / 3;
		String temp = initDeck.substring(1);
		Deck returnDeck = new Deck(length);
		for (int i = 0; i < length; i++) {
			returnDeck.addCard(Card.parseCard(temp.substring(0, 2)));
			if (i < length - 1) temp = temp.substring(3);
		}
		return returnDeck;
	}

}