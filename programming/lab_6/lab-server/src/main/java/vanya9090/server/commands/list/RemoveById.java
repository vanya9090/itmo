package vanya9090.server.commands.list;


import vanya9090.common.commands.CommandArgument;
import vanya9090.server.managers.CollectionManager;
import vanya9090.common.models.HumanBeing;
import vanya9090.common.commands.Command;
import vanya9090.common.exceptions.EmptyCollectionException;
import vanya9090.common.exceptions.FormatException;
import vanya9090.common.exceptions.NotFoundException;
import vanya9090.common.exceptions.WrongAmountOfElementsException;

/**
 * команда для удаления человека по id
 *
 * @author vanya9090
 */
public class RemoveById extends Command {
    private final CollectionManager collectionManager;

    public RemoveById(CollectionManager collectionManager) {
        super("remove_by_id", "удалить элемент из коллекции по его id",
                new CommandArgument[]{new CommandArgument("id", Integer.class)});
        this.collectionManager = collectionManager;
    }

    @Override
    public Object[] apply(CommandArgument args) throws WrongAmountOfElementsException, EmptyCollectionException, NotFoundException, FormatException {
        try {
            if (args.getStringArg().length == 0) throw new WrongAmountOfElementsException("пустой аргумент, введите id");
            if (collectionManager.getSize() == 0) throw new EmptyCollectionException();

            Integer id = Integer.parseInt(args.getStringArg()[0]);
            HumanBeing humanToDelete = collectionManager.getById(id);
            if (humanToDelete == null) throw new NotFoundException("человек с таким id не найден");

            collectionManager.remove(humanToDelete);

            return new String[]{""};

        } catch (ArrayIndexOutOfBoundsException e) {
            throw new WrongAmountOfElementsException("пустой аргумент, введите id");
        } catch (NumberFormatException e) {
            throw new FormatException("id должен быть представлен целым числом");
        }
    }
}
