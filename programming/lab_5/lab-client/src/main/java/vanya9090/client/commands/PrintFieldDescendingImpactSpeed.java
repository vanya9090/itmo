package vanya9090.client.commands;


import vanya9090.client.managers.CollectionManager;
import vanya9090.client.utils.ILogger;
import vanya9090.client.models.HumanBeing;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PrintFieldDescendingImpactSpeed extends Command {
    private final CollectionManager collectionManager;

    public PrintFieldDescendingImpactSpeed(CollectionManager collectionManager) {
        super("print_field_descending_impact_speed", "вывести значения поля impactSpeed всех элементов в порядке убывания");
        this.collectionManager = collectionManager;
    }

    @Override
    public String apply(String[] args) {
        List<Integer> result = this.getFieldDescendingImpactSpeed();
        return result.stream()
                .map(Objects::toString)
                .collect(Collectors.joining("\n")) + "\n";
    }

    private List<Integer> getFieldDescendingImpactSpeed() {
        return this.collectionManager.getCollection().stream()
                .map(HumanBeing::getImpactSpeed)
                .sorted(Comparator.reverseOrder())
                .toList();
    }
}
