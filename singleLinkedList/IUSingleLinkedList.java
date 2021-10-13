import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Single-linked node implementation of IndexedUnsortedList.
 * An Iterator with working remove() method is implemented, but
 * ListIterator is unsupported.
 * 
 * @author HeshamNatouf
 * 
 * @param <T> type to store
 */
public class IUSingleLinkedList<T> implements IndexedUnsortedList<T> {
	private Node<T> head, tail;
	private int size;
	private int modCount;
	
	/** Creates an empty list */
	public IUSingleLinkedList() {
		head = tail = null;
		size = 0;
		modCount = 0;
	}

	@Override
	public void addToFront(T element) {
		Node<T> newNode = new Node<T>(element);
		if (isEmpty()) {
			tail = newNode;
		}
		newNode.setNext(head);
		head = newNode;
		size++;
		modCount++;
	}

	@Override
	public void addToRear(T element) {
		Node<T> newNode = new Node<T>(element); 
		if (isEmpty()) {
			head = newNode;
		} else {
				tail.setNext(newNode);
		}
		tail = newNode;
		size++;
		modCount++;
	}

	@Override
	public void add(T element) {
		addToRear(element);
	}

	@Override
	public void addAfter(T element, T target) {
		Node<T> targetNode = head;
		while (targetNode != null && !targetNode.getElement().equals(target)) {
			targetNode = targetNode.getNext();
		}
		if (targetNode == null) { // didn't find target
			throw new NoSuchElementException();
		}
		
		Node<T> newNode = new Node<T>(element);
		newNode.setNext(targetNode.getNext());
		targetNode.setNext(newNode);
		if(targetNode == tail) {
			tail = newNode;
		}
		size++;
		modCount++;
	}

	@Override
	public void add(int index, T element) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		Node<T> newNode = new Node<T>(element);
		if (index == 0) {
			newNode.setNext(head);
			head = newNode;
			if (size == 0) {
				tail = newNode;
			}
		} else {
			Node<T> current = head;
			for (int i = 0; i < index-1; i++) {
				current = current.getNext();
			}
			newNode.setNext(current.getNext());
			current.setNext(newNode);
			
		}
			if (newNode.getNext() == null) {
			tail = newNode;
		}
		size++;
		modCount++;
	}

	@Override
	public T removeFirst() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		T retVal = head.getElement();
		head = head.getNext();
		if (size == 1) { // or head == null
			tail = null; // or tail == head
		}
		size--;
		modCount++;
		return retVal;
		
	}

	@Override
	public T removeLast() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		T retVal = tail.getElement();
		if ( size > 1 ) { 
			Node<T> newTail = head;
			while (newTail.getNext() != tail) {
				newTail = newTail.getNext();
			}
			newTail.setNext(null);
			tail = newTail;
		} else { 
			Node<T> newTail = head;
			for (int i = 0; i < size-1; i++) {
				newTail = newTail.getNext();
			}
			head = tail = null;
		}
		size--;
		modCount++;
		return retVal;
	}

	@Override
	public T remove(T element) {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		T retVal;
		if (head.getElement().equals(element)) {
			retVal = head.getElement();
			if (head == tail) {
				tail = null;
			}
			head = head.getNext();
		} else {
			Node<T> previousNode = head;
			while (previousNode.getNext() != null
					&& !previousNode.getNext().getElement().equals(element)) {
				previousNode = previousNode.getNext();
			}
			if (previousNode == tail) { //did not find it
				throw new NoSuchElementException();
			}
			retVal = previousNode.getNext().getElement();
			previousNode.setNext(previousNode.getNext().getNext());
			if (previousNode.getNext() == null ) { //We have new tail
				tail = previousNode;
			}
		}
		size--;
		modCount++;
		return retVal;
	}

	@Override
	public T remove(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		T retVal;
		if (index == 0) { // remove head
			retVal = head.getElement();
			head = head.getNext();
			if (head == null) {
				tail = null;
			}
		} else {
			Node<T> previous = head;

			for (int i = 0; i < index - 1; i++) {
				previous = previous.getNext();
			}
			if (previous == tail) {
				throw new NoSuchElementException();
			}
			retVal = previous.getNext().getElement();

			previous.setNext(previous.getNext().getNext());

			if (previous.getNext() == null) {
				tail = previous;
			}
		}

		size--;
		modCount++;
		return retVal;
	}

	@Override
	public void set(int index, T element) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		Node<T> current = head;

		if (index == 0) {
			head.setElement(element);
		} else {
			for (int i = 0; i < index; i++) {
				current = current.getNext();
			}

			if (current == tail) {
				tail.setElement(element);
			}
			current.setElement(element);
		}
		modCount++;
	}
		

	@Override
	public T get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		Node<T> current = head;
		for (int i = 0; i < index; i++) {
			current = current.getNext();
		}
		return current.getElement();
	}

	@Override
	public int indexOf(T element) {
		int index = -1;
		Node<T> current = head;
		int curIndex = 0;
		while (index < 0 && current != null) {
			if (element.equals(current.getElement())) {
				index = curIndex;	
			} else {
				current = current.getNext();
				curIndex++;
			}
		}
		return index;
	}

	@Override
	public T first() {
		// head == null
		// isEmpty
		// size == 0
		// size() == 0
		// tail == null
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		return head.getElement();
	}

	@Override
	public T last() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}		
		return tail.getElement();
	}

	@Override
	public boolean contains(T target) {
		return indexOf(target) > -1;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}
	

	@Override
	public int size() {
		return size;
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("[");
		// bad
//		for (int i = 0; i < size; i++) {
//			str.append(this.get(i));
//			str.append(", ");
//		}
		//good 1
//		Iterator<T> it = this.iterator();
//		while (it.hasNext()) {
//			str.append(it.next());
//			str.append(", ");
//		}
		//good 2
		for (T element : this) {
			str.append(element);
			str.append(", ");
		}
		if (!isEmpty()) {
			str.delete(str.length() -2, str.length());
		}
		str.append("]");
		return str.toString();
	}

	@Override
	public Iterator<T> iterator() {
		return new SLLIterator();
	}

	@Override
	public ListIterator<T> listIterator() {
		throw new UnsupportedOperationException();
	}

	@Override
	public ListIterator<T> listIterator(int startingIndex) {
		throw new UnsupportedOperationException();
	}

	/** Iterator for IUSingleLinkedList */
	private class SLLIterator implements Iterator<T> {
		private Node<T> nextNode;
		private int iterModCount;
		private boolean canRemove;
		
		/** Creates a new iterator for the list */
		public SLLIterator() {
			nextNode = head;
			iterModCount = modCount;
			canRemove = false;
		}

		@Override
		public boolean hasNext() {
			if (iterModCount != modCount) {
				throw new ConcurrentModificationException();
			}
			return nextNode != null;
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			T element = nextNode.getElement();
			nextNode = nextNode.getNext();
			canRemove = true;
			return element;
		}
		
		@Override
		public void remove() {
			if (iterModCount != modCount) {
				throw new ConcurrentModificationException();
			}
			if (!canRemove) {
				throw new IllegalStateException();
			}
			canRemove = false;
			if (head == tail) { // or size == 1 or head.getNext() == null
				head = tail = null;
			} else if (head.getNext() == nextNode) {//head removing head element
				head = head.getNext();
			} else {
				Node<T> targetNode = head;
				while (targetNode.getNext().getNext() != nextNode) {
					targetNode = targetNode.getNext();
				}
				targetNode.setNext(nextNode);
				if (nextNode == null) { // removed tail
					tail = targetNode;
				}
			}
			modCount++;
			iterModCount++;
			size--;
		}
	}
}