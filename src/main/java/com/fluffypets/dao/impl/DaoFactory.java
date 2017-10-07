package com.fluffypets.dao.impl;

import com.fluffypets.dao.*;
import com.fluffypets.exeptions.DAOException;
import com.fluffypets.factory.ContextFactory;
import com.ibatis.common.jdbc.ScriptRunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;

public class DaoFactory {
    private static final Logger logger = LogManager.getLogger(DaoFactory.class.getName());

    private static final StorageDAO storageDao=new StorageDAOImpl(ContextFactory.getContextConnection());

    public DaoFactory() {
        Connection connection = ContextFactory.getContextConnection();
        try {
            try {
                connection.setAutoCommit(false);
                // Initialize object for ScripRunner
                ScriptRunner sr = new ScriptRunner(connection, false, false);
                // Give the input file to Reader
                Reader reader = new BufferedReader(new FileReader("ddl.sql"));
                // Exctute script
                sr.runScript(reader);
                connection.commit();
            } catch (IOException e) {
                connection.rollback();
                logger.error("OI exception for initial ddl query " + e.getLocalizedMessage());
                throw new DAOException("OI exception for initial ddl query");
            } finally {
                connection.close();
            }
        } catch (SQLException e) {
            logger.error("SQL exception for initial ddl query " + e.getLocalizedMessage());
            throw new DAOException("SQL exception for initial ddl query");
        }
    }

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