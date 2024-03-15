package vanya9090.client.commands;

import vanya9090.common.exceptions.NotFoundException;
import vanya9090.common.exceptions.WrongAmountOfElementsException;
import vanya9090.client.models.HumanBeing;
import vanya9090.client.models.WeaponType;
import vanya9090.client.utils.ILogger;
import vanya9090.client.managers.CollectionManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FilterByWeaponType extends Command {
    private final CollectionManager collectionManager;

    public FilterByWeaponType(CollectionManager collectionManager) {
        super("filter_by_weapon_type", "вывести элементы, значение поля weaponType которых равно заданному");
        this.collectionManager = collectionManager;
    }

    @Override
    public String apply(String[] args) throws WrongAmountOfElementsException, NotFoundException {
        List<HumanBeing> list = new ArrayList<HumanBeing>();
        try {
            if (args[1].isEmpty()) throw new WrongAmountOfElementsException("пустой аргумент, введите тип оружия");
            WeaponType weaponType = WeaponType.valueOf(args[1].toUpperCase());
            List<HumanBeing> result = filterByWeaponType(weaponType);
            if (result.isEmpty()) return "no results\n";
            return result.stream().map(HumanBeing::toString).collect(Collectors.joining("\n")) + "\n";
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
