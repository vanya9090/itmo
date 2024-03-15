package vanya9090.client.managers;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import vanya9090.client.exceptions.ValidateException;
import vanya9090.client.models.HumanBeing;
import vanya9090.client.utils.ILogger;
import vanya9090.client.utils.LocalDateTypeAdapter;

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
    private final ILogger logger;
    private final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .serializeNulls()
            .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
            .create();

    public JSONManager(ILogger logger) {
        this.logger = logger;
    }

    public Collection<HumanBeing> readFile(String ENV_KEY) {
        String path = System.getenv(ENV_KEY);
        if (path != null && !path.isEmpty()) {
            if (new File(path).canRead()) {
                try (Scanner fileReader = new Scanner(new File(path))) {
                    var collectionType = new TypeToken<ArrayDeque<HumanBeing>>() {
                    }.getType();
                    var jsonString = new StringBuilder();
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

                    try {
                        ArrayDeque<HumanBeing> collection = gson.fromJson(jsonString.toString(), collectionType);

                        for (HumanBeing humanBeing : collection) {
                            if (!humanBeing.validate()) {
                                throw new ValidateException();
                            }
                        }

                        logger.info("коллекция успешна загружена");
                        return collection;
                    } catch (JsonSyntaxException e) {
                        logger.error("json файл содержит синтаксические ошибки");
                    } catch (ValidateException e) {
                        logger.error("файл содержит неправильные поля");
                    }

                } catch (NoSuchElementException exception) {
                    logger.error("файл пуст");
                } catch (IllegalStateException | FileNotFoundException exception) {
                    logger.error("непредвиденная ошибка");
                    System.exit(0);
                }
            } else {
                logger.error("нет прав чтения файла");
            }
        } else {
            logger.error("переменная окружения не найдена");
        }
        return new ArrayDeque<>();
    }

    public void writeFile(Collection<HumanBeing> collection, String path) {
        if (new File(path).canWrite()) {
            try (PrintWriter collectionPrintWriter = new PrintWriter(path)) {
                collectionPrintWriter.println(gson.toJson(collection));
                System.out.println("коллекция успешна сохранена в файл");
            } catch (IOException exception) {
                System.out.println("загрузочный файл не может быть открыт");
            }
        } else {
            logger.error("нет прав записи в файл");
        }
    }
}
