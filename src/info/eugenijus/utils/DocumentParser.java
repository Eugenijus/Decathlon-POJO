package info.eugenijus.utils;

import java.util.List;

public interface DocumentParser {
	public List<String> parseDocument(String filename);
	
	public List<List<String>> parseDocumentToLists(String filename);
}
