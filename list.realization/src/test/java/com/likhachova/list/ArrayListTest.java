package com.likhachova.list;

public class ArrayListTest extends AbstractListTest{

    @Override
    public CustomList getList() {
        return new MyArrayList<>();
    }

}
