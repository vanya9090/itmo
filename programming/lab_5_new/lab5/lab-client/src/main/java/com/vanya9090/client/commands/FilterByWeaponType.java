package com.vanya9090.client.commands;

import com.vanya9090.client.exceptions.WrongAmountOfElementsException;
import com.vanya9090.client.managers.CollectionManager;
import com.vanya9090.client.models.HumanBeing;
import com.vanya9090.client.models.WeaponType;
import com.vanya9090.client.utils.Logger;

import java.util.Arrays;
import java.util.List;

public class FilterByWeaponType extends Command {
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
            if (args[1].isEmpty()) {
                throw new WrongAmountOfElementsException();
            }
            WeaponType weaponType = WeaponType.valueOf(args[1].toUpperCase());
            List<HumanBeing> result = filterByWeaponType(weaponType);
            if (result.isEmpty()) {
                logger.info("коллекция пуста");
            }
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
