package intro;

public class Dog extends Animal{
	
	public String noise;
	
	
	public String makeNose() {
		noise = "woof woof";
		noise = Animal.makeNoise();
		return noise;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
