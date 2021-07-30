package com.likhachova.map;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.NoSuchElementException;

public class MyHashMap<K, V> implements Map<K, V> {
    private static final int INITIAL_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;
    private static final double RESIZE_INDEX = 1.5;
    private List<MyEntry<K, V>>[] buckets;
    private int size = 0;

    MyHashMap() {
        this.buckets = new ArrayList[INITIAL_CAPACITY];
    }

    @Override
    public V put(K key, V value) {
        checkLoadFactor();
        List<MyEntry<K, V>> entryArrayList;
        if(buckets[getIndex(key)] == null) {
            entryArrayList = new ArrayList<>(1);
            entryArrayList.add(new MyEntry<>(key, value));
            buckets[getIndex(key)] = entryArrayList;
            size++;
            return value;
        }
        else {
            entryArrayList = buckets[getIndex(key)];
            for(MyEntry<K, V> myEntry : entryArrayList) {
                if(key == null) {
                    if(myEntry.getKey() == null) {
                        myEntry.setValue(value);
                    }
                }
                else if(myEntry.getKey().equals(key)) {
                    if(!myEntry.getValue().equals(value)) {
                        myEntry.setValue(value);
                    }
                }
                else {
                    buckets[getIndex(key)].add(new MyEntry<>(key, value));
                    size++;
                    return value;
                }
            }
        }
        return null;
    }

    @Override
    public V get(K key) {
        List<MyEntry<K, V>> entryArrayList = buckets[getIndex(key)];
        if(entryArrayList != null) {
            for(MyEntry<K, V> myEntry : entryArrayList) {
                if(key == null) {
                    if(myEntry.getKey() == null) {
                        return myEntry.getValue();
                    }
                }
                else if(myEntry.getKey().equals(key)) {
                    return myEntry.getValue();
                }
            }
        }
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        List<MyEntry<K, V>> entryArrayList = buckets[getIndex(key)];
        if(entryArrayList != null) {
            for(MyEntry myEntry : entryArrayList) {
                if(key == null) {
                    if(myEntry.getKey() == null) {
                        return true;
                    }
                }
                else if(myEntry.getKey().equals(key)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public V remove(K key) {
        List<MyEntry<K, V>> entryArrayList = buckets[getIndex(key)];
        if(entryArrayList != null) {
            for(MyEntry<K, V> myEntry : entryArrayList) {
                if(myEntry != null) {
                    if(key == null) {
                        if(myEntry.getKey() == null) {
                            entryArrayList.remove(myEntry);
                            size--;
                            if(entryArrayList.size() == 0) {
                                buckets[getIndex(key)] = null;
                            }
                            return null;
                        }
                    }
                    else if(myEntry.getKey().equals(key)) {
                        entryArrayList.remove(myEntry);
                        size--;
                        if(entryArrayList.size() == 0) {
                            buckets[getIndex(key)] = null;
                        }
                        return null;
                    }
                }
            }
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
            private int currentEntry = 0;

            @Override
            public boolean hasNext() {
                for(int i = currentBucketIndex; i < buckets.length; i++) {
                    if(buckets[i] != null) {
                        if(buckets[i].size() > currentEntry) {
                            return true;
                        }
                    }
                }
                return false;
            }

            @Override
            public MyEntry<K, V> next() {
                if(size == 0 || currentBucketIndex == buckets.length) {
                    throw new NoSuchElementException();
                }

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
                    currentEntry = 0;
                    currentBucketIndex++;
                    for(int i = currentBucketIndex; i < buckets.length; i++) {
                        if(buckets[i] != null) {
                            currentBucketIndex = i;
                            MyEntry<K, V> result = buckets[i].get(currentEntry);
                            currentEntry++;
                            if(buckets[i].size() == currentEntry) {
                                currentEntry = 0;
                                currentBucketIndex++;
                            }
                            return result;
                        }
                    }
                }
                return null;
            }

            @Override
            public void remove() {
                List<MyEntry<K, V>> nextBucketArray = buckets[currentBucketIndex - 1];
                if(nextBucketArray != null) {
                    nextBucketArray.remove(currentEntry);
                    size--;
                    if(nextBucketArray.size() == 0) {
                        buckets[currentBucketIndex - 1] = null;
                        currentBucketIndex--;
                    }
                }
            }
        };
        return it;
    }

    final static class MyEntry<K, V> implements Entry<K, V> {
        private final K key;
        private V value;

        MyEntry(K key, V value) {
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
        int objectHashCode = o.hashCode();
        if(objectHashCode != Integer.MIN_VALUE) {
            return Math.abs(o.hashCode()) % buckets.length;
        }
        else {
            return Integer.valueOf(String.valueOf(objectHashCode).substring(1)) % buckets.length;
        }
    }

    private void checkLoadFactor() {
        if(buckets.length * LOAD_FACTOR <= size()) {
            resize();
        }
    }

    private void resize() {
        List<MyEntry<K, V>>[] arrayNew = new ArrayList[(int) (buckets.length * RESIZE_INDEX) + 1];
        for(List<MyEntry<K, V>> array : buckets) {
            if(array != null) {
                for(MyEntry<K, V> entry : array) {
                    if(arrayNew[getIndex(entry.getKey())] != null) {
                        arrayNew[getIndex(entry.getKey())].add(entry);
                    }
                    else {
                        List<MyEntry<K, V>> newEntryArray = new ArrayList<>();
                        newEntryArray.add(entry);
                        arrayNew[getIndex(entry.getKey())] = newEntryArray;
                    }
                }
            }
        }
        buckets = arrayNew;
    }

}
