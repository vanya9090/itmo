package vanya9090.client.commands;

import vanya9090.client.managers.CollectionManager;
import vanya9090.client.models.HumanBeing;
import vanya9090.client.utils.ILogger;

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
