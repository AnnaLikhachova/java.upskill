package com.likhachova.orm;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;

public interface QueryGenerator {

    String findAll(Class<?> clazz);

    String findById(Class<?> clazz, Object id);

    String deleteById(Class<?> clazz, Object id);

    String insert(Object value) throws IllegalAccessException, IntrospectionException, InvocationTargetException;

    String update(Object value) throws IllegalAccessException, InvocationTargetException, IntrospectionException;
}
