public class KeyChecker {
	
	public static char[] correctWord = null;

	
	public KeyChecker(int lenght){
		int i = 1;
		String string = "";
		while(i <= lenght){
			string += "_";
			i++;
		}
		correctWord = Words.splitWordChar(string);
		
	}
	
	public static boolean compareLetters(char word) {
		char[] words = Words.getChars();
		String wordS = "";
		String wordsS = "";
		int o = 0;
		for (int i = 0; i <= words.length -1; i++) {
			wordsS = Character.toString(words[i]);
			wordS = Character.toString(word);
			if (wordS.equalsIgnoreCase(wordsS)) {
				correctWord[i] = word;
				Screen.print(correctWord,false);
				System.out.println(Screen.arrayToString(correctWord));
				o = 1;
		 	}
		}
		if(o == 1){
			return true;
		}
		return false;
	}

	public static boolean checkIfAllDone(char[] words) {
		String letter = "";
		int correct = 0;
		for (int i = 0; i <= words.length -1; i++){
			letter = Character.toString(words[i]);
			if (!letter.equals("_")){
				correct++;
			}
		}
		if (correct == words.length){
			return true;
		}
		return false;
		
	}
}
