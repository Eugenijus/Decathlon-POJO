package info.eugenijus.strategy;

import java.util.Arrays;

import info.eugenijus.model.Constants;

/**
 * https://en.wikipedia.org/wiki/Decathlon#Points_system <br/>
 * Points = INT(A(B - P)^C) for track events (faster time produces a better score)
 * A, B and C are parameters that vary by discipline, 
 * while P is the performance by the athlete, measured in seconds (running), 
 *  metres (throwing), or centimetres (jumping).
 * @author Eugenijus Sabaliauskas
 *
 */
public class TrackFormula implements ScoringStrategy {

	@Override
	public int calculateScore(float[] eventResults) {
		int score = 0;
		if(eventResults.length > 0 && eventResults.length < 5) {
			try {
				//float[] fourRunTimes = {run100M, run400M, run110MHurdles, run1500M.getTimeInSeconds()};
				score += calculatePerEvent(Constants.TRACK_100M, eventResults[0]);
				score += calculatePerEvent(Constants.TRACK_400M, eventResults[1]);
				score += calculatePerEvent(Constants.TRACK_110M, eventResults[2]);
				score += calculatePerEvent(Constants.TRACK_1500M, eventResults[3]);
			} catch(Exception e) {
				System.out.println("Oops, not enough data in TrackFormula#calculateScore() with eventResults.length=" 
						+ eventResults.length + " eventResults:" + Arrays.toString(eventResults));
				e.printStackTrace();
			}
		}
		return score;
	}

	/**
	 * This method should calculate score for single event
	 * @param eventName
	 * @param time
	 * @return score = Math.floor(A(B - time)^C)
	 */
	@Override
	public int calculatePerEvent(String eventName, float time) {
		int score = 0;
		score = (int)Math.floor(Constants.COLUMN_A.get(eventName) *  Math.pow((Constants.COLUMN_B.get(eventName)-time), 
				Constants.COLUMN_C.get(eventName)) );
		if(true) {
			System.out.println("eventName: " + eventName + " time:  " + time + " score: " + score);
		}
		return score;
	}
}
