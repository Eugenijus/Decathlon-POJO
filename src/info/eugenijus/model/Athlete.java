package info.eugenijus.model;

public class Athlete implements Comparable<Athlete>{
	private String name;
	private Result result;
	private String place;

	public Athlete() {
		this.place = "N/A";		
	}
	
	public Athlete(String name, Result result) {
		this.place = "N/A";
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
		return 0;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{\"name\": ").append("\"").append(getName()).append("\", ");
		builder.append("\"place\": ").append("\"").append(getPlace()).append("\", ");
		builder.append(result.toString());
		builder.append("}");		
		return builder.toString();
	}
}
