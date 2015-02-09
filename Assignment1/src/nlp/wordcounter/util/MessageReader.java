package nlp.wordcounter.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

import nlp.wordcounter.Tokenizer;

public class MessageReader {

	File input;
	File output;
	Hashtable<String, Integer> countMap;

	int types = 0;
	int tokenCount = 0;
	int appearOnce = 0;

	HashMap<String, ?> stopwords;
	HashMap<String, ?> punctuation;

	public MessageReader(File input, File output) {
		this.input = input;
		this.output = output;

		this.countMap = new Hashtable<String, Integer>();

		this.stopwords = load(new File("InputFiles/StopWords.txt"));
		this.punctuation = load(new File("InputFiles/punctuation"));
	}

	public void tokenize(){
		Tokenizer tokenizer = new Tokenizer();

		try {
			PrintWriter writer = new PrintWriter(output, "UTF-8");		
			BufferedReader br = new BufferedReader(new FileReader(input));
			String line;

			// process line by line
			while ((line = br.readLine()) != null) {				
				//Split on whitespace
				writer.println(tokenizer.addWhitespace(line));
				writer.flush();				
			}
			br.close();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	//Counts the words in the tokenized text. Can ignore stopwords or punctuation
	public void countWords(boolean ignorePunctuation, boolean ignoreStopwords) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(input));
			String line;

			// process line by line
			while ((line = br.readLine()) != null) {				
				//Split on whitespace
				String[] tokens = line.split("\\s");

				//Processing and such will happen in this loop
				//The extras will be used in this loop
				for (int i = 0; i < tokens.length; i++) {					
					int value = 1;

					if(ignorePunctuation && (punctuation.containsKey(tokens[i]) ||
							tokens[i].equalsIgnoreCase("...") ||
							tokens[i].equalsIgnoreCase("!?") ||
							tokens[i].equalsIgnoreCase("?!"))){
						continue;
					} else if(ignoreStopwords && stopwords.containsKey(tokens[i])){
						continue;
					} else if (countMap.get(tokens[i]) != null) {
						value = countMap.get(tokens[i]) + 1;						
					}
					this.tokenCount++;

					if(value == 2) {
						appearOnce--;
					}

					if(value == 1) {
						appearOnce++;
						types++;
					}
					countMap.put(tokens[i], value);
				}
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		//Prints the results of the counting
		printToFile();
	}

	public void countBigrams(boolean ignorePunctuation, boolean ignoreStopwords){
		try {
			BufferedReader br = new BufferedReader(new FileReader(input));
			String line;

			// process line by line
			while ((line = br.readLine()) != null) {				
				//Split on whitespace
				String[] tokens = line.split("\\s");

				//Processing and such will happen in this loop
				//The extras will be used in this loop
				for (int i = 0; i < tokens.length-1; i++) {										
					String word1 = tokens[i];
					String word2 = tokens[i+1];

					

					if (ignorePunctuation && punctuation.containsKey(word1)){
						continue;
					} else if(ignoreStopwords && stopwords.containsKey(word1)){
						continue;
					} else if (word1.equalsIgnoreCase("...") || 
							word1.equalsIgnoreCase("?!") ||
							word1.equalsIgnoreCase("!?")){
						continue;
					}

					boolean validWord = false;
					int j = i+2;

					while (!validWord && j < tokens.length) {
						word2 = tokens[j];
						if(ignorePunctuation && punctuation.containsKey(word2)){
							j++;
							continue;
						} else if(ignoreStopwords && stopwords.containsKey(word2)){
							j++;
							continue;
						} else if (word2.equalsIgnoreCase("...") || 
								word2.equalsIgnoreCase("?!") ||
								word2.equalsIgnoreCase("!?")){
							j++;
							continue;
						} else {
							validWord = true;
						}					
					}
					
					if (!validWord){
						continue;
					}

					String bigram = word1 + " " + word2;
					
					int value = 1;
					if (countMap.get(bigram) != null) {
						value = countMap.get(bigram) + 1;						
					}
					this.tokenCount++;

					if(value == 2) {
						appearOnce--;
					}
					if(value == 1) {
						appearOnce++;
						types++;
					}
					countMap.put(bigram, value);
				}
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		//Prints the results of the counting
		printToFile();
	}

	//Print the contents of countMap to the output file
	private void printToFile() {
		try {
			PrintWriter writer = new PrintWriter(output, "UTF-8");

			ArrayList<Map.Entry<String, Integer>> sorted = sortValue(countMap);

			writer.println("Number of Tokens: \t\t" + this.tokenCount);
			writer.println("Number of Types: \t\t" + this.types);
			writer.println("Type to Token Ratio: \t\t" + (1.0*this.types)/(1.0*this.tokenCount));
			writer.println("Number of Unique Tokens: \t\t" + this.appearOnce);

			writer.flush();

			for(int i = 0; i < sorted.size(); i++){
				writer.println(sorted.get(i).getKey() + "\t\t" + sorted.get(i).getValue());
				writer.flush();	
			}

			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
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

	//Sorts a hashTable in descending order
	public static ArrayList<Map.Entry<String, Integer>> sortValue(Hashtable<String, Integer> t){
		//Transfer as List and sort it
		ArrayList<Map.Entry<String, Integer>> l = new ArrayList(t.entrySet());
		Collections.sort(l, new Comparator<Map.Entry<String, Integer>>(){

			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}});

		return l;
	}
}
