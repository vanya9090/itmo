package vanya9090.client.commands;

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
    public String apply(String[] args) {
        System.exit(0);
        return "";
    }
}
