package com.likhachova.map;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.NoSuchElementException;

public class MyHashMap<K, V> implements Map<K, V> {
    private static final int INITIAL_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;
    private static final int RESIZE_INDEX = 2;
    private List<MyEntry<K, V>>[] buckets;
    private int size = 0;

    MyHashMap() {
        this.buckets = new ArrayList[INITIAL_CAPACITY];
    }

    @Override
    public V put(K key, V value) {
        checkLoadFactor();
        if(buckets[getIndex(key)] == null) {
            List<MyEntry<K, V>> entryArrayList = new ArrayList<>(1);
            buckets[getIndex(key)] = entryArrayList;
            entryArrayList.add(new MyEntry<>(key, value));
            size++;
        }
        else {
            List<MyEntry<K, V>> entryArrayList = buckets[getIndex(key)];
            for(MyEntry<K, V> myEntry : entryArrayList) {
                if(myEntry.getKey().equals(key)) {
                    if(!myEntry.getValue().equals(value)) {
                        myEntry.setValue(value);
                    }
                }
                else {
                    buckets[getIndex(key)].add(new MyEntry<>(key, value));
                    size++;
                }
            }

        }
        return value;
    }

    @Override
    public V get(K key) {
        if(key == null){
            return null;
        }
        List<MyEntry<K, V>> entryArrayList = buckets[getIndex(key)];
        if(entryArrayList != null) {
            for(MyEntry<K, V> myEntry : entryArrayList) {
                if(myEntry != null) {
                    if(myEntry.getKey().equals(key)) {
                        return myEntry.getValue();
                    }
                }
            }
        }
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        for(java.util.Map.Entry entry : buckets[getIndex(key)]) {
            if(entry.getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public V remove(K key) {
        List<MyEntry<K, V>> entryArrayList = buckets[getIndex(key)];
        if(entryArrayList == null) {
            throw new NoSuchElementException();
        }
        else {
            entryArrayList.removeIf(nextEntry -> nextEntry.getKey().equals(key));
            size--;
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<MyHashMap.MyEntry<K, V>> iterator() {

        Iterator<MyEntry<K, V>> it = new Iterator<MyEntry<K, V>>() {
            private int currentBucketIndex = 0;
            //private Iterator<MyEntry<K, V>> currentBucketIterator;
            private int currentEntry = 0;

            @Override
            public boolean hasNext() {
                return currentEntry < size;
            }

            @Override
            public MyEntry<K, V> next() {
                List<MyEntry<K, V>> nextBucketArray = buckets[currentBucketIndex];
                if(nextBucketArray != null) {
                    MyEntry<K, V> result = nextBucketArray.get(currentEntry);
                    currentEntry++;
                    if(nextBucketArray.size() == currentEntry) {
                        currentEntry = 0;
                        currentBucketIndex++;
                    }
                    return result;
                }
                else {
                    if(currentBucketIndex != buckets.length - 1) {
                        currentBucketIndex++;
                        next();
                    }
                }
                return null;
            }

            @Override
            public void remove() {
                List<MyEntry<K, V>> nextBucketArray = buckets[currentBucketIndex - 1];
                nextBucketArray.remove(currentEntry);
            }
        };
        return it;
    }

    final static class MyEntry<K, V> implements Entry<K, V> {
        private final K key;
        private V value;

        public MyEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            V prev = this.value;
            this.value = value;
            return prev;
        }
    }

    private int getIndex(Object o) {
        if(o == null) {
            return 0;
        }
        int objectHashCode = Math.abs(o.hashCode());
        if(objectHashCode >= 0) {
            return Math.abs(o.hashCode()) % buckets.length;
        }
        else {
            return Integer.valueOf(String.valueOf(objectHashCode).substring(1));
        }
    }

    private void checkLoadFactor() {
        if(buckets.length * LOAD_FACTOR <= size()) {
            resize();
        }
    }

    private void resize() {
        List<MyEntry<K, V>>[] arrayNew = new ArrayList[(buckets.length * RESIZE_INDEX)];
        for(List<MyEntry<K, V>> array : buckets) {
            if(array != null) {
                for(MyEntry<K, V> entry : array) {
                    if(arrayNew[getIndex(entry.getKey())] != null) {
                        arrayNew[getIndex(entry.getKey())].add(entry);
                    }
                    else {
                        List<MyEntry<K, V>> newEntryArray = new ArrayList();
                        newEntryArray.add(entry);
                        arrayNew[getIndex(entry.getKey())] = newEntryArray;
                    }
                }
            }
        }
        buckets = arrayNew;
    }

}
