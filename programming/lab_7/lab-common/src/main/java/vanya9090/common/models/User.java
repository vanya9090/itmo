package vanya9090.common.models;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.Serializable;
import java.util.Map;

public class User implements Serializable {
    private final String login;
    private String password;

    public User(Map<String, Object> humanMap) {
        this.login = (String) humanMap.get("login");
        this.password = DigestUtils.sha1Hex((String) humanMap.get("password"));
    }

    public User(String login) {
        this.login = login;
//        this.password = DigestUtils.sha1Hex(password);
    }

    public User setPasswordSHA(String password) {
        this.password = password;
        return this;
    }

    public User setPassword(String password) {
        this.password = DigestUtils.sha1Hex(password);
        return this;
    }

    public String getLogin() {
        return login;
    }
    public String getPassword() {
        return password;
    }
}
