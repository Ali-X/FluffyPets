package com.fluffypets.dao.user;

import com.fluffypets.dao.GenericDAO;
import com.fluffypets.mvc.model.User;

import java.util.List;

public interface UserDAO extends GenericDAO<User> {

    User findByLoginPassword(String login, String password);

    List<User> getAllRecords();

    User findByEmail(String email);
}
