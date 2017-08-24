package info.eugenijus.model;

import java.util.Arrays;
import java.util.List;

import info.eugenijus.strategy.FieldFormula;
import info.eugenijus.strategy.TrackFormula;

/**
 * http://www.theregister.co.uk/Design/page/hub/ibm2016/#five
 * @author Eugenijus Sabaliauskas
 *
 */
public class Result implements Comparable<Result>{
	private float run100M;
	private float longJump;
	private float shotPutThrow;
	private float highJump;
	private float run400M;
	private float run110MHurdles;
	private float discusThrow;
	private float poleVaultJump;
	private float javelinThrow;
	private CustomTime run1500M;
	
	private int totalScore;
	
	public Result() {
		//regular constructor
	}
	
	public Result(String[] resultLine) throws Exception {
		parseAndSet(resultLine);
	}
	/* ================ START OF GETTERS & SETTERS ================ */

	public float getRun100M() {
		return run100M;
	}

	public void setRun100M(float run100m) {
		run100M = run100m;
	}

	public float getLongJump() {
		return longJump;
	}

	public void setLongJump(float longJump) {
		this.longJump = longJump;
	}

	public float getShotPutThrow() {
		return shotPutThrow;
	}

	public void setShotPutThrow(float shotPutThrow) {
		this.shotPutThrow = shotPutThrow;
	}

	public float getHighJump() {
		return highJump;
	}

	public void setHighJump(float highJump) {
		this.highJump = highJump;
	}

	public float getRun400M() {
		return run400M;
	}

	public void setRun400M(float run400m) {
		run400M = run400m;
	}

	public float getRun110MHurdles() {
		return run110MHurdles;
	}

	public void setRun110MHurdles(float run110mHurdles) {
		run110MHurdles = run110mHurdles;
	}

	public float getDiscusThrow() {
		return discusThrow;
	}

	public void setDiscusThrow(float discusThrow) {
		this.discusThrow = discusThrow;
	}

	public float getPoleVaultJump() {
		return poleVaultJump;
	}

	public void setPoleVaultJump(float poleVaultJump) {
		this.poleVaultJump = poleVaultJump;
	}

	public float getJavelinThrow() {
		return javelinThrow;
	}

	public void setJavelinThrow(float javelinThrow) {
		this.javelinThrow = javelinThrow;
	}

	public CustomTime getRun1500M() {
		return run1500M;
	}

	public void setRun1500M(String run1500m) {
		CustomTime time = new CustomTime(run1500m);
		run1500M = time;
	}

	public void setRun1500M(CustomTime run1500m) {
		run1500M = run1500m;
	}
	
	/* ================ END OF GETTERS & SETTERS ================ */
	
	/**
	 * Creates array of track events (running) in float that represent seconds
	 * @return float[] fourRunTimes 
	 */
	public float[] getFourTrackEvents() {
		float[] fourRunTimes = {run100M, run400M, run110MHurdles, run1500M.getTimeInSeconds()};
		return fourRunTimes ;
	}
	
	/**
	 * Creates array of field events (jumps and throws) in float that represent meters
	 * @return float[] sixFieldTimes 
	 */
	public float[] getSixFieldEvents() {
		float[] sixFieldTimes = {longJump, shotPutThrow, highJump, 
				discusThrow, poleVaultJump, javelinThrow};
		return sixFieldTimes ;
	}
	
	/**
	 * In order to work correctly resultsArr array should be submitted with times in this order: 
	 * run100M, longJump, shotPutThrow, highJump, run400M, run110MHurdles, discusThrow, poleVaultJump, javelinThrow, run1500M
	 * @param resultsArr
	 */
	public void parseAndSet(List<String> resultsList) throws Exception{
		// resultsList.toArray(new String[0]) can also be used
		String[] resultsArr = resultsList.toArray(new String[resultsList.size()]);
		parseAndSet(resultsArr);
	}

	/**
	 * In order to work correctly resultsArr array should be with length of 11
	 * and submitted with NAME in 1st element and times in rest of elements in this order: 
	 * NAME, run100M, longJump, shotPutThrow, highJump, run400M, run110MHurdles, discusThrow, poleVaultJump, javelinThrow, run1500M
	 * @param resultsArr
	 */
	public void parseAndSet(String[] resultsArr) throws Exception{
		if(resultsArr.length != 11) {
			throw new Exception("resultsArr array is not of length 11! resultArr.length=" 
					+ resultsArr.length + " resultsArr:" + Arrays.toString(resultsArr));
		}
		setRun100M(parseStrToFloat(resultsArr, 1));
		setLongJump(parseStrToFloat(resultsArr, 2));
		setShotPutThrow(parseStrToFloat(resultsArr, 3));
		setHighJump(parseStrToFloat(resultsArr, 4));
		setRun400M(parseStrToFloat(resultsArr, 5));
		setRun110MHurdles(parseStrToFloat(resultsArr, 6));
		setDiscusThrow(parseStrToFloat(resultsArr, 7));
		setPoleVaultJump(parseStrToFloat(resultsArr, 8));
		setJavelinThrow(parseStrToFloat(resultsArr, 9));
		String run1500M = "";
		if (resultsArr != null && resultsArr.length >= 10) {
			run1500M = resultsArr[10];
		}
		CustomTime ct = new CustomTime(run1500M);
		setRun1500M(ct);
	}

	private float parseStrToFloat(String[] strArr, int index) {
		if (strArr != null && strArr.length >= index + 1) {
			return parseStrToFloat(strArr[index]);
		}
		return 0.0f;
	}

	private float parseStrToFloat(String strTime) {
		float f = 0.0f;
		try {
			f = Float.parseFloat(strTime);
		} catch (NumberFormatException ne) {
			System.out.println("Setting [" + strTime + "] to 0.0");
		}
		return f;
	}
	
	public int getTotalScore() {
		if(this.totalScore != 0) {
			return this.totalScore;
		} else {
			this.totalScore = calculateTotalScore();
		}
		return this.totalScore;
	}
	
	private int calculateTotalScore() {
		int score = 0;
		TrackFormula trackFormula = new TrackFormula();
		FieldFormula fieldFormula = new FieldFormula();
		score += trackFormula.calculateScore(getFourTrackEvents());
		score += fieldFormula.calculateScore(getSixFieldEvents());
		return score;
	}

	@Override
	public int compareTo(Result o) {
		int score1 = this.getTotalScore();
		int score2 = o.getTotalScore();
		if(score1 > score2) {
			return 1;
		}
		if(score1 < score2) {
			return -1;
		}
		return 0;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		String splitBy = "\"" + Constants.COMMA + " \"";
		sb.append("\"score\": ").append("\"").append(getTotalScore()).append("\", ");
		sb.append("\"results\": [\"").append(getRun100M()).append(splitBy)
			.append(getLongJump()).append(splitBy)
			.append(getShotPutThrow()).append(splitBy)
			.append(getHighJump()).append(splitBy)
			.append(getRun400M()).append(splitBy)
			.append(getRun110MHurdles()).append(splitBy)
			.append(getDiscusThrow()).append(splitBy)
			.append(getPoleVaultJump()).append(splitBy)
			.append(getJavelinThrow()).append(splitBy)
			.append(getRun1500M().toString()).append("\"]");
		return sb.toString();
	}
}
