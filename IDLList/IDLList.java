//Susmitha Shailesh
//I pledge my honor that I have abided by the Stevens Honor System.

package hw2;

import java.util.ArrayList;

public class IDLList<E> {
	
	class Node<E> {
		
		//data fields
		private E data;
		private Node<E> next;
		private Node<E> prev;
		
		//constructors
		Node(E elem){
			data = elem;
			next = null;
			prev = null;
		}
		
		Node(E elem, Node<E> prev, Node<E> next){
			data = elem;
			this.next = next;
			this.prev = prev;
		}
	}
	
	//data fields
	private Node<E> head;
	private Node<E> tail;
	private int size;
	private ArrayList<Node<E>> indices;
	
	//constructors
	public IDLList() {
		head = null;
		tail = null;
		size = 0;
		indices = new ArrayList<Node<E>>();
	}
	
	//methods
	public boolean add(int index, E elem) {
		//adds elem at position index
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		else if(index == 0){
			add(elem);
		}
		else if(index == size) {
			append(elem);
		}
		else {
			Node<E> prev = indices.get(index-1);
			Node<E> insert = new Node<E>(elem, prev, prev.next);
			indices.add(index, insert);
			prev.next.prev= insert;
			prev.next = insert;
			size++;
			
		}
		return true;
	}
	
	public boolean add(E elem) {
		//adds elem to the head
		Node<E> n = new Node<E>(elem);
		if(head == null) {
			head = n;
			tail = n;
			size = 1;
			indices.add(n);
		}
		else {
			n.next = head;
			head.prev = n;
			head = n;
			size++;
			indices.add(0, n);
		}
		
		return true;
	}
	
	public boolean append(E elem) {
		//adds elem as the new last element of the list
		Node<E> n = new Node<E>(elem);
		if(tail == null) {
			tail = n;
			head = n;
			size = 1;
			indices.add(n);
		}
		else {
			n.prev = tail;
			tail.next = n;
			tail = n;
			size++;
			indices.add(n);
		}
		return true;
	}
	
	public E get(int index) {
		//returns object at position index
		if(index < 0 || index > size-1) {
			throw new IndexOutOfBoundsException();
		}
		else {
			return indices.get(index).data;
		}
		
	}
	
	public E getHead() {
		//returns object at the head
		if (head == null) {
			throw new NullPointerException();
		}
		else {
			return head.data;
		}
	}
	
	public E getLast() {
		//returns object at the tail
		if (tail == null) {
			throw new NullPointerException();
		}
		else {
			return tail.data;
		}
	}
	
	public int size() {
		//returns list size
		return size;
	}
	
	public E remove() {
		//removes and returns element at head
		if (head == null) {
			throw new NullPointerException();
		}
		else {
			E temp = head.data;
			head = head.next;
			head.prev = null;
			size--;
			indices.remove(0);
			return temp;
		}
	}
	
	public E removeLast() {
		//removes and returns element at tail
		if (tail == null) {
			throw new NullPointerException();
		}
		else if (size == 1){
			E temp = tail.data;
			tail = null;
			head = null;
			size = 0;
			indices.remove(0);
			return temp;
		}
		else {
			E temp = tail.data;
			tail = tail.prev;
			tail.next = null;
			indices.remove(size-1);
			size--;
			return temp;
		}
	}
	
	public E removeAt(int index) {
		//removes and returns the element at index
		if (head == null ||tail == null) {
			throw new NullPointerException();
		}
		else if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		else if (index == 0) {
			E e = remove();
			return e;
		}
		else if (index == size-1) {
			E e = removeLast();
			return e;
		}
		else {
			Node<E> temp = indices.get(index);
			Node<E> prev = temp.prev;
			Node<E> next = temp.next;
			
			prev.next = next;
			next.prev = prev;
			
			indices.remove(index);
			size--;
			return temp.data;
			}
		}
	
	public boolean remove(E elem) {
		//removes first occurance of elem in the list
		if(size == 0) {
			return false;
		}
		else {
			int i = 0;
			while(i < size && indices.get(i).data != elem) {
				i++;
			}
			if(i == size) {
				return false;
			}
			else {
				this.removeAt(i);
				return true;
			}
		}
	}
	
	public String toString() {
		//toString method
		StringBuilder s = new StringBuilder();
		
		s.append("[");
		for (int i = 0; i < size; i++) {
			s.append(indices.get(i).data);
			s.append(";");
		}
		s.append("]");
		return s.toString();
	}
	
}


