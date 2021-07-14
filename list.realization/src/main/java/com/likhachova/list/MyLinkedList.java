package com.likhachova.list;

import java.util.Iterator;
import java.util.StringJoiner;

/**
 * @author Anna Likhachova
 * Creation date: 11/07/21.
 * @since 2.0
 */
public class MyLinkedList<E> implements CustomList, Iterable<E> {

    private int size = 0;
    private Node<E> first;
    private Node<E> last;

    private static class Node<E> {
        E item;
        Node<E> prev;
        Node<E> next;

        Node(E item) {
            this.item = item;
        }

        Node(E item, Node<E> prev, Node<E> next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    @Override
    public Iterator<E> iterator() {
        Iterator<E> it = new Iterator<E>() {
            private int nodePointer = -1;
            private Node<E> current = first;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public E next() {
                if(current != null) {
                    Node<E> newNode = current;
                    current = current.next;
                    nodePointer++;
                    return newNode.item;
                }
                return null;
            }

            @Override
            public void remove() {
                checkIndex(nodePointer);
                Node indexNode = getNode(nodePointer);
                indexNode.prev = indexNode.next;
                indexNode.next = indexNode.prev;
                indexNode.item = null;
                //throw new UnsupportedOperationException();
            }
        };
        return it;
    }

    @Override
    public void add(Object value) {
        add(value, size);
    }

    @Override
    public void add(Object value, int index) {
        checkIndex(index);
        Node<E> newNode = new Node(value);
        // add to the beginning
        if(index == 0) {
            first = newNode;
            last = newNode;
            //add to the end
        }
        else if(index == size) {
            newNode.prev = last;
            last.next = newNode;
            last = newNode;
        }
        else {
            Node<E> indexNode = getNode(index - 1); // add to the middle
            newNode.prev = indexNode;
            indexNode.next = newNode;
        }
        size++;
    }

    @Override
    public void remove(int index) {
        checkIndex(index);
        checkSize(index);
        Node<E> indexNode = getNode(index);
        Node<E> next = indexNode.next;
        Node<E> prev = indexNode.prev;

        if(prev == null) {
            first = next;
        }
        else {
            prev.next = next;
            indexNode.prev = null;
        }

        if(next == null) {
            last = prev;
        }
        else {
            next.prev = prev;
            indexNode.next = null;
        }
        indexNode.item = null;
        size--;
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        checkSize(index);
        return getNode(index).item;
    }

    @Override
    public void set(Object value, int index) {
        checkIndex(index);
        if(index == size) {
            throw new IndexOutOfBoundsException("");
        }
        else {
            Node indexNode = getNode(index);
            indexNode.item = value;
        }
    }

    @Override
    public void clear() {
        // only for garbage collector optimization
        for(Node node = first; node != null; ) {
            Node next = node.next;
            node.item = null;
            node.next = null;
            node.prev = null;
            node = next;
        }
        first = last = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object value) {
        for(Node node = first; node != null; node = node.next) {
            if(node.item.equals(value)) return true;
        }
        return false;
    }

    @Override
    public int indexOf(Object value) {
        int index = 0;
        for(Node node = first; node != null; node = node.next) {
            if(value.equals(node.item)) return index;
            index++;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object value) {
        int index = size - 1;
        for(Node node = last; node != null; node = node.prev) {
            if(value.equals(node.item)) return index;
            index--;
        }
        return -1;
    }

    @Override
    public String toString() {
        StringJoiner rgbJoiner = new StringJoiner(", ", "[", "]");
        for(Node node = first; node != null; node = node.next) {
            rgbJoiner.add(node.item.toString());
        }
        return rgbJoiner.toString();
    }

    private void checkIndex(int index) {
        if(!isIndex(index))
            throw new IndexOutOfBoundsException("IndexOutOfBoundsException is thrown in checkPositionIndex method");
    }

    private boolean isIndex(int index) {
        return index >= 0 && index <= size;
    }

    private void checkSize(int index) {
        if(index == size) {
            throw new IndexOutOfBoundsException("IndexOutOfBoundsException");
        }
    }

    private Node<E> getNode(int index) {

        if(index < (size / 2)) {
            Node<E> firstNode = first;
            for(int i = 0; i < index; i++) {
                firstNode = firstNode.next;
            }
            return firstNode;
        }
        else {
            Node<E> lastNode = last;
            for(int i = size - 1; i > index; i--) {
                lastNode = lastNode.prev;
            }
            return lastNode;
        }
    }
}
