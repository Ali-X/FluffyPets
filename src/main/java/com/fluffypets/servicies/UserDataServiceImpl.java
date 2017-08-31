package com.fluffypets.servicies;

import com.fluffypets.DAO.userData.UserDataDAOImpl;
import com.fluffypets.MVC.model.UserData;

public class UserDataServiceImpl implements UserDataService {
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
