package com.shop.database.repositories;

import com.shop.database.entities.Attribute;

import java.util.List;

/**
 * Created by said on 06.05.2017.
 */
public interface AttributeRepository extends Repo<Attribute> {
    String findNameById(int id);
    int countRows();
    Attribute findById(int id);
    List<Attribute> findByName(String name);
    List<Attribute> findByType(int type);
}
