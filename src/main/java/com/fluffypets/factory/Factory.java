package com.fluffypets.factory;

import com.fluffypets.DAO.category.CategoryDAO;
import com.fluffypets.DAO.category.CategoryDAOImpl;
import com.fluffypets.DAO.product.ProductDAO;
import com.fluffypets.DAO.product.ProductDAOImpl;
import com.fluffypets.DAO.user.UserDAOImpl;
import com.fluffypets.MVC.controller.Controller;
import com.fluffypets.MVC.controller.users.*;
import com.fluffypets.MVC.servlets.ViewModel;
import com.fluffypets.servicies.CategoryService;
import com.fluffypets.servicies.CategoryServiceImpl;
import com.fluffypets.servicies.UserService;
import com.fluffypets.servicies.UserServiceImpl;
import exeptions.DAOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Factory {
    private static ViewModel vm = new ViewModel();
    public static Connection getConnection(){return Factory.getConnectionMySQL();}

    private static Connection getConnectionH2() {
        Connection connection = null;
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new DAOException("problem with JDBC H2 driver");
        }
        return connection;
    }

    private static Connection getConnectionMySQL() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Pets",
                    "root", "nicolas");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new DAOException("problem with JDBC MySQL driver");
        }
        return connection;
    }

    public static ViewModel getViewModel() {
        return vm;
    }
    //---------------------                 DAO                --------------------------------------------------------
    public static ProductDAO getProductDao() {
    return new ProductDAOImpl(Factory.getConnection());
    }

    public static UserDAOImpl getUserDao() {
        return new UserDAOImpl(Factory.getConnection());
    }

    public static CategoryDAO getCategoryDao() {
        return new CategoryDAOImpl(Factory.getConnection());
    }

    public static CategoryDAO categoryDaoByConnection(Connection connection) {
        return new CategoryDAOImpl(connection);
    }

    public static CategoryDAO getCategoryDAO() {
        return new CategoryDAOImpl(Factory.getConnection());
    }

    //---------------------                 Serveries                ----------------------------------------------------

    private static UserService getUserService() {
        return new UserServiceImpl(Factory.getUserDao());
    }

    public static CategoryService getCategoriesService() {
        return new CategoryServiceImpl(Factory.getCategoryDAO());
    }

    //---------------------                 get pages                ---------------------------------------------------

    public static Controller getHomeController() {
        return new HomePageController();
    }

    public static Controller getLoginPageController() {
        return new LoginPageController();
    }

    public static Controller getRegistrationPageController() {
        return new RegistrationPageController();
    }

    public static Controller getProfilePageController() {
        return new ProfilePageController();
    }

    public static Controller getForgotPassword() { return new ForgotPasswordController();  }
}
