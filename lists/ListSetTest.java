//Alden Quimby
//adq2101
//02.25.11
//COMS 3134

package lists;

public class ListSetTest {

    public static void main (String[] args) {

	ImmutableList iList = ImmutableList.NIL;
	for (int i = args.length - 1; i >= 0; i--)
	    iList = iList.push(Integer.parseInt(args[i]));

	iList = iList.purge();
	iList = iList.sort();
	System.out.println("ImmutableList is " + iList);

	ListSet sList = new ListSet();
	for (int i = args.length - 1; i >= 0; i--)
	    sList.include(Integer.parseInt(args[i]));

	System.out.println("ListSet is " + sList);

    }

}