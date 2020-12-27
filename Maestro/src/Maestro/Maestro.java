package Maestro;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * Specifies a program that continuously checks for the existence of a file,
 * creating it if it doesn't exist.
 *
 */
public class Maestro {
	private static final Logger LOGGER = Logger.getLogger(Maestro.class.getName());
	private static File outputFile;

	/**
	 * Checks to see if a file specified as a command-line argument exists at 0.5
	 * second intervals. Creates the file if it does not exist.
	 * 
	 * @param args Command-line arguments. Should contain the path to the file to be
	 *             created as a single argument.
	 * @throws IOException          If the specified file cannot be created.
	 * @throws InterruptedException Thrown if execution of Maestro Solo is
	 *                              interrupted while waiting for next check of the
	 *                              target file's existence.
	 */
	public static void main(String[] args) throws IOException, InterruptedException {
		LOGGER.info("Maestro Solo has started");
		validateArgs(args);
		outputFile = new File(args[0]);
		while (true) {
			createFileIfItDoesNotExist(outputFile);
			Thread.sleep(500);
		}
	}

	/**
	 * Basic check of command-line arguments. Exits the program if no arguments are
	 * given.
	 * 
	 * @param args Arguments that were passed to {@code main}
	 */
	private static void validateArgs(String[] args) {
		if (args.length == 0) {
			LOGGER.log(Level.SEVERE, "No output file specified to Maestro Solo. Stopping.");
			System.exit(1);
		}
		if (args.length > 1) {
			LOGGER.warning("More than one argument was passed to Maestro Solo. "
					+ "All but the first argument will be ignored.");
		}
	}

	/**
	 * Create a file if it does not already exist.
	 * 
	 * @param file File to create if it does not already exist.
	 * @throws IOException
	 */
	private static void createFileIfItDoesNotExist(File file) throws IOException {
		if (!outputFile.isFile()) {
			try {
				outputFile.createNewFile();
			} catch (IOException e) {
				LOGGER.log(Level.SEVERE, "Could not create file " + outputFile);
				throw e;
			}
		}
	}

}
