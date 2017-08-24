package info.eugenijus.utils;

import info.eugenijus.model.Constants;

/**
 * Tutorials used:
 * https://www.mkyong.com/java/how-to-read-and-parse-csv-file-in-java/
 * 
 * @author Eugenijus Sabaliauskas
 */
public class SSVParser extends CSVParser{	
	public SSVParser() {
		super(Constants.SEMICOLON);
	}
}
