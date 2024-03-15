package vanya9090.client.commands;

import vanya9090.common.exceptions.WrongAmountOfElementsException;
import vanya9090.client.models.HumanBeing;
import vanya9090.client.models.WeaponType;
import vanya9090.client.utils.ILogger;
import vanya9090.client.managers.CollectionManager;

import java.util.Arrays;
import java.util.List;

public class FilterByWeaponType extends Command {
    private final ILogger logger;
    private final CollectionManager collectionManager;

    public FilterByWeaponType(ILogger logger, CollectionManager collectionManager) {
        super("filter_by_weapon_type", "вывести элементы, значение поля weaponType которых равно заданному");
        this.logger = logger;
        this.collectionManager = collectionManager;
    }

    @Override
    public void apply(String[] args) {
        try {
            if (args[1].isEmpty()) throw new WrongAmountOfElementsException("пустой аргумент, введите тип оружия");
            WeaponType weaponType = WeaponType.valueOf(args[1].toUpperCase());
            List<HumanBeing> result = filterByWeaponType(weaponType);
            if (result.isEmpty()) logger.info("коллекция пуста");
            for (HumanBeing humanBeing : result) {
                logger.info(humanBeing);
            }
        } catch (WrongAmountOfElementsException | ArrayIndexOutOfBoundsException e) {
            logger.error("пустой аргумент, введите тип оружия");
        } catch (IllegalArgumentException e) {
            logger.error("такого типа оружия нет");
            logger.info("Типы оружий: " + Arrays.toString(WeaponType.values()));
        }
    }

    private List<HumanBeing> filterByWeaponType(WeaponType weaponType) {
        return this.collectionManager.getCollection().stream()
                .filter(elem -> elem.getWeaponType().equals(weaponType))
                .toList();
    }
}
