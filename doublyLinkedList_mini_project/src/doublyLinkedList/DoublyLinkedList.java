package doublyLinkedList;

import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;




public class DoublyLinkedList<T> {
    public Node<T> headNode;
    Node<T> tailNode;
    int size=0;


    public void addFirst(T data) {
        Objects.requireNonNull(data, "Data cannot be null");
        size++;
        Node<T> newNode = new Node<>(data);
        if (headNode == null) {
            headNode = tailNode = newNode;
        } else {
            newNode.next = headNode;
            headNode.prev = newNode;
            headNode = newNode;
        }
    }

    public void addLast(T data) {
        add(data); 
    }

    public void add(T data) {
        Objects.requireNonNull(data, "Data cannot be null");
        size++;
        Node<T> newNode = new Node<>(data);
        if (headNode == null) {
            headNode = tailNode = newNode;
        } else {
            tailNode.next = newNode;
            newNode.prev = tailNode;
            tailNode = newNode;
        }
    }

   
    public void add(int index, T data) {
        Objects.requireNonNull(data, "Data cannot be null");
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        if (index == 0) {
            addFirst(data);
        } else if (index == size) {
            addLast(data);
        } else {
            size++;
            Node<T> newNode = new Node<>(data);
            Node<T> temp = headNode;

            for (int i = 0; i < index; i++) {
                temp = temp.next;
            }

            Node<T> prev = temp.prev;
            prev.next = newNode;
            newNode.prev = prev;
            newNode.next = temp;
            temp.prev = newNode;
        }
    }

    public void addAll(Collection<? extends T> c) {
        Objects.requireNonNull(c, "Collection cannot be null");
        if (c.isEmpty()) {
            throw new IllegalArgumentException("Collection cannot be empty");
        }

        for (T element : c) {
            add(element);
        }
    }


    public void addAll(int index, Collection<? extends T> c) {
        Objects.requireNonNull(c, "Collection cannot be null");
        if (c.isEmpty()) {
            throw new IllegalArgumentException("Collection cannot be empty");
        }
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        if (headNode == null) { 
            addAll(c); 
            return;
        }

        Node<T> tempNode = headNode;
        Node<T> prevNode = null;

        for (int i = 0; i < index; i++) {
            prevNode = tempNode;
            tempNode = tempNode.next;
        }

        for (T element : c) {
            Node<T> newNode = new Node<>(element);
            size++;
            if (prevNode == null) {
                newNode.next = headNode;
                headNode.prev = newNode;
                headNode = newNode;
            } else {
                prevNode.next = newNode;
                newNode.prev = prevNode;

                newNode.next = tempNode;
                if (tempNode != null) {
                    tempNode.prev = newNode;
                } else {
                    tailNode = newNode;
                }

                prevNode = newNode;
            }
        }
    }
//    public void addAll(int index, Collection<? extends T> c) {
//        Objects.requireNonNull(c, "Collection cannot be null");
//        if (c.isEmpty()) {
//            throw new IllegalArgumentException("Collection cannot be empty");
//        }
//        if (index < 0 || index > size) {
//            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
//        }
//
//        Node<T> tempNode = headNode;
//        Node<T> prevNode = null;
//
//        for (int i = 0; i < index; i++) {
//            prevNode = tempNode;
//            tempNode = tempNode.next;
//        }
//
//        for (T element : c) {
//            Node<T> newNode = new Node<>(element);
//            size++;
//
//            if (prevNode == null) {
//                newNode.next = headNode;
//                if (headNode != null) {
//                    headNode.prev = newNode;
//                }
//                headNode = newNode;
//            } else {
//                prevNode.next = newNode;
//                newNode.prev = prevNode;
//
//                newNode.next = tempNode;
//                if (tempNode != null) {
//                    tempNode.prev = newNode;
//                } else {
//                    tailNode = newNode;
//                }
//            }
//            prevNode = newNode; 
//        }
//    }
//
//    
    public void print() {
        if (headNode == null) {
            System.out.println("The list is empty.");
            return;
        }
        Node<T> temp = headNode;
        while (temp != null) {
            System.out.print(temp.data + " <-> ");
            temp = temp.next;
        }
        System.out.println("null");
    }


