package nlp.wordcounter.util;

import java.io.File;
import java.util.HashMap;

public class Extras {
	
	HashMap<String, ?> stopwords;
	HashMap<String, ?> stemmingSuffixes;
	HashMap<String, ?> exceptions;
	HashMap<String, ?> punctuation;
	
	public Extras() {
		this.stopwords = load(new File("InputFiles/stopwords.txt"));
		this.stemmingSuffixes = load(new File("InputFiles/stemming.txt"));
		this.exceptions = load(new File("InputFiles/exceptions.txt"));
		this.punctuation = load(new File("InputFiles/punctuation.txt"));
	}
	
	public String stemWord(String word) {
		
		
		return null;
	}
	
	public boolean isStopWord(String word) {
		
		return false;
	}
	
	//If there is punctuation, split it from the word using whitespace
	public String[] containsPunctuation(String word){
		
		//Do not split the word if it is a special case
		if (isSpecialCase(word)) {
			return new String[]{word};
		}
		
		return null;
	}
	
	//If a word is a special case (a time, number, emoticon, etc...)
	//This method should also detect hyperlinks
	private boolean isSpecialCase(String word){
		
		return false;
	}
	
	private HashMap<String, ?> load(File inputToLoad) {
		
		return null;
	}
}
