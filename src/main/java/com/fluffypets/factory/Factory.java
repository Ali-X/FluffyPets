package com.fluffypets.factory;

import com.fluffypets.DAO.CategoryDao;
import com.fluffypets.DAO.CategoryDaoImpl;
import com.fluffypets.DAO.UserDao;
import com.fluffypets.DAO.UserDaoImpl;
import com.fluffypets.MVC.controller.Controller;
import com.fluffypets.MVC.controller.CreateUserController;
import com.fluffypets.servicies.CategoryService;
import com.fluffypets.servicies.CategoryServiceImpl;
import com.fluffypets.servicies.UserService;
import com.fluffypets.servicies.UserServiceImpl;

import java.lang.reflect.Constructor;

public class Factory {
    protected static CategoryDao getCategoryDao() {
        return new CategoryDaoImpl();
    }

    protected static CategoryService getCategoryService(CategoryDao dao) {
        return new CategoryServiceImpl(dao);
    }

    public static Controller createCategoryController(Class<? extends Controller> clazz) {
        Controller controller = null;
        try {
            Constructor<? extends Controller> constructor = clazz.getConstructor(CategoryService.class);
            CategoryService service = getCategoryService(getCategoryDao());
            controller = constructor.newInstance(service);
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }

        return controller;
    }

    public static Controller createUserController(Class<CreateUserController> clazz) {
        Controller controller = null;
        try {
            Constructor<? extends Controller> constructor = clazz.getConstructor(UserService.class);
            UserService service = getUserService(getUserDao());
            controller = constructor.newInstance(service);
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }

        return controller;
    }

    private static UserService getUserService(UserDao userDao) {
        return new UserServiceImpl(userDao);
    }

    private static UserDao getUserDao() {
        return new UserDaoImpl();
    }
}
