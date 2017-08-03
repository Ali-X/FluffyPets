package com.fluffypets.DAO;

import com.fluffypets.MVC.model.User;

import java.util.HashSet;
import java.util.Set;

public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    private static final Set<User> userList = new HashSet<>();

    static {
        userList.add(new User(23,"Vova", "123123","Voha! You are old fa*k!!!"));
        userList.add(new User(24,"Vovan", "123123","Vovan is the same man"));
        userList.add(new User(25,"Anton", "123123","Antoha is a good boy"));
    }

    public User getUser(User user) {
        if (userList.contains(user)) {
            return user;
        } else {
            return null;
        }
    }

    @Override
    public User create(User user) {
        userList.add(user);
        return user;
    }

    @Override
    public User findByToken(String token) {
        for (User u:userList) {
            if (u.getToken().equals(token))return u;
        }
        return null;
    }

    @Override
    public User delete(User user) {
        if (userList.contains(user))userList.remove(user);
        return user;
    }

    @Override
    public User update(User user) {
        for (User u:userList) {
            if (u.getId().equals(user.getId())) {u=user;return user;}
        }
        return null;
    }

    @Override
    public User findById(Integer id) {
        for (User u:userList) {
            if (u.getId().equals(id))return u;
        }
        return null;
    }
}
