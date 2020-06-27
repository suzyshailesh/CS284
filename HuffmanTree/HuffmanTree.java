package hw5;

//Susmitha Shailesh
//I pledge my honor that I have abided by the Stevens Honor System.


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/*
 * Instructions: 
 * First: Read through the assignment specification, make sure you understand what the assignment is asking for.
 * Second: There are number of "TODO" instructions within this code, make sure to complete all of them fully.
 * Third: Test you code.
 */


// TODO: Name and Pledge

// Pledge: I pledge my honor that I have abided by the Stevens Honor System.
// Name: Susmitha Shailesh


/**
 * HW4 CS284 Spring 2019
 * Implements a Huffman encoding tree.
 * The included code has been commented for the student's benefit, feel free to read through it.
 */
public class HuffmanTree {

	// ******************** Start of Stub Code ******************** //
	// ************************************************************ //
    /** Node<E> is an inner class and it is abstract.
     * There will be two kinds
     * of Node, one for leaves and one for internal nodes. */
    abstract static class Node implements Comparable<Node>{
        /** The frequency of all the items below this node */
        protected int frequency;
        
        public Node(int freq) {
        	this.frequency = freq;
        }
        
		/** Needed for the Minimum Heap used later in this stub. */
		public int compareTo(Node other) {
			return this.frequency - other.frequency;
		}
    }
    /** Leaves of a Huffman tree contain the data items */
    protected static class LeafNode extends Node {
        // Data Fields
        /** The data in the node */
        protected char data;
        /** Constructor to create a leaf node (i.e. no children) */
        public LeafNode(char data, int freq) {
            super(freq);
            this.data = data;
        }
        /** toString method */
        public String toString() {
            return "[value= "+this.data + ",freq= "+frequency+"]";
        }
    }
    /** Internal nodes contain no data,
     * just references to left and right subtrees */
    protected static class InternalNode extends Node {
        /** A reference to the left child */
        protected Node left;
        /** A reference to the right child */
        protected Node right;

        /** Constructor to create an internal node */
        public InternalNode(Node leftC, Node rightC) {
            super(leftC.frequency + rightC.frequency);
            left = leftC; right = rightC;
        }
        public String toString() {
            return "(freq= "+frequency+")";
        }
    }
	
	// Enough space to encode all "extended ascii" values
	// This size is probably overkill (since many of the values are not "printable" in the usual sense)
	private static final int codex_size = 256;
	
	/* Data Fields for Huffman Tree */
	private Node root;
	
	public HuffmanTree(String s) {
		root = buildHuffmanTree(s);
	}
	
	/**
	 * Returns the frequencies of all characters in s.
	 * @param s
	 * @return
	 */
	public static int[] frequency(String s) {
		int[] freq = new int[codex_size];
		for (char c: s.toCharArray()) {
			freq[c]++;
		}
		return freq;
	}
	
	/**
	 * Builds the actual Huffman tree for that particular string.
	 * @param s
	 * @return
	 */
	private static Node buildHuffmanTree(String s) {
		int[] freq = frequency(s);
		
		// Create a minimum heap for creating the Huffman Tree
		// Note to students: You probably won't know what this data structure
		// is yet, and that is okay.
		PriorityQueue<Node> min_heap = new PriorityQueue<Node>();
		
		// Go through and create all the nodes we need
		// as in, all the nodes that actually appear in our string (have a frequency greater then 0)
		for(int i = 0; i < codex_size; i++) {
			if (freq[i] > 0) {
				// Add a new node (for that character) to the min_heap, notice we have to cast our int i into a char.
				min_heap.add(new LeafNode((char) i, freq[i]));
			}
		}
		
		// Edge case (string was empty)
		if (min_heap.isEmpty()) {
			throw new NullPointerException("Cannot encode an empty String");
		}
		
		// Now to create the actual Huffman Tree 
		// NOTE: this algorithm is a bit beyond what we cover in cs284, 
		// you'll see this in depth in cs385
		
		// Merge smallest subtrees together
		while (min_heap.size() > 1) {
			Node left = min_heap.poll();
			Node right = min_heap.poll();
			Node merged_tree = new InternalNode(left, right);
			min_heap.add(merged_tree);
		}
		
		// Return our structured Huffman Tree
		return min_heap.poll();
	}
	
	// ******************** End of Stub Code ******************** //
	// ********************************************************** //
	
	
	public String bitsToString(Boolean[] encoding) {
		//represent bit strings as array of booleans
		String ret = "";
		for(int i = 0; i < encoding.length; i++) {
			//adds 1 to String if bit is true, 0 if false
			if(encoding[i] == true) {
				ret = ret + "1";
			}
			else {
				ret = ret + "0";
			}
		}
		return ret;
	}
	
	public String toString() {
		// toString method
		return toString(0, root);
	}
	
	private String toString(int l, Node current) {
		//helper function for toString, does preOrderTraversal
		StringBuilder s = new StringBuilder();
		
		for (int i=0; i<l; i++) {
			//adds appropriate indents
			s.append("	");
		}
		
		if (current==null) {
			s.append("null\n");
		} else {
			if(current instanceof InternalNode) {
				//preOrderTraversal
				s.append(current.toString()+"\n");
				s.append(toString(l+1,((InternalNode)current).left));
				s.append(toString(l+1,((InternalNode)current).right));
			}
			else {
				s.append(current.toString()+"\n");
			}
		}
		return s.toString();
		
	}
	
