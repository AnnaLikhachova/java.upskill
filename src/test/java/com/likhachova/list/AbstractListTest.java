package com.likhachova.list;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Anna Likhachova<br/>
 *         Creation date: 11/07/21.
 * @since 1.0
 */
public abstract class AbstractListTest {

    CustomList list = getList();

    public abstract CustomList getList();

    @Test
    public void testListInit(){
        assertEquals(0, list.size());
    }

    @Test
    public void whenCapacityIsInvalid_thenNegativeArraySizeExceptionIsThrown(){
        Assertions.assertThrows(NegativeArraySizeException.class, () -> {
            list = new MyArrayList(-1);
        });
    }

    @Test
    public void whenAddNewElementByIndex_thenElementIsAdded(){
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

        assertTrue(list.size()==4);
    }

    @Test
    public void whenAddElementWithNegativeIndex_thenIndexOutOfBoundsExceptionIsThrown(){
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            list.add("A", -1);
        });
    }

    @Test
    public void whenAddElementWithOutboundsIndex_thenIndexOutOfBoundsExceptionIsThrown(){
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            list.add("Object", list.size()+1);
        });
    }

    @Test
    public void whenSetElementByIndex_thenElementIsUpdated(){
        list.add("A", 0);
        list.add("B", 1);
        list.add("C", 2);

        list.set("D", 1);

        assertEquals("A", list.get(0));
        assertEquals("D", list.get(1));
        assertEquals("C", list.get(2));
    }

    @Test
    public void whenRemoveElementByIndex_thenElementIsRemoved(){
        list.add("A", 0);
        list.add("B", 1);
        list.add("C", 2);

        list.remove(1);

        assertEquals("C", list.get(1));
        assertTrue(list.size() == 2);
    }

    @Test
    public void whenRemoveElementWithNoExistingIndex_thenIndexOutOfBoundsExceptionIsThrown(){
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            list.remove(0);
        });
    }

    @Test
    public void whenGetElementWithOutboundsIndex_thenIndexOutOfBoundsExceptionIsThrown(){
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            list.get(list.size());
        });
    }

    @Test
    public void whenGetElementWithNotExistingIndex_thenIndexOutOfBoundsExceptionIsThrown(){
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            list.get(0);
        });
    }

    @Test
    public void whenGetElementByIndex_thenElementIsReturned(){
        list.add("A", 0);
        list.add("B", 1);
        list.add("C", 2);

        assertEquals("A", list.get(0));
        assertEquals("B", list.get(1));
        assertEquals("C", list.get(2));
    }

    @Test
    public void whenClearList_thenListSizeIsZero(){
        list.add("A", 0);
        list.add("B", 1);
        list.add("C", 2);

        list.clear();

        assertEquals(0, list.size());
    }

    @Test
    public void whenAddElement_thenListSizeIsIncreased(){
        list.add("A", 0);

        assertEquals(1, list.size());
    }

    @Test
    public void whenAddElement_thenListContainsThisElement(){
        list.add("A");

        assertEquals(true, list.contains("A"));
    }

    @Test
    public void whenGetFirstOccurrenceOfElementByIndex_thenFirstOccurrenceOfElementByIndexIsReturned(){
        list.add("A");
        list.add("B");
        list.add("A");
        list.add("C");

        assertEquals(0, list.indexOf("A"));
    }

    @Test
    public void whenGetLastOccurrenceOfElementByIndex_thenLastOccurrenceOfElementByIndexIsReturned(){
        list.add("A");
        list.add("B");
        list.add("A");
        list.add("C");

        assertEquals(2, list.lastIndexOf("A"));
    }

    @Test
    public void whenGetListToString_thenStringRepresentationIsReturned(){
        list.add("A");
        list.add("B");
        list.add("C");

        assertEquals("[A, B, C]", list.toString());
    }

    @Test
    public void whenIterateToNextElement_thenNextElementIsReturned(){
        list.add("A");
        list.add("B");
        list.add("C");

        CustomIterator customIterator = list.iterator();
        Object o = customIterator.next();
        Object o1 = customIterator.next();
        Object o2 = customIterator.next();


        assertEquals("A", o);
        assertEquals("B", o1);
        assertEquals("C", o2);
    }

    @Test
    public void whenAddNextElement_thenNextElementIsPresent(){
        list.add("A");

        CustomIterator customIterator = list.iterator();

        assertTrue(customIterator.hasNext());
    }

    @Test
    public void whenRemoveElement_thenElementIsRemoved(){
        list.add("A");
        list.add("B");

        CustomIterator customIterator = list.iterator();
        Object o = customIterator.next();
        customIterator.remove();
        Object o1 = customIterator.next();

        assertEquals("B", o1);
        assertFalse(customIterator.hasNext());
    }

}
