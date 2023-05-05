/*
 * TechTogether Miami 2023 Hackathon
 * 5/5/2023
 * Ishel Zain, Thi Pham, Kristy Hamlin
 * 
 * This Class will read a text file representing
 * a job ad and convert the key words into an ArrayList. 
 *  
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;



public class JobReader {
	private ArrayList<String> keywords = new ArrayList<String>();
	private int years;
	//private String path;
	
	public JobReader(int years, String path) {
		
		this.years = years;
		
		this.read(path);
		
	}
	
	public int getYears() {
		return this.years;
	}
	
	public ArrayList<String> getKeywords(){
		return keywords;
	}
	
	
	public void read(String path) {
		ArrayList<String> ignoreWords = new ArrayList<String>(Arrays.asList("the", "of", "and",
				"a", "to", "in", "is", "you", "that", "it", "he", "was", "for", "on", "are", "as",
				"with", "his", "they", "I", "at", "be", "this", "have", "from", "or", "one", "had",
				"by", "word", "but", "not", "what", "all", "were", "we", "when", "your", "can",
				"said", "there", "use", "an", "each", "which", "she", "do", "how", "their", "if", "will",
				"up", "other", "about", "out", "many", "then", "them", "these", "so", "some", "her", "would",
				"make", "like", "him", "into", "time", "has", "look", "two", "more"));
		
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(path));
			String line;
			
			while((line = reader.readLine()) != null) {
				Scanner scnr = new Scanner(line);
				while(scnr.hasNext()) {
					String word = scnr.next();
					if(ignoreWords.contains(word) == false && keywords.contains(word) == false) {
						keywords.add(word);
					}
					
					
				}
				scnr.close();
				
			}
			reader.close();
		} catch (IOException e) {
			System.out.println("File not found. Make sure there is a .txt file of the name specified inside the project"
					+ "\n folder or provide a string of the absolute path.");
			e.printStackTrace();
		}
		//testing
		//this.printKeywords();
		
	}
	public void printKeywords() {
		//Used for testing
		if(keywords.size() != 0) {
			for(int i = 0; i < keywords.size(); i++) {
				System.out.println(keywords.get(i));
			}
		}else {
			System.out.println("No keywords. Use .read(fileName.txt) to read a job posting.");
		}
	}

}
