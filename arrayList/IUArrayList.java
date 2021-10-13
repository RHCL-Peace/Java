import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Array-based implementation of IndexedUnsortedList.
 * An Iterator with working remove() method is implemented, but
 * ListIterator is unsupported. 
 * 
 * @author 
 *
 * @param <T> type to store
 */
public class IUArrayList<T> implements IndexedUnsortedList<T> {
	private static final int DEFAULT_CAPACITY = 10;
	private static final int NOT_FOUND = -1;
	
	private T[] array;
	private int rear;
	private int modCount;
	
	/** Creates an empty list with default initial capacity */
	public IUArrayList() {
		this(DEFAULT_CAPACITY);
	}
	
	/** 
	 * Creates an empty list with the given initial capacity
	 * @param initialCapacity
	 */
	@SuppressWarnings("unchecked")
	public IUArrayList(int initialCapacity) {
		array = (T[])(new Object[initialCapacity]);
		rear = 0;
		modCount = 0;
	}
	
	/** Double the capacity of array */
	private void expandCapacity() {
		if (array.length == rear) { //we are out of room
		array = Arrays.copyOf(array, array.length*2);
		}
	}

	@Override
	public void addToFront(T element) {
		expandCapacity();
		add(0, element);
	}

	@Override
	public void addToRear(T element) {
		expandCapacity();
		array[rear] = element;
		rear++;
		modCount++;
	}

	@Override
	public void add(T element) {
		addToRear(element);
	}

	@Override
	public void addAfter(T element, T target) {
		int targetIndex = indexOf(target);
		if (targetIndex < 0) {
			throw new NoSuchElementException();
		}
		expandCapacity();
		for(int i = rear; i > targetIndex +1; i--) {
			array[i] = array[i-1];
		}
		array[targetIndex +1] = element;
		rear++;
		modCount++;
	}

	@Override
	public void add(int index, T element) {
		if (index > rear || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		expandCapacity();
		for (int i = rear; i > index; i--) {
			array[i] = array[i-1];
		}
		array[index] = element;
		rear++;
		modCount++;
	}

	@Override
	public T removeFirst() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		T retVal = array[0];
		for (int i =0; i < rear -1; i++) {
			array[i] = array[i+1];
		}
		rear--;
		modCount++;
		array[rear] = null; //easy to miss - don't want memory leaks
		return retVal;
	}

	@Override
	public T removeLast() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		T retVal = array[rear -1];
		array[rear -1] = null;
		rear--;
		modCount++;
		return retVal;
	}

	@Override
	public T remove(T element) {
		int index = indexOf(element);
		if (index == -1) {
			throw new NoSuchElementException();
		}
		
		T retVal = array[index];
		
		rear--;
		//shift elements
		for (int i = index; i < rear; i++) {
			array[i] = array[i+1];
		}
		array[rear] = null;
		modCount++;
		
		return retVal;
	}

	@Override
	public T remove(int index) {
		if (index >= rear || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		T element = array[index];
		for (int i = 0; i < rear -1; i++) {
			array[i] = array[i+1];
		}
		array[rear] = null;
		rear--;
		modCount++;
		return element;
	}

	@Override
	public void set(int index, T element) {
		if (index >= rear || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		array[index] = element;
		modCount++;
	}

	@Override
	public T get(int index) {
		if (index < 0 || index >= rear) {
			throw new IndexOutOfBoundsException();
		}
		return array[index];
	}

	@Override
	public int indexOf(T element) {
		int index = -1;
		for (int i = 0; i < rear; i++) {
			if (element.equals(array[i])) { //Not ==
				return i;
			}
		}
		return index;
	}

	@Override
	public T first() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		return array[0];
	}

	@Override
	public T last() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		return array[rear-1]; //don't  write [rear--]
	}

	@Override
	public boolean contains(T target) {
		return (indexOf(target) != -1);
	}

	@Override
	public boolean isEmpty() {
		return rear == 0;
	}

	@Override
	public int size() {
		return rear;
	}

	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("[");
		// option 1
//		for (int i = 0; i < rear; i++) {
//			str.append(array[i].toString());
//			str.append(", ");
//		}
		// option 2
//		Iterator<T> it = iterator();
//		while (it.hasNext()) {
//			str.append(it.next().toString());
//			str.append(", ")	
//		}
		//// option 3
		for (T element : this) {
			str.append(element.toString());
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
		return new ALIterator();
	}

	@Override
	public ListIterator<T> listIterator() {
		throw new UnsupportedOperationException();
	}

	@Override
	public ListIterator<T> listIterator(int startingIndex) {
		throw new UnsupportedOperationException();
	}

	/** Iterator for IUArrayList */
	private class ALIterator implements Iterator<T> {
		private int nextIndex;
		private int iterModCount;
		private boolean canRemove;

		public ALIterator() {
			nextIndex = 0;
			iterModCount = modCount;
			canRemove = false;
		}

		@Override
		public boolean hasNext() {
			if (iterModCount != modCount) {
				throw new ConcurrentModificationException();
			}
			return nextIndex < rear;
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			nextIndex++;
			canRemove = true;
			return array[nextIndex - 1];
		}

		@Override
		public void remove() {
			if (iterModCount != modCount) {
				throw new ConcurrentModificationException();
			}
			// can I remove?
			if (!canRemove) {
				throw new IllegalStateException();
			}
			// remove
			for (int i = nextIndex - 1; i < rear - 1; i++) {
				array[i] = array[i + 1];
			}
			array[rear - 1] = null;
			rear--;
			nextIndex--;
			modCount++;
			iterModCount++;
			// make sure I can't remove twice in a row
			canRemove = false;
		}
	}
}
