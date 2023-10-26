import java.io.PrintWriter;

/**
 * Simple quick calculator. uses the command line arguments and calls upon the
 * interactive calculator to do all the work, but without scanner
 * sample command could be somethign like java QuickCalculator "1/2 + 1/2" or
 * java QuickCalculator "1/5 + 1/7" "STORE a" "a + 2"
 *
 * @author Sam Bigham
 */

// main class
// takes in command line arguments
public class QuickCalculator {
	public static void main(String[] args) throws Exception {
		InteractiveCalculator.main(args);
	}// main
}