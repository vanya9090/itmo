package commands;

import exceptions.WrongAmountOfElementsException;
import managers.CollectionManager;
import models.HumanBeing;
import models.WeaponType;
import utils.Logger;

import java.util.List;

public class FilterByWeaponType extends Command{
    private final Logger logger;
    private final CollectionManager collectionManager;

    public FilterByWeaponType(Logger logger, CollectionManager collectionManager) {
        super("filter_by_weapon_type", "вывести элементы, значение поля weaponType которых равно заданному");
        this.logger = logger;
        this.collectionManager = collectionManager;
    }

    @Override
    public void apply(String[] args) {
        try {
            if (args[1].isEmpty()) throw new WrongAmountOfElementsException();
            WeaponType weaponType = WeaponType.valueOf(args[1].toUpperCase());
            List<HumanBeing> result = filterByWeaponType(weaponType);
            for (HumanBeing humanBeing: result){
                logger.info(humanBeing);
            }
        } catch (WrongAmountOfElementsException e) {
            logger.error("пустой аргумент, введите тип оружия");
        } catch (IllegalArgumentException e) {
            logger.error("такого типа оружия нет");
        }
    }

    private List<HumanBeing> filterByWeaponType(WeaponType weaponType) {
        return this.collectionManager.getCollection().stream()
                .filter(elem -> elem.getWeaponType().equals(weaponType))
                .toList();
    }
}
