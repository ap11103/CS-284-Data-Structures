package intro;

public class Person {
	private String firstName;
	private String lastName;
	private int age;
	private String CWID;
	
	public Person(String firstName, String lastName, int age, String ID) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.CWID = ID;
		
	}
	
	public String toString(){
		return this.firstName + " " + this.lastName + "\n" + "Age: " + this.age + "\n" + "CWID: " + this.CWID;
	}
	
	public static void main(String[] args) {
		System.out.println("hi");
		Person p = new Person("Billy", "Bob", 19, "12857322");
		System.out.println(p.toString());
	}
}


