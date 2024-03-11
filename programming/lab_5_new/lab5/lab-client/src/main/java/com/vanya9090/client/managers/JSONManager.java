package com.vanya9090.client.managers;


import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.vanya9090.client.exceptions.ReadException;
import com.vanya9090.client.exceptions.WrongFieldsException;
import com.vanya9090.client.exceptions.WrongPathException;
import com.vanya9090.client.models.HumanBeing;
import com.vanya9090.client.utils.LocalDateTypeAdapter;
import com.vanya9090.client.utils.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class JSONManager implements FileManager {
    private final Logger logger;

    private final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .serializeNulls()
            .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
            .create();

    public JSONManager(Logger logger) {
        this.logger = logger;
    }

    public JsonArray readFile(String envKey) throws ReadException, WrongPathException {
        String path = System.getenv(envKey);
        if (path == null || path.isEmpty() || !(new File(path).isFile())) throw new WrongPathException();
        if (!(new File(path).canRead())) throw new ReadException();
        try (Scanner fileReader = new Scanner(new File(path))) {
            var collectionType = new TypeToken<ArrayDeque<HumanBeing>>() {
            }.getType();
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
            return jsonMap.getAsJsonArray();
        } catch (NoSuchElementException exception) {
            logger.error("файл пуст");
        } catch (FileNotFoundException exception) {
            logger.error("непредвиденная ошибка");
            System.exit(0);
        } catch (JsonParseException exception) {
            System.out.println("В загрузочном файле не обнаружена необходимая коллекция!");
        }
            return new JsonArray();
    }

    public void writeFile(Collection<HumanBeing> collection, String envKey) {
        String path = System.getenv(envKey);
        if (!(new File(path).isFile())){
            logger.error("файл не найден");
        } else if (!(new File(path).canWrite())) {
            logger.error("нет прав записи в файл");
        } else {
            try (PrintWriter collectionPrintWriter = new PrintWriter(path)) {
                collectionPrintWriter.println(gson.toJson(collection));
                System.out.println("коллекция успешна сохранена в файл");
            } catch (IOException exception) {
                System.out.println("загрузочный файл не может быть открыт");
            }
        }
    }

//    public boolean validate(Class cls, String jsonString){
//        JsonParser parser = new JsonParser();
//
//        JsonArray jsonArr = parser.parse(jsonString).getAsJsonArray();
//        for (JsonElement jsonElement: jsonArr) {
//            jsonElement = jsonElement.getAsJsonObject();
//            System.out.println(jsonElement);
//        }
//        return true;
//        if (jsonObj.entrySet().size() > cls.getDeclaredFields().length) { // if the json-string has more entries than class has fields, we're already out
//            return false;
//        }
//        for(Field field : cls.getDeclaredFields()) {
//            if (!jsonObj.has(field.getName())) { // if the json-string has a key that doesn't have a corresponding field in the class, we're out
//                return false;
//            }
//    }
}
