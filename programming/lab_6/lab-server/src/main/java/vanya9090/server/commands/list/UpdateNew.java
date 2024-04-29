package vanya9090.server.commands.list;

import vanya9090.common.commands.Command;
import vanya9090.common.commands.CommandArgument;
import vanya9090.common.commands.Formable;
import vanya9090.common.exceptions.NotFoundException;
import vanya9090.common.exceptions.WrongAmountOfElementsException;
import vanya9090.common.models.HumanBeing;
import vanya9090.server.managers.CollectionManager;

public class UpdateNew extends Command implements Formable {
    private final CollectionManager collectionManager;
    public UpdateNew(CollectionManager collectionManager) {
        super("update", "обновить значение элемента коллекции, id которого равен заданному",
                new CommandArgument[]{new CommandArgument("human", HumanBeing.class)});
        this.collectionManager = collectionManager;
    }

    @Override
    public Object[] apply(CommandArgument arg) throws Exception {
        HumanBeing humanBeing = arg.getModelArg();
        System.out.println(arg.getStringArg().length);
        if (arg.getStringArg().length == 0) throw new WrongAmountOfElementsException("пустой аргумент, введите id человека");
        int id = Integer.parseInt(arg.getStringArg()[0]);
        HumanBeing humanToUpdate = collectionManager.getById(id);
        if (humanToUpdate == null) throw new NotFoundException("человек с таким id не найден");
        humanBeing.setId(collectionManager.getNextId());
        humanToUpdate.update(humanBeing);
        return new String[]{""};
    }
}
