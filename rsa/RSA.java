//Alden Quimby
//adq2101
//April 18, 2011
//COMS 3134

package rsa;

import java.util.*;

public static class RSA {

    //returns an int array containing two random primes between 64 and 128
    public static int[] getPrimes() {

	int[] result = new int[2];
	Random gen = new Random();

	int first = gen.nextInt(63) + 64;
	int second = gen.nextInt(63) + 64;

	result[0] = nextPrime(first);
	result[1] = nextPrime(second);

	return result;

    }

    public static int nextPrime (int n) { while (true) if (prime(++n)) return n; }

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






    //returns [ (b^e) % n ], where all parameters are integers
    public static int expMod(int b, int e, int n) {

	if (n == 0) return exp(b, e);
	if (n == 1) return 0;

	if (e == 0) return 1;

	if (e%2 == 0) return (expMod(b, e/2, n) * expMod(b, e/2, n)) % n;

	return (b * expMod(b, e-1, n)) % n;

    }

    private static int exp(int b, int e) {
	if (e == 0) return 1;
	if (e%2 == 0) return exp(b, e/2) * exp(b, e/2);
	else return b * exp(b, e-1);
    }






    //returns the multiplicative inverse of e in mod m
    public static int inverse(int e, int m) {

	int i;

	for (i = 1; (i%e != 0) ; i += m);
	    
	return i/e;

    }

}