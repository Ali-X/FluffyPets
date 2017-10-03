package com.fluffypets.dao.impl;

import com.fluffypets.dao.AbstractDAO;
import com.fluffypets.dao.UserAddressDAO;
import com.fluffypets.entities.UserAddress;
import com.fluffypets.exeptions.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserAddressDAOImpl extends AbstractDAO<UserAddress> implements UserAddressDAO, AutoCloseable {
    private static final Logger logger = LogManager.getLogger(UserAddressDAOImpl.class.getName());

    public UserAddressDAOImpl(Connection connection) {
        super(connection);
    }

    @Override
    public UserAddress create(UserAddress userAddress) {
        PreparedStatement preparedStatement = null;
        try {
            String preparedQuery = "INSERT INTO Pets.userAdress (userId,fullName,district,area,street,app,phone)" +
                    " VALUES(?,?,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setInt(1, userAddress.getUserId());
            preparedStatement.setString(2, userAddress.getFullName());
            preparedStatement.setString(3, userAddress.getDistrict());
            preparedStatement.setString(4, userAddress.getArea());
            preparedStatement.setString(5, userAddress.getStreet());
            preparedStatement.setString(6, userAddress.getApp());
            preparedStatement.setString(7, userAddress.getPhone());

            preparedStatement.execute();
            logger.info("create UserAddress query");
            return get(userAddress);
        } catch (SQLException e) {
            throw new DAOException("There are problems with new userAddress insertion to DB " + e.getLocalizedMessage());
        } finally {
            closeStatement(preparedStatement, logger);
        }
    }


    @Override
    public UserAddress delete(UserAddress userAddress) {
        PreparedStatement preparedStatement = null;
        try {
            String preparedQuery = "DELETE FROM Pets.userAdress WHERE userId = ?";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setInt(1, userAddress.getUserId());
            preparedStatement.execute();
            logger.info("delete UserAddress query");
            return get(userAddress);
        } catch (SQLException e) {
            throw new DAOException("There are problems with userAddress deleting from DB " + e.getLocalizedMessage());
        } finally {
            closeStatement(preparedStatement, logger);
        }
    }

    @Override
    public UserAddress update(UserAddress userAddress) {
        PreparedStatement preparedStatement = null;
        try {
            String preparedQuery = "UPDATE Pets.userAdress SET userId = ?," +
                    "fullName = ?, district = ?, area = ?, street = ?, app = ?, phone=? WHERE id =?";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setInt(1, userAddress.getUserId());
            preparedStatement.setString(2, userAddress.getFullName());
            preparedStatement.setString(3, userAddress.getDistrict());
            preparedStatement.setString(4, userAddress.getArea());
            preparedStatement.setString(5, userAddress.getStreet());
            preparedStatement.setString(6, userAddress.getApp());
            preparedStatement.setString(7, userAddress.getPhone());
            preparedStatement.setInt(8, userAddress.getUserDataId());
            preparedStatement.execute();
            logger.info("update UserAddress query");
        } catch (SQLException e) {
            throw new DAOException("There are problems with userAddress update in DB " + e.getLocalizedMessage());
        } finally {
            closeStatement(preparedStatement, logger);
        }
        return userAddress;
    }

    @Override
    public UserAddress get(UserAddress userAddress) {
        PreparedStatement preparedStatement = null;
        try {
            String preparedQuery = "SELECT * FROM Pets.userAdress WHERE userId = ?";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setInt(1, userAddress.getUserId());
            ResultSet resultSet = preparedStatement.executeQuery();
            List<UserAddress> resSetCont = parseResultSet(resultSet);
            if (resSetCont.size() == 0) {
                userAddress = null;
            } else {
                userAddress = resSetCont.get(0);
            }
            logger.info("get UserAddress query");
        } catch (SQLException e) {
            throw new DAOException("There are problems with getting UserAddress from DB " + e.getLocalizedMessage());
        } finally {
            closeStatement(preparedStatement, logger);
        }
        return userAddress;
    }

    @Override
    public UserAddress getByUserId(Integer id) {
        UserAddress userAddress;
        PreparedStatement preparedStatement = null;
        try {
            String preparedQuery = "SELECT * FROM Pets.userAdress WHERE userId = ?";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<UserAddress> resSetCont = parseResultSet(resultSet);
            if (resSetCont.size() == 0) {
                userAddress = null;
            } else {
                userAddress = resSetCont.get(0);
            }
        } catch (SQLException e) {
            throw new DAOException("There are problems with getting userAddress by userId from DB " + e.getLocalizedMessage());
        } finally {
            closeStatement(preparedStatement, logger);
        }
        return userAddress;
    }

    @Override
    public UserAddress findById(Integer id) {
        UserAddress userAddress;
        PreparedStatement preparedStatement = null;
        try {
            String preparedQuery = "SELECT * FROM Pets.userAdress WHERE id = ?";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<UserAddress> resSetCont = parseResultSet(resultSet);
            if (resSetCont.size() == 0) {
                userAddress = null;
            } else {
                userAddress = resSetCont.get(0);
            }
        } catch (SQLException e) {
            throw new DAOException("There are problems with getting UserAddress by Id from DB " + e.getLocalizedMessage());
        } finally {
            closeStatement(preparedStatement, logger);
        }
        return userAddress;
    }

    @Override
    public List<UserAddress> parseResultSet(ResultSet resultSet) {
        List<UserAddress> userAddressList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Integer userDataId = resultSet.getInt("id");
                Integer userId = resultSet.getInt("userId");
                String fullName = resultSet.getString("fullName");
                String district = resultSet.getString("district");
                String area = resultSet.getString("area");
                String street = resultSet.getString("street");
                String app = resultSet.getString("app");
                String phone = resultSet.getString("phone");

                userAddressList.add(new UserAddress(userDataId, userId, fullName, district, area,
                        street, app, phone));
                logger.info("UserAddress findById query");
            }
        } catch (SQLException e) {
            throw new DAOException(e.getLocalizedMessage());
        }
        return userAddressList;
    }

    @Override
    public void close() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            throw new DAOException(e.getLocalizedMessage());
        }
        logger.info("connection closed from UserAddressDAO");
    }
}
