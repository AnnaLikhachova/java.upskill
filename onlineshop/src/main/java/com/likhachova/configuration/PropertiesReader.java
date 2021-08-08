package com.likhachova.configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class PropertiesReader {
    private final String path;
    private Properties properties;

    public PropertiesReader(String path) {
        this.path = path;
    }

    public Properties getProperties() {
        readProperties();
        return properties;
    }

    private void readProperties() {
        Properties propertiesFromProjectClassPath = readPropertiesFromProjectClassPath();
        if(checkProperties(propertiesFromProjectClassPath)) {
            this.properties = propertiesFromProjectClassPath;
        }
        else {
            Properties propertiesFromFile = readPropertiesFromFile();
            if(checkProperties(propertiesFromFile)) {
                this.properties = propertiesFromFile;
            }
            Properties propertiesSystem = readSystemProperties();
            if(checkProperties(propertiesSystem)) {
                this.properties = propertiesSystem;
            }
        }
    }

    private boolean checkProperties(Properties properties) {
        if(properties != null) {
            return properties.getProperty("driver") != null && properties.getProperty("url") != null && properties.getProperty("username") != null && properties.getProperty("password") != null;
        }
        return false;
    }

    public Properties readPropertiesFromProjectClassPath() {
        Properties prop = new Properties();
        try(InputStream input = PropertiesReader.class.getClassLoader().getResourceAsStream(path)) {
            prop.load(input);
        }
        catch(IOException ex) {
            throw new RuntimeException("Can not read properties from project classpath", ex);
        }
        return prop;
    }

    public Properties readPropertiesFromFile() {
        File currentDirFile = new File("");
        Properties prop = new Properties();
        try {
            prop.loadFromXML(new FileInputStream(currentDirFile.getAbsolutePath() + "/" + path));
        }
        catch(IOException ex) {
            throw new RuntimeException("Can not read properties from file", ex);
        }
        return prop;
    }

    public Properties readSystemProperties() {
        return System.getProperties();
    }
}
