package com.likhachova.reflection;

import org.springframework.stereotype.Service;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

@Service
public class ReflectionMethods {

//    Метод принимает класс и возвращает созданный объект этого класса
//    Метод принимает object и вызывает у него все методы без параметров
//    Метод принимает object и выводит на экран все сигнатуры методов в который есть final
//    Метод принимает Class и выводит все не публичные методы этого класса
//    Метод принимает Class и выводит всех предков класса и все интерфейсы которое класс имплементирует
//    Метод принимает объект и меняет всего его приватные поля на их нулевые значение (null, 0, false etc)

    public Object acceptClassAndReturnsObject(Class<?> clazz) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        Constructor<?> constructor = clazz.getConstructor();
        return constructor.newInstance();
    }

    public void acceptObjectAndCallsMethodsWithoutParam(Object obj) throws InvocationTargetException, IllegalAccessException {
        for(Method method : obj.getClass().getDeclaredMethods()) {
            if(method.getParameterCount() == 0) {
                method.setAccessible(true);
                method.invoke(obj, null);
            }
        }
    }

    public String acceptObjectAndReturnsAllFinalMethods(Object obj) {
        StringBuilder builder = new StringBuilder("All methods names with final modifier: ");
        StringJoiner joiner = new StringJoiner(", ");
        for(Method method : obj.getClass().getDeclaredMethods()) {
            int modefiers = method.getModifiers();
            if(Modifier.isFinal(modefiers)) {
                joiner.add(method.getName());
            }
        }
        builder.append(joiner);
        return builder.toString();
    }

    public String acceptClassAndReturnsAllNotPublicMethods(Class<?> clazz) {
        StringBuilder builder = new StringBuilder("All methods names with not public modifier: ");
        StringJoiner joiner = new StringJoiner(", ");
        for(Method method : clazz.getDeclaredMethods()) {
            int modefiers = method.getModifiers();
            if(!Modifier.isPublic(modefiers)) {
                joiner.add(method.getName());
            }
        }
        builder.append(joiner);
        return builder.toString();
    }

    public String acceptClassAndReturnsAllAncestorsAndInterfaces(Class<?> clazz) {
        StringBuilder builder = new StringBuilder();
        StringJoiner interfaceJoiner = new StringJoiner(", ", "All interfaces of the class: ", ". ");
        Class<?>[] intfs = clazz.getInterfaces();
        for(Class<?> anInterface : intfs) {
            interfaceJoiner.add(anInterface.getCanonicalName());
        }
        builder.append(interfaceJoiner);
        StringJoiner ancestorJoiner = new StringJoiner(", ", "All ancestor of the class: ", ".");
        Class<?> superclass = clazz.getSuperclass();
        while(superclass != null) {
            clazz = superclass;
            ancestorJoiner.add(clazz.getCanonicalName());
            superclass = clazz.getSuperclass();
        }
        builder.append(ancestorJoiner);
        return builder.toString();
    }

    public void acceptObjectAndChangeAllPrivateFieldsValues(Object obj) throws IllegalAccessException {
        for(Field field : obj.getClass().getDeclaredFields()) {
            int modefiers = field.getModifiers();
            if(Modifier.isPrivate(modefiers)) {
                Class<?> fieldType = field.getType();
                field.setAccessible(true);
                if(fieldType.isPrimitive()) {
                    if(fieldType.getName().equals("boolean")) {
                        field.set(obj, false);
                    }
                    else {
                        field.set(obj, 0);
                    }
                }
                else {
                    field.set(obj, null);
                }
            }
        }

    }

    private Field getField(Class mClass, String fieldName) throws NoSuchFieldException {
        try {
            return mClass.getDeclaredField(fieldName);
        }
        catch(NoSuchFieldException e) {
            Class superClass = mClass.getSuperclass();
            if(superClass == null) {
                throw e;
            }
            else {
                return getField(superClass, fieldName);
            }
        }
    }
}
