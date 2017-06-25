package com.shop.database.services;

import com.shop.database.entities.ObjectType;

/**
 * <code>ObjectTypeService</code> is an interface for working with object type entities in database.
 */
public interface ObjectTypeService {
    void save(ObjectType objectType);
    ObjectType findById(int id);
    ObjectType findByName(String name);
}
