package info.eugenijus.utils;

import info.eugenijus.model.Constants;

/**
 * Parses file with decathlon events and converts to lists or arrays to be used later<br/>
 * By default it is assumed that separator is a semicolon ";"!<br/>
 * Class abbreviation SSVParser = Semicolon-Separated Value Parser<br/>
 * e.g. name;1.23;0.123
 * @author Eugenijus Sabaliauskas
 */
public class SSVParser extends CSVParser{	
	public SSVParser() {
		super(Constants.SEMICOLON);
	}
}
