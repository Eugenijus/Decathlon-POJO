package info.eugenijus.strategy;

import java.util.Arrays;

import info.eugenijus.model.Constants;

public class FieldFormula implements ScoringStrategy {
	private boolean printParsedData = false;
	
	public FieldFormula() { }
	public FieldFormula(boolean printing) {
		this.printParsedData = printing;
	}
	
	/**
	 * This method should go through even results 
	 * and calculates score for each event and then adds them all 
	 * @param eventResults - MUST HAVE 6 float numbers! 
	 * {longJump, shotPutThrow, highJump, discusThrow, poleVaultJump, javelinThrow}
	 * @return totalScore
	 */
	@Override
	public int calculateScore(float[] eventResults) {
		int score = 0;
		if(eventResults.length > 0 && eventResults.length < 7) {
			//float[] sixFieldTimes = {longJump, shotPutThrow, highJump, discusThrow, poleVaultJump, javelinThrow};
			try {
				score += calculateScorePerEvent(Constants.FIELD_LONG_JUMP, eventResults[0] );
				score += calculateScorePerEvent(Constants.FIELD_SHOT_THROW, eventResults[1]);
				score += calculateScorePerEvent(Constants.FIELD_HIGH_JUMP, eventResults[2]);
				score += calculateScorePerEvent(Constants.FIELD_DISCUS_THROW, eventResults[3]);
				score += calculateScorePerEvent(Constants.FIELD_POLE_JUMP, eventResults[4]);
				score += calculateScorePerEvent(Constants.FIELD_JAVELIN_THROW, eventResults[5]);
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
	public int calculateScorePerEvent(String eventName, float distance) {
		int score = 0;
		if(distance == 0) {
			if(printParsedData) {
				System.out.println("eventName: " + eventName + " time:  " + distance + " score: " + score);
			}
			return score;
		}
		if(eventName.equals(Constants.FIELD_LONG_JUMP) || 
				eventName.equals(Constants.FIELD_HIGH_JUMP) || eventName.equals(Constants.FIELD_POLE_JUMP)) {
			double distanceCM = (double)(distance * 100); //because measured in centimeters instead of meters
			int difference = (int) (distanceCM - Constants.COLUMN_B.get(eventName));
			double diffToPower = Math.pow(difference, Constants.COLUMN_C.get(eventName));
			score = (int)Math.floor(Constants.COLUMN_A.get(eventName) * diffToPower);
		} else {
			double difference = distance - Constants.COLUMN_B.get(eventName);
			double diffToPower = Math.pow(difference, Constants.COLUMN_C.get(eventName));
			score = (int)Math.floor(Constants.COLUMN_A.get(eventName) * diffToPower );
		}
		if(printParsedData) {
			System.out.println("eventName: " + eventName + "  distance:  " + distance +'\t'+" score: " + score 
					+ " = " + Constants.COLUMN_A.get(eventName) + " * (" + distance + "-" + Constants.COLUMN_B.get(eventName)
					+ ")^" + Constants.COLUMN_C.get(eventName));
		}
		return score;
	}

}
