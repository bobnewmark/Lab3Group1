package com.shop.database.services.impl;

import com.shop.database.entities.Attribute;
import com.shop.database.entities.Object;
import com.shop.database.entities.Parameter;
import com.shop.database.entities.Reference;
import com.shop.database.repositories.ReferenceRepository;
import com.shop.database.services.ReferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <code>ReferenceServiceImpl</code> is a service class for working with reference entities.
 */
@Service
public class ReferenceServiceImpl implements ReferenceService{

    @Autowired
    private ReferenceRepository referenceRepository;
    @Transactional
    public void save(Reference reference) {
        referenceRepository.saveAndFlush(reference);
    }
    @Transactional(readOnly = true)
    public List<Reference> findByObject(Object object) {
        List<Reference> refs = referenceRepository.findByObject(object);
        for(Reference ref: refs){
            Object refObject = ref.getRefObject();
            for(Parameter param: refObject.getParameters()){
                refObject.getMapParameters().put(param.getAttribute().getName(), param);
            }
        }
        return refs;
    }
    @Transactional(readOnly = true)
    public List<Reference> findByRefObject(Object refObject) {
        return referenceRepository.findByRefObject(refObject);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Reference> findByAttribute(Attribute attribute) {
        return referenceRepository.findByAttribute(attribute);
    }
    @Transactional(readOnly = true)
    public List<Reference> findByName(String name) {
        return referenceRepository.findByName(name);
    }
    @Transactional(readOnly = true)
    public Reference findById(int id) {
        return referenceRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Reference> findByObjectAndRefObject(Object object, Object refObject) {
        return referenceRepository.findByObjectAndRefObject(object, refObject);
    }

    @Override
    @Transactional
    public void delete(Reference reference) {
        referenceRepository.delete(reference);
    }
}
