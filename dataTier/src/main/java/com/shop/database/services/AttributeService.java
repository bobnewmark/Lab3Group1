package com.shop.database.services;

import com.shop.database.entities.Attribute;
import com.shop.database.entities.ObjectType;

import java.util.List;

/**
 * Created by said on 06.05.2017.
 */
public interface AttributeService {
    void save(Attribute attribute);
    Attribute findById(int id);
    List<Attribute> findByName(String name);
    List<Attribute> findByObjectType(ObjectType objectType);
}
