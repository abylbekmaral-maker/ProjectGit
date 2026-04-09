package jm.task.core.jdbc.dao;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernate implements UserDao {


    @Override
    public void createUsersTable() {
        Session session = HibernateUtil
                .getSessionFactory()
                .openSession();

        Transaction tx = session.beginTransaction();

        session.createNativeQuery(
                "CREATE TABLE IF NOT EXISTS users (" +
                        "id BIGINT PRIMARY KEY AUTO_INCREMENT, " +
                        "name VARCHAR(255), " +
                        "lastName VARCHAR(255), " +
                        "age TINYINT" +
                        ")"
        ).executeUpdate();

        tx.commit();
        session.close();
    }

    @Override
    public void dropUsersTable() {
        Session session = HibernateUtil
                .getSessionFactory()
                .openSession();

        Transaction tx = session.beginTransaction();

        session.createNativeQuery("DROP TABLE IF EXISTS users").executeUpdate();

        tx.commit();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = HibernateUtil
                .getSessionFactory()
                .openSession();

        Transaction tx = session.beginTransaction();

        User user = new User(name, lastName, age);

        session.persist(user);

        tx.commit();
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        Session session = HibernateUtil
                .getSessionFactory()
                .openSession();

        Transaction tx = session.beginTransaction();

        User user = session.get(User.class, id);

        if (user != null) {
            session.remove(user);
        }

        tx.commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        Session session = HibernateUtil
                .getSessionFactory()
                .openSession();

        List<User> users = session
                .createQuery("from User", User.class)
                .list();

        session.close();
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Session session = HibernateUtil
                .getSessionFactory()
                .openSession();

        Transaction tx = session.beginTransaction();

        session.createNativeQuery("DELETE FROM users").executeUpdate();

        tx.commit();
        session.close();
    }
}