package hw4;

//Alisha Patel
//I pledge my honor that I have abided by the Stevens Honor System. 

import java.util.Random;
import java.util.ArrayList;
import java.util.Stack;


public class Treap<E extends Comparable<E>>{
	
	private static class Node<E>{
		
		//data fields for the private node class
		public E data; // key for the search
		public int priority; // random heap priority
		public Node <E> left;
		public Node <E> right;
		
		
		//Constructor
		/**
		 * Creates a new node with the given data and priority
		 * throw exception if the data is null
		 * @param data : used to create the node
		 * @param priority : used to create the node
		 */
		public Node(E data , int priority) {
			if (data == null) {
				throw new IllegalArgumentException("Data can't be null");
			}
			this.data = data;
			this.priority = priority;
			this.left = null;
			this.right = null;
		}
		
		/**
		 * Performs a right rotation based on the figure provided 
		 * (moving the head and the child)
		 * @return the reference to the root of the result (root node)
		 */
		Node <E> rotateRight (){
			if(this.left == null) {
                return this;
            }
			Node<E> newparent = this.left;
			Node<E> newchild = this.left.right;
			newparent.right = this;
			this.left = newchild;
			return newparent;
		}
		
		/**
		 * Performs a left rotation based on the figure provided 
		 * (moving the head and the child)
		 * @return the reference to the root of the result (root node)
		 */
		Node <E> rotateLeft(){
			Node<E> newparent = this.right;
			Node<E> newchild = this.right.left;
			newparent.left = this;
			this.right = newchild;
			return newparent;
		}
		
		/**
		 * returns the pair consisting of the key and it's priority
		 */
		public String toString() {
			return ("(Key = " + data.toString() + " Priority = " + priority + ")");
		}
	}
	
	//data fields for the class
	private Random priorityGenerator;
	private Node <E> root;
	ArrayList<Integer> pList = new ArrayList<Integer>();
	
	
	
	
	//constructors
	
	/**Creates an empty treap
	 * initializes the priority generator using Random
	 */
	public Treap() {
		root = null;
		priorityGenerator = new Random(100);
	}
	
	
	/**Creates an empty treap
	 * Initializes the priority generator using Random
	 * @param seed : argument for the Random
	 */
	public Treap(long seed) {
		root = null;
		priorityGenerator = new Random(seed);
	}
	
	/** 
    * Checks if priorityList (list of all priorities
    * has a certain priority within the list already.
    * @param int priorityKey
    * @returns boolean - true when priority is present, false when not
    */
   boolean array(int priorityKey) {
       for (int i = 0; i < pList.size(); i++) {
           if (pList.get(i) == priorityKey) {
               return true;
           }
       }
       return false;
   }
	
	
	/**
	 * Calls the add(E key, int priority)
	 * Initializes the priorityGenerator with nextInt() 
	 * -> used for calling the other add function
	 * @param key : used for calling the 
	 * @return call for the add with key and initialization of pG
	 */
	public boolean add(E key) {
		int num = priorityGenerator.nextInt(100);
		 while (array(num)) {
	            num = priorityGenerator.nextInt(100);
	            pList.add(num);
	        }
		return add(key, num);
		
	}
	
	
	/**Inserts the given node containing the key as it's data and random priority
	 * Stores each node in the path from the root until the spot
	 * where the new mode will be inserted in stack
	 * calls the helper function reheap to restore the heap variant 
	 * @param key: the data that will be stored
	 * @param priority: random number that will determine it's place(parent, child)
	 * @return true if a node with key was successfully added to the treap or else false
	 */
public boolean add(E key, int priority) {
	Node<E> current = root;

    if (pList.contains(priority)) {
        return false;
    }

    if(root == null) {
        root = new Node<E>(key, priority);
    }

    int diff = key.compareTo(root.data);
   

    Stack<Node<E>> stack = new Stack<Node<E>>();

    while(current != null) {
        if (diff == 0) {
            return false;
        }

        if (diff > 0) { 
            stack.push(current);
            if(current.right == null) {
                current.right = new Node<E>(key, priority);
                current = current.right;
                break;
            }
            else {
                current = current.right;
            }
            diff = key.compareTo(current.data);
        }
        else {
            stack.push(current);
            if(current.left == null) {
                current.left = new Node<E>(key, priority);
                current = current.left;
                break;
            }
            else {
                current = current.left;
            }
            diff = key.compareTo(current.data);
        }
    }

    pList.add(priority);
    reheap(stack, current);
    return true;
		
	}

