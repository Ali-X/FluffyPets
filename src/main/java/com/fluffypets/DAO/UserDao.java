package com.fluffypets.DAO;

import com.fluffypets.MVC.model.User;

public interface UserDao {

    User getUser(User user);

    User create(User user);

    User findByToken(String token);
}
