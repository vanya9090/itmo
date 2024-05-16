package vanya9090.client.forms;

import vanya9090.client.Client;
import vanya9090.common.models.HumanBeing;
import vanya9090.common.models.Mood;
import vanya9090.common.models.User;
import vanya9090.common.models.WeaponType;
import vanya9090.common.util.ILogger;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LoginForm extends ParentForm{
    public LoginForm(ILogger logger, Scanner scanner, boolean isExecute) {
        super(logger, scanner, isExecute);
    }

    @Override
    public User create() throws Exception {
        User user = new User(askAll());
//        Client.user = user;
        return user;
    }

    @Override
    public Map<String, Object> askAll() throws Exception {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> fieldMap;

        for (Field classField : User.class.getDeclaredFields()) {
            fieldMap = this.fieldCircle(classField);
            map.put(classField.getName(), fieldMap.get(classField.getName()));
        }
        return map;
    }
}
