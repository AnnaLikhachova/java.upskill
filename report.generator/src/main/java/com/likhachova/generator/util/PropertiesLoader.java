package com.likhachova.generator.util;

public class PropertiesLoader extends Settings {

    public Settings load() {
        Settings settings = new Settings();
        settings.setUserName(System.getProperty("username"));
        settings.setPassword(System.getProperty("password"));
        settings.setUrl(System.getProperty("url"));
        settings.setReportPath(System.getProperty("reportpath"));
        return new Settings();
    }

}
