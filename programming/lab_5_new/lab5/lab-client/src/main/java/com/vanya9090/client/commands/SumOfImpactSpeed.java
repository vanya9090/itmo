package com.vanya9090.client.commands;

import com.vanya9090.client.managers.CollectionManager;
import com.vanya9090.client.models.HumanBeing;
import com.vanya9090.client.utils.ILogger;
import com.vanya9090.client.utils.Logger;

public class SumOfImpactSpeed extends Command {
    private final ILogger logger;
    private final CollectionManager collectionManager;

    public SumOfImpactSpeed(ILogger logger, CollectionManager collectionManager) {
        super("sum_of_impact_speed", "вывести сумму значений поля impactSpeed для всех элементов коллекции");
        this.logger = logger;
        this.collectionManager = collectionManager;
    }

    @Override
    public void apply(String[] args) {
        if (this.collectionManager.getSize() == 0){
            logger.info("коллекция пуста");
        } else {
            int result = this.getSumOfImpactSpeed();
            logger.info("сумма значений поля impactSpeed для всех элементов коллекции: " + result);
        }
    }

    private int getSumOfImpactSpeed() {
        return this.collectionManager.getCollection().stream()
                .map(HumanBeing::getImpactSpeed)
                .mapToInt(Integer::intValue)
                .sum();
    }
}