	public String decode(Boolean[] coding) {
		Node curr = root;
		String ret = "";
		for(int i = 0; i < coding.length ; i++) {
			//follows path down tree based on boolean values
			if(coding[i] == false) {
				curr = ((InternalNode)curr).left;
			}
			else {
				curr = ((InternalNode)curr).right; 
			}
			if(curr instanceof LeafNode) {
				//when leaf node is reached, node's data is added to String to return
				//curr is reset to root to start finding next leaf node
				ret = ret + ((LeafNode)curr).data;
				curr = this.root;
			}
		}
		if(curr != root) {
			//curr should be back at root at the end
			throw new IllegalArgumentException();
		}
		
		return ret;
	}
	
	public Boolean[] encode(String inputText) {
		// TODO Complete encode method
		ArrayList<Boolean> ret = new ArrayList<Boolean>();
	
		for(int i = 0; i < inputText.length(); i++) {
			//for each char in the String, adds its path to ret 
			//calls findPath to find its path
			//gives error if char is not in tree
			ArrayList<Integer> arr = new ArrayList<Integer>();
			arr = findPath(inputText.charAt(i), this.root, new ArrayList<Integer>());
			if(arr.isEmpty()) {
				throw new IllegalArgumentException(); 
			}
			else {
				for(int j = 0; j < arr.size(); j++) {
					//converts ints to boolean values
					if(arr.get(j) == 0) {
						ret.add(false);
					}
					else {
						ret.add(true);
					}
				}
			}
		}
		
		Boolean[] fin = new Boolean[ret.size()];
		
		for(int i = 0; i < ret.size(); i++) {
			//adds from ArrayList to Array to return
			fin[i] = ret.get(i);
		}
		
		return fin; 
	}
	
	private ArrayList<Integer> findPath(char a, Node curr, ArrayList<Integer> arr) {
		//helper function for encode and efficientEncode
		//finds the path to get to the node, if it exists, in integers
		if(curr instanceof LeafNode && ((LeafNode)curr).data == a) {
			return arr;
		}
		else if(curr instanceof LeafNode && ((LeafNode)curr).data != a) {
			return new ArrayList<Integer>();
		}
		else {
			//checks to see which subtree the char is in
			//returns empty ArrayList if it's not in either subtree
			if(find(a, ((InternalNode)curr).left) == true) {
				//adds 0 if char found in left subtree, recurse to the left
				arr.add(0);
				arr = findPath(a, ((InternalNode)curr).left, arr);
			}
			else if(find(a, ((InternalNode)curr).right) == true) {
				//adds 1 if char found in right subtree, recurse to the right
				arr.add(1);
				arr = findPath(a, ((InternalNode)curr).right, arr);
			}
			else {
				return new ArrayList<Integer>();
			}
			
			return arr;
		}
	}
	
	private Boolean find(char a, Node curr) {
		//preorder traversal to find if char is anywhere in the tree
		if(curr == null) {
			return false;
		}
		else if(curr instanceof LeafNode && ((LeafNode)curr).data == a) {
			return true;
		}
		else if(curr instanceof LeafNode && ((LeafNode)curr).data != a) {
			return false;
		}
		else {
			return find(a, ((InternalNode)curr).left) || find(a, ((InternalNode)curr).right);
		}
	}
	
	public Boolean[] efficientEncode(String inputText) {
		// TODO Complete efficientEncode method
		// NOTE: Should only go through the tree once.
		//encode using memoization
		Map<Character, ArrayList<Integer>> memo = new HashMap<>();
		
		ArrayList<Boolean> ret = new ArrayList<Boolean>();
		for(int i = 0; i < inputText.length(); i++) {
			//goes through every char in inputText
			ArrayList<Integer> arr = new ArrayList<Integer>();
			
			if(memo.containsKey(inputText.charAt(i))) {
				//checks to see if path to char already exists in memo
				arr = memo.get(inputText.charAt(i));
			}
			else {
				//if path to char does not exist in memo, finds the path and adds it to memo
				arr = findPath(inputText.charAt(i), this.root, new ArrayList<Integer>());
				memo.put(inputText.charAt(i), arr);
			}
			
			if(arr.isEmpty()) {
				throw new IllegalArgumentException(); 
			}
			else {
				for(int j = 0; j < arr.size(); j++) {
					//converts ints to boolean values
					if(arr.get(j) == 0) {
						ret.add(false);
					}
					else {
						ret.add(true);
					}
				}
			}
		}
		
		Boolean[] fin = new Boolean[ret.size()];
		
		for(int i = 0; i < ret.size(); i++) {
			//adds from ArrayList to Array to return
			fin[i] = ret.get(i);
		}
		
		return fin; 
		
	}
	
	public static void main(String[] args) {
			
		// Code to see if stuff works...
		String s = "abcde";
		HuffmanTree t = new HuffmanTree(s); // Creates specific Huffman Tree for "s"
		// Now you can use encode, decode, and toString to interact with your specific Huffman Tree
		System.out.println(Arrays.toString(t.encode("abcde")));
		System.out.println(t.decode(t.encode("abcde")));
		
	}
}
