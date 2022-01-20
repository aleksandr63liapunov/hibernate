package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {
    }
    @Override
    public void createUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.createSQLQuery("CREATE TABLE IF NOT EXISTS users" +
                "(id mediumint not null auto_increment," +
                " name VARCHAR(45), " +
                "lastName VARCHAR(45), " +
                "age tinyint, " +
                "PRIMARY KEY (id))").executeUpdate();
        transaction.commit();

    }

    @Override
    public void dropUsersTable() {

        Session session = Util.getSessionFactory().openSession();
        Transaction transaction= session.beginTransaction();
        session.createSQLQuery("Drop table if exists users").executeUpdate();
        transaction.commit();

    }


    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction ;
        Session session = Util.getSessionFactory().openSession() ;
        transaction = session.beginTransaction();
        User user=new User();
        session.save(user);
        transaction.commit();

    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.createSQLQuery("DELETE FROM users where id").executeUpdate();
        transaction.commit();

    }

    @Override
    public List<User> getAllUsers() {

        Session session = Util.getSessionFactory().openSession() ;
        return session.createQuery("from User  User.class").list();
    }

    public void cleanUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.createSQLQuery("DELETE FROM users").executeUpdate();
        transaction.commit();


    }
}
