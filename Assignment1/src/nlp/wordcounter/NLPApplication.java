package nlp.wordcounter;

import java.io.File;

import nlp.wordcounter.util.MessageReader;

public class NLPApplication {
	
	public static void main(String[] args) {
		File input = new File("InputFiles/microblog2011.txt");
		File output = new File("OutputFiles/test.txt");
		
		
		MessageReader reader = new MessageReader();
		
		reader.tokenize();
		
	}
	
}
