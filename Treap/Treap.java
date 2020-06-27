
//Susmitha Shailesh
//I pledge my honor that I have abided by the Stevens Honor System.

package hw4;

import java.util.Random;
import java.util.Stack;

public class Treap<E extends Comparable<E>> {
	
	private static class Node<E>{
		
		//data fields
		public E data;
		public int priority;
		public Node<E> left;
		public Node<E> right;
		
		public Node(E data, int priority) {
			//constructor
			if(data == null){
				throw new IllegalArgumentException();
			}
			
			this.data = data;
			this.priority = priority;
			this.left = null;
			this.right = null;
		}
		
		//methods
		public Node<E> rotateRight(){
			//node's left child becomes its parent
			//node's right child becomes left child of new parent
			//returns new parent
			Node<E> ret = this.left;
			Node<E> temp = this.left.right;
			this.left = temp;
			ret.right = this;
			return ret;
		}
		
		public Node<E> rotateLeft(){
			//node's right child becomes its parent
			//node's left child becomes right child of new parent
			//returns new parent
			Node<E> ret = this.right;
			Node<E> temp = this.right.left;
			this.right = temp;
			ret.left = this;
			return ret;
		}
		
		public String toString() {
			//toString function
			return "(key=" + data + ", priority=" + priority + ")";
		}
	}
	
	//data fields
	private Random priorityGenerator;
	private Node<E> root;
	
	public Treap(){
		//constructor
		priorityGenerator = new Random();
		root = null;
	}
	
	public Treap(long seed){
		//constructor
		priorityGenerator = new Random(seed);
		root = null;
	}
	
	
	//methods
	
	public boolean add(E key) {
		//calls other add function with random priority
		return add(key, priorityGenerator.nextInt());
	}
	
	public boolean add(E key, int priority) {
		
		//takes in key to add and its priority
		Node<E> toAdd = new Node<E>(key, priority);
		Stack<Node<E>> stack = new Stack<Node<E>>(); //adds path of nodes taken to get to new node's location to a stack
		
		if (root == null) {
			//set node as root if treap is empty
			root = toAdd;
			return true;
		}
		
		else {
			Node<E> check = root;
			while(check.left != null || check.right != null) {
				//inserts node in as a normal binary tree 
				if(key.compareTo(check.data)<0) {
					//if key is smaller than check.data, moves to next node on the left
					if (check.left == null) {
						break;
					}
					stack.push(check);
					check=check.left;
				}
				else if(key.compareTo(check.data)>0) {
					//if key is greater than check.data, moves to next node on right
					if (check.right == null) {
						break;
					}
					stack.push(check);
					check=check.right;
				}
				else {
					//if key is equal to check.data, returns false and doesn't change treap
					return false;
				}
			}
			
			//adds key in correct position and changes appropriate references
			if(key.compareTo(check.data)<0) {
				check.left = toAdd;
			}
			else if(key.compareTo(check.data)>0) {
				check.right = toAdd;
			}
			else {
				return false;
			}
			
			stack.push(check);
			//calls reheap to organize treap based on priorities
			reheap(stack, toAdd);
			//returns true because node has been inserted
			return true;
			
		}
		
	}
	
	public void reheap(Stack<Node<E>> stack, Node<E> toAdd) {		
		//reorganizes treap based on priorities
		while(!stack.isEmpty()) {
			Node<E> top = stack.pop();
			if(top.priority < toAdd.priority) {
				//if node has higher priority than parent, rotates based on data of each
				if(top.data.compareTo(toAdd.data) > 0) {
					toAdd = top.rotateRight();
				}
				else {
					toAdd = top.rotateLeft();
				}
				if(!stack.isEmpty()) {
					//inserts node in correct position
					if (stack.peek().left == top) {
						stack.peek().left = toAdd;
					}
					else {
						stack.peek().right = toAdd;
					}
				}
				else {
					this.root = toAdd;
				}
			}
			else {
				break;
			}
		}
		
	}
	 
