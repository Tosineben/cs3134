//Alden Quimby
//adq2101
//04.10.11
//COMS 3134

package curriculum;

import java.io.*;

public class CheckCurriculum {

    public static void main (String[] args) throws IOException {

	Curriculum myC = new Curriculum(500); 

	myC.read();

	myC.fixCurriculum();

	myC.write();

    }

}