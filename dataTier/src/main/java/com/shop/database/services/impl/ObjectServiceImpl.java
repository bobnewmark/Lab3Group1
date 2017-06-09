package com.shop.database.services.impl;

import com.shop.database.entities.Object;
import com.shop.database.entities.ObjectType;
import com.shop.database.entities.Parameter;
import com.shop.database.exceptions.RegistrationException;
import com.shop.database.repositories.ObjectRepository;
import com.shop.database.repositories.ParameterRepository;
import com.shop.database.services.ObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ObjectServiceImpl implements ObjectService {

    @Autowired
    private ObjectRepository objectRepository;
    @Autowired
    private ParameterRepository parameterRepository;


    public void save(Object object) throws RegistrationException {
        for(Parameter p: object.getParameters()){
            if(p.getAttribute().isUnique()){
                for(Parameter par: parameterRepository.findByAttribute(p.getAttribute())){
                    if(p.getValue().equals(par.getValue())){
                        throw new RegistrationException();
                    }
                }
            }
        }
        objectRepository.saveAndFlush(object);
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

    @Override
    public List<Object> findByNameContaining(String keyword) {
        return objectRepository.findByNameContaining(keyword);
    }

    @Override
    public List<Object> findByNameAttrAndObjectType(String name, String otName, String value) {
        return objectRepository.findByAttrAndObjectType(name, otName, value);
    }

    @Override
    public List<Object> findByParent(Object object) {
        return objectRepository.findByParent(object);
    }
}
