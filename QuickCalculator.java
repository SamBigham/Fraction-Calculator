import java.io.PrintWriter;

/**
 * Simple quick calculator. uses the command line arguments and calls upon the
 * interactive calculator to do all the work, but without scanner
 *
 * @author Sam Bigham
 */

//main class
//takes in command line arguments
public class QuickCalculator {
	public static void main(String[] args) throws Exception {
		PrintWriter pen = new PrintWriter(System.out, true);
		BigFraction f1;
		f1 = new BigFraction(1, 1);
		InteractiveCalculator IC = new InteractiveCalculator();
		BFCalculator b2 = new BFCalculator();

		InteractiveCalculator.main(args);
	}//main
} 