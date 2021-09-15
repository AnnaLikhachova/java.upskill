package com.likhachova;

import com.likhachova.configuration.PropertiesReader;
import org.junit.jupiter.api.Test;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

public class PropertiesReaderTest {

    @Test
    public void testreadPropertiesFromProjectClassPath() {
        String pathToProperties ="application.properties";
        PropertiesReader propertiesReader = new PropertiesReader(pathToProperties);
        Properties properties = propertiesReader.readPropertiesFromProjectClassPath();
        assertEquals("products",properties.getProperty("username"));
        assertEquals("password",properties.getProperty("password"));
        assertEquals("jdbc:mysql://localhost:3306/products?",properties.getProperty("url"));
        assertEquals("com.mysql.jdbc.Driver",properties.getProperty("driver"));

    }

    @Test
    public void testreadPropertiesFromFile() {
        String pathToProperties ="properties.xml";
        PropertiesReader propertiesReader = new PropertiesReader(pathToProperties);
        Properties properties = propertiesReader.readPropertiesFromFile();
        assertEquals("products",properties.getProperty("username"));
        assertEquals("password",properties.getProperty("password"));
        assertEquals("jdbc:mysql://localhost:3306/products?",properties.getProperty("url"));
        assertEquals("com.mysql.jdbc.Driver",properties.getProperty("driver"));

    }

    @Test
    public void testeadSystemProperties() {
        System.setProperty("username","products");
        System.setProperty("password","password");
        System.setProperty("url","jdbc:mysql://localhost:3306/products?");
        System.setProperty("driver","com.mysql.jdbc.Driver");
        String pathToProperties ="";
        PropertiesReader propertiesReader = new PropertiesReader(pathToProperties);
        Properties properties = propertiesReader.readSystemProperties();
        assertEquals("products",properties.getProperty("username"));
        assertEquals("password",properties.getProperty("password"));
        assertEquals("jdbc:mysql://localhost:3306/products?",properties.getProperty("url"));
        assertEquals("com.mysql.jdbc.Driver",properties.getProperty("driver"));
    }
}
