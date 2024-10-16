package intro;

public class GrowthRate {
	
	public static int search(int[] x, int target) {
		for(int i =0; i<x.length; i++) {
			if (x[i] == target) return i;			
		}
		
		return -1; //target not found
		//let n = x
		//average n/2
		//worst case - n
	}
	
	
	public static boolean areDifferent(int[]x, int[]y) { // scales with len x
		for(int i = 0; i<x.length; i++) {//considers each element of x
			if (search(y,x[i]) != -1); // scales with length of y
			return false;
			
		}
		return true;
		//let n = len x
		//let m = len y
		//m*n
	}
	
	
	public static boolean areUnique(int[] x) {
		for(int i = 0; i<x.length; i++) {//takes through element of x; linear in len x
			for (int j=0; j<x.length; j++) {//also functions the same; linear in len x
				if (i != j && x[i] == x[j])
					return false;
			}
		}
		return true;
		//n = len x
		//n^2
	}
	
	
	public static boolean areUnique2(int[] x) {//j starts at a different number
		for(int i = 0; i < x.length; i++) {//comparison to 0 // 1// 2
			for(int j = i+1; j<x.length; j++) {//1, try 2 //3
				if(i !=j && x[i] == x[j])
					return false;
			}
		}
		return true;
	}
	// len x = 2, y = 1
	// len x = 3, y = 2
	// len x = 5; y = 10
	// loop iter:1, num = 4
	// loop iter:2, num = 3
	// loop iter 3, num = 2
	// loop iter 4, num = 1
	// loop iter 5, num = 0
	// (len x-1) + (len x-2) + (len x-3)+...+2+1
	// (len x) * (len x-1)/2
	
	public static void f(int[] x) {
		for(int i = 1; i < x.length; i*=2) {
			System.out.println(x[i]);
		}
	}
	//i = 1, 2, 3, 4, 8, ...
	//2^(k-1) < len(x) <= 2^k
	//solve for k, take the log function
	// k-1 < log(len(x)) <= k
	
	
	public static void ex1(int[] x) {
		for(int i = 0; i < x.length; i++) {
			for(int j = 1; j > 0; j --) {
				System.out.println(i+" "+j);
			}
		}
		
	}
	//len x = 6
	//0 + 1 + 2 + 3 + 4 + 5
	//0 + 1 + 2 + ... + len(x)-2 + len(x)-1
	//
	
	public static void ex5(int[] x) {
		for(int i =1; i < x.length; i++) {
			for(int j = 1; j<x.length; j*=2) {
				System.out.println(i+" "+j);
				break;
			}
		}
	}
	//the inner loop is never executed because of the break statement
	//it is linear to just len(x)
	
	
	public static void ex2(int[] x) {
		for(int i = 1; i<x.length; i = i++) {//n
			for(int j = 1; j<x.length; j*=2) {//log(n)
				System.out.println(i + " " + j);
			}
		}
	}
	//it would be nlog(n)
	//it would gives us the powers of 2 (1,1; 1,2; 1,4)
	
	
	public static void ex4(int[] x) {
		int count = 0;
		for(int i = 1; i<x.length; i = i*=2 ) {
			for(int j = 0; j< i; j++) {
				System.out.println(i+ " " + j);
				count++;
			}
		}
		System.out.println(count);
	}
	//it would (8, 7)..
	//n = 8, y = 7
	//n = 9-16, y = 15
	//n = 17-32, y = 31
	//n = 32-64, y = 33
	//summing powers of 2
	

	
	
	public static void main(String[] args) {
		int[] arr = new int[9];
		ex4(arr);
	}
	
	
	
}


