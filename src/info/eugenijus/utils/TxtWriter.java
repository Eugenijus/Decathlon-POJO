package info.eugenijus.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import info.eugenijus.model.Athlete;

/**
 * Based on tutorial:
 * http://www.mkyong.com/java/how-to-write-to-file-in-java-bufferedwriter-example/
 * @author Eugenijus Sabaliauskas
 */
public class TxtWriter implements DocumentWriter {
	public String folder;
	
	public TxtWriter() {
		folder = "";
	}
	
	/**
	 * If you want to specify your own folder then use this constructor,
	 * but make sure filename is just a file name, not the full path
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
				builder.append(athlete.toString()).append('\r').append('\n');
			}
			bw.write(builder.toString());
			System.out.println("Done writing TXT to: " + filename);
			isSuccess = true;
		} catch (IOException e) {
			isSuccess = false;
			System.out.println("Couldn't write TXT to: " + filename);
			e.printStackTrace();
		}
		return isSuccess;
	}
}
