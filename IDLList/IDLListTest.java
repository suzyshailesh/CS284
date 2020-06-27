//Susmitha Shailesh
//I pledge my honor that I have abided by the Stevens Honor System.

package hw2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class IDLListTest {

	@Test
	void testAddIndex() {
		IDLList<Integer> lst = new IDLList<Integer>();
		assertThrows(IndexOutOfBoundsException.class, () -> lst.add(1,1));
		assertThrows(IndexOutOfBoundsException.class, () -> lst.add(-1,1));
		
		assertEquals(lst.add(0,1), true);
		assertEquals(lst.toString(), "[1;]");
		
		assertEquals(lst.add(0,2), true);
		assertEquals(lst.toString(), "[2;1;]");
	}
	
	@Test
	void testAdd() {
		IDLList<Integer> lst = new IDLList<Integer>();
		
		assertEquals(lst.add(1), true);
		assertEquals(lst.toString(), "[1;]");
		
		assertEquals(lst.add(2), true);
		assertEquals(lst.toString(), "[2;1;]");

	}
	
	@Test
	void testAppend() {
		IDLList<Integer> lst = new IDLList<Integer>();
		
		assertEquals(lst.append(1), true);
		assertEquals(lst.toString(), "[1;]");
		
		assertEquals(lst.append(2), true);
		assertEquals(lst.toString(), "[1;2;]");
	}
	
	@Test
	void testGet() {
		IDLList<Integer> lst = new IDLList<Integer>();
		
		assertThrows(IndexOutOfBoundsException.class, () -> lst.get(-1));
		assertThrows(IndexOutOfBoundsException.class, () -> lst.get(1));
		
		lst.add(1);
		
		assertEquals((int)lst.get(0), 1);
	}
	
	@Test
	void testGetHead() {
		IDLList<Integer> lst = new IDLList<Integer>();
		
		assertThrows(NullPointerException.class, () -> lst.getHead());
		
		lst.append(1);
		assertEquals((int)lst.getHead(), 1);
		
		lst.add(2);
		assertEquals((int)lst.getHead(), 2);
		
		lst.remove();
		assertEquals((int)lst.getHead(), 1);
	}
	
	@Test
	void testGetLast() {
		IDLList<Integer> lst = new IDLList<Integer>();
		
		assertThrows(NullPointerException.class, () -> lst.getLast());
		
		lst.append(1);
		assertEquals((int)lst.getLast(), 1);
		
		lst.add(2);
		assertEquals((int)lst.getLast(), 1);
		
		lst.removeLast();
		assertEquals((int)lst.getLast(), 2);
	}
	
	@Test
	void testSize() {
		IDLList<Integer> lst = new IDLList<Integer>();
		
		assertEquals(lst.size(), 0);
		
		lst.add(1);
		assertEquals(lst.size(), 1);
		
		lst.append(2);
		assertEquals(lst.size(), 2);
		
		lst.removeLast();
		assertEquals(lst.size(), 1);
	}
	
	@Test
	void testRemove() {
		IDLList<Integer> lst = new IDLList<Integer>();
		
		assertThrows(NullPointerException.class, () -> lst.remove());
		
		lst.append(1);
		lst.append(2);
		
		assertEquals((int)lst.remove(), 1);
		assertEquals(lst.toString(), "[2;]");
	}
	
	@Test
	void testRemoveLast() {
		IDLList<Integer> lst = new IDLList<Integer>();
		
		assertThrows(NullPointerException.class, () -> lst.removeLast());
		
		lst.append(1);
		lst.append(2);
		
		assertEquals((int)lst.removeLast(), 2);
		assertEquals(lst.toString(), "[1;]");
		assertEquals((int)lst.removeLast(), 1);
	}
	
	@Test
	void testRemoveAt() {
		IDLList<Integer> lst = new IDLList<Integer>();
		
		assertThrows(NullPointerException.class, () -> lst.removeAt(1));
		assertThrows(NullPointerException.class, () -> lst.removeAt(-1));
		
		lst.append(1);
		lst.append(2);
		
		assertEquals((int)lst.removeAt(1), 2);
		
	}
	
	@Test
	void testRemoveAnElem() {
		IDLList<Integer> lst = new IDLList<Integer>();
		
		assertEquals(lst.remove(1), false);
		
		lst.append(1);
		lst.append(2);
		
		assertEquals(lst.remove(1), true);
		assertEquals(lst.toString(), "[2;]");
		assertEquals(lst.remove(3), false);
	}

}
