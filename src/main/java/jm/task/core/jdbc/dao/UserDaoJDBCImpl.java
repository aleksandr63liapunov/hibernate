package jm.task.core.jdbc.dao;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement statement = Util.getConnetion().createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS users" +
                    "(id mediumint not null auto_increment," +
                    " name VARCHAR(45), " +
                    "lastname VARCHAR(45), " +
                    "age tinyint, " +
                    "PRIMARY KEY (id))");

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public void dropUsersTable() {
        try (Statement statement = Util.getConnetion().createStatement()) {
            statement.executeUpdate("Drop table if exists users");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {

        try (PreparedStatement preparedStatement = Util.getConnetion().prepareStatement("INSERT INTO users(name, lastname, age) VALUES(?,?,?)")) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (Statement statement = Util.getConnetion().createStatement()) {

            statement.executeUpdate("DELETE FROM users where id");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<User> getAllUsers() {
        List<User> allUser = new ArrayList<>();

        try (Statement statement = Util.getConnetion().createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * from users");

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setAge(resultSet.getByte(4));
                allUser.add(user);
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        return allUser;
    }

    public void cleanUsersTable() {
        try (Statement statement = Util.getConnetion().createStatement()) {
            statement.executeUpdate("DELETE FROM users");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
