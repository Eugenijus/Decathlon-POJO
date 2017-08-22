package info.eugenijus.model;

public class Athlete {
	private String name;
	private Result result;

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
	
	@Override
	public String toString() {		
		return "Athlete obj {" + getName() + ": " + result.toString() + "}";
	}
}
