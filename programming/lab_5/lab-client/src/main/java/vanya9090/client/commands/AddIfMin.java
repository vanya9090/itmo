package vanya9090.client.commands;


import vanya9090.client.utils.ExecuteLogger;
import vanya9090.common.exceptions.BooleanFormatException;
import vanya9090.common.exceptions.EmptyFieldException;
import vanya9090.common.exceptions.ParseException;
import vanya9090.client.managers.CollectionManager;
import vanya9090.client.models.HumanBeing;
import vanya9090.client.models.forms.HumanBeingForm;
import vanya9090.client.models.Coordinates;
import vanya9090.client.utils.ILogger;

import java.util.Scanner;

public class AddIfMin extends Command implements Executable{
    private final ILogger logger;
    private final CollectionManager collectionManager;

    public AddIfMin(ILogger logger, CollectionManager collectionManager) {
        super("add_if_min", "добавить новый элемент в коллекцию, если его расстояние от начала координат меньше, чем у наименьшего элемента этой коллекции");
        this.logger = logger;
        this.collectionManager = collectionManager;
    }

    @Override
    public String apply(String[] args) throws BooleanFormatException, ParseException, EmptyFieldException {
        HumanBeing.updateNextId(collectionManager);
        HumanBeingForm humanBeingForm = new HumanBeingForm(this.logger, new Scanner(System.in), false);
        HumanBeing humanBeing = humanBeingForm.create();
        if (humanBeing.getCoordinates().getDistance() < this.getMin()) {
            collectionManager.add(humanBeing);
            return "added\n";
        } else {
            return "not added\n";
        }
    }
    @Override
    public String apply(String[] args, Scanner fileReader) throws BooleanFormatException, ParseException, EmptyFieldException {
        HumanBeing.updateNextId(collectionManager);
        HumanBeingForm humanBeingForm = new HumanBeingForm(new ExecuteLogger(), fileReader, true);
        HumanBeing humanBeing = humanBeingForm.create();
        if (humanBeing.getCoordinates().getDistance() < this.getMin()) {
            collectionManager.add(humanBeing);
            return "added\n";
        } else {
            return "not added\n";
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
