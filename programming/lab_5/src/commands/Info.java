package commands;

import managers.CollectionManager;
import utils.Logger;

import java.time.format.DateTimeFormatter;

public class Info extends Command{
    private final Logger logger;
    private final CollectionManager collectionManager;

    public Info(Logger logger, CollectionManager collectionManager) {
        super("info", "вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)");
        this.logger = logger;
        this.collectionManager = collectionManager;
    }

    @Override
    public void apply(String[] args) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        this.logger.info("Тип: " + this.collectionManager.getType());
        this.logger.info( "Дата инициализации: " + dtf.format(this.collectionManager.getInitDate()));
        this.logger.info("Количество элементов: " + this.collectionManager.getSize());
    }
}
