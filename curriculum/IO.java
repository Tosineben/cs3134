package curriculum;

import java.io.*;



/**

	Utility class for basic IO functions.

	@author Alexander J. Pasik, Ph.D.

*/

public final class IO {



    /**

        A BufferedReader associated with the stdin stream

    */

    public static BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));



    /**

        A PrintStream associated with stdout

    */

    public static PrintStream stdout = System.out;



    /**

        A PrintStream associated with stderr

    */

    public static PrintStream stderr = System.err;



    private IO () { }



    /**

        Tests if a string value begins with 'y' or 'Y'

        @param yn the string

        @return true if string represents "yes"

    */

    public static boolean affirmative (String yn) {

        return (yn.charAt(0) == 'y') || (yn.charAt(0) == 'Y');

    }

    

    /**

        Reads an entire file into a single string

        @param name The filename to read

        @return a single string with the entire contents of the file

    */

    public static String readFile (String name) {

        String s = "", line;

        try {

            BufferedReader f = new BufferedReader(new FileReader(name));

            while ((line = f.readLine()) != null) s += line + "\n";

            f.close();

        }

        catch (IOException e) { stderr.println("Can't open file: " + name); }

        return s;

    }



    /**

        Gets prompted input as a string from the user via stdin

        @param s The prompt to display on stdout to request input

        @return The line of input from stdin as a string

    */

    public static String prompt (String s) {

        try {

            stdout.print(s);

            stdout.flush();

            return stdin.readLine();

        }

        catch (IOException e) { stderr.println(e); return ""; }

    }



    /**

        Gets prompted input parsed as an int within the specified range from the user via stdin

        @param s The prompt to display on stdout to request input

        @param low The low value of the acceptable range of input

        @param high The high vaue of the range

        @return The int value parsed from the user input

    */

    public static int promptInt (String s, int low, int high) {

        try {

            stdout.print(s + " [" + low + " to " + high + "] ");

            stdout.flush();

            int result = Integer.parseInt(stdin.readLine());

            if (result < low || result > high) throw new IOException("Out of range.");

            return result;

        }

        catch (Exception e) { stderr.println(e); return low - 1; }

    }





}
