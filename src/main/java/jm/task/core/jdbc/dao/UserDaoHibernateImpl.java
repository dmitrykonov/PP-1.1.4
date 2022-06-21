package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.*;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private static final SessionFactory sessionFactory = Util.getSessionFactory();
    private static Transaction transactional;

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try {
            Session session = sessionFactory.getCurrentSession();
            transactional = session.beginTransaction();
            session.createSQLQuery("create table if not exists users (\" +\n" +
                    "\"id BIGINT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(20),\" +\n" +
                    "\" lastName VARCHAR(20),\" +\n" +
                    "\"age TINYINT)");
            session.getTransaction().commit();
        } catch (HibernateException e) {
            transactional.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try {
            Session session = sessionFactory.getCurrentSession();
            transactional = session.beginTransaction();
            session.createSQLQuery("drop table if exists users");
            session.getTransaction().commit();
        } catch (HibernateException e) {
            transactional.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try {
            Session session = sessionFactory.getCurrentSession();
            transactional = session.beginTransaction();
            User user = (User) session.load(User.class, id);
            session.delete(user);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            transactional.rollback();
            e.printStackTrace();
        }

    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            return session.createQuery("from User ", User.class).list();
        }
    }

    @Override
    public void cleanUsersTable() {
            try {
                Session session = sessionFactory.getCurrentSession();
                transactional = session.beginTransaction();
                session.createSQLQuery("TRUNCATE TABLE user_info.users").executeUpdate();
                session.getTransaction().commit();
            } catch (HibernateException e) {
                transactional.rollback();
                e.printStackTrace();
            }

    }
}
