package vanya9090.server.managers;

import org.apache.commons.codec.digest.DigestUtils;
import vanya9090.common.exceptions.AuthException;
import vanya9090.common.exceptions.MessageException;
import vanya9090.common.exceptions.NotFoundException;
import vanya9090.common.models.User;
import vanya9090.server.db.Requests;

import javax.crypto.spec.PSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private final String url = "jdbc:postgresql://localhost:5432/ivanmironov";
    private final String username = "ivanmironov";
    private final String password = "";
    private final Connection connection;
    public UserManager() throws SQLException {
        this.connection = this.getConnection();
        try (this.connection;
             PreparedStatement createUserSeq = connection.prepareStatement(Requests.CREATE_USER_SEQUENCE.getQuery());
             PreparedStatement createUserTable = connection.prepareStatement(Requests.CREATE_USER_TABLE.getQuery());
        ){
            createUserSeq.executeUpdate();
            createUserTable.executeUpdate();
        }
    }
    public Connection getConnection() throws SQLException {
        if (this.connection == null) {
            Connection conn = DriverManager.getConnection(url, username, password);
            return conn;
        }
        else if (this.connection.isClosed()) {
            System.out.println(this.connection.isClosed());
            Connection conn = DriverManager.getConnection(url, username, password);
            return conn;
        } else {
            return this.connection;
        }
    }

    public void add(User user) throws SQLException {
        try (Connection connection = this.getConnection();
             PreparedStatement statement = connection.prepareStatement(Requests.INSERT_USER.getQuery());) {
                statement.setString(1, user.getLogin());
                statement.setString(2, user.getPassword());
                statement.executeUpdate();
        }
    }
    public List<String> getLogins() throws SQLException {
        List<String> logins = new ArrayList<>();
        try (Connection connection = this.getConnection();
             PreparedStatement statement = connection.prepareStatement(Requests.SELECT_ALL_LOGINS.getQuery());)
        {
            System.out.println(statement);
            statement.execute();
            ResultSet rs = statement.getResultSet();
            while (rs.next()) {
                logins.add(rs.getString("login"));
            }
        }
        return logins;
    }

//    public List<User> getUsers() throws SQLException {
//        List<User> users = new ArrayList<>();
//        try (Connection connection = this.getConnection();
//             Statement statement = connection.createStatement();)
//        {
//            ResultSet rs = statement.executeQuery(Requests.SELECT_ALL_USERS.getQuery());
//            while (rs.next()) {
//                System.out.println("db password: " + rs.getString("password"));
//                users.add(new User(rs.getString("login")).setPasswordSHA(rs.getString("password")));
//            }
//        }
//        return users;
//    }

//    public boolean isUserExists(String login, String password) throws Exception {
//        List<User> users = getUsers();
//        System.out.println(login + " " + password);
//        for (User user : users) {
//            System.out.println(user.getLogin() + " " + user.getPassword() + " " + user.getLogin().equals(login) + " " + user.getPassword().equals(password));
//            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
//                return true;
//            } else if (user.getLogin().equals(login) && !user.getPassword().equals(password)) {
//                throw new AuthException("Неверный пароль");
//            } else {
//                continue;
//            }
//        }
//        return false;
//    }

    public boolean isUserLoginExists(User user) throws Exception {
        boolean isExists = false;
        try (Connection connection = this.getConnection();
             PreparedStatement statement = connection.prepareStatement(Requests.USER_LOGIN_EXISTS.getQuery());) {
            statement.setString(1, user.getLogin());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                isExists = rs.getBoolean(1);
            }
        }
        return isExists;
    }

    public boolean isUserExists(User user) throws Exception {
        boolean isExists = false;
        try (Connection connection = this.getConnection();
             PreparedStatement statement = connection.prepareStatement(Requests.USER_EXISTS.getQuery());) {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                isExists = rs.getBoolean(1);
            }
        }
        return isExists;
    }

    public int getId(User user) throws SQLException, NotFoundException {
        try (Connection connection = this.getConnection();
             PreparedStatement statement = connection.prepareStatement(Requests.SELECT_USER_ID.getQuery()))
        {
            statement.setString(1, user.getLogin());
            ResultSet rs = statement.executeQuery();
            System.out.println(rs);
            if (rs.next()) {
                return rs.getInt("id");
            }
        }
        throw new NotFoundException("Пользователь не найден");
    }

//    public boolean isUserExists(String login, String password) throws Exception {
//        User tempUser = new User(login, password);
//        List<User> users = getUsers();
//        System.out.println(tempUser.getLogin() + " " + tempUser.getPassword());
//        for (User user : users) {
//            System.out.println("all_users: " + user.getLogin() + " " + user.getPassword());
//        }
//        if (users.contains(tempUser)) {
//            return true;
//        } else if (users.stream().map(User::getLogin).toList().contains(login)) {
//            throw new Exception("Неверный пароль");
//        }
//        return false;
//    }
}
