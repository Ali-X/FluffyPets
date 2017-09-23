package com.fluffypets.DAO.user;

import com.fluffypets.DAO.AbstractDAO;
import com.fluffypets.MVC.model.UserData;
import com.fluffypets.factory.Factory;
import exeptions.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class UserDataDAOImpl extends AbstractDAO<UserData> implements UserDataDAO, AutoCloseable {
    private static final Logger logger = LogManager.getLogger(UserDataDAOImpl.class.getName());

    private static UserDataDAO instance = new UserDataDAOImpl();

    public static UserDataDAO getOrderItemDAOImpl() {
        return instance;
    }

    private UserDataDAOImpl() {
        super(Factory.getContextConnection());
    }

    @Override
    public UserData create(UserData userData) {
        PreparedStatement preparedStatement=null;
        try {
            String preparedQuery = "INSERT INTO Pets.userData (" +
                    "userId,fullName,dateOfBirth,gender,maried,district,area,street,app,primaryPhone,secondaryPhone)" +
                    " VALUES(?,?,?,?,?,?,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setInt(1, userData.getUserId());
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
            logger.info("create UserData query");
            return get(userData);
        } catch (SQLException e) {
            logger.error("create user query error\n"+e);
            throw new DAOException("There are problems with new userData insertion to DB" + e);
        }finally {
            closeStatement(preparedStatement,logger);
        }
    }


    @Override
    public UserData delete(UserData userData) {
        PreparedStatement preparedStatement=null;
        try {
            String preparedQuery = "DELETE FROM Pets.userData WHERE userId = ?";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setInt(1, userData.getUserId());
            preparedStatement.execute();
            logger.info("delete UserData query");
            return get(userData);
        } catch (SQLException e) {
            logger.error("There are problems with userData deleting from DB\n"+e);
            throw new DAOException("There are problems with userData deleting from DB" + e);
        }finally {
            closeStatement(preparedStatement,logger);
        }
    }

    @Override
    public UserData update(UserData userData) {
        PreparedStatement preparedStatement=null;
        try {
            String preparedQuery = "UPDATE Pets.userData SET userId = ?," +
                    "fullName = ?, dateOfBirth = ?, gender = ?, maried = ?, " +
                    "district = ?, area = ?, street = ?, app = ?, primaryPhone=?,secondaryPhone=? " +
                    "WHERE id =?";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setInt(1, userData.getUserId());
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
            preparedStatement.setInt(12, userData.getUserDataId());
            preparedStatement.execute();
            logger.info("update UserData query");
        } catch (SQLException e) {
            logger.error("There are problems with userData update in DB\n"+e);
            throw new DAOException("There are problems with userData update in DB" + e);
        }finally {
            closeStatement(preparedStatement,logger);
        }
        return userData;
    }

    @Override
    public UserData get(UserData userData) {
        PreparedStatement preparedStatement=null;
        try {
            String preparedQuery = "SELECT * FROM Pets.userData WHERE userId = ?";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setInt(1, userData.getUserId());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Integer userDataId = resultSet.getInt("id");
                Integer userId = resultSet.getInt("userId");
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
            logger.info("get UserData query");
        } catch (SQLException e) {
            logger.error("There are problems with getting UserData from DB\n"+e);
            throw new DAOException("There are problems with getting UserData from DB" + e);
        }finally {
            closeStatement(preparedStatement,logger);
        }
        return userData;
    }

    @Override
    public UserData getByUserId(Integer id) {
        UserData userData;
        PreparedStatement preparedStatement=null;
        try {
            String preparedQuery = "SELECT * FROM Pets.userData WHERE userId = ?";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Integer userDataId = resultSet.getInt("id");
                Integer userId = resultSet.getInt("userId");
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
                logger.info("get userData By UserId query");
            } else userData = null;
        } catch (SQLException e) {
            logger.error("There are problems with getting userData by userId from DB\n"+e);
            throw new DAOException("There are problems with getting userData by userId from DB" + e);
        }finally {
            closeStatement(preparedStatement,logger);
        }
        return userData;
    }

    @Override
    public UserData findById(Integer id) {
        UserData userData=null;
        PreparedStatement preparedStatement=null;
        try {
            String preparedQuery = "SELECT * FROM Pets.userData WHERE id = ?";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Integer userDataId = resultSet.getInt("id");
                Integer userId = resultSet.getInt("userId");
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
                logger.info("UserData findById query");
            }
        } catch (SQLException e) {
            logger.error("There are problems with getting UserData by Id from DB\n"+e);
            throw new DAOException("There are problems with getting UserData by Id from DB" + e);
        }finally {
            closeStatement(preparedStatement,logger);
        }
        return userData;
    }

    @Override
    public void close() throws Exception {
        this.connection.close();
        logger.info("connection closed from UserDataDAO");
    }
}
