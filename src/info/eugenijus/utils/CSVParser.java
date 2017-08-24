package info.eugenijus.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import info.eugenijus.model.Constants;

/**
 * Tutorials used:
 * https://www.mkyong.com/java/how-to-read-and-parse-csv-file-in-java/
 * 
 * @author Eugenijus Sabaliauskas
 */
public class CSVParser implements DocumentParser {
	
	private String separator = Constants.COMMA;
	
	public CSVParser() {
		
	}

	public CSVParser(String separator) {
		this.separator = separator;
	}
	
	public String getSeparator() {
		return separator;
	}

	public void setSeparator(String separator) {
		this.separator = separator;
	}

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
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
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
		} catch (IOException e) {
			e.printStackTrace();
		}
		return listOfLists;
	}
	
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
}
