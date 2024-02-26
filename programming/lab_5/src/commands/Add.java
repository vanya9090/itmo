package commands;

import managers.CollectionManager;
import models.HumanBeing;
import models.forms.HumanBeingForm;
import utils.Logger;

public class Add extends Command{
    private final Logger logger;
    private final CollectionManager collectionManager;

    public Add(Logger logger, CollectionManager collectionManager){
        super("add", "добавить новый элемент в коллекцию");
        this.logger = logger;
        this.collectionManager = collectionManager;
    }
    @Override
    public void apply(String[] args) {
        HumanBeing.updateNextId(collectionManager);
        HumanBeingForm humanBeingForm = new HumanBeingForm(this.logger);
        HumanBeing humanBeing = humanBeingForm.create();
        collectionManager.add(humanBeing);
        logger.success("Добавлено успешно");
    }
}
