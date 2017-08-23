package info.eugenijus.model;

import java.util.List;

public class Result {
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
	
	public void parseAndSet(List<String> resultsList) {
		// resultsList.toArray(new String[0]) can also be used
		String[] resultsArr = resultsList.toArray(new String[resultsList.size()]);
		parseAndSet(resultsArr);
	}

	public void parseAndSet(String[] resultsArr) {
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

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		String splitBy = Constants.SEMICOLON + " ";
		sb.append(Constants.TAB + "[").append(getRun100M()).append(splitBy)
			.append(getLongJump()).append(splitBy)
			.append(getShotPutThrow()).append(splitBy)
			.append(getHighJump()).append(splitBy)
			.append(getRun400M()).append(splitBy)
			.append(getRun110MHurdles()).append(splitBy)
			.append(getDiscusThrow()).append(splitBy)
			.append(getPoleVaultJump()).append(splitBy)
			.append(getJavelinThrow()).append(splitBy)
			.append(getRun1500M().toString()).append("]");
		return sb.toString();
	}
}
