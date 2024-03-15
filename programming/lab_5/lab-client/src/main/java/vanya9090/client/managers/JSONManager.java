package vanya9090.client.managers;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import vanya9090.common.exceptions.*;
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

    public Collection<HumanBeing> readFile(String ENV_KEY) throws ValidateException, JsonSyntaxException, EmptyFileException, NotFoundException, AccessException {
        String path = System.getenv(ENV_KEY);
        if (path != null && !path.isEmpty()) {
            if (new File(path).canRead()) {
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
                    for (HumanBeing humanBeing : collection) {
                        if (!humanBeing.validate()) {
                            throw new ValidateException("Некоторые поля не соответствуют описанию");
                        }
                    }
                    return collection;

                } catch (NoSuchElementException exception) {
                    throw new EmptyFileException("Файл " + path + " пуст");
                } catch (FileNotFoundException exception) {
                    throw new NotFoundException("файл не найден");
                }
            } else {
                throw new AccessException("нет прав доступа для чтения файла");
            }
        } else {
            throw new NotFoundException("переменная окружения не найдена");
        }
//        return new ArrayDeque<>();
    }

    public boolean writeFile(Collection<HumanBeing> collection, String path) throws NotFoundException, AccessException {
        if (new File(path).canWrite()) {
            try (PrintWriter collectionPrintWriter = new PrintWriter(path)) {
                collectionPrintWriter.println(gson.toJson(collection));
                return true;
            } catch (IOException exception) {
                throw new NotFoundException("загрузочный файл не может быть открыт");
            }
        } else {
            throw new AccessException("нет прав доступа для записи в файл");
        }
    }
}
