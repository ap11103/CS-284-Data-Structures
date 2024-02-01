package trees;

import java.util.Set;



import java.util.HashSet;
import java.util.Arrays;

public class heap {
	
	private static class Interval{
		private int lower;
		private int upper;
		
		public Interval(int lower, int upper) {
			this.lower = lower;
			this.upper = upper;
		}
		
		public int getLower() {
			return lower;
		}
		
		public int getUpper() {
			return upper;
		}
		
		public boolean equals(Object o) {
			Interval eq = (Interval) o;
			if (this.lower == eq.lower || this.upper == eq.upper) {
				return true;
			}
			else{
				return false;
			}
		}
		
		public int hashCode() {
			return lower * lower + upper;
		}	
	}
	
	public  static <T extends Comparable <T>> void swap(T[] array, int a, int b) {
		T temp = array[a];
		array[a] = array[b];
		array[b] = temp;
	}
	
	public static <T extends Comparable <T>> int partition(T[] array, int first, int last) {
		
		int middle = (first + last) / 2;
	    if (array[middle].compareTo(array[first]) < 0)
	           swap(array, first, middle);
	         if (array[last].compareTo(array[first]) < 0)
	           swap(array, first, last);
	         if (array[last].compareTo(array[middle]) < 0)
	           swap(array, middle, last);
		
	    T w = array[middle];
        int up = first;
        int down = last;
        swap(array, first, (first + last) / 2);
        do {
            while((up < last) && (w.compareTo(array[up]) >= 0 )) {
                up++;
            }
            while(down>first && w.compareTo(array[down]) < 0) {
                down--;
            }
            if (up < down) {
                swap(array, up, down);

            }
        } while(up < down);

        swap(array, first, down);

        return down;
            
	}
	
	public static <T extends Comparable <T>> void bubble(T[] array) {
		int pass = 1;
		boolean exchanges = false;
		do {
			exchanges = false;
			for (int i = 0; i < array.length - pass; i++) {
				if (array[i].compareTo(array[i + 1]) > 0) {
					T temp = array[i];
					array[i] = array[i + 1];
					array[i + 1] = temp;
					exchanges = true;
				}
			}
			pass++;
		} while (exchanges);
	}

	public static <T extends Comparable <T>> void sort(T[] array) {
		Set<Interval> unsorted = new HashSet<>();
		Interval value = new Interval (0, array.length -1);
		unsorted.add(value);
		while(!unsorted.isEmpty()) {
			Interval pick = unsorted.iterator().next();
			int count = pick.getUpper() - pick.getLower();
			System.out.println(count);
			if (pick.getUpper() - pick.getLower() > 1) {
				int pivot = partition(array, pick.getLower(), pick.getUpper());
				unsorted.add(new Interval(pick.getLower(), pivot));
				unsorted.add(new Interval(pivot + 1, pick.getUpper()));
				//System.out.println(unsorted.size());
				//System.out.println(Arrays.toString(array));
			}
			unsorted.remove(pick);
			
		}
		bubble(array);
		
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
				//Integer[] a = {3,2,1};
		        //Integer[] a = {9,2,5,6,7,4,3,8,1};
		        //Integer[] a = {10,9,8,7,6,5,4,3,2,1};
		        Integer[] a = {2,5,3,0,2,3,0,3};
		        //Integer[] a = {3,4,7,1,8,5,2,9,0,6};
		        //Integer[] a  = {3,4,7,1,5,8,2,9,0,6};
		        //Integer[] a = {5,4,7,1,8,3,2,9,0,6};
		        
		        System.out.println("Original: ");
		        for (int i=0; i<a.length; i++) {
		            System.out.print(a[i] + " ");
		        }        
		        sort(a);
		       System.out.println("\nSorted: ");
		        for (int i=0; i<a.length; i++) {
		            System.out.print(a[i] + " ");
		        }
		


	}
}
