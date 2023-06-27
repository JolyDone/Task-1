package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.dropUsersTable();
        userService.createUsersTable();
        userService.saveUser("Danila", "Shevchenko", (byte) 20);
        userService.saveUser("NIKITA", "Shevchenko", (byte) 21);
        userService.saveUser("ANDREY", "Shevchenko", (byte) 22);
        userService.saveUser("VIKTOR", "Shevchenko", (byte) 23);


        userService.removeUserById(23);
        userService.cleanUsersTable();
    }
}
