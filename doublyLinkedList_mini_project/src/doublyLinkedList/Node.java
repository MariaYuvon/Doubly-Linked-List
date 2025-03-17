
package doublyLinkedList;

public class Node<T> {
public T data;
public Node<T> next;
Node<T> prev;

public Node(T data) {
	this.data=data;
	this.prev=null;
	this.next=null;
}
boolean hasNext() {
    return this.next != null;
}

}
