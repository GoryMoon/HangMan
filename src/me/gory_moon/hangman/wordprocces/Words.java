package me.gory_moon.hangman.wordprocces;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JLabel;

import me.gory_moon.hangman.core.HangManCore;
import me.gory_moon.hangman.core.Screen;
import me.gory_moon.hangman.keyhandler.KeyChecker;
/**
 * Methods for the random word used in the game. 
 */
public class Words {
	static Logger logger = Logger.getLogger("Hangman");

	protected static String Text;
	protected static int rline;
	protected static int lenght;

	private static char[] word;

	/**
	 * Gets the words line variable
	 * 
	 * @return The random line
	 */
	public static int getLine() {
		return rline;
	}

	/**
	 * Gets the lenght variable
	 * 
	 * @return The word lenght
	 */
	public static int getWordlenght() {
		return lenght;
	}

	/**
	 * Gets the word variable
	 * 
	 * @return The random word
	 */
	public static String getWord() {
		return Text;
	}

	/**
	 * Counts all lines in a file and returners the nummber of them
	 * 
	 * @param filename
	 *            InputStream of the file
	 * @return Number of lines
	 * @throws IOException
	 */
	public static int countLines(InputStream filename) throws IOException {
		LineNumberReader reader = new LineNumberReader(new InputStreamReader(
				filename));
		int cnt = 0;
		@SuppressWarnings("unused")
		String lineRead = "";
		while ((lineRead = reader.readLine()) != null) {
		}

		cnt = reader.getLineNumber();
		reader.close();
		return cnt;
	}

	/**
	 * Splits the word into a char array.
	 * 
	 * @param word
	 *            The word in String
	 * @return A char array of letters
	 */
	public static char[] splitWordChar(String word) {
		char[] letters = word.toCharArray();
		return letters;
	}

	/**
	 * Splits the word into an array.
	 * 
	 * @param word
	 *            The word in String
	 * @return A array of letters.
	 */
	public static String[] splitWordString(String word) {
		String[] letters = word.split("[^a-z^A-Z]");
		return letters;
	}

	public static char[] getChars() {
		return word;
	}
	
	/**
	 * Gets a row and a row number from a specific file and the length of the
	 * row and saves them to variables inside of the class. Saves them to
	 * {@link #getLine()} and {@link #getWord()}.
	 * 
	 * @param text
	 *            String The name of the file
	 */
	public static void searchForWord(String text, JLabel jlbempty) {
		InputStream stream = HangManCore.class.getResourceAsStream(text);
		int lines = 0;
		Random lineGen = new Random();

		try {
			lines = countLines(stream);
		} catch (IOException e1) {
			logger.log(Level.WARNING,
					"Could not count lines in file: words.txt", e1);
			;
		}

		int linePicked = lineGen.nextInt(lines);
		try {
			stream = HangManCore.class.getResourceAsStream("words.txt");
			LineNumberReader br = new LineNumberReader(new InputStreamReader(
					stream));

			String strLine = null;
			int line = 0;
			int strLenght = 0;
			while ((strLine = br.readLine()) != null) {
				line = line + 1;
				if (line == linePicked) {
					strLenght = strLine.length();
					new KeyChecker(strLenght);
					char[] words = splitWordChar(strLine);
					word = words;
					Screen.print(words, jlbempty, strLenght, true, false);
					System.out.print("\n" + linePicked + " - " + strLine + " ("+ strLenght + ")");
					logger.log(Level.CONFIG, linePicked + " - " + strLine + " (" + strLenght + ")");
					Text = strLine;
					rline = linePicked;
					lenght = strLenght;
				}
			}
		} catch (Exception e2) {
			logger.log(Level.WARNING, "Could not generate random word", e2);
		}
	}

}
