package intro;



public abstract class Animal {
	
	//data fields
	private int n;
	public String noise;
	
	
	
	public int numLegs() {
		n = (int)((Math.random()*5)+1);
		return n;
	}
	
	public abstract void makeNoise();
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
