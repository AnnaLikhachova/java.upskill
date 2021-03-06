package com.likhachova.map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class MyHashMapTest {

    @Test
    @DisplayName("The element is put to the map")
    public void whenAddNewElement_thenElementIsAdded() {
        MyHashMap<String, Integer> map = new MyHashMap<>();

        map.put("A", 0);
        map.put("B", 1);
        map.put("C", 2);

        assertEquals(0, map.get("A"));
        assertEquals(1, map.get("B"));
        assertEquals(2, map.get("C"));

        assertEquals(3, map.size());
    }

    @Test
    @DisplayName("Map contains element that was added to the Map")
    public void whenAddElement_thenListContainsThisElement() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.put("A", 0);
        map.put("A", 0);
        assertTrue(map.containsKey("A"));
    }

    @Test
    @DisplayName("The element is removed by index")
    public void whenRemoveElementByIndex_thenElementIsRemoved() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.put("A", 0);
        map.put("B", 1);
        map.put("C", 2);

        map.remove("B");
        assertEquals(2, map.size());
    }

    @Test
    @DisplayName("The element is returned by index")
    public void whenGetElementByIndex_thenElementIsReturned() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.put("A", 0);
        map.put("B", 1);

        assertEquals(0, map.get("A"));
        assertEquals(1, map.get("B"));
    }

    @Test
    @DisplayName("Next element is returned by iterator")
    public void whenIterateToNextElement_thenNextElementIsReturned() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.put("A", 0);
        map.put("B", 1);
        map.put("C", 2);

        Iterator<MyHashMap.MyEntry<String, Integer>> iterator = map.iterator();
        MyHashMap.MyEntry o = iterator.next();
        MyHashMap.MyEntry o1 = iterator.next();
        MyHashMap.MyEntry o2 = iterator.next();

        assertEquals("A", o.getKey());
        assertEquals("B", o1.getKey());
        assertEquals("C", o2.getKey());
    }

    @Test
    @DisplayName("Next element is present by hasNext method in iterator")
    public void whenAddNextElement_thenNextElementIsPresent() {
        MyHashMap<String, Integer> map = new MyHashMap<>();

        map.put("A", 0);

        Iterator<MyHashMap.MyEntry<String, Integer>> iterator = map.iterator();

        assertTrue(iterator.hasNext());
    }

    @Test
    @DisplayName("Element is removed by iterator")
    public void whenRemoveElement_thenElementIsRemoved() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.put("A", 0);
        map.put("B", 1);

        Iterator<MyHashMap.MyEntry<String, Integer>> iterator = map.iterator();
        MyHashMap.MyEntry o = iterator.next();
        MyHashMap.MyEntry o1 = iterator.next();
        iterator.remove();

        assertEquals("A", o.getKey());
        assertEquals("B", o1.getKey());
        assertFalse(iterator.hasNext());
    }

    @Test
    @DisplayName("When get null element by not null key then null is returned")
    public void whenGetByNotNullKey_thenNullShouldBeReturned() {
        Map<String, String> map = new MyHashMap<>();
        assertNull(map.get("key"));
    }

    @Test
    @DisplayName("When put element to the map the size is equal to one and element is inserted")
    public void whenPutOnce_thenSizeShouldBeEqualToOneAndValueShouldBeEqualToInserted() {

        Map<String, String> map = new MyHashMap<>();

        String key = null;
        String value = "test";

        map.put(key, value);

        assertEquals(1, map.size());
        assertEquals(value, map.get(key));
    }

    @Test
    @DisplayName("When put several elements by the same key then the size remain the same and value is overwritten")
    public void whenPutMultipleTimes_thenSizeShouldBeEqualToOneAndValueShouldBeOverwrittenWithLast() {
        Map<String, String> map = new MyHashMap<>();

        String key = null;
        String firstValue = "test1";
        String secondValue = "test2";
        String thirdValue = "test3";

        map.put(key, firstValue);
        map.put(key, secondValue);
        map.put(key, thirdValue);

        assertEquals(1, map.size());
        assertEquals(thirdValue, map.get(key));
    }

    @Test
    @DisplayName("When put then corresponding value is returned")
    public void whenPut_thenSizeShouldBeEqualToSizeOfKeysAndGetByKeyReturnsCorrespondingValue() {
        Map<String, String> map = new MyHashMap<>();

        String firstKey = "key1";
        String secondKey = "key2";
        String firstValue = "value1";
        String secondValue = "value2";

        map.put(firstKey, firstValue);
        map.put(secondKey, secondValue);

        assertEquals(2, map.size());
        assertEquals(firstValue, map.get(firstKey));
        assertEquals(secondValue, map.get(secondKey));
    }

    @Test
    @DisplayName("When get then corresponding value is returned")
    public void whenGetByExistingKey_thenGetByKeyReturnsCorrespondingValue() {
        Map<String, String> map = new MyHashMap<>();

        String firstKey = "key2";
        String secondKey = "key1";
        String thirdKey = "key3";

        String firstValue = "value2";
        String secondValue = "value3";
        String thirdValue = "value4";

        map.put(firstKey, firstValue);
        map.put(secondKey, secondValue);
        map.put(thirdKey, thirdValue);

        assertEquals(3, map.size());

        assertEquals(firstValue, map.get(firstKey));
        assertEquals(secondValue, map.get(secondKey));
        assertEquals(thirdValue, map.get(thirdKey));
    }

    @Test
    @DisplayName("Null element is returned when get by not existing key")
    public void whenGetByNotExistingKey_thenNullShouldBeReturned() {
        Map<String, String> map = new MyHashMap<>();
        map.put("existing key", "value");

        assertNull(map.get("not existing key"));
    }

    @Test
    @DisplayName("When update the element")
    public void whenPutMultipleTimesWithSameKey_thenSizeShouldBeEqualToOneAndValueShouldBeOverwrittenWithLast() {
        Map<String, String> map = new MyHashMap<>();

        String key = "key";
        String firstValue = "test1";
        String secondValue = "test2";
        String thirdValue = "test3";

        map.put(key, firstValue);
        map.put(key, secondValue);
        map.put(key, thirdValue);

        assertEquals(1, map.size());
        assertEquals(thirdValue, map.get(key));
    }

    @Test
    @DisplayName("When remove by null key then size is zero")
    public void whenRemoveByNullKey_thenSizeShouldBeEqualToZero() {
        Map<String, String> map = new MyHashMap<>();
        map.put(null, "value");
        map.remove(null);

        assertEquals(0, map.size());
    }

    @Test
    @DisplayName("When put null key and remove by null key then size is decreased")
    public void whenPutWithNullKeyAndRemoveByNullKey_thenSizeShouldDecreaseByOne() {
        Map<String, String> map = new MyHashMap<>();
        map.put(null, "value");
        map.put("not null key", "value");

        assertEquals(2, map.size());

        map.remove(null);
        assertEquals(1, map.size());
    }

    @Test
    @DisplayName("When remove last element then size is zero")
    public void whenRemove_thenSizeShouldBeEqualToZero() {
        Map<String, String> map = new MyHashMap<>();

        map.put("key", "value");
        assertEquals(1, map.size());

        map.remove("key");
        assertEquals(0, map.size());
    }

    @Test
    @DisplayName("When remove multiple element then size is decreased after each removal")
    public void whenRemoveOneByOne_thenSizeShouldDecreaseAfterEachRemovalByOne() {
        Map<String, String> map = new MyHashMap<>();

        String firstKey = "key1";
        String secondKey = "key2";
        String thirdKey = "key-1";

        String firstValue = "value1";
        String secondValue = "value2";
        String thirdValue = "value3";

        map.put(firstKey, firstValue);
        map.put(secondKey, secondValue);
        map.put(thirdKey, thirdValue);

        assertEquals(3, map.size());

        map.remove(firstKey);
        assertEquals(2, map.size());

        map.remove(secondKey);
        assertEquals(1, map.size());

        map.remove(thirdKey);
        assertEquals(0, map.size());
    }

    @Test
    @DisplayName("When remove one element then size is decreased by one")
    public void whenRemoveFirstNode_thenSizeShouldDecreaseByOne() {
        Map<String, String> map = new MyHashMap<>();

        String firstKey = "key1";
        String secondKey = "key2";
        String thirdKey = "key3";
        String fourthKey = "key4";

        String firstValue = "value1";
        String secondValue = "value2";
        String thirdValue = "value3";
        String fourthValue = "value4";

        map.put(firstKey, firstValue);
        map.put(secondKey, secondValue);
        map.put(thirdKey, thirdValue);
        map.put(fourthKey, fourthValue);

        assertEquals(4, map.size());

        map.remove(secondKey);
        assertEquals(3, map.size());
    }

    @Test
    @DisplayName("When remove by not existing key")
    public void whenRemoveByNotExistingKey_thenSizeShouldNotChange() {
        Map<String, String> map = new MyHashMap<>();
        map.put("key", "value");

        assertEquals(1, map.size());

        map.remove("not existing key");
        assertEquals(1, map.size());
    }

    @Test
    @DisplayName("Map does not contain null")
    public void whenContainsNullKey_thenFalseShouldBeReturned() {
        Map<String, String> map = new MyHashMap<>();
        assertEquals(false, map.containsKey(null));
    }

    @Test
    @DisplayName("Map does not contain not existing key")
    public void whenContainsNotNullKey_thenFalseShouldBeReturned() {
        Map<String, String> map = new MyHashMap<>();
        assertEquals(false, map.containsKey("key"));
    }

    @Test
    @DisplayName("Map contains null")
    public void whenContainsNullKey_thenTrueShouldBeReturned() {
        Map<String, String> map = new MyHashMap<>();
        map.put(null, "value");

        assertEquals(true, map.containsKey(null));
    }

    @Test
    @DisplayName("Map does not contain null")
    public void whenPutNotNullKeyAndInvokeContainsMethodWithNullKey_thenFalseShouldBeReturned() {
        Map<String, String> map = new MyHashMap<>();
        map.put("key", "value");

        assertEquals(false, map.containsKey(null));
    }

    @Test
    @DisplayName("Map contains existing key")
    public void whenContainsKey_thenFalseShouldBeReturned() {
        Map<String, String> map = new MyHashMap<>();
        map.put("key", "value");

        assertEquals(true, map.containsKey("key"));
    }

    @Test
    @DisplayName("Map does not contain not existing key")
    public void whenContainsKey_thenTrueShouldBeReturned() {
        Map<String, String> map = new MyHashMap<>();
        map.put("key", "value");

        assertEquals(map.containsKey("not existing key"), false);
    }

    @Test
    @DisplayName("Map contains all elements that were put in it")
    public void whenContainsByKey_thenTrueShouldBeReturned() {
        Map<String, String> map = new MyHashMap<>();

        String firstKey = "key1";
        String secondKey = "key2";
        String thirdKey = "key3";
        String fourthKey = "key4";

        String firstValue = "value1";
        String secondValue = "value2";
        String thirdValue = "value3";
        String fourthValue = "value4";

        map.put(firstKey, firstValue);
        map.put(secondKey, secondValue);
        map.put(thirdKey, thirdValue);
        map.put(fourthKey, fourthValue);

        assertTrue(map.containsKey(firstKey));
        assertTrue(map.containsKey(secondKey));
        assertTrue(map.containsKey(thirdKey));
        assertTrue(map.containsKey(fourthKey));
    }

    @Test
    @DisplayName("When put multiple elements in same bucket then the bucket contains these elements")
    public void whenPutMultipleNodesInSameBucketAndNotExistingKeyWithHashLeadingToSameBucket_thenFalseShouldBeReturned() {
        Map<String, String> map = new MyHashMap<>();

        String firstKey = "key1";
        String secondKey = "key2";
        String thirdKey = "key3";
        String fourthKey = "key4";
        String notExistingKey = "not existing key";

        String firstValue = "value1";
        String secondValue = "value2";
        String thirdValue = "value3";
        String fourthValue = "value4";

        map.put(firstKey, firstValue);
        map.put(secondKey, secondValue);
        map.put(thirdKey, thirdValue);
        map.put(fourthKey, fourthValue);

        assertTrue(map.containsKey(firstKey));
        assertTrue(map.containsKey(secondKey));
        assertTrue(map.containsKey(thirdKey));
        assertTrue(map.containsKey(fourthKey));
        assertFalse(map.containsKey(notExistingKey));
    }

    @Test
    @DisplayName("Throw NoSuchElementException when iterate empty map")
    public void whenIteratorNext_thenNoSuchElementExceptionShouldBeRaised() {
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            new MyHashMap<>().iterator().next();
        });
    }

    @Test
    @DisplayName("Throw NoSuchElementException when there is no next element")
    public void whenNextAfterLastElement_thenNoSuchElementExceptionShouldBeRaised() {
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            Map<String, String> map = new MyHashMap<>();

            String key = "key";
            String value = "value";
            map.put(key, value);

            Iterator<MyHashMap.MyEntry<String, String>> iterator = map.iterator();

            MyHashMap.MyEntry<String, String> entry = iterator.next();
            assertEquals(entry.getKey(), key);
            assertEquals(entry.getValue(), value);

            iterator.next();
        });
    }

    @Test
    @DisplayName("Next value is returned by iterator")
    public void whenNext_thenShouldReturnNextValue() {
        Map<String, String> map = new MyHashMap<>();

        String keyQ = "0";
        String keyW = "key1";
        String keyE = "key2";
        String keyR = "key3";
        String keyT = "key4";
        String keyY = "key5";
        String keyZ = "key6";

        String keyQValue = "keyQValue";
        String keyWValue = "keyWValue";
        String keyEValue = "keyEValue";
        String keyRValue = "keyRValue";
        String keyTValue = "keyTValue";
        String keyYValue = "keyYValue";
        String keyZValue = "keyZValue";

        map.put(keyQ, keyQValue);
        map.put(keyW, keyWValue);
        map.put(keyE, keyEValue);
        map.put(keyR, keyRValue);
        map.put(keyT, keyTValue);
        map.put(keyY, keyYValue);
        map.put(keyZ, keyZValue);

        Iterator<MyHashMap.MyEntry<String, String>> iterator = map.iterator();

        MyHashMap.MyEntry<String, String> resultQ = iterator.next();
        MyHashMap.MyEntry<String, String> resultW = iterator.next();
        MyHashMap.MyEntry<String, String> resultE = iterator.next();
        MyHashMap.MyEntry<String, String> resultR = iterator.next();
        MyHashMap.MyEntry<String, String> resultT = iterator.next();
        MyHashMap.MyEntry<String, String> resultY = iterator.next();
        MyHashMap.MyEntry<String, String> resultZ = iterator.next();

        assertEquals(keyW, resultW.getKey());
        assertEquals(keyWValue, resultW.getValue());

        assertEquals(keyE, resultE.getKey());
        assertEquals(keyEValue, resultE.getValue());

        assertEquals(keyR, resultR.getKey());
        assertEquals(keyRValue, resultR.getValue());

        assertEquals(keyT, resultT.getKey());
        assertEquals(keyTValue, resultT.getValue());

        assertEquals(keyY, resultY.getKey());
        assertEquals(keyYValue, resultY.getValue());

        assertEquals(keyZ, resultZ.getKey());
        assertEquals(keyZValue, resultZ.getValue());
    }

    @Test
    @DisplayName("Empty map has no next element")
    public void whenIteratorHasNext_thenShouldReturnFalse() {
        Map<String, String> map = new MyHashMap<>();
        assertFalse(map.iterator().hasNext());
    }

    @Test
    @DisplayName("When put element then map has next element")
    public void whenIteratorHasNext_thenShouldReturnTrue() {
        Map<String, String> map = new MyHashMap<>();
        map.put("key", "value");

        Iterator<MyHashMap.MyEntry<String, String>> iterator = map.iterator();
        assertTrue(iterator.hasNext());
        assertTrue(iterator.hasNext());

        map.remove("key");
        assertFalse(iterator.hasNext());

        map.put("key", "value");
        assertTrue(iterator.hasNext());
    }

    @Test
    @DisplayName("Next element is returned by iterator")
    public void whenIteratorNext_thenIteratorHasNextShouldReturnTrue() {
        Map<String, String> map = new MyHashMap<>();
        map.put("key", "value");
        map.put("key2", "value");

        Iterator<MyHashMap.MyEntry<String, String>> iterator = map.iterator();

        assertTrue(iterator.hasNext());
        iterator.next();
        assertTrue(iterator.hasNext());
    }

    @Test
    @DisplayName("Next element is not present in map with one element when call method hasNext two times")
    public void whenIteratorNext_thenIteratorHasNextShouldReturnFalse() {
        Map<String, String> map = new MyHashMap<>();
        map.put("key", "value");

        Iterator<MyHashMap.MyEntry<String, String>> iterator = map.iterator();

        assertTrue(iterator.hasNext());
        iterator.next();
        assertFalse(iterator.hasNext());
    }

    @Test
    @DisplayName("Throw IllegalStateException when remove in empty map")
    public void whenIteratorRemove_thenIllegalStateExceptionShouldBeRaised() {
        Assertions.assertThrows(IllegalStateException.class, () -> {
            new MyHashMap<>().iterator().remove();
        });
    }

    @Test
    @DisplayName("Throw IllegalStateException when remove without call method next")
    public void whenRemoveCalledWithoutNext_thenIllegalStateExceptionShouldBeRaised() {
        Assertions.assertThrows(IllegalStateException.class, () -> {
            Map<String, String> map = new MyHashMap<>();
            map.put("key", "value");

            assertEquals(1, map.size());

            map.iterator().remove();
        });
    }

    @Test
    @DisplayName("Remove is called after next and size is decreased")
    public void whenRemoveCalledAfterNext_thenSizeShouldBeDecreasedByOneAndMapShouldNotContainKey() {
        Map<String, String> map = new MyHashMap<>();
        String key = "key";
        map.put(key, "value");
        assertEquals(1, map.size());

        Iterator<MyHashMap.MyEntry<String, String>> iterator = map.iterator();
        iterator.next();
        iterator.remove();

        assertEquals(0, map.size());
        assertFalse(map.containsKey(key));
    }

    @Test
    @DisplayName("Map size is resized when add 17 elements")
    public void whenAddTwentyElements_thenMapSizeIsResized() {
        Map<String, String> map = new MyHashMap<>();

        map.put("key1", "value1");
        map.put("key2", "value1");
        map.put("key3", "value1");
        map.put("key4", "value1");
        map.put("key5", "value1");
        map.put("key6", "value1");
        map.put("key7", "value1");
        map.put("key8", "value1");
        map.put("key9", "value1");
        map.put("key10", "value1");
        map.put("key11", "value1");
        map.put("key12", "value1");
        map.put("key13", "value1");
        map.put("key14", "value1");
        map.put("key15", "value1");
        map.put("key16", "value1");
        map.put("key17", "value1");
        assertEquals(17, map.size());
    }

}
