package com.fluffypets.dao;

import com.fluffypets.entities.UserAddress;

public interface UserAddressDAO extends GenericDAO<UserAddress> {

    UserAddress getByUserId(Integer id);

}
