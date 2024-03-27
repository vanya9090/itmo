package vanya9090.server.commands.list;

import vanya9090.server.managers.CollectionManager;
import vanya9090.server.models.HumanBeing;
import vanya9090.common.commands.Command;

/**
 * вывод суммы значений поля impactSpeed
 *
 * @author vanya9090
 */
public class SumOfImpactSpeed extends Command {
    private final CollectionManager collectionManager;

    public SumOfImpactSpeed(CollectionManager collectionManager) {
        super("sum_of_impact_speed", "вывести сумму значений поля impactSpeed для всех элементов коллекции");
        this.collectionManager = collectionManager;
    }

    @Override
    public String apply(String[] args) {
        int result = this.getSumOfImpactSpeed();
        return result + "\n";
    }

    private int getSumOfImpactSpeed() {
        return this.collectionManager.getCollection().stream()
                .map(HumanBeing::getImpactSpeed)
                .mapToInt(Integer::intValue)
                .sum();
    }
}
