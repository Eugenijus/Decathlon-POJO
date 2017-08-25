package info.eugenijus.model;

import java.math.BigDecimal;
import java.math.MathContext;

public class CustomTime {
	private int minutes;
	private float seconds;
	private boolean isPrintingRequired = false;
	
	/**
	 * Default constructor, so minutes will be 0 and seconds 0.0
	 */
	public CustomTime() {
		this(0, 0.0f);
	}
	
	/**
	 * Sets minutes as int and seconds as float
	 * @param minutes
	 * @param seconds
	 */
	public CustomTime(int minutes, float seconds) {
		this.minutes = minutes;
		this.seconds = seconds;
	}	
	
	public CustomTime(String run1500m) {
		if(run1500m !=null && !run1500m.isEmpty()) {
			if(run1500m.contains(Constants.COLON)) {
				run1500m = run1500m.replace(Constants.COLON_CHAR, Constants.DOT_CHAR);
			}
			String[] parsedTime = run1500m.split(Constants.DOT);
			if(isPrintingRequired) {
				System.out.println(run1500m + " has parsedTime.length: " + parsedTime.length);
				for(int i=0; i<parsedTime.length; i++) {
					System.out.println(parsedTime[i]);
				}
			}
			
			if(parsedTime.length >= 2) {
				parsedTime[0] = (parsedTime[0]==null) ? "0" : parsedTime[0];
				minutes = new Integer(parsedTime[0]);
				try {
					if(parsedTime.length == 3) {
						String secondsStr = parsedTime[1] + "." + parsedTime[2];
						seconds = Float.parseFloat(secondsStr);
					} else {
						seconds = Float.parseFloat(parsedTime[1]);
					}
				} catch (NumberFormatException ne) {
					seconds = 0.0f;
				}
			}
		} else {
			minutes = 0;
			seconds = 0.0f;
		}		
	}
	
	public float getTimeInSeconds() {
		int minInSec = Math.multiplyExact(minutes, 60);
		BigDecimal decimal1  = BigDecimal.valueOf(seconds);
		decimal1 = decimal1.add(new BigDecimal(minInSec));
		//System.out.println("getTimeInSeconds() floatValue: " + decimal1.floatValue());
		//System.out.println("getTimeInSeconds() decimal1.round(MathContext.DECIMAL32): " + decimal1.round(MathContext.DECIMAL32));
		float result = decimal1.round(MathContext.DECIMAL32).floatValue();
		return result;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(minutes).append(Constants.COLON).append(seconds);
		return sb.toString();
	}
}
