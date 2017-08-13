package com.fluffypets.DAO.user;

import com.fluffypets.DAO.GenericDAO;
import com.fluffypets.MVC.model.User;

public interface UserDAO extends GenericDAO<User> {

    User findByToken(String token);

}
