package vanya9090.server.commands.list;

import vanya9090.common.commands.Command;
import vanya9090.common.commands.CommandArgument;
import vanya9090.common.commands.CommandType;
import vanya9090.common.commands.Formable;
import vanya9090.common.models.*;
import vanya9090.server.managers.CollectionManager;

import java.util.Map;


public class AddIfMin extends Command implements Formable {
    private final CollectionManager collectionManager;
    public AddIfMin(CollectionManager collectionManager) {
        super("add_if_min", "добавить новый элемент в коллекцию, если его расстояние от начала координат меньше, чем у наименьшего элемента этой коллекции",
                new CommandArgument[]{new CommandArgument("human", HumanBeing.class),
                                      new CommandArgument("user", User.class, CommandType.SYSTEM)});
        this.collectionManager = collectionManager;
    }

    @Override
    public Object[] apply(Map<String, Object> arg) throws Exception {
        User user = (User) arg.get("user");
        HumanBeing humanBeing = (HumanBeing) arg.get("human");
        if (humanBeing.getCoordinates().getDistance() < this.getMin()) {
            humanBeing.setId(collectionManager.getNextId());
            collectionManager.add(humanBeing, user);
            return new String[]{"added"};
        } else {
            return new String[]{"not added"};
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
