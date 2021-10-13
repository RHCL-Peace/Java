/**
 * Node represents a node in a linked list.
 *
 * @author Java Foundations, mvail
 * @version 4.0
 */
public class Node<T> {
	private Node<T> next;
	private T element;
	private Node<T> previous;
	
	/**
  	 * Creates a node storing the specified element.
 	 *
  	 * @param elem
  	 *            the element to be stored within the new node
  	 */
	public Node(T elem) {
		next = null;
		previous = null;
		this.element = elem;
	}
	
	/**
	 * @param elem the element held by this Node
 	 * @param nextNode references the next Node in the chain
 	 */
	public Node(T elem, Node<T> nextNode) {
		this.element = elem;
		next = nextNode;
	}
	
	/**
 	 * Returns the node that follows this one.
  	 *
  	 * @return the node that follows the current one
  	 */
	public Node<T> getNext() {
		return next;
	}

	/**
 	 * Sets the node that follows this one.
 	 *
 	 * @param node
 	 *            the node to be set to follow the current one
 	 */
	public void setNext(Node<T> node) {
		next = node;
	}

	/**
 	 * Returns the element stored in this node.
 	 *
 	 * @return the element stored in this node
 	 */
	public T getElement() {
		return element;
	}

	/**
 	 * Sets the element stored in this node.
  	 *
  	 * @param elem
  	 *            the element to be stored in this node
  	 */
	public void setElement(T elem) {
		element = elem;
	}
	
	/**
 	 * Returns the node that follows this one.
  	 *
  	 * @return the node that follows the current one
  	 */
	public Node<T> getPrevious() {
		return previous;
	}

	/**
 	 * Sets the node that follows this one.
 	 *
 	 * @param node
 	 *            the node to be set to follow the current one
 	 */
	public void setPrevious(Node<T> node) {
		previous = node;
	}

	@Override
	public String toString() {
		return "Element: " + element.toString() + " Has next: " + (next != null);
	}

}

