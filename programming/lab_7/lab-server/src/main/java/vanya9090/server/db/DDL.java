package vanya9090.server.db;

import vanya9090.server.managers.DataBaseManager;

import java.sql.SQLException;

public class DDL {
    public static void main(String[] args) throws SQLException {
        DataBaseManager dataBaseManager = new DataBaseManager();
        dataBaseManager.deleteAllStorage();
    }
}
