package com.fluffypets.servicies.user;


import com.fluffypets.mvc.model.User;
import com.fluffypets.mvc.model.page_objects.SignUpPageInputs;
import com.fluffypets.mvc.model.page_objects.ValidationMessage;

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
