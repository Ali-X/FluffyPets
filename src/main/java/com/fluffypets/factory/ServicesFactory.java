package com.fluffypets.factory;

import com.fluffypets.servicies.*;
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
