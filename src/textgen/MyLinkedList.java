package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// TODO: Implement this method
		size = 0;
		head = new LLNode<E>();
		tail = new LLNode<E>();
		head.next = tail;
		tail.prev = head;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) throws IndexOutOfBoundsException, NullPointerException
	{
		// TODO: Implement this method
		if (element == null) {
			throw new NullPointerException("Last object cannot store null pointers.");
		}
		new LLNode<E>(element, tail.prev, tail) ;
		this.size ++;
		
		return false;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) throws IndexOutOfBoundsException, NullPointerException
	{
		// TODO: Implement this method.
		LLNode<E> node = this.head.next;
		if (index < 0) {
			throw new IndexOutOfBoundsException();
		}
		for (int i = 0; i < index; i++) {
			node = node.next;
			if (node.next == null) {
				break;
			}
		}
		if (node.next == null) {
			throw new IndexOutOfBoundsException();
		}

		return node.data;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) throws IndexOutOfBoundsException, NullPointerException
	{
		// TODO: Implement this method
		if (element == null) {
			throw new NullPointerException("Last object cannot store null pointers.");
		}
		

		if (index < 0) {
			throw new IndexOutOfBoundsException();
		}
		
		if (index > this.size()) {
			throw new IndexOutOfBoundsException();
		}
		
		LLNode<E> node = this.head.next;
		if (index != 0) {
			for (int i = 0; i < index; i++) {
				node = node.next;
				if (node.next == null) {
					break;
				}
			}
		
		if (node.next == null) {
			throw new IndexOutOfBoundsException();
		}
		new LLNode<E>(element, node.prev, node.next);
			
		}
		else {
			new LLNode<E>(element, node.prev, node);
		}
		
				
		this.size ++;

	}


	/** Return the size of the list */
	public int size() 
	{
		// TODO: Implement this method
		LLNode<E> node = this.head.next;
		int tempSize = 0;
		while (node.next != null) {
			tempSize ++;
			node = node.next;
		}
		return tempSize;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) throws IndexOutOfBoundsException, NullPointerException
	{
		// TODO: Implement this method
		LLNode<E> nodeTemp = new LLNode<E>();
		LLNode<E> node = this.head.next;
		if (index < 0) {
			throw new IndexOutOfBoundsException();
		}
		
		for (int i = 0; i < index; i++) {
			node = node.next;
			if (node.next == null) {
				break;
			}
		}
		
		if (node.next == null) {
			throw new IndexOutOfBoundsException();
		}
		
		nodeTemp.data = node.data;
		
		node.next.prev = node.prev;
		node.prev.next = node.next;
		this.size --;
		
		return nodeTemp.data;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) throws IndexOutOfBoundsException, NullPointerException
	{
		// TODO: Implement this method
		if (element == null) {
			throw new NullPointerException("Last object cannot store null pointers.");
		}

		if (index < 0) {
			throw new IndexOutOfBoundsException();
		}
		LLNode<E> nodeTemp = new LLNode<E>();
		LLNode<E> node = this.head.next;
		for (int i = 0; i < index; i++) {
			node = node.next;
			if (node.next == null) {
				break;
			}
		}
		
		if (node.next == null) {
			throw new IndexOutOfBoundsException();
		}
		
		nodeTemp.data = node.data;
		node.data = element;
		return nodeTemp.data;
	}   
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode() 
	{
		this.prev = null;
		this.next = null;
		this.data = null;
	}
	
	public LLNode(E e) 
	{
		this.data = e;

	}
	
	public LLNode(E e, LLNode<E> prevNode, LLNode<E> nextNode) 
	{
		this(e);
		this.next = prevNode.next;
		this.prev = this.next.prev;
		prevNode.next = this;
		this.next.prev = this;
		
	}

}
