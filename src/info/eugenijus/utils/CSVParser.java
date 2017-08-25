package info.eugenijus.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import info.eugenijus.model.Athlete;
import info.eugenijus.model.Constants;
import info.eugenijus.model.Result;

/**
 * Parses file with decathlon events and converts to lists or arrays to be used later<br/>
 * By default it is assumed that separator is a comma ","!<br/>
 * Otherwise the separator is specified in constructor by extending class
 * <br/><br/>
 * Tutorials used:
 * https://www.mkyong.com/java/how-to-read-and-parse-csv-file-in-java/
 * 
 * @author Eugenijus Sabaliauskas
 */
public class CSVParser implements DocumentParser {
	
	private String separator;
	
	/**
	 * default separator is COMMA ","
	 */
	public CSVParser() {
		separator = Constants.COMMA;
	}

	/**
	 * Only classes which are in same package or extend this class can choose their own separator
	 * @param separator
	 */
	protected CSVParser(String separator) {
		this.separator = separator;
	}
	
	
	/**
	 * Parses file and creates list of Strings of decathlon event results (distances, times) 
	 * where first String represents name and other 10 Strings are results of single athelete<br/>
	 * @return List<String> list 
	 */
	@Override
	public List<String> parseDocument(String filename) {
		List<String> list = new ArrayList<>();
		String line = "";
		//reading filename
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			while ((line = br.readLine()) != null) {
				line = line.trim();
				String[] resultLine = line.split(separator);
				list.add(Arrays.toString(resultLine));
				// PRINTING FOR TESTING ONLY
				printNicelyFromArray(resultLine);	
			}
			System.out.println("Done parsing file: " + filename);
		} catch (IOException e) {
			System.out.println("Couldn't parse file: " + filename);
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * Parses file and creates list of Athlete objects<br/>
	 * @return List<String> list 
	 */
	@Override
	public List<Athlete> parseDocumentToAthletes(String filename) {
		List<Athlete> list = new ArrayList<>();
		String line = "";
		//reading filename
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			while ((line = br.readLine()) != null) {
				line = line.trim();
				String[] resultLine = line.split(separator);
				Athlete athlete = new Athlete();
				athlete.setName(resultLine[0]);
				athlete.setResult(new Result(resultLine));				
				list.add(athlete);
			}
			System.out.println("Done parsing file: " + filename);
		} catch (IOException e) {
			System.out.println("Couldn't parse file: " + filename);
			e.printStackTrace();
		}
		return list;
	}


	/**
	 * Parses file and creates list of lists of decathlon event results (distances, times) where one single result is String<br/>
	 * @return List<String> list 
	 */
	@Override
	@Deprecated
	public List<List<String>> parseDocumentToLists(String filename) {
		List<List<String>> listOfLists = new ArrayList<>();
		List<String> list;
		String line = "";
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			while ((line = br.readLine()) != null) {
				line = line.trim();
				String[] resultLine = line.split(separator);
				list = new ArrayList<>();
				for(String str : resultLine) {
					list.add(str);
				}
				listOfLists.add(list);
			}
			System.out.println("Done parsing file: " + filename);
		} catch (IOException e) {
			System.out.println("Couldn't parse file: " + filename);
			e.printStackTrace();
		}		
		return listOfLists;
	}
	
	/**
	 * This method is for testing and debugging reasons
	 * @param resultLine
	 */
	private void printNicelyFromArray(String[] resultLine) {
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
	}
		
	public String getSeparator() {
		return separator;
	}
}
