package com.likhachova.map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MyHashMapTest {

    @Test
    @DisplayName("The element is added by index")
    public void whenAddNewElementByIndex_thenElementIsAdded() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
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
        MyHashMap<String, Integer> map = new MyHashMap<>();
        String a = "A";
        map.put(a, 0);
        assertEquals(true, map.containsKey(a));
    }

    @Test
    @DisplayName("The element is removed by index")
    public void whenRemoveElementByIndex_thenElementIsRemoved() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        String a = "A";
        String b = "B";
        String c = "C";

        map.put(a, 0);
        map.put(b, 1);
        map.put(c, 2);

        map.remove(b);
        assertTrue(map.size() == 2);
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
        Object o = iterator.next();
        Object o1 = iterator.next();
        Object o2 = iterator.next();

        assertEquals("A", ((MyHashMap.MyEntry) o).getKey());
        assertEquals("B", ((MyHashMap.MyEntry) o1).getKey());
        assertEquals("C", ((MyHashMap.MyEntry) o2).getKey());
    }

    @Test
    @DisplayName("Next element is present by hasNext method in iterator")
    public void whenAddNextElement_thenNextElementIsPresent() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        String a = "A";

        map.put(a, 0);

        Iterator<MyHashMap.MyEntry<String, Integer>> iterator = map.iterator();

        assertTrue(iterator.hasNext());
    }

    @Test
    @DisplayName("Element is removed by iterator")
    public void whenRemoveElement_thenElementIsRemoved() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        String a = "A";
        String b = "B";

        map.put(a, 0);
        map.put(b, 1);

        Iterator<MyHashMap.MyEntry<String, Integer>> iterator = map.iterator();
        Object o = iterator.next();
        Object o1 = iterator.next();
        iterator.remove();

        assertEquals(a, ((MyHashMap.MyEntry) o).getKey());
        assertEquals(b, ((MyHashMap.MyEntry) o1).getKey());
        assertFalse(iterator.hasNext());
    }

    @Test
    @DisplayName("When get null element by not null key then null is returned")
    public void whenGetByNotNullKey_thenNullShouldBeReturned() {
        Map<String, String> map = new MyHashMap<>();
        assertEquals(null, map.get("key"));
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
    @DisplayName("When det then corresponding value is returned")
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

        assertEquals(null, map.get("not existing key"));
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
    @DisplayName("When remove one element then size is decreased")
    public void whenRemoveFirstNode_thenSizeShouldDecreaseByOne() {
        Map<String, String> map = new MyHashMap<>();

        String firstKey = "key1";
        String secondKey = "key2";
        String thirdKey = "key-1";
        String fourthKey = "key-10";

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
        String thirdKey = "key-1";
        String fourthKey = "key-10";

        String firstValue = "value1";
        String secondValue = "value2";
        String thirdValue = "value3";
        String fourthValue = "value4";

        map.put(firstKey, firstValue);
        map.put(secondKey, secondValue);
        map.put(thirdKey, thirdValue);
        map.put(fourthKey, fourthValue);

        assertEquals(map.containsKey(firstKey), true);
        assertEquals(map.containsKey(secondKey), true);
        assertEquals(map.containsKey(thirdKey), true);
        assertEquals(map.containsKey(fourthKey), true);
    }

    @Test
    @DisplayName("When put multiple elements in same bucket then the bucket contains these elements")
    public void whenPutMultipleNodesInSameBucketAndNotExistingKeyWithHashLeadingToSameBucket_thenFalseShouldBeReturned() {
        Map<String, String> map = new MyHashMap<>();

        String firstKey = "key1";
        String secondKey = "key2";
        String thirdKey = "key-1";
        String fourthKey = "key-10";
        String notExistingKeyWithHashLeadingToSameBucket = "key+12";

        String firstValue = "value1";
        String secondValue = "value2";
        String thirdValue = "value3";
        String fourthValue = "value4";

        map.put(firstKey, firstValue);
        map.put(secondKey, secondValue);
        map.put(thirdKey, thirdValue);
        map.put(fourthKey, fourthValue);

        assertEquals(true, map.containsKey(firstKey));
        assertEquals(true, map.containsKey(secondKey));
        assertEquals(true, map.containsKey(thirdKey));
        assertEquals(true, map.containsKey(fourthKey));
        assertEquals(false, map.containsKey(notExistingKeyWithHashLeadingToSameBucket));
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
        String keyR = "key-1";
        String keyT = "key-10";
        String keyY = "key-100";
        String keyZ = "1000";

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
        assertEquals(false, map.iterator().hasNext());
    }

    @Test
    @DisplayName("When put element then map has next element")
    public void whenIteratorHasNext_thenShouldReturnTrue() {
        Map<String, String> map = new MyHashMap<>();
        map.put("key", "value");

        Iterator<MyHashMap.MyEntry<String, String>> iterator = map.iterator();
        assertEquals(true, iterator.hasNext());
        assertEquals(true, iterator.hasNext());

        map.remove("key");
        assertEquals(false, iterator.hasNext());

        map.put("key", "value");
        assertEquals(true, iterator.hasNext());
    }

    @Test
    @DisplayName("Next element is returned by iterator")
    public void whenIteratorNext_thenIteratorHasNextShouldReturnTrue() {
        Map<String, String> map = new MyHashMap<>();
        map.put("key", "value");
        map.put("key2", "value");

        Iterator<MyHashMap.MyEntry<String, String>> iterator = map.iterator();

        assertEquals(true, iterator.hasNext());
        iterator.next();
        assertEquals(true, iterator.hasNext());
    }

    @Test
    @DisplayName("Next element is not returned by iterator")
    public void whenIteratorNext_thenIteratorHasNextShouldReturnFalse() {
        Map<String, String> map = new MyHashMap<>();
        map.put("key", "value");

        Iterator<MyHashMap.MyEntry<String, String>> iterator = map.iterator();

        assertEquals(true, iterator.hasNext());
        iterator.next();
        assertEquals(false, iterator.hasNext());
    }

    @Test
    @DisplayName("Throw NullPointerException when remove in empty map")
    public void whenIteratorRemove_thenArrayIndexOutOfBoundsExceptionShouldBeRaised() {
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            new MyHashMap<>().iterator().remove();
        });
    }

    @Test
    @DisplayName("Throw IllegalStateException when remove without call method next")
    public void whenRemoveCalledWithoutNext_thenArrayIndexOutOfBoundsExceptionShouldBeRaised() {
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
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
        assertEquals(false, map.containsKey(key));
    }
}
