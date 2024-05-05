package vanya9090.server.managers;

import vanya9090.common.models.*;
import vanya9090.server.db.Requests;

import java.sql.*;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;

public class DataBaseManager implements StorageManager{
    private final String url = "jdbc:postgresql://localhost:5432/ivanmironov";
    private final String username = "ivanmironov";
    private final String password = "";
    private final Connection connection;
    public DataBaseManager() throws SQLException {
        this.connection = this.getConnection();
        try (this.connection;
            PreparedStatement createCarSeq = connection.prepareStatement(Requests.CREATE_CAR_SEQUENCE.getQuery());
            PreparedStatement createCoordinatesSeq = connection.prepareStatement(Requests.CREATE_COORDINATES_SEQUENCE.getQuery());
            PreparedStatement createHumanBeingSeq = connection.prepareStatement(Requests.CREATE_HUMAN_BEING_SEQUENCE.getQuery());

            PreparedStatement createCarTable = connection.prepareStatement(Requests.CREATE_CAR_TABLE.getQuery());
            PreparedStatement createCoordinatesTable = connection.prepareStatement(Requests.CREATE_COORDINATES_TABLE.getQuery());
            PreparedStatement createHumanBeingTable = connection.prepareStatement(Requests.CREATE_HUMAN_BEING_TABLE.getQuery());
        ){
            createCarSeq.execute();
            createCoordinatesSeq.execute();
            createHumanBeingSeq.execute();

            createCarTable.execute();
            createCoordinatesTable.execute();
            createHumanBeingTable.execute();
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

    public void add(HumanBeing humanBeing) throws SQLException {
        Integer coordinatesId = this.addCoordinates(humanBeing.getCoordinates());
        Integer carId = this.addCar(humanBeing.getCar());
        try (Connection connection = this.getConnection();
            PreparedStatement statement = connection.prepareStatement(Requests.INSERT_HUMAN_BEING.getQuery()))
        {
            statement.setString(1, humanBeing.getName());
            statement.setBoolean(2, humanBeing.getRealHero());
            statement.setBoolean(3, humanBeing.getHasToothpick());
            statement.setInt(4, humanBeing.getImpactSpeed());
            statement.setFloat(5, humanBeing.getMinutesOfWaiting());
            statement.setString(6, humanBeing.getWeaponType().name());
            statement.setString(7, humanBeing.getMood().name());
            statement.setInt(8, coordinatesId);
            statement.setInt(9, carId);
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
        Coordinates coordinates = new Coordinates(rs.getInt(13),
                rs.getFloat(14));
        Car car = new Car(rs.getString(16),
                rs.getBoolean(17));

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
             PreparedStatement statement = connection.prepareStatement(Requests.SELECT_ALL.getQuery()))
        {
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

        for (HumanBeing humanBeing : collection) {
            this.add(humanBeing);
        }
    }

    public void deleteAllCollection() throws SQLException{
        try (Connection connection = this.getConnection();
            PreparedStatement statement = connection.prepareStatement(Requests.DROP_ALL.getQuery()))
        {
            statement.executeUpdate();
        }
    }

    public Connection getConnection() throws SQLException {
        if (this.connection == null) {
            return DriverManager.getConnection(url, username, password);
        }
        else if (this.connection.isClosed()) {
            return DriverManager.getConnection(url, username, password);
        } else {
            return this.connection;
        }
    }
}
