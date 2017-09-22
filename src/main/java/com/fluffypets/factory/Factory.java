package com.fluffypets.factory;

import com.fluffypets.DAO.category.CategoryDAO;
import com.fluffypets.DAO.category.CategoryDAOImpl;
import com.fluffypets.DAO.orders.OrderDAO;
import com.fluffypets.DAO.orders.OrderDAOImpl;
import com.fluffypets.DAO.orders.OrderItemDAO;
import com.fluffypets.DAO.orders.OrderItemDAOImpl;
import com.fluffypets.DAO.product.ProductDAO;
import com.fluffypets.DAO.product.ProductDAOImpl;
import com.fluffypets.DAO.user.UserDAO;
import com.fluffypets.DAO.user.UserDAOImpl;
import com.fluffypets.DAO.user.UserDataDAOImpl;
import com.fluffypets.MVC.controller.Controller;
import com.fluffypets.MVC.controller.UploadPhotoController;
import com.fluffypets.MVC.controller.pages.*;
import com.fluffypets.MVC.controller.post.*;
import com.fluffypets.MVC.servlets.ViewModel;
import com.fluffypets.servicies.*;
import exeptions.FactoryException;
import exeptions.ServiciesException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;

public class Factory {

    private static final Logger logger = LogManager.getLogger(Factory.class.getName());

    private static ViewModel vm = new ViewModel();

    public static Connection getConnection() {
        return getContextConnection();
    }

    public static String[] getEmailPassword() {
        try {
            Context ctx = new InitialContext();
            Context initCtx = (Context) ctx.lookup("java:/comp/env");
            String userName = (String) initCtx.lookup("userName");
            String password = (String) initCtx.lookup("password");
            return new String[]{userName, password};
        } catch (NamingException e) {
            logger.error("error in getting email and password from context \n" + e);
            return null;
        }
    }

    public static Connection getContextConnection() {
        try {
            Context ctx = new InitialContext();
            Context initCtx = (Context) ctx.lookup("java:/comp/env");
            DataSource ds = (DataSource) initCtx.lookup("jdbc/Pets");
            return ds.getConnection();
        } catch (NamingException | SQLException e) {
            logger.error("get connection from TomCat error\n" + e);
            throw new FactoryException("get connection from TomCat error");
        }
    }

    public static ViewModel getViewModel() {
        return vm;
    }

    //---------------------                 DAO                --------------------------------------------------------
    public static ProductDAO getProductDao() {
        return new ProductDAOImpl(Factory.getConnection());
    }

    public static UserDAO getUserDao() {
        return new UserDAOImpl(Factory.getConnection());
    }

    public static UserDataDAOImpl getUserDataDao() {
        return new UserDataDAOImpl(Factory.getConnection());
    }

    public static CategoryDAO getCategoryDao() {
        return new CategoryDAOImpl(Factory.getConnection());
    }

    public static CategoryDAO getCategoryDAO() {
        return new CategoryDAOImpl(Factory.getConnection());
    }

    public static OrderDAO getOrderDAO() {
        return new OrderDAOImpl(Factory.getConnection());
    }

    public static OrderItemDAO getOrderItemDAO() {
        return new OrderItemDAOImpl(Factory.getConnection());
    }

    //---------------------                 Serveries                ----------------------------------------------------

    private static UserService getUserService() {
        return new UserServiceImpl(Factory.getUserDao());
    }

    private static UserDataService getUserDataService() {
        return new UserDataServiceImpl(Factory.getUserDataDao());
    }

    public static CategoryService getCategoriesService() {
        return new CategoryServiceImpl(Factory.getCategoryDAO());
    }

    public static ProductService getProductService() {
        return new ProductServiceImpl(Factory.getProductDao());
    }

    private static OrderService getOrderService() {
        return new OrderServiceImpl(Factory.getUserDataDao(), Factory.getOrderDAO());
    }

    private static SendEmailService getEmailSender(){
        String[] emeilPassword=Factory.getEmailPassword();
        if (emeilPassword != null) {
            return new SendEmailServiceImpl(emeilPassword[0],emeilPassword[1]);
        }else {
            throw new ServiciesException("falier with JNDI");
        }
    }

    //---------------------                 get pages                ---------------------------------------------------

    public static Controller getHomeController() {
        return new HomePageController(Factory.getProductService(), Factory.getCategoriesService());
    }

    public static Controller getLoginPageController() {
        return new LoginPageController();
    }

    public static Controller getLogoutPageController() {
        return new LogoutPageController();
    }

    public static Controller getRegistrationPageController() {
        return new RegistrationPageController();
    }

    public static Controller getProfilePageController() {
        return new ProfilePageController();
    }

    public static Controller getForgotPassword() {
        return new ForgotPasswordController();
    }

    public static Controller getEditUserProfileController() {
        return new EditUserProfileController();
    }

    public static Controller getProductController() {
        return new ProductController(Factory.getCategoriesService());
    }

    public static Controller getAdminPage() {
        return new AdminPageController(Factory.getUserService());
    }

    public static Controller getAdminOrdersPage() {
        return new AdminOrdersPageController(Factory.getOrderService());
    }
    //----------------------               Post handling            --------------------------------------------

    public static Controller getLoginCheckController() {
        return new LoginCheckController(Factory.getUserService(), Factory.getUserDataService());
    }

    public static Controller getSignUpCheckController() {
        return new SignUpCheckController(Factory.getUserService());
    }

    public static Controller getUserDataController() {
        return new UserDataController(Factory.getUserDataService());
    }

    public static Controller getCreateCategoryController() {
        return new CreateCategoryController(Factory.getCategoriesService());
    }

    public static Controller getCreateProductController() {
        return new CreateProductController(Factory.getProductService(), Factory.getCategoriesService());
    }

    public static Controller getSendForgotPassword() {
        return new SendForgotPasword(Factory.getUserDataService());
    }

    public static Controller getImageUploadController() {
        return new UploadPhotoController();
    }

    public static Controller getSelectGoodsController() {
        return new SelectGoodsController(Factory.getProductService(), Factory.getCategoriesService());
    }


    public static Controller getEditUserRole() {
        return new EditUserRoleController(Factory.getUserService());
    }

    public static Controller getAddProductToCartController() {
        return new AddProductToCartController(Factory.getProductService());
    }

    public static Controller getTakeProductFromCartController() {
        return new TakeProductFromCart(Factory.getProductService());
    }

    public static Controller getMakeOrderController() {
        return new MakeOrderController(Factory.getUserDataService());
    }

    public static Controller getSubmitOrderController() {
        return new SubmitOrderController(Factory.getOrderService(),Factory.getEmailSender());
    }


    public static Controller getEditOrder() {
        return new AdminEditOrdersController(Factory.getOrderService());
    }

    public static Controller getInternationalizationController() {
        return new LocaleController();
    }

    public static String md5Custom(String st,Logger logger) {
        MessageDigest messageDigest = null;
        byte[] digest = new byte[0];

        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(st.getBytes());
            digest = messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            logger.error("MD5 error "+e.getLocalizedMessage());
        }

        BigInteger bigInt = new BigInteger(1, digest);
        String md5Hex = bigInt.toString(16);

        while( md5Hex.length() < 32 ){
            md5Hex = "0" + md5Hex;
        }

        return md5Hex;
    }
}