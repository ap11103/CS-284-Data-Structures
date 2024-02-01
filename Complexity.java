package hw;

//Alisha Patel
//I pledge my honor that I have abided by the Stevens Honor System.

public class Complexity {
	
	//O(n^2) notation 
	public static void method1(int n) {
		int counter = 0;
		for(int i = 0; i<n; i++) {//takes through element of 
			for (int j=0; j<n; j++) {//also functions the same;
				System.out.println("Operations" + counter);
				counter++;
			}
		}
	}
	
	//O(n^3) notation
	public static void method2(int n) {
		int counter = 0;
		for(int i = 0; i<n; i++) {//takes through element of 
			for (int j=0; j<n; j++) {//also functions the same
				for(int k=0; k<n; k++) {
					System.out.println("Operations" + counter);
					counter++;
				}
			}
		}

	}
	
	
	//Question 4a
	/**
	 * 1 0 31
	 * 2 16 31
	 * 3 24 31
	 * 4 28 31
	 * 5 30 31
	 * 6 31 31
	 */
	
	//Question 4b:
	/**
	 * 1 0 63
	 * 2 32 63
	 * 3 48 63
	 * 4 56 63
	 * 5 60 63
	 * 6 62 63
	 * 7 63 63
	 */
	
	//Question 5: 
	/**
	 * the length of a determines the number of the iterations the loop goes through. 
	 * As the size of a increase, the number of iterations also increases 
	 * (like as a went from 31 to 62; number of iterations increased by 1)
	 */
	
	//Question 6: time complexity: 
	/** Because the relationship is very similar to the log based 2 of n. 
	 * Adding one of the log of n will result in time compexity for bsearch
	 * Thus, the time complexity is (log n) + 1 */
	
	
	//O(log n) notation
	public static void method3 (int n) {
		int counter = 0;
		for(int i = 1; i<n; i*=2) {
			System.out.println("Operations" + counter);
			counter++;

		}
	}
	
	//O(nlogn) notation
	public static void method4(int n) {
		int counter = 0;
		for (int i = 1; i<n; i++) {
			for(int j = 1; j<n; j*=2 ) {
				System.out.println("Operations" + counter);
				counter++;
			}
		}
	}
	
	//O(log log n) notation 
	public static void method5(int n) {
		int counter = 0;
		for(int i = 1; i<n; i*=2) {
			System.out.println("Operations" + counter);
			counter++;
		}
		int k = counter;
		for(int j = 1; j<k; j*=2) {//taking the log of the log n. 
			System.out.println("Operations"+counter);
			counter++;
		}
		
	}
	
	/**Extra credit: It happens to be that 2^n share the same time complexity 
	 * as the fibonacci sequence
	 */
	public static int method6(int n) {
		int counter  = 0;
		if (n<=1) {
			return n;
		}
		else {
			System.out.println("Operation" + counter);
			counter++;
			return method6(n-1) + method6(n-2);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
