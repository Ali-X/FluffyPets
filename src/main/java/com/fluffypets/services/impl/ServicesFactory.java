package com.fluffypets.services.impl;

import com.fluffypets.factory.ContextFactory;
import com.fluffypets.services.*;

public class ServicesFactory {

    private static  final UserService userService= new UserServiceImpl();
    private static  final UserDataService userDataService= new UserDataServiceImpl();
    private static  final CategoryService categoryService= new CategoryServiceImpl();
    private static  final ProductService productService= new ProductServiceImpl();
    private static  final OrderService orderService= new OrderServiceImpl();
    private static  final SendEmailServiceImpl emailSender =
            new SendEmailServiceImpl(ContextFactory.getEmail(),ContextFactory.getPassword());

    public static UserService getUserService() {
        return userService;
    }

    public static UserDataService getUserDataService() {
        return userDataService;
    }

    public static CategoryService getCategoryService() {
        return categoryService;
    }

    public static ProductService getProductService() {
        return productService;
    }

    public static OrderService getOrderService() {
        return orderService;
    }

    public static SendEmailServiceImpl getEmailSender() {
        return emailSender;
    }
}
