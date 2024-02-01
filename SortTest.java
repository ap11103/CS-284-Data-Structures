package hw5;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

class SortTest {

	@Test
	void sorttest1() {
		Integer [] unsorted = {9,2,5,6,7,4,3,8,1};
		Integer [] sorted = {1,2,3,4,5,6,7,8,9};
		Sort.sort(unsorted);
		assertEquals(Arrays.toString(unsorted), Arrays.toString(sorted));
	}
	
	@Test
	void sorttest2() {
		Integer [] unsorted = {3};
		Integer [] sorted = {3};
		Sort.sort(unsorted);
		assertEquals(Arrays.toString(unsorted), Arrays.toString(sorted));
	}
	
	@Test
	void sorttest3() {
		Integer [] unsorted = {3,6,4,5};
		Integer [] sorted = {3,4,5,6};
		Sort.sort(unsorted);
		assertEquals(Arrays.toString(unsorted), Arrays.toString(sorted));
	}
	
	
	@Test
	void sorttest4() {
		Integer [] unsorted = {2,5,3,0,2,3,0,3};
		Integer [] sorted = {0,0,2,2,3,3,3,5};
		Sort.sort(unsorted);
		assertEquals(Arrays.toString(unsorted), Arrays.toString(sorted));
	}
	
	@Test
	void sorttest5() {
		Integer [] unsorted = {10,9,8,7,6,5,4,3,2,1};
		Integer [] sorted = {1,2,3,4,5,6,7,8,9,10};
		Sort.sort(unsorted);
		assertEquals(Arrays.toString(unsorted), Arrays.toString(sorted));
	}
	
	@Test
	void sorttest6() {
		Integer [] unsorted = {3,4,7,1,5,8,2,9,0,6};
		Integer [] sorted = {0,1,2,3,4,5,6,7,8,9};
		Sort.sort(unsorted);
		assertEquals(Arrays.toString(unsorted), Arrays.toString(sorted));
	}
	

}
