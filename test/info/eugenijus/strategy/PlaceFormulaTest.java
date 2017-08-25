package info.eugenijus.strategy;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import info.eugenijus.model.Athlete;
import info.eugenijus.model.Result;

/**
 * 
 * @author Eugenijus
 *
 */
public class PlaceFormulaTest {
	private PlaceFormula placeFormula;

	@Before
	public void setUp() throws Exception {
		placeFormula = new PlaceFormula();
	}

	@After
	public void tearDown() throws Exception {
		placeFormula = null;
	}

	@Test
	public void testMarkPlaces() {
		/**
		 * @TODO Doesnt work with 3 athletes :(
		 */
		List<Athlete> athletes = new LinkedList<>();
		Result test9990Result = new Result();
		Result ashtonResult = new Result();
		Result ashtonResult2 = new Result();
		Result susiResults = new Result();
		//NAME, run100M, longJump, shotPutThrow, highJump, run400M, run110MHurdles, discusThrow, poleVaultJump, javelinThrow, run1500M
		String[] testArr = {"Athlete9990", "10.395", "7.76", "18.4", "2.20", "46.17", "13.8", "56.17", "5.28", "77.19", "3:53.79"};
		String[] ashtonArr = {"Ashton Eaton", "10.23", "7.88", "14.52", "2.01", "45.0", "13.69", "43.34", "5.2", "63.63", "4:17.52"};
		String[] susiArr = {"Siim Susi", "12.61", "5.00", "9.22", "1.50", "60.39", "16.43", "21.60", "2.60", "35.81", "5.25.72"};
		try {
			test9990Result.parseAndSet(testArr);
			ashtonResult.parseAndSet(ashtonArr);
			ashtonResult2.parseAndSet(ashtonArr);
			susiResults.parseAndSet(susiArr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		athletes.add(new Athlete("test9990Result", test9990Result));
		athletes.add(new Athlete("Ashton Eaton3", ashtonResult));
		athletes.add(new Athlete("Bashton Eaton", ashtonResult));
		athletes.add(new Athlete("Cashton Eaton2", ashtonResult2));
		athletes.add(new Athlete("Siim Susi", susiResults));
		//athletes.add(new Athlete("test9990Result2", test9990Result));
		
		placeFormula.markPlaces(athletes);
		
		for(Athlete athlete : athletes) {
			System.out.println(athlete.getName() 
					+ " has score: " + athlete.getResult().getTotalScore()
					+ " has place: " + athlete.getPlace());
		}
		
		String place1 = athletes.get(0).getPlace();
		String place2 = athletes.get(1).getPlace();
		String place3 = athletes.get(2).getPlace();
		String place4 = athletes.get(3).getPlace();
		String place5 = athletes.get(4).getPlace();
		
		System.out.println("places: " + place1 + ", " + place2 + ", " + place3 + ", " + place4 + ", " + place5);
		
		assertNotEquals(place1, place5);
		assertEquals("1", place1);
		assertEquals(place2, place3);
		assertEquals("2-3-4", place2);
		assertEquals(place3, place4);
		assertEquals("2-3-4", place3);
		assertEquals("5", place5);
	}

}
