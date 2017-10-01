package com.fluffypets.dao.user;

import com.fluffypets.dao.AbstractDAO;
import com.fluffypets.mvc.model.UserAdress;
import exeptions.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserDataDAOImpl extends AbstractDAO<UserAdress> implements UserDataDAO, AutoCloseable {
    private static final Logger logger = LogManager.getLogger(UserDataDAOImpl.class.getName());

    public UserDataDAOImpl(Connection connection) {
        super(connection);
    }

    @Override
    public UserAdress create(UserAdress userAdress) {
        PreparedStatement preparedStatement = null;
        try {
            String preparedQuery = "INSERT INTO Pets.userAdress (userId,fullName,district,area,street,app,phone)" +
                    " VALUES(?,?,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setInt(1, userAdress.getUserId());
            preparedStatement.setString(2, userAdress.getFullName());
            preparedStatement.setString(3, userAdress.getDistrict());
            preparedStatement.setString(4, userAdress.getArea());
            preparedStatement.setString(5, userAdress.getStreet());
            preparedStatement.setString(6, userAdress.getApp());
            preparedStatement.setString(7, userAdress.getPhone());

            preparedStatement.execute();
            logger.info("create UserAdress query");
            return get(userAdress);
        } catch (SQLException e) {
            throw new DAOException("There are problems with new userAdress insertion to DB " + e.getLocalizedMessage());
        } finally {
            closeStatement(preparedStatement, logger);
        }
    }


    @Override
    public UserAdress delete(UserAdress userAdress) {
        PreparedStatement preparedStatement = null;
        try {
            String preparedQuery = "DELETE FROM Pets.userAdress WHERE userId = ?";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setInt(1, userAdress.getUserId());
            preparedStatement.execute();
            logger.info("delete UserAdress query");
            return get(userAdress);
        } catch (SQLException e) {
            throw new DAOException("There are problems with userAdress deleting from DB " + e.getLocalizedMessage());
        } finally {
            closeStatement(preparedStatement, logger);
        }
    }

    @Override
    public UserAdress update(UserAdress userAdress) {
        PreparedStatement preparedStatement = null;
        try {
            String preparedQuery = "UPDATE Pets.userAdress SET userId = ?," +
                    "fullName = ?, district = ?, area = ?, street = ?, app = ?, phone=? WHERE id =?";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setInt(1, userAdress.getUserId());
            preparedStatement.setString(2, userAdress.getFullName());
            preparedStatement.setString(3, userAdress.getDistrict());
            preparedStatement.setString(4, userAdress.getArea());
            preparedStatement.setString(5, userAdress.getStreet());
            preparedStatement.setString(6, userAdress.getApp());
            preparedStatement.setString(7, userAdress.getPhone());
            preparedStatement.setInt(8, userAdress.getUserDataId());
            preparedStatement.execute();
            logger.info("update UserAdress query");
        } catch (SQLException e) {
            throw new DAOException("There are problems with userAdress update in DB " + e.getLocalizedMessage());
        } finally {
            closeStatement(preparedStatement, logger);
        }
        return userAdress;
    }

    @Override
    public UserAdress get(UserAdress userAdress) {
        PreparedStatement preparedStatement = null;
        try {
            String preparedQuery = "SELECT * FROM Pets.userAdress WHERE userId = ?";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setInt(1, userAdress.getUserId());
            ResultSet resultSet = preparedStatement.executeQuery();
            List<UserAdress> resSetCont = parseResultSet(resultSet);
            if (resSetCont.size() == 0) {
                userAdress = null;
            } else {
                userAdress = resSetCont.get(0);
            }
            logger.info("get UserAdress query");
        } catch (SQLException e) {
            throw new DAOException("There are problems with getting UserAdress from DB " + e.getLocalizedMessage());
        } finally {
            closeStatement(preparedStatement, logger);
        }
        return userAdress;
    }

    @Override
    public UserAdress getByUserId(Integer id) {
        UserAdress userAdress;
        PreparedStatement preparedStatement = null;
        try {
            String preparedQuery = "SELECT * FROM Pets.userAdress WHERE userId = ?";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<UserAdress> resSetCont = parseResultSet(resultSet);
            if (resSetCont.size() == 0) {
                userAdress = null;
            } else {
                userAdress = resSetCont.get(0);
            }
        } catch (SQLException e) {
            throw new DAOException("There are problems with getting userAdress by userId from DB " + e.getLocalizedMessage());
        } finally {
            closeStatement(preparedStatement, logger);
        }
        return userAdress;
    }

    @Override
    public UserAdress findById(Integer id) {
        UserAdress userAdress;
        PreparedStatement preparedStatement = null;
        try {
            String preparedQuery = "SELECT * FROM Pets.userAdress WHERE id = ?";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<UserAdress> resSetCont = parseResultSet(resultSet);
            if (resSetCont.size() == 0) {
                userAdress = null;
            } else {
                userAdress = resSetCont.get(0);
            }
        } catch (SQLException e) {
            throw new DAOException("There are problems with getting UserAdress by Id from DB " + e.getLocalizedMessage());
        } finally {
            closeStatement(preparedStatement, logger);
        }
        return userAdress;
    }

    @Override
    public List<UserAdress> parseResultSet(ResultSet resultSet) {
        List<UserAdress> userAdressList = new ArrayList<>();
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

                userAdressList.add(new UserAdress(userDataId, userId, fullName, district, area,
                        street, app, phone));
                logger.info("UserAdress findById query");
            }
        } catch (SQLException e) {
            throw new DAOException(e.getLocalizedMessage());
        }
        return userAdressList;
    }

    @Override
    public void close() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            throw new DAOException(e.getLocalizedMessage());
        }
        logger.info("connection closed from UserDataDAO");
    }
}
