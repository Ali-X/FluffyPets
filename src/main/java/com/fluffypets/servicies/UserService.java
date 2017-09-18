package com.fluffypets.servicies;


import com.fluffypets.MVC.model.User;

import java.util.List;

public interface UserService {

    User getUser(User user);

    User findUser(String name, String password);

    User create(User user);

    List<User> getAllUsers();

    User changeRole(Integer userId, String command);

    void close() throws Exception;
}
