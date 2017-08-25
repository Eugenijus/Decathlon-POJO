package info.eugenijus.utils;

import java.util.List;

import info.eugenijus.model.Athlete;

public interface DocumentParser {
	public List<String> parseDocument(String filename);
	
	public List<Athlete> parseDocumentToAthletes(String filename);
	
	public List<List<String>> parseDocumentToLists(String filename);
}
