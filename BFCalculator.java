import java.math.BigInteger;
import java.util.Scanner;

/**
 * This file contains functions that store BigFractions, and evaluate those
 * fractions there are also other functions that assist in that process and make
 * the code prettier
 *
 * @author Sam Bigham
 */
public class BFCalculator {
	char[] letters = "abcdefghijklmnopqrstuvwxyz".toCharArray(); // alphabetical characters
	BigFraction frac = new BigFraction(0, 0);
	BigFraction frac2 = new BigFraction(1, 10);
	BigFraction[] data = new BigFraction[26]; // where the BigFraction data is stored

	/** The numerator of the fraction. Can be positive, zero or negative. */
	BigInteger num;

	/** The denominator of the fraction. Must be non-negative. */
	BigInteger denom;

	/*
	 * Input: BigFraction
	 * Output: void
	 * takes a bigfraction and stores it. To be used to keep track of the last bigfraction computed
	 */
	public void lastresult(BigFraction fracres) {// stores the last result computed by the calculator
		this.frac = fracres;
	}

	/*
	 * Input: char
	 * Output: void
	 * Takes in a register from a-z and converts it to an index
	 * stores lastresult at value of index
	 */
	public void store(char register) { // stores data
		int index = charToInt(register);
		this.data[index] = this.frac;
	}

	/*
	 * Input: char
	 * Output: int
	 * takes in a char and converts  it to an int starting with a = 0 
	 */
	public int charToInt(char ch) {// converts chars to ints
		int converter = (char) (ch - 'a');
		return converter;
	}

	/*
	 * Input: int
	 * Output: char
	 * takes in an int and converts it to char. Undoes what charToInt does
	 */ 
	public char intToChar(int x) {// converts ints to chars
		char converter = (char) (x + 'a');
		return converter;
	}
	/*
	 * Input: void
	 * Output: void
	 * initializes the data array. Not strictly necessary, but is helpful
	 */
	public void setFrac() {// initializes the data array
		for (int i = 0; i < 26; i++) {
			data[i] = frac;
		}
	}

	/*
	 * input: string of length 1
	 * output: BigFraction
	 * strToFrac also checks whether the string has an alphabetical character and
	 * then returns BigFrac value associated
	 */
	public BigFraction strToFrac(String str1) {
		BigFraction bf = new BigFraction("default");
		if (str1.length() == 1) {
			char charcheck = str1.charAt(0);
			if (Character.isLetter(charcheck)) {
				if (this.frac != null) {
					return data[charToInt(charcheck)];
				} else {
					System.err.println("no value is stored");
				}
			}
		}
		return bf.toFraction(str1);
	}

	// Pre: string: in form of fraction with operators and/or an alphabetical
	// character
	// Pre: string is either has two or three fractions and 1/2 operators
	// Post: a computed fraction in simplified form
	public BigFraction evaluate(String exp) { // powerhorse of the calculator... computes everything given a string
		BigFraction bf = new BigFraction("default");

		Scanner string = new Scanner(exp);
		boolean change1 = false;

		String str1 = (string.next()); // gets each compenent which is seperated by whitespace

		/// hererserfz
		if (!string.hasNext()) {
			return strToFrac(str1); // ends evaluate if there's only one argument
		}

		String operator = string.next();
		String operat2 = "default";
		String str2 = (string.next());
		String str3 = "default";
		BigFraction fract1 = frac;
		BigFraction fract2 = frac;
		BigFraction fract3 = frac;

		try {
			if (string.hasNext()) { // checks whether there are more than 3 arguments
				change1 = true;
				operat2 = string.next();
				str3 = (string.next());
			}
		} catch (Exception e) {
		}
		// if statements to determine if any letter in the alphabet is used
		if (strCompare(str1)) {
			fract1 = this.data[charToInt(str1.charAt(0))];
		} else {
			fract1 = bf.toFraction(str1);
		}
		if (strCompare(str2)) {
			fract2 = this.data[charToInt(str2.charAt(0))];
		} else {
			fract2 = bf.toFraction(str2);
		}
		BigFraction FractReturn = new BigFraction(0, 0);

		if (operator.equals("+")) { // checks which operator is used
			FractReturn = bf.simplify(fract1.add(fract2));
		} else if (operator.equals("-")) {
			FractReturn = bf.simplify(fract1.subtrac(fract2));
		} else if (operator.equals("*")) {
			FractReturn = bf.simplify(fract1.multiply1(fract2));
		} else if (operator.equals("/")) {
			FractReturn = bf.simplify(fract1.divide1(fract2));
		}

		if (change1) {
			if (strCompare(str3)) {
				fract3 = this.data[charToInt(str3.charAt(0))];
			} else {
				fract3 = bf.toFraction(str3);
			}
			if (operat2.equals("+")) { // checks which operator but for fract3
				FractReturn = bf.simplify(FractReturn.add(fract3));
			} else if (operat2.equals("-")) {
				FractReturn = bf.simplify(FractReturn.subtrac(fract3));
			} else if (operat2.equals("*")) {
				FractReturn = bf.simplify(FractReturn.multiply1(fract3));
			} else if (operat2.equals("/")) {
				FractReturn = bf.simplify(FractReturn.divide1(fract3));
			}
		} // if
		return FractReturn;
	}// evalauate

	/*
	 * Input: char
	 * Output: boolean
	 * checks whether a char equals any alphabetical character
	 */
	public boolean charEquals(char hold) { // checks whether a char equals any alphabetical character
		boolean equa = false;
		Character cha = hold;
		for (int i = 0; i < 26; i++) {
			if (cha.equals(letters[i])) {
				equa = true;
			}
		}
		return equa;
	}

	/*
	 * Input: String
	 * Output: boolean
	 *  checks whether string is equal to an alphabetical character
	 */
	public boolean strCompare(String str) {// checks whether string is equal to an alphabetical character
		boolean tru = false;
		char holder;
		if (str.length() == 1) {
			holder = str.charAt(0);
			if (charEquals(holder)) {
				tru = true;
			} else {
				tru = false; // default value as input isn't correct
			}
		}
		return tru;
	}

}