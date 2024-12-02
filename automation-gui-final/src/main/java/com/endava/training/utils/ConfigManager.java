package com.endava.training.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
    private static Properties properties = new Properties();

    static {
        loadProperties();
    }

    private static void loadProperties() {
        try{
            InputStream inputStream = ConfigManager.class.getClassLoader().getResourceAsStream("test-config.properties");
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("Unable to load configuration properties", e);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }
}
