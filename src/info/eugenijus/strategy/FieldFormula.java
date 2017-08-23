package info.eugenijus.strategy;

import java.util.Arrays;

import info.eugenijus.model.Constants;

public class FieldFormula implements ScoringStrategy {

	@Override
	public int calculateScore(float[] eventResults) {
		int score = 0;
		if(eventResults.length > 0 && eventResults.length < 7) {
			//float[] sixFieldTimes = {longJump, shotPutThrow, highJump, discusThrow, poleVaultJump, javelinThrow};
			try {
				score += calculatePerEvent(Constants.FIELD_LONG_JUMP, eventResults[0] );
				score += calculatePerEvent(Constants.FIELD_SHOT_THROW, eventResults[1]);
				score += calculatePerEvent(Constants.FIELD_HIGH_JUMP, eventResults[2]);
				score += calculatePerEvent(Constants.FIELD_DISCUS_THROW, eventResults[3]);
				score += calculatePerEvent(Constants.FIELD_POLE_JUMP, eventResults[4]);
				score += calculatePerEvent(Constants.FIELD_JAVELIN_THROW, eventResults[5]);
			} catch(Exception e) {
				System.out.println("Oops, not enough data in FieldFormula#calculateScore() with eventResults.length=" 
						+ eventResults.length + " eventResults:" + Arrays.toString(eventResults));
				e.printStackTrace();
			}
		}
		return score;
	}

	/**
	 * This method should calculate score for single event
	 * @param eventName
	 * @param distance
	 * @return score = Math.floor(A(time - B)^C)
	 */
	@Override
	public int calculatePerEvent(String eventName, float distance) {
		int score = 0;
		if(eventName.equals(Constants.FIELD_LONG_JUMP) || 
				eventName.equals(Constants.FIELD_HIGH_JUMP) || eventName.equals(Constants.FIELD_POLE_JUMP)) {
			distance = distance * 100; //because measured in centimeters instead of meters
		}
		double difference = distance - Constants.COLUMN_B.get(eventName);
		double diffToPower = Math.pow(difference, Constants.COLUMN_C.get(eventName));
		score = (int)Math.floor(Constants.COLUMN_A.get(eventName) * Math.pow(distance - Constants.COLUMN_B.get(eventName), 
				Constants.COLUMN_C.get(eventName))  );
		if(true) {
			System.out.println("eventName: " + eventName +'\t'+"distance:  " + distance +'\t'+" score: " + score 
					+ " =" + Constants.COLUMN_C.get(eventName) + " * (" + difference + ")^"+diffToPower);
		}
		return score;
	}

}
