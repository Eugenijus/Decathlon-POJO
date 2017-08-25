package info.eugenijus.model;

public class Athlete implements Comparable<Athlete>{
	private String name;
	private Result result;
	private String place;

	public Athlete() {
		this.name = "N/A";
		this.place = "DNF";	
	}
	
	public Athlete(String name, Result result) {
		this.place = "DNF";
		this.name = name;
		this.result = result;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}
	
	public void setPlace(String place) {
		this.place = place;
	}
	
	public String getPlace() {
		return place;
	}
	
	/**
	 * compares two athletes based on the score<br/>
	 * If this has higher score, then 1, otherwise -1 <br/>
	 * If both athletes have same score, then sort by name in A->Z order<br/>
	 * If score and name equal, then returns 0.
	 */
	@Override
	public int compareTo(Athlete o) {
		int score1 = this.getResult().getTotalScore();
		int score2 = o.getResult().getTotalScore();
		if(score1 > score2) {
			return 1;
		}
		if(score1 < score2) {
			return -1;
		}
		if(score1 == score2) {
			//System.out.println(">>" + this.getName() + " place: " + this.getPlace()  + " ?= " + o.getName() + " place: " + o.getPlace());
			return o.getName().compareTo(this.getName());
		}
		return 0;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{\"name\": ").append("\"").append(getName()).append("\", ");
		builder.append("\"place\": ").append("\"").append(getPlace()).append("\", ");
		if(result == null) {
			builder.append("null");
		} else {
			builder.append(result.toString());
		}		
		builder.append("}");		
		return builder.toString();
	}
}
