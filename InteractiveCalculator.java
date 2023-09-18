
import java.io.PrintWriter;
import java.util.Scanner; // Import the Scanner class

/**
 * An interactive calculator which can take fractions and integers uses scanner
 * to get input when ran directly
 *
 * @author Sam Bigham
 */
public class InteractiveCalculator {
	public static void main(String[] args) throws Exception {
		PrintWriter pen = new PrintWriter(System.out, true);
		BigFraction f1;
		f1 = new BigFraction(0, 0); // shall be the default value for fractions
		Scanner myObj = new Scanner(System.in); // Create a Scanner object
		String input; // setting up variables
		BigFraction ansresult;
		String isStore;
		char leter;
		BFCalculator b1 = new BFCalculator();
		b1.setFrac();
		int p = 0;
		boolean quick; // whether this function will use scanner or take in command line arguments from quick calculator
		if(args.length == 0) {
			quick = false;
			p = 100; 
		}else {
			quick = true;
		}
		
		do {

			pen.println("Input something: ");
			if (args.length == 0) {
				input = myObj.nextLine(); // Read user input
			} else {
				input = args[p]; // first command line argument gotten from quickcalculator
				p++;
			}
			if (input.length() >= 7) { // dividing input string into a char[] to be tested
				isStore = input.substring(0, 5); // will check wether it's equal to store
				leter = input.substring(6, 7).charAt(0); // the letter taken
			} else {
				isStore = input;
				leter = 'a'; // default
			}
			if (!input.equalsIgnoreCase("QUIT") && !isStore.equalsIgnoreCase("store")) {
				ansresult = b1.evaluate(input); // this is where all the heavy lifting takes place
				b1.lastresult(ansresult); // stores last result
				pen.println("your input was: " + input);
				pen.println("your result is: " + ansresult);
			} else if (isStore.equalsIgnoreCase("store")) { // if input is store
				pen.println("storing " + leter);
				b1.store(leter);
			}
			if(args.length != 0) {
				if (p < args.length) { // applies for when command line arguments are being used
					quick = false;
				} else {
					quick = true;
				}
			}
		} while (!input.equalsIgnoreCase("QUIT") && !quick);
	} // main(String[])
} // class FractionExpt