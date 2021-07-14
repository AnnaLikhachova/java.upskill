package com.likhachova.list;

import java.util.Iterator;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * @author Anna Likhachova
 * Creation date: 11/07/21.
 * @since 2.0
 */
public class MyArrayList<E> implements CustomList, Iterable<E> {

    private static final int INITIAL_CAPACITY = 16;
    private static final double RESIZE_INDEX = 1.5;
    private Object[] array;
    private int size = 0;
    private int nextElement = 0;

    MyArrayList() {
        this.array = new Object[INITIAL_CAPACITY];
    }

    MyArrayList(int requiredCapacity) {
        this.array = new Object[requiredCapacity];
    }

    @Override
    public Iterator<E> iterator() {
        Iterator<E> it = new Iterator<E>() {

            @Override
            public boolean hasNext() {
                return nextElement < size;
            }

            @Override
            public E next() {
                Object result = array[nextElement];
                nextElement = nextElement + 1;
                return (E) result;
            }

            @Override
            public void remove() {
                if(nextElement < size - 1) {
                    System.arraycopy(array, nextElement + 1,
                            array, nextElement, size - nextElement - 1);
                }
                size--;
                // throw new UnsupportedOperationException();
            }
        };
        return it;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return array.length == 0;
    }

    @Override
    public boolean contains(Object o) {
        if(o != null) {
            for(Object anArray : array) {
                if(Objects.equals(anArray, o)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void add(Object o) {
        insureCapacity();
        array[size++] = o;
    }

    @Override
    public void add(Object value, int index) {
        insureCapacity();
        checkIndex(index);
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = value;
        size++;
    }

    @Override
    public void clear() {
        for(int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        checkSize(index);
        return (E) array[index];
    }

    @Override
    public void set(Object value, int index) {
        checkIndex(index);
        array[index] = value;
    }

    @Override
    public void remove(int index) {
        checkIndex(index);
        checkSize(index);
        for(int i = 0; i < size; i++) {
            if(i == index) {
                System.arraycopy(array, index + 1, array, index, size - index - 1);
                array[--size] = null;
            }
        }
    }

    @Override
    public int indexOf(Object o) {
        for(int i = 0; i < size; i++) {
            if(array[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for(int i = size - 1; i >= 0; i--) {
            if(array[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        StringJoiner rgbJoiner = new StringJoiner(", ", "[", "]");
        for(int i = 0; i < size; i++) {
            rgbJoiner.add(array[i] == null? "": array[i].toString());
        }
        return rgbJoiner.toString();
    }

    private void insureCapacity() {
        if(size == array.length) {
            Object[] arrayNew = new Object[(int) (size * RESIZE_INDEX + 1)];
            System.arraycopy(array, 0, arrayNew, size - 1, size);
            array = arrayNew;
        }
    }

    private void checkIndex(int index) {
        if(index < 0 || index > array.length - 1) {
            throw new ArrayIndexOutOfBoundsException("ArrayIndexOutOfBoundsException index: " + index);
        }
    }

    private void checkSize(int index) {
        if(index >= size) {
            throw new ArrayIndexOutOfBoundsException("ArrayIndexOutOfBoundsException index: " + index);
        }
    }
}
