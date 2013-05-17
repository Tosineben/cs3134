//Alden Quimby
//adq2101
//March 20, 2011
//COMS 3134
//Written by Alexander Pasik

package names;

public class Array {

    private Object[] values;
    private int count;
    private boolean sorted;
    public static final int FAIL = -1;


    public Array (int max) {

        this.values = new Object[max];
        this.count = 0;
        this.sorted = true;

    }


    public int maxsize () { return this.values.length; }


    public Object get (int i) {

        if (i < 0 || i >= this.count) throw new ArrayIndexOutOfBoundsException("Out of Range");

        return this.values[i];

    }


    public void set (int i, Object v) {

        if (i < 0 || i >= this.count) throw new ArrayIndexOutOfBoundsException("Out of Range");

        this.values[i] = v;
        this.sorted = false;

    }


    public void add (Object d) {

        if (this.isFull()) throw new ArrayIndexOutOfBoundsException("Array Full");

        set(this.count++, d);

    }


    public boolean isEmpty () { return this.count == 0; }

    public boolean isFull () { return this.count == this.values.length; }

    public boolean isSorted () { return this.sorted; }

    public void declareSorted () { this.sorted = true; }

    public int length () { return count; }

    public void clear () { this.count = 0; this.declareSorted(); }


    public String toString () {

        String result = "[";

        for (int i = 0; i < this.length(); i++)
            result += get(i) + (i != this.length() - 1 ? " " : "");

        return result + "]";

    }


    public int find (Object d) {

        return this.isSorted() ?
            this.find((Comparable) d, 0, this.length()) :
            this.find(d, 0, this.length());

    }


    private int find (Object d, int top, int out) {

        for (int i = top; i < out; i++) if (d.equals(get(i))) return i;

        return out;

    }


    private int find (Comparable d, int top, int out) {

        if (top == out) return top;

        int mid = (top + out) / 2;
        if (d.compareTo(get(mid)) == 0) return mid;

        if (d.compareTo(get(mid)) > 0) return this.find(d, mid + 1, out);
        else return this.find(d, top, mid);

    }


    private void shiftDown (int p) {

        this.count++;
        for (int i = this.length() - 2; i >= p; i--) set(i+1, get(i));

    }


    private void shiftUp (int p) {

        for (int i = p; i < this.length() - 1; i++) set(i, get(i+1));
        this.count--;

    }


    public void insert (Comparable d) {

        if (this.isFull()) throw new ArrayIndexOutOfBoundsException("Array Full");
        if (!this.isSorted()) throw new RuntimeException("Cannot insert into unsorted array");

        int p = this.find(d);
        this.shiftDown(p);
        set(p, d);

        this.declareSorted();

    }


    public void delete (Object d) {

        if (this.isEmpty()) throw new ArrayIndexOutOfBoundsException("Array Empty");

        int i = this.find(d);

        if (i < this.length() && d.equals(get(i))) {
            if (this.isSorted()) { this.shiftUp(i); this.declareSorted(); }
            else { set(i, get(this.count - 1)); this.count--; }
        }

    }


//************************************** SORTING METHODS ****************************************


//----------------------------------------- NAIVESORT -------------------------------------------


    public void swap (int i, int j) {

        if (i >= this.length() || j >= this.length() || i < 0 || j < 0) 
            throw new ArrayIndexOutOfBoundsException("Out of Range");

        Object temp = get(i);
        set(i, get(j));
        set(j, temp);

    }


    private int findSmallest (int smallestSoFar, int top, int out)  { 

        if (top == out) return smallestSoFar;

        return ((Comparable) get(smallestSoFar)).compareTo(get(top)) < 0 ?
            this.findSmallest(smallestSoFar, top + 1, out) :
            this.findSmallest(top, top + 1, out);

    }


    private void naivesort (int top, int out) {

        if (top < out - 1) {
            int p = this.findSmallest(top, top + 1, out);
            this.swap(top, p);
            this.naivesort(top + 1, out);
        }

    }


    public void naivesort () { this.naivesort(0, this.length()); this.declareSorted(); }


//----------------------------------------- MERGESORT ------------------------------------------


    public void merge (int top1, int top2, int out2) { 

        Array temp = new Array(out2 - top1);

        int start = top1;
        int out1 = top2;

        int i, topRemaining, outRemaining;

        while (top1 < out1 && top2 < out2) {
            if (((Comparable) get(top1)).compareTo(get(top2)) < 0) temp.add(get(top1++));
            else temp.add(get(top2++)); 
        }

        if (top1 == out1) { topRemaining = top2; outRemaining = out2; }
        else { topRemaining = top1; outRemaining = out1; }

        for (i = topRemaining; i < outRemaining; i++) temp.add(get(i));

        for (i = 0; i < temp.length(); i++) set(start++, temp.get(i)); 

    }
   

