package com.shop.database.repositories;


/**
 * Created by said on 06.05.2017.
 */
public interface Repo<T> {

    void insert(T entity);
    void update(T entity);
    void delete(T entity);
}
