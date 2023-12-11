package assignment2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyDoublyLinkedList<E> extends MyLinkedList<E>{
	private DNode head;
	private DNode tail;
	
	
	/*
	 * ADD YOUR CODE HERE
	 */

	public void add(E element){
		this.addLast(element);
	}

	public void clear(){
		head = null; // Find out if "this" keyword is needed for head and tail
		tail = null;
		size = 0;
	}

	public E remove(){
		return this.removeLast();
	}

	public void addFirst(E ele){ // Logic checked
		DNode newNode = new DNode();
		newNode.element = ele;

		if (head != null){
			newNode.next = head;
			head.prev = newNode;
		}
		else{
			tail = newNode;
		}

		head = newNode;
		size += 1;
	}

	public void addLast(E ele){ // Logic Checked
		DNode newNode = new DNode();
		newNode.element = ele;

		if (tail != null){
			newNode.prev = tail;
			tail.next = newNode;
		}
		else{
			head = newNode;
		}

		tail = newNode;
		size += 1;
	}

	public E removeFirst(){
		if (head == null || tail == null){
			throw new NoSuchElementException("The list is empty");
		}
		else if (size == 1){ // Not sure if this is how size should be accessed
			E ele = head.element;
			clear();
			return ele;
		}

		DNode oldHead = head;
		head = oldHead.next;

		if (head != null){
			head.prev = null;
		}
		else {
			tail = null;
		}

		oldHead.next = null;
		size -= 1;

		return oldHead.element;
	}

	public E removeLast(){
		if (head == null || tail == null){
			throw new NoSuchElementException("The list is empty");
		}
		else if (size == 1){
			E ele = tail.element;
			clear();
			return ele;
		}


		DNode oldTail = tail;
		tail = oldTail.prev;

		if (tail != null){
			tail.next = null;
		}
		else {
			head = null;
		}

		oldTail.prev = null;
		size -= 1;

		return oldTail.element;
	}

	public E peekFirst(){
		if (size == 0){
			throw new NoSuchElementException("The list is empty");
		}

		return head.element;
	}

	public E peekLast(){
		if (size == 0){
			throw new NoSuchElementException("The list is empty");
		}

		return tail.element;
	}

	public boolean equals(Object obj){
		if (obj == null){
			return false;
		}

		if (!(obj instanceof MyDoublyLinkedList<?>)){
			return false;
		}

		MyDoublyLinkedList<E> other = (MyDoublyLinkedList<E>) obj;

		if (this.getSize() != other.getSize()) {
			return false;
		}

		Iterator<E> thisIterator = this.iterator();
		Iterator<E> otherIterator = other.iterator();

		while (thisIterator.hasNext() && otherIterator.hasNext()){
			E thisEle = thisIterator.next();
			E otherEle = otherIterator.next();

			if (!(thisEle.equals(otherEle))){
				return false;
			}
		}

		return !(thisIterator.hasNext() || otherIterator.hasNext());

		/*if (thisIterator.hasNext() || otherIterator.hasNext()){
			return false;
		}

		return true;*/
	}

	//######################### CODE ENDS HERE #########################
	public Iterator<E> iterator() {
		return new DLLIterator();
	}

	private class DNode {
		private E element;
		private DNode next;
		private DNode prev;
	}

	private class DLLIterator implements Iterator<E> {
		DNode curr;

		public DLLIterator() {
			this.curr = head;
		}

		public boolean hasNext() {
			return this.curr != null;
		}

		public E next() {
			if (!this.hasNext())
				throw new NoSuchElementException();

			E element = this.curr.element;
			this.curr = this.curr.next;
			return element;
		}
	}
}
