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
import exeptions.ServiciesException;

public class ServicesFactory {
    static UserService getUserService() {
        return new UserServiceImpl(DaoFactory.getUserDao());
    }

    static UserDataService getUserDataService() {
        return new UserDataServiceImpl(DaoFactory.getUserDataDao());
    }

    static CategoryService getCategoriesService() {
        return new CategoryServiceImpl(DaoFactory.getCategoryDAO());
    }

    static ProductService getProductService() {
        return new ProductServiceImpl(DaoFactory.getProductDao());
    }

    static OrderService getOrderService() {
        return new OrderServiceImpl(DaoFactory.getUserDataDao(), DaoFactory.getOrderDAO());
    }

    static SendEmailService getEmailSender() {
        String[] emeilPassword = ContextFactory.getEmailPassword();
        if (emeilPassword != null) {
            return new SendEmailServiceImpl(emeilPassword[0], emeilPassword[1]);
        } else {
            throw new ServiciesException("falier with JNDI");
        }
    }
}
