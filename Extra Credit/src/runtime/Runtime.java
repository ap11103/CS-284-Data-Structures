package runtime;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;


public class Runtime {
	// data fields
	private ArrayList<Instruction> pgm;
	private int pc;
	private Stack<Double> stack;
	private ArrayList<Double> memory;
	
	Runtime() {
		stack = new Stack<>();
		memory = new ArrayList<>(10);	
	}
	
	/**
	 * Set SBM to initial state
	 */
	private void initialize() {
		// empty stack
		while (!stack.isEmpty()) {
			stack.pop();
		}
		// reset all memory locations
		for (int i=0; i<10; i++) {
			memory.add(0.0);
		}
		// set pc to first instructionq
		pc=1;
	}
	
	/**Throws an illegal error with message if the labelName is not declared
	 * The function uses for each loop to iterate through the labels
	 * @param label: the input string as where the labelName is accessed
	 * @return the code line where the labelName is declared
	 */
     private int jumpToLabel(String label) {
    	 int code = -1;
    	 for (Instruction labelName: pgm) {
    		 if (labelName instanceof Label) {
    			 if(((Label)labelName).getName().equals(label)) {
    				 code = pgm.indexOf(labelName); 
    			 }
    		 }	 
    	 }
    	 
    	 if (code == -1) {
    		 throw new IllegalStateException("runtime: label not found");
    	 }
    	 return code;
	 
	}
	
     
     /**Gives an instruction to the method accordingly to the table provided
      * this is based on the code, instead of the string 
      * this method updates the memory, stack, and program accordingly.
      * @return true for all instruction and execute the method, 
      * unless the instruction is exit, in which it returns false
     */
	private boolean processInstruction(Instruction i) {
	 
	 switch (i.code) {
	 
	 case 0:
		pc++;
	 	return false;
	 case 1:
	 	stack.push(((PushLiteral)i).getLiteral());
	 	pc++;
	 	return true;
	 case 2:
	 	stack.push(memory.get(((PushLocation)i).getAddress()));
	 	 pc++;
	 	return true;
	 case 3:
		 memory.set(((Pop)i).getAddress(), stack.pop());
		 pc++;
		 return true;
	case 4:
		double one = stack.pop();
		double two = stack.pop();
		double add = one + two;
		stack.push(add);
		pc++;
		return true;
		
	case 5: 
		double three = stack.pop();
		double four = stack.pop();
		double sub = four - three;
		stack.push(sub);
		pc++;
		return true;
	case 6: 
		double six = stack.pop();
		double seven = stack.pop();
		double mul = six * seven;
		stack.push(mul);
		pc++;
		return true;
	case 7:
		double five = stack.pop();
		double eight = stack.pop();
		double div = eight / five;
		stack.push(div);
		pc++;
		return true;
		
	case 8:
		
		Label name = new Label(((Label)i).code, ((Label)i).mnemonic, ((Label)i).getName()) ;
		pc++;
		return true;
	case 9:
		if (stack.peek() == 0){
			pc = jumpToLabel(((Jmpz)i).getTargetLabel());
		}
		pc++;
		return true;
	case 10:
		pc = jumpToLabel(((Jmp)i).getTargetLabel());
		pc++;
		return true;
	case 11:
		double value = stack.pop() - 1;
		stack.push(value);
		pc++;
		return true;
	  
	 }
	 return false;
	}
	
	
	/**Method executes the program stored in the data field pgm
	 * Initializes the SMB through the given method
	 * Processes each instruction until it hits the exit
	 * then it stops, the checker is when it hits the false boolean
	 */
	public void run() {
		initialize();
		boolean k = true;
		while (k) {
			k = processInstruction(pgm.get(pc-1));
		}

	}
	
	private Instruction parseInstruction(String str, int line) {
		String[] p = str.split("[ ]+"); // delimiters are non-empty sequences of spaces
		Instruction i=null;

		switch (p[0]) {
		case "exit":
			i = new Exit(0,"exit");
			break;
		case "push": 
			if (p.length==1) {
				throw new IllegalStateException("parseInstruction: syntax error at line "+line);
			}
			try {
			if (p[1].charAt(0)=='m') {
				int loc = Integer.parseInt(p[1].substring(1));
				if (loc<0 || loc>9) {
					throw new IllegalStateException("parseInstruction: syntax error at line "+line);
				}
				i = new PushLocation(2,"push",loc);
			} else {
				i = new PushLiteral(1,"push",Double.parseDouble(p[1]));
			}
			} catch (NumberFormatException e) {
				throw new IllegalStateException("parseInstruction: syntax error at line "+line);
			}
			break;
		case "pop": 
			if (p.length==1) {
				throw new IllegalStateException("parseInstruction: syntax error at line "+line);
			}
			int loc = Integer.parseInt(p[1].substring(1));
			if (loc<0 || loc>9) {
				throw new IllegalStateException("parseInstruction: syntax error at line "+line);
			}
			i = new Pop(3,"pop",loc);
			break;
		case "add": 
			i = new Add(4,"add");
			break;
		case "sub": 
			i = new Sub(5,"sub");
			break;
		case "mul": 
			i = new Mul(6,"mul");
			break;
		case "div": 
			i = new Div(7,"div");
			break;
		case "label":
			i = new Label(8,"label",p[1]);
			break;
		case "jmpz":
			i = new Jmpz(9,"jmpz",p[1]);
			break;
		case "jmp":
			i = new Jmp(10,"jmp",p[1]);
			break;
		case "dec":
			i = new Dec(11,"dec");
			break;
		case "":
			break;
		default:
			throw new IllegalStateException("parseInstruction: syntax error at line "+line);
		}
		return i;
	}
	
	public void readFromFile(String name)  {
		pgm = new ArrayList<>();
		File f = new File(name);
		try {
			Scanner s = new Scanner(f);
			int line=1;
			
			while (s.hasNext()) {
				Instruction i = parseInstruction(s.nextLine(),line);
				if (i!=null) {
					pgm.add(i);
				}
				line++;
			}
			s.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String toString() {
		StringBuilder r = new StringBuilder();
		r.append("Pgm   : ");
		r.append(pgm==null?"null":pgm.toString());
		r.append("\nPc    : "+pc);
		r.append("\nStack : ");
		r.append(stack.toString());
		r.append("\nMemory: ");
		r.append(memory.toString());
		r.append("\n------------------------------------------------\n");
		
		return r.toString();
	}
	
	public static void main(String[] args) {
		Runtime r = new Runtime();
		r.readFromFile("eg1.pgm"); // Load and parse mini-bytecode program
		r.run(); // execute program
		System.out.println(r);  // print resulting state of the SBM
	}
}

