package com.fluffypets.DAO;

public interface GenericDao<T> {

    T create(T t);

    T delete(T t);

    T update(T t);

    T findById(Integer id);
}
