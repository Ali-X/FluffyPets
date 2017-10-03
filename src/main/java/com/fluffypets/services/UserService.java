package com.fluffypets.services;


import com.fluffypets.entities.User;

import java.util.List;

public interface UserService {

    User getUser(User user);

    User findUser(String name, String password);

    User create(User user);

    List<User> getAllUsers();

    User changeRole(Integer userId, String command);

    User findByEmail(String email);

    User update(User user);
}
