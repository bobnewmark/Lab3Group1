package com.shop.database.repositories;

import com.shop.database.entities.Object;

import java.util.List;

/**
 * Created by said on 06.05.2017.
 */
public interface ObjectRepository extends Repo<Object> {

    String findNameById(int id);
    int countRows();
    Object findById(int id);
    List<Object> findByName(String name);
    List<Object> findByType(int type);
}
