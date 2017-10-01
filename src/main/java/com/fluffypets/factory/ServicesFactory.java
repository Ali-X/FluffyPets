package com.fluffypets.factory;

import com.fluffypets.servicies.email.SendEmailService;
import com.fluffypets.servicies.email.SendEmailServiceImpl;
import com.fluffypets.servicies.order.OrderService;
import com.fluffypets.servicies.order.OrderServiceImpl;
import com.fluffypets.servicies.product.CategoryService;
import com.fluffypets.servicies.product.CategoryServiceImpl;
import com.fluffypets.servicies.product.ProductService;
import com.fluffypets.servicies.product.ProductServiceImpl;
import com.fluffypets.servicies.user.UserDataService;
import com.fluffypets.servicies.user.UserDataServiceImpl;
import com.fluffypets.servicies.user.UserService;
import com.fluffypets.servicies.user.UserServiceImpl;

class ServicesFactory {
    static UserService getUserService() {
        return new UserServiceImpl();
    }

    static UserDataService getUserDataService() {
        return new UserDataServiceImpl();
    }

    static CategoryService getCategoriesService() {
        return new CategoryServiceImpl();
    }

    static ProductService getProductService() {
        return new ProductServiceImpl();
    }

    static OrderService getOrderService() {
        return new OrderServiceImpl();
    }

    static SendEmailService getEmailSender() {
        String[] emeilPassword = ContextFactory.getEmailPassword();
        return new SendEmailServiceImpl(emeilPassword[0], emeilPassword[1]);
    }
}
