package vanya9090.server.commands.list;


import vanya9090.common.commands.CommandArgument;
import vanya9090.server.managers.CollectionManager;
import vanya9090.common.commands.Command;

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
    public Object[] apply(CommandArgument args) {
        StringBuilder stringBuilder = new StringBuilder();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        stringBuilder.append("Тип: ").
                append(this.collectionManager.getType()).
                append("\nДата инициализации: ").
                append(dtf.format(this.collectionManager.getInitDate())).
                append("\nКоличество элементов: ").
                append(this.collectionManager.getSize())
                .append("\n");
        return new String[]{stringBuilder.toString()};
    }
}
