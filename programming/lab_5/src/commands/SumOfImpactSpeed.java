package commands;

import managers.CollectionManager;
import models.HumanBeing;
import utils.Logger;

public class SumOfImpactSpeed extends Command{
    private final Logger logger;
    private final CollectionManager collectionManager;
    public SumOfImpactSpeed(Logger logger, CollectionManager collectionManager) {
        super("sum_of_impact_speed", "вывести сумму значений поля impactSpeed для всех элементов коллекции");
        this.logger = logger;
        this.collectionManager = collectionManager;
    }

    @Override
    public void apply(String[] args) {
        int result = this.getSumOfImpactSpeed();
        logger.info("сумма значений поля impactSpeed для всех элементов коллекции: " + result);
    }

    private int getSumOfImpactSpeed() {
        return this.collectionManager.getCollection().stream()
                .map(HumanBeing::getImpactSpeed)
                .mapToInt(Integer::intValue)
                .sum();
    }
}
