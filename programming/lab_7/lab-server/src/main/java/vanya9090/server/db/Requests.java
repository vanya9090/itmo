package vanya9090.server.db;

public enum Requests {
    CREATE_HUMAN_BEING_SEQUENCE("CREATE SEQUENCE IF NOT EXISTS human_being_seq START 1"),
    CREATE_COORDINATES_SEQUENCE("CREATE SEQUENCE IF NOT EXISTS coordinates_seq START 1"),
    CREATE_CAR_SEQUENCE("CREATE SEQUENCE IF NOT EXISTS car_seq START 1"),

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
    + "car_id BIGINT NOT NULL REFERENCES CAR(id))"),

    CREATE_COORDINATES_TABLE("CREATE TABLE IF NOT EXISTS COORDINATES ("
    + "id BIGINT PRIMARY KEY DEFAULT nextval('coordinates_seq'),"
    + "x INT NOT NULL CHECK (x <= 925),"
    + "y FLOAT NOT NULL CHECK (y > -208))"),

    CREATE_CAR_TABLE("CREATE TABLE IF NOT EXISTS CAR ("
    + "id BIGINT PRIMARY KEY DEFAULT nextval('car_seq'),"
    + "name TEXT NOT NULL,"
    + "cool BOOLEAN NOT NULL)"),

    INSERT_COORDINATES("INSERT INTO COORDINATES (x, y) VALUES (?,?)"),
    INSERT_CAR("INSERT INTO CAR (name, cool) VALUES (?,?)"),
    INSERT_HUMAN_BEING("INSERT INTO HUMAN_BEING" +
            " (name, real_hero, has_tooth_pick, impact_speed, minutes_of_waiting, weapon_type, mood, coordinates_id, car_id)" +
            " VALUES (?,?,?,?,?,?,?,?,?)"),

    SELECT_HUMAN_BEING_BY_ID("SELECT * FROM HUMAN_BEING "
            + "INNER JOIN COORDINATES "
            + "ON HUMAN_BEING.id = COORDINATES.id "
            + "INNER JOIN CAR "
            + "ON HUMAN_BEING.id = CAR.id "
            + "WHERE HUMAN_BEING.id = ?"),

    SELECT_ALL("SELECT * FROM HUMAN_BEING "
            + "INNER JOIN COORDINATES "
            + "ON HUMAN_BEING.id = COORDINATES.id "
            + "INNER JOIN CAR "
            + "ON HUMAN_BEING.id = CAR.id "),

    DROP_ALL("DROP TABLE IF EXISTS HUMAN_BEING;"
            + "DROP TABLE IF EXISTS COORDINATES;"
            + "DROP TABLE IF EXISTS CAR;"
            + "DROP SEQUENCE IF EXISTS human_being_seq;"
            + "DROP SEQUENCE IF EXISTS coordinates_seq;"
            + "DROP SEQUENCE IF EXISTS car_seq;"),

    TRUNCATE_ALL("TRUNCATE TABLE IF EXISTS HUMAN_BEING;"
            + "TRUNCATE TABLE IF EXISTS COORDINATES;"
            + "TRUNCATE TABLE IF EXISTS CAR;");

    private final String query;
    Requests(String query) {
        this.query = query;
    }
    public String getQuery() {return this.query;}
}
