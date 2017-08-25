/**
 * 
 */
package info.eugenijus.utils;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Test;

import info.eugenijus.model.Athlete;
import info.eugenijus.model.Constants;
import info.eugenijus.model.Result;

/**
 * @author Eug
 *
 */
public class JSONWriterTest {
	private String testFileName = "z_superSecretTest.txt";

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		try {
			File file = new File(Constants.TEST_FOLDER + testFileName);
			if(file.exists()) {
				file.delete();
				System.out.println(testFileName + " deleted!");
			} else {
				System.out.println("Couldn't delete file: " + testFileName);
			}
		} catch (Exception e) {
			System.out.println("Couldn't delete file: " + testFileName);
			e.printStackTrace();
		}
	}

	/**
	 * Test method for {@link info.eugenijus.utils.JSONWriter#writeToFile(java.lang.String, java.util.List)}.
	 */
	@Test
	public void testWriteToFile() {
		String[] ashton = {"Ashton Eaton", "10.23", "7.88", "14.52", "2.01", "45.0", "13.69", "43.34", "5.2", "63.63", "4:17.52"};
		Result ashtonResult = new Result();
		try {
			ashtonResult.parseAndSet(ashton);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JSONWriter jsonWriter = new JSONWriter(Constants.TEST_FOLDER);
		List<Athlete> athletesList = new LinkedList<>();
		athletesList.add(new Athlete(ashton[0], ashtonResult));
		boolean isSucces = jsonWriter.writeToFile(testFileName, athletesList);
		assertTrue(isSucces);
		
		List<String> list = new ArrayList<>();
		String line = "";
		try (BufferedReader br = new BufferedReader(new FileReader(Constants.TEST_FOLDER + testFileName))) {
			while ((line = br.readLine()) != null) {
				line = line.trim();
				System.out.println(line);
				list.add(line);
			}
		} catch (IOException e) {
			System.out.println("Couldn't read file: " + testFileName);
			e.printStackTrace();
		}
		assertTrue(list.get(0).contains("{\"athletes\":{\"a\":{\"name\":"));
		assertTrue(list.get(0).contains("Ashton Eaton"));
		assertTrue(list.get(0).contains("\"results\":"));
		assertTrue(list.get(0).contains("4:17.52"));
	}

}
