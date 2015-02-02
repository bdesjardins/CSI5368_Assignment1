package nlp.wordcounter;

import java.util.ArrayList;

public class Tokenizer {

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
		
		//Do not split the word if it is a special case
		if (isSpecialCase(word)) {
			return new String[]{word};
		}
		
		return null;
	}
	
	public String stemWord(String word) {
		
		
		return null;
	}
	
	//If a word is a special case (a time, number, emoticon, etc...)
	//This method should also detect hyperlinks
	private boolean isSpecialCase(String word){
		
		return false;
	}
	
	
}
