package vanya9090.server.commands.list;

import vanya9090.common.commands.Command;
import vanya9090.common.commands.CommandArgument;
import vanya9090.common.exceptions.NotFoundException;
import vanya9090.common.exceptions.WrongAmountOfElementsException;

import java.util.Map;

/**
 * команда для запуска скрипта
 *
 * @author vanya9090
 */
public class ExecuteScript extends Command {
    public ExecuteScript() {
        super("execute_script", "считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.",
                new CommandArgument[]{new CommandArgument("filename", String.class)});
    }

    /**
     * запуск скрипта
     *
     * @param arg аргументы, переданные в командной строке
     * @return пустая строка
     * @throws Exception ошибки
     */
    @Override
    public Object[] apply(Map<String, Object> arg) throws Exception {
//        try {
//            this.runner.executeScript(arg[1]);
//        } catch (WrongAmountOfElementsException | ArrayIndexOutOfBoundsException | NotFoundException e) {
//            throw new WrongAmountOfElementsException("пустой аргумент, введите название файла");
//        }
        return new Object[]{""};
    }
}
