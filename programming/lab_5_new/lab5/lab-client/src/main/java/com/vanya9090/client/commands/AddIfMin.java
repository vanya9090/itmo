package com.vanya9090.client.commands;

import com.vanya9090.client.exceptions.EmptyFieldException;
import com.vanya9090.client.exceptions.ParseException;
import com.vanya9090.client.exceptions.WrongFieldsException;
import com.vanya9090.client.managers.CollectionManager;
import com.vanya9090.client.models.Coordinates;
import com.vanya9090.client.models.HumanBeing;
import com.vanya9090.client.models.forms.HumanBeingForm;
import com.vanya9090.client.utils.ILogger;
import com.vanya9090.client.utils.Logger;

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
        try {
            HumanBeing.updateNextId(collectionManager);
            logger.info("добавьте нового человека:");
            HumanBeingForm humanBeingForm = new HumanBeingForm(this.logger, new Scanner(System.in), false);
            HumanBeing humanBeing = humanBeingForm.create();
            if (humanBeing.getCoordinates().getDistance() < this.getMin()) {
                collectionManager.add(humanBeing);
                logger.info("добавлено успешно");
            } else {
                logger.warning("расстояние от начала координат не меньше, чем у наименьшего элемента этой коллекции");
            }
        }  catch (ParseException | EmptyFieldException | WrongFieldsException e) {
            logger.error(e);
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
