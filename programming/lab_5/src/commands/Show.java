package commands;

import managers.CollectionManager;
import models.HumanBeing;
import utils.Logger;

public class Show extends Command{
    private final CollectionManager collectionManager;
    private final Logger logger;

    public Show(Logger logger, CollectionManager collectionManager){
        super("show", "вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
        this.collectionManager = collectionManager;
        this.logger = logger;
    }
    @Override
    public void apply(String[] args) {
        for (HumanBeing human: collectionManager.getCollection()){
            logger.info(human);
            logger.info("");
        }
    }
}
