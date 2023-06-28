package jm.task.core.jdbc.dao;
import jm.task.core.jdbc.util.Util;

import java.sql.*;

import jm.task.core.jdbc.model.User;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() { }

    public void createUsersTable(){
        try(Statement statement = Util.getConnection().createStatement()){
            String command = "CREATE TABLE IF NOT EXISTS users_data " +
                    "(id SERIAL not NULL, " +
                    " name VARCHAR(255), " +
                    " lastname VARCHAR(255), " +
                    " age INT, " +
                    " PRIMARY KEY (id))";
            statement.executeUpdate(command);
            System.out.println("Таблица была создана.");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public void dropUsersTable() {
        try (Statement statement = Util.getConnection().createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS users_data ");
            System.out.println("Таблица была удалена.");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try(Connection connection = Util.getConnection()){
            String command = "INSERT INTO users_data (name, lastName, age) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(command);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            int rows = preparedStatement.executeUpdate();
            System.out.printf("User с именем - %s добавлен в базу данных.\n", name);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public void removeUserById(long id) {
        try(Connection connection = Util.getConnection()){
            String command = "DELETE FROM users_data WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(command);
            preparedStatement.setByte(1, (byte) id);
            preparedStatement.executeUpdate();

            System.out.printf("User с id: %d был удален.\n", id);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try(Connection connection = Util.getConnection()){
            String command = "SELECT * FROM users_data";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(command);

            while (resultSet.next()){
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("lastname");
                Byte age = resultSet.getByte("age");

                User user = new User();
                user.setId(id);
                user.setName(name);
                user.setLastName(lastName);
                user.setAge(age);

                userList.add(user);
            }
            System.out.println("Получены все пользователи.");

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return userList;
    }

    public void cleanUsersTable() {
        try(Statement statement = Util.getConnection().createStatement()){
            statement.executeUpdate("TRUNCATE TABLE users_data ");
            System.out.println("Таблица была очищена.");
        } catch (SQLException ignored) { }
    }
}
