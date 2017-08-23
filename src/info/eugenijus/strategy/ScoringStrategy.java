package info.eugenijus.strategy;

/**
 * https://en.wikipedia.org/wiki/Strategy_pattern
 * @author Eugenijus Sabaliauskas
 *
 */
public interface ScoringStrategy {
	/**
	 * This method should go through even results 
	 * and calculates score for each event and then adds them all 
	 * @param eventResults
	 * @return totalScore
	 */
	public int calculateScore(float[] eventResults);
	
	public int calculatePerEvent(String eventName, float result);
}
