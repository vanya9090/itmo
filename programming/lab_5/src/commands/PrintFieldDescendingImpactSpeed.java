package commands;

import managers.CollectionManager;
import models.HumanBeing;
import utils.Logger;

import java.util.Comparator;
import java.util.List;

public class PrintFieldDescendingImpactSpeed extends Command{
    private final Logger logger;
    private final CollectionManager collectionManager;

    public PrintFieldDescendingImpactSpeed(Logger logger, CollectionManager collectionManager) {
        super("print_field_descending_impact_speed", "вывести значения поля impactSpeed всех элементов в порядке убывания");
        this.logger = logger;
        this.collectionManager = collectionManager;
    }

    @Override
    public void apply(String[] args) {
        List<Integer> result = this.getFieldDescendingImpactSpeed();
        for (Integer i: result){
            logger.info(i);
        }
    }

    private List<Integer> getFieldDescendingImpactSpeed(){
        return this.collectionManager.getCollection().stream()
                .map(HumanBeing::getImpactSpeed)
                .sorted(Comparator.reverseOrder())
                .toList();
    }
}
