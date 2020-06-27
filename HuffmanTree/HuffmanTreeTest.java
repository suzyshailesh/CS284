package hw5;

//Susmitha Shailesh
//I pledge my honor that I have abided by the Stevens Honor System.

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class HuffmanTreeTest {

	@Test
	void test() {
		String s1 = "This is the first test sentence.";
		HuffmanTree t1 = new HuffmanTree(s1);
		String s2 = "Here is yet another way to test my code!";
		HuffmanTree t2 = new HuffmanTree(s2);
		String s3 = "abcdefghijklmnopqrstuvwxyz";
		HuffmanTree t3 = new HuffmanTree(s3);
		
		assertEquals(s1, t1.decode(t1.encode(s1)));
		assertEquals(s2, t2.decode(t2.encode(s2)));
		assertEquals(s3, t3.decode(t3.encode(s3)));
	}
	
	@Test
	void test2() {
		String s1 = "This is the first test sentence.";
		HuffmanTree t1 = new HuffmanTree(s1);
		String s2 = "Here is yet another way to test my code!";
		HuffmanTree t2 = new HuffmanTree(s2);
		String s3 = "abcdefghijklmnopqrstuvwxyz";
		HuffmanTree t3 = new HuffmanTree(s3);
		
		assertEquals(s1, t1.decode(t1.efficientEncode(s1)));
		assertEquals(s2, t2.decode(t2.efficientEncode(s2)));
		assertEquals(s3, t3.decode(t3.efficientEncode(s3)));
	}
	
	@Test
	void test3() {
		Boolean[] s1 = {true, false};
		HuffmanTree t1 = new HuffmanTree("hello");
		assertEquals(t1.bitsToString(s1), "10");
		
		Boolean[] s2 = {};
		HuffmanTree t2 = new HuffmanTree("hello");
		assertEquals(t2.bitsToString(s2), "");
		
		Boolean[] s3 = {true, true, false, true, false};
		HuffmanTree t3 = new HuffmanTree("hello");
		assertEquals(t3.bitsToString(s3), "11010");
	}

}
