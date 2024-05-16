package vanya9090.server.commands.list;

import vanya9090.common.commands.Command;
import vanya9090.common.commands.CommandArgument;
import vanya9090.common.commands.CommandType;
import vanya9090.common.exceptions.AuthException;
import vanya9090.common.models.User;
import vanya9090.server.managers.UserManager;

import java.util.Map;

public class Auth extends Command {
    private final UserManager userManager;
    public Auth(UserManager userManager) {
        super("login", "login", new CommandArgument[]{new CommandArgument("user", User.class)});
        this.userManager = userManager;
    }

    @Override
    public Object[] apply(Map<String, Object> arg) throws Exception {
        User user = (User) arg.get("user");
        if (!userManager.isUserExists(user.getLogin(), user.getPassword())) {
            throw new AuthException("Пользователь не зарегистрирован");
        } else {
            return new Object[]{user};
        }
    }
}
