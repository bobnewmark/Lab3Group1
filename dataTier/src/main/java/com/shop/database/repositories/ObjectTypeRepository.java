package com.shop.database.repositories;

import com.shop.database.entities.ObjectType;

import java.util.List;

/**
 * Created by said on 06.05.2017.
 */
public interface ObjectTypeRepository extends Repo<ObjectType> {

    String findNameById(int id);
    int countRows();
    ObjectType findById(int id);
    List<ObjectType> findByName(String name);
}
