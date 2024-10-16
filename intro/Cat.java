package intro;

public class Cat extends Animal implements Pet{
	
	public String name;
	
	public String makeNoise() {
		String noise = "woof woof";
		noise = Animal.makeNoise();
		return noise;
	}
	
	public String getName() {
		name = "kitty";
		return name;
	}
	
	
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
