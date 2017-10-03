package com.fluffypets.services.impl;

import com.fluffypets.dao.UserAddressDAO;
import com.fluffypets.dao.impl.UserAddressDAOImpl;
import com.fluffypets.factory.ContextFactory;
import com.fluffypets.entities.UserAddress;
import com.fluffypets.services.UserDataService;
import com.fluffypets.exeptions.DAOException;

import java.sql.Connection;
import java.sql.SQLException;

public class UserDataServiceImpl implements UserDataService {

    @Override
    public UserAddress get(Integer userId) {
        Connection connection = ContextFactory.getContextConnection();
        try {
            try {
                connection.setAutoCommit(false);
                UserAddressDAO userAddressDao = new UserAddressDAOImpl(connection);
                UserAddress userAddress = userAddressDao.getByUserId(userId);
                connection.commit();
                return userAddress;
            } catch (SQLException e) {
                connection.rollback();
            } finally {
                connection.close();
            }
        } catch (SQLException e) {
            throw new DAOException("Transaction error " + e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public UserAddress create(UserAddress userAddress) {
        Connection connection = ContextFactory.getContextConnection();
        try {
            try {
                connection.setAutoCommit(false);
                UserAddressDAO userAddressDao = new UserAddressDAOImpl(connection);
                UserAddress theUserAddress = userAddressDao.create(userAddress);
                connection.commit();
                return theUserAddress;
            } catch (SQLException e) {
                connection.rollback();
            } finally {
                connection.close();
            }
        } catch (SQLException e) {
            throw new DAOException("Transaction error " + e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public UserAddress update(UserAddress userAddress) {
        Connection connection = ContextFactory.getContextConnection();
        try {
            try {
                connection.setAutoCommit(false);
                UserAddressDAO userAddressDao = new UserAddressDAOImpl(connection);
                UserAddress theUserAddress = userAddressDao.update(userAddress);
                connection.commit();
                return theUserAddress;
            } catch (SQLException e) {
                connection.rollback();
            } finally {
                connection.close();
            }
        } catch (SQLException e) {
            throw new DAOException("Transaction error " + e.getLocalizedMessage());
        }
        return null;
    }


}
