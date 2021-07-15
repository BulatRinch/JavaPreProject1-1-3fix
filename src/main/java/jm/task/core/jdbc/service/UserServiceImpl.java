package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    UserDao dao = new UserDaoJDBCImpl();

    public UserServiceImpl() throws SQLException {
    }

    public void createUsersTable() throws SQLException {
        dao.createUsersTable();
    }

    public void dropUsersTable() throws SQLException {
        dao.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        dao.saveUser(name, lastName, age);
        System.out.println("User с именем – "+name+" добавлен в базу данных");
      }

    public void removeUserById(long id) throws SQLException {
        dao.removeUserById(id);
    }

    public List<User> getAllUsers() throws SQLException {
        return dao.getAllUsers();
    }

    public void cleanUsersTable() throws SQLException {
        dao.cleanUsersTable();
    }
}
