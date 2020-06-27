
//Susmitha Shailesh
//I pledge my honor that I have abided by the Stevens Honor System.

package hw4;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TreapTest {

	@Test
	void test1() {
		Treap<Integer> testTree = new Treap<Integer>();
		assertTrue(testTree.add(4,19));
		assertTrue(testTree.add(2,31));
		assertTrue(testTree.add(6,70));
		assertTrue(testTree.add(1,84));
		assertTrue(testTree.add(3,12));
		assertTrue(testTree.add(5,83));
		assertTrue(testTree.add(7,26));
		
		assertFalse(testTree.add(6));
		assertTrue(testTree.find(6));
		assertFalse(testTree.find(9));
		assertFalse(testTree.delete(9));
		assertTrue(testTree.delete(6));
		assertFalse(testTree.find(6));
	}
	
	@Test
	void test2() {
		Treap<String> testTree = new Treap<String>();
		assertTrue(testTree.add("p",99));
		assertTrue(testTree.add("g",80));
		assertTrue(testTree.add("a",60));
		assertTrue(testTree.add("j",65));
		assertTrue(testTree.add("u",75));
		assertTrue(testTree.add("r",40));
		assertTrue(testTree.add("w",32));
		assertTrue(testTree.add("v",21));
		assertTrue(testTree.add("x",25));
		assertTrue(testTree.add("z",47));
		
		assertTrue(testTree.find("p"));
		assertTrue(testTree.delete("p"));
		assertFalse(testTree.find("p"));
		
	}
	
	@Test
	void test3() {
		Treap<String> testTree = new Treap<String>();
		assertTrue(testTree.add("p",99));
		
		assertTrue(testTree.find("p"));
		assertTrue(testTree.delete("p"));
		assertFalse(testTree.find("p"));
	}

}
