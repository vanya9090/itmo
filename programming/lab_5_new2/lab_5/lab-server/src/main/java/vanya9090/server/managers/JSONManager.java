package vanya9090.server.managers;

import com.google.gson.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class JSONManager implements FileManager{
    @Override
    public void write() {

    }

    @Override
    public JsonArray read() {
        String path = "data/file.json";
        try (Scanner fileReader = new Scanner(new File(path))) {
            StringBuilder jsonString = new StringBuilder();
            String line;
            while (fileReader.hasNext()) {
                line = fileReader.nextLine().trim();
                if (!line.isEmpty()) {
                    jsonString.append(line);
                }
            }
            if (jsonString.isEmpty()) {
                jsonString = new StringBuilder("[]");
            }
            JsonElement jsonMap = JsonParser.parseString(String.valueOf(jsonString));
            System.out.println(jsonMap);
            System.out.println(jsonMap.getAsJsonArray().get(0).getAsJsonObject().get("id"));
            System.out.println(jsonMap.getAsJsonArray());
            return jsonMap.getAsJsonArray();
        } catch (FileNotFoundException e) {
            return null;
        }
    }
}
