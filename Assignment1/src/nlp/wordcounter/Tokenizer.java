package nlp.wordcounter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Tokenizer {

	String[] suffixes = new String[]{"ed","ing","en","ly","iful","ation","es"};
	
	HashMap<String, ?> stopwords;
	HashMap<String, ?> stemmingSuffixes;
	HashMap<String, ?> exceptions;
	HashMap<String, ?> punctuation;
	
	public Tokenizer() {
		this.stopwords = load(new File("InputFiles/StopWords.txt"));
//		this.stemmingSuffixes = load(new File("InputFiles/stemming.txt"));
		this.exceptions = load(new File("InputFiles/exceptions"));
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
		word = word.toLowerCase();
		
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
		}
			
		if (tokens == null) {
			return new String[]{stemWord(word)};
		} else {
			for (int i = 0; i < tokens.length; i++) {
				tokens[i] = stemWord(tokens[i]);
			}
			return tokens;
		}
	}
	
	//Stemming like this feels too random...
	//A smart stemmer would likely require a dictionary
	public String stemWord(String word) {		
//		if(word.length() <= 4) {
//			return word;
//		}
//		
//		for(int i = 0; i < this.suffixes.length; i++) {
//			if (word.endsWith(this.suffixes[i])){
//				int sufLength = this.suffixes[i].length();
//				return word.substring(0, word.length()-sufLength);
//			}
//		}
		
		return word;
	}
	
	public boolean isStopWord(String word) {		
		if(stopwords.containsKey(word)){
			return true;
		}		
		return false;
	}
	
	public boolean isPunctuation(String word) {		
		if(punctuation.containsKey(word)){
			return true;
		}	
		return false;
	}
	
	//If a word is a special case (a time, number, emoticon, etc...)
	//This method should also detect hyperlinks
	private String[] isSpecialCase(String word){
		//Check exception list first to reduce overhead
		if (exceptions.containsKey(word)) {
			return new String[]{word};
		}
		
		//Check for hyperlink, user, or hastag
		if (word.startsWith("http") || word.startsWith("@") || word.startsWith("#")){
			return new String[]{word};
		}
		
		//Deal with special punctuation cases
		String ellipsis = "...";
		String interrobang1 = "!?";
		String interrobang2 = "?!";
		
		String special = null;
		
		if (word.contains(ellipsis)) {
			special = ellipsis;
		} else if (word.contains(interrobang1)){
			special = interrobang1;
		} else if (word.contains(interrobang2)){
			special = interrobang2;
		}
		
		if (special != null) {
			if (word.endsWith(special)) {
				return new String[]{word.substring(0, word.length() - special.length()),
						word.substring(word.length() - special.length())};
			} else if (word.startsWith(special)) {
				return new String[]{word.substring(0, special.length()),
						word.substring(special.length(), word.length())};
			}
		}	
		
		//We return null if its not a special case so the other method will know
		return null;
	}
	
	//Method to fill the text lists used for lookup
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
