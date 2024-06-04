package vanya9090.server.db;

public enum Requests {
    CREATE_HUMAN_BEING_SEQUENCE("CREATE SEQUENCE IF NOT EXISTS human_being_seq START 1"),
    CREATE_COORDINATES_SEQUENCE("CREATE SEQUENCE IF NOT EXISTS coordinates_seq START 1"),
    CREATE_CAR_SEQUENCE("CREATE SEQUENCE IF NOT EXISTS car_seq START 1"),
    CREATE_USER_SEQUENCE("CREATE SEQUENCE IF NOT EXISTS user_seq START 1"),

    CREATE_HUMAN_BEING_TABLE("CREATE TABLE IF NOT EXISTS HUMAN_BEING ("
            + "id BIGINT PRIMARY KEY DEFAULT nextval('human_being_seq'),"
            + "name VARCHAR(255) NOT NULL CHECK (name <> ''),"
            + "creation_date DATE NOT NULL DEFAULT CURRENT_DATE,"
            + "real_hero BOOLEAN,"
            + "has_tooth_pick BOOLEAN,"
            + "impact_speed INT,"
            + "minutes_of_waiting FLOAT,"
            + "weapon_type TEXT NOT NULL CHECK (weapon_type in ('HAMMER', 'AXE', 'SHOTGUN', 'RIFLE', 'KNIFE')),"
            + "mood TEXT NOT NULL CHECK (mood in ('SADNESS', 'SORROW', 'APATHY', 'CALM', 'RAGE')),"
            + "coordinates_id BIGINT NOT NULL REFERENCES COORDINATES(id),"
            + "car_id BIGINT NOT NULL REFERENCES CAR(id),"
            + "author VARCHAR(255) NOT NULL REFERENCES user1(login) DEFAULT 'admin')"),
    CREATE_COORDINATES_TABLE("CREATE TABLE IF NOT EXISTS COORDINATES ("
            + "id BIGINT PRIMARY KEY DEFAULT nextval('coordinates_seq'),"
            + "x INT NOT NULL CHECK (x <= 925),"
            + "y FLOAT NOT NULL CHECK (y > -208))"),
    CREATE_CAR_TABLE("CREATE TABLE IF NOT EXISTS CAR ("
            + "id BIGINT PRIMARY KEY DEFAULT nextval('car_seq'),"
            + "name TEXT NOT NULL,"
            + "cool BOOLEAN NOT NULL)"),
    CREATE_USER_TABLE("CREATE TABLE IF NOT EXISTS USER1 ("
            + "login VARCHAR(255) PRIMARY KEY CHECK (login <> ''),"
            + "password VARCHAR(255) NOT NULL CHECK (password <> ''))"),

    INSERT_COORDINATES("INSERT INTO COORDINATES (x, y) VALUES (?,?)"),
    INSERT_CAR("INSERT INTO CAR (name, cool) VALUES (?,?)"),
    INSERT_HUMAN_BEING("INSERT INTO HUMAN_BEING" +
            " (name, real_hero, has_tooth_pick, impact_speed, minutes_of_waiting, weapon_type, mood, coordinates_id, car_id, author)" +
            " VALUES (?,?,?,?,?,?,?,?,?,?)"),
    INSERT_USER("INSERT INTO USER1 (login, password) VALUES (?,?)"),

    UPDATE_HUMAN_BEING("UPDATE HUMAN_BEING "
            + "SET name = ?,"
            + "real_hero = ?,"
            + "has_tooth_pick = ?,"
            + "impact_speed = ?,"
            + "minutes_of_waiting = ?,"
            + "weapon_type = ?,"
            + "mood = ?,"
            + "coordinates_id = ?,"
            + "car_id = ? "
            + "WHERE id = ?"),
    UPDATE_COORDINATES("UPDATE COORDINATES "
            + "SET x = ?,"
            + "y = ? "
            + "WHERE id = ?"),
    UPDATE_CAR("UPDATE CAR "
            + "SET name = ?,"
            + "cool = ? "
            + "WHERE id = ?"),

    DELETE_HUMAN_BEING_BY_ID("DELETE FROM HUMAN_BEING WHERE id = ?"),
    DELETE_COORDINATES_BY_ID("DELETE FROM COORDINATES WHERE id = ?"),
    DELETE_CARS_BY_ID("DELETE FROM CAR WHERE id = ?"),

    SELECT_HUMAN_BEING_BY_ID("SELECT * FROM HUMAN_BEING "
            + "INNER JOIN COORDINATES "
            + "ON HUMAN_BEING.id = COORDINATES.id "
            + "INNER JOIN CAR "
            + "ON HUMAN_BEING.id = CAR.id "
            + "WHERE HUMAN_BEING.id = ?"),
    SELECT_FIELD_BY_ID("SELECT ? FROM HUMAN_BEING "
            + "WHERE HUMAN_BEING.id = ?"),
    SELECT_AUTHOR_BY_ID("SELECT author FROM HUMAN_BEING WHERE HUMAN_BEING.id = ?"),
    SELECT_ALL("SELECT * FROM HUMAN_BEING "
            + "LEFT JOIN COORDINATES "
            + "ON HUMAN_BEING.coordinates_id = COORDINATES.id "
            + "LEFT JOIN CAR "
            + "ON HUMAN_BEING.car_id = CAR.id "),
    SELECT_FOREIGN_KEYS("SELECT coordinates_id, car_id FROM HUMAN_BEING "
            + "WHERE id = ?"),
    SELECT_ALL_LOGINS("SELECT login FROM USER1"),
    SELECT_ALL_USERS("SELECT * FROM USER1"),
    SELECT_USER_ID("SELECT id FROM USER1 WHERE login = ?"),

    DROP_ALL("DROP TABLE IF EXISTS HUMAN_BEING;"
            + "DROP TABLE IF EXISTS COORDINATES;"
            + "DROP TABLE IF EXISTS CAR;"
            + "DROP TABLE IF EXISTS user1;"
            + "DROP SEQUENCE IF EXISTS human_being_seq;"
            + "DROP SEQUENCE IF EXISTS coordinates_seq;"
            + "DROP SEQUENCE IF EXISTS car_seq;"
            + "DROP SEQUENCE IF EXISTS user_seq;"),

    TRUNCATE_ALL("DELETE FROM HUMAN_BEING WHERE author = ?"),

    USER_LOGIN_EXISTS("SELECT EXISTS(SELECT * FROM user1 WHERE login = ?);"),
    USER_EXISTS("SELECT EXISTS(SELECT * FROM user1 WHERE login = ? AND password = ?);"),

    SELECT_USER_HUMAN_ID("SELECT user1.login FROM HUMAN_BEING "
            + "JOIN user1 ON HUMAN_BEING.author = user1.login "
            + "WHERE id = ?");

    private final String query;
    Requests(String query) {
        this.query = query;
    }
    public String getQuery() {return this.query;}
}
