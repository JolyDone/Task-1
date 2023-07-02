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

        List<User> userList = userService.getAllUsers();
        System.out.println(userList.isEmpty());
        for (User user: userList) {
            System.out.println(user);
        }
        if(userList.size()!=1){
            System.out.println("NEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEET");
        }

//        userService.cleanUsersTable();
//        userService.dropUsersTable();
    }
}
