package com.likhachova.map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MyHashMapTest {

    private MyHashMap<String, Integer> map;

    @BeforeEach
    public void testListInit() {
        map = new MyHashMap<>();
    }

    @Test
    @DisplayName("The element is added by index")
    public void whenAddNewElementByIndex_thenElementIsAdded() {
        String a = "A";
        String b = "B";
        String c = "C";

        map.put(a, 0);
        map.put(b, 1);
        map.put(c, 2);

        assertEquals(0, map.get(a));
        assertEquals(1, map.get(b));
        assertEquals(2, map.get(c));

        assertTrue(map.size() == 3);
    }

    @Test
    @DisplayName("Map contains element that was added to the Map")
    public void whenAddElement_thenListContainsThisElement() {
        String a = "A";
        map.put(a, 0);
        assertEquals(true, map.containsKey(a));
    }

    @Test
    @DisplayName("The element is removed by index")
    public void whenRemoveElementByIndex_thenElementIsRemoved() {
        String a = "A";
        String b = "B";
        String c = "C";

        map.put(a, 0);
        map.put(b, 1);
        map.put(c, 2);

        map.remove(b);

        assertFalse(map.get(b) != null);
        assertTrue(map.size() == 2);
    }

    @Test
    @DisplayName("The element is returned by index")
    public void whenGetElementByIndex_thenElementIsReturned() {
        String a = "A";
        String b = "B";

        map.put(a, 0);
        map.put(b, 1);

        assertEquals(0, map.get(a));
        assertEquals(1, map.get(b));

    }

    @Test
    @DisplayName("Next element is returned by iterator")
    public void whenIterateToNextElement_thenNextElementIsReturned() {
        String a = "A";
        String b = "B";
        String c = "C";

        map.put(a, 0);
        map.put(b, 1);
        map.put(c, 2);

        Iterator<MyHashMap<String, Integer>.MyEntry<String, Integer>> iterator = map.iterator();
        Object o = iterator.next();
        Object o1 = iterator.next();
        Object o2 = iterator.next();

        assertEquals(b, ((MyHashMap.MyEntry) o1).getKey());
        assertEquals(c, ((MyHashMap.MyEntry) o2).getKey());
    }

    @Test
    @DisplayName("Next element is present by iterator")
    public void whenAddNextElement_thenNextElementIsPresent() {
        String a = "A";

        map.put(a, 0);

        Iterator<MyHashMap<String, Integer>.MyEntry<String, Integer>> iterator = map.iterator();

        assertTrue(iterator.hasNext());
    }

    @Test
    @DisplayName("Element is removed by iterator")
    public void whenRemoveElement_thenElementIsRemoved() {
        String a = "A";
        String b = "B";

        map.put(a, 0);
        map.put(b, 1);


        Iterator<MyHashMap<String, Integer>.MyEntry<String, Integer>> iterator = map.iterator();
        Object o = iterator.next();
        Object o1 = iterator.next();
        iterator.remove();

        assertEquals(b, ((MyHashMap.MyEntry) o1).getKey());
      //  assertFalse(iterator.hasNext());
    }
}
