package info.eugenijus;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

import info.eugenijus.model.Athlete;
import info.eugenijus.model.Constants;
import info.eugenijus.strategy.PlaceFormula;
import info.eugenijus.utils.HTMLWriter;
import info.eugenijus.utils.SSVParser;
import info.eugenijus.utils.TxtWriter;
import info.eugenijus.utils.XMLWriter;

/**
 * Tutorials used:
 * https://www.mkyong.com/java/how-to-read-and-parse-csv-file-in-java/
 * http://www.theregister.co.uk/Design/page/hub/ibm2016/#five
 * 
 * @author Eugenijus Sabaliauskas
 */
public class DecathlonMain {
	
	public static void main(String[] args) {
		String inputFile = Constants.TEST_FOLDER + "Decathlon_input.txt";
		String outputFile = Constants.TEST_FOLDER  + "Decathlon_output.txt";
		List<Athlete> athletes = new LinkedList<>();
		
		if(args != null && args.length > 0){
			inputFile = args[0];
			if(args.length >= 2) {
				outputFile = args[1];
			}
		} else {
			try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
				//asking for input file
				System.out.println("Input File: ");
				String inputLine = br.readLine();
				if(inputLine.equals("") || inputLine.equals("0") || inputLine.equals("n")) {
					System.out.println("Ok, will use default files: \n" + inputFile + "\n" + outputFile);
				} else {
					inputFile = inputLine;
					//asking for output file
					System.out.println("Output File: ");
					inputLine = br.readLine();
					if(inputLine.equals("") || inputLine.equals("0")) {
						System.out.println("Ok, will use default output file: " + outputFile);
					} else {
						outputFile = inputLine;
					}
				}
			} catch (Exception e) {
				System.out.println("Couldn't read from command line!");
				e.printStackTrace();
			}			
		}				
		
		//System.out.println("========== Parsing from file to List<Athlete> ============");
		SSVParser parser = new SSVParser();		
		List<Athlete> listOfAthletes = parser.parseDocumentToAthletes(inputFile);
		athletes.addAll(listOfAthletes);
				
		//System.out.println("========== Scoring ==========================");
		
		PlaceFormula placement = new PlaceFormula();
		placement.markPlaces(athletes);
		
		//System.out.println("========== Writing ==========================");
		TxtWriter writer = new TxtWriter();
		writer.writeToFile(outputFile, athletes);
		
//		JSONWriter jsonWriter = new JSONWriter();
//		jsonWriter.writeToFile(Constants.TEST_FOLDER + "output.json", athletes);
		
		String xmlFilename = outputFile + "_ouput.xml";
		String xslFilename = Constants.TEST_FOLDER + Constants.STYLE_FOLDER + Constants.XSL_STYLESHEET;
		String htmlFilename = outputFile + "_output.html";
		//tested with https://www.w3schools.com/xml/xml_validator.asp
		XMLWriter xmlWriter = new XMLWriter();
		xmlWriter.setStylesheetFile(xslFilename);
		boolean createdXML = xmlWriter.writeToFile(xmlFilename, athletes);
		
		if(createdXML) {
			HTMLWriter htmlWriter = new HTMLWriter();
			htmlWriter.convertXMLtoHTML(xmlFilename, xslFilename, htmlFilename);
		}		
	}
}
