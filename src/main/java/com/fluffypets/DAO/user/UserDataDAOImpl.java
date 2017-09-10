package com.fluffypets.DAO.user;

import com.fluffypets.DAO.AbstractDAO;
import com.fluffypets.MVC.model.UserData;
import exeptions.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;

public class UserDataDAOImpl extends AbstractDAO<UserData> implements UserDataDAO, AutoCloseable {
    private static final Logger logger = LogManager.getLogger(UserDataDAOImpl.class.getName());

    public UserDataDAOImpl(Connection connection) {
        super(connection);
        createTableIfNotExists();
    }

    public void createTableIfNotExists() throws DAOException {
        String initialQuery = "CREATE TABLE IF NOT EXISTS `Pets`.`userData` (" +
                "`id` INT NOT NULL AUTO_INCREMENT," +
                "`userId` INT NOT NULL," +
                "`fullName` VARCHAR(100) NOT NULL ," +
                "`dateOfBirth` DATE," +
                "`gender` VARCHAR(7) NOT NULL," +
                "`maried` BOOLEAN NOT NULL," +
                "`district` VARCHAR(30) NOT NULL," +
                "`area` VARCHAR(30) NOT NULL," +
                "`street` VARCHAR(30) NOT NULL," +
                "`app` VARCHAR(30) NOT NULL," +
                "`primaryPhone` VARCHAR(20) NOT NULL," +
                "`secondaryPhone` VARCHAR(20)," +
                "PRIMARY KEY (`id`), UNIQUE INDEX `id_UNIQUE` (`id` ASC)," +
                " CONSTRAINT FOREIGN KEY (userId) REFERENCES users(id))";
        try {
            Statement statement = connection.createStatement();
            statement.execute(initialQuery);
        } catch (SQLException e) {
            throw new DAOException("Table pages creation error");
        }
    }

    @Override
    public UserData create(UserData userData) {
        try {
            PreparedStatement preparedStatement;
            String preparedQuery = "INSERT INTO Pets.userData (" +
                    "userId,fullName,dateOfBirth,gender,maried,district,area,street,app,primaryPhone,secondaryPhone)" +
                    " VALUES(?,?,?,?,?,?,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setLong(1, userData.getUserId());
            preparedStatement.setString(2, userData.getFullName());
            preparedStatement.setDate(3, java.sql.Date.valueOf(userData.getDateOfBirth()));
            preparedStatement.setString(4, userData.getGender());
            preparedStatement.setBoolean(5, userData.getMarried());
            preparedStatement.setString(6, userData.getDistrict());
            preparedStatement.setString(7, userData.getArea());
            preparedStatement.setString(8, userData.getStreet());
            preparedStatement.setString(9, userData.getApp());
            preparedStatement.setString(10, userData.getPrimaryNumber());
            preparedStatement.setString(11, userData.getSecondaryNumber());

            preparedStatement.execute();
            logger.info("create user query");
            return get(userData);
        } catch (SQLException e) {
            logger.error("create user query error\n"+e);
            throw new DAOException("There are problems with new userData insertion to DB" + e);
        }
    }


    @Override
    public UserData delete(UserData userData) {
        try {
            PreparedStatement preparedStatement;
            String preparedQuery = "DELETE FROM Pets.userData WHERE userId = ?";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setLong(1, userData.getUserId());
            preparedStatement.execute();
            logger.info("delete user query");
            return get(userData);
        } catch (SQLException e) {
            logger.error("There are problems with userData deleting from DB\n"+e);
            throw new DAOException("There are problems with userData deleting from DB" + e);
        }
    }

