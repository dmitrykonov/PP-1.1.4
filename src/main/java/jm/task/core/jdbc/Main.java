package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Dmitry", "Konovalov", (byte) 34);
        userService.saveUser("Andrew", "Sokolov", (byte) 19);
        userService.saveUser("Vasiliy", "Pupkin", (byte) 26);
        userService.saveUser("Elza", "Savina", (byte) 40);
        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        userService.dropUsersTable();


    }
}
