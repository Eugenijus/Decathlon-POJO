/**
 * 
 */
package info.eugenijus.utils;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import info.eugenijus.model.Athlete;
import info.eugenijus.model.Constants;
import info.eugenijus.strategy.PlaceFormula;

/**
 * @author Eugenijus
 *
 */
public class HTMLWriterTest {
	private Exception exc;
	private String testFileName = "z_superSecretTest.txt";
	private XMLWriterTest xmlWriter;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		xmlWriter = new XMLWriterTest();
		xmlWriter.setUp();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		xmlWriter.tearDown();
	}

	/**
	 * Test method for {@link info.eugenijus.utils.HTMLWriter#HTMLWriter()}.
	 */
	@Test
	public void testHTMLWriter() {
		HTMLWriter writer = new HTMLWriter();
		assertEquals("", writer.getFolder());
	}

	/**
	 * Test method for {@link info.eugenijus.utils.HTMLWriter#HTMLWriter(java.lang.String)}.
	 */
	@Test
	public void testHTMLWriterString() {
		HTMLWriter writer = new HTMLWriter("test-folder/");
		assertEquals("test-folder/", writer.getFolder());
	}

	/**
	 * Test method for {@link info.eugenijus.utils.HTMLWriter#convertXMLtoHTML(java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testConvertXMLtoHTML() {
		String xmlFilename = "junit-test/" + "junit_test_output.xml";
		String xslFilename = Constants.STYLE_FOLDER + Constants.XSL_STYLESHEET;
		String htmlFilename = "junit-test/" + "output.html";
		
		//2) parsing a test file
		SSVParser ssvParser = new SSVParser();
		List<Athlete> athletes = ssvParser.parseDocumentToAthletes(Constants.TEST_FOLDER + this.testFileName);
		
		PlaceFormula placement = new PlaceFormula();
		placement.markPlaces(athletes);
		
		//3) writing a test file as XML		
		XMLWriter xmlWriter = new XMLWriter();
		xmlWriter.setStylesheetFile(xslFilename);
		assertTrue(xmlWriter.writeToFile(Constants.TEST_FOLDER + xmlFilename, athletes));
				
		try {
			//4 writing test file as HTML
			HTMLWriter htmlWriter = new HTMLWriter(Constants.TEST_FOLDER);
			htmlWriter.convertXMLtoHTML(xmlFilename, xslFilename, htmlFilename);
		} catch (Exception e1) {
			exc = e1;
			e1.printStackTrace();
		}		
		assertEquals(null, exc);
		
		//5 read html file as text file and double check
		List<String> list = new ArrayList<>();
		String line = "";
		try (BufferedReader br = new BufferedReader(new FileReader(Constants.TEST_FOLDER + htmlFilename))) {
			while ((line = br.readLine()) != null) {
				line = line.trim();
				//System.out.println(line);
				list.add(line);
			}
		} catch (IOException e1) {
			exc = e1;
			System.out.println("Couldn't read file: " + Constants.TEST_FOLDER + htmlFilename);
			e1.printStackTrace();
		}
		assertEquals(null, exc);
		
		assertTrue(list.get(0).contains("<html>"));
		assertTrue(list.get(2).contains("<h2>Decathlon Results</h2>"));
		assertTrue(list.get(5).contains("<th>run100M</th>"));
		assertTrue(list.get(8).contains("<td>Siim Susi</td>"));
		assertTrue(list.get(8).contains("<td>5:25.72</td>"));
	}

}
