package com.fluffypets.dao.user;

import com.fluffypets.dao.AbstractDAO;
import com.fluffypets.mvc.model.User;
import com.fluffypets.factory.ContextFactory;
import exeptions.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl extends AbstractDAO<User> implements UserDAO, AutoCloseable {
    private static final Logger logger = LogManager.getLogger(UserDAOImpl.class.getName());

    private static UserDAO instance = new UserDAOImpl();

    public static UserDAO getOrderItemDAOImpl() {
        return instance;
    }

    private UserDAOImpl() {
        super(ContextFactory.getContextConnection());
    }

    @Override
    public User create(User user) {
            PreparedStatement preparedStatement=null;
        try {
            String preparedQuery = "INSERT INTO Pets.users (userName, password, email,roleString) VALUES(?,?,?,?)";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getRoleString());
            preparedStatement.execute();
            logger.info("create user query");
            return get(user);
        } catch (SQLException e) {
            logger.error("create user query error\n"+e);
            throw new DAOException("There are problems with new user insertion to DB" + e);
        }finally {
            closeStatement(preparedStatement,logger);
        }
    }

    @Override
    public User delete(User user) {
            PreparedStatement preparedStatement=null;
        try {
            String preparedQuery = "DELETE FROM Pets.users  WHERE userName = ? AND password=?  AND email=? AND roleString=?";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getRoleString());
            preparedStatement.execute();
            logger.info("delete user query");
        } catch (SQLException e) {
            logger.error("There are problems with user deleting from DB\n"+e);
            throw new DAOException("There are problems with user deleting from DB" + e);
        }finally {
            closeStatement(preparedStatement,logger);
        }
        return user;
    }

    @Override
    public User update(User user) {
        PreparedStatement preparedStatement=null;
        try {
            String preparedQuery = "UPDATE Pets.users SET userName = ?," +
                    "password = ?, email = ?, roleString = ? WHERE id =?";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getRoleString());
            preparedStatement.setLong(5, user.getId());
            preparedStatement.execute();
            logger.info("update user query");
        } catch (SQLException e) {
            logger.error("There are problems with new user update in DB\n"+e);
            throw new DAOException("There are problems with new user update in DB" + e);
        }finally {
            closeStatement(preparedStatement,logger);
        }
        return user;
    }

    @Override
    public User get(User user) {
        PreparedStatement preparedStatement=null;
        try {
            String preparedQuery = "SELECT * FROM Pets.users WHERE userName = ? AND password = ?";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getPassword());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String userName = resultSet.getString("userName");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                String roleString = resultSet.getString("roleString");
                int id = resultSet.getInt("id");
                user = new User(id, userName, password, email, roleString);
            } else user = null;
            logger.info("get user query");
        } catch (SQLException e) {
            logger.error("There are problems with getting user from DB\n"+e);
            throw new DAOException("There are problems with getting user from DB" + e);
        }finally {
            closeStatement(preparedStatement,logger);
        }
        return user;
    }

    @Override
    public User findByLoginPassword(String login, String password) {
        PreparedStatement preparedStatement=null;
        User user;
        try {
            String preparedQuery = "SELECT * FROM Pets.users WHERE userName = ? AND password = ?";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String email = resultSet.getString("email");
                String roleString = resultSet.getString("roleString");
                int id = resultSet.getInt("id");
                user = new User(id, login, password, email, roleString);
            } else user = null;
            logger.info("findByLoginPassword user query");
        } catch (SQLException e) {
            logger.error("There are problems with getting user from DB\n"+e);
            throw new DAOException("There are problems with getting user from DB" + e);
        }finally {
            closeStatement(preparedStatement,logger);
        }
        return user;
    }

    @Override
    public User findById(Integer id) {
        PreparedStatement preparedStatement=null;
        User user;
        try {
            String preparedQuery = "SELECT * FROM Pets.users WHERE id = ?";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String userName = resultSet.getString("userName");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                String roleString = resultSet.getString("roleString");
                user = new User(id, userName, password, email, roleString);
                return user;
            }
            logger.info("findById user query");
        } catch (SQLException e) {
            logger.error("There are problems searching user by id\n"+e);
            throw new DAOException("There are problems searching user by id " + e);
        }finally {
            closeStatement(preparedStatement,logger);
        }
        return null;
    }

    @Override
    public User findByEmail(String email) {
        PreparedStatement preparedStatement=null;
        User user;
        try {
            String preparedQuery = "SELECT * FROM Pets.users WHERE email = ?";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String userName = resultSet.getString("userName");
                String password = resultSet.getString("password");
                String roleString = resultSet.getString("roleString");
                user = new User(id, userName, password, email, roleString);
                return user;
            }
            logger.info("findByEmail user query");
        } catch (SQLException e) {
            logger.error("There are problems searching user by email\n"+e);
            throw new DAOException("There are problems searching user by email " + e);
        }finally {
            closeStatement(preparedStatement,logger);
        }
        return null;
    }

    public List<User> getAllRecords() {
        PreparedStatement preparedStatement=null;
        List<User> users= new ArrayList<>();
        try {
            String preparedQuery = "SELECT * FROM Pets.users";
            preparedStatement = connection.prepareStatement(preparedQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String userName = resultSet.getString("userName");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                String roleString = resultSet.getString("roleString");
                users.add( new User(id, userName, password, email, roleString));
            }
            logger.info("get all users query");
            return users;
        } catch (SQLException e) {
            logger.error("There are problems with getting all records\n"+e);
            throw new DAOException("There are problems with getting all records" + e);
        }finally {
            closeStatement(preparedStatement,logger);
        }
    }

    @Override
    public void close()  {
        logger.info("connection close from UserDAO");
        try {
            this.connection.close();
        } catch (SQLException e) {
            throw new DAOException(e.getLocalizedMessage());
        }
    }

}
