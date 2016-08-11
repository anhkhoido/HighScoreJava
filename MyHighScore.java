import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class MyHighScore {

	/**
	 * The main function
	 * 
	 * @param args	Expected argument(s) taken by the program.
	 */
	public static void main(String[] args) {		
		
		/**
		 * Variables and instances:
		 * 1. Variable of type double (userInput) that represents the number
		 * storing the user's input.
		 * 
		 * 2. Instance of the class Scanner (keyboard), which will put the number
		 * typed by the user into variable userInput.
		 * 
		 * 3. Instance of the Score class named myInput. Its 
		 * overloaded constructor will accept the variable
		 * named userInput as a parameter. The instance represents
		 * the score of a player in a game.
		 * 
		 * 4. Variable of type integer that represents the number of lines in the
		 * text file named Scores.txt. It takes the value of what is returned by
		 * the method of type int named getNumberOfLinesInTextFile().
		 * 
		 * 5. Instance of PrintWriter, which will take care of the file output stream.
		 * 
		 * 6. Variable of type double that will look for the highest number ever
		 * inputted by the user.
		 */
		double userInput = 0; // 1.
		Scanner keyboard = new Scanner(System.in); // 2.
		Score myInput = new Score(); // 3.
		int numberOfLines = getNumberOfLinesInTextFile(); // 4.
		PrintWriter outputStream = null; // 5.
		double highScore = 0; // 6.
		
		
		/**
		 * FIRST VERIFICATION
		 * 
		 * If the text file named Scores.txt has 10 entries, the program
		 * will call a void method named shrinkTextFile(int n). The latter
		 * will shrink the text file from ten to five lines.
		 */
		if (numberOfLines == 10) shrinkTextFile(numberOfLines);
		
		
		/**
		 * Use of the outputStream instance (PrintWriter) to open the
		 * text file named Scores.txt.
		 */
		try {
			outputStream = new PrintWriter(new FileOutputStream("src/Scores.txt", true));
		} catch (FileNotFoundException e) {
			System.out.println("Error opening the file Scores.txt while trying to append the value of userInput.");
			System.exit(0);
		}
		
		
		/**
		 * If the program has managed to open the text file named Scores.txt,
		 * then it displays a message that prompts the user to input a number.
		 */
		System.out.println("Please enter a number: ");
		userInput = keyboard.nextDouble();
		myInput = new Score(userInput);
		
		/**
		 * Append the number inputted by the user
		 * to the text file named Scores.txt.
		 */
		outputStream.println(userInput);
		outputStream.close();
		
		/**
		 * Close the input stream of the Scanner class's
		 * instance named keyboard.
		 */
		keyboard.close();
		
		
		/**
		 * Variable highScore, which is of type double, will call the method
		 * getHighScore(int n) in order to retrieve the highest number ever
		 * inputted by the user.
		 */
		highScore = getHighScore(getNumberOfLinesInTextFile());
		
		
		
		/**
		 * Display the results on the screen.
		 */
		System.out.println("Your input: " + myInput.getScore());
		System.out.println("High score: " + highScore);
	}
	
	/**
	 * This method goes through the text file named Scores.txt by populating
	 * an array of type double. Afterwards, it calls the method to quickly
	 * sort the array. In the end, local variable highScore, of type double,
	 * will take the value of the last element in the array.
	 * 
	 * @param n		Represents the number of lines in the text file Scores.txt.
	 * @return highScore	The highest number ever inputted by the user.
	 */
	public static final double getHighScore(int n)
	{
		double highScore = 0;
		double[] arr;
		arr = new double[n];
		int i = 0;
		
		String[] whatYouSeeOnTheLine;
		whatYouSeeOnTheLine = new String[n];
		
		Scanner inputStream = null;
		try {
			inputStream = new Scanner(new FileInputStream("src/Scores.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("File " + e.getMessage() + " not found.");
			System.exit(0);
		}
		
		while (inputStream.hasNextLine())
		{
			whatYouSeeOnTheLine[i] = inputStream.nextLine();
			arr[i] = Double.parseDouble(whatYouSeeOnTheLine[i]);
			i++;
		}
		
		inputStream.close();
		
		quick(arr, 0, n - 1);
		
		highScore = arr[arr.length - 1];
		
		return highScore;
	}
	
	/**
	 * 
	 * This static method of type int will use a file input stream in order
	 * to scan the text file named Scores.txt. Upon going through the file,
	 * the method will return the number of lines in the text file.
	 * 
	 * @return numberOfLines	This variable represents the number of lines in text file.
	 */
	public static int getNumberOfLinesInTextFile()
	{
		int numberOfLines = 0;
		
		@SuppressWarnings("unused")
		String whatYouSeeOnTheLine = null;
		
		String fileName = "src/Scores.txt";
		
		Scanner inputStream = null;
		
		/**
		 * Attempt by the program to open the text file
		 * named Scores.txt.
		 */
		try {
			inputStream = new Scanner(new FileInputStream(fileName));
		} catch (FileNotFoundException e) {
			System.out.println("File " + e.getMessage() + " not found.");
			System.exit(0);
		}
		
		/**
		 * While-loop: While the text file named Scores.txt still has a line,
		 * keep going downward in it and increment by one the variable
		 * of type integer named numberOfLines.
		 */
		while (inputStream.hasNextLine())
		{
			whatYouSeeOnTheLine = inputStream.nextLine();
			numberOfLines++;
		}
		
		
		/**
		 * Close the input stream from the Scanner class.
		 */
		inputStream.close();
		
		return numberOfLines;
	}
	
	/**
	 * 
	 * This static method shrink the text file named Scores.txt from
	 * ten to five lines. This prevent the text file from ever becoming
	 * bloated and keeps the file's size under 50 bytes.
	 * 
	 * @param n		Number of lines in the text file named Scores.txt.
	 */
	public static void shrinkTextFile(int n)
	{
		double[] arr;
		arr = new double[n];
		int i = 0;
		
		String[] whatYouSeeOnTheLine;
		whatYouSeeOnTheLine = new String[n];
		
		Scanner inputStream = null;
		
		/**
		 * Attempt to open the file.
		 */
		try {
			inputStream = new Scanner(new FileInputStream("src/Scores.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("File " + e.getMessage() + " not found.");
			System.exit(0);
		}
		
		/**
		 * If the file is there, the program
		 * reads the file until it reaches 
		 * the last line.
		 */
		while (inputStream.hasNextLine())
		{
			whatYouSeeOnTheLine[i] = inputStream.nextLine();
			arr[i] = Double.parseDouble(whatYouSeeOnTheLine[i]);
			i++;
		}
		
		/**
		 * Close the file input stream.
		 */
		inputStream.close();
		
		quick(arr, 0, n - 1);
		
		double[] secondArr;
		secondArr = new double[5];
		
		for (int j = 0; j < 5; j++) secondArr[j] = arr[(n - 1) - j]; 
		
		PrintWriter outputStream = null;
		
		try {
			outputStream = new PrintWriter(new FileOutputStream("src/Scores.txt"));
		} catch (FileNotFoundException e)
		{
			System.out.println("File " + e.getMessage() + " not found.");
			System.exit(0);
		}
		
		for (int k = 0; k < secondArr.length; k++) outputStream.println(secondArr[k]);
		
		
		outputStream.close();
		
	}
	
	/**
	 * 
	 * The quick sort algorithm.
	 * 
	 * @param arr	The array.
	 * @param left	The first element in the array.
	 * @param right	The last element in the array.
	 */
	public static void quick(double[] arr, int left, int right)
	{
		int i = left;
		int j = right;
		
		double tmp;
		
		double pivot = arr[(left + right) / 2];
		
		while (i <= j) {
			while (arr[i] < pivot) i++;
			while (arr[j] > pivot) j--;
			if (i <= j) {
				tmp = arr[i];
				arr[i] = arr[j];
				arr[j] = tmp;
				i++;
				j--;
			}

		}

		if (left < j) quick(arr, left, j);
								
		if (i < right) quick(arr, i, right);
	}
}
