package com.endava.training.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class DataReader {
    private Map<String, Object> testData;

    public DataReader(String jsonFilePath) {
        parseJsonData(jsonFilePath);
    }

    private void parseJsonData(String jsonFilePath) {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(jsonFilePath)) {
            ObjectMapper objectMapper = new ObjectMapper();
            testData = objectMapper.readValue(inputStream, new TypeReference<Map<String, Object>>(){});
        } catch (IOException e) {
            throw new RuntimeException("Could not read the JSON file: " + jsonFilePath, e);
        } catch (Exception e){
            throw new RuntimeException("Unexpected error found: " + jsonFilePath, e);
        }
    }

    public <T> T  getData(String key, Class<T> type) {
        Object data = testData.get(key);
        if (data == null) {
            return null;
        }
        return new ObjectMapper().convertValue(data, type);
    }

    public Map<String, Object> getData() {
        return testData;
    }
}
