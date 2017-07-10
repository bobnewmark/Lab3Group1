package com.shop.database.services.impl;

import com.shop.database.entities.Object;
import com.shop.database.entities.ObjectType;
import com.shop.database.entities.Parameter;
import com.shop.database.entities.Reference;
import com.shop.database.exceptions.RegistrationException;
import com.shop.database.repositories.ObjectRepository;
import com.shop.database.repositories.ParameterRepository;
import com.shop.database.services.ObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <code>ObjectServiceImpl</code> is a service class for working with object entities.
 */
@Service
public class ObjectServiceImpl implements ObjectService {

    @Autowired
    private ObjectRepository objectRepository;
    @Autowired
    private ParameterRepository parameterRepository;

    private void checkUnique(Object object) throws RegistrationException {
        for (Parameter param : object.getParameters()) {
            if (param.getAttribute().isUnique()) {
                for (Parameter par : parameterRepository.findByAttribute(param.getAttribute())) {
                    if (param.getValue().equals(par.getValue())) {
                        throw new RegistrationException();
                    }
                }
            }
        }
    }
    @Transactional
    @CacheEvict(value = "objects", allEntries=true)
    public Object save(Object object) throws RegistrationException {
        checkUnique(object);
        return objectRepository.saveAndFlush(object);
    }
    @Transactional(readOnly = true)
    public Object findById(int id) {
        Object obj = objectRepository.findOne(id);
        for (Parameter param : obj.getParameters()) {
            obj.getMapParameters().put(param.getAttribute().getName(), param);
        }
        return obj;
    }
    @Cacheable(value="objects")
    @Transactional(readOnly = true)
    public List<Object> findByName(String name) {
        List<Object> objects = objectRepository.findByName(name);
        for (Object obj : objects) {
            for (Parameter par : obj.getParameters()) {
                obj.getMapParameters().put(par.getAttribute().getName(), par);
            }
        }
        return objects;
    }
    @Cacheable(value="objects")
    @Transactional(readOnly = true)
    public List<Object> findByObjectType(ObjectType objectType) {
        List<Object> objects = objectRepository.findByObjectType(objectType);
        for (Object obj : objects) {
            for (Parameter param : obj.getParameters()) {
                obj.getMapParameters().put(param.getAttribute().getName(), param);
            }
        }
        return objects;
    }
    @Cacheable(value="objects")
    @Override
    @Transactional(readOnly = true)
    public Page<Object> findByNameContaining(String keyword, Pageable pageable) {
        Page<Object> objects = objectRepository.findByNameContaining(keyword, pageable);
        for (Object obj : objects) {
            for (Parameter param : obj.getParameters()) {
                obj.getMapParameters().put(param.getAttribute().getName(), param);
            }
        }
        return objects;
    }
    @Cacheable(value="objects")
    @Override
    @Transactional(readOnly = true)
    public List<Object> findByNameAttrAndObjectType(String name, String otName, String value) {
        List<Object> objects = objectRepository.findByAttrAndObjectType(name, otName, value);
        for (Object obj : objects) {
            for (Parameter param : obj.getParameters()) {
                obj.getMapParameters().put(param.getAttribute().getName(), param);
            }
        }
        return objects;
    }
    @Cacheable(value="objects")
    @Override
    @Transactional(readOnly = true)
    public List<Object> findByParent(Object object) {
        List<Object> objects = objectRepository.findByParent(object);
        for (Object obj : objects) {
            List<Reference> refs = obj.getReferences();
            for (Reference ref : refs) {
                Object refObject = ref.getRefObject();
                for (Parameter param : refObject.getParameters()) {
                    refObject.getMapParameters().put(param.getAttribute().getName(), param);
                }
            }
            for (Parameter param : obj.getParameters()) {
                obj.getMapParameters().put(param.getAttribute().getName(), param);
            }
        }
        return objects;
    }

    @Override
    @CacheEvict(value = "objects", allEntries=true)
    @Transactional
    public void delete(int id) {
        objectRepository.delete(id);
    }
    @Cacheable(value="objects")
    @Override
    @Transactional(readOnly = true)
    public List<Object> getObjectByAttribute(String typeName, String name, Pageable pageable) {
        List<Object> objects =  objectRepository.findByAttrAndName(typeName, name, pageable);
        for (Object obj : objects) {
            for (Parameter param : obj.getParameters()) {
                obj.getMapParameters().put(param.getAttribute().getName(), param);
            }
        }
        return objects;
    }
    @Cacheable(value="objects")
    @Override
    @Transactional(readOnly = true)
    public Page<Object> getObjectByTypes(List<String> names, Pageable pageable) {
        Page<Object> objects =  objectRepository.findByTypes(names, pageable);
        for (Object obj : objects) {
            for (Parameter param : obj.getParameters()) {
                obj.getMapParameters().put(param.getAttribute().getName(), param);
            }
        }
        return objects;
    }
    @Cacheable(value="objects")
    @Override
    @Transactional(readOnly = true)
    public Page<Object> getObjectByType(String name, Pageable pageable) {
        Page<Object> objects =  objectRepository.findByTypeName(name, pageable);
        for (Object obj : objects) {
            for (Parameter param : obj.getParameters()) {
                obj.getMapParameters().put(param.getAttribute().getName(), param);
            }
        }
        return objects;
    }
    @Cacheable(value="objects")
    @Override
    @Transactional(readOnly = true)
    public Page<Object> getAllProducts(Pageable pageable) {
        Page<Object> objects =  objectRepository.findAllProducts(pageable);
        for (Object obj : objects) {
            for (Parameter param : obj.getParameters()) {
                obj.getMapParameters().put(param.getAttribute().getName(), param);
            }
        }
        return objects;
    }

}
