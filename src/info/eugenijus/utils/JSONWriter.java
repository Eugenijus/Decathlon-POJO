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
public class JSONWriter implements DocumentWriter {
	public String folder;
	
	public JSONWriter() {
		folder = "";
	}
	
	/**
	 * If you want to specify your own folder then use this constructor,
	 * but make sure filename is just a file name, not the full path
	 * @param folder
	 */
	public JSONWriter(String folder) {
		this.folder = folder;
	}

	/**
	 * This JSON writer outputs Athlete object and results as long as Result#toString() is also in JSON format<br/>
	 * Hence this is very optimistic implementation
	 * @param folder
	 */
	@Override
	public boolean writeToFile(String filename,  List<Athlete> athletesList) {
		boolean isSuccess = false;
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(folder + filename))) {
			StringBuilder builder = new StringBuilder();
			builder.append("{\"athletes\":{");
			for(int i=0; i<athletesList.size()-1; i++){
				builder.append("\"a\":");
				builder.append(athletesList.get(i).toString()).append(",").append('\r').append('\n');
			}
			builder.append("\"a\":");
			builder.append(athletesList.get(athletesList.size()-1).toString()).append('\r').append('\n');
			builder.append("} }");
			bw.write(builder.toString());
			System.out.println("Done writing to: " + filename);
			isSuccess = true;
		} catch (IOException e) {
			isSuccess = false;
			System.out.println("Couldn't write to: " + filename);
			e.printStackTrace();
		}
		return isSuccess;
	}
}
