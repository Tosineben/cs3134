//Alden Quimby
//adq2101
//COMS 3134
//Feb 05, 2011

package hearts;

public class Card implements Comparable <Card>{

	//suits will be denoted as s=spades, h=hearts, c=clubs, d=diamonds
	//card values will be: 2,3,4,5,6,7,8,9,T,J,Q,K,A
	//note that suits are lower case, values are upper case
	private String suit;
	private int value;

	public Card(int rank, String initSuit) {
		if (!(initSuit.equals("c") || initSuit.equals("s") || initSuit.equals("d") || initSuit.equals("h")))
			throw new IllegalArgumentException("Card suits must be s, h, d, or c.");
		if (rank < 2 || rank > 14)
			throw new IllegalArgumentException("Card ranks must be between 2 and 14 inclusive.");
		value = rank;
		suit = initSuit;
	}
	
	public int getValue() {
		return this.value;
	}
	
	//returns the Card rank in String form
	public String getStringValue() {
		String valueOut;
		if (this.value == 10) valueOut = "T";
		else if (this.value == 11) valueOut = "J";
		else if (this.value == 12) valueOut = "Q";
		else if (this.value == 13) valueOut = "K";
		else if (this.value == 14) valueOut = "A";
		else valueOut = String.valueOf(this.value);
		return valueOut;
	}
	
	public String getSuit() {
		return this.suit;
	}
	
	public String toString() {
		return (this.getStringValue() + this.getSuit());
	}
	
	public static Card parseCard(String initCard) {
		if (initCard.length() != 2) throw new IllegalArgumentException("String must be of form: \"4s\" to parseCard");

		String vIn = initCard.substring(0,1);
		int valueIn;
		if (vIn.equals("T")) valueIn = 10;
		else if (vIn.equals("J")) valueIn = 11;
		else if (vIn.equals("Q")) valueIn = 12;
		else if (vIn.equals("K")) valueIn = 13;
		else if (vIn.equals("A")) valueIn = 14;
		else valueIn = Integer.parseInt(vIn);

		return new Card(valueIn, initCard.substring(1));
	}
	
	//sorts cards by their value, ignoring suit
	public int compareTo(Card other) {
		if (this.getValue() > other.getValue()) return 1;
		else if (this.getValue() < other.getValue()) return -1;
		return 0;
	}
}