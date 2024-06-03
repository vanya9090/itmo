package vanya9090.server.managers;

import vanya9090.common.exceptions.AuthException;
import vanya9090.common.exceptions.NotFoundException;
import vanya9090.common.models.*;
import vanya9090.server.Server;
import vanya9090.server.db.Requests;

import javax.swing.plaf.IconUIResource;
import java.sql.*;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;
import java.util.Objects;

public class DataBaseManager implements StorageManager{
    private final String url = "jdbc:postgresql://localhost:5432/ivanmironov";
    private final String username = "ivanmironov";
    private final String password = "";
    private final Connection connection;
    public DataBaseManager() throws SQLException {
        this.connection = this.getConnection();
        try (this.connection;
            PreparedStatement createUserSeq = connection.prepareStatement(Requests.CREATE_USER_SEQUENCE.getQuery());
            PreparedStatement createCarSeq = connection.prepareStatement(Requests.CREATE_CAR_SEQUENCE.getQuery());
            PreparedStatement createCoordinatesSeq = connection.prepareStatement(Requests.CREATE_COORDINATES_SEQUENCE.getQuery());
            PreparedStatement createHumanBeingSeq = connection.prepareStatement(Requests.CREATE_HUMAN_BEING_SEQUENCE.getQuery());

            PreparedStatement createUserTable = connection.prepareStatement(Requests.CREATE_USER_TABLE.getQuery());
            PreparedStatement createCarTable = connection.prepareStatement(Requests.CREATE_CAR_TABLE.getQuery());
            PreparedStatement createCoordinatesTable = connection.prepareStatement(Requests.CREATE_COORDINATES_TABLE.getQuery());
            PreparedStatement createHumanBeingTable = connection.prepareStatement(Requests.CREATE_HUMAN_BEING_TABLE.getQuery());
        ){
            createUserSeq.execute();
            createCarSeq.execute();
            createCoordinatesSeq.execute();
            createHumanBeingSeq.execute();

            createUserTable.execute();
            createCarTable.execute();
            createCoordinatesTable.execute();
            System.out.println(createHumanBeingTable);
            createHumanBeingTable.execute();
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
            System.out.println(this.connection.isClosed());
            return this.connection;
        }
    }

    private void setFieldsHumanBeing(HumanBeing humanBeing, int coordinatesId, int carId, PreparedStatement statement) throws SQLException {
        statement.setString(1, humanBeing.getName());
        statement.setBoolean(2, humanBeing.getRealHero());
        statement.setBoolean(3, humanBeing.getHasToothpick());
        statement.setInt(4, humanBeing.getImpactSpeed());
        statement.setFloat(5, humanBeing.getMinutesOfWaiting());
        statement.setString(6, humanBeing.getWeaponType().name());
        statement.setString(7, humanBeing.getMood().name());
        statement.setInt(8, coordinatesId);
        statement.setInt(9, carId);
    }

    public void remove(int humanBeingId, User user) throws SQLException, AuthException, NotFoundException {
        String userLogin = getLoginById(humanBeingId);
        if (!Objects.equals(userLogin, user.getLogin())) {
            throw new AuthException("Пользователь не может редактировать объекты другого пользователя");
        }
        try (Connection connection = this.getConnection();
             PreparedStatement statement = connection.prepareStatement(Requests.DELETE_HUMAN_BEING_BY_ID.getQuery()))
        {
            statement.setInt(1, humanBeingId);
            statement.executeUpdate();
        }
    }

    public Integer addCar(Car car) throws SQLException {
        Integer generatedId = null;
        try (Connection connection = this.getConnection();
             PreparedStatement statement = connection.prepareStatement(Requests.INSERT_CAR.getQuery(), Statement.RETURN_GENERATED_KEYS))
        {
            statement.setString(1, car.getName());
            statement.setBoolean(2, car.getCool());
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                generatedId = rs.getInt(1);
            }
        }
        return generatedId;
    }

    public Integer addCoordinates(Coordinates coordinates) throws SQLException {
        Integer generatedId = null;
        try (Connection connection = this.getConnection();
             PreparedStatement statement = connection.prepareStatement(Requests.INSERT_COORDINATES.getQuery(), Statement.RETURN_GENERATED_KEYS))
        {
            statement.setInt(1, coordinates.getX());
            statement.setFloat(2, coordinates.getY());
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                generatedId = rs.getInt(1);
            }
        }
        return generatedId;
    }

    public int getForeignId(int humanBeingId, String fieldName) throws SQLException {
        System.out.println(humanBeingId + " " + fieldName);
        Integer foreignId = null;
        try (Connection connection = this.getConnection();
             PreparedStatement statement = connection.prepareStatement(Requests.SELECT_FOREIGN_KEYS.getQuery());)
        {
            statement.setInt(1, humanBeingId);
            statement.execute();
            ResultSet rs = statement.getResultSet();
            if (rs.next()) {
                foreignId = rs.getInt(fieldName);
            }
        }
        return foreignId;
    }

    public void updateCoordinates(int coordinatesId, Coordinates coordinates) throws SQLException {
        System.out.println(coordinatesId);
        try (Connection connection = this.getConnection();
             PreparedStatement statement = connection.prepareStatement(Requests.UPDATE_COORDINATES.getQuery());)
        {
            System.out.println(statement);
            statement.setInt(1, coordinates.getX());
            statement.setFloat(2, coordinates.getY());
            statement.setInt(3, coordinatesId);
            statement.executeUpdate();
        }
    }

    public void updateCar(int carId, Car car) throws SQLException {
        System.out.println(carId);
        try (Connection connection = this.getConnection();
        PreparedStatement statement = connection.prepareStatement(Requests.UPDATE_CAR.getQuery());)
        {
            System.out.println(statement);
            statement.setString(1, car.getName());
            statement.setBoolean(2, car.getCool());
            statement.setInt(3, carId);
            statement.executeUpdate();
        }
    }

