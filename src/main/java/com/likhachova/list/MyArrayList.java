package com.likhachova.list;

import java.util.Objects;

/**
 * @author Anna Likhachova<br/>
 *         Creation date: 11/07/21.
 * @since 1.0
 */
public class MyArrayList implements CustomList {

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

    public CustomIterator iterator(){
        CustomIterator ci = new CustomIterator() {

            @Override
            public boolean hasNext() {
                return nextElement < size;
            }

            @Override
            public Object next() {
                Object result = array[nextElement];
                nextElement = nextElement + 1;
                return result;
            }

            @Override
            public void remove() {
                if (nextElement < size - 1) {
                    System.arraycopy(array, nextElement + 1,
                            array, nextElement, size - nextElement - 1);
                }
                size--;
            }
        };
        return ci;
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
        for(Object anArray : array) {
            if(Objects.equals(anArray, o)) return true;
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
    public Object get(int index) {
        checkIndex(index);
        if(index >= size)
            throw new ArrayIndexOutOfBoundsException("ArrayIndexOutOfBoundsException is thrown in get method");
        return array[index];
    }

    @Override
    public void set(Object value, int index) {
        checkIndex(index);
        array[index] = value;
    }

    @Override
    public void remove(int index) {
        checkIndex(index);
        if(index >= size)
            throw new ArrayIndexOutOfBoundsException("ArrayIndexOutOfBoundsException is thrown in remove method");
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
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for(Object o : array) {
            sb.append(o == null? "": o + ", ");
        }
        sb.delete(sb.length() - 2, sb.length());
        return sb.append(']').toString();
    }

    private void insureCapacity() {
        if(size == array.length) {
            Object[] arrayNew = new Object[(int) (size * RESIZE_INDEX + 1)];
            System.arraycopy(array, 0, arrayNew, size - 1, size);
            array = arrayNew;
        }
    }

    private void checkIndex(int index) {
        if(index < 0 || index > array.length - 1)
            throw new ArrayIndexOutOfBoundsException("ArrayIndexOutOfBoundsException is thrown in checkIndex method");

    }

}
