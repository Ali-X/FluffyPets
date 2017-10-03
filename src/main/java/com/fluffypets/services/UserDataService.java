package com.fluffypets.services;

import com.fluffypets.entities.UserAddress;

public interface UserDataService {

    UserAddress get(Integer userId);

    UserAddress create(UserAddress userAddress);

    UserAddress update(UserAddress userAddress);
}
