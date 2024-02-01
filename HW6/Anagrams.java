package hw6;

//Alisha Patel
//I pledge my honor that I have abided by the Stevens Honor System.

import java.util.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Anagrams {
	
	final Integer[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61,
			67, 71, 73, 79, 83, 89, 97, 101};
	
	Map<Character,Integer> letterTable;
	Map<Long,ArrayList<String>> anagramTable;
	//The list of alphabets that will be later organized by the buildLetterTable
	final Character[] alphabets = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
			'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
	
	
	/**Creates a new letterTable that will have key and value 
	 * calls on the buildLetterTable
	 * adds on the anagramTable
	 * */
	public Anagrams (){
		letterTable = new HashMap<Character, Integer>();
		buildLetterTable();
		anagramTable = new HashMap<Long, ArrayList<String>>();
	}
	
	
	/**Build a hash table which corresponds each alphabets 
	 * with their specific keys
	 * and adds it to the letterTable
	 */
    public void buildLetterTable(){
    	for(int i = 0; i < primes.length; i++) {
    		letterTable.put(alphabets[i], primes[i]);
    	}
    }
    
    /**Throws an error if the string s given is empty
     * anagrams should have the same hash code
     * this is implement by a while loop that keep iterating until the list is empty
     * @param s: the string for which the hash code should be computed
     * @return: the hash code of the string
     */
    public long myHashCode(String s){
    	if (s.isEmpty()) {
    		throw new IllegalArgumentException("myHashCode: empty string");
    	}
    	else {
    		int i = 0;
    		long value = 1;
    		while(i < s.length()) {
    			Character hash = s.charAt(i);
    			value = value * letterTable.get(hash);
    			i++;
    		}
    		return value;
    	}
    	
    }
    
    /**Computes the hash code of the string 
     * if it is the same, it adds to the anagramTable
     * If the word is already present, then it throws an error
     * @param s: the string that needs to be added to the anagramTable
     */
    public void addWord(String s){
    	if (anagramTable.containsKey(myHashCode(s))) {
    		if((anagramTable.get(myHashCode(s))).contains(s)) {
    			throw new IllegalArgumentException("addWord: duplicate value");
    		}
    		(anagramTable.get(myHashCode(s))).add(s);
    	}
    	else {
    		ArrayList<String> array = new ArrayList<String>();
    		array.add(s);
    		anagramTable.put(myHashCode(s), array);
    	}
    }
    
    
    /**Given function
     * Receives the name of the text file containing words
     * builds the hash table, anagramTable
     * @param s
     * @throws IOException
     */
    public void processFile(String s) throws IOException {
    	FileInputStream fstream = new FileInputStream(s);
    	BufferedReader br = new BufferedReader(new InputStreamReader(fstream ));
    	String strLine;
    	while (( strLine = br.readLine ()) != null) {
    		this.addWord(strLine );
    	}
    	br.close ();
	}
	
	
    /**Iteratively goes through the anagramTable for anagrams
     * Keeps a count for the number of anagrams, and the largest is returned
     * and the largest is added and the one previous are removed
     * @return: the entries in the anagramTable that has the largest anagrams
     */
    public ArrayList<Map.Entry<Long,ArrayList<String>>> getMaxEntries(){
    	int value = 0;
    	ArrayList<Map.Entry<Long,ArrayList<String>>> keys = new ArrayList<Map.Entry<Long, ArrayList<String>>>();
    	Set<Long> keyset = anagramTable.keySet();
    	for(Long key : keyset) {
    		ArrayList<String> array = anagramTable.get(key);
    		int temp = array.size();
    		
    		if (temp == value) {
    			keys.add(new AbstractMap.SimpleEntry<Long, ArrayList<String>>(key, array));
    		}
    		
    		else if (temp > value) {
    			value = temp;
    			keys.clear();
    			keys.add(new AbstractMap.SimpleEntry<Long, ArrayList<String>>(key, array));
    		}
    	}
    	return keys;
    }
	 
	

	public static void main(String[] args) {
		Anagrams a = new Anagrams ();
		final long startTime = System.nanoTime ();
		try {
			a.processFile("words_alpha.txt");
		} catch (IOException e1) {
			e1.printStackTrace ();
		}
		ArrayList <Map.Entry <Long ,ArrayList <String >>> maxEntries = a.getMaxEntries ();
		final long estimatedTime = System.nanoTime () - startTime;
		final double seconds = (( double) estimatedTime /1000000000);
		System.out.println("Time: "+ seconds );
		System.out.println("List of max anagrams: "+ maxEntries );
		
		
		

	}

}
