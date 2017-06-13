package com.shop.database.services.impl;

import com.shop.database.entities.Object;
import com.shop.database.entities.ObjectType;
import com.shop.database.entities.Parameter;
import com.shop.database.entities.Reference;
import com.shop.database.exceptions.RegistrationException;
import com.shop.database.repositories.ObjectRepository;
import com.shop.database.repositories.ParameterRepository;
import com.shop.database.repositories.ReferenceRepository;
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
    @Autowired
    private ReferenceRepository referenceRepository;


    public void save(Object object) throws RegistrationException {
        for (Parameter p : object.getParameters()) {
            if (p.getAttribute().isUnique()) {
                for (Parameter par : parameterRepository.findByAttribute(p.getAttribute())) {
                    if (p.getValue().equals(par.getValue())) {
                        throw new RegistrationException();
                    }
                }
            }
        }
        objectRepository.saveAndFlush(object);
    }


    public Object findById(int id) {
        Object o = objectRepository.findById(id);
        for (Parameter p : o.getParameters()) {
            o.getMapParameters().put(p.getAttribute().getName(), p);
        }
        return o;
    }


    public List<Object> findByName(String name) {
        List<Object> objects = objectRepository.findByName(name);
        for (Object o : objects) {
            for (Parameter p : o.getParameters()) {
                o.getMapParameters().put(p.getAttribute().getName(), p);
            }
        }
        return objects;
    }


    public List<Object> findByObjectType(ObjectType objectType) {
        List<Object> objects = objectRepository.findByObjectType(objectType);
        for (Object o : objects) {
            for (Parameter p : o.getParameters()) {
                o.getMapParameters().put(p.getAttribute().getName(), p);
            }
        }
        return objects;
    }

    @Override
    public List<Object> findByNameContaining(String keyword) {
        List<Object> objects = objectRepository.findByNameContaining(keyword);
        for (Object o : objects) {
            for (Parameter p : o.getParameters()) {
                o.getMapParameters().put(p.getAttribute().getName(), p);
            }
        }
        return objects;
    }

    @Override
    public List<Object> findByNameAttrAndObjectType(String name, String otName, String value) {
        List<Object> objects = objectRepository.findByAttrAndObjectType(name, otName, value);
        for (Object o : objects) {
            for (Parameter p : o.getParameters()) {
                o.getMapParameters().put(p.getAttribute().getName(), p);
            }
        }
        return objects;
    }

    @Override
    public List<Object> findByParent(Object object) {
        List<Object> objects = objectRepository.findByParent(object);
        for (Object o : objects) {
            List<Reference> refs = o.getReferences();
            for (Reference r : refs) {
                Object refObject = r.getRefObject();
                for (Parameter p : refObject.getParameters()) {
                    refObject.getMapParameters().put(p.getAttribute().getName(), p);
                }
            }
            for (Parameter p : o.getParameters()) {
                o.getMapParameters().put(p.getAttribute().getName(), p);
            }
        }
        return objects;
    }

    @Override
    public void delete(int id) {
        objectRepository.delete(id);
    }

    @Override
    public Object findByLogin(String login) {
        return objectRepository.findByLogin(login);
    }
}
