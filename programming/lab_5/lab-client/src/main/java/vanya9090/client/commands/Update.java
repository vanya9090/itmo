package vanya9090.client.commands;


import vanya9090.client.managers.CollectionManager;
import vanya9090.client.models.HumanBeing;
import vanya9090.client.models.forms.HumanBeingForm;
import vanya9090.client.utils.ILogger;
import vanya9090.common.exceptions.*;

import java.util.Arrays;
import java.util.Scanner;

public class Update extends Command {
    private final CollectionManager collectionManager;
    private final ILogger logger;

    public Update(ILogger logger, CollectionManager collectionManager) {
        super("update", "обновить значение элемента коллекции, id которого равен заданному");
        this.logger = logger;
        this.collectionManager = collectionManager;
    }

    public void apply(String[] args) throws CollectionIsEmptyException, WrongAmountOfElementsException, NotFoundException {
        try {
            if (args[1].isEmpty()) throw new WrongAmountOfElementsException("пустой аргумент, введите id");
            if (collectionManager.getSize() == 0) throw new CollectionIsEmptyException("коллекция пуста");

            Integer id = Integer.parseInt(args[1].trim());
            HumanBeing humanToUpdate = collectionManager.getById(id);
            if (humanToUpdate == null) throw new NotFoundException("человек с таким id не найден");

            HumanBeingForm humanBeingForm = new HumanBeingForm(this.logger, new Scanner(System.in), false);
            HumanBeing humanBeing = humanBeingForm.create();

            humanToUpdate.update(humanBeing);
            logger.info("Обновлено успешно");

        } catch (ArrayIndexOutOfBoundsException e) {
            logger.error("пустой аргумент, введите id");
        } catch (NumberFormatException e) {
            logger.error("id должен быть представлен целым числом");
        } catch (BooleanFormatException | ParseException | EmptyFieldException e) {
            logger.error("непредвиденная ошибка");
        }
    }

    public void apply(String[] args, Scanner fileReader) {
        try {
            if (args[1].isEmpty()) {
                throw new WrongAmountOfElementsException("пустой аргумент, введите id");
            }
            if (collectionManager.getSize() == 0) {
                throw new CollectionIsEmptyException("коллекция пуста");
            }

            Integer id = Integer.parseInt(args[1].trim());
            HumanBeing humanToUpdate = collectionManager.getById(id);
            if (humanToUpdate == null) {
                throw new NotFoundException("человек с таким id не найден");
            }

            HumanBeingForm humanBeingForm = new HumanBeingForm(this.logger, fileReader, true);
            HumanBeing humanBeing = humanBeingForm.create();

            humanToUpdate.update(humanBeing);
            logger.success("Обновлено успешно");

        } catch (CollectionIsEmptyException e) {
            logger.error("коллекция пуста");
        } catch (WrongAmountOfElementsException | ArrayIndexOutOfBoundsException e) {
            logger.error("пустой аргумент, введите id");
        } catch (NumberFormatException e) {
            logger.error("id должен быть представлен целым числом");
        } catch (NotFoundException e) {
            logger.error(e);
            logger.info("доступные id: " + Arrays.toString(collectionManager.getCollection().stream().map(HumanBeing::getId).toArray()));
//        } catch (ParseException | EmptyFieldException | WrongFieldsException e) {
//            logger.error(e);
        } catch (BooleanFormatException | ParseException | EmptyFieldException e) {
            logger.error(e);
        }
    }
}
