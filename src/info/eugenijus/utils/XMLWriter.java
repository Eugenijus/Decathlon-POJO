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
import info.eugenijus.model.Result;

public class XMLWriter implements DocumentWriter {
	private String folder;
	private String stylesheetFile;
	
	public XMLWriter() {
		folder = "";
		stylesheetFile = "";
	}
	
	/**
	 * If you want to specify your own folder then use this constructor,
	 * but make sure filename is just a file name, not the full path
	 * @param folder
	 */
	public XMLWriter(String folder) {
		this.folder = folder;
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
	public boolean writeToFile(String filename, List<Athlete> athleteList) {
		boolean isSuccess = false;		
		if(athleteList.size() < 1) {
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
				for(Athlete athlete : athleteList) {
					// single athlete
					Element athleteElement = doc.createElement("athlete");
					rootElement.appendChild(athleteElement);
					
					Element place = doc.createElement("place");
					place.appendChild(doc.createTextNode(athlete.getPlace()));
					athleteElement.appendChild(place);
					
					Element name = doc.createElement("name");
					name.appendChild(doc.createTextNode(athlete.getName()));
					athleteElement.appendChild(name);
					
					Result resultsObj = athlete.getResult();
					
					Element score = doc.createElement("score");
					score.appendChild(doc.createTextNode("" + resultsObj.getTotalScore()));
					athleteElement.appendChild(score);
					
					Element resultsElement = doc.createElement("results");
					athleteElement.appendChild(resultsElement);
					
					/* the order of elements in <results> will be the same as in input
					 * run100M, longJump, shotPutThrow, highJump, run400M, 
					 * run110MHurdles, discusThrow, poleVaultJump, javelinThrow, run1500M
					 */
					//1
					Element run100M = doc.createElement("run100M");
					run100M.appendChild(doc.createTextNode("" + resultsObj.getRun100M()));
					resultsElement.appendChild(run100M);
					//2
					Element longJump = doc.createElement("longJump");
					longJump.appendChild(doc.createTextNode("" + resultsObj.getLongJump()));
					resultsElement.appendChild(longJump);
					//3
					Element shotPutThrow = doc.createElement("shotPutThrow");
					shotPutThrow.appendChild(doc.createTextNode("" + resultsObj.getShotPutThrow()));
					resultsElement.appendChild(shotPutThrow);
					//4
					Element highJump = doc.createElement("highJump");
					highJump.appendChild(doc.createTextNode("" + resultsObj.getHighJump()));
					resultsElement.appendChild(highJump);
					//5
					Element run400M = doc.createElement("run400M");
					run400M.appendChild(doc.createTextNode("" + resultsObj.getRun400M()));
					resultsElement.appendChild(run400M);
					//6
					Element run110MHurdles = doc.createElement("run110MHurdles");
					run110MHurdles.appendChild(doc.createTextNode("" + resultsObj.getRun110MHurdles()));
					resultsElement.appendChild(run110MHurdles);
					//7
					Element discusThrow = doc.createElement("discusThrow");
					discusThrow.appendChild(doc.createTextNode("" + resultsObj.getDiscusThrow()));
					resultsElement.appendChild(discusThrow);
					//8
					Element poleVaultJump = doc.createElement("poleVaultJump");
					poleVaultJump.appendChild(doc.createTextNode("" + resultsObj.getPoleVaultJump()));
					resultsElement.appendChild(poleVaultJump);
					//9
					Element javelinThrow = doc.createElement("javelinThrow");
					javelinThrow.appendChild(doc.createTextNode("" + resultsObj.getJavelinThrow()));
					resultsElement.appendChild(javelinThrow);
					//10
					Element run1500M = doc.createElement("run1500M");
					run1500M.appendChild(doc.createTextNode("" + resultsObj.getRun1500M()));
					resultsElement.appendChild(run1500M);
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
			System.out.println("Done writing XML to: " + filename);
			isSuccess = true;
		} catch (IOException e) {
			isSuccess = false;
			System.out.println("Couldn't write XML to: " + filename);
			e.printStackTrace();
		}
		return isSuccess;
	}
	
	public String getStylesheetFile() {
		return stylesheetFile;
	}

	public void setStylesheetFile(String stylesheetFile) {
		this.stylesheetFile = stylesheetFile;
	}
}
