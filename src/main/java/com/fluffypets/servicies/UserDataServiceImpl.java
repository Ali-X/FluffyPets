package com.fluffypets.servicies;

import com.fluffypets.DAO.user.UserDataDAOImpl;
import com.fluffypets.MVC.model.UserData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserDataServiceImpl implements UserDataService {
    private static final Logger logger = LogManager.getLogger(UserDataServiceImpl.class.getName());

    private UserDataDAOImpl userDataDao;

    public UserDataServiceImpl(UserDataDAOImpl userDataDao) {
        this.userDataDao = userDataDao;
    }

    @Override
    public UserData get(Long userId) {
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
}
