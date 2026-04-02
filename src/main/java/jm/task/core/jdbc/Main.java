package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;

public class Main {
    public static void main(String[] args) {
        UserDao userDao = new UserDaoJDBCImpl();

        userDao.createUsersTable();
        userDao.getAllUsers().forEach(System.out::println);

        userDao.saveUser("Inguk", "So", (byte) 22);
        userDao.saveUser("Suho", "Kim", (byte) 25);
        userDao.saveUser("Kai", "Park", (byte) 21);
        userDao.saveUser("Sehun", "O", (byte) 30);

        userDao.cleanUsersTable();

        userDao.dropUsersTable();
    }
}
