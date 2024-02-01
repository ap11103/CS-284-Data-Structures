package hw4;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TreapTest {

	@Test
	void testadd() {
		Treap<Integer> testTree = new Treap<Integer>();
		testTree.add(4,19);
		testTree.add(6,18);
		boolean bool = testTree.find(4);
		assertEquals(true, bool);
	}
	
	@Test
	void testdelete() {
		Treap<Integer> testTree = new Treap<Integer>();
		testTree.add(4,19);
		testTree.add(6,18);
		testTree.delete(6);
		boolean bool = testTree.find(6);
		assertEquals(false, bool);
	}
	
	@Test
	void testfind() {
		Treap<Integer> testTree = new Treap<Integer>();
		testTree.add(4,19);
		testTree.add(6,18);
		testTree.add(2,16);
		boolean bool = testTree.find(8);
		assertEquals(false, bool);
	}
	
	
	@Test
	void testtoString() {
		Treap<Integer> testTree = new Treap<Integer>();
		testTree.add(4,19);
		assertEquals(testTree.toString(), ("(Key = 4 Priority = 19)\n" + "--null\n" +"--null\n"));
		
	}

}
