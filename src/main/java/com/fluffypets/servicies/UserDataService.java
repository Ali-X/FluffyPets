package com.fluffypets.servicies;

import com.fluffypets.MVC.model.UserData;

public interface UserDataService {

    UserData get(Long userId);

    UserData create(UserData userData);

    UserData update(UserData userData);

}
