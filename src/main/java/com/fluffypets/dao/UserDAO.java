package com.fluffypets.dao;

import com.fluffypets.entities.User;

import java.util.List;

public interface UserDAO extends GenericDAO<User> {

    User findByLoginPassword(String login, String password);

    List<User> getAllRecords();

    User findByEmail(String email);
}
