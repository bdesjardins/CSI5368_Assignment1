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
	

	
	public boolean isStopWord(String word) {
		
		return false;
	}
	
	public boolean isPunctuation(String word) {
		
		return false;
	}
	

	

	
	private HashMap<String, ?> load(File inputToLoad) {
		
		return null;
	}
}
