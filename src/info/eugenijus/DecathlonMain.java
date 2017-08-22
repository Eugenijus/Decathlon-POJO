package info.eugenijus;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import info.eugenijus.model.Athlete;
import info.eugenijus.model.CustomTime;
import info.eugenijus.model.Result;

/**
 * Tutorials used:
 * https://www.mkyong.com/java/how-to-read-and-parse-csv-file-in-java/
 * 
 * 
 * @author Eugenijus Sabaliauskas
 */
public class DecathlonMain {
	private static final String SPLITTER = ";";
	private static final char QUOTE = '"';

	public List<String> parseDocument(String filename) {
		List<String> list = new ArrayList<>();
		String line = "";

		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			while ((line = br.readLine()) != null) {
				line = line.trim();
				String[] resultLine = line.split(DecathlonMain.SPLITTER);
				if (resultLine.length > 0) {
					String athlete = (resultLine[0]);
					System.out.print(athlete + "[");
					int limit = resultLine.length - 1;
					for (int i = 1; i < limit; i++) {
						System.out.print(resultLine[i] + ",");
					}
					if (resultLine[limit] != null) {
						System.out.print(resultLine[limit].trim());
					}
					System.out.println("]");
				}
								
				list.add(Arrays.toString(resultLine));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<List<String>> parseDocumentToLists(String filename) {
		List<List<String>> listOfLists = new ArrayList<>();
		List<String> list;
		String line = "";
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			while ((line = br.readLine()) != null) {
				line = line.trim();
				String[] resultLine = line.split(DecathlonMain.SPLITTER);
				list = new ArrayList<>();
				for(String str : resultLine) {
					list.add(str);
				}
				listOfLists.add(list);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return listOfLists;
	}
	
	private void printList(List<String> list) {
		for (String str : list) {
			System.out.println(str);
		}
	}
	
	private void printListNicely(List<String> resultsList) {
		//System.out.println("printListNicely(resultsList) where resultsList = " + resultsList.toString());
		
		//optimistic approach without any checks
		Athlete athlete = new Athlete();
		athlete.setName(resultsList.get(0));
		
		Result result = new Result();
		result.parseAndSet(resultsList);
		athlete.setResult(result);
		System.out.println(athlete.toString());
		
	}
	
	public static void main(String[] args) {
		final String FOLDER = "test-data/";

		DecathlonMain deca = new DecathlonMain();
		System.out.println("========== Printing while parsing ============");
		List<String> listOfResults = deca.parseDocument(FOLDER + "Decathlon_input.txt");
		System.out.println("========== Printing from a list ========================");
		deca.printList(listOfResults);
		System.out.println("========== Advanced printing from a list ==========================");
		List<List<String>> listOfLists = deca.parseDocumentToLists(FOLDER + "Decathlon_input.txt");
		for(List<String> result : listOfLists) {
			deca.printListNicely(result);
		}
		System.out.println("========== Testing CustomTime parsing for minutes and seconds ==========================");
		CustomTime ct1 = new CustomTime("5.25.72");
		System.out.println(ct1.toString());
		CustomTime ct2 = new CustomTime("5.25");
		System.out.println(ct2.toString());
		CustomTime ct3 = new CustomTime("4.00.");
		System.out.println(ct3.toString());
	}

}
