package vanya9090.client.commands;


import vanya9090.client.exceptions.BooleanFormatException;
import vanya9090.client.exceptions.EmptyFieldException;
import vanya9090.client.exceptions.ParseException;
import vanya9090.client.managers.CollectionManager;
import vanya9090.client.models.HumanBeing;
import vanya9090.client.models.forms.HumanBeingForm;
import vanya9090.client.models.Coordinates;
import vanya9090.client.utils.ILogger;

import java.util.Scanner;

public class AddIfMin extends Command {
    private final ILogger logger;
    private final CollectionManager collectionManager;

    public AddIfMin(ILogger logger, CollectionManager collectionManager) {
        super("add_if_min", "добавить новый элемент в коллекцию, если его расстояние от начала координат меньше, чем у наименьшего элемента этой коллекции");
        this.logger = logger;
        this.collectionManager = collectionManager;
    }

    @Override
    public void apply(String[] args) {
        HumanBeing.updateNextId(collectionManager);
        logger.info("добавьте нового человека:");
        HumanBeingForm humanBeingForm = new HumanBeingForm(this.logger, new Scanner(System.in), false);
        try {
            HumanBeing humanBeing = humanBeingForm.create();
            if (humanBeing.getCoordinates().getDistance() < this.getMin()) {
                collectionManager.add(humanBeing);
                logger.info("добавлено успешно");
            } else {
                logger.warning("расстояние от начала координат не меньше, чем у наименьшего элемента этой коллекции");
            }
        } catch (BooleanFormatException | ParseException | EmptyFieldException e) {
            logger.error("непредвиденная ошибка");
        }
    }

    private Double getMin() {
        return this.collectionManager.getCollection().stream()
                .map(HumanBeing::getCoordinates)
                .map(Coordinates::getDistance)
                .mapToDouble(Double::doubleValue)
                .min()
                .orElse(Double.MAX_VALUE);
    }
}
