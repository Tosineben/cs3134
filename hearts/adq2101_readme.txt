// Alden Quimby
// adq2101
// Feb 12, 2011
// COMS 3134

1. How to run my program:
First compile all of the classes, then run the following commands:

java hearts/NewDeck | java hearts/Shuffle | java hearts/Deal | tee hands | java hearts/Show

This will print four hands North, West, East and South, and save a copy of the
four hands in hands. Then run the command:

java hearts/Leader < hands

This will print the name of the hand (North, West, East, or South) that contains
the two of clubs, and therefore is supposed to go first in the game of hearts.

Using the classes NewDeck, Shuffle, Deal, Show and Leader, you can do pretty much do
everything professor Pasik asked of us in the assignment.



2. Explanation of how my programs behave:
I used 10 classes to complete this assignment. They are listed below with
a brief description of what each class does and what methods they use.

IO - This is professor Pasik's class, which replaces Standard In, Standard Out
and Standard Error, and adds a few methods.

Array - Same deal here as IO. This is one of professor Pasik's classes, which
is pretty similar to ArrayList.

NewDeck (main) - This class creates a Deck object with the parameter 52 (for 52 cards),
fills the deck in order by calling myDeck.fill(), and then prints
the deck to stdout using the toString() method of my Deck class.

Shuffle (main) - This class reads a line from stdin, and attempts to convert that
String into a Deck by calling the parseDeck method of my Deck class. The 
Deck object is then shuffled, and printed back to stdout. Note that a try 
catch block surrounds the three lines of this class (and the bulk of every
subsequent class with a main method, so I'm not going to repeat myself for each
of the following classes) because IO.stdin.readLine() could throw an IOException
if the class is not utilized properly.

Deal (main) - This class reads a String from stdin and parses the String into a Deck
object. Then four Hand objects of size 13 are initialized and filled with the
cards of the Deck object. Each Hand object is then printed to stdout. Note that
the format for printing a Hand and a Deck object using the respective toString()
methods is exactly the same: [Card1 Card2 .... Cardn]

Leader (main) - This class reads four lines from stdin, which should be the String 
representation of four Hand objects. After parsing the strings into four Hand
objects, the method hasTwoOfClubs() is called for each Hand object. Assuming the
deck was correct (so that the two of clubs is in one of the hands), one of these
calls will return true and the name of the hand containing 2c will be printed.

Show (main) - This class reads four lines from stdin and tries to parse them into Hand
objects. Each Hand object - North, West, East, South - is then printed to stdout
in the format specified by professor Pasik by calling the printClubs(),
printDiamonds(), printSpades() and printHearts() methods of the Hand class, and using
a bunch of newline and tab characters.

Card - Objects of this class have two instance variables, a String suit, and an int value.
The suits are s, h, c, and d, and the values run from 2 to 14. The constructor will throw an
IllegalArgumentException if the suit is not s, h, c, or d, or if the rank isn't between 2
and 14 inclusive. There are a few accessor methods, a toString() which prints cards
in the form "As" for ace of spades and "7c" for seven of clubs, and a parseCard()
which takes a string of the form of the toString() method and returns a Card object. Note
that the parseCard method throws and IllegalArgumentException if the inputed String is not
of length 2. And lastly, the class implements the Comparable interface, and ranks cards
by their value (suit does not matter in the compareTo() method).

Deck - This class has an Array instance variable called deck, which is meant to hold an
inputed number of Card objects. The fill() method adds 52 cards to the deck Array in
order from 2s to Ad. shuffle() randomly swaps two of the Card objects 10,000 times to shuffle
the deck. The toString() method calls the toString() method of the
Array class, which professor Pasik wrote. parseDeck() throws an IllegalArgumentException if
the length of the inputted String mod 3 is not 1 - for all possible decks in the correct
format this would not be true and an exception would not be thrown - or if the first and last
characters are not [ and ] respectively. 

Hand - I created this class to make printing the hands easier for the Show class. Note that
I could have done this work in the Deck class, but thought it would be easier and more clear
to create a Hand class. So, this class has 4 Array object instance variables: spades, hearts,
clubs and diamonds. These 4 Array objects hold the Card objects of their respective suits.
To add a card to a Hand object, the addCard() method calls the insert() method of the Array
class, so that the Hand object holds a sorted hand of Card objects, and puts the inputted Card
object into one of the four instance variables depending on the suit of the Card. The four print
methods are used by the Show class to print each of the four hands dealt in the game of hearts.
The methods return a String with all of the Card objects of a particular suit, and add space
characters on the end of the String so that each String has a total of 26 characters. The
toString() and parseHand() are very similar to the corresponding methods of the Deck class.
