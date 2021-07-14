package com.likhachova.list;

public class LinkedListTest extends AbstractListTest {

    @Override
    public CustomList getList() {
        return new MyLinkedList<>();
    }

}