    public void clear() {
        headNode = tailNode = null;
        size = 0;
    }

  
    public boolean contains(Object o) {
        Objects.requireNonNull(o, "The input object cannot be null");
        Node<T> temp = headNode;
        while (temp != null) {
            if (temp.data.equals(o)) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }


    public int indexOf(Object o) {
        Objects.requireNonNull(o, "The input object cannot be null");
        Node<T> temp = headNode;
        int index = 0;
        while (temp != null) {
            if (temp.data.equals(o)) {
                return index;
            }
            temp = temp.next;
            index++;
        }
        return -1; 
    }

    public Iterator<T> getDescendingIterator() {
        return new Iterator<>() {
            private Node<T> currentNode = tailNode;

            @Override
            public boolean hasNext() {
                return currentNode != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new java.util.NoSuchElementException("No more elements in the iterator");
                }
                T data = currentNode.data;
                currentNode = currentNode.prev;
                return data;
            }
        };
    }


    public Node<T> element() {
        if (headNode == null) {
            throw new IllegalStateException("The list is empty");
        }
        return headNode;
    }


    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        Node<T> desiredNode = headNode;
        int i = 0;
        while (desiredNode != null) {
            if (i == index) {
                return desiredNode.data;
            }
            i++;
            desiredNode = desiredNode.next;
        }
        throw new IndexOutOfBoundsException("Index: " + index + " is out of bounds");
    }


    public T getFirst() {
        if (headNode == null) {
            throw new IllegalStateException("The list is empty");
        }
        return headNode.data;
    }


    public T getLast() {
        if (tailNode == null) {
            throw new IllegalStateException("The list is empty");
        }
        return tailNode.data;
    }


    int lastIndexOf() {
        return size - 1;
    }


    boolean offer(T data) {
        Objects.requireNonNull(data, "Data cannot be null");
        size++;
        Node<T> newNode = new Node<>(data);
        if (headNode == null) {
            headNode = tailNode = newNode;
        } else {
            tailNode.next = newNode;
            newNode.prev = tailNode;
            tailNode = newNode;
        }
        return true;
    }


    boolean offerFirst(T data) {
        Objects.requireNonNull(data, "Data cannot be null");
        size++;
        Node<T> newNode = new Node<>(data);
        if (headNode == null) {
            headNode = tailNode = newNode;
        } else {
            newNode.next = headNode;
            headNode.prev = newNode;
            headNode = newNode;
        }
        return true;
    }


    boolean offerLast(T data) {
        return offer(data);
    }


    T peek() {
        if (headNode == null) {
            throw new IllegalStateException("The list is empty");
        }
        return headNode.data;
    }


    T peekFirst() {
        return peek();
    }


    T peekLast() {
        if (tailNode == null) {
            throw new IllegalStateException("The list is empty");
        }
        return tailNode.data;
    }


    T poll() {
        if (headNode == null) {
            return null;
        }
        T data = headNode.data;
        removeFirst();
        return data;
    }


    T pollFirst() {
        return poll();
    }


    T pollLast() {
        if (tailNode == null) {
            return null; 
        }
        T data = tailNode.data;
        removeLast();
        return data;
    }


    T pop() {
        if (tailNode == null) {
            throw new IllegalStateException("The list is empty");
        }
        return pollLast();
    }


    void push(T data) {
        add(data);
    }

    

    boolean remove(T data) {
        Objects.requireNonNull(data, "Data cannot be null");
        Node<T> temp = headNode;
        while (temp != null) {
            if (temp.data.equals(data)) {
                if (temp == headNode) {
                    removeFirst();
                } else if (temp == tailNode) {
                    removeLast();
                } else {
                    size--;
                    Node<T> tempPrev = temp.prev;
                    Node<T> tempNext = temp.next;
                    tempPrev.next = tempNext;
                    tempNext.prev = tempPrev;
                }
                return true; 
            }
            temp = temp.next;
        }
        return false; 
    }


    void removeLast() {
        if (tailNode == null) {
            throw new IllegalStateException("The list is empty");
        }
        size--;
        if (tailNode.prev == null) {
            headNode = tailNode = null;
        } else {
            tailNode = tailNode.prev;
            tailNode.next = null;
        }
    }

 
    void remove() {
        removeFirst();
    }


    void removeFirst() {
        if (headNode == null) {
            throw new IllegalStateException("The list is empty");
        }
        size--;
        if (headNode.next == null) { 
            headNode = tailNode = null;
        } else {
            headNode = headNode.next;
            headNode.prev = null;
        }
    }

        int size() {
        return size;
    }

    T set(int index, T element) {
        Objects.requireNonNull(element, "Element cannot be null");
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        Node<T> temp = headNode;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        T oldData = temp.data; 
        temp.data = element;  
        return oldData;
    }
    public Object[] toArray() {
        Object[] result = new Object[size];
        Node<T> currentNode = headNode;
        int index = 0;
        while (currentNode != null) {
            result[index++] = currentNode.data;
            currentNode = currentNode.next;
        }
        return result;
    }


	public boolean isEmpty() {
		if(size==0) {
			return true;
		}
		return false;
	}
	
	public T findMiddle() {
	    if (headNode == null) {
	        throw new IllegalStateException("The list is empty");
	    }

	    Node<T> slow = headNode;
	    Node<T> fast = headNode;

	    while (fast != null && fast.next != null) {
	        slow = slow.next;
	        fast = fast.next.next;
	    }

	    return slow.data;
	}
	
	
	public void merge(DoublyLinkedList<T> otherList) {
	    Objects.requireNonNull(otherList, "Other list cannot be null");

	    if (otherList.isEmpty()) {
	        return;
	    }

	    if (this.isEmpty()) {
	        this.headNode = otherList.headNode;
	        this.tailNode = otherList.tailNode;
	    } else {
	        this.tailNode.next = otherList.headNode;
	        otherList.headNode.prev = this.tailNode;
	        this.tailNode = otherList.tailNode;
	    }
	    this.size += otherList.size();
	}
	public void removeAll(T data) {
	    Objects.requireNonNull(data, "Data cannot be null");

	    Node<T> current = headNode;
	    while (current != null) {
	        if (current.data.equals(data)) {
	            Node<T> next = current.next;
	            remove(current);
	            current = next;
	        } else {
	            current = current.next;
	        }
	    }
	}

	private void remove(Node<T> node) {
	    if (node == headNode) {
	        removeFirst();
	    } else if (node == tailNode) {
	        removeLast();
	    } else {
	        size--;
	        node.prev.next = node.next;
	        node.next.prev = node.prev;
	    }
	}
	
	public void removeDuplicates() {
	    if (headNode == null || headNode.next == null) {
	        return;
	    }

	    Node<T> current = headNode;

	    while (current != null) {
	        Node<T> temp = current.next;

	        while (temp != null) {
	            if (current.data.equals(temp.data)) {
	                Node<T> next = temp.next;
	                remove(temp);
	                temp = next;
	            } else {
	                temp = temp.next;
	            }
	        }

	        current = current.next;
	    }
	}

	public DoublyLinkedList<T> cloneList() {
	    DoublyLinkedList<T> newList = new DoublyLinkedList<>();
	    Node<T> current = headNode;

	    while (current != null) {
	        newList.add(current.data);
	        current = current.next;
	    }
	    return newList;
	}

	public void reverse() {
	    if (headNode == null || headNode.next == null) {
	        return;
	    }
	    Node<T> current = headNode;
	    Node<T> temp = null;

	    while (current != null) {
	        temp = current.prev;
	        current.prev = current.next;
	        current.next = temp;
	        current = current.prev;
	    }

	    if (temp != null) {
	        headNode = temp.prev;
	    }
	}



}




































