package hw5;

//Alisha Patel
//I pledge my honor that I have abided by the Stevens Honor System. 

import java.util.Set;
import java.util.HashSet;

public class Sort {
	
		
	private static class Interval{		
		//data fields	
		private int lower;
		private int upper;
		
		//constructors for upper and lower bounds
		public Interval(int lower, int upper) {
			this.lower = lower;
			this.upper = upper;
		}
		
		//Returns the lower bound of the interval
		public int getLower (){
			return lower;
		}
		
		//Returns the upper bound of the interval
		public int getUpper () {
			return upper;
		}
		
		//returns true if this interval and
		//the given interval have the same lower and upper bounds
		public boolean equals(Object o) { 
			Interval eq = (Interval) o;
			if (this.lower == eq.lower || this.upper == eq.upper) {
				return true;
			}
			else{
				return false;
			}
		}
		
		// returns lower * lower + upper
		public int hashCode () { 
			return lower * lower + upper;	
		}
	}
	
	/**Swaps the entities, in this case intervals when it is called in bubblesort
	 * @param <T>
	 * @param table: the array type that contains the entities
	 * @param a: the first integer that is being swapped
	 * @param b: the second integer that is being swapped
	 */
	private static <T extends Comparable <T>> void swap(T[] table, int a, int b) {
		T temp = table[a];
		table[a] = table[b];
		table[b] = temp;
	}
	
	
	/** Creates a set of intervals that is empty 
	 * calls partition for the pivot point and then calls the bubblesort
	 * @param <T>
	 * @param array: contains the data that is needed to be sort	
	 */
	public static <T extends Comparable <T>> void sort(T[] array) {
		Set<Interval> intervals =  new HashSet<Interval>();
		if (array.length == 1) {
			return;
		}
		intervals.add(new Interval(0, array.length - 1));
		//Partition that contains a single element, the entire interval
		//stop when there are no more intervals, then pick an interval 
		//split into small items before then the large ones
		while (!intervals.isEmpty()) {
			Interval iterate = intervals.iterator().next();
			if((iterate.getUpper() - iterate.getLower()) > 1) {
				System.out.println(iterate.getUpper() - iterate.getLower());
				int pivot = partition(array, iterate.getLower(), iterate.getUpper());
				intervals.add(new Interval(iterate.getLower(), pivot));
				intervals.add(new Interval(pivot+1, iterate.getUpper()));
			}
			intervals.remove(iterate);
		}
		bubblesort(array);
		
		
		
		
	}
	
	/**The implement of partition is finding the pivot for sorting  
	 * @param <T>
	 * @param table: array that includes the data to be sorted
	 * @param first represents the upper bound
	 * @param last represents the lower bound
	 * @return an integer that represents the pivot of the sorting array
	 */
	private static <T extends Comparable <T>> int partition(T[] table, int first, int last) {
		int middle = (first + last) / 2;
	     if (table[middle].compareTo(table[first]) < 0)
	       swap(table, first, middle);
	     if (table[last].compareTo(table[first]) < 0)
	       swap(table, first, last);
	     if (table[last].compareTo(table[middle]) < 0)
	       swap(table, middle, last);
		
		T pivot = table[middle];
		int up = first;
		
		int down = last;
		
		do {
			while((up < last) && (pivot.compareTo(table[up]) >= 0 )) {
				up++;
			}
			while(down>first && pivot.compareTo(table[down]) < 0) {
				down --;
			}
			if (up < down) {
				swap(table, up, down);
				
			}
		}
		while(up < down);
		
		swap(table, first, down);
		
			return down;
		}
		
	
	/**Bubble sorts the array
	 * It repeatedly swaps the adjacent elements if they are in the wrong order
	 * If they are in the right order, the loop stops and that array is sorted
	 * @param <T>
	 * @param table: array that includes the data that needs to be sorted
	 */
	private static <T extends Comparable <T>> void bubblesort(T[] table) {
		int pass = 1;
		boolean exchange = false;
		//Checks if elements after table.length-pass+! are in place then exchange is false
		do{
			exchange = false;
			for(int i = 0; i < table.length-pass; i++) {
				if(table[i].compareTo(table[i+1]) > 0) {
					T temp = table[i];
					table[i] = table[i+1];
					table[i+1] = temp;
					exchange = true;
				}
			}
			pass++;
		}
		
		while(exchange);
		
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

