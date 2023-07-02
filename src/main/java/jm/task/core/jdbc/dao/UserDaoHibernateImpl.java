package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.postgresql.core.Query;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();

            String createTableSql = "CREATE TABLE IF NOT EXISTS users_data " +
                    "(id SERIAL not NULL, " +
                    " name VARCHAR(255), " +
                    " lastname VARCHAR(255), " +
                    " age INT, " +
                    " PRIMARY KEY (id))";

            session.createNativeQuery(createTableSql).executeUpdate();
            session.getTransaction().commit();

            System.out.println("Таблица успешно создана.");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();

            String createTableSql = "DROP TABLE IF EXISTS users_data ";

            session.createNativeQuery(createTableSql).executeUpdate();
            session.getTransaction().commit();

            System.out.println("Таблица успешно удалена.");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            System.out.printf("User с именем - %s добавлен в базу данных.\n", name);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            User user = session.find(User.class, id);
            session.delete(user);
            session.getTransaction().commit();

            System.out.printf("User с id - %d удален из базы данных.\n", id);
            System.out.printf("User с name - %s удален из базы данных.\n", user.getName());
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            userList = session.createQuery("FROM User").getResultList();
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();

            String createTableSql = "DELETE FROM user_data";

            session.createNativeQuery(createTableSql).executeUpdate();
            session.getTransaction().commit();

            System.out.println("Таблица успешно очищена.");
        }
    }
}
