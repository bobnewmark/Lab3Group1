package com.shop.database.services.impl;

import com.shop.database.entities.Attribute;
import com.shop.database.entities.Parameter;
import com.shop.database.repositories.ParameterRepository;
import com.shop.database.services.ParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by said on 10.05.2017.
 */
@Service
public class ParameterServiceImpl implements ParameterService{

    @Autowired
    private ParameterRepository parameterRepository;


    public void save(Parameter parameter) {
        parameterRepository.save(parameter);
    }

    public Parameter findById(int id) {
        return parameterRepository.findById(id);
    }


    public List<Parameter> findByObject(Object object) {
        return parameterRepository.findByObject(object);
    }


    public List<Parameter> findByAttribute(Attribute attribute) {
        return parameterRepository.findByAttribute(attribute);
    }


    public List<Parameter> findByValue(String value) {
        return parameterRepository.findByValue(value);
    }

    @Override
    public Parameter findByObjectAndAttribute(int ob_id, int attr_id) {
        return parameterRepository.findByObjectAndAttribute(ob_id, attr_id);
    }
}
