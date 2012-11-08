import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HangManCore {

	/**
	* @author Gory Moon
 	* @version 1.09
	* @param args
	* @throws IOException
	* Core file, is used to run the program.
	*/
	public static void main(String[] args) throws IOException {

		Logger logger = Logger.getLogger("Hangman");
		FileHandler fh;
		File file = new File("HangmanLogFile.log");
		file.delete();
		try {

			// This block configure the logger with handler and formatter
			fh = new FileHandler("HangmanLogFile.log", true);

			logger.addHandler(fh);
			logger.setLevel(Level.ALL);
			HangmanFormatter formatter = new HangmanFormatter();
			fh.setFormatter(formatter);

			// the following statement is used to log any messages
			// logger.log(Level.FINE,"My first log");

		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		new Screen();

	}

}
