package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernate;
// import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        UserDao userDao = new UserDaoHibernate();
        userDao.createUsersTable();

        userDao.saveUser("Maral", "Abylbek", (byte) 25);

        List<User> users = userDao.getAllUsers();
        System.out.println("users: ");
        for (User user : users) {
            System.out.println(user);
        }

        userDao.removeUserById(1);

        userDao.cleanUsersTable();
    }
}
