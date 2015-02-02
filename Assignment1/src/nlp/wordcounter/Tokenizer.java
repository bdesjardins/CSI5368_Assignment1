package nlp.wordcounter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Tokenizer {

	
	HashMap<String, ?> stopwords;
	HashMap<String, ?> stemmingSuffixes;
	HashMap<String, ?> exceptions;
	HashMap<String, ?> punctuation;
	
	public Tokenizer() {
//		this.stopwords = load(new File("InputFiles/StopWords.txt"));
//		this.stemmingSuffixes = load(new File("InputFiles/stemming.txt"));
//		this.exceptions = load(new File("InputFiles/exceptions.txt"));
		this.punctuation = load(new File("InputFiles/punctuation"));
	}
	
	public String addWhitespace(String line) {
		ArrayList<String> tokenized = new ArrayList<String>();
		String[] firstPass = line.split("\\s");
		
		for (int i = 0; i < firstPass.length; i++){
			String[] token = preProcess(firstPass[i]);
			
			for (int j = 0; j < token.length; j++){
				tokenized.add(token[j]);
			}			
		}
		
		String newLine = tokenized.get(0);
		
		for (int i = 1; i < tokenized.size(); i++) {
			newLine = newLine + " " + tokenized.get(i);
		}
		
		return newLine;
	}
	
	//If there is punctuation, split it from the word using whitespace
	public String[] preProcess(String word){
		String[] tokens = null;
		
		//Do not split the word if it is a special case
		tokens = isSpecialCase(word);
		if (tokens != null) {
			return tokens;
		}
		
		if (word.length() <= 1) {
			return new String[]{word};
		}
		
		String last = "" + word.charAt(word.length()-1);
		
		if (punctuation.containsKey(last)){
			tokens = new String[]{word.substring(0, word.length()-1), last};
			return tokens;
		}
		
		return new String[]{word};
	}
	
	public String stemWord(String word) {
		
		
		return null;
	}
	
	public boolean isStopWord(String word) {
		
		return false;
	}
	
	public boolean isPunctuation(String word) {
		
		return false;
	}
	
	//If a word is a special case (a time, number, emoticon, etc...)
	//This method should also detect hyperlinks
	private String[] isSpecialCase(String word){
		
		return null;
	}
	
	private HashMap<String, ?> load(File inputToLoad) {
		
		HashMap<String, ?> thing = new HashMap<String, Object>();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(inputToLoad));
			
			String line;
			while ((line = br.readLine()) != null) {				
				//Split on whitespace	
				thing.put(line, null);
			}
			br.close();
					
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return thing;
	}
}
