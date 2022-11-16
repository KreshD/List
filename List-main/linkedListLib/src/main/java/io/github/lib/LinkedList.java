package io.github.lib;

import java.util.Iterator;

public class LinkedList<T> implements Iterable<T> {

    public static class LinkedListException extends Exception {
        public LinkedListException(String message) {
            super(message);
        }
    }

    private class LinkedListNode {
        public T value;
        public LinkedListNode next;
        public LinkedListNode prev;

        public LinkedListNode(T value, LinkedListNode next, LinkedListNode prev) {
            this.value = value;
            this.next = next;
            this.prev = prev;
        }

        public LinkedListNode(T value) {
            this(value, null, null);
        }
    }

    private LinkedListNode head = null;
    private LinkedListNode tail = null;
    private int size = 0;

    public Object[] toArray() {
        Object[] values = new Object[this.size];
        if (size() > 0) {
            values[0] = this.head.value;
            int count = 0;
            for ( LinkedListNode node = this.head.next; node != this.head; node = node.next) {
                values[count++] = node.value;
            }
        }
        return values;
    }

    public void addFirst(T value) {
        LinkedListNode node = new LinkedListNode(value);
        if (size == 0) {
            node.next = null;
            node.prev = null;
            head = node;
            tail = node;
        }
        else{
            head.prev = node;
            node.next = head;
            node.prev = null;
            head = node;
        }

        size++;
    }

    public void addLast(T value) {
        if (size == 0) {
            head = tail = new LinkedListNode(value);
        } else {
            tail.next = new LinkedListNode(value);
            tail.prev = tail;
            tail = tail.next;
        }
        size++;
    }

    private void checkEmptyError() throws LinkedListException {
        if (size == 0) {
            throw new LinkedListException("Empty list");
        }
    }

    private LinkedListNode getNode(int index) {
        LinkedListNode curr = head;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        return curr;
    }

    public void removeFirst() throws LinkedListException {
        checkEmptyError();
        head.next.prev = null;
        head = head.next;
        if (size == 1) {
            tail = null;
        }
        size--;
    }

    public void removeLast() throws LinkedListException {
        checkEmptyError();
        if (size == 1) {
            head = tail = null;
        } else {
            tail = getNode(size - 2);
            tail.next = null;
        }
        size--;
    }

    public void removeByIndex(int index) throws LinkedListException {
        checkEmptyError();
        if (index < 0 || index >= size) {
            throw new LinkedListException("Incorrect index");
        }
        if (index == 0) {
            removeFirst();
        } else {
            LinkedListNode previous = getNode(index - 1);
            previous.next.next.prev = previous.next.next.prev.prev;
            previous.next = previous.next.next;

            if (previous.next == null) {
                tail = previous;
            }


            size--;
        }
    }

    public int size() {
        return size;
    }

    public T get(int index) throws LinkedListException {
        checkEmptyError();
        return getNode(index).value;
    }

    public T getLast() throws LinkedListException {
        checkEmptyError();
        return tail.value;
    }


    @Override
    public Iterator<T> iterator() {
        class LinkedListIterator implements Iterator<T> {
            LinkedListNode curr = head;



            @Override
            public boolean hasNext() {return curr != null;}

            @Override
            public T next() {
                T value = curr.value;
                curr = curr.next;
                return value;
            }


        }

        return new LinkedListIterator();
    }
}
