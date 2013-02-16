package test.handsonworkshopjava;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
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
		ArrayList<String> inputList1 = null;
		ArrayList<String> inputList2 = null;
		ArrayList<String> outputList = null;
		
		if (!findCommonLines.isInputValid(inputArgs)) {
			System.out.println("Wrong input parameters");
			System.exit(0);
		}
		
		try {
			System.out.println("Read file 1");
			inputList1 = findCommonLines.readFileToArrayliArrayList(inputArgs.get(0));
			System.out.println("Read file 2");
			inputList2 = findCommonLines.readFileToArrayliArrayList(inputArgs.get(1));
			System.out.println("Compare file 1 and file 2");
			outputList = new ArrayList<String>(inputList1);
			outputList.retainAll(inputList2);
			System.out.println("Sort lexicographically the resulting file");
			Collections.sort(outputList, new Comparator<String>() {
	            @Override
	            public int compare(String a, String b) {
	                return a.compareTo(b);
	            }
	        });
			System.out.println("Write out file");
			FileUtils.writeLines(new File(inputArgs.get(2)), outputList);
			
		} catch (FileNotFoundException e) {
			System.out.print("Something went wrong with reading of the input files");
			e.printStackTrace();
			System.exit(0);
		} catch (IOException e) {
			System.out.print("Something went wrong with writing the output file");
			e.printStackTrace();
		}
		System.out.println("Output file writen");
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
