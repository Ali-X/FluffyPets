package com.fluffypets.servicies.user;

import com.fluffypets.dao.user.UserDataDAO;
import com.fluffypets.dao.user.UserDataDAOImpl;
import com.fluffypets.factory.ContextFactory;
import com.fluffypets.mvc.model.UserAdress;
import exeptions.DAOException;

import java.sql.Connection;
import java.sql.SQLException;

public class UserDataServiceImpl implements UserDataService {

    @Override
    public UserAdress get(Integer userId) {
        Connection connection = ContextFactory.getContextConnection();
        try {
            try {
                connection.setAutoCommit(false);
                UserDataDAO userDataDao = new UserDataDAOImpl(connection);
                UserAdress userAdress = userDataDao.getByUserId(userId);
                connection.commit();
                return userAdress;
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
    public UserAdress create(UserAdress userAdress) {
        Connection connection = ContextFactory.getContextConnection();
        try {
            try {
                connection.setAutoCommit(false);
                UserDataDAO userDataDao = new UserDataDAOImpl(connection);
                UserAdress theUserAdress = userDataDao.create(userAdress);
                connection.commit();
                return theUserAdress;
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
    public UserAdress update(UserAdress userAdress) {
        Connection connection = ContextFactory.getContextConnection();
        try {
            try {
                connection.setAutoCommit(false);
                UserDataDAO userDataDao = new UserDataDAOImpl(connection);
                UserAdress theUserAdress = userDataDao.update(userAdress);
                connection.commit();
                return theUserAdress;
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
