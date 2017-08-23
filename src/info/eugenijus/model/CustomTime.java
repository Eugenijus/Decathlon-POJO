package info.eugenijus.model;

public class CustomTime {
	private int minutes;
	private float seconds;
	
	private boolean isPrintingRequired = false;
	
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
	
	public CustomTime() {
		this(0, 0.0f);
	}
	
	public CustomTime(int minutes, float seconds) {
		this.minutes = minutes;
		this.seconds = seconds;
	}	
	
	public float getTimeInSeconds() {
		return (minutes*60)+seconds;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(minutes).append(Constants.COLON).append(seconds);
		return sb.toString();
	}
}
