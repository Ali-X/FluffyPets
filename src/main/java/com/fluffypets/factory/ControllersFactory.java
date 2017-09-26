package com.fluffypets.factory;

import com.fluffypets.MVC.controller.Controller;
import com.fluffypets.MVC.controller.UploadPhotoController;
import com.fluffypets.MVC.controller.pages.*;
import com.fluffypets.MVC.controller.post.*;

public class ControllersFactory {

    //---------------------                 get pages                ---------------------------------------------------

    public static Controller getHomeController() {
        return new HomePageController(ServicesFactory.getProductService(), ServicesFactory.getCategoriesService());
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
        return new ProductController(ServicesFactory.getCategoriesService());
    }

    public static Controller getAdminPage() {
        return new AdminPageController(ServicesFactory.getUserService());
    }

    public static Controller getAdminOrdersPage() {
        return new AdminOrdersPageController(ServicesFactory.getOrderService());
    }
    //----------------------               Post handling            --------------------------------------------

    public static Controller getLoginCheckController() {
        return new LoginCheckController(ServicesFactory.getUserService(), ServicesFactory.getUserDataService());
    }

    public static Controller getSignUpCheckController() {
        return new SignUpCheckController(ServicesFactory.getUserService());
    }

    public static Controller getUserDataController() {
        return new UserDataController(ServicesFactory.getUserDataService());
    }

    public static Controller getCreateCategoryController() {
        return new CreateCategoryController(ServicesFactory.getCategoriesService());
    }

    public static Controller getCreateProductController() {
        return new CreateProductController(ServicesFactory.getProductService(), ServicesFactory.getCategoriesService());
    }

    public static Controller getSendForgotPassword() {
        return new SendForgotPasword(ServicesFactory.getUserService(),ServicesFactory.getEmailSender());
    }

    public static Controller getImageUploadController() {
        return new UploadPhotoController();
    }

    public static Controller getSelectGoodsController() {
        return new SelectGoodsController(ServicesFactory.getProductService(), ServicesFactory.getCategoriesService());
    }


    public static Controller getEditUserRole() {
        return new EditUserRoleController(ServicesFactory.getUserService());
    }

    public static Controller getAddProductToCartController() {
        return new AddProductToCartController(ServicesFactory.getProductService());
    }

    public static Controller getTakeProductFromCartController() {
        return new TakeProductFromCart(ServicesFactory.getProductService());
    }

    public static Controller getMakeOrderController() {
        return new MakeOrderController(ServicesFactory.getUserDataService());
    }

    public static Controller getSubmitOrderController() {
        return new SubmitOrderController(ServicesFactory.getOrderService(), ServicesFactory.getEmailSender());
    }

    public static Controller getRecoverPassword() {
        return new PasswordRecoveringController(DaoFactory.getUserDao());
    }

    public static Controller getUpdatePassword() {
        return new PasswordUpdateController(ServicesFactory.getUserService());
    }

    public static Controller getEditOrder() {
        return new AdminEditOrdersController(ServicesFactory.getOrderService());
    }

    public static Controller getInternationalizationController() {
        return new LocaleController();
    }

}