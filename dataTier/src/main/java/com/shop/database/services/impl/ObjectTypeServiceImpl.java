package com.shop.database.services.impl;

import com.shop.database.entities.ObjectType;
import com.shop.database.repositories.ObjectTypeRepository;
import com.shop.database.services.ObjectTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ObjectTypeServiceImpl implements ObjectTypeService {

    @Autowired
    private ObjectTypeRepository objectTypeRepository;

    public void save(ObjectType objectType) {
        objectTypeRepository.save(objectType);
    }

    public ObjectType findById(int id) {
        return objectTypeRepository.findById(id);
    }

    public ObjectType findByName(String name) {
        return objectTypeRepository.findByName(name);
    }
}
