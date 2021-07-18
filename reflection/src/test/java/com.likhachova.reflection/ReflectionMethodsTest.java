package com.likhachova.reflection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReflectionMethodsTest {

    private TestObject testObject;

    @BeforeEach
    public void testListInit() {
        testObject = new TestObject();
    }

    @Test
    @DisplayName("When accept class then object is returned")
    public void testAcceptClassAndReturnsObject() throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException{
        ReflectionMethods reflectionMethods = new ReflectionMethods();
        TestObject reflectionObject = (TestObject) reflectionMethods.acceptClassAndReturnsObject(TestObject.class);
        assertTrue(reflectionObject instanceof TestObject);

    }

    @Test
    @DisplayName("When invoke methods without params then methods are invoked")
    public void testAcceptObjectAndCallsMethodsWithoutParam() throws IllegalAccessException, InvocationTargetException {
        ReflectionMethods reflectionMethods = new ReflectionMethods();
        reflectionMethods.acceptObjectAndCallsMethodsWithoutParam(testObject);
        assertEquals("New York", testObject.getAddress());
    }

    @Test
    @DisplayName("When invoke private methods without params with illegal type then InvocationTargetException is thrown")
    public void whenCapacityIsInvalid_thenNegativeArraySizeExceptionIsThrown() {
        Assertions.assertThrows(InvocationTargetException.class, () -> {
            ReflectionMethods reflectionMethods = new ReflectionMethods();
            reflectionMethods.acceptObjectAndCallsMethodsWithoutParam(testObject.getClass());
        });
    }

    @Test
    @DisplayName("When invoke acceptObjectAndReturnsAllFinalMethods then all methods with final modifier are returned")
    public void testAcceptObjectAndReturnsAllFinalMethods() {
        ReflectionMethods reflectionMethods = new ReflectionMethods();
        String actualResult = reflectionMethods.acceptObjectAndReturnsAllFinalMethods(testObject);
        String expectedResult = "All methods names with final modifier: publicIncreaseSalary";
        assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("When invoke acceptClassAndReturnsAllNotPublicMethods then not public methods are returned")
    public void testAcceptClassAndReturnsAllNotPublicMethods() {
        ReflectionMethods reflectionMethods = new ReflectionMethods();
        String actualResult = reflectionMethods.acceptClassAndReturnsAllNotPublicMethods(testObject.getClass());
        String expectedResult = "All methods names with not public modifier: privateAnd, protectedMax, privateChangeLocation, privatePrintAddress";
        assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("When invoke acceptClassAndReturnsAllAncestorsAndInterfaces then all ancestors and interfaces are returned")
    public void testAcceptClassAndReturnsAllAncestorsAndInterfaces() {
        ReflectionMethods reflectionMethods = new ReflectionMethods();
        String actualResult = reflectionMethods.acceptClassAndReturnsAllAncestorsAndInterfaces(testObject.getClass());
        String expectedResult = "All interfaces of the class: java.io.Serializable, java.lang.Runnable. All ancestor of the class: java.lang.Object.";
        assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("When change all private fields values then values are changed")
    public void testAcceptObjectAndChangeAllPrivateFieldsValues() throws IllegalAccessException {
        ReflectionMethods reflectionMethods = new ReflectionMethods();
        int expectedAge = 0;
        String expectedName = null;
        double expectedSalary = 0.0;
        String expectedAddress = null;
        reflectionMethods.acceptObjectAndChangeAllPrivateFieldsValues(testObject);
        assertEquals(expectedAge, testObject.getAge());
        assertEquals(expectedName, testObject.getName());
        assertEquals(expectedSalary, testObject.getSalary());
        assertEquals(expectedAddress, testObject.getAddress());
    }

}
