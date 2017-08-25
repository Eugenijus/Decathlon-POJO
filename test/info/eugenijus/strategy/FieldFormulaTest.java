package info.eugenijus.strategy;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import info.eugenijus.model.Constants;

public class FieldFormulaTest {
	private FieldFormula fieldFormula;

	@Before
	public void setUp() throws Exception {
		fieldFormula = new FieldFormula(true);
	}

	@After
	public void tearDown() throws Exception {
		fieldFormula = null;
	}

	@Test
	public void testCalculateScore() {
		float[] trackResults = {0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f};
		int result = fieldFormula.calculateScore(trackResults);
		assertTrue(result == 0);
		
		//BASED ON BENCHMARK TABLE FROM: https://en.wikipedia.org/wiki/Decathlon#Points_system
		//{longJump, shotPutThrow, highJump, discusThrow, poleVaultJump, javelinThrow}
		float[] fieldResults700 = {6.51f, 13.53f, 1.88f, 41.72f, 4.29f, 57.45f};
		//Looking at the table 700 * 6 = 4200 - but actually its 4195.
		int result4195 = fieldFormula.calculateScore(fieldResults700);
		int actualResults = 700+700+696+700+699+700;
		assertTrue(result4195 == actualResults);
	}

	@Test
	public void testCalculatePerEvent() {
		//should return 900
		int singleResult = fieldFormula.calculateScorePerEvent(Constants.FIELD_JAVELIN_THROW, 70.67f);
		assertTrue(singleResult == 900);
	}

}
