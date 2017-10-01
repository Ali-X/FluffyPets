package com.fluffypets.servicies.user;

import com.fluffypets.mvc.model.UserAdress;

public interface UserDataService {

    UserAdress get(Integer userId);

    UserAdress create(UserAdress userAdress);

    UserAdress update(UserAdress userAdress);
}
