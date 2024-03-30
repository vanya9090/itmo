package vanya9090.server.commands.list;

import vanya9090.common.commands.Command;
import vanya9090.common.commands.CommandArgument;
import vanya9090.common.commands.CommandManager;

/**
 * выход из программы
 *
 * @author vanya9090
 */
public class Exit extends Command {

    public Exit() {
        super("exit", "завершить программу (без сохранения в файл)");
    }

    /**
     * выполняет команду
     *
     * @param args аргументы, переданные в командной строке
     * @return пустая строка
     */
    @Override
    public Object[] apply(CommandArgument args) throws Exception {
        CommandManager.getCommands().get("save").apply(new CommandArgument().withStringArg(new String[]{""}));
        System.exit(0);
        return new String[]{""};
    }
}
