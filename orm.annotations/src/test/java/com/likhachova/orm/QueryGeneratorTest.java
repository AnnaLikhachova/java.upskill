package com.likhachova.orm;

import com.likhachova.orm.entity.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QueryGeneratorTest {

    private Person person;

    @BeforeEach
    public void testListInit() {
        person = new Person(2, "name", 1500.00);
    }

    @Test
    @DisplayName("Generate SELECT ALL Query")
    public void testGenerateSelectAll() {
        String executeQuery = "SELECT id, person_name, person_salary FROM Person;";
        QueryGenerator queryGenerator = new DefaultQueryGenerator();
        String selectAllQuery = queryGenerator.findAll(Person.class);
        assertEquals(executeQuery, selectAllQuery);

    }

    @Test
    @DisplayName("Generate SELECT BY Id Query")
    public void testGenerateSelectById() {
        String executeQuery = "SELECT id, person_name, person_salary FROM Person WHERE id=" + person.getId() + ";";
        QueryGenerator queryGenerator = new DefaultQueryGenerator();
        String selectAllQuery = queryGenerator.findById(Person.class, person.getId());
        assertEquals(executeQuery, selectAllQuery);

    }

    @Test
    @DisplayName("Generate DELETE BY Id Query")
    public void testGenerateDeleteById() {
        String executeQuery = "DELETE FROM Person WHERE id=" + person.getId() + ";";
        QueryGenerator queryGenerator = new DefaultQueryGenerator();
        String selectAllQuery = queryGenerator.deleteById(Person.class, person.getId());
        assertEquals(executeQuery, selectAllQuery);

    }

    @Test
    @DisplayName("Generate INSERT Query")
    public void testInsert() throws IllegalAccessException, IntrospectionException, InvocationTargetException {
        String executeQuery = "INSERT INTO Person (id, person_name, person_salary) VALUES (" + person.getId() + ", " + person.getName() + ", " + person.getSalary() + ");";
        QueryGenerator queryGenerator = new DefaultQueryGenerator();
        String selectAllQuery = queryGenerator.insert(person);
        assertEquals(executeQuery, selectAllQuery);

    }

    @Test
    @DisplayName("Generate UPDATE Query")
    public void testUpdate() throws IllegalAccessException, InvocationTargetException, IntrospectionException {
        String executeQuery = "UPDATE Person SET id=" + person.getId() + ", person_name=" + person.getName() + ", person_salary=" + person.getSalary() + " WHERE id=" + person.getId() + ";";
        QueryGenerator queryGenerator = new DefaultQueryGenerator();
        String selectAllQuery = queryGenerator.update(person);
        assertEquals(executeQuery, selectAllQuery);
    }
}
