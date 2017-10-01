package com.fluffypets.dao.user;

import com.fluffypets.dao.GenericDAO;
import com.fluffypets.mvc.model.UserAdress;

public interface UserDataDAO extends GenericDAO<UserAdress> {

    UserAdress getByUserId(Integer id);

}
