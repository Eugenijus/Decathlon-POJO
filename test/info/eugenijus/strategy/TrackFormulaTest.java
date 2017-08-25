package info.eugenijus.strategy;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import info.eugenijus.model.Constants;
import info.eugenijus.model.CustomTime;

/**
 * 
 * @author Eugenijus
 *
 */
public class TrackFormulaTest {
	private TrackFormula trackFormula;

	@Before
	public void setUp() throws Exception {
		trackFormula = new TrackFormula(true);
	}
	
	@After
	public void tearDown() throws Exception {
		trackFormula = null;
	}
	
	/**
	 * BASED ON BENCHMARK TABLE FROM: https://en.wikipedia.org/wiki/Decathlon#Points_system
	 * {run100M, run400M, run110MHurdles, run1500M.getTimeInSeconds()}
	 */
	@Test
	public void testCalculateScore() {
		float[] trackResults = {0.0f, 0.0f, 0.0f, 0.0f};
		int result = trackFormula.calculateScore(trackResults);
		assertTrue(result == 0);
		
		CustomTime run1500M = new CustomTime("4:21.77");
		float[] trackResults800 = {11.278f, 50.32f, 15.419f, run1500M.getTimeInSeconds()};
		//800 * 4 = 3200
		int result3200 = trackFormula.calculateScore(trackResults800);
		assertTrue(result3200 == 3200);
		
		float[] eventResults = {11.756f, 52.58f, 16.29f, (4*60)+36.96f};
		//each event should have score of 700 and total of 2800
		int result2800 = trackFormula.calculateScore(eventResults);
		assertEquals(2800, result2800);
	}

	@Test
	public void testCalculatePerEvent() {
		//should return 800
		int singleResult = trackFormula.calculateScorePerEvent(Constants.TRACK_100M, 11.278f);
		assertTrue(singleResult == 800);
	}

}
