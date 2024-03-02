package com.vanya9090.client.managers;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
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

    public Collection<HumanBeing> readFile(String envKey) {
        String path = System.getenv(envKey);
        if (path == null || path.isEmpty()) {
            logger.error("переменная окружения не найдена");
            return new ArrayDeque<>();
        }
        if (!(new File(path).isFile())){
            logger.error("файл не найден");
            return new ArrayDeque<>();
        }
        if (!(new File(path).canRead())) {
            logger.error("нет прав чтения файла");
            return new ArrayDeque<>();
        }
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
            ArrayDeque<HumanBeing> collection = gson.fromJson(jsonString.toString(), collectionType);
            logger.info("коллекция успешна загружена");
            return collection;
        } catch (NoSuchElementException exception) {
            logger.error("файл пуст");
        } catch (IllegalStateException | FileNotFoundException exception) {
            logger.error("непредвиденная ошибка");
            System.exit(0);
        }
        return new ArrayDeque<>();
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
}
