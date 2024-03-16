package vanya9090.client.commands;


import vanya9090.client.managers.CollectionManager;

import java.time.format.DateTimeFormatter;

/**
 * информация о коллекции
 *
 * @author vanya9090
 */
public class Info extends Command {
    private final CollectionManager collectionManager;

    public Info(CollectionManager collectionManager) {
        super("info", "вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)");
        this.collectionManager = collectionManager;
    }

    @Override
    public String apply(String[] args) {
        StringBuilder stringBuilder = new StringBuilder();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        stringBuilder.append("Тип: ").
                append(this.collectionManager.getType()).
                append("\nДата инициализации: ").
                append(dtf.format(this.collectionManager.getInitDate())).
                append("\nКоличество элементов: ").
                append(this.collectionManager.getSize())
                .append("\n");
        return stringBuilder.toString();
    }
}
