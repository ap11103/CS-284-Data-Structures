package intro;

public class Student {
	
	//instance variables
	private int gradYear;
	
	//constructor
	public Student(String first, String last, int years, String id, gradYear) {
		super(first, last, years, id);
		this.gradYear = gradYear;
		
	}
	
	// make getters and setters here
	public void setGradYear(int gradYear) {
		this.gradYear = gradYear;
	}
	
	public double getGradYear() {
		return gradYear;
	}
	
	public String getCredentials() {
		return super.toString() + "I will graduate in" + gradYear;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
