package com.shop.database.services.impl;

import com.shop.database.entities.Attribute;
import com.shop.database.entities.Parameter;
import com.shop.database.repositories.ParameterRepository;
import com.shop.database.services.ParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <code>ParameterServiceImpl</code> is a service class for working with parameter entities.
 */
@Service
public class ParameterServiceImpl implements ParameterService{

    @Autowired
    private ParameterRepository parameterRepository;
    @Transactional
    public Parameter save(Parameter parameter) {
        return parameterRepository.saveAndFlush(parameter);
    }
    @Transactional(readOnly = true)
    public Parameter findById(int id) {
        return parameterRepository.findById(id);
    }
    @Transactional(readOnly = true)
    public List<Parameter> findByObject(Object object) {
        return parameterRepository.findByObject(object);
    }
    @Transactional(readOnly = true)
    public List<Parameter> findByAttribute(Attribute attribute) {
        return parameterRepository.findByAttribute(attribute);
    }
    @Transactional(readOnly = true)
    public List<Parameter> findByValue(String value) {
        return parameterRepository.findByValue(value);
    }

    @Override
    @Transactional(readOnly = true)
    public Parameter findByObjectAndAttribute(int objid, int attrid) {
        return parameterRepository.findByObjectAndAttribute(objid, attrid);
    }
}
