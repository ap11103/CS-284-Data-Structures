package hw6;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;


import org.junit.jupiter.api.Test;

class AnagramsTest {
	
	Anagrams test = new Anagrams();
	
	@Test
	void myHashCodetest() {
		String a = "hi";
		assertEquals(test.myHashCode(a), 437);
		
		String b = "car";
		assertEquals(test.myHashCode(b), 610);
	}
	
	@Test
	void addWordTest() {
		String a = "aid";
		test.addWord(a);
		String b = "cat";
		test.addWord(b);
		
		ArrayList<String> one = new ArrayList<String>();
		one.add(a);
		
		ArrayList<String> two = new ArrayList<String>();
		two.add(b);
		
		assertTrue(test.anagramTable.containsKey(322L));
		assertTrue(test.anagramTable.containsValue(one));
		assertTrue(test.anagramTable.containsValue(two));
		
	}
	
	
	@Test
	void getMaxEntriesTest() {
		String a = "aid";
		String b = "car";
		String c = "rac";
		String d = "arc";
		String e = "dia";
		String f = "ida";
		String g = "cat";
		
		test.addWord(a);
		test.addWord(b);
		test.addWord(c);
		test.addWord(d);
		test.addWord(e);
		test.addWord(f);
		test.addWord(g);
		
		ArrayList<String> one  = new ArrayList<String>();
		one.add(b);
		one.add(c);
		one.add(d);
		
		ArrayList<String> two = new ArrayList<String>();
		two.add(a);
		two.add(e);
		two.add(f);
		
		ArrayList<String> three = new ArrayList<String>();
		three.add(g);
		
		Entry<Long, ArrayList<String>> entry1 = Map.entry(610L, one);
		Entry<Long, ArrayList<String>> entry2 = Map.entry(322L, two);
		Entry<Long, ArrayList<String>> entry3 = Map.entry(710L, three);
		
		
		ArrayList <Map.Entry <Long ,ArrayList <String >>> max = test.getMaxEntries ();
		assertTrue(max.contains(entry1));
		assertTrue(max.contains(entry2));
		assertFalse(max.contains(entry3));
	}
	
	

}
