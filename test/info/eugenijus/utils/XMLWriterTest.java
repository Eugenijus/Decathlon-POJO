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

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import info.eugenijus.model.Athlete;
import info.eugenijus.model.Constants;

/**
 * @author Eugenijus
 *
 */
public class XMLWriterTest {
	private Exception exc;
	private String testFileName = "z_superSecretTest.txt";
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		//1) creating a test file
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(Constants.TEST_FOLDER + testFileName))) {
			StringBuilder builder = new StringBuilder();
			builder.append("Siim Susi;12.61;5.00;9.22;1.50;60.39;16.43;21.60;2.60;35.81;5.25.72").append('\r').append('\n');
			builder.append("Beata Kana;13.04;4.53;7.79;1.55;64.72;18.74;24.20;2.40;28.20;6.50.76 ").append('\r').append('\n');
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
	 * Test method for {@link info.eugenijus.utils.XMLWriter#XMLWriter()}.
	 */
	@Test
	public void testXMLWriter() {
		XMLWriter writer = new XMLWriter();
		assertEquals("", writer.getFolder());
		assertEquals("style.xsl", writer.getStylesheetFile());
	}

	/**
	 * Test method for {@link info.eugenijus.utils.XMLWriter#XMLWriter(java.lang.String)}.
	 */
	@Test
	public void testXMLWriterString() {
		XMLWriter writer = new XMLWriter("test-folder");
		assertEquals("test-folder", writer.getFolder());
		assertEquals("style.xsl", writer.getStylesheetFile());
	}

	/**
	 * Test method for {@link info.eugenijus.utils.XMLWriter#XMLWriter(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testXMLWriterStringString() {
		XMLWriter writer = new XMLWriter("test-folder", "stylesheet.xsl");
		assertEquals("test-folder", writer.getFolder());
		assertEquals("stylesheet.xsl", writer.getStylesheetFile());
	}

	/**
	 * The most important method of XMLWriter. 
	 * Testing is done using javax.xml.validation.Validator <br/>
	 * XSD file generated from XML using: {@link javax.xml.validation.Validator} <br/>
	 * Used code from wtih my modifications: https://docs.oracle.com/javase/7/docs/api/javax/xml/validation/package-summary.html <br/>
	 * Test method for {@link info.eugenijus.utils.XMLWriter#writeToFile(java.lang.String, java.util.List)}.
	 */
	@Test
	public void testWriteToFile() {
		String xmlFile = "junit_test_output.xml";
		String xsdFile = "output.xml.xsd";
		String xslFilename = Constants.STYLESHEET;
		
		//2) parsing a test file
		SSVParser ssvParser = new SSVParser();
		List<Athlete> athletes = ssvParser.parseDocumentToAthletes(Constants.TEST_FOLDER + this.testFileName);
		
		//3) writing a test file as XML		
		XMLWriter xmlWriter = new XMLWriter();
		xmlWriter.setStylesheetFile(xslFilename);
		assertTrue(xmlWriter.writeToFile(Constants.TEST_XML_FOLDER + xmlFile, athletes));
		
		//4) read XML document and validate it
		// parse an XML document into a DOM tree
	    DocumentBuilder parser;
		try {
			parser = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document document = parser.parse(new File(Constants.TEST_XML_FOLDER + xmlFile));
			assertNotNull(document);
			
		    // create a SchemaFactory capable of understanding WXS schemas
		    SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		    assertNotNull(factory);
		    
		    // load a WXS schema, represented by a Schema instance
		    Source schemaFile = new StreamSource(new File(Constants.XSD_FOLDER + xsdFile));
		    assertNotNull(schemaFile);
		    Schema schema = factory.newSchema(schemaFile);
		    assertNotNull(schema);
	
		    // create a Validator instance, which can be used to validate an instance document
		    Validator validator = schema.newValidator();
		    assertNotNull(validator);

	    	// validate the DOM tree
	        validator.validate(new DOMSource(document));
	    } catch (ParserConfigurationException e1) {
			exc = e1;
			e1.printStackTrace();
		} catch (SAXException | IOException e1) {
			exc = e1;
			e1.printStackTrace();
		}
		assertEquals(null, exc);
	}
}
