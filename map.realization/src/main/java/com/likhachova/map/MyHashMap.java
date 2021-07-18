package com.likhachova.map;

import java.util.*;

public class MyHashMap<K,V> implements Map<K,V>{
    private static final int INITIAL_CAPACITY = 16;
    private ArrayList<MyEntry>[] buckets;
    private int nextBucket = 0;
    private int nextEntry = 0;

    MyHashMap() {
        this.buckets = new ArrayList[INITIAL_CAPACITY];
        for(ArrayList<MyEntry> array : buckets){
            array = new ArrayList<>();
        }
    }

    @Override
    public Object put(Object key, Object value) {
        if(buckets[getIndex(key)] == null){
            ArrayList<MyEntry> entryArrayList = new ArrayList<>();
            buckets[getIndex(key)] = entryArrayList;
            entryArrayList.add(new MyEntry(key,value) );
        } else {
            buckets[getIndex(key)].add(new MyEntry(key,value));
        }
        return value;
    }

    @Override
    public Object get(Object key) {
        ArrayList<MyEntry> entryArrayList = buckets[getIndex(key)];
        for(MyEntry myEntry: entryArrayList){
            if(myEntry.getKey().equals(key)){
                return myEntry.getValue();
            }
        }
        return null;
    }

    @Override
    public boolean containsKey(Object key) {
        for(java.util.Map.Entry entry : buckets[getIndex(key)]){
            if(entry.getKey().equals(key)){
                return true;
            }
        }
        return false;
    }

    @Override
    public Object remove(Object key) {
        ArrayList<MyEntry> entryArrayList = buckets[getIndex(key)];
        Iterator<MyEntry> iterator = entryArrayList.iterator();
        while(iterator.hasNext()){
            MyEntry nextEntry = iterator.next();
            if(nextEntry.getKey().equals(key)){
                iterator.remove();
            }
        }
        return null;
    }

    @Override
    public int size() {
        int size = 0;
        for(ArrayList<MyEntry> bucket : buckets){
            if(bucket != null) {
                size += bucket.size();
            }
        }
        return size;
    }

    @Override
    public Iterator<MyEntry<K, V>> iterator() {

        Iterator<MyEntry<K, V>> it = new Iterator<MyEntry<K, V>>() {

            @Override
            public boolean hasNext() {
                return nextEntry < size();
            }

            @Override
            public MyEntry next() {
                ArrayList<MyEntry> nextBucketArray = buckets[nextBucket];
                if(nextBucketArray != null) {
                    MyEntry result = nextBucketArray.get(nextEntry);
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
                ArrayList<MyEntry> nextBucketArray = buckets[nextBucket-1];
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
