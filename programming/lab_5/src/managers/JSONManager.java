package managers;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import models.HumanBeing;
import utils.LocalDateTypeAdapter;
import utils.Logger;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class JSONManager implements FileManager{
    private final Logger logger;

    public JSONManager(Logger logger) {
        this.logger = logger;
    }
    private final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .serializeNulls()
            .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
            .create();

    public Collection<HumanBeing> readFile(String name){
        if (name != null && !name.isEmpty()) {
            try (Scanner fileReader = new Scanner(new File(name))) {
                var collectionType = new TypeToken<ArrayDeque<HumanBeing>>() {}.getType();
                var jsonString = new StringBuilder();
                String line;
                while(fileReader.hasNext()) {
                    line = fileReader.nextLine().trim();
                    if (!line.isEmpty()) {
                        jsonString.append(line);
                    }
                }
                if (jsonString.isEmpty()) {
                    jsonString = new StringBuilder("[]");
                }
                ArrayDeque<HumanBeing> collection = gson.fromJson(jsonString.toString(), collectionType);

                logger.info("Коллекция успешна загружена!");
                return collection;

            } catch (NoSuchElementException exception) {
                logger.error("Загрузочный файл пуст!");
            } catch (IllegalStateException | FileNotFoundException exception) {
                logger.error("Непредвиденная ошибка!");
                System.exit(0);
            }
        } else {
            logger.error("Аргумент командной строки с загрузочным файлом не найден!");
        }
        return new ArrayDeque<>();
    }

    public void writeFile(Collection<HumanBeing> collection, String name){
        try (PrintWriter collectionPrintWriter = new PrintWriter(new File(name))) {
            collectionPrintWriter.println(gson.toJson(collection));
            System.out.println("Коллекция успешна сохранена в файл!");
        } catch (IOException exception) {
            System.out.println("Загрузочный файл не может быть открыт!");
        }
    }
}
