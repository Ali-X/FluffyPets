package com.fluffypets.servicies;

import com.fluffypets.MVC.model.UserData;

public interface UserDataService {

    UserData get(Integer userId);

    UserData create(UserData userData);

    UserData update(UserData userData);

}
