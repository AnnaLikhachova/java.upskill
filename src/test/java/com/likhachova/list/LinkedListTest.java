package com.likhachova.list;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LinkedListTest extends AbstractListTest {

    @Override
    public CustomList getList() {
        return new MyLinkedList();
    }

}
