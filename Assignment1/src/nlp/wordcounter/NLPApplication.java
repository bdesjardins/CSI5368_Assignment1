package nlp.wordcounter;

import java.io.File;

import nlp.wordcounter.util.MessageReader;

public class NLPApplication {
	
	public static void main(String[] args) {
//		File input = new File("InputFiles/tokenTest");
		File input = new File("InputFiles/microblog2011.txt");
		File output = new File("OutputFiles/test1.txt");
				
		MessageReader reader = new MessageReader(input, output);
		
		reader.tokenize();		
	}
	
}
