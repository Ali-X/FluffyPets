package com.fluffypets.servicies.user;

import com.fluffypets.dao.user.UserDataDAO;
import com.fluffypets.dao.user.UserDataDAOImpl;
import com.fluffypets.factory.ContextFactory;
import com.fluffypets.mvc.model.UserData;
import exeptions.DAOException;

import java.sql.Connection;
import java.sql.SQLException;

public class UserDataServiceImpl implements UserDataService {

    @Override
    public UserData get(Integer userId) {
        Connection connection = ContextFactory.getContextConnection();
        try {
            try {
                connection.setAutoCommit(false);
                UserDataDAO userDataDao = new UserDataDAOImpl(connection);
                UserData userData = userDataDao.getByUserId(userId);
                connection.commit();
                return userData;
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
    public UserData create(UserData userData) {
        Connection connection = ContextFactory.getContextConnection();
        try {
            try {
                connection.setAutoCommit(false);
                UserDataDAO userDataDao = new UserDataDAOImpl(connection);
                UserData theUserData = userDataDao.create(userData);
                connection.commit();
                return theUserData;
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
    public UserData update(UserData userData) {
        Connection connection = ContextFactory.getContextConnection();
        try {
            try {
                connection.setAutoCommit(false);
                UserDataDAO userDataDao = new UserDataDAOImpl(connection);
                UserData theUserData = userDataDao.update(userData);
                connection.commit();
                return theUserData;
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
