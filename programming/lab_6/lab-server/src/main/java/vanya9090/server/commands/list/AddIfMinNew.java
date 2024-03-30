package vanya9090.server.commands.list;

import vanya9090.common.commands.Command;
import vanya9090.common.commands.CommandArgument;
import vanya9090.common.commands.Formable;
import vanya9090.common.models.*;
import vanya9090.server.managers.CollectionManager;


public class AddIfMinNew extends Command implements Formable {
    private final CollectionManager collectionManager;
    public AddIfMinNew(CollectionManager collectionManager) {
        super("add_if_min", "добавить новый элемент в коллекцию, если его расстояние от начала координат меньше, чем у наименьшего элемента этой коллекции");
        this.collectionManager = collectionManager;
    }

    @Override
    public Object[] apply(CommandArgument arg) throws Exception {
        HumanBeing humanBeing = arg.getModelArg();
        if (humanBeing.getCoordinates().getDistance() < this.getMin()) {
            humanBeing.setId(collectionManager.getNextId());
            collectionManager.add(humanBeing);
            return new String[]{"added\n"};
        } else {
            return new String[]{"not added\n"};
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
