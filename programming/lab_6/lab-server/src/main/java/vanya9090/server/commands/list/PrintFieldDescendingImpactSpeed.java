package vanya9090.server.commands.list;


import vanya9090.common.commands.Command;
import vanya9090.common.commands.CommandArgument;
import vanya9090.server.managers.CollectionManager;
import vanya9090.common.models.HumanBeing;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * выводит значения поля impactSpeed всех элементов в порядке убывания
 *
 * @author vanya9090
 */
public class PrintFieldDescendingImpactSpeed extends Command {
    private final CollectionManager collectionManager;

    public PrintFieldDescendingImpactSpeed(CollectionManager collectionManager) {
        super("print_field_descending_impact_speed", "вывести значения поля impactSpeed всех элементов в порядке убывания",
                new CommandArgument[]{});
        this.collectionManager = collectionManager;
    }

    /**
     * выполняет команду
     *
     * @param args аргументы, переданные в командной строке
     * @return значения поля impactSpeed всех элементов
     */
    @Override
    public Object[] apply(CommandArgument args) {
        List<Integer> result = this.getFieldDescendingImpactSpeed();
        return result.stream()
                .map(Objects::toString).toArray();
    }

    private List<Integer> getFieldDescendingImpactSpeed() {
        return this.collectionManager.getCollection().stream()
                .map(HumanBeing::getImpactSpeed)
                .sorted(Comparator.reverseOrder())
                .toList();
    }
}
