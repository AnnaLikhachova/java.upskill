package com.likhachova.map;

import java.util.Iterator;

public interface Map<K, V> extends Iterable<MyHashMap.MyEntry<K, V>> {
    V put(K key, V value);

    V get(K key);

    boolean containsKey(K key);

    V remove(K key);

    int size();

    default Iterator<MyHashMap.MyEntry<K, V>> iterator() {
        throw new UnsupportedOperationException();
    }

    interface MyEntry<K, V> {

        K getKey();

        V getValue();

        V setValue(V value);
    };
}