package Programs.Program1;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Queue<Item> implements Iterable<Item> {

    private Node<Item> first; // beginning of queue
    private Node<Item> last; // end of queue
    private int n; // number of elements on queue

    // Helper linked list class
    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    // Initialize an empty queue
    public Queue() {
        first = null;
        last = null;
        n = 0;
    }

    // Is this queue empty?
    public boolean isEmpty() {
        return first == null;
    }

    // Returns the number of items in this queue
    public int size() {
        return n;
    }

    // Returns the item least recently added to this queue
    public Item peek() {
        if (isEmpty())
            throw new NoSuchElementException("Queue underflow");
        return first.item;
    }

    // Add an item to the queue
    public void enqueue(Item item) {
        Node<Item> oldLast = last;
        last = new Node<>();
        last.item = item;
        last.next = null;
        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
        }
        n++;
    }

    // Remove and return the item least recently added to this queue
    public Item dequeue() {
        if (isEmpty())
            throw new NoSuchElementException("Queue underflow");
        Item item = first.item;
        first = first.next;
        n--;
        if (isEmpty())
            last = null; // to avoid loitering
        return item;
    }

    // Returns an iterator that iterates over the items in this queue in FIFO order
    @Override
    public Iterator<Item> iterator() {
        return new ListIterator(first);
    }

    // An iterator, doesn't implement remove() since it's optional
    public class ListIterator implements Iterator<Item> {
        private Node<Item> current;

        public ListIterator(Node<Item> first) {
            this.current = first;
        }

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}
