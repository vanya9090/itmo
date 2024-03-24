package vanya9090.server.commands.list;

import vanya9090.client.utils.Runner;
import vanya9090.common.exceptions.NotFoundException;
import vanya9090.common.exceptions.WrongAmountOfElementsException;
import vanya9090.server.commands.Command;

/**
 * команда для запуска скрипта
 *
 * @author vanya9090
 */
public class ExecuteScript extends Command {
    private final Runner runner;

    public ExecuteScript(Runner runner) {
        super("execute_script", "считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.");
        this.runner = runner;
    }

    /**
     * запуск скрипта
     *
     * @param args аргументы, переданные в командной строке
     * @return пустая строка
     * @throws Exception ошибки
     */
    @Override
    public String apply(String[] args) throws Exception {
        try {
            if (args[1].isEmpty()) throw new WrongAmountOfElementsException("пустой аргумент, введите название файла");
            this.runner.executeScript(args[1]);
        } catch (WrongAmountOfElementsException | ArrayIndexOutOfBoundsException | NotFoundException e) {
            throw new WrongAmountOfElementsException("пустой аргумент, введите название файла");
        }
        return "";
    }
}