	/**Helper function that checks the priority
	 * reorders from max to min
	 * @param stack: stack that stores the treap
	 * @param current: takes in the current function
	 * 
	 */
	public void reheap(Stack<Node<E>> stack, Node<E> current) {		
		if(!stack.empty()) {
            Node<E> top = stack.pop();
            while(top.priority < current.priority) {
                if(current.data.compareTo(top.data) < 0) {
                    if(stack.empty()) {
                        this.root = top.rotateRight();
                    }
                    else {
                        if(stack.peek().left == top) {
                            stack.peek().left = top.rotateRight();
                        }
                        else {
                            stack.peek().right = top.rotateRight();
                        }

                    }
                }
                else {
                    if(stack.empty()) {
                        this.root = top.rotateLeft();
                    }
                    else {
                        if(stack.peek().left == top) {
                            stack.peek().left = top.rotateLeft();
                        }
                        else {
                            stack.peek().right = top.rotateLeft();
                        }
                    }
                }
                if(!stack.empty()) {
                    top = stack.pop();
                }
                else {
                    break;
                }
            }
        }

		
	}
	
	
	/**Helper function of the delete
	 * removes it by rotating right or left (depending on the node)
	 * when it becomes the leaf of the last node, it deletes it
	 * @param key: the data which is to be deleted
	 * @param current: acts as the pointer for the stack
	 * @return deletes and node and returns the pointer
	 */
	private Node<E> delete(E key, Node<E> current){
		if (current == null) {
			return current;
		}
		else {
			if (current.data.compareTo(key) < 0) {
				current.right = delete(key, current.right);
			}
			else {
				if(current.data.compareTo(key) > 0) {
					current.left = delete(key, current.left);
				}
				else {
					if (current.right == null) {
						current = current.left;
					}
					else if (current.left == null) {
						current = current.right;
					}
					else {
						if (current.right.priority < current.left.priority) {
							current = current.rotateRight();
							current.right = delete(key, current.right);
						}
						else {
							current = current.rotateLeft();
							current.left = delete(key, current.left);
						}
					}
				}
			}
		}
		return current;
	}
	
	
	/**Uses the helper function
	 * makes the base cases: if the root is null -> false
	 * @param key: the key that is to be deleted
	 * @return true or false based on the bases and the call to the helper function
	 */
	public boolean delete(E key) {
		if (root == null) {
			return false;
		}
		else if (!find(key)) {
			return false;
		}
		else {
			root = delete(key, root);
			return true;
		}
	}
	
	
	/**Finds a node with the key in the treap
	 * it keeps calling the find recursively if the bases cases are not met
	 * @param root: the node that is need to be found
	 * @param key: the key of the node that is need to be found
	 * @return true if the node in the treap, and false otherwise
	 */
	public boolean find(Node<E> root, E key) {
		if (root == null) {
			return false;
		}
		else if (root.data == key) {
			return true;
		}
		else {
			return (find(root.left, key)) & (find(root.right, key));
		}
	}
	
	/**Calls the find function above
	 * @param key: key of the node that needs to be found
	 * @return calls the find function with the root
	 */
	public boolean find(E key) {
		return find(root, key);
	}
	
	
	/**Helper function for the toString function
	 * Uses the current to branch out the treap
	 * Performs a preorder traversal
	 * @param current: node of the treap
	 * @param depth: the height?
	 * @return the string version of the treap
	 */
	private String toString(Node<E> current, int depth) {
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i<depth; i++) {
			sb.append("--");
		}
		
		if (current == null) {
			sb.append("null\n");
		}
		else {
			sb.append(current.toString() + "\n");
			sb.append(toString(current.left, depth+1));
			sb.append(toString(current.right, depth+1));
		}
		return sb.toString();
	}
	
	
	/**Carries out the toString function 
	 * by calling the function above
	 */
	public String toString() {
		return toString(root, 0);
	}
	
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Treap<Integer> testTree = new Treap<Integer>();
		//testTree = new Treap <Integer >();
		//testTree.add(4 ,19);
		//testTree.add(2 ,31);
		//testTree.add(6 ,70);
		//testTree.add(1 ,84);
		//testTree.add(3 ,12);
		//testTree.add(5 ,83);
		//testTree.add(7 ,26);
		
		//System.out.println(testTree.toString());
	}

}
