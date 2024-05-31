package vanya9090.common.models;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.Serializable;
import java.util.Map;

public class User implements Serializable {
    private final String login;
    private String password;

    public User(Map<String, Object> humanMap) {
        this.login = (String) humanMap.get("login");
        this.password = (String) humanMap.get("password");
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public void setSHAPassword() {
        this.password = DigestUtils.sha1Hex(password);
    }

    public String getLogin() {
        return login;
    }
    public String getPassword() {
        return password;
    }
}
