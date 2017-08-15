package com.fluffypets.DAO;

import exeptions.DAOException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.StringJoiner;

public abstract class AbstractDAO<T> implements GenericDAO<T> {

    protected final Connection connection;

    protected AbstractDAO(Connection connection) {
        this.connection = connection;
    }

    protected abstract void createTableIfNotExists() throws DAOException;

    @Override
    public String printConnectInfo() {
        StringJoiner sj = new StringJoiner("\n");
        try {
            sj.add("DB name: " + connection.getMetaData().getDatabaseProductName());
            sj.add("DB version: " + connection.getMetaData().getDatabaseProductVersion());
            sj.add("Driver: " + connection.getMetaData().getDriverName());
            sj.add("Autocommit: " + connection.getAutoCommit());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sj.toString();
    }
}

