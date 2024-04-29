package vanya9090.server.commands.list;

import vanya9090.common.commands.CommandArgument;
import vanya9090.server.managers.CollectionManager;
import vanya9090.common.models.HumanBeing;
import vanya9090.common.commands.Command;

import java.util.Map;

/**
 * вывод суммы значений поля impactSpeed
 *
 * @author vanya9090
 */
public class SumOfImpactSpeed extends Command {
    private final CollectionManager collectionManager;

    public SumOfImpactSpeed(CollectionManager collectionManager) {
        super("sum_of_impact_speed", "вывести сумму значений поля impactSpeed для всех элементов коллекции",
                new CommandArgument[]{});
        this.collectionManager = collectionManager;
    }

    @Override
    public Object[] apply(Map<String, Object> args) {
        int result = this.getSumOfImpactSpeed();
        return new String[]{result + "\n"};
    }

    private int getSumOfImpactSpeed() {
        return this.collectionManager.getCollection().stream()
                .map(HumanBeing::getImpactSpeed)
                .mapToInt(Integer::intValue)
                .sum();
    }
}
