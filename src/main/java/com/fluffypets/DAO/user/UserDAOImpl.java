package com.fluffypets.DAO.user;

import com.fluffypets.DAO.AbstractDAO;
import com.fluffypets.MVC.model.User;
import com.fluffypets.factory.Factory;
import exeptions.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl extends AbstractDAO<User> implements UserDAO, AutoCloseable {
    private static final Logger logger = LogManager.getLogger(UserDAOImpl.class.getName());

    private static UserDAO instance = new UserDAOImpl();

    public static UserDAO getOrderItemDAOImpl() {
        return instance;
    }

    private UserDAOImpl() {
        super(Factory.getContextConnection());
        createTableIfNotExists();
    }

    @Override
    public void createTableIfNotExists() throws DAOException {
        String initialQuery = "CREATE TABLE IF NOT EXISTS `Pets`.`users` (" +
                "`id` INT NOT NULL AUTO_INCREMENT," +
                "`userName` VARCHAR(256) NOT NULL UNIQUE ," +
                "`password` VARCHAR(256) NOT NULL," +
                "`token` VARCHAR(256) NOT NULL," +
                "`email` VARCHAR(256) NOT NULL," +
                "`roleString` VARCHAR(128) NOT NULL," +
                "PRIMARY KEY (`id`), UNIQUE INDEX `id_UNIQUE` (`id` ASC))";
        Statement statement=null;
        try {
            statement = connection.createStatement();
            statement.execute(initialQuery);
            logger.info("Table users createTableIfNotExists query");
        } catch (SQLException e) {
            logger.error("Table users creation error\n"+e);
            throw new DAOException("Table users creation error");
        }finally {
            closeStatement(statement,logger);
        }
    }

    @Override
    public User create(User user) {
            PreparedStatement preparedStatement=null;
        try {
            String preparedQuery = "INSERT INTO Pets.users (userName, password, token, email,roleString) VALUES(?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getToken());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getRoleString());
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
            String preparedQuery = "DELETE FROM Pets.users  WHERE userName = ? AND password=? " +
                    "AND token=? AND email=? AND roleString=?";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getToken());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getRoleString());
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
                    "password = ?, token = ?, email = ?, roleString = ? WHERE id =?";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getToken());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getRoleString());
            preparedStatement.setLong(6, user.getId());
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
                String token = resultSet.getString("token");
                String email = resultSet.getString("email");
                String roleString = resultSet.getString("roleString");
                int id = resultSet.getInt("id");
                user = new User(id, userName, password, token, email, roleString);
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
                String token = resultSet.getString("token");
                String email = resultSet.getString("email");
                String roleString = resultSet.getString("roleString");
                int id = resultSet.getInt("id");
                user = new User(id, login, password, token, email, roleString);
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
                String token = resultSet.getString("token");
                String email = resultSet.getString("email");
                String roleString = resultSet.getString("roleString");
                user = new User(id, userName, password, token, email, roleString);
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
    public User findByToken(String token) {
        PreparedStatement preparedStatement=null;
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
            logger.info("findByToken user query");
        } catch (SQLException e) {
            logger.error("There are problems with user search by token\n"+e);
            throw new DAOException("There are problems with user search by token" + e);
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
                String token = resultSet.getString("token");
                users.add( new User(id, userName, password, token, email, roleString));
            }
            logger.info("get all users query");
            return users;
        } catch (SQLException e) {
            logger.error("There are problems with user search by token\n"+e);
            throw new DAOException("There are problems with user search by token" + e);
        }finally {
            closeStatement(preparedStatement,logger);
        }
    }

    @Override
    public void close() throws Exception {
        logger.info("connection close from UserDAO");
        this.connection.close();
    }

}
