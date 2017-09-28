package com.fluffypets.servicies.user;

import com.fluffypets.DAO.user.UserDataDAO;
import com.fluffypets.MVC.model.UserData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserDataServiceImpl implements UserDataService,AutoCloseable {
    private static final Logger logger = LogManager.getLogger(UserDataServiceImpl.class.getName());

    private UserDataDAO userDataDao;

    public UserDataServiceImpl(UserDataDAO userDataDao) {
        this.userDataDao = userDataDao;
    }

    @Override
    public UserData get(Integer userId) {
        return userDataDao.getByUserId(userId);
    }

    @Override
    public UserData create(UserData userData) {
        return userDataDao.create(userData);
    }

    @Override
    public UserData update(UserData userData){
        return userDataDao.update(userData);
    }

    @Override
    public void close() throws Exception {
        userDataDao.close();
    }
}