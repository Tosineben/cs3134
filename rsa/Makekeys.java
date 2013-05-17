//Alden Quimby
//adq2101
//April 18, 2011
//COMS 3134

package rsa;

public class Makekeys {

    public static void main (String[] args) {

	String fileName = args[0];

	int[] primes = RSA.getPrimes();

	int p = primes[0];
	int q = primes[1];

	int n = p*q;
	int m = (p-1)*(q-1);

	int e = 2;

	while (m % e == 0) e = RSA.nextPrime(e);

	int d = RSA.inverse(e, m);
	
	String temp1 = e + "\n" + n;
	String temp2 = d + "\n" + n;



	//public key should be (e, n)
	//private key should be (d, n)

	//make bob.public contain: "e\nn"

	//generate the files "fileName".public and "fileName".private containing "fileName"’s public and private keys

    }

}