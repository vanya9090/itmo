package vanya9090.client.managers;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import vanya9090.client.models.HumanBeing;
import vanya9090.client.utils.LocalDateTypeAdapter;
import vanya9090.common.exceptions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * реализация файлового менеджера
 *
 * @author vanya9090
 */
public class JSONManager implements FileManager {
    private final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .serializeNulls()
            .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
            .create();

    /**
     * @param ENV_KEY переменная окружения
     * @return коллекция из HumanBeing
     * @throws ValidateException  синтетическое описание
     * @throws EmptyFileException пустое поле
     * @throws NotFoundException  файл/переменная окржения не найдены
     * @throws AccessException    ошибка доступа
     * @throws FormatException    неправильная разметка файла
     */
    public Collection<HumanBeing> readFile(String ENV_KEY) throws ValidateException, EmptyFileException, NotFoundException, AccessException, FormatException {
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
                            throw new ValidateException("некоторые поля не соответствуют синтетическому описанию");
                        }
                    }
                    return collection;

                } catch (NoSuchElementException e) {
                    throw new EmptyFileException("файл " + path + " пуст");
                } catch (FileNotFoundException e) {
                    throw new NotFoundException("файл не найден");
                } catch (JsonSyntaxException e) {
                    throw new FormatException("неправильная разметка файла");
                }
            } else {
                throw new AccessException("нет прав доступа для чтения файла");
            }
        } else {
            throw new NotFoundException("переменная окружения не найдена");
        }
    }

    /**
     * @param collection коллекция из HumanBeing
     * @param path       путь
     * @return true/false
     * @throws NotFoundException файл не может быть открыт
     * @throws AccessException   нет прав для записи
     */
    public boolean writeFile(Collection<HumanBeing> collection, String ENV_KEY) throws NotFoundException, AccessException {
        String path = System.getenv(ENV_KEY);
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
