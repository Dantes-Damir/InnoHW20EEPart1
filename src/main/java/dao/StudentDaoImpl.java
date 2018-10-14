package dao;

import ConnectionManager.ConnectionManager;
import ConnectionManager.ConnectionManagerJdbcImpl;
import pojo.City;
import pojo.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao {
    private static ConnectionManager connectionManager = ConnectionManagerJdbcImpl.getInstance();

    @Override
    public boolean addStudent(Student student) {
        Connection connection = connectionManager.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO students(id, name, family_name, age, contact, city) VALUES (DEFAULT, ?, ?, ?, ?, ?)");
        ) {
            statement.setString(1, student.getName());
            statement.setString(2, student.getFamilyName());
            statement.setInt(3, student.getAge());
            statement.setString(4, student.getContact());
            statement.setInt(5, student.getCity().getId());
            System.out.println(student.getCity());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Student getStudentById(int id) {
        Connection connection = connectionManager.getConnection();
        Student student = null;
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * from students WHERE id = ?");
        ) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    City city = null;
                    city = new CityDaoImpl().getCityById(resultSet.getInt("city"));
                    if (city == null)
                        student = new Student(resultSet.getInt("id"),
                                resultSet.getString("name"),
                                resultSet.getString("family_name"),
                                resultSet.getInt("age"),
                                resultSet.getString("contact"),
                                city);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return student;
    }

    @Override
    public boolean update(Student student) {
        if (student.getId() != 0) {
            Connection connection = connectionManager.getConnection();
            try (PreparedStatement statement = connection.prepareStatement(
                    "UPDATE students SET name=?, family_name=?, age=?, contact=?, city=? WHERE id=?");
            ) {
                statement.setString(1, student.getName());
                statement.setString(2, student.getFamilyName());
                statement.setInt(3, student.getAge());
                statement.setString(4, student.getContact());
                statement.setInt(5, student.getCity().getId());
                statement.setInt(6, student.getId());
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
    public boolean deleteStudentById(int id) {
        Connection connection = connectionManager.getConnection();
        Student student = null;
        try (PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM students WHERE id=?");
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
    public boolean deleteStudentByName(Student student) {
        return true;
    }

    @Override
    public List<Student> getAllStudents() {
        List<Student> result = new ArrayList<>();
        Connection connection = connectionManager.getConnection();
        Student student = null;
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * from students");
        ) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    City city = null;
                    city = new CityDaoImpl().getCityById(resultSet.getInt("city"));

                    student = new Student(resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("family_name"),
                            resultSet.getInt("age"),
                            resultSet.getString("contact"),
                            city);
                    result.add(student);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return result;
    }
}
