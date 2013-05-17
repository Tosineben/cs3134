//Alden Quimby
//adq2101
//March 20, 2011
//COMS 3134

package names;

public class Person implements Comparable <Person> {

    private String firstName;
    private String lastName;
    private int number;

    public Person(String f, String l, int n) {
	firstName = f;
	lastName = l;
	number = n;
    }

    public String getFirst() { return firstName; }
    public String getLast() { return lastName; }
    public int getNumber() { return number; }

    public int compareTo(Person that) {

	if (this.getLast().compareTo(that.getLast()) != 0)
	    return this.getLast().compareTo(that.getLast());

	if (this.getFirst().compareTo(that.getFirst()) != 0)
	    return this.getFirst().compareTo(that.getFirst());

	if (this.getNumber() < that.getNumber()) return -1;
	if (this.getNumber() > that.getNumber()) return 1;

	return 0;

    }

    public String toString() {
	return this.getFirst() + " " + this.getLast() + " " + this.getNumber();
    }

}