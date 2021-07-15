package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Connection con = Util.getInstance().getConnection();
    PreparedStatement preparedStatement = null;


    public UserDaoJDBCImpl() throws SQLException {

    }

    public void createUsersTable() throws SQLException {

        try  {
            String sql = "CREATE TABLE IF NOT EXISTS user (id BIGINT NOT NULL AUTO_INCREMENT, " +
                    "name VARCHAR(20), lastName VARCHAR(20), age TINYINT, PRIMARY KEY (id))";
            preparedStatement = con.prepareStatement(sql);
            con.setAutoCommit(false);
            preparedStatement.execute();
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            con.rollback();
        } finally {
            con.setAutoCommit(true);
        }
    }

    public void dropUsersTable() throws SQLException{
        try  {
            String sql = "DROP TABLE IF EXISTS user";
            preparedStatement = con.prepareStatement(sql);
            con.setAutoCommit(false);
            preparedStatement.execute();
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            con.rollback();
        } finally {
            con.setAutoCommit(true);
        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException{

        try {
            String sql = "INSERT INTO user (name, lastName, age) Values (?, ?, ?)";
            preparedStatement = con.prepareStatement(sql);
            con.setAutoCommit(false);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.execute();
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            con.rollback();
        } finally {
            con.setAutoCommit(true);
        }



    }

    public void removeUserById(long id) throws SQLException {

        try  {
            String sql = "DELETE FROM user WHERE id ="+id;
            preparedStatement = con.prepareStatement(sql);
            con.setAutoCommit(false);
            preparedStatement.execute();
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            con.rollback();
        } finally {
            con.setAutoCommit(true);
        }
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> userList = new ArrayList<>();

        try {
            String sql = "SELECT ID, NAME, LASTNAME, AGE FROM user";
            preparedStatement = con.prepareStatement(sql);
            con.setAutoCommit(false);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                userList.add(user);
            }
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            con.rollback();
        } finally {
            con.setAutoCommit(true);
        }
        return userList;
    }

    public void cleanUsersTable() throws SQLException {

        try {
            String sql = "DELETE FROM user";
            preparedStatement = con.prepareStatement(sql);
            con.setAutoCommit(false);
            preparedStatement.execute();
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            con.rollback();
        } finally {
            con.setAutoCommit(true);
        }

    }
}
