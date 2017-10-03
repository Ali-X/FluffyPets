package com.fluffypets.services.impl;

import com.fluffypets.dao.UserDAO;
import com.fluffypets.dao.impl.UserDAOImpl;
import com.fluffypets.factory.ContextFactory;
import com.fluffypets.entities.User;
import com.fluffypets.services.UserService;
import com.fluffypets.exeptions.DAOException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {

    @Override
    public User getUser(User user) {
        Connection connection = ContextFactory.getContextConnection();
        try {
            try {
                connection.setAutoCommit(false);
                UserDAO userDao = new UserDAOImpl(connection);
                User theUser = userDao.get(user);
                connection.commit();
                return theUser;
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
    public User findUser(String name, String password) {
        Connection connection = ContextFactory.getContextConnection();
        try {
            try {
                connection.setAutoCommit(false);
                UserDAO userDao = new UserDAOImpl(connection);
                User theUser = userDao.findByLoginPassword(name, password);
                connection.commit();
                return theUser;
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
    public User create(User user) {
        Connection connection = ContextFactory.getContextConnection();
        try {
            try {
                connection.setAutoCommit(false);
                UserDAO userDao = new UserDAOImpl(connection);
                User theUser = userDao.create(user);
                connection.commit();
                return theUser;
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
    public List<User> getAllUsers() {
        Connection connection = ContextFactory.getContextConnection();
        try {
            try {
                connection.setAutoCommit(false);
                UserDAO userDao = new UserDAOImpl(connection);
                List<User> users = userDao.getAllRecords();
                connection.commit();
                return users;
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
    public User changeRole(Integer userId, String role) {
        Connection connection = ContextFactory.getContextConnection();
        try {
            try {
                connection.setAutoCommit(false);
                UserDAO userDao = new UserDAOImpl(connection);
                User theUser = userDao.findById(userId);
                theUser.setRoleString(role);
                userDao.update(theUser);
                connection.commit();
                return theUser;
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
    public User findByEmail(String email) {
        Connection connection = ContextFactory.getContextConnection();
        try {
            try {
                connection.setAutoCommit(false);
                UserDAO userDao = new UserDAOImpl(connection);
                User theUser = userDao.findByEmail(email);
                connection.commit();
                return theUser;
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
    public User update(User user) {
        Connection connection = ContextFactory.getContextConnection();
        try {
            try {
                connection.setAutoCommit(false);
                UserDAO userDao = new UserDAOImpl(connection);
                User theUser = userDao.update(user);
                connection.commit();
                return theUser;
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
