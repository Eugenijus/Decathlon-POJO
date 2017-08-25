/**
 * 
 */
package info.eugenijus.utils;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import info.eugenijus.model.Athlete;
import info.eugenijus.model.Constants;

/**
 * @author Eugenijus
 *
 */
public class CSVParserTest {
	private String testFileName = "z_superSecretTest.txt";
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(Constants.TEST_FOLDER + testFileName))) {
			StringBuilder builder = new StringBuilder();
			builder.append("Siim Susi,12.61,5.00,9.22,1.50,60.39,16.43,21.60,2.60,35.81,5.25.72").append('\r').append('\n');
			builder.append("Beata Kana,13.04,4.53,7.79,1.55,64.72,18.74,24.20,2.40,28.20,6.50.76 ").append('\r').append('\n');
			bw.write(builder.toString());
			//System.out.println(testFileName + " created!");
		} catch (IOException e) {
			System.out.println("Couldn't write file: " + testFileName);
			e.printStackTrace();
		}
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		//System.out.println("TEARDOWN!");
		try {
			File file = new File(Constants.TEST_FOLDER + testFileName);
			if(file.exists()) {
				file.delete();
				//System.out.println(testFileName + " deleted!");
			} else {
				System.out.println("Couldn't delete file: " + testFileName);
			}
		} catch (Exception e) {
			System.out.println("Couldn't delete file: " + testFileName);
			e.printStackTrace();
		}
	}

	/**
	 * Test method for {@link info.eugenijus.utils.CSVParser#CSVParser()}.
	 */
	@Test
	public void testCSVParser() {
		CSVParser parser = new CSVParser();
		assertEquals(",", parser.getSeparator());
	}

	/**
	 * Test method for {@link info.eugenijus.utils.CSVParser#parseDocument(java.lang.String)}.
	 */
	@Test
	public void testParseDocument() {
		CSVParser parser = new CSVParser();
		List<String> list = parser.parseDocument(Constants.TEST_FOLDER + this.testFileName);
		assertEquals(2, list.size());
		assertTrue(list.get(0).contains("Siim Susi"));
		assertTrue(list.get(1).contains("13.04"));
	}

	/**
	 * Test method for {@link info.eugenijus.utils.CSVParser#parseDocumentToAthletes(java.lang.String)}.
	 */
	@Test
	public void testParseDocumentToAthletes() {
		CSVParser parser = new CSVParser();
		List<Athlete> list = parser.parseDocumentToAthletes(Constants.TEST_FOLDER + this.testFileName);
		assertEquals(2, list.size());
		assertEquals("Siim Susi", list.get(0).getName());
		assertTrue(13.04f == list.get(1).getResult().getRun100M());
	}

}
