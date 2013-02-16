package test.handsonworkshopjava;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;

/**
 * This is a program that reads two files finds the common lines and then saves
 * them into a third file
 * 
 * 
 * @author apostelis
 * @version 0.1 @
 */
/**
 * @author apostelis
 *
 */
/**
 * @author apostelis
 * 
 */
public class FindCommonLines {

	/**
	 * Main method that is called from command line
	 * 
	 * @param args
	 *            String[] argument of size 3, first two are the file paths of
	 *            the input files and the third is the the path of the output
	 *            file
	 */
	public static void main(String[] args) {
		ArrayList<String> inputArgs = new ArrayList<String>(Arrays.asList(args));
		FindCommonLines findCommonLines = new FindCommonLines();
		ArrayList<String> inputFile1 = null;
		ArrayList<String> inputFile2 = null;
		ArrayList<String> outputFile = null;
		
		if (!findCommonLines.isInputValid(inputArgs)) {
			System.out.println("Wrong input parameters");
			System.exit(0);
		}
		
		try {
			inputFile1 = findCommonLines.readFileToArrayliArrayList(inputArgs.get(0));
			inputFile2 = findCommonLines.readFileToArrayliArrayList(inputArgs.get(1));
			outputFile = new ArrayList<String>(inputFile1);
			outputFile.retainAll(inputFile2);
			FileUtils.writeLines(new File(inputArgs.get(2)), outputFile);
		} catch (FileNotFoundException e) {
			System.out.print("Something went wrong with reading of the input files");
			e.printStackTrace();
			System.exit(0);
		} catch (IOException e) {
			System.out.print("Something went wrong with writing the output file");
			e.printStackTrace();
		}
	}

	/**
	 * Validates the input arguments
	 * 
	 * @param args
	 *            Arraylist containing the input arguments
	 * @return true if it is a valid input, false if not
	 */
	private boolean isInputValid(ArrayList<String> args) {
		boolean retVal = false;
		// Agmuments must be exaclty 3
		if (args.size() == 3) {
			retVal = true;
		}
		// Arguments must be existing and valid filenames
		for (String test : args) {
			if (isPathCorrect(test) || retVal) {
				retVal = true;
			}
		}

		return retVal;
	}

	/**
	 * @param path
	 *            the path to validate
	 * @return true if it is a valid path, false if not
	 */
	private boolean isPathCorrect(String path) {
		File file = new File(path);
		return file.isFile();
	}

	/**
	 * Read a file to an arraylist of strings
	 * 
	 * @param path Path to the file to read
	 * @return Arraylist<String> containg the lines of the file
	 * @throws FileNotFoundException Exception thrown if file does not exist 
	 */
	private ArrayList<String> readFileToArrayliArrayList(String path)
			throws FileNotFoundException {
		Scanner scanner = new Scanner(new File(path));
		ArrayList<String> list = new ArrayList<String>();
		while (scanner.hasNext()) {
			list.add(scanner.next());
		}
		scanner.close();
		return list;
	}

}
