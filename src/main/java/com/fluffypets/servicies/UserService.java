package com.fluffypets.servicies;

import com.fluffypets.MVC.model.User;

public interface UserService {

  User create(User user);
  User findById(Integer id);
  User findByToken(String token);
}
