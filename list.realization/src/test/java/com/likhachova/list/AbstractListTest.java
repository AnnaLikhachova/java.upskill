package com.likhachova.list;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Anna Likhachova
 * Creation date: 11/07/21.
 * @since 2.0
 */
public abstract class AbstractListTest {

    private CustomList<Object> list = getList();

    abstract CustomList getList();

    @Test
    @DisplayName("Set initial List size")
    public void testListInit() {
        assertEquals(0, list.size());
    }

    @Test
    @DisplayName("Throw NegativeArraySizeException when List capacity is invalid")
    public void whenCapacityIsInvalid_thenNegativeArraySizeExceptionIsThrown() {
        Assertions.assertThrows(NegativeArraySizeException.class, () -> {
            list = new MyArrayList(-1);
        });
    }

    @Test
    @DisplayName("The element is added by index")
    public void whenAddNewElementByIndex_thenElementIsAdded() {
        list.add("A", 0);
        list.add("B", 1);
        list.add("C", 2);

        assertEquals("A", list.get(0));
        assertEquals("B", list.get(1));
        assertEquals("C", list.get(2));

        list.add("D", 1);

        assertEquals("A", list.get(0));
        assertEquals("D", list.get(1));
        assertEquals("B", list.get(2));
        assertEquals("C", list.get(3));

        assertTrue(list.size() == 4);
    }

    @Test
    @DisplayName("Throw IndexOutOfBoundsException when add element with negative index to List")
    public void whenAddElementWithNegativeIndex_thenIndexOutOfBoundsExceptionIsThrown() {
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            list.add("A", -1);
        });
    }

    @Test
    @DisplayName("Throw IndexOutOfBoundsException when add element with outbounds index to List")
    public void whenAddElementWithOutboundsIndex_thenIndexOutOfBoundsExceptionIsThrown() {
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            list.add("Object", list.size() + 1);
        });
    }

    @Test
    @DisplayName("The element is updated by index")
    public void whenSetElementByIndex_thenElementIsUpdated() {
        list.add("A", 0);
        list.add("B", 1);
        list.add("C", 2);

        list.set("D", 1);

        assertEquals("A", list.get(0));
        assertEquals("D", list.get(1));
        assertEquals("C", list.get(2));
    }

    @Test
    @DisplayName("The element is removed by index")
    public void whenRemoveElementByIndex_thenElementIsRemoved() {
        list.add("A", 0);
        list.add("B", 1);
        list.add("C", 2);

        list.remove(1);

        assertEquals("C", list.get(1));
        assertTrue(list.size() == 2);
    }

    @Test
    @DisplayName("Throw IndexOutOfBoundsException when remove element with outbounds index to List")
    public void whenRemoveElementWithNoExistingIndex_thenIndexOutOfBoundsExceptionIsThrown() {
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            list.remove(0);
        });
    }

    @Test
    @DisplayName("Throw IndexOutOfBoundsException when get element with outbounds index to List")
    public void whenGetElementWithOutboundsIndex_thenIndexOutOfBoundsExceptionIsThrown() {
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            list.get(list.size());
        });
    }

    @Test
    @DisplayName("Throw IndexOutOfBoundsException when get element with not eisting index to List")
    public void whenGetElementWithNotExistingIndex_thenIndexOutOfBoundsExceptionIsThrown() {
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            list.get(0);
        });
    }

    @Test
    @DisplayName("The element is returned by index")
    public void whenGetElementByIndex_thenElementIsReturned() {
        list.add("A", 0);
        list.add("B", 1);
        list.add("C", 2);

        assertEquals("A", list.get(0));
        assertEquals("B", list.get(1));
        assertEquals("C", list.get(2));
    }

    @Test
    @DisplayName("List size is zero when clean the List")
    public void whenClearList_thenListSizeIsZero() {
        list.add("A", 0);
        list.add("B", 1);
        list.add("C", 2);

        list.clear();

        assertEquals(0, list.size());
    }

    @Test
    @DisplayName("List size increases when add element")
    public void whenAddElement_thenListSizeIsIncreased() {
        list.add("A", 0);

        assertEquals(1, list.size());
    }

    @Test
    @DisplayName("List contains element that was added to the List")
    public void whenAddElement_thenListContainsThisElement() {
        list.add("A");

        assertEquals(true, list.contains("A"));
    }

    @Test
    @DisplayName("Get first occurrence by index")
    public void whenGetFirstOccurrenceOfElementByIndex_thenFirstOccurrenceOfElementByIndexIsReturned() {
        list.add("A");
        list.add("B");
        list.add("A");
        list.add("C");

        assertEquals(0, list.indexOf("A"));
    }

    @Test
    @DisplayName("Get last occurrence by index")
    public void whenGetLastOccurrenceOfElementByIndex_thenLastOccurrenceOfElementByIndexIsReturned() {
        list.add("A");
        list.add("B");
        list.add("A");
        list.add("C");

        assertEquals(2, list.lastIndexOf("A"));
    }

    @Test
    @DisplayName("Get toString representation")
    public void whenGetListToString_thenStringRepresentationIsReturned() {
        list.add("A");
        list.add("B");
        list.add("C");

        assertEquals("[A, B, C]", list.toString());
    }

    @Test
    @DisplayName("Next element is returned by iterator")
    public void whenIterateToNextElement_thenNextElementIsReturned() {
        list.add("A");
        list.add("B");
        list.add("C");

        Iterator<Object> iterator = list.iterator();
        Object o = iterator.next();
        Object o1 = iterator.next();
        Object o2 = iterator.next();


        assertEquals("A", o);
        assertEquals("B", o1);
        assertEquals("C", o2);
    }

    @Test
    @DisplayName("Next element is present by iterator")
    public void whenAddNextElement_thenNextElementIsPresent() {
        list.add("A");

        Iterator<Object> iterator = list.iterator();

        assertTrue(iterator.hasNext());
    }

    @Test
    @DisplayName("Element is removed by iterator")
    public void whenRemoveElement_thenElementIsRemoved() {
        list.add("A");
        list.add("B");

        Iterator<Object> iterator = list.iterator();
        Object o = iterator.next();
        iterator.remove();
        Object o1 = iterator.next();

        assertEquals("B", o1);
        assertFalse(iterator.hasNext());
    }

}
