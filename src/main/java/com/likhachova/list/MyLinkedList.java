package com.likhachova.list;

/**
 * @author Anna Likhachova<br/>
 *         Creation date: 11/07/21.
 * @since 1.0
 */
public class MyLinkedList implements CustomList {

    private int size = 0;
    private Node first;
    private Node last;

    private static class Node {
        Object item;
        Node prev;
        Node next;

        Node(Object item, Node prev, Node next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    @Override
    public void add(Object value) {
        Node lastNode = last;
        Node newNode = new Node(value, lastNode, null);
        last = newNode;
        if(lastNode == null) {
            first = newNode;
        }
        else {
            lastNode.next = newNode;
        }
        size++;
    }

    @Override
    public void add(Object value, int index) {
        checkIndex(index);
        if(index == size) {
            add(value);
        }
        else {
            Node indexNode = getNode(index);
            Node prevNode = indexNode.prev;
            Node newNode = new Node(value, prevNode, indexNode);
            indexNode.prev = newNode;
            prevNode.next = newNode;

            size++;
        }
    }

    @Override
    public void remove(int index) {
        checkIndex(index);
        if(index == size) throw new IndexOutOfBoundsException("IndexOutOfBoundsException is thrown in remove method");
        Node indexNode = getNode(index);
        Node next = indexNode.next;
        Node prev = indexNode.prev;

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
    public Object get(int index) {
        checkIndex(index);
        if(index == size) throw new IndexOutOfBoundsException("IndexOutOfBoundsException is thrown in get method");
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
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for(Node node = first; node != null; node = node.next) {
            sb.append(node.item + ", ");
        }
        sb.delete(sb.length() - 2, sb.length());
        return sb.append(']').toString();
    }

    private void checkIndex(int index) {
        if(!isIndex(index))
            throw new IndexOutOfBoundsException("IndexOutOfBoundsException is thrown in checkPositionIndex method");
    }

    private boolean isIndex(int index) {
        return index >= 0 && index <= size;
    }

    private Node getNode(int index) {
        if(index < (size / 2)) {
            Node firstNode = first;
            for(int i = 0; i < index; i++) {
                firstNode = firstNode.next;
            }
            return firstNode;
        }
        else {
            Node lastNode = last;
            for(int i = size - 1; i > index; i--) {
                lastNode = lastNode.prev;
            }
            return lastNode;
        }
    }
}
