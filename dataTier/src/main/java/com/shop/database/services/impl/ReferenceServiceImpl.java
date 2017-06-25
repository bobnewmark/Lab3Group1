package com.shop.database.services.impl;

import com.shop.database.entities.Attribute;
import com.shop.database.entities.Object;
import com.shop.database.entities.Parameter;
import com.shop.database.entities.Reference;
import com.shop.database.repositories.ReferenceRepository;
import com.shop.database.services.ReferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <code>ReferenceServiceImpl</code> is a service class for working with reference entities.
 */
@Service
public class ReferenceServiceImpl implements ReferenceService{

    @Autowired
    private ReferenceRepository referenceRepository;

    public void save(Reference attribute) {
        referenceRepository.save(attribute);
    }

    public List<Reference> findByObject(Object object) {
        List<Reference> refs = referenceRepository.findByObject(object);
        for(Reference r: refs){
            Object refObject = r.getRefObject();
            for(Parameter p: refObject.getParameters()){
                refObject.getMapParameters().put(p.getAttribute().getName(), p);
            }
        }
        return refs;
    }

    public List<Reference> findByRefObject(Object refObject) {
        return referenceRepository.findByRefObject(refObject);
    }

    @Override
    public List<Reference> findByAttribute(Attribute attribute) {
        return referenceRepository.findByAttribute(attribute);
    }

    public List<Reference> findByName(String name) {
        return referenceRepository.findByName(name);
    }

    public Reference findById(int id) {
        return referenceRepository.findById(id);
    }

    @Override
    public List<Reference> findByObjectAndRefObject(Object object, Object refObject) {
        return referenceRepository.findByObjectAndRefObject(object, refObject);
    }

    @Override
    public void delete(Reference reference) {
        referenceRepository.delete(reference);
    }
}
