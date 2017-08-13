package com.fluffypets.DAO.user;

import com.fluffypets.DAO.AbstractDAO;
import com.fluffypets.DAO.GenericDAO;
import com.fluffypets.MVC.model.User;

public interface UserDAO extends GenericDAO<User> {

    User findByToken(String token);

    void createTableIfNotExists();
}
