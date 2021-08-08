package com.likhachova.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    private Properties properties;

    public ConnectionFactory(Properties properties) {
        this.properties = properties;
    }

    public Connection getConnection() {
        Connection conn;
        try {
            Class.forName(properties.getProperty("driver"));
        }
        catch(ClassNotFoundException e) {
            throw new RuntimeException("Cannot find the driver in the classpath!", e);
        }

        try {
            conn = DriverManager.getConnection(
                    properties.getProperty("url"), properties.getProperty("username"), properties.getProperty("password"));
        }
        catch(SQLException e) {
            throw new RuntimeException("Cannot open sql connection", e);
        }
        return conn;
    }
}
