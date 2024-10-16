package runtime;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RuntimeTest {

	@Test
	void test1() {
		Runtime r = new Runtime();
		r.readFromFile("eg3.pgm");
		r.run();
		assertEquals(r.toString(), "Pgm   : " +"[push 6.0, push 3.0, mul, pop m0, exit]" + 
		"\nPc    : " + "6" + "\nStack : " + "[]" + "\nMemory: " + "[18.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0]" + 
				"\n------------------------------------------------\n");
	}
	
	/**
	@Test
	void test2() {
		Runtime r = new Runtime();
		r.readFromFile("eg1.pgm");
		r.run();
		assertEquals(r.toString(), "Pgm   : " +"[push 5.0, push 3.0, sub, pop m0, exit]" + 
				"\nPc    : " + "6" + "\nStack : " + "[]" + "\nMemory: " + "[2.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0]" + 
						"\n------------------------------------------------\n");
		
	}*/
	
	@Test
	void test3() {
		Runtime r = new Runtime();
		r.readFromFile("eg2.pgm");
		r.run();
		assertEquals(r.toString(), "Pgm   : " +"[push 8.0, pop m0, push m0, push m0, label l2, dec, jmpz done, pop m0, push m0, sub, push m0, jmp l2, label done, pop m0, exit]" + 
				"\nPc    : " + "16" + "\nStack : " + "[-20.0]" + "\nMemory: " + "[0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0]" + 
						"\n------------------------------------------------\n");
	}

}
