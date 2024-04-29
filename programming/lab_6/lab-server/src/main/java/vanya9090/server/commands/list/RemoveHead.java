package vanya9090.server.commands.list;


import vanya9090.common.commands.CommandArgument;
import vanya9090.server.managers.CollectionManager;
import vanya9090.common.commands.Command;
import vanya9090.common.exceptions.EmptyCollectionException;
import vanya9090.common.exceptions.EmptyFieldException;

/**
 * удаление первого человека и вывод в консоли
 *
 * @author vanya9090
 */
public class RemoveHead extends Command {
    private final CollectionManager collectionManager;

    public RemoveHead(CollectionManager collectionManager) {
        super("remove_head", "вывести первый элемент коллекции и удалить его",
                new CommandArgument[]{});
        this.collectionManager = collectionManager;
    }

    @Override
    public Object[] apply(CommandArgument args) throws EmptyFieldException, EmptyCollectionException {
        if (this.collectionManager.getSize() == 0) throw new EmptyCollectionException();
        return new String[]{collectionManager.removeHead().toString()};
    }
}
