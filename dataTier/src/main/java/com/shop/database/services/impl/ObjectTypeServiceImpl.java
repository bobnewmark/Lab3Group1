package com.shop.database.services.impl;

import com.shop.database.entities.ObjectType;
import com.shop.database.repositories.ObjectTypeRepository;
import com.shop.database.services.ObjectTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ObjectTypeServiceImpl implements ObjectTypeService {

    @Autowired
    private ObjectTypeRepository objectTypeRepository;
    @Transactional
    public ObjectType save(ObjectType objectType) {
        return objectTypeRepository.saveAndFlush(objectType);
    }
    @Transactional(readOnly = true)
    public ObjectType findById(int id) {
        return objectTypeRepository.findById(id);
    }
    @Transactional(readOnly = true)
    public ObjectType findByName(String name) {
        return objectTypeRepository.findByName(name);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ObjectType> findAll() {
        return objectTypeRepository.findAll();
    }

}
