package vanya9090.server.commands.list;

import vanya9090.common.commands.Command;
import vanya9090.common.commands.CommandArgument;
import vanya9090.common.models.HumanBeing;
import vanya9090.common.commands.Formable;
import vanya9090.server.managers.CollectionManager;

public class AddNew extends Command implements Formable {
    private final CollectionManager collectionManager;
    public AddNew(CollectionManager collectionManager) {
        super("add", "добавить новый элемент в коллекцию",  new CommandArgument[]{new CommandArgument("human", HumanBeing.class)});
        this.collectionManager = collectionManager;
    }

    @Override
    public Object[] apply(CommandArgument arg) throws Exception {
        HumanBeing humanBeing = arg.getModelArg();
        humanBeing.setId(collectionManager.getNextId());
        collectionManager.add(humanBeing);
        return new String[]{""};
    }
}
