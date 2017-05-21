package com.shop.database.services.impl;

import com.shop.database.entities.Object;
import com.shop.database.entities.Reference;
import com.shop.database.repositories.ReferenceRepository;
import com.shop.database.services.ReferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by said on 10.05.2017.
 */
@Service
public class ReferenceServiceImpl implements ReferenceService{

    @Autowired
    private ReferenceRepository referenceRepository;



    public void save(Reference attribute) {
        referenceRepository.save(attribute);
    }


    public List<Reference> findByObject(Object object) {
        return referenceRepository.findByObject(object);
    }


    public List<Reference> findByRefObject(Object refObject) {
        return referenceRepository.findByRefObject(refObject);
    }


    public List<Reference> findByName(String name) {
        return referenceRepository.findByName(name);
    }

    public Reference findById(int id) {
        return referenceRepository.findById(id);
    }
}