    @Override
    public UserData update(UserData userData) {
        PreparedStatement preparedStatement;
        try {
            String preparedQuery = "UPDATE Pets.userData SET userId = ?," +
                    "fullName = ?, dateOfBirth = ?, gender = ?, maried = ?, " +
                    "district = ?, area = ?, street = ?, app = ?, primaryPhone=?,secondaryPhone=? " +
                    "WHERE id =?";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setLong(1, userData.getUserId());
            preparedStatement.setString(2, userData.getFullName());
            preparedStatement.setDate(3, java.sql.Date.valueOf(userData.getDateOfBirth()));
            preparedStatement.setString(4, userData.getGender());
            preparedStatement.setBoolean(5, userData.getMarried());
            preparedStatement.setString(6, userData.getDistrict());
            preparedStatement.setString(7, userData.getArea());
            preparedStatement.setString(8, userData.getStreet());
            preparedStatement.setString(9, userData.getApp());
            preparedStatement.setString(10, userData.getPrimaryNumber());
            preparedStatement.setString(11, userData.getSecondaryNumber());
            preparedStatement.setLong(12, userData.getUserDataId());
            preparedStatement.execute();
            logger.info("update user query");
        } catch (SQLException e) {
            logger.error("There are problems with userData update in DB\n"+e);
            throw new DAOException("There are problems with userData update in DB" + e);
        }
        return userData;
    }

    @Override
    public UserData get(UserData userData) {
        PreparedStatement preparedStatement;
        try {
            String preparedQuery = "SELECT * FROM Pets.userData WHERE userId = ?";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setLong(1, userData.getUserId());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Long userDataId = resultSet.getLong("id");
                Long userId = resultSet.getLong("userId");
                String fullName = resultSet.getString("fullName");
                LocalDate dateOfBirth = resultSet.getDate("dateOfBirth").toLocalDate();
                String gender = resultSet.getString("gender");
                Boolean married = resultSet.getBoolean("maried");
                String district = resultSet.getString("district");
                String area = resultSet.getString("area");
                String street = resultSet.getString("street");
                String app = resultSet.getString("app");
                String primaryNumber = resultSet.getString("primaryPhone");
                String secondaryNumber = resultSet.getString("secondaryPhone");

                userData = new UserData(userDataId, userId, fullName, dateOfBirth, gender, married, district, area,
                        street, app, primaryNumber, secondaryNumber);
            } else userData = null;
            logger.info("get user query");
        } catch (SQLException e) {
            logger.error("There are problems with getting user from DB\n"+e);
            throw new DAOException("There are problems with getting user from DB" + e);
        }
        return userData;
    }

    @Override
    public UserData getByUserId(Long id) {
        UserData userData;
        PreparedStatement preparedStatement;
        try {
            String preparedQuery = "SELECT * FROM Pets.userData WHERE userId = ?";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Long userDataId = resultSet.getLong("id");
                Long userId = resultSet.getLong("userId");
                String fullName = resultSet.getString("fullName");
                LocalDate dateOfBirth = resultSet.getDate("dateOfBirth").toLocalDate();
                String gender = resultSet.getString("gender");
                Boolean married = resultSet.getBoolean("maried");
                String district = resultSet.getString("district");
                String area = resultSet.getString("area");
                String street = resultSet.getString("street");
                String app = resultSet.getString("app");
                String primaryNumber = resultSet.getString("primaryPhone");
                String secondaryNumber = resultSet.getString("secondaryPhone");

                userData = new UserData(userDataId, userId, fullName, dateOfBirth, gender, married, district, area,
                        street, app, primaryNumber, secondaryNumber);
            } else userData = null;
        } catch (SQLException e) {
            throw new DAOException("There are problems with getting user from DB" + e);
        }
        return userData;
    }

    @Override
    public UserData findById(Integer id) {
        UserData userData=null;
        PreparedStatement preparedStatement;
        try {
            String preparedQuery = "SELECT * FROM Pets.userData WHERE id = ?";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Long userDataId = resultSet.getLong("id");
                Long userId = resultSet.getLong("userId");
                String fullName = resultSet.getString("fullName");
                LocalDate dateOfBirth = resultSet.getDate("dateOfBirth").toLocalDate();
                String gender = resultSet.getString("gender");
                Boolean married = resultSet.getBoolean("maried");
                String district = resultSet.getString("district");
                String area = resultSet.getString("area");
                String street = resultSet.getString("street");
                String app = resultSet.getString("app");
                String primaryNumber = resultSet.getString("primaryPhone");
                String secondaryNumber = resultSet.getString("secondaryPhone");

                userData = new UserData(userDataId, userId, fullName, dateOfBirth, gender, married, district, area,
                        street, app, primaryNumber, secondaryNumber);
            }
        } catch (SQLException e) {
            throw new DAOException("There are problems with getting user from DB" + e);
        }
        return userData;
    }

    @Override
    public void close() throws Exception {
        this.connection.close();
    }
}
