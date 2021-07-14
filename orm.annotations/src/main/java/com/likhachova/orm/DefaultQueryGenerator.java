package com.likhachova.orm;

import com.likhachova.orm.annotation.Column;
import com.likhachova.orm.annotation.Id;
import com.likhachova.orm.annotation.Table;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.StringJoiner;

public class DefaultQueryGenerator implements QueryGenerator {

    @Override
    public String findAll(Class<?> clazz) {
        String tableName = getTableName(clazz);
        StringBuilder query = new StringBuilder("SELECT ");
        StringJoiner columnNames = getColumnNames(clazz);
        query.append(columnNames);
        query.append(" FROM ");
        query.append(tableName);
        query.append(";");
        return query.toString();
    }

    @Override
    public String findById(Class<?> clazz, Object id) {
        String tableName = getTableName(clazz);
        StringBuilder query = new StringBuilder("SELECT ");
        StringJoiner columnNames = getColumnNames(clazz);
        query.append(columnNames);
        query.append(" FROM ");
        query.append(tableName);
        query.append(" WHERE id=");
        query.append(id);
        query.append(";");
        return query.toString();
    }

    @Override
    public String deleteById(Class<?> clazz, Object id) {
        String tableName = getTableName(clazz);
        StringBuilder query = new StringBuilder("DELETE");
        query.append(" FROM ");
        query.append(tableName);
        query.append(" WHERE id=");
        query.append(id);
        query.append(";");
        return query.toString();
    }

    @Override
    public String insert(Object value) throws IllegalAccessException, IntrospectionException, InvocationTargetException {
        String tableName = value.getClass().getSimpleName();
        StringBuilder query = new StringBuilder("INSERT INTO ");
        query.append(tableName);
        query.append(" (");
        StringJoiner columnNames = getColumnNames(value.getClass());
        query.append(columnNames);
        query.append(") VALUES (");
        query.append(getObjectValues(value));
        query.append(");");
        return query.toString();
    }

    @Override
    public String update(Object value) throws IllegalAccessException, InvocationTargetException, IntrospectionException {
        String tableName = value.getClass().getSimpleName();
        StringBuilder query = new StringBuilder("UPDATE ");
        query.append(tableName);
        query.append(" SET ");
        StringJoiner columnNames = getColumnNamesWithValues(value);
        query.append(columnNames);
        query.append(" WHERE ");
        query.append(getIdColumn(value));
        query.append(";");
        return query.toString();
    }

    private StringJoiner getColumnNames(Class<?> clazz) {
        StringJoiner columnNames = new StringJoiner(", ");
        for(Field declaredField : clazz.getDeclaredFields()) {
            Column columnAnnotation = declaredField.getAnnotation(Column.class);
            if(columnAnnotation != null) {
                String columnName = columnAnnotation.name().isEmpty()? declaredField.getName(): columnAnnotation.name();
                columnNames.add(columnName);
            }
        }
        return columnNames;
    }

    private String getIdColumn(Object o) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        StringBuilder stringBuilder = new StringBuilder();
        for(Field declaredField : o.getClass().getDeclaredFields()) {
            Id columnAnnotation = declaredField.getAnnotation(Id.class);
            if(columnAnnotation != null) {
                String columnName = columnAnnotation.name().isEmpty()? declaredField.getName(): columnAnnotation.name();
                stringBuilder.append(columnName);
                stringBuilder.append("=");
                PropertyDescriptor pd = new PropertyDescriptor(declaredField.getName(), o.getClass());
                Method fieldGetter = pd.getReadMethod();
                String f = fieldGetter.invoke(o).toString();
                stringBuilder.append(f);
            }
        }
        return stringBuilder.toString();
    }

    private String getObjectValues(Object o) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        StringJoiner stringJoiner = new StringJoiner(", ");
        for(Field declaredField : o.getClass().getDeclaredFields()) {
            Column columnAnnotation = declaredField.getAnnotation(Column.class);
            if(columnAnnotation != null) {
                PropertyDescriptor pd = new PropertyDescriptor(declaredField.getName(), o.getClass());
                Method fieldGetter = pd.getReadMethod();
                String f = fieldGetter.invoke(o).toString();
                stringJoiner.add(f);
            }
        }
        return stringJoiner.toString();
    }

    private StringJoiner getColumnNamesWithValues(Object o) throws InvocationTargetException, IllegalAccessException, IntrospectionException {
        StringJoiner stringJoiner = new StringJoiner(", ");
        for(Field declaredField : o.getClass().getDeclaredFields()) {
            Column columnAnnotation = declaredField.getAnnotation(Column.class);
            if(columnAnnotation != null) {
                String columnName = columnAnnotation.name().isEmpty()? declaredField.getName(): columnAnnotation.name();
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(columnName);
                stringBuilder.append("=");
                PropertyDescriptor pd = new PropertyDescriptor(declaredField.getName(), o.getClass());
                Method fieldGetter = pd.getReadMethod();
                String f = fieldGetter.invoke(o).toString();
                stringBuilder.append(f);
                stringJoiner.add(stringBuilder);
            }
        }
        return stringJoiner;
    }

    private String getTableName(Class<?> clazz) {
        Table tableAnnotation = clazz.getAnnotation(Table.class);
        if(tableAnnotation == null) {
            throw new IllegalArgumentException("Class is not ORM entity");
        }
        return tableAnnotation.name().isEmpty()? clazz.getSimpleName(): tableAnnotation.name();
    }
}
