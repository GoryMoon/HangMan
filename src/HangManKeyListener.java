import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JTextField;

public class HangManKeyListener implements KeyListener {

	static Logger logger = Logger.getLogger("Hangman");
	public static boolean detect = true;
	@Override
	public void keyPressed(KeyEvent arg0) {
		boolean status = false;
		detect = true;
		if (Screen.done){
			detect = false;
			Screen.restart();
		}
		if (detect){
		System.out.println("Key: " + arg0.getKeyChar() + " pressed");
		if (KeyChecker.compareLetters(arg0.getKeyChar())) {
			System.out.println("True");
			status = true;
		} else {
			System.out.println("False");
			Screen.triesLeft --;
			Screen.print(KeyChecker.correctWord, false);
			if (Screen.triesLeft == 0){
				Screen.Lose(Words.getWord());
			}
		}
		JTextField typingArea = Screen.getTypingArea();
		typingArea.setText(null);
		logger.log(Level.FINE, "Key: "+ arg0.getKeyChar() + " pressed \t"+status);
		if (KeyChecker.checkIfAllDone(KeyChecker.correctWord)){
			Screen.finalPrint(KeyChecker.correctWord, true);
		}
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		JTextField typingArea = Screen.getTypingArea();
		typingArea.setText(null);

	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}

}
