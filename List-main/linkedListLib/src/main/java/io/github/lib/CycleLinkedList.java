package io.github.lib;

import java.util.Iterator;

public class CycleLinkedList<T> implements Iterable<T>{

    public static class CycleLinkedListException extends Exception {
        public CycleLinkedListException(String message) {
            super(message);
        }
    }

    private class CycleLinkedListNode{

        T value;
        CycleLinkedListNode next;

        public CycleLinkedListNode(T value){
            this.value = value;
        }
    }

    private CycleLinkedListNode head;
    private CycleLinkedListNode tail;
    private int size = 0;

    public  CycleLinkedList(){
        this.head = null;
        this.tail = null;
    }


    public void add(T value){
        CycleLinkedListNode node = new CycleLinkedListNode(value);
        if(head == null){
            head = node;
            tail = node;
            return;
        }
        tail.next = node;
        node.next = head;
        tail = node;
        size++;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int indexOf(T value) {
        int index = -1;
        if (value != null && !isEmpty()) {
            if (this.head.value.equals(value)) {
                index = 0;
            } else {
                int count = 1;
                for (CycleLinkedListNode node = this.head.next; node != this.head; node = node.next) {
                    if (node.value.equals(value)) {
                        index = count;
                        break;
                    } else {
                        count++;
                    }
                }
            }
        }
        return index;
    }

    public void display() throws CycleLinkedListException {
        checkEmptyError();
        CycleLinkedListNode node = head;
        if(head != null){
            do{
                System.out.print(node.value  + "->");
                node = node.next;
            } while (node != head);
        }
    }
    public void delete(T value) throws CycleLinkedListException {
        checkEmptyError();
        CycleLinkedListNode node = head;
        if(node == null){
            return;
        }
        if(node.value == value){
            head = head.next;
            tail.next = head;
            return;
        }
        do{
            CycleLinkedListNode n = node.next;
            if(n.value == value){
                node.next = n.next;
                break;
            }
            node = node.next;
        }while (node != head);
        size--;
    }

    public CycleLinkedListNode getNode(int index) {
        CycleLinkedListNode curr = head;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        return curr;
    }

    public T get(int index)  {
        return getNode(index).value;
    }

    public int size() {
        return size;
    }

    private void checkEmptyError() throws CycleLinkedListException {
        if (size == 0) {
            throw new CycleLinkedListException("Empty list");
        }
    }

    @Override
    public Iterator<T> iterator() {
        class LinkedListIterator implements Iterator<T> {
            CycleLinkedListNode curr = head;

            @Override
            public boolean hasNext() {
                return curr != null;
            }

            @Override
            public T next() {
                T val = curr.value;
                curr = curr.next;
                return val;
            }
        }

        return new LinkedListIterator();
    }
}
