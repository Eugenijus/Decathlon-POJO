package info.eugenijus.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import info.eugenijus.model.Athlete;
import info.eugenijus.model.Constants;

/**
 * Based on tutorial:
 * http://www.mkyong.com/java/how-to-write-to-file-in-java-bufferedwriter-example/
 * @author Eugenijus Sabaliauskas
 */
public class TxtWriter implements DocumentWriter {
	public String folder;
	
	/**
	 * If you want to save in default folder then use this constructor
	 * @param folder
	 */
	public TxtWriter() {
		folder = Constants.TEST_FOLDER;
	}
	
	/**
	 * If you want to specify your own folder then use this constructor
	 * @param folder
	 */
	public TxtWriter(String folder) {
		this.folder = folder;
	}

	@Override
	public boolean writeToFile(String filename,  List<Athlete> results) {
		boolean isSuccess = false;
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(folder + filename))) {
			StringBuilder builder = new StringBuilder();
			for(Athlete athlete : results) {
				builder.append(athlete.toString()).append('\n');
			}
			bw.write(builder.toString());
			System.out.println("Done writing to: " + filename);
			isSuccess = true;
		} catch (IOException e) {
			isSuccess = false;
			e.printStackTrace();
		}
		return isSuccess;
	}
}
