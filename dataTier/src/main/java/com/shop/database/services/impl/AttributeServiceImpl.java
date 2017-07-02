package com.shop.database.services.impl;

import com.shop.database.entities.Attribute;
import com.shop.database.entities.ObjectType;
import com.shop.database.repositories.AttributeRepository;
import com.shop.database.services.AttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <code>AttributeServiceImpl</code> is a service class for working with attribute entities.
 */
@Service
public class AttributeServiceImpl implements AttributeService {

    @Autowired
    private AttributeRepository attributeRepository;

    public Attribute save(Attribute attribute) {
        return attributeRepository.saveAndFlush(attribute);
    }

    public Attribute findById(int id) {
        return attributeRepository.findById(id);
    }

    public List<Attribute> findByName(String name) {
        return attributeRepository.findByName(name);
    }

    public List<Attribute> findByObjectType(ObjectType objectType) {
        return attributeRepository.findByObjectType(objectType);
    }

    @Override
    public Attribute findByNameAndObjectType(String name, ObjectType objectType) {
        return attributeRepository.findByNameAndObjectType(name, objectType);
    }
}
