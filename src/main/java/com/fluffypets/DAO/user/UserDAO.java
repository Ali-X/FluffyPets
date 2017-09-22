package com.fluffypets.DAO.user;

import com.fluffypets.DAO.GenericDAO;
import com.fluffypets.MVC.model.User;

import java.util.List;

public interface UserDAO extends GenericDAO<User> {

    User findByToken(String token);

    User findByLoginPassword(String login, String password);

    List<User> getAllRecords();
}
