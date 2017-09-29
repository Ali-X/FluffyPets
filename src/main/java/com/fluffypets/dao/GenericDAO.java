package com.fluffypets.dao;

import java.sql.ResultSet;
import java.util.List;

public interface GenericDAO<T> {

    T create(T t);

    T delete(T t);

    T update(T t);

    T get(T t);

    T findById(Integer id);

    String printConnectInfo() ;

    void close();

    List<T> parseResultSet(ResultSet rs);
}
