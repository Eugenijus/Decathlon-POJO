package info.eugenijus.model;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import info.eugenijus.strategy.PlaceFormula;

/**
 * 
 * @author Eugenijus
 *
 */
public class AthleteTest {
	
	private Athlete athlete;
	private final String[] athlete9990 = {"Athlete9990", "10.395", "7.76", "18.4", "2.20", "46.17", "13.8", "56.17", "5.28", "77.19", "3:53.79"};
	private final String[] athleteAshton = {"Ashton Eaton", "10.23", "7.88", "14.52", "2.01", "45.0", "13.69", "43.34", "5.2", "63.63", "4:17.52"};
	
	@Before
	public void setUp() throws Exception {
		athlete = new Athlete();
	}

	@After
	public void tearDown() throws Exception {
		athlete = null;
	}

	@Test
	public void testAthlete() {
		Athlete testConstructor = new Athlete();
		assertTrue("N/A".equals(testConstructor.getName()));
		assertNull(testConstructor.getResult());
		assertTrue("DNF".equals(testConstructor.getPlace()));
	}

	@Test
	public void testAthleteStringResult() {
		String name = "name";
		Result result = new Result();
		Athlete testConstructor = new Athlete(name, result);
		assertTrue("name".equals(testConstructor.getName()));
		assertNotNull(testConstructor.getResult());
		assertTrue("DNF".equals(testConstructor.getPlace()) );
	}

	@Test
	public void testGetName() {
		athlete.setName("name");
		assertTrue("name".equals(athlete.getName()));		
	}

	@Test
	public void testSetName() {
		athlete.setName("anotherName");
		assertTrue("anotherName".equals(athlete.getName()));	
	}

	@Test
	public void testGetResult() {
		Result result = new Result();
		athlete.setResult(result);
		assertTrue(athlete.getResult() == result);
	}

	@Test
	public void testSetResult() {
		Result result = new Result();
		athlete.setResult(result);
		assertTrue(athlete.getResult() == result);
	}

	@Test
	public void testSetPlace() {
		athlete.setPlace("1-2");
		assertTrue(athlete.getPlace() == "1-2");
	}

	@Test
	public void testGetPlace() {
		athlete.setPlace("3");
		assertTrue(athlete.getPlace() == "3");
	}

	@Test
	public void testCompareTo() {
		try {
			Result result1 = new Result();
			result1.parseAndSet(this.athlete9990);
			Athlete athlete1 = new Athlete("Athlete9990", result1);
			
			Result result2 = new Result();
			result2.parseAndSet(this.athleteAshton);
			Athlete athlete2 = new Athlete("Ashton Eaton", result2);
			
			assertTrue(athlete1.getResult().getTotalScore() == 9990);
			assertTrue(athlete2.getResult().getTotalScore() == 9045);
			assertTrue(athlete1.compareTo(athlete2) == 1);
			assertTrue(athlete2.compareTo(athlete1) == -1);
			assertTrue(athlete1.compareTo(athlete1) == 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testToString() {
		try {
			Result result1 = new Result();
			result1.parseAndSet(this.athlete9990);
			Athlete athlete1 = new Athlete("Athlete9990", result1);
			PlaceFormula placeFormula = new PlaceFormula();
			List<Athlete> list = new LinkedList<Athlete>();
			list.add(athlete1);
			placeFormula.markPlaces(list);
			
			System.out.println(athlete1.toString());
			String testStr1 = "{\"name\": \"Athlete9990\", \"place\": \"1\", \"score\": \"9990\", \"results\": [\"10.395\", \"7.76\", \"18.4\", \"2.2\", \"46.17\", \"13.8\", \"56.17\", \"5.28\", \"77.19\", \"3:53.79\"]}";
			assertTrue(athlete1.toString().equals(testStr1));
			
			String testStr3 = "{\"name\": \"Empty\", \"place\": \"DNF\", \"score\": \"0\", \"results\": [\"0.0\", \"0.0\", \"0.0\", \"0.0\", \"0.0\", \"0.0\", \"0.0\", \"0.0\", \"0.0\", \"0:0.0\"]}";
			Athlete athlete3 = new Athlete("Empty", new Result());
			System.out.println(athlete3.toString());
			assertTrue(athlete3.toString().equals(testStr3));
			
			String testStr4 = "{\"name\": \"N/A\", \"place\": \"DNF\", null}";
			Athlete athlete4 = new Athlete();
			System.out.println(athlete4.toString());
			assertTrue(athlete4.toString().equals(testStr4));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}
