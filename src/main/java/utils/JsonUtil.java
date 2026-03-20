package utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class JsonUtil {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T> T readJsonFileToModel(String filePath, Class<T> clazz) {
        try {
            return mapper.readValue(new File(filePath), clazz);
        } catch (IOException e) {
            LoggerUtil.error("Error reading JSON file to model: " + filePath, e);
            throw new RuntimeException("Failed to read JSON", e);
        }
    }

    public static List<Map<String, Object>> readJsonArray(String filePath) {
        try {
            return mapper.readValue(new File(filePath), new TypeReference<List<Map<String, Object>>>() {});
        } catch (IOException e) {
            LoggerUtil.error("Error reading JSON Array from file: " + filePath, e);
            throw new RuntimeException("Failed to read JSON Array", e);
        }
    }
}
