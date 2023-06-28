package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.dropUsersTable();
        userService.createUsersTable();
        userService.saveUser("DANILA", "ARTUROVICH", (byte) 20);
        userService.saveUser("NIKITA", "REDENKO", (byte) 21);
        userService.saveUser("ANDREY", "VIKTOROVICH", (byte) 22);
        userService.saveUser("VIKTOR", "ANDREEVICH", (byte) 23);
        List<User> userList = userService.getAllUsers();
        for (User user: userList) {
            System.out.println(user.toString());
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
