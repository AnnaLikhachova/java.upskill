package com.likhachova.list;

public interface CustomIterator {

    boolean hasNext();

    // moves the cursor/iterator to next element
    Object next();

    // we use to remove an element
    // otherwise throws UnsupportedOperationException.
    void remove();

}
