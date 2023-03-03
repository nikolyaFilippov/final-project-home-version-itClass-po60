package by.mysite.model.dao;

import by.mysite.model.db.ConnectionManager;
import by.mysite.model.entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static by.mysite.constants.DbConstants.*;

public class UserDao {
    private static UserDao dao;

    public UserDao() {
        ConnectionManager.init();
    }

    public static UserDao getInstance() {
        return dao == null ? new UserDao() : dao;
    }

    public User getUser(String login, String password) {
        try (Connection cn = ConnectionManager.getConnection();
             PreparedStatement ps = cn.prepareStatement(SELECT_USER_BY_CREDENTIAL)) {
            ps.setString(1, login);
            ps.setString(2, password);
            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()) {
                int id = resultSet.getInt(ID_COL);
                String name = resultSet.getString(NAME_COL);
                String email = resultSet.getString(EMAIL_COL);
                return new User(id, login, name, email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean addUser(User user, String password) {
        try (Connection cn = ConnectionManager.getConnection();
             PreparedStatement ps = cn.prepareStatement(INSERT_USER)) {
            if (isAccessible(user.getLogin())) {
                ps.setString(1, user.getLogin());
                ps.setString(2, user.getName());
                ps.setString(3, password);
                ps.setString(4, user.getEmail());
                return ps.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private synchronized  boolean isAccessible(String login) throws SQLException {
        Connection cn = ConnectionManager.getConnection();
        try (PreparedStatement ps = cn.prepareStatement(SELECT_USER_ID_BY_LOGIN)) {
            ps.setString(1, login);
            return !ps.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
