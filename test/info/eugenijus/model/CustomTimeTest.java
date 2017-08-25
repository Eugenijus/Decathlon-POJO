package info.eugenijus.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CustomTimeTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test constructor for {@link info.eugenijus.model.CustomTime#CustomTime()}.
	 */
	@Test
	public void testCustomTime() {
		CustomTime ct = new CustomTime();
		assertTrue(ct.getTimeInSeconds() == 0.0f);
	}

	/**
	 * Test constructor for {@link info.eugenijus.model.CustomTime#CustomTime(int, float)}.
	 */
	@Test
	public void testCustomTimeIntFloat() {
		CustomTime ct = new CustomTime(0, 0.0f);
		assertTrue(ct.getTimeInSeconds() == 0.0f);
		System.out.println("Testing: " + ct.getTimeInSeconds());
		
		ct = new CustomTime(1, 4.321f);
		float time = (1*60)+4.321f; //64.321f
		System.out.println("Testing: " + ct.getTimeInSeconds());
		assertTrue(ct.getTimeInSeconds() == time);
	}
	
	@Test
	public void testCustomTimeString() {
		CustomTime ct1 = new CustomTime("5.25.72");
		CustomTime ct2 = new CustomTime("5.25");
		CustomTime ct3 = new CustomTime("4.00.");
		CustomTime ct4 = new CustomTime("3:53.79");
		
		assertTrue(ct1.getTimeInSeconds() == 325.72f);
		assertTrue(ct2.getTimeInSeconds() == 325.0f);
		assertTrue(ct3.getTimeInSeconds() == 240.0f);
		assertTrue(ct4.getTimeInSeconds() == 233.79f);
	}
	
	@Test
	public void testGetTimeInSeconds() {
		CustomTime ct = new CustomTime(0, 0.0f);
		assertTrue(ct.getTimeInSeconds() == 0.0f);
		System.out.println("Testing: " + ct.getTimeInSeconds());
		
		ct = new CustomTime(1, 4.321f);
		float time = (1*60)+4.321f; //64.321f
		System.out.println("Testing: " + ct.getTimeInSeconds());
		assertTrue(ct.getTimeInSeconds() == time);
		
		ct = new CustomTime(0, 1.321f);
		time = (0*60)+1.321f; //1.321f
		System.out.println("Testing: " + ct.getTimeInSeconds());
		assertTrue(ct.getTimeInSeconds() == 1.321f);
		
		ct = new CustomTime(4, 0.0f);
		time = (4*60)+0.0f; //240.0f
		System.out.println("Testing: " + ct.getTimeInSeconds());
		assertTrue(ct.getTimeInSeconds() == time);
		
		/**
		 * this one was good test, because before it would give me 233.790001 :)
		 * so I ended up upgrading my CustomTime#getTimeInSeconds() method
		 */
		ct = new CustomTime(3, 53.79f);
		time = (3*60)+53.79f; //233.79f
		System.out.println("Testing: " + ct.getTimeInSeconds());
		assertTrue(ct.getTimeInSeconds() == 233.79f);
	}

	@Test
	public void testToString() {
		CustomTime ct1 = new CustomTime("5.25.72");
		CustomTime ct2 = new CustomTime("5.25");
		CustomTime ct3 = new CustomTime("4:00.");
		CustomTime ct4 = new CustomTime("3:53.79");
		CustomTime ct5 = new CustomTime("0.0");
		CustomTime ct6 = new CustomTime("0");
		
		assertEquals(ct1.toString(), "5:25.72");
		assertEquals(ct2.toString(), "5:25.0");
		assertEquals(ct3.toString(), "4:0.0");
		assertEquals(ct4.toString(), "3:53.79");
		assertEquals(ct5.toString(), "0:0.0");
		assertEquals(ct6.toString(), "0:0.0");
	}

}