    private void mergesort (int top, int out) {

        if (top < out - 1) {
            int mid = (top + out) / 2;

            this.mergesort(top, mid);
            this.mergesort(mid, out);
            this.merge(top, mid, out);
        }

    }

                
    public void mergesort () { this.mergesort(0, this.length()); this.declareSorted(); }


//---------------------------------------- QUICKSORT ------------------------------------------


    private int qComps = 0, qSwaps = 0;


    private int pivotIndex (int top, int out) {

        Comparable pivot = (Comparable) get(top);

        for (int i = top + 1; i < out; i++) {

	    qComps++;
            if (pivot.compareTo(get(i)) < 0) return i;

	    qComps++;
            if (pivot.compareTo(get(i)) > 0) return top;

        }

        return Array.FAIL;

    }


    private int split (int i, int j, Comparable pivot) {

        while (i < j) {

            while (i < j && pivot.compareTo(get(i)) > 0) { i++; qComps++; }

            while (i < j && pivot.compareTo(get(j)) <= 0) { j--; qComps++; }

            if (i < j) { this.swap(i, j); qSwaps += 3; }

        }

        return i;

    }


    private void quicksort (int top, int out) {

        if (top < out - 1) {
            int i = this.pivotIndex(top, out);

            if (i != Array.FAIL) {
                int mid = this.split(top, out - 1, (Comparable) get(i));
                this.quicksort(top, mid);
                this.quicksort(mid, out);
            }
        }
    }


    public void quicksort () {
	qComps = 0; qSwaps = 0;
	this.quicksort(0, length());
	this.sorted = true;
    }


//------------------------------------- IMPROVED QUICKSORT --------------------------------------


    private int personPivotIndex (int top, int out) {

	Comparable first = (Comparable) get(top);
	Comparable middle = (Comparable) get((top+out)/2);

	int comp1 = first.compareTo(middle);
	qComps++;

	if (top == out - 2) {
	    if (comp1 < 0) return top+1;
	    else return top;
	}

	Comparable last = (Comparable) get(out-1);

	int comp2 = middle.compareTo(last);
	qComps++;

	//first element comes before middle element
	if (comp1 < 0) {

	    //middle element comes before last element, median = middle element
	    if (comp2 < 0) return (top+out)/2;

	    else {
		qComps++;
		if (first.compareTo(last) > 0) return top;
		else return out-1;
	    }

	}

	//first element comes after middle element
	else {

	    //middle element comes after last element, median = middle element
	    if (comp2 > 0) return (top+out)/2;

	    else {
		qComps++;
		if (first.compareTo(last) < 0) return top;
		else return out-1;
	    }

	}

    }


    private void personQuicksort (int top, int out) {
        if (top < out - 1) {
            int i = this.personPivotIndex(top, out);
            int mid = this.split(top, out - 1, (Comparable) get(i));
            this.personQuicksort(top, mid);
            this.personQuicksort(mid, out);
        }
    }


    public void personQuicksort () {
	qComps = 0; qSwaps = 0;
	this.personQuicksort(0, length());
	this.sorted = true;
    }


//----------------------------------------- HEAPSORT ------------------------------------------


    private static int left (int i) { return 2 * i + 1; }

    private static int right (int i) { return 2 * i + 2; }

    private int hComps = 0, hSwaps = 0;


    private void makeHeap (int top, int out) {

        Comparable object = (Comparable) get(top);

        int larger;
        int p = top;

        while (p < out / 2) {

            larger = right(p) < out && ((Comparable) get(right(p))).compareTo(get(left(p))) > 0 ?
                right(p) : left(p);

	    hComps++;

	    hComps++;

            if (object.compareTo(get(larger)) > 0) { set(p, object); hSwaps++; return; }

	    else { set(p, get(larger)); hSwaps++; p = larger; }

        }

        set(p, object); hSwaps++;

    }


    public void heapsort () {

	hComps = 0; hSwaps = 0;

        int i;

        for (i = (this.length() - 1) / 2; i >= 0; i--) this.makeHeap(i, this.length());

        for (i = this.length() - 1; i > 0; i--) {

            this.swap(0,i); hSwaps += 3;

            this.makeHeap(0,i);

        }

        this.sorted = true;

    }


//************************************* END SORTING METHODS *************************************
   

    public String showSortStats(String type) {

	if (!(type.equals("h") || type.equals("q")))
	    throw new IllegalArgumentException("Must be heapsort or quicksort stats.");

	if (type.equals("h")) return ("Element Comps: " + hComps + ", Swaps: " + hSwaps);

	else return ("Element Comps: " + qComps + ", Swaps: " + qSwaps);

    }


    public String showSortedElements() {

	String result = "";

	for (int i = 0; i < count; i++)
	    result += values[i].toString() + ((i != count - 1) ? "\n" : "");

	return result;

    }

}

