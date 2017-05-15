package com.shop.database.services.impl;

import com.shop.database.entities.Attribute;
import com.shop.database.entities.ObjectType;
import com.shop.database.repositories.AttributeRepository;
import com.shop.database.services.AttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by said on 10.05.2017.
 */
@Service
public class AttributeServiceImpl implements AttributeService {

    @Autowired
    private AttributeRepository attributeRepository;


    public void save(Attribute attribute) {
        attributeRepository.save(attribute);
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
}
