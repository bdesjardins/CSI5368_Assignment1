package nlp.part2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class CalcAccuracy {

	public CalcAccuracy() {
		// TODO Auto-generated constructor stub

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub


		try {
			BufferedReader br1 = new BufferedReader(new FileReader(new File("InputFiles/Expected_solution.txt")));
			BufferedReader br2 = new BufferedReader(new FileReader(new File("InputFiles/output_file_conjoint.txt")));
			String line1;
			String line2;
			int tokenCount = 0;
			int match =0;
			// process line by line
			while (((line1 = br1.readLine()) != null)&&(line2 = br2.readLine()) != null)
			{	

				//Split on whitespace
				String[] token1 = line1.split("\\s");
				String[] token2 = line2.split("\\s");
				//Processing and such will happen in this loop
				//The extras will be used in this loop
				for (int i = 0; i < token2.length; i++) {
					tokenCount++;


					if(token1[i].equalsIgnoreCase(token2[i]) ){
						match ++;
					}	
				}

			}

			br1.close();
			br2.close();
			System.out.println("Total number of tokens: " + tokenCount);
			System.out.println("Total number of matching tokens: " + match);
			System.out.println("Accuracy " + ((match*1.0)/(tokenCount*1.0))*100 + "%");
		} 
		catch (IOException e) {
			e.printStackTrace();
		}

	}}

