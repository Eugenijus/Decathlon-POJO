package info.eugenijus;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import info.eugenijus.model.Athlete;
import info.eugenijus.model.Constants;
import info.eugenijus.model.CustomTime;
import info.eugenijus.model.Result;
import info.eugenijus.strategy.FieldFormula;
import info.eugenijus.strategy.PlaceFormula;
import info.eugenijus.strategy.TrackFormula;
import info.eugenijus.utils.DocumentWriter;
import info.eugenijus.utils.SSVParser;
import info.eugenijus.utils.TxtWriter;

/**
 * Tutorials used:
 * https://www.mkyong.com/java/how-to-read-and-parse-csv-file-in-java/
 * http://www.theregister.co.uk/Design/page/hub/ibm2016/#five
 * 
 * @author Eugenijus Sabaliauskas
 */
public class DecathlonMain {
	
	private void printList(List<String> list) {
		for (String str : list) {
			System.out.println(str);
		}
	}	
	
	/**
	 * Prints athlete's name and results
	 * @param resultsList - athlete's name and list of results of a single athlete
	 * @return athlete - Athlete object
	 */
	private Athlete printListNicely(List<String> resultsList) {
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
		return athlete;
	}
	
	public static void main(String[] args) {
		final String FOLDER = "test-data/";
		List<Athlete> athletes = new LinkedList<>();
		boolean testFileRead = true;
		
		if(testFileRead) {
			DecathlonMain deca = new DecathlonMain();
			SSVParser parser = new SSVParser();
			
			System.out.println("========== Printing while parsing ============");
			List<String> listOfResults = parser.parseDocument(FOLDER + "Decathlon_input.txt");
			
			System.out.println("========== Printing from a list ========================");
			deca.printList(listOfResults);
			
			System.out.println("========== Parsing from file to List<Athlete> ============");
			List<Athlete> listOfAthletes = parser.parseDocumentToAthletes(FOLDER + "Decathlon_input.txt");
			athletes.addAll(listOfAthletes);
			
			System.out.println("========== Advanced printing from a list ==========================");
			List<List<String>> listOfLists = parser.parseDocumentToLists(FOLDER + "Decathlon_input.txt");
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
		Result ashtonResult = new Result();
		//NAME, run100M, longJump, shotPutThrow, highJump, run400M, run110MHurdles, discusThrow, poleVaultJump, javelinThrow, run1500M
		String[] resultsArr = {"Athlete1000", "10.395", "7.76", "18.4", "2.20", "46.17", "13.8", "56.17", "5.28", "77.19", "3:53.79"};
		String[] ashton = {"Ashton Eaton", "10.23", "7.88", "14.52", "2.01", "45.0", "13.69", "43.34", "5.2", "63.63", "4:17.52"};
		try {
			test1000Result.parseAndSet(resultsArr);
			ashtonResult.parseAndSet(ashton);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(test1000Result.toString());
		System.out.println("test1000Result: " + test1000Result.getTotalScore());
		//System.out.println("new Float(5.74352f) " + new Float(5.74352f));
		System.out.println();
		System.out.println(ashtonResult.toString());
		System.out.println("ashtonResult: " + ashtonResult.getTotalScore());
		System.out.println();
		System.out.println("longJump of 60.4M: " + ff.calculatePerEvent(Constants.FIELD_LONG_JUMP, 6.9f));
		System.out.println("javelinThrow of 690cm: " + ff.calculatePerEvent(Constants.FIELD_JAVELIN_THROW, 60.4f));
		
		
		
		athletes.add(new Athlete("test1000Result", test1000Result));
		athletes.add(new Athlete("test9990Result", test1000Result));
		athletes.add(new Athlete("Ashton Eaton", ashtonResult));
		athletes.add(new Athlete("Ashton Eaton2", ashtonResult));
		PlaceFormula placement = new PlaceFormula();
		placement.markPlaces(athletes);
		TxtWriter writer = new TxtWriter(Constants.TEST_FOLDER);
		writer.writeToFile("Decathlon_output.txt", athletes);
		
	}

}
