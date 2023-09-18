import java.math.BigInteger;
import java.lang.String;
import java.util.Scanner; // Import the Scanner class

/**
 * A simple implementation of Fractions.
 * 
 * @author Sam Bigham
 * @co-author Kevin Fitzgerald did the lab in class with me to create parts of
 *            fractions.
 * 
 */
public class BigFraction {
	// +------------------+---------------------------------------------
	// | Design Decisions |
	// +------------------+
	/*
	 * (1) Denominators are always positive. Therefore, negative fractions are
	 * represented with a negative numerator. Similarly, if a fraction has a
	 * negative numerator, it is negative.
	 * 
	 * (2) Fractions are not necessarily stored in simplified form. To obtain a
	 * fraction in simplified form, one must call the `simplify` method.
	 */

	// +--------+-------------------------------------------------------
	// | Fields |
	// +--------+

	/** The numerator of the fraction. Can be positive, zero or negative. */
	BigInteger num;

	/** The denominator of the fraction. Must be non-negative. */
	BigInteger denom;

	// +--------------+-------------------------------------------------
	// | Constructors |
	// +--------------+

	/**
	 * Build a new fraction with numerator num and denominator denom.
	 * 
	 * Warning! Not yet stable.
	 */
	public BigFraction(BigInteger num, BigInteger denom) {
		this.num = num;
		this.denom = denom;
	} // Fraction(BigInteger, BigInteger)

	/**
	 * Build a new fraction with numerator num and denominator denom.
	 * 
	 * Warning! Not yet stable.
	 */
	public BigFraction(int num, int denom) {
		this.num = BigInteger.valueOf(num);
		this.denom = BigInteger.valueOf(denom);
	} // Fraction(int, int)

	/**
	 * Build a new fraction by parsing a string.
	 *
	 * Warning! Not yet implemented.
	 */
	public BigFraction(String str) {
		this.num = BigInteger.valueOf(2);
		this.denom = BigInteger.valueOf(7);
	} // Fraction

	// +---------+------------------------------------------------------
	// | Methods |
	// +---------+

	/**
	 * Express this fraction as a double.
	 */

	public double doubleValue() {
		return this.num.doubleValue() / this.denom.doubleValue();
	} // doubleValue()

	public BigFraction Fractional() {
		BigInteger numer = this.num.mod(this.denom);
		BigInteger denomin = this.denom;

		return this;
	}

	public BigFraction toFraction(String str) {
		int numer;
		int denomer;
		if (!str.contains("/")) {
			numer = Integer.valueOf(str);
			denomer = 1;
		} else {
			int divisor = str.indexOf("/");
			numer = Integer.valueOf(str.substring(0, divisor));
			denomer = Integer.valueOf(str.substring(divisor + 1, str.length()));
		}
		return new BigFraction(numer, denomer);
	}

	public BigFraction multiply1(BigFraction mult1) {
		BigInteger numerator = (this.num.multiply(mult1.num));
		BigInteger denomenator = (this.denom.multiply(mult1.denom));
		return new BigFraction(numerator, denomenator);
	}// Muliply

	/**
	 * Add the fraction `addMe` to this fraction.
	 */
	public BigFraction add(BigFraction addMe) {
		BigInteger resultNumerator;
		BigInteger resultDenominator;

		// The denominator of the result is the
		// product of this object's denominator
		// and addMe's denominator
		resultDenominator = this.denom.multiply(addMe.denom);
		// The numerator is more complicated
		resultNumerator = (this.num.multiply(addMe.denom)).add(addMe.num.multiply(this.denom));

		// Return the computed value
		return new BigFraction(resultNumerator, resultDenominator);
	}// add(Fraction)

	public BigFraction subtrac(BigFraction subme) {
		BigInteger numerator;
		BigInteger denominator;

		denominator = this.denom.multiply(subme.denom);
		numerator = (this.num.multiply(subme.denom)).subtract(subme.num.multiply(this.denom));

		return new BigFraction(numerator, denominator);
	}// subtract

	public BigFraction divide1(BigFraction dividme) {

		BigInteger numerator = (this.num.multiply(dividme.denom));
		BigInteger denomenator = (this.denom.multiply(dividme.num));

		return new BigFraction(numerator, denomenator);
	}

	public BigFraction simplify(BigFraction simplif) {

		BigInteger numerator = simplif.numerator();
		BigInteger denomenator = simplif.denominator();
		BigInteger gc = gcd(numerator, denomenator);
		numerator = numerator.divide(gc);
		denomenator = denomenator.divide(gc);

		return new BigFraction(numerator, denomenator);
	}

	public BigInteger gcd(BigInteger x, BigInteger y) {
		BigInteger zero;
		zero = BigInteger.valueOf(0);
		if (y == zero) {
			return x; // this will be the gcd when returned
		}

		return gcd(y, x.mod(y)); // recursion to find the greatest common denominator

	}// greatest common denominator

	/**
	 * Get the denominator of this fraction.
	 */
	public BigInteger denominator() {
		return this.denom;
	} // denominator()

	/**
	 * Get the numerator of this fraction.
	 */
	public BigInteger numerator() {
		return this.num;
	} // numerator()

	/**
	 * Convert this fraction to a string for ease of printing.
	 */
	public String toString() {
		// Special case: It's zero
		if (this.num.equals(BigInteger.ZERO)) {
			return "0";
		} // if it's zero

		// Lump together the string represention of the numerator,
		// a slash, and the string representation of the denominator
		// return this.num.toString().concat("/").concat(this.denom.toString());
		return this.num + "/" + this.denom;
	} // toString()

} // class Fraction