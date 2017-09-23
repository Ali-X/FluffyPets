package com.fluffypets.DAO.user;

import com.fluffypets.DAO.GenericDAO;
import com.fluffypets.MVC.model.UserData;

public interface UserDataDAO extends GenericDAO<UserData> {

    UserData getByUserId(Integer id);

}
