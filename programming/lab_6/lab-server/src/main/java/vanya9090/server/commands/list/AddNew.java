package vanya9090.server.commands.list;

import vanya9090.common.commands.Command;
import vanya9090.common.commands.CommandArgument;
import vanya9090.common.models.HumanBeing;
import vanya9090.server.managers.CollectionManager;

public class AddNew extends Command {
    private final CollectionManager collectionManager;
    public AddNew(CollectionManager collectionManager) {
        super("add", "добавить новый элемент в коллекцию");
        this.collectionManager = collectionManager;
    }

    @Override
    public Object apply(CommandArgument args) throws Exception {
        HumanBeing humanBeing = args.getHumanBeing();
        collectionManager.add(humanBeing);
        return "";
    }
}
