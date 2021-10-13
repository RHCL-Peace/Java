import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Double-linked implementation of IndexedUnsortedList.
 * 
 * @author HeshamNatouf
 * 
 */
public class IUDoubleLinkedList<T> implements IndexedUnsortedList<T> {
	private Node<T> head, tail;
	private int size;
	private int modCount;

	/** Creates an empty list */
	public IUDoubleLinkedList() {
		head = tail = null;
		size = 0;
		modCount = 0;
	}

	@Override
	public void addToFront(T element) {
		Node<T> newNode = new Node<T>(element);
		if (isEmpty()) {
			head = tail = newNode;
		} else {
			newNode.setNext(head);
			head.setPrevious(newNode);
			head = newNode;
		}
		size++;
		modCount++;
	}

	@Override
	public void addToRear(T element) {
		add(element);
	}

	@Override
	public void add(T element) {
		Node<T> newNode = new Node<T>(element);
		if (isEmpty()) {
			head = tail = newNode;
		} else {
			tail.setNext(newNode);
			newNode.setPrevious(tail);
			tail = newNode;
		}
		size++;
		modCount++;
	}

	@Override
	public void addAfter(T element, T target) {
		Node<T> targetNode = head;
		while (targetNode != null && !targetNode.getElement().equals(target)) {
			targetNode = targetNode.getNext();
		}
		if (targetNode == null) {
			throw new NoSuchElementException();
		}
		Node<T> newNode = new Node<T>(element);
		newNode.setNext(targetNode.getNext());
		newNode.setPrevious(targetNode);
		targetNode.setNext(newNode);
		if (targetNode != tail) {
			newNode.getNext().setPrevious(newNode);
		} else {
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
		Node<T> current = head;
		for (int i = 0; i < index; i++) {
			current = current.getNext();
		}
		if (index == 0) {
			addToFront(element);
		} else if (index == size) {
			add(element);
		} else {
			Node<T> newNode = new Node<T>(element);
			newNode.setNext(current);
			newNode.setPrevious(current.getPrevious());
			current.getPrevious().setNext(newNode);
			current.setPrevious(newNode);
			size++;
			modCount++;
		}
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
		tail = tail.getPrevious();
		if (tail == null) { // removed only node
			head = null;
		} else {
			tail.setNext(null);
		}
		modCount++;
		size--;
		return retVal;
	}

	@Override
	public T remove(T element) {
		// Option 1, correct, but not as efficifent as we would like
//		int index = indexOf(element);
//		if (index < 0) {
//			throw new NoSuchElementException();
//		}
//		return remove(index);

		Node<T> current = head;
		while (current != null && !current.getElement().equals(element)) {
			current = current.getNext();
		}
		if (current == null) {
			throw new NoSuchElementException();
		}
		if (current != head) {
			current.getPrevious().setNext(current.getNext());
		} else {
			head = head.getNext();
		}
		if (current != tail) {
			current.getNext().setPrevious(current.getPrevious());
		} else {
			tail = tail.getPrevious();
		}
		size--;
		modCount++;
		return current.getElement();

	}

	@Override
	public T remove(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		Node<T> current = head;
		for (int i = 0; i < index; i++) {
			current = current.getNext();
		}
		if (current != head) {
			current.getPrevious().setNext(current.getNext());
		} else {
			head = head.getNext(); // or current.getNext();
		}
		if (current != tail) {
			current.getNext().setPrevious(current.getPrevious());
		} else {
			tail = tail.getPrevious();
		}
		size--;
		modCount++;
		return current.getElement();
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
		Node<T> current = head;
		T retVal = null;
		if (index >= size || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		for (int i = 0; i <= index; i++) {
			if (i == index) {
				retVal = current.getElement();
				i = index + 2;
			}
			current = current.getNext();
		}
		return retVal;
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
		if (isEmpty()) {
			return false;
		}
		Node<T> current = head;
		while (current != null && !current.getElement().equals(target)) {
			current = current.getNext();
		}
		if (current == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public boolean isEmpty() {
		return (head == null);
	}

	@Override
	public int size() {
		return size;
	}

	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("[");
		for (T element : this) {
			str.append(element);
			str.append(", ");
		}
		if (!isEmpty()) {
			str.delete(str.length() - 2, str.length());
		}
		str.append("]");
		return str.toString();
	}

	@Override
	public Iterator<T> iterator() {
		return new DLLIterator();
	}

	@Override
	public ListIterator<T> listIterator() {
		return new DLLIterator();
	}

	@Override
	public ListIterator<T> listIterator(int startingIndex) {
		return new DLLIterator(startingIndex);
	}

	/**
	 * ListIterator for IUDoubleLinkedList
	 */
	private class DLLIterator implements ListIterator<T> {
		private Node<T> nextNode;
		private int nextIndex;
		private int iterModCount;
		private Node<T> lastReturned;

		public DLLIterator() {
			this(0);
		}

		/**
		 * Initialize before startingIndex
		 * @param startingIndex index of next element
		 */
		DLLIterator(int startingIndex) {
			if (startingIndex < 0 || startingIndex > size) {
				throw new IndexOutOfBoundsException();
			}
			nextNode = head;
			nextIndex = 0;
			iterModCount = modCount;
			for (; nextIndex < startingIndex; nextIndex++) {
				nextNode = nextNode.getNext();
			}
		}

		@Override
		public boolean hasNext() {
			if (iterModCount != modCount) {
				throw new ConcurrentModificationException();
			}
			return (nextNode != null);
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			lastReturned = nextNode;
			nextNode = nextNode.getNext();
			nextIndex++;
			return lastReturned.getElement();
		}

		@Override
		public boolean hasPrevious() {
			if (iterModCount != modCount) {
				throw new ConcurrentModificationException();
			}
			return (nextNode != head);
		}

		@Override
		public T previous() {
			if (!hasPrevious()) {
				throw new NoSuchElementException();
			}
			if (nextNode == null) {
				nextNode = tail;
			} else {
				nextNode = nextNode.getPrevious();
			}
			lastReturned = nextNode;
			nextIndex--;
			return lastReturned.getElement();
		}

		@Override
		public int nextIndex() {
			if (iterModCount != modCount) {
				throw new ConcurrentModificationException();
			}
			return nextIndex;
		}

		@Override
		public int previousIndex() {
			if (iterModCount != modCount) {
				throw new ConcurrentModificationException();
			}
			return nextIndex - 1;
		}

		@Override
		public void remove() {
			if (iterModCount != modCount) {
				throw new ConcurrentModificationException();
			}
			if (lastReturned == null) {
				throw new IllegalStateException();
			}
			//remove lastReturned
			if (lastReturned != head) {
			lastReturned.getPrevious().setNext(lastReturned.getNext());
			} else {
				head = head.getNext();
			}
			if (lastReturned  != tail) {
			lastReturned.getNext().setPrevious(lastReturned.getPrevious());
			} else {
				tail = tail.getPrevious();
			}
			if (lastReturned == nextNode) {// last move was previous
				nextNode = nextNode.getNext();
			} else {
				nextIndex--;
			}
			size--;
			modCount++;
			iterModCount++;
			//don't allow duplicate removal
			lastReturned = null;
		}

		@Override
		public void set(T element) {
			if (iterModCount != modCount) {
				throw new ConcurrentModificationException();
			}
			if (lastReturned == null) {
				throw new IllegalStateException();
			}

			lastReturned.setElement(element);
			modCount++;
			iterModCount++;

		}

		@Override
		public void add(T element) {
			if (iterModCount != modCount) {
				throw new ConcurrentModificationException();
			}
			Node<T> newNode = new Node<T>(element);
			newNode.setNext(nextNode);
			if (size == 0) {
				head = tail = newNode;
			} else if (nextIndex == size) {
				tail.setNext(newNode);
				newNode.setPrevious(tail);
				tail = newNode;
			} else if (nextIndex == 0) {
				head.setPrevious(newNode);
				newNode.setNext(head);
				head = newNode;
			} else {
				nextNode.getPrevious().setNext(newNode);
				newNode.setPrevious(nextNode.getPrevious());
				nextNode.setPrevious(newNode);
			}
			size++;
			modCount++;
			iterModCount++;
			nextIndex++;

		}
	}

}