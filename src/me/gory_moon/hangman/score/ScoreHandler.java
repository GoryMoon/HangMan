package me.gory_moon.hangman.score;

public class ScoreHandler {

	private static int score;
	
	/**
	 * Let you add or remove from the score.
	 * @param n
	 * 		Value added or removed
	 * @param add
	 * 		True if add, false if remove.
	 */
	public static void setScore(int n, boolean add) {
		if (add){
			score += n;
		}
		else{
			score -= n;
		}
	}

	/**
	 * Let other methodes get the score.
	 * 
	 * @return
	 * 		The score.
	 */
	public static int getScore() {
		return score;
	}
}
