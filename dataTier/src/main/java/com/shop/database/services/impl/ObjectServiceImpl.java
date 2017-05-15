package com.shop.database.services.impl;

import com.shop.database.entities.Object;
import com.shop.database.entities.ObjectType;
import com.shop.database.repositories.ObjectRepository;
import com.shop.database.services.ObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by said on 10.05.2017.
 */
@Service
public class ObjectServiceImpl implements ObjectService {

    @Autowired
    private ObjectRepository objectRepository;


    public void save(Object object) {
        objectRepository.save(object);
    }


    public Object findById(int id) {
        return objectRepository.findById(id);
    }


    public List<Object> findByName(String name) {
        return objectRepository.findByName(name);
    }


    public List<Object> findByObjectType(ObjectType objectType) {
        return objectRepository.findByObjectType(objectType);
    }
}
