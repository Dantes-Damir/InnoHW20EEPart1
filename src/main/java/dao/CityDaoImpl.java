package dao;

import ConnectionManager.ConnectionManager;
import ConnectionManager.ConnectionManagerJdbcImpl;
import pojo.City;
import pojo.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CityDaoImpl implements CityDao {
    private static ConnectionManager connectionManager = ConnectionManagerJdbcImpl.getInstance();

    @Override
    public boolean addCity(City city) {
        Connection connection = connectionManager.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO city VALUES (DEFAULT, ?, ?)");
        ) {
            statement.setString(1, city.getName());
            statement.setInt(2, city.getCitizens());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public City getCityById(int id) {
        Connection connection = connectionManager.getConnection();
        City city = null;
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * from city WHERE id = ?");
        ) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    city = new City(resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getInt("citizens"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return city;
    }

    @Override
    public City getCityByName(String name) {
        Connection connection = connectionManager.getConnection();
        City city = null;
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * from city WHERE name = ?");
        ) {
            statement.setString(1, name);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    city = new City(resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getInt("citizens"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return city;
    }

    @Override
    public boolean update(City city) {
        if (city.getId() != 0) {
            Connection connection = connectionManager.getConnection();
            try (PreparedStatement statement = connection.prepareStatement(
                    "UPDATE city SET name=?, citizens=? WHERE id=?");
            ) {
                statement.setString(1, city.getName());
                statement.setInt(2, city.getCitizens());
                statement.setInt(3, city.getId());
                statement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteCityById(int id) {
        Connection connection = connectionManager.getConnection();
        Student student = null;
        try (PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM city WHERE id=?");
        ) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteCityByName(City city) {
        return true;
    }

}
