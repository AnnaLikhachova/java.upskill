package com.likhachova.list;

import java.util.Iterator;

public interface CustomList<E> {
        // add value to the end of the list
        void add(E value);

        // [A, B, C, null, null ] size = 3
        // add (D, [0,1,2,3])
        // we can add value by index between [0, size]
        // otherwise throw new IndexOutOfBoundsException
        void add(E value, int index);

        // we can remove value by index between [0, size - 1]
        // otherwise throw new IndexOutOfBoundsException

        // [A, B, C] remove = 0
        // [B (index = 0) , C (index = 1)]
        void remove(int index);

        // [A, B, C] size = 3
        // we can get value by index between [0, size - 1]
        // otherwise throw new IndexOutOfBoundsException
        E get(int index);

        // we can set value by index between [0, size - 1]
        // otherwise throw new IndexOutOfBoundsException
        void set(E value, int index);

        void clear();

        int size();

        boolean isEmpty();

        boolean contains(E value);

        // [A, B, A, C] indexOf(A) -> 0
        // -1 if not exist
        int indexOf(Object value);

        // [A, B, A, C] lastIndexOf(A) -> 2
        int lastIndexOf(Object value);

        // [A, B, C]
        String toString();

        Iterator<E> iterator();

}
