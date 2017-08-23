package info.eugenijus;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import info.eugenijus.model.Athlete;
import info.eugenijus.model.Constants;
import info.eugenijus.model.CustomTime;
import info.eugenijus.model.Result;
import info.eugenijus.strategy.FieldFormula;
import info.eugenijus.strategy.TrackFormula;

/**
 * Tutorials used:
 * https://www.mkyong.com/java/how-to-read-and-parse-csv-file-in-java/
 * http://www.theregister.co.uk/Design/page/hub/ibm2016/#five
 * 
 * @author Eugenijus Sabaliauskas
 */
public class DecathlonMain {
	private static final char QUOTE = '"';

	public List<String> parseDocument(String filename) {
		List<String> list = new ArrayList<>();
		String line = "";

		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			while ((line = br.readLine()) != null) {
				line = line.trim();
				String[] resultLine = line.split(Constants.SPLITTER);
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
				String[] resultLine = line.split(Constants.SPLITTER);
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
		try{
			result.parseAndSet(resultsList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		athlete.setResult(result);
		System.out.println(athlete.toString());
		
	}
	
	public static void main(String[] args) {
		final String FOLDER = "test-data/";
		boolean testFileRead = false;
		if(testFileRead) {
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
		}
		System.out.println("========== Testing CustomTime parsing for minutes and seconds ==========================");
		CustomTime ct1 = new CustomTime("5.25.72");
		System.out.println(ct1.toString());
		CustomTime ct2 = new CustomTime("5.25");
		System.out.println(ct2.toString());
		CustomTime ct3 = new CustomTime("4.00.");
		System.out.println(ct3.toString());
		
		CustomTime ct4 = new CustomTime("2.10.123");
		System.out.println(ct4.getTimeInSeconds());
		CustomTime ct5 = new CustomTime("3:53.79");
		System.out.println(ct5.getTimeInSeconds());
		
		//233 = 3:53.79
		float time = (3*60)+53.79f;
		System.out.println("time: " + time);
		int result1000 = (int)Math.floor(0.03768f *  Math.pow((480-time), 1.85) );
		System.out.println("Should be 1000: " + result1000);
		
		TrackFormula tf = new TrackFormula();
		float[] eventResults = {11.756f, 52.58f, 16.29f, (4*60)+36.96f};
		//each event should have score of 700 and total of 2800
		System.out.println(tf.calculateScore(eventResults));
		
		FieldFormula ff = new FieldFormula();
		//float[] sixFieldTimes = {longJump, shotPutThrow, highJump, discusThrow, poleVaultJump, javelinThrow};
		float[] eventResults2 = {6.94f, 15.16f, 1.99f, 46.59f, 4.63f, 64.09f};
		//each event should have score of 800 and total of 4800
		System.out.println(ff.calculateScore(eventResults2));
		
		Result test1000Result = new Result();
		//NAME, run100M, longJump, shotPutThrow, highJump, run400M, run110MHurdles, discusThrow, poleVaultJump, javelinThrow, run1500M
		String[] resultsArr = {"Athlete1000", "10.395", "7.76", "18.4", "2.20", "46.17", "13.8", "56.17", "5.28", "77.19", "3:53.79"};
		try {
			test1000Result.parseAndSet(resultsArr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(test1000Result.toString());
		System.out.println("test1000Result: " + test1000Result.getTotalScore());
		//System.out.println("new Float(5.74352f) " + new Float(5.74352f));
		System.out.println();
		System.out.println("longJump of 60.4M: " + ff.calculatePerEvent(Constants.FIELD_LONG_JUMP, 6.9f));
		System.out.println("javelinThrow of 690cm: " + ff.calculatePerEvent(Constants.FIELD_JAVELIN_THROW, 60.4f));
	}

}
