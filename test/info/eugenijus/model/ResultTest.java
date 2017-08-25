/**
 * 
 */
package info.eugenijus.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Eugenijus
 *
 */
public class ResultTest {
	private Result resultAthlete9990;
	private Result resultAthleteAshton;
	private final String[] athlete9990 = {"Athlete9990", "10.395", "7.76", "18.4", "2.20", "46.17", "13.8", "56.17", "5.28", "77.19", "3:53.79"};
	private final String[] athleteAshton = {"Ashton Eaton", "10.23", "7.88", "14.52", "2.01", "45.0", "13.69", "43.34", "5.2", "63.63", "4:17.52"};
	
	@Before
	public void setUp() throws Exception {
		resultAthlete9990 = new Result(athlete9990);
		resultAthleteAshton = new Result(athleteAshton);
	}

	@After
	public void tearDown() throws Exception {
		resultAthlete9990 = null;
		resultAthleteAshton = null;
	}
	
	/**
	 * Test method for {@link info.eugenijus.model.Result#Result()}.
	 */
	@Test
	public void testResult() {
		Result result = new Result();
		assertNotNull(result);
		assertTrue(result.getTotalScore() == 0);
	}

	/**
	 * Test method for {@link info.eugenijus.model.Result#Result(java.lang.String[])}.
	 */
	@Test
	public void testResultStringArray() {
		assertTrue(resultAthlete9990 != null);
		assertTrue(resultAthleteAshton != null);
		
		assertTrue(7.76f == resultAthlete9990.getLongJump());
		assertEquals(9990, resultAthlete9990.getTotalScore());
	}

	/**
	 * Test method for {@link info.eugenijus.model.Result#setRun1500M(java.lang.String)}.
	 */
	@Test
	public void testSetRun1500MString() {
		String run1500m = "3:53.79";
		Result result = new Result();
		result.setRun1500M(run1500m);
		assertEquals(run1500m, result.getRun1500M().toString());
		assertTrue(233.79f == result.getRun1500M().getTimeInSeconds());
	}

	/**
	 * Test method for {@link info.eugenijus.model.Result#setRun1500M(info.eugenijus.model.CustomTime)}.
	 */
	@Test
	public void testSetRun1500MCustomTime() {
		String run1500m = "3:53.79";
		CustomTime time = new CustomTime(run1500m);
		Result result = new Result();
		result.setRun1500M(time);
		assertEquals(run1500m, result.getRun1500M().toString());
		assertTrue(233.79f == result.getRun1500M().getTimeInSeconds());
	}

	/**
	 * {run100M, run400M, run110MHurdles, run1500M.getTimeInSeconds()} <br/>
	 * Test method for {@link info.eugenijus.model.Result#getFourTrackEvents()}.
	 */
	@Test
	public void testGetFourTrackEvents() {
		float[] trackEvents = resultAthlete9990.getFourTrackEvents();
		assertEquals(4, trackEvents.length);
		assertTrue(trackEvents[0] == 10.395f);
		assertTrue(trackEvents[1] == 46.17f);
		assertTrue(trackEvents[2] == 13.8f);
		assertTrue(trackEvents[3] == 233.79f);
	}

	/**
	 * {longJump, shotPutThrow, highJump, discusThrow, poleVaultJump, javelinThrow} <br/>
	 * Test method for {@link info.eugenijus.model.Result#getSixFieldEvents()}.
	 */
	@Test
	public void testGetSixFieldEvents() {
		float[] fieldEvents = resultAthleteAshton.getSixFieldEvents();
		assertEquals(6, fieldEvents.length);
		assertTrue(fieldEvents[0] == 7.88f);
		assertTrue(fieldEvents[1] == 14.52f);
		assertTrue(fieldEvents[2] == 2.01f);
		assertTrue(fieldEvents[3] == 43.34f);
		assertTrue(fieldEvents[4] == 5.2f);
		assertTrue(fieldEvents[5] == 63.63f);
	}

