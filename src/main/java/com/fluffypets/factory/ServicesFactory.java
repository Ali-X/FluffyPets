package com.fluffypets.factory;

import com.fluffypets.services.SendEmailService;
import com.fluffypets.services.impl.SendEmailServiceImpl;
import com.fluffypets.services.OrderService;
import com.fluffypets.services.impl.OrderServiceImpl;
import com.fluffypets.services.CategoryService;
import com.fluffypets.services.impl.CategoryServiceImpl;
import com.fluffypets.services.ProductService;
import com.fluffypets.services.impl.ProductServiceImpl;
import com.fluffypets.services.UserDataService;
import com.fluffypets.services.impl.UserDataServiceImpl;
import com.fluffypets.services.UserService;
import com.fluffypets.services.impl.UserServiceImpl;

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
