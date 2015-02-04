package nlp.wordcounter.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;

import nlp.wordcounter.Tokenizer;

public class MessageReader {

	File input;
	File output;
	Hashtable<String, Integer> countMap;
	
	int types = 0;
	int tokens = 0;
	int appearOnce = 0;


	public MessageReader(File input, File output) {
		this.input = input;
		this.output = output;

		this.countMap = new Hashtable<String, Integer>();
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

	//Run, counting all words as they are
	public void run() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(input));
			String line;

			// process line by line
			while ((line = br.readLine()) != null) {				
				//Split on whitespace
				String[] tokens = line.split("\\s");

				//Processing and such will happen in this loop
				for (int i = 0; i < tokens.length; i++) {
					int value = 0;

					if (countMap.get(tokens[i]) != null) {
						value = countMap.get(tokens[i]) + 1;
					}

					countMap.put(tokens[i], value);
				}
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		printToFile();
	}

	//Run, using stopwords, stemming
	public void runWithExtras() {
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
					int value = 0;

					if (countMap.get(tokens[i]) != null) {
						value = countMap.get(tokens[i]) + 1;
					}

					countMap.put(tokens[i], value);
				}
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		printToFile();
	}

	private void printToFile() {
		//Print the contents of countMap to the outouot file
	}

}