	public boolean delete(E key) {
		if(!find(key)) {
			//checks that key is in the treap
			return false;
		}
		
		Node<E> del = root;
		Node<E> top = null;
		
		Stack<Node<E>> stack = new Stack<Node<E>>();
		
		while(del.data != key) {
			//finds the node in treap with data equal to key
			//runs down tree by comparing key with data values
			top = del;
			stack.push(top);
			if(key.compareTo(del.data)>0) {
				del = del.right;
			}
			else {
				del = del.left;
			}
			
		}
		
		if (del.equals(root)) {
			if (del.left == null && del.right == null) {
				root = null;
				return true;
			}
			else if(del.left == null) {
				top = del.rotateLeft();
				root = top;
			}
			else if (del.right == null){
				top = del.rotateRight();
				root = top;
			}
			else if (del.right.priority > del.left.priority) {
				top = del.rotateLeft();
				root = top;
			}
			else {
				top = del.rotateRight();
				root = top;
			}
			stack.push(top);
		}
		while(true) {
			//runs down tree and makes appropriate rotations based on priorities
			//until node to be deleted is a leaf
			if (del.right == null & del.left == null) {
				break;
			}
			else if (del.right == null && del.left != null) {
				if(stack.peek().left != null && stack.peek().left.equals(del)) {
					stack.peek().left = del.left;
				}
				else {
					stack.peek().right = del.left;
				}
				top = del.rotateRight();
				stack.push(top);
			}
			else if(del.left == null && del.right != null){
				if(stack.peek().left != null && stack.peek().left.equals(del)) {
					stack.peek().left = del.right;
				}
				else {
					stack.peek().right = del.right;
				}
				top = del.rotateLeft();
				stack.push(top);
			}
			else if(del.right.priority > del.left.priority) {
				if(stack.peek().left != null && stack.peek().left.equals(del)) {
					stack.peek().left = del.right;
				}
				else {
					stack.peek().right = del.right;
				}
				top = del.rotateLeft();
				stack.push(top);
			}
			else {
				if(stack.peek().left != null && stack.peek().left.equals(del)) {
					stack.peek().left = del.left;
				}
				else {
					stack.peek().right = del.left;
				}
				top = del.rotateRight();
				stack.push(top);
			}
		}
		
		if(top.left != null && top.left.equals(del)) {
			//deletes the proper node
			top.left = null;
		}
		else {
			top.right = null;
		}
		
		return true;
		
	}
	
	
	
	private boolean find(Node <E> root , E key) {
		if (root == null) {
			//returns false if there is nothing in the treap
			return false;
		}
		else if (root.data.equals(key) ) {
			//returns true if key is found at root
			return true;
		}
		else {
			//recursively checks left and right nodes and works its way down tree
			//returns true as soon as key is found somewhere
			return find(root.left, key) || find(root.right, key);
		}
	}
	
	public boolean find(E key) {
		//calls other find function with root this.root
		return find(root, key);
	}
	
	private String toString(int l, Node<E> current) {
		//toString helper function
		
		StringBuilder s = new StringBuilder();
		
		for (int i=0; i<l; i++) {
			s.append("--");
		}
		
		if (current==null) {
			s.append("null\n");
		} else {
			s.append(current.toString()+"\n");
			s.append(toString(l+1,current.left));
			s.append(toString(l+1,current.right));
		}
		return s.toString();
	}
	
	public String toString() {
		//toString function
		
		return toString(0,root);
	}
	
	public static void main(String[]args) {
		Treap<String> testTree = new Treap<String>();
		testTree.add("p",99);
		testTree.add("g",80);
		testTree.add("a",60);
		testTree.add("j",65);
		testTree.add("u",75);
		testTree.add("r",40);
		testTree.add("w",32);
		testTree.add("v",21);
		testTree.add("x",25);
		testTree.add("z",47);
		
		testTree.delete("p");
		System.out.println(testTree);
		
	}
	
}