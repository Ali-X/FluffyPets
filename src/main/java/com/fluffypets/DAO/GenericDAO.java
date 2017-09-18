package com.fluffypets.DAO;

import java.sql.Connection;

public interface GenericDAO<T> {

    T create(T t);

    T delete(T t);

    T update(T t);

    T get(T t);

    T findById(Integer id);

    String printConnectInfo() ;

    void close() throws Exception;
}
