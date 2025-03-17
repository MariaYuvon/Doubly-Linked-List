package doublyLinkedList;

import java.util.Arrays;
import java.util.LinkedList;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();

        System.out.println("1. Adding elements:");
        list.addFirst(10);
        list.addLast(20);
        list.add(30);
        list.add(1, 15);
        list.print(); 

        System.out.println("\n2. Adding a collection:");
        List<Integer> collection = Arrays.asList(40, 50, 60);
        list.addAll(collection);
        list.print(); 

        System.out.println("\n3. Adding a collection at index:");
        list.addAll(0, Arrays.asList(100, 200));
        list.print(); 

        System.out.println("\n4. Removing elements:");
        list.remove(100);
        list.removeFirst();
        list.removeLast();
        list.print(); 

        System.out.println("\n5. Checking for contains:");
        System.out.println(list.contains(30)); 
        System.out.println(list.contains(100)); 

        System.out.println("\n6. Index of an element:");
        System.out.println(list.indexOf(30)); 
        System.out.println(list.indexOf(100)); 

        System.out.println("\n7. Getting elements by index:");
        System.out.println(list.get(2)); 

        System.out.println("\n8. Getting first and last elements:");
        System.out.println("First: " + list.getFirst());
        System.out.println("Last: " + list.getLast()); 

        System.out.println("\n9. Finding middle element:");
        System.out.println("Middle: " + list.findMiddle()); 

        System.out.println("\n10. Converting to array:");
        System.out.println(Arrays.toString(list.toArray())); 

        System.out.println("\n11. Clearing the list:");
        list.clear();
        System.out.println("Is empty: " + list.isEmpty());

        System.out.println("\n12. Merging two lists:");
        DoublyLinkedList<Integer> list1 = new DoublyLinkedList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        DoublyLinkedList<Integer> list2 = new DoublyLinkedList<>();
        list2.add(4);
        list2.add(5);
        list2.add(6);
        list1.merge(list2);
        list1.print();

        System.out.println("\n13. Removing all occurrences of an element:");
        list1.add(2);
        list1.add(2);
        list1.print(); 
        list1.removeAll(2);
        list1.print(); 

        System.out.println("\n14. Descending iterator:");
        Iterator<Integer> descIterator = list1.getDescendingIterator();
        while (descIterator.hasNext()) {
            System.out.print(descIterator.next() + " <-> ");
        }
        System.out.println("null"); 
    }
}
