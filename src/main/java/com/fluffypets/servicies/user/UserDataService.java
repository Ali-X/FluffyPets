package com.fluffypets.servicies.user;

import com.fluffypets.mvc.model.UserData;

public interface UserDataService {

    UserData get(Integer userId);

    UserData create(UserData userData);

    UserData update(UserData userData);
}
