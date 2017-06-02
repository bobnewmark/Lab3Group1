package com.shop.database.services;

import com.shop.database.entities.ObjectType;

import java.util.List;

/**
 * Created by said on 06.05.2017.
 */
public interface ObjectTypeService {
    void save(ObjectType objectType);
    ObjectType findById(int id);
    ObjectType findByName(String name);
}
