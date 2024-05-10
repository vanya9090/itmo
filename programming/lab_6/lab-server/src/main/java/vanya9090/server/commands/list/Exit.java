package vanya9090.server.commands.list;

import vanya9090.common.commands.Command;
import vanya9090.common.commands.CommandArgument;
import vanya9090.common.commands.CommandManager;

import java.util.Map;

/**
 * выход из программы
 *
 * @author vanya9090
 */
public class Exit extends Command {

    public Exit() {
        super("exit", "завершить программу", new CommandArgument[]{});
    }

    /**
     * выполняет команду
     *
     * @param args аргументы, переданные в командной строке
     * @return пустая строка
     */
    @Override
    public Object[] apply(Map<String, Object> args) throws Exception {
        Command save = CommandManager.getCommands().get("save");
        save.apply(args);
        return new String[]{};
    }
}
