package com.fluffypets.DAO.user;

import com.fluffypets.DAO.AbstractDAO;
import com.fluffypets.DAO.DAOException;
import com.fluffypets.MVC.model.User;

import java.sql.*;

public class UserDAOImpl extends AbstractDAO<User> implements UserDAO, AutoCloseable {

    public UserDAOImpl(Connection connection) {
        super(connection);
        createTableIfNotExists();
    }

    @Override
    public void createTableIfNotExists() throws DAOException {
        String initialQuery = "CREATE TABLE IF NOT EXISTS `Pets`.`users` (" +
                "`id` INT NOT NULL AUTO_INCREMENT," +
                "`userName` VARCHAR(256) NOT NULL," +
                "`password` VARCHAR(256) NOT NULL," +
                "`token` VARCHAR(256) NOT NULL," +
                "`email` VARCHAR(256) NOT NULL," +
                "`roleString` VARCHAR(128) NOT NULL," +
                "PRIMARY KEY (`id`), UNIQUE INDEX `id_UNIQUE` (`id` ASC))";
        try {
            Statement statement = connection.createStatement();
            statement.execute(initialQuery);
        } catch (SQLException e) {
            throw new DAOException("Table users creation error");
        }
    }

    public User create(User user) {
        try {
            PreparedStatement preparedStatement;
            String preparedQuery = "INSERT INTO Pets.users (userName, password, token, email,roleString) VALUES(?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getToken());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getRoleString());
            preparedStatement.execute();

            return get(user);
        } catch (SQLException e) {
            throw new DAOException("There are problems with new user insertion to DB" + e);
        }
    }


    @Override
    public User delete(User user) {
        try {
            PreparedStatement preparedStatement;
            String preparedQuery = "DELETE FROM Pets.users  WHERE userName = ? AND password=? " +
                    "AND token=? AND email=? AND roleString=?";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getToken());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getRoleString());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DAOException("There are problems with user deleting from DB" + e);
        }
        return user;
    }

    @Override
    public User update(User user) {
        PreparedStatement preparedStatement;
        try {
            String preparedQuery = "UPDATE Pets.users SET userName = ?," +
                    "password = ?, token = ?, email = ?, roleString = ? WHERE id =?";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getToken());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getRoleString());
            preparedStatement.setLong(6, user.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DAOException("There are problems with new user update in DB" + e);
        }
        return user;
    }

    @Override
    public User get(User user) {
        PreparedStatement preparedStatement;
        try {
            String preparedQuery = "SELECT * FROM Pets.users WHERE userName = ? AND password = ? AND email = ?";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String userName = resultSet.getString("userName");
                String password = resultSet.getString("password");
                String token = resultSet.getString("token");
                String email = resultSet.getString("email");
                String roleString = resultSet.getString("roleString");
                int id = resultSet.getInt("id");
                user = new User(id, userName, password, token, email, roleString);
            } else user = null;
        } catch (SQLException e) {
            throw new DAOException("There are problems with getting user from DB" + e);
        }
        return user;
    }

    @Override
    public User findById(Integer id) {
        PreparedStatement preparedStatement;
        User user;
        try {
            String preparedQuery = "SELECT * FROM Pets.users WHERE id = ?";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String userName = resultSet.getString("userName");
                String password = resultSet.getString("password");
                String token = resultSet.getString("token");
                String email = resultSet.getString("email");
                String roleString = resultSet.getString("roleString");
                user = new User(id, userName, password, token, email, roleString);
                return user;
            }
        } catch (SQLException e) {
            throw new DAOException("There are problems searching user by id " + e);
        }
        return null;
    }


    @Override
    public User findByToken(String token) {
        PreparedStatement preparedStatement;
        User user;
        try {
            String preparedQuery = "SELECT * FROM Pets.users WHERE token = ?";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setString(1, token);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String userName = resultSet.getString("userName");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                String roleString = resultSet.getString("roleString");
                user = new User(id, userName, password, token, email, roleString);
                return user;
            }
        } catch (SQLException e) {
            throw new DAOException("There are problems with user search by token" + e);
        }
        return null;
    }

    @Override
    public void close() throws Exception {
        this.connection.close();
    }
}
