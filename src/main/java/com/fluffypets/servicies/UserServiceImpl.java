package com.fluffypets.servicies;

import com.fluffypets.DAO.user.UserDAOImpl;
import com.fluffypets.MVC.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserServiceImpl implements UserService {
    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class.getName());

    private UserDAOImpl userDao;

    public UserServiceImpl(UserDAOImpl userDao) {
        this.userDao = userDao;
    }

    @Override
    public User getUser(User user) {
        return userDao.get(user);
    }

    @Override
    public User findUser(String name, String password) {
        return userDao.findByLoginPassword(name, password);
    }

    @Override
    public User create(User user) {
        return userDao.create(user);
    }
}
