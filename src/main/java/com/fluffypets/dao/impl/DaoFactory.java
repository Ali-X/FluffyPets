package com.fluffypets.dao.impl;

import com.fluffypets.dao.*;
import com.fluffypets.factory.ContextFactory;

import java.sql.Connection;

public class DaoFactory {

    private static final StorageDAO storageDao=new StorageDAOImpl(ContextFactory.getContextConnection());


    public static UserDAO getUserDAO(Connection connection) {
        return new UserDAOImpl(connection);
    }

    public static CategoryDAO getCategoryDAO(Connection connection) {
        return new CategoryDAOImpl(connection);
    }

    public static OrderDAO getOrderDAO(Connection connection) {
        return new OrderDAOImpl(connection);
    }

    public static OrderItemDAO getOrderItemDAO(Connection connection) {
        return new OrderItemDAOImpl(connection);
    }

    public static ProductDAO getProductDAO(Connection connection) {
        return new ProductDAOImpl(connection);
    }

    public static UserAddressDAO getUserAddressDAO(Connection connection) {
        return new UserAddressDAOImpl(connection);
    }

    public static StorageDAO getStorageDao(){return storageDao;}
}