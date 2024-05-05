package vanya9090.server.managers;


import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import vanya9090.common.models.HumanBeing;
import vanya9090.server.utils.LocalDateTypeAdapter;
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
 * @author vanya9090
 */
public class JSONManager implements FileManager {
    private final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .serializeNulls()
            .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
            .create();

    public JsonArray readFile(String ENV_KEY) throws EmptyFileException, NotFoundException, FormatException, FileNotFoundException, AccessException {
        String path = System.getenv(ENV_KEY);
        if (path == null || path.isEmpty()) throw new NotFoundException("переменная окружения не найдена");
        if (!new File(path).exists()) throw new FileNotFoundException();
        if (!new File(path).canRead()) throw new AccessException("нет прав доступа для чтения файла");
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
            return jsonMap.getAsJsonArray();
        } catch (NoSuchElementException exception) {
            throw new EmptyFileException("файл " + path + " пуст");
        } catch (FileNotFoundException exception) {
            throw new NotFoundException("файл не найден");
        } catch (JsonParseException exception) {
            throw new FormatException("неправильная разметка файла");
        }
    }


    public void writeFile(Collection<HumanBeing> collection, String ENV_KEY) throws NotFoundException, AccessException {
        String path = System.getenv(ENV_KEY);
        if (new File(path).canWrite()) {
            try (PrintWriter collectionPrintWriter = new PrintWriter(path)) {
                collectionPrintWriter.println(gson.toJson(collection));
            } catch (IOException exception) {
                throw new NotFoundException("загрузочный файл не может быть открыт");
            }
        } else {
            throw new AccessException("нет прав доступа для записи в файл");
        }
    }
}