//    public Object getFieldById(String field1, Object id) throws SQLException, NotFoundException {
//        try (Connection connection = this.getConnection();
//             PreparedStatement statement = connection.prepareStatement(Requests.SELECT_FIELD_BY_ID.getQuery());)
//        {
//            statement.setString(1, field1);
//            statement.setObject(2, id);
//
//            System.out.println(statement);
//            statement.execute();
//            ResultSet rs = statement.getResultSet();
//            System.out.println(rs.getMetaData().);
//            if (rs.next()) {
//                return rs.getObject("");
//            }
//        }
//        throw new NotFoundException("Ни одно поле не найдено");
//    }

    public String getLoginById(int id) throws SQLException, NotFoundException {
        try (Connection connection = this.getConnection();
             PreparedStatement statement = connection.prepareStatement(Requests.SELECT_AUTHOR_BY_ID.getQuery());)
        {
            statement.setInt(1, id);
            statement.execute();
            ResultSet rs = statement.getResultSet();
            if (rs.next()) {
                return rs.getString(1);
            }
        }
        throw new NotFoundException("Ни одно поле не найдено");
    }

    public void update(HumanBeing humanBeing, int humanBeingId, User user) throws SQLException, NotFoundException, AuthException {
        String userLogin = getLoginById(humanBeingId);
        if (!Objects.equals(userLogin, user.getLogin())) {
            throw new AuthException("Пользователь не может редактировать объекты другого пользователя");
        }
        int coordinatesId = this.getForeignId(humanBeingId, "coordinates_id");
        int carId = this.getForeignId(humanBeingId, "car_id");
        this.updateCoordinates(coordinatesId, humanBeing.getCoordinates());
        this.updateCar(carId, humanBeing.getCar());
        try (Connection connection = this.getConnection();
        PreparedStatement statement = connection.prepareStatement(Requests.UPDATE_HUMAN_BEING.getQuery());)
        {
            setFieldsHumanBeing(humanBeing, coordinatesId, carId, statement);
            statement.setInt(10, humanBeingId);
            statement.executeUpdate();
        }
    }

    public void add(HumanBeing humanBeing, User user) throws SQLException, NotFoundException {
        Integer coordinatesId = this.addCoordinates(humanBeing.getCoordinates());
        Integer carId = this.addCar(humanBeing.getCar());
        System.out.println(coordinatesId + " " + carId);
        try (Connection connection = this.getConnection();
            PreparedStatement statement = connection.prepareStatement(Requests.INSERT_HUMAN_BEING.getQuery()))
        {
            setFieldsHumanBeing(humanBeing, coordinatesId, carId, statement);
            statement.setString(10, user.getLogin());
            statement.executeUpdate();
        }
    }

    public HumanBeing getHumanBeing(Integer id) throws SQLException {
        try (Connection connection = this.getConnection();
            PreparedStatement statement = connection.prepareStatement(Requests.SELECT_HUMAN_BEING_BY_ID.getQuery()))
        {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return handleHumanBeing(resultSet);
            }
        }
        return null;
    }

    private HumanBeing handleHumanBeing(ResultSet rs) throws SQLException {
        Coordinates coordinates = new Coordinates(rs.getInt(14),
                rs.getFloat(15));
        Car car = new Car(rs.getString(17),
                rs.getBoolean(18));

        return new HumanBeing(
                rs.getInt("id"),
                rs.getString("name"),
                coordinates,
                rs.getDate("creation_date").toLocalDate(),
                rs.getBoolean("real_hero"),
                rs.getBoolean("has_tooth_pick"),
                rs.getInt("impact_speed"),
                rs.getFloat("minutes_of_waiting"),
                WeaponType.valueOf(rs.getString("weapon_type")),
                Mood.valueOf(rs.getString("mood")),
                car);
    }

    public Deque<HumanBeing> read() throws SQLException{
        Deque<HumanBeing> collection = new ArrayDeque<>();
        try (Connection connection = this.getConnection();
             PreparedStatement statement = connection.prepareStatement(Requests.SELECT_ALL.getQuery())) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                collection.add(handleHumanBeing(resultSet));
            }
        }
        return collection;
    }

    public void write(Deque<HumanBeing> collection) throws SQLException {
        try (Connection connection = this.getConnection();
            PreparedStatement statement = connection.prepareStatement(Requests.TRUNCATE_ALL.getQuery()))
        {
            statement.executeUpdate();
        }

//        for (HumanBeing humanBeing : collection) {
//            this.add(humanBeing);
//        }
    }

    public void deleteAllStorage() throws SQLException{
        try (Connection connection = this.getConnection();
            PreparedStatement statement = connection.prepareStatement(Requests.DROP_ALL.getQuery()))
        {
            statement.executeUpdate();

        }
    }

    public void truncateStorage(User user) throws SQLException{
        try (Connection connection = this.getConnection();
             PreparedStatement statement = connection.prepareStatement(Requests.TRUNCATE_ALL.getQuery()))
        {
            statement.setString(1, user.getLogin());
            statement.executeUpdate();
        }
    }

    public String getUserByHumanId(Integer humanBeingId) throws SQLException {
        try (Connection connection = this.getConnection();
             PreparedStatement statement = connection.prepareStatement(Requests.SELECT_USER_HUMAN_ID.getQuery()))
        {
            statement.setInt(1, humanBeingId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                System.out.println(resultSet.getString("login"));
                return resultSet.getString("login");
            }
        }
        return "";
    }
}
