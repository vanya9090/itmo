package vanya9090.server.commands.list;

import vanya9090.common.commands.Command;
import vanya9090.common.commands.CommandArgument;
import vanya9090.common.models.User;
import vanya9090.server.managers.UserManager;

import java.util.Map;

public class Register extends Command {
    private final UserManager userManager;
    public Register(UserManager userManager) {
        super("signin", "signin", new CommandArgument[]{new CommandArgument("user", User.class)});
        this.userManager = userManager;
    }

    @Override
    public Object[] apply(Map<String, Object> arg) throws Exception {
        User user = (User) arg.get("user");
        if (userManager.getLogins().contains(user.getLogin())) {
            return new String[]{"this name is already taken!"};
        } else {
            userManager.add(user);
        }
        return new String[]{};
    }
}
