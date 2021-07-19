package com.likhachova.map;

import java.util.*;

public class MyHashMap<K,V> implements Map<K,V>{
    private static final int INITIAL_CAPACITY = 16;
    private ArrayList<MyEntry<K,V>>[] buckets;

    MyHashMap() {
        this.buckets = new ArrayList[INITIAL_CAPACITY];
    }

    @Override
    public V put(K key, V value) {
        if(key == null) {
            if(buckets[0] == null) {
                ArrayList<MyEntry<K, V>> entryArrayList = new ArrayList<>();
                buckets[0] = entryArrayList;
                buckets[0].add(new MyEntry<>(key, value));
            }
            else {
                ArrayList<MyEntry<K, V>> entryArrayList = buckets[0];
                for(MyEntry<K, V> myEntry : entryArrayList) {
                    if(myEntry.getKey() == null) {
                        if(!myEntry.getValue().equals(value)) {
                            myEntry.setValue(value);
                        }
                    }
                    else {
                        buckets[0].add(new MyEntry<>(key, value));
                    }
                }
            }
        }
        else {
            if(buckets[getIndex(key)] == null) {
                ArrayList<MyEntry<K, V>> entryArrayList = new ArrayList<>();
                buckets[getIndex(key)] = entryArrayList;
                entryArrayList.add(new MyEntry<>(key, value));
            }
            else {
                ArrayList<MyEntry<K, V>> entryArrayList = buckets[getIndex(key)];
                for(MyEntry<K, V> myEntry : entryArrayList) {
                    if(myEntry.getKey().equals(key)) {
                        if(!myEntry.getValue().equals(value)) {
                            myEntry.setValue(value);
                        }
                    }
                    else {
                        buckets[getIndex(key)].add(new MyEntry<>(key, value));
                    }
                }

            }
        }
        return value;
    }

    @Override
    public V get(K key) {
        ArrayList<MyEntry<K,V>> entryArrayList = buckets[getIndex(key)];
        for(MyEntry<K,V> myEntry: entryArrayList){
            if(myEntry.getKey().equals(key)){
                return myEntry.getValue();
            }
        }
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        for(java.util.Map.Entry entry : buckets[getIndex(key)]){
            if(entry.getKey().equals(key)){
                return true;
            }
        }
        return false;
    }

    @Override
    public V remove(K key) {
        ArrayList<MyEntry<K,V>> entryArrayList = buckets[getIndex(key)];
        entryArrayList.removeIf(nextEntry -> nextEntry.getKey().equals(key));
        return null;
    }

    @Override
    public int size() {
        int size = 0;
        for(ArrayList<MyEntry<K,V>> bucket : buckets){
            if(bucket != null) {
                size += bucket.size();
            }
        }
        return size;
    }

    @Override
    public Iterator<MyEntry<K, V>> iterator() {

        Iterator<MyEntry<K, V>> it = new Iterator<MyEntry<K, V>>() {
            private int nextBucket = 0;
            private int nextEntry = 0;
            @Override
            public boolean hasNext() {
                return nextEntry < size();
            }

            @Override
            public MyEntry<K,V> next() {
                ArrayList<MyEntry<K,V>> nextBucketArray = buckets[nextBucket];
                if(nextBucketArray != null) {
                    MyEntry<K,V> result = nextBucketArray.get(nextEntry);
                    nextEntry++;
                    if(nextBucketArray.size() == nextEntry) {
                        nextEntry = 0;
                        nextBucket++;
                    }
                    return result;
                } else {
                    if(nextBucket != buckets.length - 1){
                        nextBucket++;
                        next();
                    }
                }
                return null;
            }

            @Override
            public void remove() {
                ArrayList<MyEntry<K,V>> nextBucketArray = buckets[nextBucket-1];
                nextBucketArray.remove(nextEntry);

            }
        };
        return it;
    }

    final class MyEntry<K, V> implements java.util.Map.Entry<K, V> {
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

    private int getIndex(Object o){
        return o.hashCode() % buckets.length;
    }

}
