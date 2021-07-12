package com.likhachova.list;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ArrayListTest extends AbstractListTest{

    @Override
    public CustomList getList() {
        return new MyArrayList();
    }

}
