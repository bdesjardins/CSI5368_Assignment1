package nlp.wordcounter;

import java.io.File;
import java.util.Scanner;

import nlp.wordcounter.util.MessageReader;

public class NLPApplication {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		System.out.println("Please select the command to execute (1-4):");
		System.out.println("1 - Tokenize the corpus");
		System.out.println("2 - Count all words");
		System.out.println("3 - Count all words (no punctuation)");
		System.out.println("4 - Count all words (no punctuation or stopwords");
		System.out.println("5 - Count all bigrams");

		int selected = in.nextInt();
		while(selected <= 0 && selected > 5){
			System.out.println("Please select the command to execute (1-5):");
			in.nextInt();
		}

		MessageReader reader;
		File input, output;
		
		switch(selected) {
		case 1:
			input = new File("InputFiles/microblog2011.txt");
			output = new File("OutputFiles/tokens.txt");
			reader = new MessageReader(input, output);
			reader.tokenize();
			break;
		case 2:
			input = new File("OutputFiles/tokens.txt");
			output = new File("OutputFiles/AllCount.txt");
			reader = new MessageReader(input, output);
			reader.countWords(false, false);
			break;
		case 3:
			input = new File("OutputFiles/tokens.txt");
			output = new File("OutputFiles/count_noPunct.txt");
			reader = new MessageReader(input, output);
			reader.countWords(true, false);
			break;
		case 4:
			input = new File("OutputFiles/tokens.txt");
			output = new File("OutputFiles/count_noPunct_noStop.txt");
			reader = new MessageReader(input, output);
			reader.countWords(true, true);
			break;
		case 5:
			input = new File("OutputFiles/tokens.txt");
			output = new File("OutputFiles/bigrams.txt");
			reader = new MessageReader(input, output);
			reader.countBigrams(true, true);
			break;
		}		
	}
}