	/**
	 * Test method for {@link info.eugenijus.model.Result#parseAndSet(java.util.List)}.
	 */
	@Test
	public void testParseAndSetListOfString() {
		Result test9990Result = new Result();
		String[] resultsArr = {"Athlete1000", "10.395", "7.76", "18.4", "2.20", "46.17", "13.8", "56.17", "5.28", "77.19", "3:53.79"};
		List<String> list = new ArrayList<>();
		for(String str : resultsArr) {
			list.add(str);
		}
		assertEquals(resultsArr.length, list.size());
		for(int i=0; i<resultsArr.length; i++) {
			assertEquals(resultsArr[i], list.get(i));
		}
		try {
			test9990Result.parseAndSet(list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(9990, test9990Result.getTotalScore());
	}

	/**
	 * Test method for {@link info.eugenijus.model.Result#parseAndSet(java.lang.String[])}.
	 */
	@Test
	public void testParseAndSetStringArray() {
		Result test9990Result = new Result();
		Result ashtonResult = new Result();
		//NAME, run100M, longJump, shotPutThrow, highJump, run400M, run110MHurdles, discusThrow, poleVaultJump, javelinThrow, run1500M
		String[] resultsArr = {"Athlete1000", "10.395", "7.76", "18.4", "2.20", "46.17", "13.8", "56.17", "5.28", "77.19", "3:53.79"};
		String[] ashton = {"Ashton Eaton", "10.23", "7.88", "14.52", "2.01", "45.0", "13.69", "43.34", "5.2", "63.63", "4:17.52"};
		try {
			test9990Result.parseAndSet(resultsArr);
			ashtonResult.parseAndSet(ashton);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		float[] trackEvents = ashtonResult.getFourTrackEvents();
		assertEquals(4, trackEvents.length);
		assertTrue(trackEvents[0] == 10.23f);
		assertTrue(trackEvents[1] == 45.0f);
		assertTrue(trackEvents[2] == 13.69f);
		assertTrue(trackEvents[3] == 257.52f);
		
		//{longJump, shotPutThrow, highJump, discusThrow, poleVaultJump, javelinThrow} 
		float[] fieldEvents = test9990Result.getSixFieldEvents();
		assertEquals(6, fieldEvents.length);
		assertTrue(fieldEvents[0] == 7.76f);
		assertTrue(fieldEvents[1] == 18.4f);
		assertTrue(fieldEvents[2] == 2.2f);
		assertTrue(fieldEvents[3] == 56.17f);
		assertTrue(fieldEvents[4] == 5.28f);
		assertTrue(fieldEvents[5] == 77.19f);
	}

	/**
	 * Test method for {@link info.eugenijus.model.Result#getTotalScore()}.
	 */
	@Test
	public void testGetTotalScore() {
		Result emptyResult = new Result();
		assertEquals(0, emptyResult.getTotalScore());
		assertEquals(9990, this.resultAthlete9990.getTotalScore());
		assertEquals(9045, this.resultAthleteAshton.getTotalScore());
	}

	/**
	 * Test method for {@link info.eugenijus.model.Result#compareTo(info.eugenijus.model.Result)}.
	 */
	@Test
	public void testCompareTo() {
		Result result1 = new Result();
		Result result2 = new Result();
		
		int score1 = result1.getTotalScore();
		int score2 = result2.getTotalScore();
		
		assertEquals(0, score1);
		assertEquals(score1, score2);
		assertEquals(0, result1.compareTo(result2));
		
		//System.out.println(score1 + "==" + score2);
		
		result1.setRun100M(10.395f); //1000pts
		result2.setRun100M(11.756f); //700pts
		
		assertEquals(1000, result1.getTotalScore());
		assertEquals(700, result2.getTotalScore());
		assertEquals(1, result1.compareTo(result2));
		assertEquals(-1, result2.compareTo(result1));
		
		//System.out.println(result1.getTotalScore() + ">" + result2.getTotalScore());
	}

	/**
	 * Test method for {@link info.eugenijus.model.Result#toString()}.
	 */
	@Test
	public void testToString() {
		Result emptyResult = new Result();
		System.out.println(emptyResult);
		assertEquals("\"score\": \"0\", \"results\": [\"0.0\", \"0.0\", \"0.0\", \"0.0\", \"0.0\", \"0.0\", \"0.0\", \"0.0\", \"0.0\", \"0:0.0\"]"
				, emptyResult.toString());
		
		System.out.println(this.resultAthlete9990);
		assertEquals("\"score\": \"9990\", \"results\": [\"10.395\", \"7.76\", \"18.4\", \"2.2\", \"46.17\", \"13.8\", \"56.17\", \"5.28\", \"77.19\", \"3:53.79\"]"
				, this.resultAthlete9990.toString());
		
		System.out.println(this.resultAthleteAshton);
		assertEquals("\"score\": \"9045\", \"results\": [\"10.23\", \"7.88\", \"14.52\", \"2.01\", \"45.0\", \"13.69\", \"43.34\", \"5.2\", \"63.63\", \"4:17.52\"]"
				, this.resultAthleteAshton.toString());
	}

}
