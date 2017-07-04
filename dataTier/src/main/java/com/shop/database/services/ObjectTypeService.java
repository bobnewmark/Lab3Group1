package com.shop.database.services;

import com.shop.database.entities.ObjectType;

import java.util.List;

/**
 * <code>ObjectTypeService</code> is an interface for working with object type entities in database.
 */
public interface ObjectTypeService {
    ObjectType save(ObjectType objectType);
    ObjectType findById(int id);
    ObjectType findByName(String name);
    List<ObjectType> findAll();
}
