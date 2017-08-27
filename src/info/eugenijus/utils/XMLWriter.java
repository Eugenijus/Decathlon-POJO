package info.eugenijus.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.ProcessingInstruction;

import info.eugenijus.model.Athlete;
import info.eugenijus.model.Constants;
import info.eugenijus.model.Result;

public class XMLWriter implements DocumentWriter {
	private String folder;
	private String stylesheetFile;
	
	public XMLWriter() {
		folder = "";
		stylesheetFile = Constants.STYLE_FOLDER + Constants.XSL_STYLESHEET; //default stylesheet
	}
	
	/**
	 * If you want to specify your own folder then use this constructor,
	 * but make sure filename is just a file name, not the full path
	 * @param folder
	 */
	public XMLWriter(String folder) {
		this.folder = folder;
		stylesheetFile = Constants.STYLE_FOLDER + Constants.XSL_STYLESHEET; //default stylesheet
	}
	
	public XMLWriter(String folder, String styleSheet) {
		this.folder = folder;
		this.stylesheetFile = styleSheet;
	}
	
	/**
	 * Based on tutorial and material:
	 * http://www.mkyong.com/java/how-to-create-xml-file-in-java-dom/
	 * https://www.w3schools.com/xml/xsl_intro.asp
	 * https://www.w3schools.com/xml/xml_validator.asp
	 */
	@Override
	public boolean writeToFile(String filename, List<Athlete> athletesList) {
		boolean isSuccess = false;		
		if(athletesList.size() < 1) {
			return false;
		}
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(folder + filename))) {
			try {
				DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
				// root element
				Document doc = docBuilder.newDocument();
				Element rootElement = doc.createElement("athletes");
				if(stylesheetFile.length() > 1) {
					doc.setXmlStandalone(true);
					ProcessingInstruction processingInstruction = doc.createProcessingInstruction("xml-stylesheet", 
							"type=\"text/xsl\" href=\"" + stylesheetFile + "\"");
					doc.appendChild(rootElement);					
					doc.insertBefore(processingInstruction, rootElement);
				} else {
					doc.appendChild(rootElement);
				}
				
				/* now lets iterate through athletes and their results */
				for(Athlete athlete : athletesList) {
					// single athlete
					Element athleteElement = doc.createElement("athlete");
					rootElement.appendChild(athleteElement);
					
					Result resultsObj = athlete.getResult();
					addElement(doc, athleteElement, "place", athlete.getPlace());
					addElement(doc, athleteElement, "name", athlete.getName());
					addElement(doc, athleteElement, "score", "" + resultsObj.getTotalScore());
										
					Element resultsElement = doc.createElement("results");
					athleteElement.appendChild(resultsElement);
					
					/* the order of elements in <results> will be the same as in input
					 * run100M, longJump, shotPutThrow, highJump, run400M, 
					 * run110MHurdles, discusThrow, poleVaultJump, javelinThrow, run1500M
					 */
					//1-10
					addElement(doc, resultsElement, "run100M", "" + resultsObj.getRun100M());
					addElement(doc, resultsElement, "longJump", "" + resultsObj.getLongJump());
					addElement(doc, resultsElement, "shotPutThrow", "" + resultsObj.getShotPutThrow());
					addElement(doc, resultsElement, "highJump", "" + resultsObj.getHighJump());
					addElement(doc, resultsElement, "run400M", "" + resultsObj.getRun400M());
					addElement(doc, resultsElement, "run110MHurdles", "" + resultsObj.getRun110MHurdles());
					addElement(doc, resultsElement, "discusThrow", "" + resultsObj.getDiscusThrow());
					addElement(doc, resultsElement, "poleVaultJump", "" + resultsObj.getPoleVaultJump());
					addElement(doc, resultsElement, "javelinThrow", "" + resultsObj.getJavelinThrow());
					addElement(doc, resultsElement, "run1500M", "" + resultsObj.getRun1500M());
				}
				// write the content into xml file
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				transformer.setOutputProperty(OutputKeys.INDENT, "yes");
				transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
				DOMSource sourceDOM = new DOMSource(doc);
				StreamResult resultXML = new StreamResult(new File(filename));
				transformer.transform(sourceDOM, resultXML);
				
			} catch (ParserConfigurationException e1) {
				e1.printStackTrace();
			} catch (TransformerException tfe) {
				tfe.printStackTrace();
			}
			System.out.println("Done writing XML to: " + folder + filename);
			isSuccess = true;
		} catch (IOException e) {
			isSuccess = false;
			System.out.println("Couldn't write XML to: " + folder + filename);
			e.printStackTrace();
		} catch (Exception e) {
			isSuccess = false;
			System.out.println("Couldn't write XML to: " + folder + filename);
			e.printStackTrace();
		}
		return isSuccess;
	}

	private void addElement(Document doc, Element parentElement, String elementName, String value) {
		Element element = doc.createElement(elementName);
		element.appendChild(doc.createTextNode(value));
		parentElement.appendChild(element);
	}
	
	public String getStylesheetFile() {
		return stylesheetFile;
	}

	public void setStylesheetFile(String stylesheetFile) {
		this.stylesheetFile = stylesheetFile;
	}

	public String getFolder() {
		return folder;
	}

	public void setFolder(String folder) {
		this.folder = folder;
	}
}
