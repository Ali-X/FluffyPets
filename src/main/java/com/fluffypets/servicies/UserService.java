package com.fluffypets.servicies;


import com.fluffypets.MVC.model.User;

public interface UserService {

    User getUser(User user);
    User findUser(String name, String password);
    User create(User user);
}
