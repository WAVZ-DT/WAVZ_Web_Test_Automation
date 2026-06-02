package Utils;

import lombok.SneakyThrows;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class JsonReader {
    public static class TestJson {
        @SneakyThrows
        public static String getJson(String filePath, String key) {
            JSONObject jsonObject = null;
            try {
                jsonObject = (JSONObject) new JSONParser().parse(new FileReader(filePath));
            } catch (IOException | ParseException e) {
                throw new RuntimeException(e);
            }
            return (String) jsonObject.get(key);
        }
    }
}
