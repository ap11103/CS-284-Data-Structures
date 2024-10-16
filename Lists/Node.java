package Lists;

import Lists.MySLL.Node;

public class Node<E> {
	
	private E age;
	
	public void add(int index, E item) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException(index);
		}
		//handle the case where we are updating the head of SLL
		if (index == 0) {
			addFirst(item);
		}
		else {
			Node<E> currentNode = head; //start at the head of the list
			//Iterate through the list using the next field
			//Find the Node we want to insert the element after.
			for (int i = 0; i < index - 1; i++) { //notice the index - 1 here
				currentNode = currentNode.next;
			}
			//create a new Node
			Node<E> node = new Node<E>(item, currentNode.next); 
			//next field is whatever follows currentNode
			currentNode.next = node; //insert the new Node after current Node
			size++;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
