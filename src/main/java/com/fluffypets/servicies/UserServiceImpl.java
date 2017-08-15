package com.fluffypets.servicies;

import com.fluffypets.DAO.user.UserDAOImpl;
import com.fluffypets.MVC.model.User;

public class UserServiceImpl implements UserService {
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
