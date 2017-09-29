package com.fluffypets.dao.user;

import com.fluffypets.dao.GenericDAO;
import com.fluffypets.mvc.model.UserData;

public interface UserDataDAO extends GenericDAO<UserData> {

    UserData getByUserId(Integer id);

}
