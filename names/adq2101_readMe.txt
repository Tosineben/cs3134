//Alden Quimby
//adq2101
//March 20, 2011
//COMS 3134

1. How to run my program:

Compile the following classes:
Array.java, IO.java, Sort.java, ImprovedPivotSort.java, Person.java,
MakeNames.java

Then to run my program, run the following command:
java names/MakeNames names/firstNames names/lastNames
java names/MakeNames names/firstNames names/lastNames | java names/Sort -h
java names/MakeNames names/firstNames names/lastNames | java names/Sort -q

The first command will print 1000 unique, randomly created Person objects.
The second and third commands will sort a new list and then print it.
Note the the second and third commands won't sort the same list, but will
demonstrate that both sorts work.

Then run the following commands:
java names/MakeNames names/firstNames names/lastNames > names/personData
java names/Sort -h -stats < names/personData
java names/Sort -q -stats < names/personData
java names/ImprovedPivotSort -stats < names/personData

This will print statistics on the different sorts (heapsort, quicksort)

___________________________________________________________________________

2. Description of classes used:

IO.java

Written by professor Pasik.

Person.java

Three instance variables: firstName, lastName, number. The constructor takes
two strings and a number, and assigns them to the instance variables. There
are three accessor methods for the instance variables. The compareTo() method
sorts Person objects by lastName, then firstName, then number. And the
toString() method prints firstName, then lastName, then number.

MakeNames.java

This class reads two file names from the command line - the first of which
should contain a list of first names, and the second of which should contain
a list of last names - and then generates 1000 people, each with a unique
identifier, and a random first name and last name. The people are all printed
to stdout. Note that an exception is thrown if two file names aren't given.

Sort.java

This class takes one or two command line arguments, which specify how to sort,
and whether or not to print sorting statistics. If the command line args are
not formatted correctly, an exception is thrown. Then 1000 lines are read
from stdin, which should be in the format: "John Doe 1392". Note this is
the same format that MakeNames.java prints to stdout. 1000 Person objects
are created by reading from stdin, and put in an Array called "people". If
the first command line argument was "-h" then "people" gets heapsorted. If
the first command line argument was "-q" then "people" gets quicksorted.
If there wasn't a second command line argument, then the sorted Array
"people" is printed to stdout using the showSortedElements() method of the
Array class. Otherwise, the statistics of the respective sort are printed
using the showSortStats(String type) method of the Array class.

ImprovedPivotSort.java (EXTRA CREDIT)

This class is very similar to Sort, but only allows for quicksorting (no
heapsorting). The difference in the quicksort is that the Array "people" is
quicksorted using the method personQuicksort() of the Array class, instead
of the method quicksort(). personQuicksort() is slightly different and picks
a better pivot, which reduces the amount of comparisons made when sorting.
Note that you do not need to specify how to sort when running this program,
but you do need to specify "-stats" if you want statistics printed.

Array.java

When looking at this code, please skip immediately to the section marked:
************************** SORTING METHODS ******************************

Then skip down to the section marked:
---------------------------- QUICKSORT ----------------------------------

Everything before this was written by professor Pasik, and doesn't need to
be looked at.

Quicksort:
I added two ints (qComps, qSwaps) to professor Pasik's quicksort method, to
count the number of comparisons, and the number of swaps made while 
quicksorting an Array. qSwaps is very easy to count. The only time any
swaps are made is in the split() method, where this.swap(i, j) is called.
This counts as 3 swaps, as professor Pasik explained in the assignment.
The qComps variable, on the other hand, is incremented quite often. In the
split() method, qComps is incremented in both inner while loops, because the
pivot is being compared to another Object in the Array. In the pivotIndex()
method, qComps is incremented before each if statement, because a comparison
is made to check the if statement. Note that neither qComps or qSwaps is 
incremented in the methods quicksort() or quicksort(int top, int out), because
no comparisons or swaps are made. Also note that both variables are set to 0
when quicksort() is called.

Improved Quicksort: (EXTRA CREDIT)
The only difference here is the pivotIndex() method. The personQuicksort()
methods are identical to the quicksort() methods, except they call
personPivotIndex() when choosing a pivot index. personPivotIndex() chooses
the median of three Person objects, as opposed to the larger of two Person
objects (which is how pivotIndex() works). The first element is compared to
the middle (and qComps is incremented). If there are only two Person objects,
then the index of the larger object is returned. Otherwise, the middle element
is compared to the last element (and qComps is incremented). Then the index
of the median of those three objects is return appropriately. It is crucial
to note here that each Person object has a UNIQUE identifier, so the
compareTo() method will never return 0 (there will never be two of the same
element). Because of this, we do not need to consider the possibility that
the first, middle, and last elements might be the same. Further, this method
will never return Array.FAIL, so the personQuicksort(int top, int out) method
does not need to check for that. And lastly, note that there are two more
times in the personPivotIndex() method where qComps is incremented. They
are both before comparing the first and last objects, and only happen if the
middle object is not the median object. Also, this improved quicksort still 
uses the split() method.

Heapsort:
Written by professor Pasik. hSwaps counts the swaps, and hComps counts the
comparisons. In the heapsort() method, both counter variables are set to 0,
and then hSwaps is incremented by 3 when this.swap(0, i) is called. In the
makeHeap() method, hSwaps is incremented immediately following every call to
the set() method. hComps is incremented after "larger" is chosen, and before
"object" is compared to "get(larger)".

showSortStats(String type):
This method throws an exception if the parameter "type" is not "h" (for 
heapsort) or "q" (for quicksort). Then depending on the parameter, the
statistics counters hComps, hSwaps, qComps, and qSwaps are printed
appropriately.

showSortedElements():
This is similar to a toString() method. Each object in the Array is stored
in a String and separated by a newline character. The String is returned.





That's everything! Please note that the improved quicksort does actually
perform that much better. Picking the median of three objects only
saves 1000 or so out of 14000 comparisons, and actually increases the number
of swaps made by a few. However, I also ran the two quicksort methods on a 
list of 10,000 Person objects a few times, and found the following:

java names/Sort -q -stats < personData
Element Comps: 184822, Swaps: 93555

java names/ImprovedPivotSort -stats < personData
Element Comps: 161757, Swaps: 94865

Therefore, it seems that by picking the median of three objects, we get roughly
12.5% fewer comparisons, and roughly 1.4% more swaps. So, this choice of pivot
seems better to me.

