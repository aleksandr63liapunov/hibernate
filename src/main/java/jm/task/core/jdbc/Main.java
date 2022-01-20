package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    private static UserServiceImpl userService = new UserServiceImpl();

    private static User user1 = new User("aleks", "qaz", (byte) 10);
    private static User user2 = new User("oleg", "wsx", (byte) 15);
    private static User user3 = new User("rita", "edc", (byte) 40);
    private static User user4 = new User("vika", "rfv", (byte) 20);

    public static void main(String[] args) {
        userService.createUsersTable();

        userService.saveUser(user1.getName(), user1.getLastName(), user1.getAge());
        userService.saveUser(user2.getName(), user2.getLastName(), user2.getAge());
        userService.saveUser(user3.getName(), user3.getLastName(), user3.getAge());
        userService.saveUser(user4.getName(), user4.getLastName(), user4.getAge());

        userService.removeUserById(4);

        userService.getAllUsers();


        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
