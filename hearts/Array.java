package hearts;

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


    
    public void swap (int i, int j) {

        if (i >= this.length() || j >= this.length() || i < 0 || j < 0) 

            throw new ArrayIndexOutOfBoundsException("Out of Range");

        Object temp = get(i);

        set(i, get(j));

        set(j, temp);

    }
    
    public void merge (int top1, int top2, int out2) { 

        Array temp = new Array(out2 - top1);

        int start = top1;

        int out1 = top2;

        int i, topRemaining, outRemaining;

        while (top1 < out1 && top2 < out2) {

            if (((Comparable) get(top1)).compareTo(get(top2)) < 0)

                temp.add(get(top1++));

            else temp.add(get(top2++)); 

        }

        if (top1 == out1) { topRemaining = top2; outRemaining = out2; }

        else { topRemaining = top1; outRemaining = out1; }

        for (i = topRemaining; i < outRemaining; i++)

            temp.add(get(i));

        for (i = 0; i < temp.length(); i++)

            set(start++, temp.get(i)); 

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



}