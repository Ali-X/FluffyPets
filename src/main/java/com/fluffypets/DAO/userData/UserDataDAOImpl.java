package com.fluffypets.DAO.userData;

import com.fluffypets.DAO.AbstractDAO;
import com.fluffypets.MVC.model.UserData;
import exeptions.DAOException;

import java.sql.*;

public class UserDataDAOImpl extends AbstractDAO<UserData> implements UserDataDAO, AutoCloseable {

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
                "`primaryPhone` INT NOT NULL," +
                "`secondaryPhone` INT," +
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
            preparedStatement.setLong(10, userData.getPrimaryNumber());
            preparedStatement.setLong(11, userData.getSecondaryNumber());

            preparedStatement.execute();

            return get(userData);
        } catch (SQLException e) {
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
            return get(userData);
        } catch (SQLException e) {
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
            preparedStatement.setLong(10, userData.getPrimaryNumber());
            preparedStatement.setLong(11, userData.getSecondaryNumber());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DAOException("There are problems with userData update in DB" + e);
        }
        return userData;
    }

    @Override
    public UserData get(UserData userData) {
        return null;
    }

    @Override
    public UserData findById(Integer id) {
        return null;
    }

    @Override
    public String printConnectInfo() {
        return null;
    }



    @Override
    public void close() throws Exception {
        this.connection.close();
    }
}
