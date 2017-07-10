package com.shop.database.services.impl;

import com.shop.database.entities.Attribute;
import com.shop.database.entities.ObjectType;
import com.shop.database.repositories.AttributeRepository;
import com.shop.database.services.AttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <code>AttributeServiceImpl</code> is a service class for working with attribute entities.
 */
@Service
public class AttributeServiceImpl implements AttributeService {

    @Autowired
    private AttributeRepository attributeRepository;
    @Transactional
    public Attribute save(Attribute attribute) {
        return attributeRepository.saveAndFlush(attribute);
    }
    @Transactional(readOnly = true)
    public Attribute findById(int id) {
        return attributeRepository.findById(id);
    }
    @Transactional(readOnly = true)
    public List<Attribute> findByName(String name) {
        return attributeRepository.findByName(name);
    }
    @Transactional(readOnly = true)
    public List<Attribute> findByObjectType(ObjectType objectType) {
        return attributeRepository.findByObjectType(objectType);
    }

    @Override
    @Transactional(readOnly = true)
    public Attribute findByNameAndObjectType(String name, ObjectType objectType) {
        return attributeRepository.findByNameAndObjectType(name, objectType);
    }
}
