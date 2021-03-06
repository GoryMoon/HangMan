package me.gory_moon.hangman.core;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;

import me.gory_moon.hangman.keyhandler.HangManKeyListener;
import me.gory_moon.hangman.keyhandler.KeyChecker;
import me.gory_moon.hangman.score.ScoreHandler;
import me.gory_moon.hangman.wordprocces.Words;

@SuppressWarnings("unused")
/**
 * Handels all related to the screen and more.
 * It's kind of the core. 
 */
public class Screen extends JFrame {

	private static final long serialVersionUID = 1L;
	static char[] words = null;
	private static JLabel jlab;
	static JLabel jlbempty = null;
	static JLabel jlfinal = null;
	static JLabel tries = null;
	static JLabel score = null;
	static JTextField typingArea = new JTextField(20);
	static Logger logger = Logger.getLogger("Hangman");
	public static boolean done = false;
	public static int triesLeft = 10;
	static int scorep = 0;

	/**
	* The main method of this class that's the core of everything.
	* This run all the other classes and makes  it work.
	*/
	public Screen() {
		super("Hangman Demo");
		JButton button = new JButton("Randomise Word");
		final String labelText = "Random Words";
		done = false;
		setResizable(false);

		// --Menu Start--\\

		JMenuBar menuBar = new JMenuBar();

		// Add the menubar to the frame
		setJMenuBar(menuBar);

		// Define and add two drop down menu to the menubar
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);

