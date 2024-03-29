package vanya9090.server.commands.list;

import vanya9090.common.commands.Command;
import vanya9090.common.commands.CommandArgument;
import vanya9090.server.managers.CollectionManager;
import vanya9090.common.models.HumanBeing;
import vanya9090.common.models.WeaponType;
import vanya9090.common.exceptions.NotFoundException;
import vanya9090.common.exceptions.WrongAmountOfElementsException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * фильтрация по типу оружия
 * @author vanya9090
 */
public class FilterByWeaponType extends Command {
    private final CollectionManager collectionManager;

    public FilterByWeaponType(CollectionManager collectionManager) {
        super("filter_by_weapon_type", "вывести элементы, значение поля weaponType которых равно заданному");
        this.collectionManager = collectionManager;
    }

    /**
     * выполняет команду
     *
     * @param args аргументы, переданные в командной строке
     * @return подходящие HumanBeing объекты
     * @throws WrongAmountOfElementsException пустой аргумент
     * @throws NotFoundException              нет такого типа оружия
     */
    @Override
    public Object[] apply(CommandArgument args) throws WrongAmountOfElementsException, NotFoundException {
        try {
            if (args.getStringArg().length == 0) throw new WrongAmountOfElementsException("пустой аргумент, введите тип оружия");
            WeaponType weaponType = WeaponType.valueOf(args.getStringArg()[0].toUpperCase());
            List<HumanBeing> result = filterByWeaponType(weaponType);
            if (result.isEmpty()) return new String[]{"no results\n"};
            return result.stream().map(HumanBeing::toString).toArray();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new WrongAmountOfElementsException("пустой аргумент, введите тип оружия");
        } catch (IllegalArgumentException e) {
            throw new NotFoundException("такого типа оружия нет");
        }
    }

    private List<HumanBeing> filterByWeaponType(WeaponType weaponType) {
        return this.collectionManager.getCollection().stream()
                .filter(elem -> elem.getWeaponType().equals(weaponType))
                .toList();
    }
}
