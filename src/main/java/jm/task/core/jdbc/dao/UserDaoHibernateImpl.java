package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
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
        Transaction transaction = session.beginTransaction();
        session.createSQLQuery("Drop table if exists users").executeUpdate();
        transaction.commit();
        session.close();
    }


    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(new User(name, lastName, age));
        transaction.commit();
        System.out.println("User с именем – " + name + " добавлен в базу данных");


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

        List<User> list = new ArrayList<>();
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        list = session.createCriteria(User.class).list();
        transaction.commit();


        return list;
    }

    public void cleanUsersTable() {

        Session    session = Util.getSessionFactory().getCurrentSession();
           Transaction transaction = session.beginTransaction();
            session.createQuery("DELETE FROM User").executeUpdate();
            transaction.commit();


        }

}





