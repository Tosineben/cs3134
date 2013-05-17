//Alden Quimby
//adq2101
//COMS 3134
//Feb 05, 2011

package hearts;

public class Hand {
	
	private Array spades, hearts, clubs, diamonds;
	
	public Hand(int size) {
		spades = new Array(size);
		hearts = new Array(size);
		clubs = new Array(size);
		diamonds = new Array(size);
	}

	public void addCard(Card toAdd) {
		if (this.spades.length() + this.hearts.length() + this.clubs.length() + this.diamonds.length() == this.spades.maxsize())
			throw new ArrayIndexOutOfBoundsException("The hand is already full.");
		
		if (toAdd.getSuit().equals("s")) this.spades.insert(toAdd);
		else if (toAdd.getSuit().equals("h")) this.hearts.insert(toAdd);
		else if (toAdd.getSuit().equals("c")) this.clubs.insert(toAdd);
		else this.diamonds.insert(toAdd);
	}

	public String printClubs() {
		String pClubs = "";
		for (int i = 0; i < clubs.length(); i++) pClubs += " " + ((Card)this.clubs.get(i)).getStringValue();
		for (int i = 0; i < 26 - 2*clubs.length(); i++) pClubs += " ";
		return "Clubs:   " + pClubs;
	}
	
	public String printDiamonds() {
		String pDiamonds = "";
		for (int i = 0; i < diamonds.length(); i++)
			pDiamonds += " " + ((Card)this.diamonds.get(i)).getStringValue();
		for (int i = 0; i < 26 - 2*diamonds.length(); i++) pDiamonds += " ";
		return "Diamonds:" + pDiamonds;
	}	
	
	public String printSpades() {
		String pSpades = "";
		for (int i = 0; i < spades.length(); i++) pSpades += " " + ((Card)this.spades.get(i)).getStringValue();
		for (int i = 0; i < 26 - 2*spades.length(); i++) pSpades += " ";
		return "Spades:  " + pSpades;
	}
	
	public String printHearts() {
		String pHearts = "";
		for (int i = 0; i < hearts.length(); i++) pHearts += " " + ((Card)this.hearts.get(i)).getStringValue();
		for (int i = 0; i < 26 - 2*hearts.length(); i++) pHearts += " ";
		return "Hearts:  " + pHearts;
	}	
	
	public boolean hasTwoOfClubs() {
		if (this.toString().substring(1,3).equals("2c")) return true;
		return false;
	}
	
	public String toString() {
		Array hand = new Array(13);
		for (int i = 0; i < this.clubs.length(); i++) hand.add(this.clubs.get(i));
		for (int i = 0; i < this.diamonds.length(); i++) hand.add(this.diamonds.get(i));
		for (int i = 0; i < this.spades.length(); i++) hand.add(this.spades.get(i));		
		for (int i = 0; i < this.hearts.length(); i++) hand.add(this.hearts.get(i));
		return hand.toString();
	}

	public static Hand parseHand(String initHand) {
		if (initHand.length()%3 != 1 || !(initHand.substring(0,1).equals("[")) || !(initHand.substring(initHand.length() - 1).equals("]")))
			throw new IllegalArgumentException("String must be of form: \"[2s 3s ... Kd Ad]\" to parseHand");

		int length = (initHand.length() - 1) / 3;
		String temp = initHand.substring(1);
		Hand returnHand = new Hand(length);
		for (int i = 0; i < length; i++) {
			returnHand.addCard(Card.parseCard(temp.substring(0, 2)));
			if (i < length - 1) temp = temp.substring(3);
		}
		return returnHand;
	}
}