		// Create and add simple menu item to one of the drop down menu
		JMenuItem debugAction = new JMenuItem("Console");
		JMenuItem exitAction = new JMenuItem("Exit");
		JMenuItem changeWordAction = new JMenuItem("New Word");
		// Create and add CheckButton as a menu item to one of the drop down
		// menu
		// JCheckBoxMenuItem checkAction = new
		// JCheckBoxMenuItem("Check Action");
		// Create a ButtonGroup and add both radio Button to it. Only one radio
		// button in a ButtonGroup can be selected at a time.
		fileMenu.add(debugAction);
		fileMenu.addSeparator();
		fileMenu.add(changeWordAction);
		fileMenu.addSeparator();
		fileMenu.add(exitAction);
		// editMenu.add(radioAction2);
		// Add a listener to the New menu item. actionPerformed() method will
		// invoked, if user triggered this menu item
		debugAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new AWTConsole();
				System.out.println(Words.getLine()+" - "+Words.getWord()+" ("+Words.getWordlenght()+")");
			}
		});
		exitAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		// -- Menu End--\\
		jlbempty = new javax.swing.JLabel();
		jlfinal = new javax.swing.JLabel();
		tries = new javax.swing.JLabel();
		score = new javax.swing.JLabel();
		score.setText("");

		changeWordAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Words.searchForWord("words.txt", jlbempty);
				triesLeft = 10;
			}
		});
		// Add a window listener for close button
		addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		// This is an empty content area in the frame

		jlfinal.setFont(new java.awt.Font("Serif", 2, 18)); // NOI18N
		jlbempty.setFont(new java.awt.Font("Serif", 0, 36)); // NOI18N
		tries.setFont(new java.awt.Font("Serif", 0, 14));
		score.setFont(new java.awt.Font("Serif", 2, 14));
		jlbempty.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jlfinal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		tries.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        tries.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        score.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        score.setVerticalAlignment(javax.swing.SwingConstants.TOP);
		
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap(85, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jlfinal)
                            .addGap(40, 181, 181))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jlbempty, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(9, 79, 79))
                        .addComponent(score, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(tries, javax.swing.GroupLayout.Alignment.TRAILING)))
            );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(tries)
                    .addComponent(score)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                    .addComponent(jlbempty)
                    .addGap(58, 58, 58)
                    .addComponent(jlfinal)
                    .addGap(86, 86, 86))
            );
                
		Container content = getContentPane();
		typingArea.addKeyListener(new HangManKeyListener());
		content.add(typingArea, BorderLayout.SOUTH);
		setSize(500, 400);
		setVisible(true);
		Words.searchForWord("words.txt", jlbempty);
	}

	/**
	* Convert an char[] array of strings to one string.
	* Put the 'separator' string between each element.
	* @param words
	*			A char[] array.
	* @return The Array as String
	*/
	public static String arrayToString(char[] words) {
		StringBuffer result = new StringBuffer();
		if (words.length > 0) {
			result.append(words[0]);
			for (int i = 1; i < words.length; i++) {
				result.append(" ");
				result.append(words[i]);
			}
		}
		return result.toString();
	}
	
	/**
	* Prints the char[] array on the JLabel on the screen.
	* 
	* @param word
	*			The char[] array to print to the JLabel.
	* @param jlbempty
	*			The JLabel to print to.
	* @param strLenght
	*			The lenght of the char[] array printed counted as a string.
	* @param first
	*			If it's the first time runned.
	* @param finalp
	*			If it's the last time runned.
	*/
	public static void print(char[] word, JLabel jlbempty, int strLenght,
			boolean first, boolean finalp) {
		jlab = jlbempty;
		String string = "";
		int i = 1;
		scorep = ScoreHandler.getScore();
		score.setText("Your Score: "+scorep);
		tries.setText("Tries left: "+triesLeft);
		if (first) {
			tries.setText("Tries left: 10");
			while (i <= strLenght) {
				string += "_ ";
				i++;
			}
		} else {
			string = arrayToString(word);
		}
			jlbempty.setText(string);
		if (finalp){
			jlbempty.setText("Well Done");
			jlfinal.setText("Hit any button to continue");
			System.out.println("User finished the word: " + Screen.arrayToString(word));
			logger.log(Level.FINEST, "User have finished the word: "+ Screen.arrayToString(word));
			done = true;
		}
	}

	/**
	* An print that don't need all the @params for easier print.
	* This is with the option of FirstPrint it.
	* @param printText
	*			The text to print to the JLabel.
	* @param firstPrint
	* 			If it's the first print or not.
	*/
	public static void print(char[] printText, boolean firstPrint) {
		print(printText, jlbempty, Words.getWordlenght(), firstPrint, false);
	}

	/**
	* An print that don't need all the @params for easier print.
	* This is with the option of FinalPrint it.
	* @param printText
	*			The text to print to the JLabel.
	* @param firstPrint
	* 			If it's the last print or not.
	*/
	public static void finalPrint(char[] printText, boolean lastPrint) {
		print(printText, jlbempty, Words.getWordlenght(), false,lastPrint);
	}

	/**
	* An print that don't need all the @params for easier print.
	* This is just a regular print.
	* @param printText
	*			The text to print to the JLabel.
	*/
	public static void print(String printText) {
		print(Words.splitWordChar(printText), jlbempty, Words.getWordlenght(), false, false);
	}

	/**
	* It returns the typingarea where the user types it's letters.
	* @return typingArea
	*			The user input
	*/
	public static JTextField getTypingArea() {
		return typingArea;
	}

	/**
	* Restarts the HangMan game when
	* the player wins or losses.
	*/
	public static void restart() {		
		done = false;
		triesLeft = 10;
		HangManKeyListener.setPressedKeyArray(Words.splitWordChar(""));
		jlfinal.setText(null);
		Words.searchForWord("words.txt", jlbempty);
	}

	/**
	* Do the Informtion for the user when he/she losses and
	* put information to the console and log.
	* @param word
	*			The String that got printed in log and console.
	*/
	public static void Lose(String word) {
		ScoreHandler.setScore(10, false);
		jlbempty.setText("You Lose");
		jlfinal.setText("Hit any button to continue");
		System.out.println("User failed the word: " + word);
		logger.log(Level.FINEST, "User have failed the word: "+ word);
		done = true;
	}
}