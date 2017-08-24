package info.eugenijus.utils;

import java.util.List;
import info.eugenijus.model.Athlete;

public interface DocumentWriter {
	public boolean writeToFile(String filename, List<Athlete> results);
}
