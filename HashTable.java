//Written by Professor Pasik

public class HashTable {

    private List[] theTable;

    private static boolean prime (int n) {
        int sqrtN = (int) Math.sqrt(n);
        boolean[] sieve = new boolean[sqrtN];
        int i;

        for (i = 2; i < sqrtN; i++) sieve[i] = true;

        for (int p = 2; p < sqrtN; p++)
            if (sieve[p]) 
                for (i = 2 * p; i < sqrtN; i += p) sieve[i] = false;

        for (i = 2; i < sqrtN; i++)
            if (sieve[i] && n % i == 0) return false;

        return true;
    }

    private static int nextPrime (int n) { while (true) if (prime(++n)) return n; }

    public HashTable (int estimatedElements) {
        int size = nextPrime(2 * estimatedElements);
        theTable = new List[size];
        for (int i = 0; i < size; i++) theTable[i] = List.NIL;
    }

    public int size () { return theTable.length; }

    public void insert (HashData d) {
        int i = d.hashCode();
        List L = theTable[i].find(d);
        if (!L.isEmpty()) ((HashData)L.head()).incr();
        else theTable[i] = theTable[i].push(d);
    }

    public HashData find (HashData d) {
        int i = d.hashCode();
        List L = theTable[i].find(d);
        return L.isEmpty() ? null : (HashData)L.head();
    }

    public String toString () {
        String result = "";
        for (int i = 0; i < theTable.length; i++) 
            if (!theTable[i].isEmpty()) {
                result += i + ": " + theTable[i].toString() + "\n";
            }

        return result;
    }

    public void writeStats () {
        int longest = 0;
        int collisions = 0;
        int elements = 0;
        int empty = 0;

        for (int i = 0; i < theTable.length; i++)
            if (!theTable[i].isEmpty()) {
                int len = theTable[i].length();
                longest = len > longest ? len : longest;
                collisions += len - 1;
                elements += len;
            } else empty++;

        IO.stdout.println((100*empty/theTable.length)+"% empty, "
                          +elements+" elements, "
                          +collisions+" collisions, "
                          +longest+" longest, "
                          +((float)elements / (float)(theTable.length - empty))+" average.");
    }

}