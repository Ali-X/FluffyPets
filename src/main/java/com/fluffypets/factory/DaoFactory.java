package com.fluffypets.factory;

public class DaoFactory {
    private static DaoFactory instance = new DaoFactory();

    public static DaoFactory getInstance() {
        return instance;
    }

    private DaoFactory() {
    }
}
